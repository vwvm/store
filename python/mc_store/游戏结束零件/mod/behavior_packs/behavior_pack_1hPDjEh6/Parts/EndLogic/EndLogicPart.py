# -*- coding: utf-8 -*-
from Preset.Model.PartBase import PartBase
from Preset.Model.GameObject import registerGenericClass
from mod.client.clientEvent import ClientEvent
from mod.server.serverEvent import ServerEvent
import Preset.Controller.PresetApi as presetApi

ModName = "EndLogicMod"
EndLogicUIName = "endLogicUI"
EndLogicUIScreenDef = "endLogicUI.main"

# Server System
ServerSystemName = "EndLogicPart"
NotifyVictoryEvent = "NotifyVictoryEvent"

# 结束游戏倒计时
UpdateGameEndSecond = "UpdateGameEndSecond"
# 重启游戏倒计时
UpdateReStartSecond = "UpdateReStartSecond"

# 结束游戏事件，收到时结束游戏（目前被大本营零件使用）
ServerEndLogicEvent = "EndLogicEvent"


# 接收广播事件->接收到预设系统的 ServerEndLogicEvent 事件时结束游戏
@registerGenericClass("EndLogicPart")
class EndLogicPart(PartBase):
	isUnique = True
	LastVersion = [0, 0, 4]

	def __init__(self):
		super(EndLogicPart, self).__init__()
		self.name = "结束游戏"
		self.endConditionType = "player"
		self.endJudgeCondition = "clockEndCondition"
		self.pcVictoryCondition = "deathNumMin"
		self.prVictoryCondition = "onlyOnePlayer"
		self.tcVictoryCondition = "queueScoreMax"
		self.trVictoryCondition = "onlyOneQueue"
		self.clockEndTime = 10
		self.endWaitPos = (0, 65, 0)
		self.showEndNotifyUIFlag = True
		self.restartGameFlag = True
		self.reStartGameTime = 5
		self.clearInvFlag = True

		# 开始游戏零件，在第一个玩家进入时获取
		self.startLogicServerPart = None
		# 队伍零件，在第一个玩家进入时获取
		self.teamServerPart = None
		# 结束判断条件列表
		self.endConditionTypeList = ["player", "team", "notEnd"]
		self.endJudgeConditionList = ["clockEndCondition", "reachVictoryCondition"]
		# 胜利判断条件列表(大本营状态的条件是否可以去掉)
		self.victoryJudgeConditionDict = {
			("team", "clockEndCondition"): ["queueScoreMax"],
			("player", "clockEndCondition"): ["deathNumMin", "killNumMax"],
			("team", "reachVictoryCondition"): ["onlyOneQueue"],
			("player", "reachVictoryCondition"): ["onlyOnePlayer"]
		}

		# 结束游戏界面
		self.endLogicUINode = None
		# 是否显示结束倒计时
		self.showGameEndSecond = True
		# 是否显示重新开启游戏倒计时
		self.showReStartSecond = True

		# 动态数据
		# 结束游戏倒计时
		self.countGameEndSecond = 0
		self.countGameEndTimer = None
		# 重新开启游戏倒计时
		self.countReStartSecond = 0
		self.countReStartTimer = None
		# 玩家死亡次数统计
		self.playerDeathNumList = {}
		# 玩家杀人次数统计
		self.playerKillNumList = {}
		self.endGameFlag = False
		self.victoryPlayerIdList = []
		# 最终 boss 死亡统计
		self.finalBossDieCount = 0
		self.players = set()

	def InitServer(self):
		self.ListenForEngineEvent(ServerEvent.AddServerPlayerEvent, self, self.OnPlayerAdd)
		self.ListenForEngineEvent(ServerEvent.PlayerDieEvent, self, self.OnPlayerDie)
		self.ListenForEngineEvent(ServerEvent.DelServerPlayerEvent, self, self.OnDelServerPlayer)
		if self.endConditionType == self.endConditionTypeList[2]:
			self.endJudgeCondition = ""
			self.ListenPresetSystemEvent(ServerEndLogicEvent, self, self.GameEnd)

		self.countGameEndSecond = self.clockEndTime
		self.countReStartSecond = self.reStartGameTime

		# 处理已经加载的Player
		for player in self.GetManager().loadedPlayers:
			self.OnPlayerAdd({"id": player})

	# AddServerPlayerEvent的回调函数，在服务器端加入玩家的时候被调用
	def OnPlayerAdd(self, data):
		self.LogInfo("OnPlayerAdd : %s", data)
		playerId = data.get("id", "-1")
		if playerId == "-1":
			return
		else:
			self.players.add(playerId)
			if len(self.playerKillNumList) == 0:
				self.FirstPlayerEnter()
			self.playerDeathNumList[playerId] = 0
			self.playerKillNumList[playerId] = 0

	def isAlive(self, playerId):
		if playerId not in self.players:
			return False
		playerObject = self.GetPlayerObject(playerId)
		if playerObject is not None:
			return playerObject.IsAlive()
		return False

	# 对是否达成胜利条件进行判断
	def VictoryJudgeWhenPlayerDie(self):
		# 不自行结束
		if self.endConditionType == self.endConditionTypeList[2]:
			return
		victorName = ""
		# 如果是个人+达成胜利条件
		players = self.playerDeathNumList
		if self.startLogicServerPart:
			players = self.startLogicServerPart.getPlayerEnsure()
		victoryJudgeConditionKey = (self.endConditionType, self.endJudgeCondition)
		victoryCondition = self.victoryJudgeConditionDict[victoryJudgeConditionKey]
		# 如果是个人+达成胜利条件
		if victoryJudgeConditionKey == (self.endConditionTypeList[0], self.endJudgeConditionList[1]):
			# 如果是仅剩一人
			if self.prVictoryCondition == victoryCondition[0]:
				livePlayerNum = 0
				livePlayerId = -1
				for playerId in players:
					if self.isAlive(playerId):
						livePlayerNum += 1
						livePlayerId = playerId
				if livePlayerNum <= 1:
					victorName = self.GetEntityName(livePlayerId)
					self.victoryPlayerIdList.append(livePlayerId)
				else:
					return
			else:
				self.LogError("Use player reachVictoryCondition Error")
				return
		# 如果是队伍+达成胜利条件
		elif victoryJudgeConditionKey == (self.endConditionTypeList[1], self.endJudgeConditionList[1]):
			# 如果是仅剩一支队伍
			if self.trVictoryCondition == victoryCondition[0]:
				# 如果存在队伍组件
				if self.teamServerPart:
					teamDict = {}
					teamPlayer = []
					for playerId in players:
						if not self.isAlive(playerId):
							continue
						teamName = self.teamServerPart.GetPlayerTeamName(playerId)
						if teamName:
							teamDict[teamName] = True
							victorName = teamName
							teamPlayer.append(playerId)
					if len(teamDict) == 1:
						for playerId in teamPlayer:
							self.victoryPlayerIdList.append(playerId)
					else:
						# 不只一个队伍存活
						return
				else:
					self.LogError("Get teamServerPart Fail")
					return
			else:
				self.LogError("Use team reachVictoryCondition Error")
				return
		# 如果是个人加塔防组件血量
		elif victoryJudgeConditionKey == (self.endConditionTypeList[0], self.endJudgeConditionList[2]):
			pass
		else:
			self.LogError("Use VictoryJudgeWhenPlayerDie Error")
			return
		self.GameEnd(victorName)

	# 系统PlayerDieEvent的回调函数，当玩家死亡时触发
	def OnPlayerDie(self, args):
		victimId = args["id"]
		attackerId = args["attacker"]
		# 玩家死亡次数统计
		self.playerDeathNumList[victimId] += 1
		# 若玩家从高空掉落不进行UI播报
		if attackerId in self.playerKillNumList:
			# 对攻击者杀死次数进行统计
			self.playerKillNumList[attackerId] += 1
		# 如果是达成胜利条件
		if self.endJudgeCondition == self.endJudgeConditionList[1]:
			# 如果不存在开始游戏组件
			if self.startLogicServerPart is None:
				self.AddTimer(0.25, self.VictoryJudgeWhenPlayerDie)
			else:
				# 如果游戏已经开始
				if self.startLogicServerPart.GetGameStartState():
					self.AddTimer(0.25, self.VictoryJudgeWhenPlayerDie)

	# 开启定时器
	def StartClock(self):
		# 如果结束判断条件是定时结束(防止外部调用时没有条件判断)
		if self.endJudgeCondition == self.endJudgeConditionList[0]:
			if self.showGameEndSecond:
				self.countGameEndTimer = self.AddRepeatedTimer(1, self.UpdateGameEndSecond)
			self.AddTimer(self.clockEndTime, self.DelayStartClock)
		# 如果结束判断条件是达成胜利条件
		elif self.endJudgeCondition == self.endJudgeConditionList[1]:
			victoryConditionOne = self.victoryJudgeConditionDict[("team", "reachVictoryCondition")]
			victoryConditionTwo = self.victoryJudgeConditionDict[("player", "reachVictoryCondition")]
			# 胜利条件是仅剩一队或一人，则游戏开开局就检查一次，处理单人开局，或随机到一队
			if (self.trVictoryCondition == victoryConditionOne[0] and self.endConditionType == self.endConditionTypeList[1]) or \
				(self.prVictoryCondition == victoryConditionTwo[0] and self.endConditionType == self.endConditionTypeList[0]):
				self.AddTimer(4, self.VictoryJudgeWhenPlayerDie)

	def FirstPlayerEnter(self):
		# 获取开始游戏组件
		self.startLogicServerPart = presetApi.GetGameObjectByTypeName("StartLogicPart")
		# 获取队伍组件
		self.teamServerPart = presetApi.GetGameObjectByTypeName("TeamPart")
		# 如果不存在开始游戏组件
		if self.startLogicServerPart is None:
			# 如果结束判断条件是定时结束
			if self.endJudgeCondition == self.endJudgeConditionList[0]:
				self.StartClock()

	# 通知胜利信息
	def NotifyVictory(self, victoryName):
		self.LogInfo("=================victoryName={0}=================".format(victoryName))
		text = "§6{0}§f获得胜利".format(victoryName)
		textInfo = self.CreateEventData()
		textInfo["text"] = text
		if self.startLogicServerPart:
			playerEnsure = self.startLogicServerPart.getPlayerEnsure()
			for player in playerEnsure:
				self.NotifyToClient(player, NotifyVictoryEvent, textInfo)
		else:
			self.BroadcastToAllClient(NotifyVictoryEvent, textInfo)
		self.BroadcastEvent(NotifyVictoryEvent, textInfo)

	# 更新游戏结束倒计时
	def UpdateGameEndSecond(self):
		if self.showGameEndSecond:
			if self.countGameEndSecond >= 0:
				self.countGameEndSecond -= 1
				textInfo = self.CreateEventData()
				textInfo["text"] = "游戏结束\n 倒计时"
				textInfo["visiable"] = True
				textInfo["second"] = self.countGameEndSecond
				self.BroadcastToAllClient(UpdateGameEndSecond, textInfo)

	# 更新游戏重新开始倒计时
	def UpdateReStartSecond(self):
		if self.showReStartSecond:
			if self.countReStartSecond > 0:
				self.countReStartSecond -= 1
				textInfo = self.CreateEventData()
				textInfo["text"] = "重启游戏\n 倒计时"
				textInfo["visiable"] = True
				textInfo["second"] = self.countReStartSecond
				self.BroadcastToAllClient(UpdateReStartSecond, textInfo)

	# 定时器到时
	def DelayStartClock(self):
		if self.showGameEndSecond:
			textInfo = self.CreateEventData()
			textInfo["visiable"] = False
			textInfo["second"] = self.clockEndTime
			self.BroadcastToAllClient(UpdateGameEndSecond, textInfo)
			self.CancelTimer(self.countGameEndTimer)
		victorName = ""
		players = self.playerDeathNumList
		if self.startLogicServerPart:
			players = self.startLogicServerPart.getPlayerEnsure()
		# 开启倒计时则结束判断条件是定时结束
		# 获胜条件
		victoryJudgeConditionKey = (self.endConditionType, self.endJudgeCondition)
		victoryCondition = self.victoryJudgeConditionDict[victoryJudgeConditionKey]
		# 如果结束条件是个人+定时
		if victoryJudgeConditionKey == (self.endConditionTypeList[0], self.endJudgeConditionList[0]):
			victoryPlayerIdList = []
			# 如果获胜条件是死亡次数最少
			if self.pcVictoryCondition == victoryCondition[0]:
				deathNumMin = min(self.playerDeathNumList[i] for i in players)
				for player in players:
					if self.playerDeathNumList[player] == deathNumMin:
						victoryPlayerIdList.append(player)
				self.victoryPlayerIdList = victoryPlayerIdList
			# 如果是杀人数最多获胜
			elif self.pcVictoryCondition == victoryCondition[1]:
				killNumMax = max(self.playerKillNumList[i] for i in players)
				for player in players:
					if self.playerKillNumList[player] == killNumMax:
						victoryPlayerIdList.append(player)
				self.victoryPlayerIdList = victoryPlayerIdList
			else:
				self.LogError("player clockEndCondition judge Error")
				return
			# 考虑并列第一
			for i in range(len(victoryPlayerIdList)):
				playerId = victoryPlayerIdList[i]
				victorName += self.GetEntityName(playerId)
				if i != len(victoryPlayerIdList) - 1:
					victorName += ","
		# 如果是队伍+定时结束
		elif victoryJudgeConditionKey == (self.endConditionTypeList[1], self.endJudgeConditionList[0]):
			# 如果队伍组件存在
			if self.teamServerPart:
				queueScoreList = self.teamServerPart.GetQueueScoreList()
				data = self.teamServerPart.GetQueueNameInfo()
				queueNameList = data["queueNameList"]
				playerQueueMapList = self.teamServerPart.GetPlayerQueueMap()
				victoryQueueNameList = []
				scoreMax = max(queueScoreList.values())
				for queueName in queueScoreList:
					if queueScoreList[queueName] == scoreMax:
						victoryQueueNameList.append(queueName)
						queueIndex = queueNameList.index(queueName)
						for playerId in self.playerDeathNumList:
							if playerId in playerQueueMapList:
								if playerQueueMapList[playerId] == queueIndex:
									self.victoryPlayerIdList.append(playerId)
				# 考虑并列第一
				for i in range(len(victoryQueueNameList)):
					victorName += victoryQueueNameList[i]  # .encode("-utf8")
					if i != len(victoryQueueNameList) - 1:
						victorName += ","
			else:
				self.LogInfo("Get teamServerSystem Fail")
				return
		else:
			self.LogError("Use StartClock Error")
			return
		self.GameEnd(victorName)

	# 游戏结束
	def GameEnd(self, victorName):
		print ('GameEnd')
		self.endGameFlag = True
		# 进行通知
		if victorName:
			self.NotifyVictory(victorName)
		if self.clearInvFlag:
			for playerId in self.playerKillNumList:
				for i in xrange(36):
					self.SetInvItemNum(playerId, i, 0)
		# 自动开始下一轮
		if self.restartGameFlag:
			self.AddTimer(self.reStartGameTime, self.ReStartGame)
			if self.showReStartSecond:
				self.countReStartTimer = self.AddRepeatedTimer(1, self.UpdateReStartSecond)

	# 重设动态数据
	def ReSetDynamicData(self):
		for player in self.playerDeathNumList:
			self.playerDeathNumList[player] = 0
			self.playerKillNumList[player] = 0

	# 重新开始游戏
	def ReStartGame(self):
		if self.showReStartSecond:
			textInfo = self.CreateEventData()
			textInfo["visiable"] = False
			textInfo["second"] = self.reStartGameTime
			self.BroadcastToAllClient(UpdateReStartSecond, textInfo)
			self.CancelTimer(self.countReStartTimer)
		self.endGameFlag = False
		self.countGameEndSecond = self.clockEndTime
		self.countReStartSecond = self.reStartGameTime
		self.victoryPlayerIdList = []
		self.ReSetDynamicData()
		if self.startLogicServerPart is None:
			# 如果结束判断条件是定时结束
			if self.endJudgeCondition == self.endJudgeConditionList[0]:
				self.StartClock()
		else:
			# 如果有开始组件
			self.startLogicServerPart.ReStartGame()
		self.LogInfo("=================ReStartGame============")

	def OnDelServerPlayer(self, args):
		if self.endConditionType == self.endConditionTypeList[2]:
			return
		playerId = args["id"]
		if playerId in self.players:
			self.players.remove(playerId)
		# 如果结束方式不是个人+定时+击杀次数最多(即在这种方式下即便玩家离开，仍保留玩家数据，但只要玩家杀人数也足够多，也可能获胜)
		victoryCondition = self.victoryJudgeConditionDict[(self.endConditionTypeList[0], self.endJudgeConditionList[0])][1]
		victoryJudgeConditionKey = (self.endConditionType, self.endJudgeCondition)
		if victoryJudgeConditionKey != (self.endConditionTypeList[0], self.endJudgeConditionList[0]) and self.pcVictoryCondition != victoryCondition:
			self.playerKillNumList.pop(playerId)
			self.playerDeathNumList.pop(playerId)
		# 如果是达成胜利条件
		if self.endJudgeCondition == self.endJudgeConditionList[1]:
			# 如果不存在开始游戏组件
			if self.startLogicServerPart is None:
				self.VictoryJudgeWhenPlayerDie()
			else:
				# 如果游戏已经开始
				if self.startLogicServerPart.GetGameStartState():
					self.VictoryJudgeWhenPlayerDie()

	# 可能是外部调用的函数
	def GetVictoryPlayerList(self):
		return self.victoryPlayerIdList

	# 可能是外部调用的函数
	def GetGameEndState(self):
		return self.endGameFlag

	def DestroyServer(self):
		self.UnListenForEngineEvent(ServerEvent.AddServerPlayerEvent, self, self.OnPlayerAdd)
		self.UnListenForEngineEvent(ServerEvent.PlayerDieEvent, self, self.OnPlayerDie)
		self.UnListenForEngineEvent(ServerEvent.DelServerPlayerEvent, self, self.OnDelServerPlayer)
		if self.endConditionType == self.endConditionTypeList[2]:
			self.UnListenPresetSystemEvent(ServerEndLogicEvent, self, self.GameEnd)

	def OnTickClient(self):
		return

	def OnUpdateCountDownSecond(self, text):
		if self.endLogicUINode is None:
			self.LogInfo("endlogicpart OnUpdateCountDownSecond warning")
			return
		if "text" in text:
			self.endLogicUINode.UpdateCountDownSecondType(text["text"])
		if "second" in text:
			self.endLogicUINode.UpdateCountDownSecond(text["second"])
		if "visiable" in text:
			self.endLogicUINode.ShowCountDownPanel(text["visiable"])

	def IsEndTeamType(self):
		return self.endConditionType == self.endConditionTypeList[1]

	def InitClient(self):
		if self.endConditionType == self.endConditionTypeList[2]:
			self.endJudgeCondition = ""
		self.ListenForEngineEvent(ClientEvent.UiInitFinished, self, self.OnUIInitFinished)
		self.ListenSelfEvent(NotifyVictoryEvent, self, self.OnNotifyVictoryEvent)
		self.ListenSelfEvent(UpdateGameEndSecond, self, self.OnUpdateCountDownSecond)
		self.ListenSelfEvent(UpdateReStartSecond, self, self.OnUpdateCountDownSecond)

	def DestroyClient(self):
		self.UnListenForEngineEvent(ClientEvent.UiInitFinished, self, self.OnUIInitFinished)
		self.UnListenSelfEvent(NotifyVictoryEvent, self, self.OnNotifyVictoryEvent)
		self.UnListenSelfEvent(UpdateGameEndSecond, self, self.OnUpdateCountDownSecond)
		self.UnListenSelfEvent(UpdateReStartSecond, self, self.OnUpdateCountDownSecond)

	def OnUIInitFinished(self, args):
		from endLogicUI import EndLogicUIScreen
		cls = '{}.{}'.format(EndLogicUIScreen.__module__, EndLogicUIScreen.__name__)
		uiApi = self.GetApi()
		uiApi.RegisterUI(ModName, EndLogicUIName, cls, EndLogicUIScreenDef)
		uiApi.CreateUI(ModName, EndLogicUIName, {"isHud": 1})
		self.endLogicUINode = uiApi.GetUI(ModName, EndLogicUIName)
		if self.endLogicUINode:
			self.endLogicUINode.Init()
		else:
			self.LogError("create ui %s failed!" % "endLogicUI")

	def OnNotifyVictoryEvent(self, args):
		if self.showEndNotifyUIFlag:
			text = args["text"]
			self.endLogicUINode.ShowNotifyPanel(text)
			if self.restartGameFlag:
				self.AddTimer(self.reStartGameTime, self.endLogicUINode.HideNotifyPanel)

	def TickClient(self):
		pass

# -*- coding: utf-8 -*-
from Preset.Model.PartBase import PartBase
from Preset.Model.GameObject import registerGenericClass
from mod.server.serverEvent import ServerEvent
from mod.client.clientEvent import ClientEvent
from Preset.Model.Entity.EntityPreset import EntityPreset

@registerGenericClass("LevelUpPart")
class LevelUpPart(PartBase):
	def __init__(self):
		PartBase.__init__(self)
		# 零件名称
		self.name = "升级零件"
		self.item = ('minecraft:apple', 0)
		self.levelList = [{'upgradeType': 2, 'upgradeNum': 2, 'nextNeedNum': 1}]
		self.uiLoad = False
		self.level = 0
		self.replicated = ['level']
		self.myDamage = 2

	def InitServer(self):
		PartBase.InitServer(self)
		self.ListenForEngineEvent('DamageEvent', self, self.DamageEvent)
		self.ListenSelfEvent('UIClickUpgrade', self, self.SUIClickUpgrade)
		self.ListenSelfEvent('UIClickRemove', self, self.SUIClickRemove)

	def CanAdd(self, parent):
		if not isinstance(parent, EntityPreset):
			return "升级零件只能挂在实体预设下"
		if parent.GetPartByType(self.classType):
			return "同一个实体预设只能挂接一个升级零件"


	def DamageEvent(self, e):
		parent = self.GetParent()
		if not parent or not parent.entityId:
			return
		if e['entityId'] == parent.entityId and e['srcId'] in self.GetLoadedPlayers():
			self.NotifyToClient(e['srcId'], 'PlayerClickEntity', {'entityId': e['entityId']})
		if e['srcId'] == parent.entityId:
			e['damage'] = self.myDamage

	def InitClient(self):
		PartBase.InitClient(self)
		self.ListenForEngineEvent(ClientEvent.UiInitFinished, self, self.COnUIInitFinished)
		self.RegisterUI()
		self.ListenSelfEvent('PlayerClickEntity', self, self.PlayerClickEntity)
		self.ListenSelfEvent('UIClickUpgradeCallback', self, self.UIClickUpgradeCallback)

	def SUIClickRemove(self, e):
		parent = self.GetParent()
		name = self.GetEntityName(e['client'])
		for client in self.GetLoadedPlayers():
			self.NotifyOneMessage(client, '%s 撤下了 %s' % (name, parent.entityId))
		self.DestroyEntity(parent.entityId)
		parent.Unload()
		parent.Destroy()

	def PlayerClickEntity(self, e):
		parent = self.GetParent()
		if not parent or not parent.entityId:
			return
		doll1 = 'lv%s' % (self.level + 1)
		if self.level + 1 <= len(self.levelList):
			doll2 = 'lv%s' % (self.level + 2)
			data = self.levelList[self.level]
			upgradeNum = data['upgradeNum']
			nextNeedNum = data['nextNeedNum']
			upgradeType = data['upgradeType']
			# from LevelUpPartMeta import UpgradeType
			UpgradeType = {
				self.GetMinecraftEnum().AttrType.HEALTH: '生命值',
				self.GetMinecraftEnum().AttrType.DAMAGE: '攻击力',
				self.GetMinecraftEnum().AttrType.SPEED: '速度'
			}
			name = UpgradeType[upgradeType]
			before = self.GetEntityAttrValue(parent.entityId, upgradeType)
			if upgradeType == self.GetMinecraftEnum().AttrType.DAMAGE:
				before = self.myDamage
			after = before + upgradeNum
			text = '当前{0}: {1}\n升级后{0}: {2}\n消耗数量: {3}\n消耗物品:'.format(name, before, after, nextNeedNum)
		else:
			doll2 = doll1
			text = '&a满级'
		entityTypeStr = self.GetEntityEngineTypeStr(parent.entityId)
		entityName = self.GetChinese('entity.%s.name' % entityTypeStr)
		if not entityName:
			entityName = '未知'
		uiApi = self.GetApi()
		def UpgradeButtonClick(_):
			self.NotifyToServer('UIClickUpgrade', {'client': self.GetLocalPlayerId()})
		def RemoveButtonClick(_):
			uiApi.PopScreen()
			self.NotifyToServer('UIClickRemove', {'client': self.GetLocalPlayerId()})
		from levelUpPartUI import UIName
		uiNode = uiApi.PushScreen(self.__class__.__name__, UIName)
		if not uiNode:
			return
		uiNode.InitParam({
			'entityId': e['entityId'],
			'title': '为%s[id:%s]升级' % (entityName, e['entityId']),
			'doll1': doll1,
			'doll2': doll2,
			'text': text,
			'item': self.item,
			'renderItem': doll2 != doll1,
			'upgradeButtonCallback': UpgradeButtonClick,
			'removeButtonCallback': RemoveButtonClick,
		})

	def SUIClickUpgrade(self, e):
		print 'SUIClickUpgrade'
		def Upgrade(client):
			if not self.level + 1 <= len(self.levelList):
				return False, '已经达到最高等级'
			from mod.common.minecraftEnum import ItemPosType
			comp = self.CreateItemComponent(client)
			i = comp.GetPlayerAllItems(ItemPosType.INVENTORY)
			a = comp.GetPlayerAllItems(ItemPosType.ARMOR)
			o = comp.GetPlayerAllItems(ItemPosType.OFFHAND)
			from collections import defaultdict
			itemMap = defaultdict(int)
			for item in i + a + o:
				if item:
					itemMap[item['newItemName'], item['newAuxValue']] += int(item['count'])
			for item in [[x, amount] for x, amount in itemMap.iteritems()]:
				need = self.levelList[self.level]['nextNeedNum']
				if item[0] == tuple(self.item) and item[1] >= need:
					command = '/clear @s {} {} {}'.format(self.item[0].replace('minecraft:', ''), self.item[1], need)
					self.SetCommand(command, client)
					parent = self.GetParent()
					data = self.levelList[self.level]
					original = self.GetEntityAttrValue(parent.entityId, data['upgradeType'])
					isDamage = data['upgradeType'] == self.GetMinecraftEnum().AttrType.DAMAGE
					if isDamage:
						original = self.myDamage
					newValue = original + data['upgradeNum']
					if newValue > original:
						self.SetEntityAttrMaxValue(parent.entityId, data['upgradeType'], newValue)
					self.SetEntityAttrValue(parent.entityId, data['upgradeType'], newValue)
					if isDamage:
						self.myDamage = newValue
					self.level += 1
					self.SetOneTipMessage(e['client'], '升级成功')
					name = self.GetEntityName(e['client'])
					for client in self.GetLoadedPlayers():
						self.NotifyOneMessage(client, '%s 升级了 %s' % (name, parent.entityId))
					return True, ''
			return False, '所需物品不足'
		suc, msg = Upgrade(e['client'])
		self.NotifyToClient(e['client'], 'UIClickUpgradeCallback', {'suc': suc, 'msg': msg})


	def UIClickUpgradeCallback(self, e):
		uiApi = self.GetApi()
		if e['suc']:
			uiApi.PopScreen()
		else:
			from levelUpPartUI import LevelUpUIScreen, UIName, UIScreenDef
			uiNode = uiApi.GetUI(self.__class__.__name__, UIName)
			if not uiNode:
				self.LogError("create ui %s failed!" % UIScreenDef)
			else:
				uiNode.ShowTip(e['msg'])

	def COnUIInitFinished(self, e):
		if not self.uiLoad:
			self.RegisterUI()

	def RegisterUI(self):
		from levelUpPartUI import LevelUpUIScreen, UIName, UIScreenDef
		cls = '{}.{}'.format(LevelUpUIScreen.__module__, LevelUpUIScreen.__name__)
		uiApi = self.GetApi()
		uiApi.RegisterUI(self.__class__.__name__, UIName, cls, UIScreenDef)

	def TickClient(self):
		PartBase.TickClient(self)

	def TickServer(self):
		PartBase.TickServer(self)

	def DestroyClient(self):
		PartBase.DestroyClient(self)
		self.UnListenForEngineEvent(ClientEvent.UiInitFinished, self, self.COnUIInitFinished)
		self.UnListenSelfEvent('PlayerClickEntity', self, self.PlayerClickEntity)
		self.UnListenSelfEvent('UIClickUpgradeCallback', self, self.UIClickUpgradeCallback)

	def DestroyServer(self):
		PartBase.DestroyServer(self)
		self.UnListenForEngineEvent('DamageEvent', self, self.DamageEvent)
		self.UnListenSelfEvent('UIClickUpgrade', self, self.SUIClickUpgrade)
		self.UnListenSelfEvent('UIClickRemove', self, self.SUIClickRemove)

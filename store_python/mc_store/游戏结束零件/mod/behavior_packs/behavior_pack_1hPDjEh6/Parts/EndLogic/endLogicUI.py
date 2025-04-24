# -*- coding: utf-8 -*-

from mod.client.ui.screenNode import ScreenNode
import mod.client.extraClientApi as clientApi


# 所有的UI类需要继承自引擎的ScreenNode类
class EndLogicUIScreen(ScreenNode):
	def __init__(self, namespace, name, param):
		ScreenNode.__init__(self, namespace, name, param)
		# 当前客户端的玩家Id
		self.mPlayerId = clientApi.GetLocalPlayerId()

	# Create函数是继承自ScreenNode，会在UI创建完成后被调用
	def Create(self):
		# 游戏结束布局
		self.notifyPanel = "/notifyPanel"
		# 胜利队伍显示的文本
		self.notifyLabel = self.notifyPanel + "/notifyLabel"
		# 倒计时布局
		self.countDownPanel = "/countDownPanel"
		# 倒计时类型
		self.countDownLabel = self.countDownPanel + "/countDownLabel"
		# 倒计时计时
		self.countDownSecond = self.countDownPanel + "/countDownSecond"

	# 界面的一些初始化操作
	def Init(self):
		self.SetVisible(self.notifyPanel, False)
		self.SetVisible(self.countDownPanel, False)

	def ShowNotifyPanel(self, text):
		self.SetText(self.notifyLabel, text)
		self.SetVisible(self.notifyPanel, True)

	def HideNotifyPanel(self):
		self.SetText(self.notifyLabel, "default")
		self.SetVisible(self.notifyPanel, False)

	def ShowCountDownPanel(self, isShow):
		self.SetVisible(self.countDownPanel, isShow)

	def UpdateCountDownSecond(self, second):
		self.SetText(self.countDownSecond, str(second))

	def UpdateCountDownSecondType(self, downTimeType):
		self.SetText(self.countDownLabel, downTimeType)

	def Update(self):
		pass

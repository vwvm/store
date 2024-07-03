# -*- coding: UTF-8 -*-

from mod.client.ui.screenNode import ScreenNode
from mod.client import extraClientApi as clientApi

ModName = 'LevelUpMod'
UIName = 'levelUpUI'
UIScreenDef = 'levelUpUI.main'


class LevelUpUIScreen(ScreenNode):
	def __init__(self, namespace, name, param):
		ScreenNode.__init__(self, namespace, name, param)
		self.upgradeButtonPath = '/panel/up'
		self.cancelButtonPath = '/panel/cancel'
		self.removeButtonPath = '/panel/remove'
		self.titlePath = '/panel/title'
		self.paperDollPath = '/panel/paper_doll'
		self.paperDollPath1 = '/panel/paper_doll(0)'
		self.msgPath = '/panel/message'
		self.itemPath = '/panel/init/item_renderer'
		self.labelPath = '/panel/init/label'
		self.doll1Path = '/panel/paper_doll/label'
		self.doll2Path = '/panel/paper_doll(0)/label'
		self.displayName = ''
		self.entityId = ''
		self.title = ''
		self.doll1 = ''
		self.doll2 = ''
		self.text = ''
		self.item = []
		self.renderItem = True
		self.upgradeCallback = lambda c: None
		self.removeCallback = lambda c: None

	def InitParam(self, param):
		self.entityId = param['entityId']
		self.title = param['title']
		self.doll1 = param['doll1']
		self.doll2 = param['doll2']
		self.text = param['text']
		self.item = param['item']
		self.renderItem = param['renderItem']
		self.upgradeCallback = param['upgradeButtonCallback']
		self.removeCallback = param['removeButtonCallback']

	def ShowTip(self, msg, second=1):
		tip = self.GetBaseUIControl(self.msgPath)
		tip.GetChildByName('label').asLabel().SetText(msg)
		tip.SetVisible(True)
		game = clientApi.GetEngineCompFactory().CreateGame(clientApi.GetLevelId())
		game.AddTimer(second, tip.SetVisible, False)

	def Create(self):  # type: () -> None
		button = self.GetBaseUIControl(self.upgradeButtonPath).asButton()
		button.SetButtonTouchUpCallback(self.upgradeCallback)
		button.AddTouchEventParams()

		button = self.GetBaseUIControl(self.cancelButtonPath).asButton()
		def CancelButtonUp(e):
			self.SetRemove()
		button.SetButtonTouchUpCallback(CancelButtonUp)
		button.AddTouchEventParams()

		button = self.GetBaseUIControl(self.removeButtonPath).asButton()
		button.SetButtonTouchUpCallback(self.removeCallback)
		button.AddTouchEventParams()

		def RenderDoll(path):
			paperDoll = self.GetBaseUIControl(path).asNeteasePaperDoll()
			paperDoll.RenderEntity({
				'entity_id': self.entityId,
				'scale': 0.3,
				'render_depth': -50,
				'init_rot_y': 10,
				'molang_dict': {}
			})
		RenderDoll(self.paperDollPath)
		RenderDoll(self.paperDollPath1)

		item = self.GetBaseUIControl(self.itemPath).asItemRenderer()
		if self.renderItem:
			item.SetUiItem(self.item[0], self.item[1])
		else:
			item.SetVisible(False)

		label = self.GetBaseUIControl(self.titlePath).asLabel()
		label.SetText(self.title.replace('&', '§'))
		label = self.GetBaseUIControl(self.labelPath).asLabel()
		label.SetText(self.text.replace('&', '§'))
		label = self.GetBaseUIControl(self.doll1Path).asLabel()
		label.SetText(self.doll1.replace('&', '§'))
		label = self.GetBaseUIControl(self.doll2Path).asLabel()
		label.SetText(self.doll2.replace('&', '§'))

		msg = self.GetBaseUIControl(self.msgPath)
		msg.SetVisible(False)


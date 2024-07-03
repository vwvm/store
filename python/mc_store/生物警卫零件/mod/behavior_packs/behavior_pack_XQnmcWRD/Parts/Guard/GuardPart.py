# -*- coding: utf-8 -*-
from Preset.Model.PartBase import PartBase
from Preset.Model.Entity.EntityPreset import EntityPreset
from Preset.Model.GameObject import registerGenericClass

@registerGenericClass("GuardPart")
class GuardPart(PartBase):
	def __init__(self):
		PartBase.__init__(self)
		self.area = {'min': (1.0, 0.0, 1.0), 'max': (5.0, 2.0, 5.0), 'dimensionId': 0}
		self.area2 = {'min': (1.0, 0.0, 1.0), 'max': (5.0, 2.0, 5.0), 'dimensionId': 0}
		self.intervalTick = 10
		self.mobTypeList = []
		self.mobTypeStrList = []
		self.name = "警卫零件"
		self.myTick = 0
		self.areaType = 0
		self.m1 = (0, 0, 0)
		self.m2 = (0, 0, 0)

	def InitClient(self):
		PartBase.InitClient(self)

	def CanAdd(self, parent):
		if not isinstance(parent, EntityPreset):
			return "警卫零件只能挂在实体预设下"
		if parent.GetPartByType(self.classType):
			return "同一个实体预设只能挂接一个警卫零件"


	def InitServer(self):
		PartBase.InitServer(self)
		for entityType in self.mobTypeList:
			self.mobTypeStrList.append(entityType['mobType'])
		parent = self.GetParent()
		self.AddTimer(1, self.SetEntityBlockControlAi, parent.entityId, True)
		# self.RegisterEntityAOIEvent(self.area['dimensionId'], str(parent.entityId), tuple(self.area['min'] + self.area['max']), [])
		self.ListenForEngineEvent('DamageEvent', self, self.DamageEvent)
		# self.ListenForEngineEvent('NewOnEntityAreaEvent', self, self.NewOnEntityAreaEvent)

		if self.areaType == 0: # 固定区域
			x, y, z = self.area['min']
			x1, y1, z1 = self.area['max']
		if self.areaType == 1: # 相对区域
			x, y, z = self.area2['min']
			x1, y1, z1 = self.area2['max']
			px, py, pz = parent.GetWorldPosition()
			x, y, z = x + px, y + py, z + pz
			x1, y1, z1 = x1 + px, y1 + py, z1 + pz
		self.m1 = (int(min(x, x1)), int(min(y, y1)), int(min(z, z1)))
		self.m2 = (int(max(x, x1)), int(max(y, y1)), int(max(z, z1)))

	def DamageEvent(self, e):
		parent = self.GetParent()
		if not parent or not parent.entityId:
			return
		if e['entityId'] == parent.entityId and e['srcId'] in self.GetLoadedPlayers():
			e['damage'] = 0
			e['knock'] = False
			e['ignite'] = False
		if e['srcId'] == parent.entityId or e['entityId'] == parent.entityId:
			e['knock'] = False

	def TickClient(self):
		PartBase.TickClient(self)

	def TickServer(self):
		PartBase.TickServer(self)
		parent = self.GetParent()
		if not parent:
			return
		self.myTick += 1
		if self.myTick >= self.intervalTick:
			self.myTick = 0
		else:
			return
		if self.areaType == 0: # 固定区域
			dimensionId = self.area['dimensionId']
		if self.areaType == 1: # 相对区域
			dimensionId = self.area2['dimensionId']
		entityList = self.GetEntitiesInArea(self.m1, self.m2, dimensionId)
		# entityList = self.GetEntitiesInArea(self.area['min'], self.area['max'], self.area['dimensionId'])
		target = self.GetEntityAttackTarget(parent.entityId)
		newTarget = None
		newTargetDistance = 0
		for entityId in entityList:
			if entityId is None:
				continue
			typeStr = self.GetEntityEngineTypeStr(entityId)
			if not typeStr or not typeStr in self.mobTypeStrList:
				continue
			if target == entityId:
				return
			pos = self.GetEntityFootPos(entityId)
			myPos = self.GetEntityFootPos(parent.entityId)
			if not pos or not myPos:
				continue
			x1, y1, z1 = pos
			x2, y2, z2 = myPos
			dx, dy, dz = x1-x2, y1-y2, z1-z2
			d = dx * dx + dy * dy + dz * dz
			if newTarget is None or d < newTargetDistance:
				newTarget = entityId
				newTargetDistance = d
		if (target is None or target == '-1') and newTarget:
			self.SetEntityAttackTarget(parent.entityId, newTarget)
		else:
			self.ResetEntityAttackTarget(parent.entityId)





	def DestroyClient(self):
		PartBase.DestroyClient(self)

	def DestroyServer(self):
		PartBase.DestroyServer(self)
		self.UnListenForEngineEvent('DamageEvent', self, self.DamageEvent)

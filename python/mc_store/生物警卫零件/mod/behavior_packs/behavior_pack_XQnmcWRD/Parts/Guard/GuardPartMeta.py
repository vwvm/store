# -*- coding: utf-8 -*-
from MC.World.EntityManager import EntityManager
from Meta.ClassMetaManager import sunshine_class_meta
from Meta.EnumMeta import DefEnum
from Meta.TypeMeta import PBool, PStr, PInt, PCustom, PVector3, PVector3TF, PEnum, PDict, PFloat, PArray, PVector2, PColor, PGameObjectArea
from Preset.Model import PartBaseMeta
DefEnum('areaType', {0: '固定座标（世界坐标）', 1: '相对坐标（根据生成位置偏移）'})
@sunshine_class_meta
class GuardPartMeta(PartBaseMeta):
	CLASS_NAME = "GuardPart"
	PROPERTIES = {
		"intervalTick": PInt(text='检测频率', sort=13, default=10, tip='每多少帧检测一次入侵，数值越小怪物入侵时响应越快，但消耗越多性能。30tick是1秒，没有特殊需求建议保持默认。', group='零件设置'),
		"support": PEnum(visible=False, default=2),
		"area2": PGameObjectArea(
			sort=14,
			text="警卫区域", tip='设置生物需要警卫的区域起点和终点，注意不要离父级预设太远，否则可能会导致生物无法成功寻路到入侵生物。',
			children={
				'min': PVector3(sort=0, text="顶点1", default=[0.0, 0.0, 0.0]),
				'max': PVector3(sort=1, text="顶点2", default=[0.0, 0.0, 0.0]),
				'dimensionId': PInt(sort=2, text="维度", default=0),
			},
			func=lambda obj: {'visible': obj.areaType == 1}
		),
		"area": PDict(
			sort=14,
			text="警卫区域", tip='设置生物需要警卫的区域起点和终点，注意不要离父级预设太远，否则可能会导致生物无法成功寻路到入侵生物。',
			children={
				'min': PVector3(sort=0, text="顶点1", default=[0.0, 0.0, 0.0]),
				'max': PVector3(sort=1, text="顶点2", default=[0.0, 0.0, 0.0]),
				'dimensionId': PInt(sort=2, text="维度", default=0),
			},
			func=lambda obj: {'visible': obj.areaType == 0}
		),
		"areaType": PEnum(text="区域类型", sort=15, enumType='areaType', group="零件设置", tip="若为固定座标，则直接填写最终世界坐标；若为相对坐标，则填写相对实体生成位置的偏移，不会随着实体移动实时更新。"),
		"mobTypeList": PArray(text="敌对生物列表", sort=16, childAttribute=PDict(children={
			"mobType": PCustom(sort=0, text="生物类型", editAttribute="MCEnum", customFunc=lambda _: EntityManager.getCreatureEnum(), tip='点击"+"可添加警卫生物的敌对生物，该类生物进入警卫区域时，会受到警卫生物的攻击。'),
		}, tip='点击"+"可添加警卫生物的敌对生物，该类生物进入警卫区域时，会受到警卫生物的攻击。')),
	}

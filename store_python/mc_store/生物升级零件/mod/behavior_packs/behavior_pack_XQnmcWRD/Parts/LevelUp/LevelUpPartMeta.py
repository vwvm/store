# -*- coding: utf-8 -*-
from Meta.ClassMetaManager import sunshine_class_meta
from Meta.EnumMeta import DefEnum
from Meta.TypeMeta import PBool, PStr, PInt, PCustom, PVector3, PVector3TF, PEnum, PDict, PFloat, PArray, PVector2, \
    PColor
from Preset.Model import PartBaseMeta

# from mod.common.minecraftEnum import AttrType
class AttrType(object):
    HEALTH = 0  # 生命值, 原版的值范围为[0,20]
    SPEED = 1  # 移速, 原版的值范围为[0,+∞]
    DAMAGE = 2  # 攻击力, 原版的值范围为[1,1]
    UNDERWATER_SPEED = 3  # 水里的移速, 原版的值范围为[0,+∞]
    HUNGER = 4  # 饥饿值, 原版的值范围为[0,20]
    SATURATION = 5  # 饱和值, 原版的值范围为[0,20]
    ABSORPTION = 6  # 伤害吸收生命值，详见备注, 原版的值范围为[0,16]
    LAVA_SPEED = 7  # 岩浆里的移速, 原版的值范围为[0,+∞]
    LUCK = 8  # 幸运值，原版的值范围为[-1024,1024]

DefEnum('UpgradeType', {AttrType.HEALTH: '生命值', AttrType.DAMAGE: '攻击力', AttrType.SPEED: '速度'})


UpgradeType = {AttrType.HEALTH: '生命值', AttrType.DAMAGE: '攻击力', AttrType.SPEED: '速度'}

@sunshine_class_meta
class LevelUpPartMeta(PartBaseMeta):
    CLASS_NAME = "LevelUpPart"
    PROPERTIES = {
        'myDamage': PInt(text='初始攻击力', group='升级属性', sort=11, default=2, tip='定义该实体在未经过任何升级时的对敌人攻击造成的伤害，将会覆盖原生攻击力，同时升级也在此数值基础上增加'),
        'item': PCustom(
            sort=12,
            text='物品选择',
            editAttribute='MCItems',
            default=('minecraft:apple', 0),
            withNamespace=True,
            withAuxValue=True,
            isBlock=None, group='升级属性'
        ),
        'levelList': PArray(text='等级列表', group='升级属性', sort=13, childAttribute=PDict(children={
            'upgradeType': PEnum(text='属性提升类型', sort=0, enumType='UpgradeType', default=2, tip='定义该等级获得的属性提升类型'),
            'upgradeNum': PFloat(text='属性提升数值', sort=1, min=1, max=100, default=1, tip='定义该等级获得的属性提升数值'),
            'nextNeedNum': PInt(text='到下一级所需物品数量', min=1, max=100, default=1, sort=2, tip='定义该等级到下一等级所需的物品数量'),
        }, tip='定义该生物的等级列表，包括属性提升和到下一级所需的物品数量'))

    }

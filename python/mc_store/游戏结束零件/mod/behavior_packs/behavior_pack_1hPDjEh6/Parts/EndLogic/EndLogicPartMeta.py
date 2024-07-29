# -*- coding: utf-8 -*-
from Meta.ClassMetaManager import sunshine_class_meta
from MC.World.EntityManager import EntityManager
from Meta.EnumMeta import DefEnum
from Meta.TypeMeta import PCustom, PBool, PCoordinate, PEnum, PInt, PStr
from Preset.Model import PartBaseMeta


def _getVictoryJudgeEnumData(data):
	if data.endConditionType == 'player':
		if data.endJudgeCondition == 'clockEndCondition':
			enumData = {'deathNumMin': '死亡次数最少', 'killNumMax': '击杀玩家次数最多'}
		else:
			enumData = {'onlyOnePlayer': '仅剩1人存活'}
	elif data.endConditionType == 'team':
		if data.endJudgeCondition == 'clockEndCondition':
			enumData = {'queueScoreMax': '积分最高的获胜'}
		else:
			enumData = {'onlyOneQueue': '仅剩1只队伍的玩家存活'}
	else:
		return {}
	return enumData


def _getPCVictoryCondition(data):
	if data.endConditionType == 'player' and data.endJudgeCondition == 'clockEndCondition':
		return {'enumData': {'deathNumMin': '死亡次数最少', 'killNumMax': '击杀玩家次数最多'}}
	else:
		return {'visible': False}


def _getPRVictoryCondition(data):
	if data.endConditionType == 'player' and data.endJudgeCondition == 'reachVictoryCondition':
		return {'enumData': {'onlyOnePlayer': '仅剩1人存活'}}
	else:
		return {'visible': False}


def _getTCVictoryCondition(data):
	if data.endConditionType == 'team' and data.endJudgeCondition == 'clockEndCondition':
		return {'enumData': {'queueScoreMax': '积分最高的获胜'}}
	else:
		return {'visible': False}


def _getTRVictoryCondition(data):
	if data.endConditionType == 'team' and data.endJudgeCondition == 'reachVictoryCondition':
		return {'enumData':  {'onlyOneQueue': '仅剩1只队伍的玩家存活'}}
	else:
		return {'visible': False}


def _getEndJudgeEnumData(data):
	if data.endConditionType != 'notEnd':
		return {'enumData': {'clockEndCondition': '定时结束', 'reachVictoryCondition': '达成胜利条件'}}
	else:
		return {'visible': False}


@sunshine_class_meta
class EndLogicPartMeta(PartBaseMeta):
	CLASS_NAME = "EndLogicPart"
	PROPERTIES = {
		"showEndNotifyUIFlag": PBool(sort=10, text="是否显示公告界面", group="零件设置", tip='若勾选，则在游戏结束时，会弹出提示游戏胜利或失败的界面。'),
		"endConditionType": PEnum(sort=11, text="结束条件类型", group="零件设置", enumType='endType', tip='个人与队伍的结束条件会有所区别\n不自行结束则依赖其它零件的通知结束(接收到EndLogicEvent事件触发游戏结束)'),
		"endJudgeCondition": PEnum(
			sort=12, text="结束判断条件", group="零件设置", func=_getEndJudgeEnumData,
			tip='定时结束：开始游戏后一定时间结束游戏。\n大本营状态：若添加了玩法元素下的塔防大本营，那么会以大本营的血量来判断游戏是否结束。\n达成胜利条件：达成特定的胜利条件后游戏结束，具体条件类型在后续配置中选择。\n'
		),
		"pcVictoryCondition": PEnum(
			sort=13, text="胜利判断条件", group="零件设置", func=_getPCVictoryCondition,
			tip=(
				'死亡次数最少：死亡次数最少的玩家获得胜利。\n'
				'击杀玩家次数最多：击杀玩家次数最多的玩家获得胜利。\n'
				'积分最高的获胜：积分最高的队伍获得胜利。\n'
				'仅剩1人存活：最后1个存活的人获得胜利。\n'
				'仅剩1支队伍的玩家存活：当剩余的存活玩家都是一支队伍的人时，该支队伍获得胜利。\n'
				'“存活”条件需配合有限复活组件或床组件使用。\n'
				'在有限复活组件中，复活次数用完后玩家将不计入存活人数。\n'
				'在床组件中，床被破坏后玩家死亡将不计入存活人数。'
			)
		),
		"prVictoryCondition": PEnum(
			sort=13, text="胜利判断条件", group="零件设置", func=_getPRVictoryCondition,
			tip=(
				'死亡次数最少：死亡次数最少的玩家获得胜利。\n'
				'击杀玩家次数最多：击杀玩家次数最多的玩家获得胜利。\n'
				'积分最高的获胜：积分最高的队伍获得胜利。\n'
				'仅剩1人存活：最后1个存活的人获得胜利。\n'
				'仅剩1支队伍的玩家存活：当剩余的存活玩家都是一支队伍的人时，该支队伍获得胜利。\n'
				'“存活”条件需配合有限复活组件或床组件使用。\n'
				'在有限复活组件中，复活次数用完后玩家将不计入存活人数。\n'
				'在床组件中，床被破坏后玩家死亡将不计入存活人数。'
			)
		),
		"tcVictoryCondition": PEnum(
			sort=13, text="胜利判断条件", group="零件设置", func=_getTCVictoryCondition,
			tip=(
				'死亡次数最少：死亡次数最少的玩家获得胜利。\n'
				'击杀玩家次数最多：击杀玩家次数最多的玩家获得胜利。\n'
				'积分最高的获胜：积分最高的队伍获得胜利。\n'
				'仅剩1人存活：最后1个存活的人获得胜利。\n'
				'仅剩1支队伍的玩家存活：当剩余的存活玩家都是一支队伍的人时，该支队伍获得胜利。\n'
				'“存活”条件需配合有限复活组件或床组件使用。\n'
				'在有限复活组件中，复活次数用完后玩家将不计入存活人数。\n'
				'在床组件中，床被破坏后玩家死亡将不计入存活人数。'
			)
		),
		"trVictoryCondition": PEnum(
			sort=13, text="胜利判断条件", group="零件设置", func=_getTRVictoryCondition,
			tip=(
				'死亡次数最少：死亡次数最少的玩家获得胜利。\n'
				'击杀玩家次数最多：击杀玩家次数最多的玩家获得胜利。\n'
				'积分最高的获胜：积分最高的队伍获得胜利。\n'
				'仅剩1人存活：最后1个存活的人获得胜利。\n'
				'仅剩1支队伍的玩家存活：当剩余的存活玩家都是一支队伍的人时，该支队伍获得胜利。\n'
				'“存活”条件需配合有限复活组件或床组件使用。\n'
				'在有限复活组件中，复活次数用完后玩家将不计入存活人数。\n'
				'在床组件中，床被破坏后玩家死亡将不计入存活人数。'
			)
		),
		"clockEndTime": PInt(sort=14, text="游戏时长（秒）", group="零件设置", min=1, func=lambda x: {'visible': x.endJudgeCondition == 'clockEndCondition' and x.endConditionType != 'notEnd'}),
		"endWaitPos": PCoordinate(sort=15, text="结束等待地点", group="零件设置", visible=False),
		"restartGameFlag": PBool(
			sort=19, text="是否重新开始新一轮游戏", group="零件设置",
			tip='若勾选，则在游戏结束时，会将玩家传送到开始游戏组件的等待区域，重新开始游戏。'
		),
		"reStartGameTime": PInt(sort=20, text="结束游戏后自动开始时间", group="零件设置", func=lambda x: {'visible': x.restartGameFlag}) ,
		"clearInvFlag": PBool(
			sort=21, text="是否清理背包和装备栏", group="零件设置", tip='若勾选，则在结束游戏时，会清空玩家背包和装备栏。'
		),
	}

	@staticmethod
	def registerEnum():
		DefEnum("endType", {'player': '个人', 'team': '队伍', 'notEnd': "不自行结束"})

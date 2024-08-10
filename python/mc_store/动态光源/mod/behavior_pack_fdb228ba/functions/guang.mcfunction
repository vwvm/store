scoreboard objectives add guang dummy guang
scoreboard players add @e guang 1
execute @e [scores={guang=3}] ~~~ fill ~-4~-4~-4 ~4~4~4 air 0 replace light_block 10
scoreboard players set @e [scores={guang=4}] guang 0
execute @e [hasitem={location=slot.weapon.mainhand,item=minecraft:torch}] ~~~ fill ~~~ ~~1~ light_block 10 replace air 0


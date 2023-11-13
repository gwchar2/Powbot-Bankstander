# Powbot Enchanting Bankstander

## Useful API's and Repositories

### Powbot API
https://docs.powbot.org/jdocs/app/org.powbot.api.action/index.html

https://docs.powbot.org/#/

### OSbot API   
https://osbot.org/api/

### Devious API
https://github.com/jbx5/devious-client/tree/master/runelite-client/src/main/java/net/unethicalite/api

### Runescape Tile Map
https://map.runemax.com/?centreX=3222&centreY=3428&centreZ=0&zoom=8

### Powbot Example Script / Project
https://github.com/Protoprize/PowBot-Base-Gradle-Project

### Open Source Powbot Scripts
https://github.com/PTYB?tab=repositories


### Script Update Log
##### October 4.2023    - Finished UI Paint, Auto Walk.
##### September 29.2023 - Added Log configuration.
##### September 28.2023 - Added bank areas, Default variables.
##### September 22.2023 - Finished Interface.


###### To Do List:
1. [✓] finish requirements method 
2. [✓] checkMagicLevel() -- check if you pass the max level while casting (when to stop)
3. [✓] checkIfAtBank() -- check if you are at the bank area already
4. [✓] if not at bank, move to the bank.
5. [✓] moveToBank() -- []**teleports to the bank**, [✓]or walks to it.
6. [✓] if you are at the bank :
7. [✓] if suitableWeapon true : remove specific rune from runesNeededList.
8. [✓] else if suitableWeapon false : 
             if correctStaff() exists (true) ,take from bank (or inventory) & equip & remove specific rune from runesNeededList.
8. [✓] withdrawRunes() (takes the runes from runesNeededList)
9. [✓] check for the item chosen, save total amount as int & withdraw 28 minus amount of runes in runesNeededList. 
             if item doesnt exist in bank, terminate script
10. [✓] get the total amount of casts available & compare to total amount of item in bank
11. [✓] get the price of runes used (if staff equipped, no element rune) from ge, and price of item enchanted item not enchanted, and enable profit counter.
12. [] startCasting() - has designed click patterns, and fast clicking for bolts.



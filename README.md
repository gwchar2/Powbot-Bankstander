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
##### September 29.2023 - Added Log configuration
##### September 28.2023 - Added bank areas, Default variables.
##### September 22.2023 - Finished Interface.


###### To Do List:
[✓] finish requirements method
[✓] checkMagicLevel() -- check if you pass the max level while casting (when to stop)
[✓] checkIfAtBank() -- check if you are at the bank area already
[✓] if not at bank, move to the bank.
[✓] moveToBank() -- []**teleports to the bank**, [✓]or walks to it.
[ ] if you are at the bank :
[ ] if correctStaff() exist, equip, takeRunesNeeded() without element rune.
[ ] else, takeRunesNeeded()
[ ] check for the item chosen, withdraw, minimum amount is 28-runes amount.
[ ] get the price of runes used (if staff equipped, no element rune) from ge, and price of item enchanted item not enchanted, and enable profit counter.
[ ] startCasting() - has designed click patterns, and fast clicking for bolts.



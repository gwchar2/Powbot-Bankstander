package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum  Level_1 implements Enchantable {
    SAPPHIRE_RING(ItemLoader.lookup("Sapphire ring").id(), ItemLoader.lookup("Ring of recoil").id(), 7),
    SAPPHIRE_BRACELET(ItemLoader.lookup("Sapphire bracelet").id(), ItemLoader.lookup("Bracelet of clay").id(), 7),
    SAPPHIRE_NECKLACE(ItemLoader.lookup("Sapphire necklace").id(), ItemLoader.lookup("Games necklace").id(), 7),
    SAPPHIRE_AMULET(ItemLoader.lookup("Sapphire amulet").id(), ItemLoader.lookup("Amulet of magic").id(), 7),
    OPAL_RING(ItemLoader.lookup("Opal ring").id(), ItemLoader.lookup("Ring of pursuit").id(), 7),
    OPAL_BRACELET(ItemLoader.lookup("Opal bracelet").id(), ItemLoader.lookup("Expeditious bracelet").id(), 7),
    OPAL_NECKLACE(ItemLoader.lookup("Opal necklace").id(), ItemLoader.lookup("Dodgy necklace").id(), 7),
    OPAL_AMULET(ItemLoader.lookup("Opal amulet").id(), ItemLoader.lookup("Amulet of bounty").id(), 7);
    
    private int unenchantedID;
    private int enchantedID;
    private int levelReq;

    Level_1(int unenchantedID, int enchantedID, int levelReq){
        this.unenchantedID = unenchantedID;
        this.enchantedID = enchantedID;
        this.levelReq = levelReq;
    }

    public int getUnenchantedID(){
        return unenchantedID;
    }

    public int getEnchantedID(){
        return enchantedID;
    }

    public int getLevelReq(){
        return levelReq;
    }
}

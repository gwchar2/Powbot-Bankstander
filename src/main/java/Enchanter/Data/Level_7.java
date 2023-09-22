package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_7 implements Enchantable{
    ZENYTE_RING(ItemLoader.lookup("Zenyte ring").id(), ItemLoader.lookup("Ring of suffering").id(), 93),
    ZENYTE_BRACELET(ItemLoader.lookup("Zenyte bracelet").id(), ItemLoader.lookup("Necklace of anguish").id(), 93),
    ZENYTE_NECKLACE(ItemLoader.lookup("Zenyte necklace").id(), ItemLoader.lookup("Tormented bracelet").id(), 93),
    ZENYTE_AMULET(ItemLoader.lookup("Zenyte amulet").id(), ItemLoader.lookup("Amulet of torture").id(), 93);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;


    Level_7(int unenchantedID, int enchantedID, int levelReq){
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


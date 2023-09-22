package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_2 implements Enchantable{
    EMERALD_RING(ItemLoader.lookup("Emerald ring").id(),ItemLoader.lookup("Ring of dueling").id(), 27),
    EMERALD_BRACELET(ItemLoader.lookup("Emerald bracelet").id(), ItemLoader.lookup("Castle wars bracelet").id(), 27),
    EMERALD_NECKLACE(ItemLoader.lookup("Emerald necklace").id(),ItemLoader.lookup("Binding necklace").id(), 27),
    EMERALD_AMULET(ItemLoader.lookup("Emerald amulet").id(), ItemLoader.lookup("Amulet of defence").id(), 27),
    JADE_RING(ItemLoader.lookup("Jade ring").id(),ItemLoader.lookup("Ring of returning").id(), 27),
    JADE_BRACELET(ItemLoader.lookup("Jade bracelet").id(),ItemLoader.lookup("Flamtaer bracelet").id(), 27),
    JADE_NECKLACE(ItemLoader.lookup("Jade necklace").id(), ItemLoader.lookup("Necklace of passage").id(), 27),
    JADE_AMULET(ItemLoader.lookup("Jade amulet").id(), ItemLoader.lookup("Amulet of chemistry").id(), 27);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;


    Level_2(int unenchantedID, int enchantedID, int levelReq){
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

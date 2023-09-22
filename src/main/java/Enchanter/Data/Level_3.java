package Enchanter.Data;


import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_3 implements Enchantable{
    RUBY_RING(ItemLoader.lookup("Ruby ring").id(), ItemLoader.lookup("Ring of forging").id(), 49),
    RUBY_BRACELET(ItemLoader.lookup("Ruby bracelet").id(),ItemLoader.lookup("Digsite pendant").id(), 49),
    RUBY_NECKLACE(ItemLoader.lookup("Ruby necklace").id(),ItemLoader.lookup("Inoculation bracelet").id(), 49),
    RUBY_AMULET(ItemLoader.lookup("Ruby amulet").id(),ItemLoader.lookup("Amulet of strength").id(), 49),
    TOPAZ_RING(ItemLoader.lookup("Topaz ring").id(),ItemLoader.lookup("Efaritay's aid").id(), 49),
    TOPAZ_BRACELET(ItemLoader.lookup("Topaz bracelet").id(), ItemLoader.lookup("Bracelet of slaughter").id(), 49),
    TOPAZ_NECKLACE(ItemLoader.lookup("Topaz necklace").id(),ItemLoader.lookup("Necklace of faith").id(), 49),
    TOPAZ_AMULET(ItemLoader.lookup("Topaz amulet").id(),ItemLoader.lookup("Burning amulet").id(), 49);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;
 

    Level_3(int unenchantedID, int enchantedID, int levelReq){
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

package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum CrossbowBolt implements Enchantable {   
    OPAL(ItemLoader.lookup("Opal bolts").id(), ItemLoader.lookup("Opal bolts (e)").id(),9),
    OPAL_DRAGON(ItemLoader.lookup("Opal dragon bolts").id(), ItemLoader.lookup("Opal dragon bolts (e)").id(), 9),
    SAPPHIRE(ItemLoader.lookup("Sapphire bolts").id(), ItemLoader.lookup("Sapphire bolts (e)").id(), 56),
    SAPPHIRE_DRAGON(ItemLoader.lookup("Sapphire dragon bolts").id(), ItemLoader.lookup("Sapphire dragon bolts (e)").id(), 56),
    JADE(ItemLoader.lookup("Jade bolts").id(), ItemLoader.lookup("Jade bolts (e)").id(), 58),
    JADE_DRAGON(ItemLoader.lookup("Jade dragon bolts").id(),ItemLoader.lookup("Jade dragon bolts (e)").id(), 58),
    PEARL(ItemLoader.lookup("Pearl bolts").id(), ItemLoader.lookup("Pearl bolts (e)").id(), 24),
    PEARL_DRAGON(ItemLoader.lookup("Pearl dragon bolts").id(), ItemLoader.lookup("Pearl dragon bolts (e)").id(), 24),
    EMERALD(ItemLoader.lookup("Emerald bolts").id(), ItemLoader.lookup("Emerald bolts (e)").id(), 27),
    EMERALD_DRAGON(ItemLoader.lookup("Emerald dragon bolts").id(), ItemLoader.lookup("Emerald dragon bolts (e)").id(), 27),
    TOPAZ(ItemLoader.lookup("Topaz bolts").id(), ItemLoader.lookup("Topaz bolts (e)").id(), 48),
    TOPAZ_DRAGON(ItemLoader.lookup("Topaz dragon bolts").id(),ItemLoader.lookup("Topaz dragon bolts (e)").id(), 48),
    RUBY(ItemLoader.lookup("Ruby bolts").id(), ItemLoader.lookup("Ruby bolts (e)").id(), 49),
    RUBY_DRAGON(ItemLoader.lookup("Ruby dragon bolts").id(), ItemLoader.lookup("Ruby dragon bolts (e)").id(), 49),
    DIAMOND(ItemLoader.lookup("Diamond bolts").id(), ItemLoader.lookup("Diamond bolts (e)").id(), 57),
    DIAMOND_DRAGON(ItemLoader.lookup("Diamond dragon bolts").id(), ItemLoader.lookup("Diamond dragon bolts (e)").id(), 57),
    DRAGONSTONE(ItemLoader.lookup("Dragonstone bolts").id(), ItemLoader.lookup("Dragonstone bolts (e)").id(), 68),
    DRAGONSTONE_DRAGON(ItemLoader.lookup("Dragonstone dragon bolts").id(), ItemLoader.lookup("Dragonstone dragon bolts (e)").id(), 68),
    ONYX(ItemLoader.lookup("Onyx bolts").id(), ItemLoader.lookup("Onyx bolts (e)").id(), 87),
    ONYX_DRAGON(ItemLoader.lookup("Onyx dragon bolts").id(), ItemLoader.lookup("Onyx dragon bolts (e)").id(), 87);
    ;

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;
    
    CrossbowBolt(int unenchantedID, int enchantedID, int levelReq){
        this.unenchantedID = unenchantedID;
        this.enchantedID = enchantedID;
        this.levelReq = levelReq;
    }

    public int getUnenchantedID() {
        return unenchantedID;
    }

    public int getEnchantedID() {
        return enchantedID;
    }

    public int getLevelReq() {
        return levelReq;
    }
}


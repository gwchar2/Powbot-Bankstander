package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_4 implements Enchantable {
    DIAMOND_RING(1643 ,2570 ,57),
    DIAMOND_BRACELET(11092 ,11090 ,57),
    DIAMOND_NECKLACE(1662  ,11095  ,57),
    DIAMOND_AMULET(1700  ,1731  ,57);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;

    Level_4(int unenchantedID, int enchantedID, int levelReq) {
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

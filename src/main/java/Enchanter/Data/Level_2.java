package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_2 implements Enchantable{
    EMERALD_RING(1639 ,2552 , 27),
    EMERALD_BRACELET(11076 ,11079  , 27),
    EMERALD_NECKLACE(1658 ,5521 , 27),
    EMERALD_AMULET(1696 ,1729 , 27),
    JADE_RING(21084 ,21129  , 27),
    JADE_BRACELET(21120 ,21180  , 27),
    JADE_NECKLACE(21093  ,21146 , 27),
    JADE_AMULET(21102  , 21163  , 27);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;


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

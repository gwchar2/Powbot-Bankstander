package Enchanter.Data;

public enum Level_7 implements Enchantable{
    ZENYTE_RING(19538  ,19550 , 93),
    ZENYTE_BRACELET(19492 ,19547 , 93),
    ZENYTE_NECKLACE(19535  ,19544 , 93),
    ZENYTE_AMULET(19541  ,19553  , 93);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;


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


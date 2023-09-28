package Enchanter.Data;

public enum CrossbowBolt implements Enchantable {   
    OPAL(879 ,9236 ,9),
    OPAL_DRAGON(21955 ,21932  , 9),
    SAPPHIRE(9240 ,9240 , 56),
    SAPPHIRE_DRAGON(21940  ,21940  , 56),
    JADE(9335  ,9237  , 58),
    JADE_DRAGON(21957 ,21934  , 58),
    PEARL(880  ,9238 , 24),
    PEARL_DRAGON(21959  ,21936  , 24),
    EMERALD(9338  ,9241  , 27),
    EMERALD_DRAGON(21965  ,21942 , 27),
    TOPAZ(9336 ,   9239 , 48),
    TOPAZ_DRAGON(21961 ,21938 , 48),
    RUBY(9339  ,9242  , 49),
    RUBY_DRAGON(21967  ,21944  , 49),
    DIAMOND(9340  ,9243  , 57),
    DIAMOND_DRAGON(21969  ,21946  , 57),
    DRAGONSTONE(9341 ,9244 , 68),
    DRAGONSTONE_DRAGON(21971 ,21948 , 68),
    ONYX(9342  ,9245  , 87),
    ONYX_DRAGON(21973  ,21950 , 87);
    

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


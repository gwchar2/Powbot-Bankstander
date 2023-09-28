package Enchanter.Data;


import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_3 implements Enchantable{
    DEFAULT_VALUE(1641 ,2568  , 49),
    RUBY_RING(1641 ,2568  , 49),
    RUBY_BRACELET(11085 ,11194 , 49),
    RUBY_NECKLACE(1660  ,11065  , 49),
    RUBY_AMULET(1698 ,1725 , 49),
    TOPAZ_RING(21087  ,21140 , 49),
    TOPAZ_BRACELET(21123 ,21183 , 49),
    TOPAZ_NECKLACE(21096 ,21157 , 49),
    TOPAZ_AMULET(21114 ,21166  , 49);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;
 

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
    public String getName() {
        return name();
    }



    
}

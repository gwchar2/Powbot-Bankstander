package Enchanter.Data;

import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Magic.Spell;

public enum EnchantType {
    BOLT(Magic.Spell.ENCHANT_CROSSBOW_BOLT),
    LEVEL_1(Magic.Spell.ENCHANT_LEVEL_1_JEWELLERY),
    LEVEL_2(Magic.Spell.ENCHANT_LEVEL_2_JEWELLERY),
    LEVEL_3(Magic.Spell.ENCHANT_LEVEL_3_JEWELLERY),
    LEVEL_4(Magic.Spell.ENCHANT_LEVEL_4_JEWELLERY),
    LEVEL_5(Magic.Spell.ENCHANT_LEVEL_5_JEWELLERY),
    LEVEL_6(Magic.Spell.ENCHANT_LEVEL_6_JEWELLERY),
    LEVEL_7(Magic.Spell.ENCHANT_LEVEL_7_JEWELLERY);

    private final Magic.Spell spell;

    EnchantType(Magic.Spell spell) {
        this.spell = spell;
    }

    public Magic.Spell getSpell() {
        return spell;
    }

    public String getName() {
        return name();
    }

}

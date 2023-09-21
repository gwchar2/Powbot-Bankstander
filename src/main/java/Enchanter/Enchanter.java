package Enchanter;

import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;

@ScriptManifest(name = "Open Enchanter", description = "Enchants bolts and items", version = "1.0.1", category = ScriptCategory.Magic, author = "Great Mental", markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Enchant Type", description = "Type of enchant to cast", allowedValues = {
                "Bolt", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7" }, visible = true),
        @ScriptConfiguration(name = "Item to Enchant", description = "Type of item to enchant", visible = true),
})

public class Enchanter extends AbstractScript {

    @Override
    public void onStart() {
        // Logic to happen before poll() starts

    }

    @Override
    public void poll() {
        // Looped tasks go here. Example below is of a simple fighter script

    }

    public static void main(String[] args) {
        // Start your script with this function. Make sure your device is connected via
        // ADB, and only one is connected
        new Enchanter().startScript();
    }
}
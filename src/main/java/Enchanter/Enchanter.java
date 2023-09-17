package Enchanter;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.script.*;

// Example of GUI option which asks for a string
@ScriptConfiguration(name = "NPC", description = "NPC to fight", optionType = OptionType.STRING)
@ScriptConfiguration(name = "Example GUI option", description = "Example GUI option", optionType = OptionType.STRING, visible = false)
@ScriptManifest(name = "Example Script", description = "Example Script", version = "1")


public class Enchanter extends AbstractScript {

    private String GuiNPC; // Creates an empty Logic to happen before poll() starts
    @Override
    public void onStart() {
        
        boolean GuiNPC = getOption("NPC"); // Gets value of specific GUI key and saves it in a variable.
    }

    @Override
    public void poll() {
        // Looped tasks go here. Example below is of a simple fighter script

    }


    @ValueChanged(keyName = "NPC") // Listens for changes to the specific GUI config key.
    private void exampleConfigChanged(Boolean valueChanged) {
        updateVisibility("Example GUI option", true); // Changes visibility of the second GUI config key if another key is modified
    }

    public static void main(String[] args) {
        // Start your script with this function. Make sure your device is connected via ADB, and only one is connected
        new Enchanter().startScript();
    }
}

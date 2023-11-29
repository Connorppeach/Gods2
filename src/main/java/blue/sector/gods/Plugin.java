package blue.sector.gods;

import blue.sector.gods.commands.TestCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new TestCommand(this));
    }
}

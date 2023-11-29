package blue.sector.gods;

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

package blue.sector.gods;

import java.util.UUID;

import blue.sector.gods.commands.TestCommand;
import blue.sector.gods.listeners.BlockListener;
import blue.sector.gods.managers.GodsManager;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Plugin extends JavaPlugin {
    @Override
    public void onDisable() {
	instance = null;
    }
    private static Plugin instance;

    public static Plugin instance() {
	return instance;
    }

    @Override
    public void onEnable() {
	instance = this;
	GodsManager.instance().load();
        getCommand("test").setExecutor(new TestCommand());
	getServer().getPluginManager().registerEvents(new BlockListener(), this);
	
    }
}

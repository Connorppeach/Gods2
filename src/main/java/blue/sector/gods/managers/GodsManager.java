package blue.sector.gods.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import blue.sector.gods.Plugin;

public class GodsManager {
    private static GodsManager instance;
    public static enum GodGender {
	None, Male, Female, Nonbinary;
    }
    public static enum GodMood {
	EXALTED, PLEASED, NEUTRAL, DISPLEASED, ANGRY;
    }

    private FileConfiguration	godsConfig		= null;
    private File				godsConfigFile	= null;
    private List<String>		onlineGods		= new ArrayList<String>();
    private long				lastSaveTime;
    private String				pattern			= "HH:mm:ss dd-MM-yyyy";
    DateFormat					formatter		= new SimpleDateFormat(this.pattern);
    
    public static GodsManager instance() {
	if (instance == null)
	    instance = new GodsManager();
	return instance;
    }
    public void load() {
	this.godsConfigFile = new File(Plugin.instance().getDataFolder(), "gods.yml");

	this.godsConfig = YamlConfiguration.loadConfiguration(this.godsConfigFile);

	
    }
    public void save() {
	this.lastSaveTime = System.currentTimeMillis();
	if ((this.godsConfig == null) || (this.godsConfigFile == null)) {
	    return;
	}
	new BukkitRunnable() {
	    @Override
	    public void run() {
		try {
		    godsConfig.save(godsConfigFile);
		} catch (Exception ex) {
		}
	    }
	}.runTaskAsynchronously(Plugin.instance());
    }

    public boolean useAlter(Player player, String godName, Location location) {
	if(this.godsConfig.getDouble(godName + ".power") > 0) {
	    Date thisDate = new Date();
	    DateFormat formatter = new SimpleDateFormat(this.pattern);
	    double power = 0;
	    try {
		power = this.godsConfig.getDouble(godName + ".power") + 1 - (((thisDate.getTime() - formatter.parse(this.godsConfig.getString(godName + ".lastUpdate")).getTime()) / 1000) / 1);
		this.godsConfig.set(godName + ".power", power);
		this.godsConfig.set(godName + ".lastUpdate", formatter.format(thisDate));
	    } catch (Exception ex) {
		return false;
	    }
	    if(power <= 0)
		Plugin.instance().getServer().broadcastMessage(godName + " has lost all power");
		
	    //Plugin.instance().getServer().broadcastMessage(godName + " has " + String.valueOf(power));
	    save();
	    return true;
	    
	} else {
	    Date thisDate = new Date();
	    DateFormat formatter = new SimpleDateFormat(this.pattern);	    
	    this.godsConfig.set(godName + ".power", 1);
	    Plugin.instance().getServer().broadcastMessage(godName + " has gained power");
	    this.godsConfig.set(godName + ".lastUpdate", formatter.format(thisDate));
	    save();
	    return true;

	}
	//return false;
    }


}

package blue.sector.gods.listeners;


import blue.sector.gods.Plugin;
import blue.sector.gods.managers.AltarManager;
import blue.sector.gods.Plugin;
import blue.sector.gods.managers.GodsManager;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



public class BlockListener implements Listener {
    /*
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void OnBlockPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		if (player == null || event.getBlock() == null)
		{
			return;
		}

		
		
	}
    */


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))	{
	    if (!AltarManager.instance().isAltarSign(event.getClickedBlock())) {
		return;
	    }
	    Plugin.instance().getLogger().info("altar found");
	    BlockState state = event.getClickedBlock().getState();

	    Sign sign = (Sign) state;
	    String godName = sign.getLine(0);
	    godName = godName.trim();
	    // player.sendMessage( godName); 
	    if (godName.length() <= 1) {
		player.sendMessage( ChatColor.DARK_RED + "Invalid god name"); 
		return;
	    }
	    event.setCancelled(true);
	    GodsManager.instance().useAlter(player, godName, event.getClickedBlock().getLocation());
	}

    }
    /*
    @EventHandler(priority = EventPriority.HIGH)
    public void OnSignChange(SignChangeEvent event) {
	Player player = event.getPlayer();
	event.setCancelled(true);
	if (AltarManager.instance().isAltarSign(event.getBlock())) {
	}
    }
    */
}


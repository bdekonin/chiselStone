package me.Dopeey.chiselStibe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	BreakAndReplace BreakAndReplace = new BreakAndReplace();
	boolean status;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents((Listener) this, this);
		status = false;
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("chiselStone")) {
			if (args.length != 1)
				return false;
			if (args[0].equalsIgnoreCase("on")) {
				if (status == true) {
					sender.sendMessage(ChatColor.GREEN + "Chiselstone is already on.");
					return true;
				}
				status = true;
			}
			else if (args[0].equalsIgnoreCase("off")) {
				if (status == false) {
					sender.sendMessage(ChatColor.RED + "Chiselstone is already off.");
					return true;
				}
				status = false;
			}
			else
				return false;
		}
		return true;
	}
	
	@EventHandler
	public void chiselBlock(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK || status == false) {
			// No action associated with this method
			return ;
		}
		
		Material clickedBlock = event.getClickedBlock().getType();
		if (clickedBlock == Material.STONE_BRICKS || 
				clickedBlock == Material.NETHER_BRICKS || 
				clickedBlock == Material.POLISHED_BLACKSTONE_BRICKS) {
			ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
			
			// Handles the rest for chiseling, see BreakAndReplace.java
			BreakAndReplace.handleChiseling(event, clickedBlock, item);
		}
		return ;	
	}
}



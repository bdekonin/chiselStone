package me.Dopeey.chiselStibe;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class BreakAndReplace {

	public void handleChiseling(PlayerInteractEvent event, Material clickedBlock, ItemStack item) {
		// Check if item is a pickaxe
		if (this.errorHandling(item) == false) {
			// item was not a pickaxe
			return ;
		}
		
		// Replaces and plays sound of new block
		this.replaceBlock(event, clickedBlock);
		
		// Sets new durability
		this.setDamage((Damageable) item.getItemMeta(), 1, item, event.getPlayer());
	}
	
	// Check if item is a pickaxe
	private boolean errorHandling(ItemStack item) {
		// Can only be pickaxes
		if (item.getType() == Material.WOODEN_PICKAXE || 
				item.getType() == Material.STONE_PICKAXE ||
				item.getType() == Material.IRON_PICKAXE ||
				item.getType() == Material.GOLDEN_PICKAXE ||
				item.getType() == Material.DIAMOND_PICKAXE ||
				item.getType() == Material.NETHERITE_PICKAXE) {		
			return true;
		}
		return false;
	}
	
	// Replaces and plays sound of new block
	private void replaceBlock(PlayerInteractEvent event, Material clickedBlock) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();

		if (clickedBlock == Material.STONE_BRICKS) {
			player.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 1f, 1f);
			block.setType(Material.CRACKED_STONE_BRICKS);
		}
		else if (clickedBlock == Material.NETHER_BRICKS) {
			player.playSound(player.getLocation(), Sound.BLOCK_NETHER_BRICKS_PLACE, 1f, 1f);
			block.setType(Material.CRACKED_NETHER_BRICKS);
		}
		else {
			player.playSound(player.getLocation(), Sound.BLOCK_GILDED_BLACKSTONE_PLACE, 1f, 1f);
			block.setType(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);	
		}
		return ;
	}
	
	// Sets new durability
	private void setDamage(Damageable meta, int amount, ItemStack item, Player player) {
		
		// check if item has enough durability. if not it returns
		if (meta.getDamage() + 1 == item.getType().getMaxDurability()) {
			player.playEffect(EntityEffect.BREAK_EQUIPMENT_MAIN_HAND);
			player.getInventory().remove(item);
			return ;
		}
		int damage = meta.getDamage();
		meta.setDamage(damage + 1);
		
		item.setItemMeta((ItemMeta) meta);
		return ;
	}

}

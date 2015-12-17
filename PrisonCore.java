package me.Logan.prisoncore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PrisonCore extends JavaPlugin implements Listener {

    private final List<ItemStack> items = Arrays.asList(new ItemStack(Material.DIAMOND_BLOCK, 1), new ItemStack(Material.GOLD_BLOCK, 1), new ItemStack(Material.IRON_BLOCK, 1), new ItemStack(Material.EMERALD_BLOCK, 1), new ItemStack(Material.DIRT), new ItemStack(Material.WOOD), new ItemStack(Material.LAPIS_BLOCK));
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void explodingBlock(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.WOOL)) {
            e.setCancelled(true);
            return;
        }
        if (e.getPlayer().getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
            if (e.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.RED + "BOUNTIFUL")) {
                e.setCancelled(true);
                Player player = e.getPlayer();
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        Block block = e.getBlock();
                        block.setTypeIdAndData(35, DyeColor.LIME.getData(), true);
                        block.setType(Material.WOOL);
                        block.setData(DyeColor.LIME.getData());
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                    }
                }, 1L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        Block block = e.getBlock();
                        block.setTypeIdAndData(35, DyeColor.YELLOW.getData(), true);
                        block.setType(Material.WOOL);
                        block.setData(DyeColor.YELLOW.getData());
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                    }
                }, 21L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        Block block = e.getBlock();
                        block.setTypeIdAndData(35, DyeColor.RED.getData(), true);
                        block.setType(Material.WOOL);
                        block.setData(DyeColor.RED.getData());
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                        player.getWorld().playEffect(e.getBlock().getLocation().add(0.500, 2, 0.500), Effect.LARGE_SMOKE, 0);
                    }
                }, 41L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        e.getBlock().setType(Material.AIR);
                        player.getWorld().playSound(e.getBlock().getLocation(), Sound.EXPLODE, 4F, 0F);
                        player.getWorld().dropItemNaturally(e.getBlock().getLocation().add(0, 0, 0), items.get(ThreadLocalRandom.current().nextInt(items.size())));
                    }
                }, 61L);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("adm")) {
            if (!sender.hasPermission("Admin.adm")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.YELLOW + "Incorrect Format /adm <Bountiful>");
                return true;
            }
            if (sender.hasPermission("Admin.adm")) {
                if (args[0].equalsIgnoreCase("bountiful")) {
                    Player p = (Player) sender;
                    if (p.getItemInHand().getType().equals(Material.AIR)) {
                        sender.sendMessage(ChatColor.YELLOW + "You must have something in your hand!");
                        return true;
                    }
                    if (!p.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
                        sender.sendMessage(ChatColor.YELLOW + "You can only apply this enchantment to diamond pickaxes");
                        return true;
                    }
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(" ");
                    lore.add(ChatColor.RED + "BOUNTIFUL");
                    ItemMeta bountpick = p.getItemInHand().getItemMeta();
                    bountpick.addEnchant(Enchantment.DIG_SPEED, 5, true);
                    bountpick.setLore(lore);
                    p.getItemInHand().setItemMeta(bountpick);

                }
            }
        }
        return true;
    }
}

package me.rqyztm.FancyChat;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("§aPlugin enabled!");
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("");
		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("§cPlugin disabled!");
		Bukkit.getServer().getConsoleSender().sendMessage("");
		Bukkit.getServer().getConsoleSender().sendMessage("");
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		
		IChatBaseComponent component = ChatSerializer.a("[\"\", {\"text\":\"§f" + p.getName() +"\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Player UUID: §b" + p.getUniqueId() + "\"},\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/msg " + p.getName() + " \"}}, {\"text\":\" §7> \"}, {\"text\":\"§2" + e.getMessage().replace("\"", "\\\"") + "\"}]");
		PacketPlayOutChat chat = new PacketPlayOutChat(component);
		
		for(Player online : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(chat);
		}
	}
}

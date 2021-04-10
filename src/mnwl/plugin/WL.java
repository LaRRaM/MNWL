package mnwl.plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WL implements CommandExecutor{
	private Main plugin;
	public WL(Main plugin) {this.plugin = plugin;}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		File users = new File(plugin.getDataFolder() + File.separator + "users.yml");
		FileConfiguration wl = YamlConfiguration.loadConfiguration(users);
		List<String> list = wl.getStringList("users");

		
		String help = plugin.getConfig().getString("messages.help");
		String act = args[0];
		String name = args[1];
		help.replace("&", "\u00a7");
		
		if (!sender.hasPermission("wl")) {
			sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("messages.permDenied"));
			return true;
		}
	
		if(act == null || name == null) {
			return false;
		} else if(act == "help") {
			sender.sendMessage(help);
		} else if(act == "add") {	//ADD PLAYER
			if (list.contains(name)) {
				sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("messages.plrExist"));
				return true;
			}		
			list.add(name);
			wl.set("users", list);
			try {
				wl.save(users);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.plrAdded").replace("{name}", name));
			plugin.reloadConfig();
		} else if (act == "remove") { //REMOVE PLAYER
//			Player not exist		
			if (!list.contains(name)) {
				sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("messages.plrNotExist"));
				return true;
			}
//	 		Remove player form white list	
			list.remove(name);
			wl.set("users", list);
			try {
				wl.save(users);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.plrRemoved").replace("{name}", name));
			plugin.reloadConfig();
		} else if(act == "list") {		
			for (int i = 0; i < list.size(); i++) {
				sender.sendMessage(ChatColor.GREEN + list.get(i));
			}
		}
		return true;
	}

}

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

public class WLREMOVE implements CommandExecutor{
	private Main plugin;	
	public WLREMOVE(Main plugin) {this.plugin = plugin;}	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		File users = new File(plugin.getDataFolder() + File.separator + "users.yml");
		FileConfiguration wl = YamlConfiguration.loadConfiguration(users);
		List<String> list = wl.getStringList("users");

		// 		Permission denied		
		if (!sender.hasPermission("wladd")) {
			sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("message.permDenied"));
//			sender.sendMessage(ChatColor.RED + "Permission denied");
			return true;
		}
		if (args.length == 0) {
			return false;
		}
		String name = args[0];

//		Player not exist		
		if (!list.contains(name)) {
			sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("message.plrNotExist"));
//			sender.sendMessage(ChatColor.RED + "Player not exist");
			return true;
		}
// 		Remove player form white list	
		list.remove(name);
		wl.set("users", list);
		try {
			wl.save(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("message.plrRemoved").replace("{name}", name));
		
//		sender.sendMessage(ChatColor.RED + "Player " +  name + " was removed from white list");
		plugin.reloadConfig();
		
		return true;
	}
	
}

package mnwl.plugin;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WLIST implements CommandExecutor{
	private Main plugin;	
	public WLIST(Main plugin) {this.plugin = plugin;}	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		File users = new File(plugin.getDataFolder() + File.separator + "users.yml");
		FileConfiguration wl = YamlConfiguration.loadConfiguration(users);
		List<String> list = wl.getStringList("users");
		
		if (!sender.hasPermission("wladd")) {
			sender.sendMessage(ChatColor.RED + "Permission denied");
			return true;
		}
		
		for (int i = 0; i < list.size(); i++) {
			sender.sendMessage(ChatColor.GREEN + list.get(i));
		}
		
		return true;
	}
}

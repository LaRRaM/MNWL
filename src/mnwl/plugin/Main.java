package mnwl.plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
//Commands register
		getCommand("wladd").setExecutor(new WLADD(this));
		getCommand("wlremove").setExecutor(new WLREMOVE(this));
		getCommand("wlist").setExecutor(new WLIST(this));
//White list create				
		File users = new File(getDataFolder() + File.separator + "users.yml");
		
		if (!users.exists()) {
			try {
				users.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
//White list check
	public void join(PlayerJoinEvent e) {
		File users = new File(getDataFolder() + File.separator + "users.yml");
		FileConfiguration wl = YamlConfiguration.loadConfiguration(users);
		Player p = e.getPlayer();
		String name = p.getName();
		List<String> list = wl.getStringList("users");
		
		if (!list.contains(name)) {
			p.kickPlayer("You are not in white list!");
			return;
		}
	}
}

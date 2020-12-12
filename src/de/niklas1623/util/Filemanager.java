package de.niklas1623.util;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.niklas1623.main.Main;
import de.niklas1623.database.MySQL;

public class Filemanager {

	public static File getConfigFile() {
		return new File("plugins/DailyReward", "config.yml");
		
	}

	public static FileConfiguration getConfigFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getConfigFile());
	}	
	
	
	public static void readConfig() {
		FileConfiguration cfg = getConfigFileConfiguration();
		Main.getInstace().prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.Prefix") + "§r");
		Main.getInstace().RewardCommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.RewardCommand"));
		MySQL.username = cfg.getString("Database.username");
		MySQL.password = cfg.getString("Database.password");
		MySQL.database = cfg.getString("Database.database");  
		MySQL.host = cfg.getString("Database.host");
		MySQL.port = cfg.getString("Database.port");
	
	}
	
	public static void getDayReward(int onlinedays) {
		FileConfiguration cfg = getConfigFileConfiguration();
		Main.getInstace().Reward = ChatColor.translateAlternateColorCodes('&', cfg.getString("Rewards."+onlinedays));
	}
	
}

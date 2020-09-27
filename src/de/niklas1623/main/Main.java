package de.niklas1623.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.niklas1623.listeners.JoinListener;
import de.niklas1623.util.Filemanager;
import de.niklas1623.commands.ReloadCommand;
import de.niklas1623.database.MySQL;

public class Main extends JavaPlugin{

	public void onEnable() {
		instance = this;
		registerEvents();
		registerCommand();
		saveDefaultConfig();
		Filemanager.readConfig();
		MySQL.connect();
		MySQL.createTable();
		Bukkit.getConsoleSender().sendMessage(prefix + " Plugin wurde §ageladen§r!");
	}
	
	
	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		
	}


	private void registerCommand() {
		ReloadCommand reloadCMD = new ReloadCommand(this);
		getCommand("dailyreload").setExecutor(reloadCMD);
	}
	   
	public void onDisable() {
		MySQL.close();
		
		
	}
	
	
	public String prefix;
	public String RewardCommand;


	
	
	public static Main instance;
	
	public static Main getInstace() {
		return instance;

	}
	
}

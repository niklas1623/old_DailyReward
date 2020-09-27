package de.niklas1623.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklas1623.main.Main;
import de.niklas1623.util.DailyRewardManager;

public class JoinListener implements Listener {

	static SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd");

	@EventHandler
	public boolean onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String playername = p.getName();
		String uuid = p.getUniqueId().toString();

		if (DailyRewardManager.isJoined(uuid)) {
			giveDailyReward(uuid, playername);
		} else {
			DailyRewardManager.FirstJoin(playername, uuid);
		}
		return true;
	}

	public void giveDailyReward(String uuid, String playername) {
		Date Date = new Date();
		String letzterJoin = DailyRewardManager.getDate(uuid).toString();
		String today = now.format(Date);
		if (letzterJoin.equalsIgnoreCase(today)) {
		} else {
			DailyRewardManager.Join(uuid);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstace().RewardCommand.replaceAll("%player%", playername));
		}
	}

}

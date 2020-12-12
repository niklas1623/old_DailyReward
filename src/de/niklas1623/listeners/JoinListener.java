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
import de.niklas1623.util.Filemanager;

public class JoinListener implements Listener {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@EventHandler
	public boolean onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String playername = p.getName();
		String uuid = p.getUniqueId().toString();

		if (DailyRewardManager.isJoined(uuid)) {
			countDays(uuid, playername);
			giveDailyReward(uuid, playername);
		} else {
			DailyRewardManager.FirstJoin(playername, uuid);
			DailyRewardManager.setDays(uuid, 1);
		}
		return true;
	}

	public void giveDailyReward(String uuid, String playername) {
		Date Date = new Date();
		String letzterJoin = DailyRewardManager.getDate(uuid).toString();
		String today = sdf.format(Date);
		if (letzterJoin.equalsIgnoreCase(today)) {
		} else {
			DailyRewardManager.Join(uuid);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					Main.getInstace().RewardCommand.replaceAll("%player%", playername));
		}
	}

	public void countDays(String uuid, String playername) {
		String lastjoin = DailyRewardManager.getDate(uuid).toString();
		Date Date = new Date();
		String yesterday = sdf.format(Date.getTime() - 86399000);
		Bukkit.broadcastMessage(yesterday);
		if (lastjoin.equals(yesterday)) {
			Bukkit.broadcastMessage("It worked!");
			int onlinedays = DailyRewardManager.getDays(uuid);
			onlinedays = onlinedays + 1;
			DailyRewardManager.setDays(uuid, onlinedays);
			if (Filemanager.getConfigFileConfiguration().getString("Rewards." + onlinedays) == null) {

			} else {
				Filemanager.getDayReward(onlinedays);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						Main.getInstace().Reward.replaceAll("%player%", playername));
			}
		} else {
			DailyRewardManager.setDays(uuid, 0);
		}

	}

}

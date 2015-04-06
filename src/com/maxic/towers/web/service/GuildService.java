package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.model.*;
import com.maxic.towers.web.dao.GuildDao;


@Service("guildService")
public class GuildService {
	private GuildDao guildDao;

	@Autowired
	public void setGuildDao(GuildDao guildDao) {
		this.guildDao = guildDao;
	}

	public List<Guild> getGuilds() {
		return guildDao.getGuilds();
	}
	
	public boolean addGuild(Guild guild) {
		return guildDao.addGuild(guild);
	}

	public Guild getGuild(int id) {
		return guildDao.getGuild(id);
	}

	public boolean deleteGuild(int id) {
		return guildDao.deleteGuild(id);
	}

	public boolean editGuild(Guild guild) {
		return guildDao.editGuild(guild);
		
	}

	public boolean addGuilds(ArrayList<Guild> guildList) {
		return guildDao.addGuilds(guildList);
	}
}

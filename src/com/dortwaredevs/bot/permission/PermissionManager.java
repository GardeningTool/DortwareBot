package com.dortwaredevs.bot.permission;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author Gardening_Tool
 */
public class PermissionManager {
	
	private Cache<Long, PermissionUser> users;
	
	public PermissionManager() {
		File directory = new File("users" + File.separator);
		if (!directory.exists()) directory.mkdir();
		users = CacheBuilder.newBuilder().
				expireAfterWrite(1, TimeUnit.MINUTES)
				.concurrencyLevel(4)
				.build();
	}
	
	public PermissionUser getUser(long userId) {
		if (!users.asMap().containsKey(userId))
			users.put(userId, new PermissionUser(userId));
		return users.asMap().get(userId);
	}
}

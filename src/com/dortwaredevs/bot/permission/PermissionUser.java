package com.dortwaredevs.bot.permission;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PermissionUser {
	
	private long userId;
	private File file;
	private List<String> permissions;
	private static final File DIRECTORY = new File("permissionusers" + File.separator);
	
	public PermissionUser(long userId) {
		this(new File(DIRECTORY + "\\" + userId + ".user"));
	}
	
	public PermissionUser(File file) {
		this.file = file;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		this.userId = Long.parseLong(file.getName().replace(".user", ""));
		permissions = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				permissions.add(line);
			}
			reader.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
	public boolean hasPermission(String permission) {
		return permissions.contains(permission);
	}
	
	public void addPermission(String permission) {
		permissions.add(permission);
		update();
	}
	
	public void removePermission(String permission) {
		permissions.remove(permission);
		update();
	}

	public void update() {
		new Thread(() -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
				permissions.forEach(perm -> {
					try {
						writer.write(perm + "\n");
					} catch (IOException exc) {
						exc.printStackTrace();
					};
				});
				writer.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}).start();
	}
}

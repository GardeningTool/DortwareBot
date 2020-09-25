package com.dortwaredevs.bot.permission;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @author Gardening_Tool
 */
@Getter
public class PermissionUser {
	
	private long userId;
	private File file;
	private List<String> permissions;
	private static final File DIRECTORY = new File("users" + File.separator);
	
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
		this.userId = Long.valueOf(file.getName().replace(".user", ""));
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
				//Deleting and creating a new file to remove the contents
				file.delete();
				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
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

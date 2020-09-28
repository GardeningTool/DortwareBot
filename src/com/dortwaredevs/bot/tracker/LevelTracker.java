package com.dortwaredevs.bot.tracker;

import lombok.Getter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LevelTracker {

    /**
     * The tracker for levels. It's necessary to use a HashMap instead of a Cache
     * because in this case, data will be accessed from the map randomly (for example
     * when a user runs the xp leaderboard command) and, if it were stored on the disk
     * instead of system memory, it would take longer to give a response.
     */
    private volatile HashMap<Long, Integer> xp;

    /**
     * The file which XP data will be written to
     */
    private File xpFile;

    /**
     * The last time the xp map was written to the disk
     */
    @Getter private long lastSave;

    public LevelTracker() {
        lastSave = System.currentTimeMillis();
        xpFile = new File("xp.txt");
        if (!xpFile.exists()) {
            try {
                xpFile.createNewFile();
            } catch (IOException exc) {
                exc.printStackTrace();;
            }
        }
        xp = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(xpFile));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] ln = line.split(":");
                xp.put(Long.valueOf(ln[0]), Integer.valueOf(ln[1]));
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void save() {
        lastSave = System.currentTimeMillis();
        new Thread(() -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(xpFile, false));
                xp.forEach((userId, xp) -> {
                    try {
                        writer.write(userId + ":" + xp + "\n");
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                });
                writer.close();;
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }).start();
    }

    /**
     *
     * @param user The user ID to return the level of
     * @return The level of the user
     */
    public int getLevel(long user) {
        long xp = this.xp.getOrDefault(user, 0);
        if (xp == 0) return 0;
        return (int) xp / 100;
    }

    /**
     *
     * @param userId The user to add XP to
     * @param xp The amount of XP to give
     */
    public void addXp(long userId, int xp) {
        this.xp.put(userId, this.xp.getOrDefault(userId, 0) + xp);
    }

    /**
     * @return The keySet for the xp map sorted by amount of XP, as a list
     */
    public List<Long> getLeaderboard() {
        return xp.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(Map.Entry::getKey).collect(Collectors.toList());
    }
    /**
     *
     * @param id The user whos level wil be returned
     * @return The user's level
     */
    public long getXp(long id) {
        return this.xp.getOrDefault(id, 0);
    }
}

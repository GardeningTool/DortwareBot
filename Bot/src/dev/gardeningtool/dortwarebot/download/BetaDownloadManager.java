package dev.gardeningtool.dortwarebot.download;

import dev.gardeningtool.dortwarebot.event.impl.EventPrivateMessageReceived;
import dev.gardeningtool.dortwarebot.util.MessageUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.AttachmentOption;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BetaDownloadManager {

    private File directory;
    private File file;
    private List<Long> allowedIDs;
    private File betaFile;
    private Queue<User> betaUploadQueue;

    public BetaDownloadManager() {
        allowedIDs = new ArrayList<>();
        if (!(directory = new File("Config/")).isDirectory()) {
            directory.mkdir();
        }
        if (!(file = new File(directory + "/allowed-beta-testers.txt")).exists()) {
            try {
                file.createNewFile();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        String line = "";
        try {
            while((line = bufferedReader.readLine()) != null) {
                allowedIDs.add(Long.parseLong(line));
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        new Thread(() -> {
            while (!betaUploadQueue.isEmpty()) {
                User user = betaUploadQueue.poll();
                user.openPrivateChannel().complete().sendFile(betaFile).submit();
            }
        }).start();
    }

    public void addToBeta(User user) {
        allowedIDs.add(user.getIdLong());
        write();
    }

    private void write() {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(file, false);
            bufferedWriter = new BufferedWriter(writer);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            for (long user : allowedIDs) {
                bufferedWriter.write(user + "\n");
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void requestBeta(User user) {
        PrivateChannel privateChannel = user.openPrivateChannel().complete();
        if (!allowedIDs.contains(user.getIdLong())) {
            privateChannel.sendMessage(":x: You aren't a beta tester!");
            return;
        }
        betaUploadQueue.add(user);
        privateChannel.sendMessage(":clock: Please wait while we upload the latest beta. You're currently position " +
                betaUploadQueue.size() + "/" + betaUploadQueue.size() + " in the queue.");
    }
}
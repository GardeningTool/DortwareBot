package dev.gardeningtool.dortwarebot.config;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class DortwareConfiguration {

    private String token, status;
    private File directory;
    private File file;

    public DortwareConfiguration() {
        if (!(directory = new File("Config/")).isDirectory()) {
            directory.mkdir();
        }
        if (!(file = new File(directory + "/config.json")).exists()) {
            try {
                file.createNewFile();
            } catch (IOException exc) {
                exc.printStackTrace();
                return;
            }
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) (new JSONParser()).parse((new FileReader(file)));
        } catch (IOException | ParseException exc) {
            exc.printStackTrace();
        }
        token = (String) jsonObject.get("Token");
        status = (String) jsonObject.get("Status");
    }
}

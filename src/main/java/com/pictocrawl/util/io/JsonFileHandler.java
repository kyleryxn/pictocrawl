package com.pictocrawl.util.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pictocrawl.config.LoggingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JsonFileHandler implements ObjectWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileHandler.class);
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private static final String DEFAULT_FILENAME = "untitled.json";
    private static final String DEFAULT_DIRECTORY = "src/main/resources/"; // File.listRoots()[0].toString(), for root directory of drive "C:" for Windows, "/" for Unix

    public JsonFileHandler() {
        LoggingConfig.configure(JsonFileHandler.class);
    }

    @Override
    public void writeObjectToFile(Object object, String filename, String directory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename))) {
            String json = GSON.toJson(object);
            writer.write(json);

        } catch (IOException e) {
            LOGGER.error("Cannot write to file: {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    /*private String requireValidFilename(String filename) {
        return filename == null || filename.trim().isEmpty() ? DEFAULT_FILENAME : filename;
    }

    private String requireValidDirectory(String directory) {
        return directory == null || directory.trim().isEmpty() ? DEFAULT_DIRECTORY : directory;
    }*/

}
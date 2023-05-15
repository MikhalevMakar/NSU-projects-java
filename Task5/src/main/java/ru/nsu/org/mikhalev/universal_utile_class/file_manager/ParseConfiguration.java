package ru.nsu.org.mikhalev.universal_utile_class.file_manager;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;

import java.io.*;
import java.util.regex.Pattern;
import java.lang.reflect.Type;

@Log4j2
public class ParseConfiguration {

    private static final String formatJSON = ".*\\.json$";

    private static final String formatProperties = ".*\\.properties$";

    private ParseConfiguration() {
        throw new IllegalStateException("Utility class");
    }

    @Contract(pure = true)
    public static boolean isCorrectArgs(Integer correctNumArgs, String @NotNull ... args) {
        return args.length ==  correctNumArgs;
    }

    private static @NotNull String searchCommandLine(@NotNull String linkJSON, String formatFile) throws ExcParseFileJSON {
        Pattern patternTxt = Pattern.compile(formatFile);

        if (!linkJSON.matches(patternTxt.pattern())) {
            throw new ExcParseFileJSON("Incorrect file name, expected format JSON");
        }

        linkJSON = linkJSON.replaceFirst (".*=+", "");
        return linkJSON;
    }

    public static <T> @Nullable T parseConfigurationJSON(Type clazz, String linkJSON) throws ExcParseFileJSON{
        log.info("Entrance ParseFileJSON");

        linkJSON = searchCommandLine(linkJSON, formatJSON);

        try {
            Gson gson = new GsonBuilder().create();
            JsonReader reader = new JsonReader(new FileReader(linkJSON));

            return gson.fromJson(reader, clazz);
        } catch (JsonSyntaxException | FileNotFoundException e) {
            log.error("File json isn't found", e);
            return null;
        }
    }

    public static @NotNull String checkConfigurationProperties(String linkProperties) throws ExcParseFileJSON {
        log.info("Entrance ParseFileProperties");

        linkProperties = searchCommandLine(linkProperties, formatProperties);

        return linkProperties;
    }
}

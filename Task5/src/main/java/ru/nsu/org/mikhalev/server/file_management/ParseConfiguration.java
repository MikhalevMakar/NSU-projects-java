package ru.nsu.org.mikhalev.server.file_management;

//import com.google.gson.*;
//import com.google.gson.stream.JsonReader;

import lombok.extern.log4j.Log4j2;
//import org.apache.commons.cli.*;
//import org.jetbrains.annotations.Nullable;
//
//import java.io.*;
//import java.util.regex.Pattern;
//import java.lang.reflect.Type;
//
//@Log4j2
//public class ParseConfiguration {
//    private static final String formatJSON = ".*\\.json$";
//    private static final String formatProperties = ".*\\.properties$";
//
//    private ParseConfiguration() {
//        throw new IllegalStateException("Utility class");
//    }
//
//    private static String searchCommandLine(String linkJSON, String formatFile) throws ParseException {
//        Pattern patternTxt = Pattern.compile(formatFile);
//
//        if (!linkJSON.matches(patternTxt.pattern())) {
//            throw new ParseException("Incorrect file name, expected format JSON");
//        }
//
//        linkJSON = linkJSON.replaceFirst (".*=+", "");
//        return linkJSON;
//    }
//
//    public static <T> @Nullable T parseConfigurationJSON(Type clazz, String linkJSON)
//                                                                                      throws ParseException {
//        log.info("Entrance ParseFileJSON");
//
//        linkJSON = searchCommandLine(linkJSON, formatJSON);
//
//        try {
//            Gson gson = new GsonBuilder().create();
//            JsonReader reader = new JsonReader(new FileReader(linkJSON));
//
//            return gson.fromJson(reader, clazz);
//        } catch(JsonSyntaxException | FileNotFoundException e) {
//            log.error("File json isn't found", e);
//            return null;
//        }
//
//    }
//
//    public static String parseConfigurationProperties(String linkProperties) throws ParseException {
//        log.info("Entrance ParseFileProperties");
//
//        linkProperties = searchCommandLine(linkProperties, formatProperties);
//
//        return linkProperties;
//    }
//}

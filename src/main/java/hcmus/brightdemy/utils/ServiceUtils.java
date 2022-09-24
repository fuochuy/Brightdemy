package hcmus.brightdemy.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class ServiceUtils {
    public static List<String> parseStringToList(Map messages, String key) {
        String parser = String.valueOf(messages.get(key));

        return Arrays.asList(parser
                .replace("[", "")
                .replace("]", "")
                .split("\\s*, \\s*"));
    }
}

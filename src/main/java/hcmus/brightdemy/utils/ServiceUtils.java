package hcmus.brightdemy.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
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
    public static String getStringStream(ServletInputStream stream, int length) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
        for (; ; ) {
            int index = in.read(buffer, 0, length);
            if (index < 0) break;
            out.append(buffer, 0, index);
        }
        return out.toString();
    }
}

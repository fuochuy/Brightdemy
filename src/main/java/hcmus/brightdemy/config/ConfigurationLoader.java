package hcmus.brightdemy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigurationLoader {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationLoader.class);
    private static final String FOLDER = "conf/";
    private List<String> resources = new LinkedList<>();
    private Properties properties = new Properties();

    private ConfigurationLoader() {
        String profile = getProfile();
        initialize(profile);
        loadConfiguration();
    }

    public static ConfigurationLoader getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private void loadConfiguration() {
        for (String resource : resources) {
            loadConfiguration(resource);
        }
    }

    private void loadConfiguration(String resource) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        try {
            File file = new File(resource);
            if (!file.exists()) {
                return;
            }
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            bf = new BufferedReader(isr);
            properties.load(bf);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize(String profile) {
        if (profile.equals("prod")) {
            addResource("application-prod.properties");
        } else {
            addResource("application-dev.properties");
        }
    }

    private String getProfile() {
        String profile = System.getProperty("spring.profiles.active");
        LOG.info("getProfile : " + profile);
        if (null == profile || profile.equals("prod")) {
            return "prod";
        } else {
            return "dev";
        }
    }

    private void addResource(String s) {
        LOG.info("addResource : " + s);
        String resource = FOLDER + s;
        if (!this.resources.contains(resource)) {
            this.resources.add(resource);
        }
    }

    public Object get(String key) {
        return properties.get(key);
    }

    public Object get(String key, Object o) {
        return properties.getOrDefault(key, o);
    }

    public int getAsInteger(String key, int defaultNumb) {
        String trimmer = this.getTrimmed(key);
        if (trimmer != null) {
            return Integer.parseInt(properties.getProperty(key));
        } else {
            return defaultNumb;
        }
    }

    public String getAsString(String key) {
        return this.getTrimmed(key);
    }

    public String getAsString(String key, String defaultStr) {
        String value = this.getTrimmed(key);
        return value == null ? defaultStr : value;
    }

    public long getAsLong(String key, long defaultNumb) {
        String trimmer = this.getTrimmed(key);
        if (trimmer != null) {
            return Long.parseLong(properties.getProperty(key));
        } else {
            return defaultNumb;
        }
    }

    public boolean getAsBoolean(String key, boolean defaultValue) {
        String valueString = this.getTrimmed(key);
        if (null != valueString && !valueString.isEmpty()) {
            valueString = valueString.toLowerCase();
            if ("true".equals(valueString)) {
                return true;
            } else {
                return !"false".equals(valueString) && defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    private String getTrimmed(String key) {
        String value = this.properties.getProperty(key);
        return null == value ? null : value.trim();
    }

    private static class SingletonHelper {
        private static final ConfigurationLoader INSTANCE = new ConfigurationLoader();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BerryConf;



import BerryConf.error.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A Configuration Manager. Reads the file then uses Configuration Parser to
 * parse the file into a HashMap of keys and values.
 * @author indigo
 */
public class Config {
    private static Config instance = null;


    /**
     * Get the Config instance.
     * @return Config
     */
//    public static Config instance(){
//        if (instance == null) {
//            try {
//                instance = new Config();
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ConfigParserSyntaxException ex) {
//                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return instance;
//    }

    /**
     * The static method of getting a configuration from a key.
     * @param key, String of the key whose value you want to get.
     * @return the aformentioned value
     */
//    public static String getConfig(String key) {
//        String tmp = null;
//        try {
//            tmp = Config.instance().getConfig(key);
//        } catch (ConfigKeyNotFoundException ex) {
//            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if(tmp != null) {
//            Logger.getLogger(Config.class.getName()).log(Level.INFO,
//                    "Config requested: [" + key + ", " + tmp + "]");
//        }
//        return tmp;
//    }


    private HashMap<String, String> config;

    /**
     * Create a new Config manager.
     * @param configFileName the file to read from the disk and parse as a
     * configuration file
     * @param initialConfig a HashMap<String, String> that contains
     * configuration to add to the main config database.
     * @throws FileNotFoundException thrown when the file asked for cannot be
     * found
     * @throws ConfigParserSyntaxException thrown when the ConfigurationParser runs
     * into a syntax error
     * @throws IOException thown when there is a general IO exception.
     */
    public Config(String configFileName, HashMap<String, String> initialConfig)
            throws FileNotFoundException,
            ConfigParserSyntaxException, IOException {
        config = new HashMap();

        config.putAll(initialConfig);
        config.putAll(Config.parseConfigFile(configFileName));
    }

    /**
     * Create a new Config manager, with the main config database populated with
     * initialConfig
     * @param initialConfig what the main database will be populated with.
     */
    public Config(HashMap<String, String> initialConfig) {
        config = new HashMap();

        config.putAll(initialConfig);
    }

    /**
     * Create a new Config manager, and initialize the main database, but don't
     * do anyting with it.
     */
    public Config() {
        config = new HashMap();
    }

    /**
     * Parse and add the String paramater to the configuration database.
     * @param in String of the code to parse
     * @throws ConfigParserSyntaxException if the Parser encounters a syntax
     * error
     */
    public void addConfig(String in) throws ConfigParserSyntaxException {
        ConfigurationParser cp = new ConfigurationParser();
        cp.addParse(in);
        config.putAll(cp.getConfig());
    }


    /**
     * Load, parse, and add the contents of a file to the config database.
     * @param filename the location of the file to load, parse, and add
     * @throws FileNotFoundException thrown when the File couldn't be found
     * @throws ConfigParserSyntaxException thrown when the configuration parser
     * encountered a syntax error
     * @throws IOException thrown when an error is encountered when reading the
     * file in
     */
    public void addConfigFile(String filename) throws FileNotFoundException,
            ConfigParserSyntaxException, IOException {
        config.putAll(Config.parseConfigFile(filename));
    }

    /**
     * An instance version of getConfig(). Gets the value of a key
     * @param key String of the key whose value you would like to aquire
     * @return the aformentioned value
     * @throws ConfigKeyNotFoundException if the value was not found in the configuration
     */
    public String getConfig(String key) throws ConfigKeyNotFoundException {
        String tmp = config.get(key);
        if (tmp == null) {
            throw new ConfigKeyNotFoundException(key);
        }
        return tmp;
    }

    /**
     * Parse a file on the hard disk into a configuration object
     * @param filename the filename of the file to read.
     * @return HashMap<String, String> product of ConfigurationParser
     * @throws FileNotFoundException if the file isn't found
     * @throws ConfigParserSyntaxException a configuration parser error, or, I suppose, a general
     * exception.
     * @throws IOException when an IO error is encountered.
     */
    public static HashMap<String, String> parseConfigFile(String filename)
            throws FileNotFoundException, ConfigParserSyntaxException, IOException {
        ConfigurationParser cp = new ConfigurationParser();
        cp.addParse(readFileAsString(filename));
        return cp.getConfig();
    }

    /**
     * Read a file into a string.
     * @param filePath where the file is.
     * @return String of  the file contents.
     * @throws IOException if something failed.
     */
    public static String readFileAsString(String filePath) throws IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
        f.read(buffer);
        return new String(buffer);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BerryConf;

import BerryConf.error.ConfigParserSyntaxException;
import java.util.*;


/**
 * A nice configuration parsing library. Note that this library is fairly
 * inflexible. Whitespace is ignored, and comments are started with a "#". Each
 * line that isn't whitespace or a comment should be in a key: value format.
 * Both the key and the value are String.trim()ed (prefixed and postfixed
 * whitespace is removed), and returned as a HashMap<String, String>. Multiple
 * expressions can be placed on the same line, separated by a ";".
 * @author indigo
 */
public class ConfigurationParser {
    private HashMap<String, String> config;

    /**
     * Create a new configuration parser. Note that this only initializes the
     * hashmap of keys
     */
    public ConfigurationParser() {
        config = new HashMap<String, String>();
    }

    /**
     * Do a simple parse of a string and return a HashMap
     * @param input as a string, to parse. Should be properly formatted.
     * @return A HashMap<String, String> containing the parsed configuration
     * @throws ConfigParserSyntaxException when an expression is not properly formatted.
     */
    public HashMap<String, String> parse(String input) throws ConfigParserSyntaxException {
        HashMap<String, String> results = new HashMap<String, String>();
        String[] lines = input.split("\n");
        for (String s : lines) {
            // Iterate through lines and pass them to the line parser
            HashMap<String, String> tmp = parseLine(s);
            if (tmp != null) {
                results.putAll(tmp);
            }

        }

        return results;
    }

    private HashMap<String, String> parseLine(String physical_line) throws ConfigParserSyntaxException {
        HashMap<String, String> results = new HashMap<String, String>();
        String expression;
        if (physical_line.equals("") || physical_line.equals(" ") || physical_line.equals("\t")) {
            return null; // It is merely an empty line, and we don't do anything else.
        } else {
            if (physical_line.contains("#")) { // If it contains a "#", which denotes a comment
                // Grab the actual expression (before the "#") and set it equal to the expression.
                int i = physical_line.indexOf("#");
                expression = physical_line.substring(0, i); // I'm not entirely sure why the second argument isn't i-1
            } else {
                expression = physical_line; // This sets expression if there isn't a comment.
            }
            // There might be multiple expressions on each line, and they are separated by a ";"
            String[] expressions = expression.split(";");


            // Iterate through and parse all the expressions, putting them into results
            for (String expr : expressions) {
                String[] tmp = parseExpr(expr);
                if (tmp != null)
                    results.put(tmp[0], tmp[1]);
            }
        }

        return results;
    }

    /**
     * Parse an expression in key:value form.
     * @param expr, the expression to parse
     * @return a String[] with [0] as the key and [1] as the value, or null if the expression is empty
     * @throws ConfigParserSyntaxException when the expression is improperly formatted (does not contain a ":")
     */
    private String[] parseExpr(String expr) throws ConfigParserSyntaxException {
        if (!expr.equals("")) {
           // Make sure the expression contains a separator. If it doesn't, through that exception as far as you can before catching it!
            if (!expr.contains(":")) {
                throw new ConfigParserSyntaxException("EXPR \"" + expr + "\" did not contain a :!");
            }

            // Note that while I would love to keep this in a kind of map format,
            //this works best, though it mores the actual adding it to the hashmap to the line parser
            String[] key_value = expr.split(":");

            for (int i = 0; i < key_value.length; i++)
                key_value[i] = key_value[i].trim(); // Go through and trim the white space from each item
            return key_value;
        } else {
            return null;
        }
    }

    /**
     * Parses the param and adds it to the general config, which can be got from
     * getConfig();
     * @param code as a string, of Configuration to parse.
     * @throws ConfigParserSyntaxException when the parser encounters a syntax
     * error
     */
    public void addParse(String code) throws ConfigParserSyntaxException {
        config.putAll(parse(code));
    }

    /**
     * Get the config that you have been building with addParse()
     * @return The cumulative configuration that you have built with addParse()
     */
    public HashMap<String, String> getConfig() {
        return config;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BerryConf.error;

/**
 *
 * @author andrew
 */
public class ConfigParserSyntaxException extends Exception {

    public ConfigParserSyntaxException() {
        super("There was a syntax error the configuration file");
    }

    public ConfigParserSyntaxException(String m) {
        super(m);
    }

}

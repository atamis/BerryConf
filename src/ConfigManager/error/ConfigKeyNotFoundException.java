/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConfigManager.error;

/**
 *
 * @author andrew
 */
public class ConfigKeyNotFoundException extends Exception {

    /**
     * General, non specific, message.
     */
    public ConfigKeyNotFoundException() {
        super("Key requested was not found");
    }

    /**
     * When we actually know the key we couldn't find!
     * @param key, the key we couldn't find.
     */
    public ConfigKeyNotFoundException(String key) {
        super("Key \"" + key + "\" was not found");
    }
}

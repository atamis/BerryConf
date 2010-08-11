/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BerryConf;

import BerryConf.Config;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew
 */
public class ConfigTest {

    public ConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of addConfig method, of class Config.
     */
    @Test
    public void testAddConfig() throws Exception {
        System.out.println("addConfig");
        String in = "test:test";
        Config instance = new Config();
        instance.addConfig(in);
        assertEquals("test", instance.getConfig("test"));
    }

    /**
     * Test of getConfig method, of class Config.
     */
    @Test
    public void testGetConfig() throws Exception {
        System.out.println("getConfig");
        String key = "input";
        String value = "hell yeah";

        HashMap<String, String> hm= new HashMap();
        hm.put(key, value);

        Config instance = new Config(hm);

        assertEquals(value, instance.getConfig(key));
    }
}
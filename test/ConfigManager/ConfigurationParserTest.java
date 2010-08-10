/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConfigManager;

import ConfigManager.error.ConfigParserSyntaxException;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew
 */
public class ConfigurationParserTest {

    public ConfigurationParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of parse method, of class ConfigurationParser.
     */
    @Test
    public void testParse() throws ConfigParserSyntaxException{
        System.out.println("parse");
        String input = "test:test";
        ConfigurationParser instance = new ConfigurationParser();
        HashMap expResult = new HashMap();

        expResult.put("test", "test");

        HashMap result = instance.parse(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of addParse method, of class ConfigurationParser.
     */
    @Test
    public void testAddParse() throws ConfigParserSyntaxException{
        System.out.println("addParse");
        String code = "test:test";
        ConfigurationParser instance = new ConfigurationParser();
        instance.addParse(code);

        HashMap expResult = new HashMap();

        expResult.put("test", "test");

        assertEquals(expResult, instance.getConfig());
    }

    @Test
    public void testRealWorld() throws ConfigParserSyntaxException{
        System.out.println("realworld");
        ConfigurationParser instance = new ConfigurationParser();
        String input = "test: even more awesome\n" +
                        "game_name:Overmachine_Retribution\n" +
                        "default_area:first_area\n" +
                        "enable_groovy_scripting:false\n";
        instance.parse(input);
    }

//    @Test
//    public void testParseExpr() {
//        System.out.println("parseExpr");
//        String in = "test:test";
//        String[] exp = {"test", "test"};
//
//        String[] act = new ConfigurationParser().
//    }
}
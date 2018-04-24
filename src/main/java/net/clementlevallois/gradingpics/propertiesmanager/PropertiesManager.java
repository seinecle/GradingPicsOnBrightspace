/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.propertiesmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author LEVALLOIS
 */
public class PropertiesManager {


    public String readingProperty(String key) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        InputStream input;

        input = new FileInputStream("config.properties");

        // load the properties file
        prop.load(input);

        // get the property value and returns it
        return prop.getProperty(key);

    }
}

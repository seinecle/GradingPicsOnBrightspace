/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertiesManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author LEVALLOIS
 */
public class PropertiesManager {

    public void writingProperties() {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("pathAssignments", "H:\\files\\Lectures and tutorials\\EMLyon\\CODAPPS\\desktop grading\\Assignment 1");
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("error in io properties");
                }
            }

        }
    }

    public String readingProperty(String key) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        InputStream input = null;

        input = new FileInputStream("config.properties");

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        return prop.getProperty(key);

    }
}

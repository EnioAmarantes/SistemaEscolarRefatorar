/**
 * 
 */
package shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author enio
 *
 */
public abstract class ADatabase implements IDatabase {
    protected static String url;
    protected static String username;
    protected static String password;
    
    public static void init(File fileName) throws IOException, ClassNotFoundException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        System.out.println(driver);
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }

}

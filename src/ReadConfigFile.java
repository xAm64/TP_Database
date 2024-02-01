import java.io.*;
import java.util.*;
public class ReadConfigFile {
	private String driver;
	private String url;
	private String login;
	private String password;
	
	public ReadConfigFile() throws IOException {
		Properties prop = readPropertiesFile("config.properties");
		this.driver = prop.getProperty("db.driver.class");
		this.url = prop.getProperty("db.url");
		this.login = prop.getProperty("db.login");
		this.password = prop.getProperty("db.password");
	}
	
	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}

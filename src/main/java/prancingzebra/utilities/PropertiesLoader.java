package prancingzebra.utilities;

import prancingzebra.exceptions.InternalException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

public class PropertiesLoader {
	public static Properties loadPropertiesFromPackage(String filePath) {
		Properties prop = new Properties();
		try (InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream(filePath)) {
			prop.load(in);
			return prop;
		} catch (IOException e) {
			throw new InternalException("Failed Properties Loader");
		}
	}
}
package persistance;

import java.io.InputStream;
import java.util.Properties;



public class BaseConfiguration {

	private static ProjectLogger log = ProjectLogger
			.getLogger(BaseConfiguration.class);

	Properties properties;

	private static BaseConfiguration instance;

	public BaseConfiguration() {
		loadProperties(BaseConfiguration.class);
		if (!this.getClass().getSimpleName().equals("BaseConfiguration")) {
			loadProperties(this.getClass());
		}
	}

	private void loadProperties(Class<?> clazz) {
		try {
			InputStream file = clazz.getClassLoader().getResourceAsStream(
					getConfigurationFileName(clazz));
			if (properties == null) {
				properties = new Properties();
			}
			properties.load(file);
			file.close();
		} catch (Exception e) {
			log.error(
					"Error: No pudieron obtenerse los valores de configuraci�n base",
					e);
		}
	}

	private String getConfigurationFileName(Class<?> clazz) {

		String fileName = clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ clazz.getSimpleName().substring(1) + ".properties";
		return fileName;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public String[] getValueArray(String key) {
		String[] array = new String[0];
		String value = properties.getProperty(key);
		if ((value != null) && (!value.equals(""))) {
			array = value.split(",");
		}
		return array;
	}

	public int getValueInt(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

	public static BaseConfiguration getInstance() {
		if (instance == null) {
			instance = new BaseConfiguration();
		}
		return instance;
	}
}

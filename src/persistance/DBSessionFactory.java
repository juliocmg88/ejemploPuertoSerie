package persistance;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.HashMap;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBSessionFactory {

	private static DBSessionFactory instance;

	private HashMap<String, ComboPooledDataSource> dataSources;

	protected DBSessionFactory() {
		initialize();
	}

	public static DBSessionFactory getInstance() {
		if (instance == null) {
			instance = new DBSessionFactory();
		}
		return instance;
	}

	protected void initialize() {
		this.dataSources = new HashMap<String, ComboPooledDataSource>();
		initializeBaseDataSource();
	}

	private void initializeBaseDataSource() {
		String driverClassName = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.driverClassName");
		String url = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.url");
		String userName = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.userName");
		String password = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.password");
		System.out.println(driverClassName+" "+ url+" "+ userName+" "+ password);
		addDataSource("base", driverClassName, url, userName, password);
	}

	public void addDataSource(String dataSourceName, String driverClassName,
			String url, String userName, String password) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driverClassName);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userName);
			dataSource.setPassword(password);
                        // Opciones adicionales
                        dataSource.setMinPoolSize(5);
                        dataSource.setAcquireIncrement(5);
                        dataSource.setMaxPoolSize(20);
                        dataSource.setMaxStatements(180);
                        dataSource.setIdleConnectionTestPeriod(300);
                        dataSource.setMaxIdleTime(600);
                        dataSource.setTestConnectionOnCheckin(true);
                        dataSource.setPreferredTestQuery("SELECT 1");
			this.dataSources.put(dataSourceName, dataSource);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	private ComboPooledDataSource getDataSource(String dataSourceName) {
		return (ComboPooledDataSource) this.dataSources.get(dataSourceName);
	}

	public DBSession openBaseDBSession() {
		Connection connection = null;
		try {
			connection = this.getDataSource("base").getConnection();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new RuntimeException(
					"Error obteniendo Conexion de Base de Datos", t);
			
		}
		return new DBSession(connection);
	}

	public DBSession openDBSession(String dataSource) {
		Connection connection = null;
		try {
			connection = this.getDataSource(dataSource).getConnection();
		} catch (Throwable t) {
			throw new AppRuntimeException(
					"Error obteniendo Conexion de Base de Datos", t);
		}
		return new DBSession(connection);
	}

}

package persistance;

public class BasePersistenceSessionFactory {
	private static BasePersistenceSessionFactory instance;

	private BasePersistenceSessionFactory() {
		String driverClassName = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.driverClassName");
		String url = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.url");
		String userName = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.userName");
		String password = BaseConfiguration.getInstance().getValue(
				"base.services.persistence.db.password");
		DBSessionFactory.getInstance().addDataSource("base", driverClassName,
				url, userName, password);
	}

	public static BasePersistenceSessionFactory getInstance() {
		if (instance == null) {
			instance = new BasePersistenceSessionFactory();
		}
		return instance;
	}

	public BasePersistenceSession openBasePersistenceSession() {

		BasePersistenceSession persistenceSession = new BasePersistenceSession(
				DBSessionFactory.getInstance().openDBSession("base"));

		return persistenceSession;
	}

}

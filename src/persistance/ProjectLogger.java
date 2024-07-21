package persistance;

import org.apache.log4j.Logger;

public class ProjectLogger {

	Logger logger;

	public ProjectLogger(Logger logger) {
		this.logger = logger;
	}

	public static ProjectLogger getLogger(Class<?> clazz) {
		return new ProjectLogger(Logger.getLogger(clazz));
	}

	public void trace(Object message) {
		this.logger.trace(message);
	}

	public void error(Object message) {
		this.logger.error(message);
	}

	public void error(Object message, Throwable t) {
		this.logger.error(message, t);
	}

	public void warn(Object message) {
		this.logger.warn(message);
	}

	public void info(Object message) {
		this.logger.info(message);
	}

	public void fatal(Object message, Throwable t) {
		this.logger.fatal(message, t);
	}

	public void fatal(Object message) {
		this.logger.fatal(message);
	}

}

package persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;



public class DBSession {

	private static ProjectLogger log = ProjectLogger.getLogger(DBSession.class);

	private Connection connection;

	private Statement currentStatement;

	private ArrayList<Statement> statements;

	protected DBSession(Connection connection) {
		this.connection = connection;
		this.statements = new ArrayList<Statement>();
	}

	private Statement getCurrentStatement() throws SQLException {
		if (this.currentStatement == null) {
			this.currentStatement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		return this.currentStatement;
	}

	private void resetCurrentStatement() {
		this.statements.add(this.currentStatement);
		this.currentStatement = null;
	}

	public void beginTransaction() throws SQLException {
		this.connection.setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException {
		this.connection.commit();
		this.connection.setAutoCommit(true);
	}

	public void rollbackTransaction() {
		try {
			this.connection.rollback();
			this.connection.setAutoCommit(true);
		} catch (Exception e) {
			log.error("Error haciendo rollback", e);
		}
	}

	public int executeUpdate(String sql) throws SQLException {
		return this.getCurrentStatement().executeUpdate(sql);
	}

	public boolean execute(String sql) throws SQLException {
		return this.getCurrentStatement().execute(sql);
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = this.getCurrentStatement().executeQuery(sql);
		this.resetCurrentStatement();
		return rs;
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

	public void close() {
		try {
			closeStatements();
			this.connection.close();
		} catch (SQLException e) {
			log.error("Error cerrando objectos de persistencia", e);
		}
	}

	private void closeStatements() {
		if (this.currentStatement != null) {
			try {
				this.currentStatement.close();
			} catch (SQLException e) {
				log.error("Error cerrando objectos de persistencia", e);
			}
		}
		for (Iterator<Statement> iter = this.statements.iterator();;) {
			Statement statement = (Statement) iter.next();
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Error cerrando objectos de persistencia", e);
			}
		}
	}

}

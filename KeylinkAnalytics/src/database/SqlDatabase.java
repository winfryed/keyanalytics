package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import database.OutputArray;
import database.OutputFunc;
import database.OutputObject;
import database.OutputRS;
import database.RS;
import database.Updater;


/**
 * This class represents an SQL Database.
 * @author Matthias Weigt
 */
public class SqlDatabase {
	private String url;
	private String username;
	private String password;
	private String databaseName;
	private int port;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Provides some get ResultSet methods for Database.
	 * 
	 * {@link OutputRS}
	 */
	public OutputRS outputRS;
	
	/**
	 * Provides some get Array methods for Database.
	 * 
	 * {@link OutputArray}
	 */
	public OutputArray outputArray;
	
	/**
	 * Provides some a Object methods for Database.
	 * 
	 * {@link OutputObject}
	 */
	public OutputObject outputObj;
	
	
	/**
	 * Provides a Func method for Database.
	 * 
	 * {@link OutputFunc}
	 */
	public OutputFunc outputFunc;
	
	/**
	 * Provides some Update methods for Database.
	 * 
	 * {@link Updater}
	 */
	public Updater update;
	
	public RS resultSetChecker;
	
	public SqlDatabase(String url, String username, String password, String databaseName, int port) {
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
		this.outputRS = new OutputRS(this);
		this.outputArray = new OutputArray(this);
		this.outputObj = new OutputObject(this);
		this.outputFunc = new OutputFunc(this);
		this.update = new Updater(this);
		this.resultSetChecker = new RS();
	}
	
	/**
	 * This Method can be used query this Database and get an Output.
	 * @param queryString An sql String like select only usable for Output Querys.
	 * @return an ResultSet.
	 */
	public ResultSet getQuery(String queryString) {
		connect();
		
		try {
			ResultSet rs = statement.executeQuery(queryString);
			return rs;
		} catch (SQLException e) {
			System.out.println("Fehler bei Query: " + queryString);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This Method can be used to query this Database and manipulate the Database.
	 * @param queryString An sql String like update or insert only usable for update Querys.
	 * @return 0 if it was no sucess.
	 */
	public int setQuery(String queryString) {
		connect();
		try {
			int i = statement.executeUpdate(queryString);
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private boolean connect() {
		return connect(0);
	}
	
	private boolean connect(int i) {
		if(connection != null) {
			return true;
		}
		if(i >= 10) {
			return false;
		}
		try {
			connection = DriverManager.getConnection(String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", url, port, databaseName, username,password));
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			try {
				System.err.println("hier");
				e.printStackTrace();
				TimeUnit.SECONDS.sleep(120*i);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return connect(i + 1);
		}
		return true;
	}
	
	
	public boolean close() {
		try {
			statement.close();
			connection.close();
			statement = null;
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
}
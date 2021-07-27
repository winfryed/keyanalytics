package database;

import java.sql.ResultSet;

/**
 * This Class is used in Database.java in the same package.
 * It gives some Methods to get an Output ResultSet for Database Class.
 * 
 * @author Matthias Weigt
 *
 */
public class OutputRS {
	private SqlDatabase db;
	
	
	
	public OutputRS(SqlDatabase db) {
		this.db = db;
	}

	/**
	 * Outputs SQL table.
	 * 
	 * @param tableName the Name of the database table.
	 * @return The table as a ResultSet.
	 */
	public ResultSet table(String tableName) {
		return db.getQuery("select * From " + tableName);
	}

	/**
	 * Outputs SQL table.
	 * 
	 * @param tableName   the Name of the database table.
	 * @param whereClause which is executed in the SQL statement.
	 * @return The table as a ResultSet.
	 */
	public ResultSet table(String tableName, String whereClause) {
		return db.getQuery("select * From " + tableName + " Where " + whereClause);
	}

	/**
	 * 
	 * @param tableName  the Name of the database table.
	 * @param columnName the name of the Column you wish to output.
	 * @return the column as a ResultSet.
	 */
	public ResultSet column(String tableName, String columnName) {
		return db.getQuery("select " + columnName + " From " + tableName);
	}

	/**
	 * 
	 * @param tableName   the Name of the database table.
	 * @param columnName  the name of the Column you wish to output.
	 * @param whereClause which is executed in the SQL statement.
	 * @return the column as a ResultSet.
	 */
	public ResultSet column(String tableName, String columnName, String whereClause) {
		return db.getQuery("select " + columnName + " From " + tableName + " Where " + whereClause);
	}
}

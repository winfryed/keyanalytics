package database;

/**
 *  @author Collin Flügel
 */
public class Updater {
	private SqlDatabase db;
	public Updater(SqlDatabase db) {
		this.db = db;
	}
	/**
	 * This Method can be used to update all values in a Column of a specific table.
	 * 
	 * @param tableName  Name of the table.
	 * @param columnName Name of the column.
	 * @param value      The new value.
	 */
	public void updateColumn(String tableName, String columnName, String value) {
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + value;
		db.setQuery(cool);
	}
	
	/**
	 * This Method can be used to update all values in a Column of a specific table.
	 * 
	 * @param tableName  Name of the table.
	 * @param columnName Name of the column.
	 * @param value      The new value.
	 */
	public void updateColumn(String tableName, String columnName, int value) {
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + value;
		db.setQuery(cool);
	}
	
	/**
	 * This Method can be used to update all values in a Column of a specific table.
	 * Converts Boolean:
	 * true --> 1
	 * false--> 0
	 * 
	 * @param tableName  Name of the table.
	 * @param columnName Name of the column.
	 * @param value      The new value.
	 */
	public void updateColumn(String tableName, String columnName, boolean value) {
		int convertedV = value ? 1 : 0;
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + convertedV;
		db.setQuery(cool);
	}

	/**
	 * This Method can be used to update specific values in a Column of a specific
	 * table.
	 * 
	 * @param tableName   Name of the table.
	 * @param columnName  Name of the column.
	 * @param value       The new value.
	 * 
	 * @param whereClause A String that looks like this: "columnName = value".
	 */
	public void updateColumn(String tableName, String columnName, String value, String whereClause) {
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + value + " WHERE " + whereClause;
		db.setQuery(cool);
	}
	
	/**
	 * This Method can be used to update specific values in a Column of a specific
	 * table.
	 * 
	 * @param tableName   Name of the table.
	 * @param columnName  Name of the column.
	 * @param value       The new value.
	 * 
	 * @param whereClause A String that looks like this: "columnName = value".
	 */
	public void updateColumn(String tableName, String columnName, int value, String whereClause) {
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + value + " WHERE " + whereClause;
		db.setQuery(cool);
	}
	
	/**
	 * This Method can be used to update specific values in a Column of a specific
	 * table.
	 * Converts Boolean:
	 * true --> 1
	 * false--> 0
	 * 
	 * @param tableName   Name of the table.
	 * @param columnName  Name of the column.
	 * @param value       The new value.
	 * 
	 * @param whereClause A String that looks like this: "columnName = value".
	 */
	public void updateColumn(String tableName, String columnName, boolean value, String whereClause) {
		int convertedV = value ? 1 : 0;
		String cool = "UPDATE " + tableName + " SET " + columnName + " = " + convertedV + " WHERE " + whereClause;
		db.setQuery(cool);
	}

	public void insert(String tableName,String[] values) {
		String sqlString = "INSERT INTO " + tableName + " VALUES (";
		for(int i = 0; i < values.length; i++) {
			
			
			sqlString = sqlString + ((i == 0) ? ((values[i] != null) ? "'" + values[i] + "'" : "null") : ", " + ((values[i] != null) ? "'" + values[i] + "'" : "null"));
		}
		sqlString = sqlString + ");";
		System.out.println(sqlString);
		db.setQuery(sqlString);
	}
	
}
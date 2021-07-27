
package database;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This Class provides some Methods to get Arrays out of the Database.
 * @author Matthias Weigt
 *
 */
public class OutputArray {
	private SqlDatabase db;
	public OutputArray(SqlDatabase db) {
		this.db = db;
	}
	/**
	 * Can be used to get an Array of a column in a Table of the Database.
	 * @param tableName
	 * @param columnName
	 * @param whereClause
	 * @return The Array of Strings.
	 */
	public String[] column(String tableName, String columnName, String whereClause) {
		ResultSet rs = (whereClause == "") ? db.outputRS.column(tableName, columnName)
				: db.outputRS.column(tableName, columnName, whereClause);
		try {
			
			int rows = db.resultSetChecker.getRows(rs);
			String[] outputArray = new String[rows];
			int i = 0;
			while (rs.next()) {
				outputArray[i] = rs.getString(1);
				i++;
			}
			return outputArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Can be used to get an Array of a column in a Table of the Database.
	 * @param tableName
	 * @param columnName
	 * @return The Array of Strings.
	 */
	public String[] column(String tableName, String columnName) {
		return column(tableName, columnName, "");
	}
	/**
	 * Can be used to get an Array of a Table of the Database.
	 * @param tableName
	 * @param whereClause
	 * @return An String[][] array which represents the Database Table;
	 */
	public String[][] table(String tableName, String whereClause) {
		ResultSet rs = (whereClause == "") ? db.outputRS.table(tableName)
				: db.outputRS.table(tableName, whereClause);
		try {
			
			int columns = db.resultSetChecker.getColumnCount(rs);
			int rows = db.resultSetChecker.getRows(rs);
			String[][] outputArray = new String[rows][columns];
			int i = 0;
			while (rs.next()) {
				for (int k = 0; k < columns; k++) {
					outputArray[i][k] = rs.getString(k+1);
				}
				i++;
			}
			return outputArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Can be used to get an Array of a Table of the Database.
	 * @param tableName
	 * @return An String[][] array which represents the Database Table;
	 */
	public String[][] table(String tableName) {
		return table(tableName, "");
	}
}

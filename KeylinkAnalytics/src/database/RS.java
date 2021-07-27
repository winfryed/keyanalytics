package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Output Result set
 *  @author Matthias Weigt
 */
public class RS {
	/**
	 * Computes how many rows a ResultSet has.
	 * @param rs a ResultSet.
	 * @return the rows of the given set.
	 */
	protected int getRows(ResultSet rs) {
		try {
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Computes how many columns a ResultSet has.
	 * @param rs a ResultSet.
	 * @return the columns of the given set.
	 */
	public int getColumnCount(ResultSet rs) {
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Returns a column name of column index.
	 * @param rs a ResultSet.
	 * @param columnIndex.
	 * @return the column name.
	 */
	public String getColumnName(ResultSet rs, int columnIndex) {
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			return rsmd.getColumnName(columnIndex);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Computes an array of all column names of a given set.
	 * @param rs a ResultSet.
	 * @return the name array.
	 */
	public String[] getColumnNames(ResultSet rs) {
		int colCount = getColumnCount(rs);
		if(colCount == 0) {
			return null;
		}
		String[] outputArray = new String[colCount];
		for(int i = 0; i<colCount;i++) {
			outputArray[i] = getColumnName(rs, i+1);
		}
		return outputArray;
	}
	
}

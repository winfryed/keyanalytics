package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides some SQL outputs as functions.
 *  @author Matthias Weigt
 */
public class OutputFunc {
	private SqlDatabase db;
	public OutputFunc(SqlDatabase db) {
		this.db = db;
	}
	
	public String func(String funcName,String tableName, String columnName , String whereClause) {
		ResultSet rs = db.getQuery("select " + funcName + "(" + columnName + ")" +  " From " + tableName + " Where " + whereClause);
		try {
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public synchronized String func(String funcName,String tableName, String columnName) {
		ResultSet rs = db.getQuery("select " + funcName + "(" + columnName + ")" +  " From " + tableName);
		try {
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

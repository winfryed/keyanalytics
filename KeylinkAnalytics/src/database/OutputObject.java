package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class can output a HashMap.
 *  @author Matthias Weigt
 */
public class OutputObject {
	private SqlDatabase db;
	public OutputObject(SqlDatabase db) {
		this.db = db;
	}
	/**
	 * Method returns a Hashmap of one Row of SQL Table.
	 * @param tableName of the Table;
	 * @param clearWhereStatement where only one row of SQL Table will be returnt. Like Id = 1; No Id > 3;
	 * @return the <String,String>Hashmap.
	 */
	public synchronized HashMap<String,String> getRowAsHashmap(String tableName,String clearWhereStatement) {
		HashMap<String,String> outputMap = new HashMap<String,String>();
		ResultSet rs = db.outputRS.table(tableName, clearWhereStatement);
		
		if(db.resultSetChecker.getRows(rs) != 1) {
			return null;
		}
		String[] rsNames = db.resultSetChecker.getColumnNames(rs);
		try {
			rs.next();
			for(int i = 0; i < rsNames.length; i++) {
				outputMap.put(rsNames[i], rs.getString(i+1));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return outputMap;
	}
}

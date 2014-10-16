package search;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DataBaseManager;

public class SearchManager {
	
	private static String SEARCHURL = "select * from srcConstraints";
	
	public static String[] search(Object[] input, Object output) throws SQLException{
		DataBaseManager.connect();
		ResultSet result = DataBaseManager.query(SEARCHURL);
		while(result.next()){
			String[] input1 = result.getString(4).split("\n");
			if(input1.length != input.length){
				continue;
			}
			String[] constraints = result.getString(3).split("\n");
			addResultToconstraints(input, output, constraints, input1);
		}
		return null;
	}

	private static void addResultToconstraints(Object[] input, Object output,
			String[] constraints, String[] input1) {
		// TODO Auto-generated method stub
		
	}

}

package com.hr.employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

public class DBUtilEmployee {
	
	private DataSource dataSource;
	
	public DBUtilEmployee(DataSource dataSource) throws Exception{
		this.dataSource=dataSource;
	}
	
	public ResultSet get_EmpInfo() throws Exception{
		Connection conn = dataSource.getConnection();
		String sql="BEGIN "+
					"HR.GET_EMPINFO(?); "+
					"END;";
		CallableStatement stmt = conn.prepareCall(sql);
		stmt.registerOutParameter(1, OracleTypes.CURSOR);
		stmt.execute();
		ResultSet rSet=(ResultSet)stmt.getObject(1);
		while(rSet.next()) {
			System.out.println(rSet.getString(1));
		}
		return rSet;
	}

}

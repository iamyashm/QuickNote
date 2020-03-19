package application;
import java.sql.*;

public class DBConnection {
	Connection con;
	DBConnection() {
		try  {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sys as sysdba","yash2000");
		}
		catch(Exception e) {
			System.out.println("Could not connect to database");
		}
	}
	
	Connection getCon() {
		return con;
	}
}

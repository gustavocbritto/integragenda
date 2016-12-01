package persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DAO {
	protected Connection con;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	protected Statement st;
	
	String url="jdbc:postgresql://localhost:5432/integragenda";
	String usuario="postgres";
	String senha= "123456";
	
	protected void open()throws Exception{

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);

	}
	public void close()throws Exception{
		con.close();
	}
}

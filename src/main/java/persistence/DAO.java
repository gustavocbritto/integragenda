package persistence;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	Statement st;
	
	String url="jdbc:postgresql://localhost:5432/PClientes";
	String usuario="postgres";
	String senha= "2734";
	
	protected void open()throws Exception{
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, usuario, senha);
	}
	public void close()throws Exception{
		con.close();
	}
}

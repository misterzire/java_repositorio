package conexaoBanco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoMySQL implements FabricaConexao {
	
	private Properties props = null; 
	private static Connection con;
	private String url;
	private String userName;
	private String password;
	private String driver;
	
	@SuppressWarnings("unused")
	private ConexaoMySQL(){
		
		
	}
	
	ConexaoMySQL(Properties props){
		
		this.props = props;
		
	}
 	
	@Override
	public void init() throws IOException, ClassNotFoundException {
				
		// get the properties
		driver = props.getProperty("MYSQL_JDBC_DRIVER");
		url = props.getProperty("MYSQL_JDBC_CONNECTION_URL");
		userName = props.getProperty("MYSQL_USERNAME");
		password = props.getProperty("MYSQL_PASSWORD");

		// load the class
		Class.forName(driver);

	}

	@Override
	public Connection getConnection()  {

		if (con == null) {

			try {

				init();

				// get the connection to the database
				con = DriverManager.getConnection(url, userName, password);
								
				System.out.println("iniciou conexao");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("falha conexao");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return con;

	}

	@Override
	public void close(Connection con)  {

	 try {
					 
		 if ( con != null && !con.isClosed() ) {
		  //con.close();
		 }
		 
	  } catch (SQLException e) {
		
		e.printStackTrace();
	  }
		
	}
	

}

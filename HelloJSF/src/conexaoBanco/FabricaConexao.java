package conexaoBanco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface FabricaConexao {

	public void init() throws IOException, ClassNotFoundException;

	public Connection getConnection() throws SQLException, IOException;

	public void close(Connection con) throws SQLException;
	

}

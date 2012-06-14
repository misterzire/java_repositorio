package conexaoBanco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Conexao {
	
	private static String propertiesFile = "C:\\sistemas\\projetos j2ee\\HelloJSF\\src\\projeto.properties";
	private static Properties props = null;
	
	
	private Conexao() {
		
	}
	
	public static FabricaConexao getConexao() {
		
		//load the properties
	
		if(props == null){		
			
			props = new Properties();
	
		}	
			
		try {
			props.load(new FileInputStream(propertiesFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get the properties
		String bancoAtual = props.getProperty("BANCO_ATUAL");
	
		if (bancoAtual.equals("FIREBIRD")){
		
			return new ConexaoFirebird(props);
		
		}else if (bancoAtual.equals("ORACLE")){
		
			return new ConexaoOracle(props);
		
		}
		else{
			
			return new ConexaoMySQL(props);
		}

	}


}

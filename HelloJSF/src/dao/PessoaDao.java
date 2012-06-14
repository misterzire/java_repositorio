package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PessoaBean;
import conexaoBanco.*;

public class PessoaDao {

	private static FabricaConexao banco = Conexao.getConexao();
			
			
	private static PessoaDao instancia = new PessoaDao();

	private PessoaDao() {
	}

	
	public static PessoaDao getInstance() throws IOException,
			ClassNotFoundException {

		System.out.println("retornado instancia de pessoadao");

		return instancia;
	}

	public String getCurrentTime() throws SQLException, IOException {
		
		String dateTime = null;
		
		Connection con = banco.getConnection();
							
		PreparedStatement pstmt = con.prepareStatement("select SYSDATE from dual");
		
		ResultSet rst = pstmt.executeQuery(); 
		
		while (rst.next()) {
			dateTime = rst.getString(1);
		}
		
		rst.close();
		
		banco.close(con);
		
		return dateTime;

	}

	public void insertPessoaDao(PessoaBean pessoa) throws IOException,
			ClassNotFoundException, SQLException {

		try {
			System.out.println("PessoaDao.insertPessoaDao : inicio ");

			Connection con = banco.getConnection();
			
			String insert = "INSERT INTO txt_pessoal (codigo,nome,telefone) VALUES (?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(insert);

			System.out.println("Codigo " + pessoa.getCodigo());
			System.out.println("Nome " + pessoa.getNome());
			System.out.println("Telefone " + pessoa.getTelefone());

			pstmt.setInt(1, pessoa.getCodigo());
			pstmt.setString(2, pessoa.getNome());
			pstmt.setString(3, pessoa.getTelefone());

			System.out.println("vai executar");

			pstmt.executeUpdate();
			
			banco.close(con);
			
			System.out.println("comitou");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}
	
	public void updatePessoaDao(PessoaBean pessoa) throws IOException,ClassNotFoundException, SQLException {

		try {
			
			System.out.println("PessoaDao.updatePessoaDao : inicio ");

			String update = "UPDATE txt_pessoal set nome = ?,telefone = ? where codigo = ?";

			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(update);

			System.out.println("Codigo " + pessoa.getCodigo());
			System.out.println("Nome " + pessoa.getNome());
			System.out.println("Telefone " + pessoa.getTelefone());

		
			pstmt.setString(1, pessoa.getNome());
			pstmt.setString(2, pessoa.getTelefone());
			pstmt.setInt(3, pessoa.getCodigo());
	
			System.out.println("vai executar");

			pstmt.executeUpdate();

			banco.close(con);
			
			System.out.println("comitou");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}

	
	public void deletePessoaDao(PessoaBean pessoa) throws IOException,ClassNotFoundException, SQLException {

		try {
			
			System.out.println("PessoaDao.deletePessoaDao : inicio ");

			String delete = "delete from txt_pessoal where codigo = ?";

			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(delete);

			System.out.println("Codigo " + pessoa.getCodigo());
			System.out.println("Nome " + pessoa.getNome());
			System.out.println("Telefone " + pessoa.getTelefone());
		
			pstmt.setInt(1, pessoa.getCodigo());
	
			System.out.println("vai executar");

			pstmt.executeUpdate();

			banco.close(con);
			
			System.out.println("comitou");			

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

	}
	
	public boolean getCodigoDao(PessoaBean pessoa) throws IOException,
			ClassNotFoundException, SQLException {

		int codigo = 0;

		try {

			System.out.println("PessoaDao.getCodigoDao");

			String selectCodigoPessoa = "SELECT CODIGO FROM txt_pessoal WHERE codigo = "+pessoa.getCodigo();
			
			Connection con = banco.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(selectCodigoPessoa);
						
			ResultSet rs = pstmt.executeQuery();
						
			while (rs.next()) {
				codigo = rs.getInt("CODIGO");
			}

			// close the resultset
			rs.close();
			
			banco.close(con);

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println(sqlex.toString());
		}

		if (codigo > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<PessoaBean> getList(){
		
		System.out.println("PessoaDao.getList");
		
		String selectPessoalAll= "SELECT CODIGO,NOME,TELEFONE FROM txt_pessoal order by codigo";
	
		Connection con = null;
		
		try {
			con = banco.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement pstmt;
		
		List<PessoaBean> items = null;
		
		try {
						
			pstmt = con.prepareStatement(selectPessoalAll);
			
			ResultSet rs = pstmt.executeQuery();
			
			items = new ArrayList<PessoaBean>();
			
			while (rs.next()) {
			    
				PessoaBean pessoa = new PessoaBean();
			    
			    pessoa.setCodigo(rs.getInt(1));
			    pessoa.setNome(rs.getString(2));
			    pessoa.setTelefone(rs.getString(3));
			    		    
			    items.add(pessoa);
			}
						
			banco.close(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return items;		
	}
	
	
	public List<PessoaBean> getSearchPessoas(PessoaBean pessoaBean) {
		
		 System.out.println("PessoaDao.getSearchPessoas");
		
		 Connection con = null;
		try {
			con = banco.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		 PreparedStatement pstmt;
		 
		 boolean flagPesquisa = false; 
		 
		 String selectPessoalFind= "SELECT CODIGO,NOME,TELEFONE FROM txt_pessoal ";
		
		 if (pessoaBean.getCodigo() != 0 ){
			 
			 selectPessoalFind = selectPessoalFind + " where codigo = ? order by codigo";
			 
			 flagPesquisa = true;
			 
		 } else if (!pessoaBean.getNome().isEmpty() ){
			 
			 selectPessoalFind = selectPessoalFind + " where nome = ? order by codigo";
			 
			 flagPesquisa = true;
			 
		 } else if (!pessoaBean.getTelefone().isEmpty() ){
			 
			 selectPessoalFind = selectPessoalFind + " where telefone = ? order by codigo";
			 
			 flagPesquisa = true;
			 
		 }	   
			
		 List<PessoaBean> items = null;
		
		 if (flagPesquisa == true){
		
		 	 
			 try {

				 pstmt = con.prepareStatement(selectPessoalFind);

				 if (pessoaBean.getCodigo() != 0 ){

					 pstmt.setInt(1, pessoaBean.getCodigo());


				 } else if (!pessoaBean.getNome().isEmpty() ){
					 pstmt.setString(1, pessoaBean.getNome());


				 } else if (!pessoaBean.getTelefone().isEmpty() ){

					 pstmt.setString(1, pessoaBean.getTelefone());

				 }	   			

				 ResultSet rs = pstmt.executeQuery();

				 items = new ArrayList<PessoaBean>();

				 while (rs.next()) {

					 PessoaBean pessoa = new PessoaBean();

					 pessoa.setCodigo(rs.getInt(1));
					 pessoa.setNome(rs.getString(2));
					 pessoa.setTelefone(rs.getString(3));

					 items.add(pessoa);
				 }


				 banco.close(con);

			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }

		 }
		 
			return items;		
		
		}

	
		
	}

package bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import dao.PessoaDao;

@ManagedBean
@SessionScoped
public class PessoaBean {

	private String nome;

	private String telefone;

	private int codigo;
	
	private FacesContext context;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String executaInclusao() throws IOException,ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		executaValidacoesGenericas();

		if (obterCodigoValido() == true) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Codigo já cadastrado.",""));
		}

		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		} 
		
		String retorno = incluir();
		
		if (retorno == "OK"){
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Inclusão efetuada com sucesso.",""));
			
		}
		else{
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
			
		}
		
		return ("page-list");
				
	}
	
	public String executaAlteracao() throws IOException,	ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		executaValidacoesGenericas();
					
		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		}
		
		
		String retorno = alterar();

		if (retorno == "OK"){
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Alteração efetuada com sucesso.",""));
				
		}
		else{
				
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
				
		}

		return ("page-list");
		
	}

	public String executaExclusao() throws IOException,	ClassNotFoundException, SQLException {

		context = FacesContext.getCurrentInstance();

		if (getNome().equals("")) {
			context.addMessage(null, new FacesMessage(
			"Campo nome deve ser preenchido"));
		}

				
		if (!obterCodigoValido() ) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Codigo não esta cadastrado.",""));
		}

		if (context.getMessageList().size() > 0) {

			return (null); // Returning null means to redisplay the form

		} 
		
		String retorno = excluir();

		if (retorno == "OK"){
			
		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exclusão efetuada com sucesso.",""));
				
		}
		else{
				
		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retorno,""));
				
		}

		return ("page-list");
		
	}

	public List <PessoaBean> getListPessoas() throws IOException, ClassNotFoundException,SQLException {
		
		PessoaDao pessoa = PessoaDao.getInstance();

		return pessoa.getList();
		
		
	}
	
	public List <PessoaBean> getSearchPessoas() throws IOException, ClassNotFoundException,SQLException {
		
		PessoaDao pessoa = PessoaDao.getInstance();

		return pessoa.getSearchPessoas(this);
				
	}
	
	private String incluir()  {
						
		PessoaDao pessoa;
		
		try {
			
			pessoa = PessoaDao.getInstance();
			
			pessoa.insertPessoaDao(this);
						
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());			
					
		} catch (SQLException e) {
			e.printStackTrace();			
			return (e.getMessage());
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return (e.getMessage());
		}	

		return "OK";
		
	}
	
	private String alterar(){

		PessoaDao pessoa;

		try {
			
			pessoa = PessoaDao.getInstance();
			
			pessoa.updatePessoaDao(this);
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		}
				
		return "OK";

	} 

	private String excluir() {

		PessoaDao pessoa;

		try {
			
			pessoa = PessoaDao.getInstance();
			
			pessoa.deletePessoaDao(this);
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return (e.getMessage());	
			
		}
		
		return "OK";
		
	} 
	
	private boolean obterCodigoValido() throws IOException,ClassNotFoundException, SQLException {

		PessoaDao pessoa = PessoaDao.getInstance();

		return pessoa.getCodigoDao(this);

	}
	
	private void executaValidacoesGenericas(){
		
		
		if (getNome().equals("")) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Campo nome deve ser preenchido",""));
		}

		if (getTelefone().equals("")) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Campo telefone deve ser preenchido",""));
		}
	
		
	}
	
}

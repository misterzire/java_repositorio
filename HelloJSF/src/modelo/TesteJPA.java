package modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteJPA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// exemplo de inclusão
		
		PessoaJPA pessoa = new PessoaJPA();
		
		pessoa.setCodigo(111);
		pessoa.setNome("Teste JPA");
		pessoa.setTelefone("1234-4321");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJSF");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(pessoa);
		
		em.getTransaction().commit();
		
		em.close();
		
		emf.close();
		
		
		
		
		
		
		
		
		
	}

}

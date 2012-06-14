package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the txt_pessoal database table.
 * 
 */
@Entity
@Table(name="txt_pessoal")
public class PessoaJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String nome;

	private String telefone;

    public PessoaJPA() {
    }

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
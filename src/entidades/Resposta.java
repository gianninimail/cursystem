package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import util.ModeloBase;

@Entity
@Table(name = "answer")
public class Resposta extends ModeloBase {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen_resposta", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "gen_resposta", sequenceName = "seq_resposta", schema = "ccbh4851system")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String titulo;
	
	@Column(name = "description")
	private String desc;
	
	@Column(name = "validations")
	private int validacoes;
	
	@Column(name = "status")
	private Boolean status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenge_id")
	private Desafio desafio;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "researcher_id")
	Pesquisador pesquisador;
	
	@Column(name = "date_register")
	Date dtCadastro;
	
	@Column(name = "new_equation")
	private String novaEquacao;
	
	
	public Resposta() {
		super();
		this.id = new Long(0);
	}

	public Resposta(Long id, String desc, int validacoes, Boolean status, Desafio desafio, Pesquisador pesquisador, Date dt_cadastro, String _novaEquacao, String _titulo) {
		super();
		this.id = id;
		this.desc = desc;
		this.validacoes = validacoes;
		this.status = status;
		this.desafio = desafio;
		this.pesquisador = pesquisador;
		this.dtCadastro = dt_cadastro;
		this.novaEquacao = _novaEquacao;
		this.titulo = _titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getValidacoes() {
		return validacoes;
	}

	public void setValidacoes(int validacoes) {
		this.validacoes = validacoes;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}
		
	public Date getDataCadastro() {
		return dtCadastro;
	}

	public void setDataCadastro(Date dt_cadastro) {
		this.dtCadastro = dt_cadastro;
	}
	
	public String getNovaEquacao() {
		return novaEquacao;
	}

	public void setNovaEquacao(String novaEquacao) {
		this.novaEquacao = novaEquacao;
	}
	
	public Date getDt_cadastro() {
		return dtCadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dtCadastro = dt_cadastro;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (getClass() != obj.getClass())
			return false;
		
		Resposta other = (Resposta) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Resposta [id=" + id + ", desc=" + desc + ", validacoes=" + validacoes + ", status=" + status
				+ ", desafio="+ desafio + ", pesquisador=" + pesquisador + "]";
	}
	
}

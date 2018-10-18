package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import util.ModeloBase;

@Entity
@Table(name = "status")
public class Status extends ModeloBase {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen_status", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "gen_status", sequenceName = "seq_status", schema = "ccbh4851system")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	String nome;
	
	public Status() {
		
	}
	
	public Status(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}

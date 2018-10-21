package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import util.EntidadeBase;

@Entity
@Table(name = "speciality")
public class Especialidade implements Serializable, EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen_especialidade", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "gen_especialidade", sequenceName = "seq_especialidade", schema = "ccbh4851system")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String nome;
	
	public Especialidade() {
		this.id = new Long(0);
	}
	
	public Especialidade(Long id, String nome) {
		
		this.id = id;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		if (id != null) {
			this.id = id;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Especialidade [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Especialidade other = (Especialidade) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
      
        return true;
	}
	
	
}

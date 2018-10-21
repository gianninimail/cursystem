package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "researcher")
@PrimaryKeyJoinColumn(name = "id")
public class Pesquisador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "institute")
	private String instituto;
	
	@OneToOne
	@JoinColumn(name = "specialty_id")
	private Especialidade especialidade;

	public Pesquisador() {
		super();
		this.especialidade = new Especialidade();
	}
	/*
	public Pesquisador(Long id, String nome, String login, String senha, String tel, String email, Date dtCadastro, String instituto, Especialidade especialidade, int tipoUsuario) {
		super(id, nome, login, senha, tel, email, dtCadastro, tipoUsuario);
		this.instituto = instituto;
		this.especialidade = especialidade;
		//this.idEspecialidade = idEspecialidade;
	}
	
	public Pesquisador(Usuario _usuario) {
		super(_usuario.id, _usuario.nome, _usuario.login, _usuario.senha, _usuario.tel, _usuario.email, _usuario.dataCadastro, _usuario.tipoUsuario);
	}
	*/
	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		if (!instituto.equals("")) {
			this.instituto = instituto;
		}	
	}

//	public int getIdEspecialidade() {
//		return this.idEspecialidade;
//	}
//
//	public void setIdEspecialidade(int idEspecialidade) {
//		if (idEspecialidade != 0) {
//			this.idEspecialidade = idEspecialidade;
//		}
//	}
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public void setUsuario(Usuario _usuario) {
		this.id = _usuario.id;
		this.nome = _usuario.nome;
		this.login = _usuario.login;
		this.senha = _usuario.senha;
		this.tel = _usuario.tel;
		this.email = _usuario.email;
		this.dataCadastro = _usuario.dataCadastro;
		this.tipoUsuario = _usuario.tipoUsuario;
	}

	@Override
	public String toString() {
		return "Pesquisador [instituto=" + instituto + ", especialidade=" + especialidade + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
            return false;
        }
		if(super.equals(obj) == false) {
			return false;
		}
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Pesquisador other = (Pesquisador) obj;
        
        if (this.especialidade != other.especialidade && (this.especialidade == null || !this.especialidade.equals(other.especialidade))) {
            return false;
        }
        if (this.instituto != other.instituto && (this.instituto == null || !this.instituto.equals(other.instituto))) {
            return false;
        }
        return true;
		
	}

}

package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import util.ModeloBase;

@Entity
@Table(name = "challenge")
public class Desafio extends ModeloBase {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen_desafio", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "gen_desafio", sequenceName = "seq_desafio", schema = "ccbh4851system")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String titulo;
	
	@Column(name = "description")
	private String desc;
	
	@Column(name = "status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "specialty_id")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name = "reaction_id")
	private Reacao reacao;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuario usuario;
	
	@Column(name = "answer_accepted")
	private Long idRespostaAceita;
	
	@Column(name = "dt_register")
	private Date dt_cadastro;
	
	@OneToMany(mappedBy = "desafio", fetch = FetchType.LAZY)
	private List<Resposta> respostas;
	
	@ManyToOne
	@JoinColumn(name = "reconstruction_id")
	private Reconstrucao recontrucao;
	
	public Desafio() {
		this.id = new Long(0);
		this.respostas = new ArrayList<Resposta>();
		this.especialidade = new Especialidade();
		this.usuario = new Usuario();
		this.reacao = new Reacao();
		this.status = new Status();
	}
	
	public Desafio(Long id, String titulo, String desc, Status status, Especialidade especialidade, Reacao reacao, Usuario usuario, Long idRespostaAceita, Date dt_cadastro, List<Resposta> respostas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.desc = desc;
		this.status = status;	
		this.especialidade = especialidade;
		this.reacao = reacao;
		this.usuario = usuario;
		this.idRespostaAceita = idRespostaAceita;
		this.dt_cadastro = dt_cadastro;
		this.respostas = respostas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
		
	public Date getDataCadastro() {
		return dt_cadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Reacao getReacao() {
		return reacao;
	}

	public void setReacao(Reacao reacao) {
		this.reacao = reacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdRespostaAceita() {
		return idRespostaAceita;
	}

	public void setIdRespostaAceita(Long idRespostaAceita) {
		this.idRespostaAceita = idRespostaAceita;
	}

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public void setDataCadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
	
	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "Desafio [id=" + id + ", titulo=" + titulo + ", desc=" + desc + ", status=" + status + ", especialidade="
				+ especialidade + ", reacao=" + reacao + ", usuario=" + usuario + ", id_resposta_aceita="
				+ idRespostaAceita + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Desafio other = (Desafio) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        
        return true;
	}
}

package entidades;

import java.io.Serializable;
import javax.persistence.*;

import util.ModeloBase;

import java.util.Date;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comentario c")
public class Comentario extends ModeloBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne @JoinColumn(name="answer_id")
	private Resposta resposta;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_register")
	private Date dataRegistro;

	@ManyToOne @JoinColumn(name="researcher_id")
	private Pesquisador pesquisador;

	@Lob
	private String text;

	public Comentario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
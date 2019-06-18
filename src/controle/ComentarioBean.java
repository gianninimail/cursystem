package controle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.TreeNode;

import dados.ComentarioDAO;
import entidades.Resposta;
import entidades.Comentario;
import util.JSFUtil;

@ManagedBean
public class ComentarioBean {
	private Long idResposta;
	private List<Comentario> listaComentarios;
	private TreeNode noSelecionado;
	private Comentario comentario;
	private boolean respostaSelecionado = false;
	
	
	public ComentarioBean() {
		this.listaComentarios = new ArrayList<Comentario>();
	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public List<Comentario> getListaComentarios() {
		CarregarComentarioDeResposta();
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public TreeNode getNoSelecionado() {
		return noSelecionado;
	}

	public void setNoSelecionado(TreeNode noSelecionado) {
		this.noSelecionado = noSelecionado;
		try {
			
			if (this.noSelecionado != null) 
			{	
				if (this.noSelecionado.getData() instanceof Resposta) 
				{	
					this.respostaSelecionado = true;
				} 
				else 
				{
					this.respostaSelecionado = false;
				}
			}
		} 
		catch (Exception e) {
			this.respostaSelecionado = false;
			e.printStackTrace();
		}
	}
	
	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	
	public boolean isRespostaSelecionado() {
		return respostaSelecionado;
	}

	public void setRespostaSelecionado(boolean respostaSelecionado) {
		this.respostaSelecionado = respostaSelecionado;
	}

	public void PrepararNovo() {
		this.comentario = new Comentario();
		
		try {
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			//ComentarioDAO dao = new ComentarioDAO();
			
			//this.comentario.setId(dao.PegarID());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void CadastrarComentario() {
		try {
			
			this.comentario.setDataRegistro(new Date());
			//this.comentario.setPesquisador(new Pesquisador());
			this.comentario.setResposta((Resposta)this.noSelecionado.getData());
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			ComentarioDAO dao = new ComentarioDAO();
			dao.Inserir(this.comentario);

			//this.listaComentarios = dao.listarTodos();
			CarregarComentarioDeResposta();

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Coment�rio cadastrado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	private void CarregarComentarioDeResposta() {
		
		try {
			if(this.noSelecionado != null) {
				if (this.noSelecionado.getData() instanceof Resposta) {
					//FabricaConexao fabrica = new FabricaConexao();
					//Connection conexao = fabrica.fazerConexao();
			
					Resposta resposta = (Resposta)noSelecionado.getData();
					ComentarioDAO dao = new ComentarioDAO();
					
					this.listaComentarios = dao.TodasComentariosDaResposta(resposta);
					
					//fabrica.fecharConexao();
				}
				else {
					this.listaComentarios = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void PreparaPesquisa() {
		try {
			
			
//			FabricaConexao fabrica = new FabricaConexao();
//			Connection conexao = fabrica.fazerConexao();
//
//			Resposta resposta = (Resposta)noSelecionado.getData();
//			ComentarioDAO dao = new ComentarioDAO(conexao);
//			
//			this.listaComentarios = dao.TodasComentariosDaResposta(resposta);
//
//			fabrica.fecharConexao();

			//this.respostas = new ListDataModel<Resposta>(this.listaRespostas);
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}

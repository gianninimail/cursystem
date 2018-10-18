package controle;

//import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;

import org.primefaces.model.TreeNode;

//import dao.RespostaDAO;
import entidades.Resposta;
//import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
public class AnaliseRespostaBean {

	private Resposta resposta;
	private List<Resposta> listaRespostas;
	private ListDataModel<Resposta> respostas;
	private TreeNode raiz;
	private TreeNode noSelecionado;
	
	@ManagedProperty("#{servicoRespostas}")
	private ServicoRespostas servico;
	
	public AnaliseRespostaBean() {
		this.resposta = new Resposta();
		this.listaRespostas = new ArrayList<Resposta>();
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public List<Resposta> getListaRespostas() {
		return listaRespostas;
	}

	public void setListaRespostas(List<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}
	
	public ListDataModel<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(ListDataModel<Resposta> respostas) {
		this.respostas = respostas;
	}

	public ServicoRespostas getServico() {
		return servico;
	}

	public void setServico(ServicoRespostas servico) {
		this.servico = servico;
	}

	public TreeNode getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	public TreeNode getNoSelecionado() {
		return noSelecionado;
	}

	public void setNoSelecionado(TreeNode noSelecionado) {
		this.noSelecionado = noSelecionado;
	}

	@PostConstruct
	public void PreparaPesquisa() {
		try {
			servico = new ServicoRespostas();
			this.raiz = servico.criarArvoreRespostas();
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}
 
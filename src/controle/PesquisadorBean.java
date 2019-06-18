package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;

import org.primefaces.event.TabChangeEvent;
//import org.primefaces.event.TabCloseEvent;

import dados.PesquisadorDAO;
import entidades.Pesquisador;
import entidades.Usuario;
//import util.FabricaConexao;
import util.JSFUtil;
import util.TipoUsuario;

@ManagedBean
public class PesquisadorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Pesquisador pesquisador;
	private Usuario usuario;
	private String msgOps;
	private List<Pesquisador> listaPesquisadores;
	private List<Usuario> listaUsuariosParaPesquisadores;
	private Boolean ehNovoPesquisador = true;
	private ListDataModel<Pesquisador> pesquisadores;

	public PesquisadorBean() {
		this.pesquisador = new Pesquisador();
	}

	public Pesquisador getPesquisador() {
		return this.pesquisador;
	}

	public void setPesquisador(Pesquisador _pesquisador) {
		this.pesquisador = _pesquisador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMsgOps() {
		return msgOps;
	}

	public void setMsgOps(String msgOps) {
		this.msgOps = msgOps;
	}

	public Boolean getEhNovoPesquisador() {
		return ehNovoPesquisador;
	}

	public void setEhNovoPesquisador(Boolean ehNovoPesquisador) {
		this.ehNovoPesquisador = ehNovoPesquisador;
	}

	public ListDataModel<Pesquisador> getPesquisadores() {
		return pesquisadores;
	}

	public void setPesquisadores(ListDataModel<Pesquisador> pesquisadores) {
		this.pesquisadores = pesquisadores;
	}

	public List<Pesquisador> getListaPesquisadores() {
		return listaPesquisadores;
	}

	public void setListaPesquisadores(List<Pesquisador> listaPesquisadores) {
		this.listaPesquisadores = listaPesquisadores;
	}

	public List<Usuario> getListaUsuariosParaPesquisadores() {
		return listaUsuariosParaPesquisadores;
	}

	public void setListaUsuariosParaPesquisadores(List<Usuario> listaUsuariosParaPesquisadores) {
		this.listaUsuariosParaPesquisadores = listaUsuariosParaPesquisadores;
	}

	// M�TODOS DAO - ACESSO AO BANCO DE DADOS
	public void CadastrarPesquisador() {

		try {

			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			PesquisadorDAO dao = new PesquisadorDAO();

			if (this.ehNovoPesquisador) {

				this.pesquisador.setDataCadastro(new Date());
				this.pesquisador.setTipoUsuario(TipoUsuario.PESQUISADOR);
				//dao.InserirNovo(this.pesquisador);
			}
			if (!this.ehNovoPesquisador) {

				this.pesquisador.setUsuario(this.usuario);
				//dao.InserirExiste(this.pesquisador);
			}

			this.listaPesquisadores = dao.listarTodos();

			this.pesquisadores = new ListDataModel<Pesquisador>(listaPesquisadores);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Pesquisador cadastrado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void PrepararNovo() {
		this.pesquisador = new Pesquisador();

		try {

			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			//PesquisadorDAO dao = new PesquisadorDAO();

			//this.pesquisador.setId(dao.PegarProximoID());
			
			AtualizarListaUsuarioParaPesquisadores();

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void ExcluirPesquisador() {
		try {

			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			PesquisadorDAO dao = new PesquisadorDAO();
			dao.Excluir(this.pesquisador);

			this.listaPesquisadores = dao.listarTodos();

			this.pesquisadores = new ListDataModel<Pesquisador>(listaPesquisadores);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Pesquisador exclu�do com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void PrepararExcluir() {
		this.pesquisador = pesquisadores.getRowData();
	}

	public void EditarPesquisador() {
		try {

			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			PesquisadorDAO dao = new PesquisadorDAO();
			dao.Editar(this.pesquisador);

			this.listaPesquisadores = dao.listarTodos();

			this.pesquisadores = new ListDataModel<Pesquisador>(listaPesquisadores);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Usu�rio editado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void PrepararEditar() {
		this.pesquisador = pesquisadores.getRowData();
	}

	private void AtualizarListaUsuarioParaPesquisadores() {

		try {

			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			PesquisadorDAO dao = new PesquisadorDAO();

			this.listaUsuariosParaPesquisadores = dao.listarUsuariosParaPesquisador();

			//fabrica.fecharConexao();

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	// FIM DOS METODOS DAO - ACESSO AO BANCO DE DADOS

	// METODOS PARA MANIPULACAO DA PAGINA - FRONT END
	@PostConstruct
	public void PreparaPesquisa() {
		try {
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			PesquisadorDAO dao = new PesquisadorDAO();
			this.listaPesquisadores = dao.listarTodos();

			//fabrica.fecharConexao();

			pesquisadores = new ListDataModel<Pesquisador>(listaPesquisadores);
			
			AtualizarListaUsuarioParaPesquisadores();

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void onTabChange(TabChangeEvent event) {
		String nomeAba = event.getTab().getId().toString();
		System.out.println(nomeAba);

		if (nomeAba.equals("abaNovo")) {
			this.ehNovoPesquisador = true;
			// this.abaExistenteHabilitado = false;
		}
		if (nomeAba.equals("abaExistente")) {
			this.ehNovoPesquisador = false;
			// this.abaExistenteHabilitado = true;
		}
	}

	// public void onTabClose(TabCloseEvent event) {
	// String nomeAba = event.getTab().getTitle();
	// System.out.println(nomeAba);
	// }

	// FIM DOS M�TODOS PARA MANIPULA��O DA P�GINA - FRONT END
}

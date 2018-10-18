package controle;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;

import dados.UsuarioDAO;
import entidades.Usuario;
import util.BeanBase;
//import util.FabricaConexao;
import util.JSFUtil;

//@Scope(value = WebApplicationContext.SCOPE_SESSION)
//@Named("usuarioBean")
@ManagedBean
public class UsuarioBean extends BeanBase {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private String msgOps;
	private List<Usuario> listaUsuarios;
	
	private ListDataModel<Usuario> usuarios;

	public UsuarioBean() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario _usuario) {
		this.usuario = _usuario;
	}

	public String getMsgOps() {
		return msgOps;
	}

	public void setMsgOps(String msgOps) {
		this.msgOps = msgOps;
	}
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ListDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void CadastrarUsuario() {
		try {
			
			this.usuario.setDataCadastro(new Date());
			this.usuario.setTipoUsuario(1);
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			UsuarioDAO dao = new UsuarioDAO();
			dao.Inserir(this.usuario);

			this.listaUsuarios = dao.listarTodos();

			this.usuarios = new ListDataModel<Usuario>(listaUsuarios);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Usuario cadastrado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararNovo() {
		this.usuario = new Usuario();
		
		try {
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			//UsuarioDAO dao = new UsuarioDAO();
			
			//this.usuario.setId(dao.PegarProximoID());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void ExcluirUsuario() {
		try {
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			UsuarioDAO dao = new UsuarioDAO();
			dao.Excluir(this.usuario);

			this.listaUsuarios = dao.listarTodos();

			this.usuarios = new ListDataModel<Usuario>(listaUsuarios);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Usuario exclu�do com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararExcluir() 
	{
		this.usuario = usuarios.getRowData();
	}
	
	public void EditarUsuario() {
		try {
			
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			UsuarioDAO dao = new UsuarioDAO();
			dao.Editar(this.usuario);

			this.listaUsuarios = dao.listarTodos();

			this.usuarios = new ListDataModel<Usuario>(listaUsuarios);

			//fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Usu�rio editado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararEditar() 
	{
		this.usuario = usuarios.getRowData();
	}
	
	
	
	@PostConstruct
	public void PreparaPesquisa() {
		try {
			//FabricaConexao fabrica = new FabricaConexao();
			//Connection conexao = fabrica.fazerConexao();

			UsuarioDAO dao = new UsuarioDAO();
			this.listaUsuarios = dao.listarTodos();

			//fabrica.fecharConexao();
			
			usuarios = new ListDataModel<Usuario>(listaUsuarios);
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}

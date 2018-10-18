package controle;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;

import org.apache.commons.mail.EmailException;
import org.primefaces.event.FlowEvent;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dao.DesafioDAO;
import dao.FuncaoSubsistemaDAO;
import dao.ReacaoDAO;
import dao.RespostaDAO;
import modelo.Desafio;
import modelo.Pesquisador;
import modelo.Reacao;
import modelo.Resposta;
import modelo.Status;
import modelo.Subsistema;
import util.EmailUtil;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
public class DesafioBean {

	Desafio desafio;
	private String msgOps;
	
	private boolean skip;
	
	private List<Desafio> listaDesafios;
	private ListDataModel<Desafio> desafios;
	
	private ListDataModel<Resposta> respostas;
	
	private Resposta respostaSelecionada;
	
	private Subsistema subsistema;
	
	private Resposta novaResposta;
	
	private boolean desafioSelecionado = false;
	
	private List<Subsistema> listaSubsistemas;
	//private ListDataModel<Subsistema> subsistemas;
	
	private List<Reacao> listaReacoes;
	//private ListDataModel<Reacao> reacoes;
	
	public DesafioBean() {
		this.desafio = new Desafio();
		this.desafio.setTitulo("Nenhum desafio selecionado");
		this.subsistema = new Subsistema();
		//this.novaResposta = new Resposta();
	}
	
	public Desafio getDesafio() {
		return desafio;
	}
	
	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
		//this.respostas = new ListDataModel<Resposta>(this.desafio.getRespostas());
		this.desafioSelecionado = true;
	}
	
	public Subsistema getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(Subsistema subsistema) {
		this.subsistema = subsistema;
	}
	
	public boolean isDesafioSelecionado() {
		return desafioSelecionado;
	}

	public String getMsgOps() {
		return msgOps;
	}
	
	public void setMsgOps(String msgOps) {
		this.msgOps = msgOps;
	}
	
	public ListDataModel<Desafio> getDesafios() {
		return desafios;
	}
	
	public void setDesafios(ListDataModel<Desafio> desafios) {
		this.desafios = desafios;
	}
	
	public ListDataModel<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(ListDataModel<Resposta> respostas) {
		this.respostas = respostas;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	public Resposta getRespostaSelecionada() {
		return respostaSelecionada;
	}

	public Resposta getNovaResposta() {
		return novaResposta;
	}

	public void setNovaResposta(Resposta novaResposta) {
		this.novaResposta = novaResposta;
	}

	public void setRespostaSelecionada(Resposta respostaSelecionada) {
		this.respostaSelecionada = respostaSelecionada;
	}
	
	public List<Desafio> getListaDesafios() {
		return listaDesafios;
	}

	public void setListaDesafios(List<Desafio> listaDesafios) {
		this.listaDesafios = listaDesafios;
	}
	
	public List<Reacao> getListaReacoes() {
		return listaReacoes;
	}
	
	public List<Subsistema> getListaSubsistemas() {
		return listaSubsistemas;
	}

	public void setListaSubsistemas(List<Subsistema> listaSubsistemas) {
		this.listaSubsistemas = listaSubsistemas;
	}

	public void setListaReacoes(List<Reacao> listaReactions) {
		this.listaReacoes = listaReactions;
	}

	//ACESSO AO BANCO DE DADOS
	public void CadastrarDesafio() {
		try {
			this.desafio.setDataCadastro(new Date());

			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			DesafioDAO dao = new DesafioDAO(conexao);
			dao.Inserir(this.desafio);
			
			EnviarEmail();

			this.listaDesafios = dao.listarTodos();

			this.desafios = new ListDataModel<Desafio>(listaDesafios);

			fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Desafio cadastrado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararNovo() {
		this.desafio = new Desafio();
		
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			DesafioDAO dao = new DesafioDAO(conexao);
			
			this.desafio.setId(dao.PegarID());
			
			modelo.Status status = new Status(2, "ABERTO");
			
			this.desafio.setStatus(status);
			
			CarregarReacoes();
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void ExcluirDesafio() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			DesafioDAO dao = new DesafioDAO(conexao);
			dao.Excluir(this.desafio);

			this.listaDesafios = dao.listarTodos();

			this.desafios = new ListDataModel<Desafio>(listaDesafios);

			fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Desafio excluído com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararExcluir() 
	{
		this.desafio = desafios.getRowData();
	}
	
	public void EditarDesafio() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			DesafioDAO dao = new DesafioDAO(conexao);
			dao.Atualizar(this.desafio);

			this.listaDesafios = dao.listarTodos();

			this.desafios = new ListDataModel<Desafio>(listaDesafios);

			fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Desafio editado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void PrepararEditar() 
	{
		CarregarReacoes();
		
		this.desafio = desafios.getRowData();
	}
	
	public void PrepararVerInformcoes() 
	{
		this.desafio = desafios.getRowData();
	}
	
	public void PesquisarReacoes() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ReacaoDAO dao = new ReacaoDAO(conexao);
			
			String nomeSubSistema = dao.BuscarNomeSubSistema(this.subsistema.getId());
			
			this.listaReacoes = dao.listarTodosPorSistemaAndSubSistema(null, nomeSubSistema);
			
			//this.reacoes = new ListDataModel<>(this.listaReacoes);
	
			fabrica.fecharConexao();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void CarregarReacoes() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncaoSubsistemaDAO daoFuncSubsistema = new FuncaoSubsistemaDAO(conexao);
			this.listaSubsistemas = daoFuncSubsistema.listarTodos();
			//this.setSubsistemas(new ListDataModel<>(listaSubsistemas));
			
			ReacaoDAO daoReacao = new ReacaoDAO(conexao);
			this.listaReacoes = daoReacao.listarTodos();
			//this.reacoes = new ListDataModel<>(this.listaReacoes);
	
			fabrica.fecharConexao();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void PrepararNovaResposta() {
		
		this.novaResposta = new Resposta();
		
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			RespostaDAO dao = new RespostaDAO(conexao);
			
			this.novaResposta.setId(dao.PegarID());
			this.novaResposta.setDataCadastro(new Date());
			this.novaResposta.setStatus(false);
			this.novaResposta.setValidacoes(0);
			this.novaResposta.setDesafio(this.desafio.getId());
			Pesquisador pesquisador = new Pesquisador(desafio.getUsuario());
			this.novaResposta.setPesquisador(pesquisador);
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void CadastrarResposta() {        
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			RespostaDAO dao = new RespostaDAO(conexao);
			dao.Inserir(this.novaResposta);
			
			//EnviarEmail();

			this.desafio.getRespostas().add(this.novaResposta);		
			this.respostas = new ListDataModel<Resposta>(this.desafio.getRespostas());
			
			DesafioDAO daoDesafio = new DesafioDAO(conexao);
			this.listaDesafios = daoDesafio.listarTodos();

			fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Resposta cadastrada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
    }
  	
	public void PrepararExcluirResposta() {
		try {
			this.respostaSelecionada = respostas.getRowData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ExcluirResposta() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			RespostaDAO dao = new RespostaDAO(conexao);
			dao.Excluir(this.respostaSelecionada);
			
			this.desafio.getRespostas().remove(this.respostaSelecionada);		
			this.respostas = new ListDataModel<Resposta>(this.desafio.getRespostas());
			
			DesafioDAO daoDesafio = new DesafioDAO(conexao);
			this.listaDesafios = daoDesafio.listarTodos();

			fabrica.fecharConexao();

			JSFUtil.adicionarMensagemSucesso("Resposta excluída com sucesso!");
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Verifique se não existe comentários cadastrados para esse resposta");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void CarregarRespostasDoDesafioSelecionado() {
		try {
						
			this.respostas = new ListDataModel<Resposta>(this.desafio.getRespostas());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String onProcessoFluxo(FlowEvent event) {
        
		if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
	//FIM DO ACESSO AO BANCO DE DADOS
	
	public void CopiarEquacaoExistenteParaNovaEquacao() {
		try {
			this.novaResposta.setNovaEquacao(this.desafio.getReacao().getEquation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EnviarEmail() {
		
		//util.EmailUtil email = new EmailUtil();
		
		try {
			
			String titulo = "Novo desafio Cadastrado";
			String msg = "Dados do Desafio: \n"
				+"\nTitulo do desafio: " + this.desafio.getTitulo()
				+ "\nDescrição do desafio: " + this.desafio.getDesc()
				+ "\nReação envolvida no desafio: " + this.desafio.getReacao().getAbbreviation() + "  |--|  Eq:" + this.desafio.getReacao().getEquation()
				+ "";
			
			EmailUtil.enviaEmail(titulo, msg, this.desafio.getUsuario().getEmail());
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void PreparaPesquisa() {
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();

			DesafioDAO dao = new DesafioDAO(conexao);
			this.listaDesafios = dao.listarTodos();
			
			//this.respostas = new ListDataModel<Resposta>(this.desafio.getRespostas());

			fabrica.fecharConexao();
			
			desafios = new ListDataModel<Desafio>(listaDesafios);
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

}

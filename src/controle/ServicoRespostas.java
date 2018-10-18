package controle;

import java.sql.Connection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import dao.DesafioDAO;
import dao.PesquisadorDAO;
import dao.RespostaDAO;
import modelo.Desafio;
import modelo.Pesquisador;
import modelo.Resposta;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean(name = "servicoRespostas")
@ViewScoped
public class ServicoRespostas {

	public TreeNode criarArvoreRespostas() {
		
        TreeNode raiz = new DefaultTreeNode();
        
        try {
			
        	FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			
			PesquisadorDAO daoP = new PesquisadorDAO(conexao);
			List<Pesquisador> pesquisadores = daoP.listarTodos();
			
			for (Pesquisador p : pesquisadores) {
				
				TreeNode noPesquisador = new DefaultTreeNode("pesquisador", p, raiz);
				
				DesafioDAO daoD = new DesafioDAO(conexao);
				List<Desafio> desafiosDoEspecialista = daoD.listarTodosPorEspecialista(p.getId());
				
				for (Desafio d : desafiosDoEspecialista) {
					
					TreeNode noDesafio = new DefaultTreeNode("desafio", d, noPesquisador);
					
					RespostaDAO daoR = new RespostaDAO(conexao);
					List<Resposta> respostas = daoR.TodasRespostasDoDesafio(d.getId());
					
					for (Resposta r : respostas) {
						
						@SuppressWarnings("unused")
						TreeNode noResposta = new DefaultTreeNode("resposta", r, noDesafio);
					}
				}
			}

			fabrica.fecharConexao();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
         
        return raiz;
    }
}

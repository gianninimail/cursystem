package dados;

import java.sql.SQLException;
import java.util.List;

import entidades.Pesquisador;

public interface PesquisadorINTdao {

	void Inserir(Pesquisador _pesquisador) throws SQLException;
	
	void Editar(Pesquisador _pesquisador) throws SQLException;

	void Excluir(Pesquisador _pesquisador) throws SQLException;
	
	void ExcluirPorID(Long _id) throws SQLException;

	Pesquisador PegarPeloID(Long _id) throws SQLException;
	
	List<Pesquisador> listarTodos() throws SQLException;

	Pesquisador ExistePesquisador(String _login, String _senha) throws SQLException;
}

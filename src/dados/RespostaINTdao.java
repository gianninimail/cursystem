package dados;

import java.sql.SQLException;
import java.util.List;

import entidades.Resposta;

public interface RespostaINTdao {

	void Inserir(Resposta _resposta) throws SQLException;
	
	void Editar(Resposta _resposta) throws SQLException;

	void Excluir(Resposta _resposta) throws SQLException;
	
	void ExcluirPorID(Long _id) throws SQLException;

	Resposta PegarPeloID(Long _id) throws SQLException;
	
	List<Resposta> listarTodos() throws SQLException;
}

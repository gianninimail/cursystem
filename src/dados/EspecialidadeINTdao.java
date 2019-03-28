package dados;

import java.sql.SQLException;
import java.util.List;

import entidades.Especialidade;

public interface EspecialidadeINTdao {

	void Inserir(Especialidade _especialidade) throws SQLException;
	
	void Editar(Especialidade _especialidade) throws SQLException;

	void Excluir(Especialidade _especialidade) throws SQLException;
	
	void ExcluirPorID(Long _id) throws SQLException;

	Especialidade PegarPeloID(Long _id) throws SQLException;
	
	List<Especialidade> listarTodos() throws SQLException;
}

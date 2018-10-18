package dados;

import java.sql.SQLException;
import java.util.List;

import entidades.Comentario;

public interface ComentarioINTdao {

void Inserir(Comentario _comentario) throws SQLException;
	
	void Editar(Comentario _comentario) throws SQLException;

	void Excluir(Comentario _comentario) throws SQLException;
	
	void ExcluirPorID(Long _id) throws SQLException;

	Comentario PegarPeloID(Long _id) throws SQLException;
	
	List<Comentario> listarTodos() throws SQLException;
}

package dados;

import java.sql.SQLException;
import java.util.List;

import entidades.Desafio;

public interface DesafioINTdao {

	void Inserir(Desafio _desafio) throws SQLException;
	
	void Editar(Desafio _desafio) throws SQLException;

	void Excluir(Desafio _desafio) throws SQLException;
	
	void ExcluirPorID(Long _id) throws SQLException;

	Desafio PegarPeloID(Long _id) throws SQLException;
	
	List<Desafio> listarTodos() throws SQLException;
}

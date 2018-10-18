package dados;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Comentario;

public class ComentarioDAO implements ComentarioINTdao {

	private static ComentarioDAO instancia;
	protected EntityManager entityManager;
	
	public ComentarioDAO() {
		this.entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("ccbh4851System");
		
		if (this.entityManager == null) {
			
			this.entityManager = fabrica.createEntityManager();
		}
		
		return this.entityManager;
	}
	
	public static ComentarioDAO getInstancia() {
		
		if (instancia == null) {
			
			instancia = new ComentarioDAO();
		}
		
		return instancia;
	}
	
	@Override
	public void Inserir(Comentario _comentario) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(_comentario);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Editar(Comentario _comentario) throws SQLException {
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.merge(_comentario);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Excluir(Comentario _comentario) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		Comentario comentario = entityManager.find(Comentario.class, _comentario.getId());
		
		this.entityManager.remove(comentario);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void ExcluirPorID(Long _id) throws SQLException {

		Comentario comentario = PegarPeloID(_id);
		
		Excluir(comentario);

	}

	@Override
	public Comentario PegarPeloID(Long _id) throws SQLException {

		Comentario comentario = entityManager.find(Comentario.class, _id);
		
		return comentario;
	}

	@Override
	public List<Comentario> listarTodos() throws SQLException {

		Query q = entityManager.createQuery("from Compound r", Comentario.class);
		
		@SuppressWarnings("unchecked")
		List<Comentario> comentarios = q.getResultList();
		
		return comentarios;
	}

}

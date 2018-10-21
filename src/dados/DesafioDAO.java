package dados;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Desafio;

public class DesafioDAO implements DesafioINTdao {

	private static DesafioDAO instancia;
	protected EntityManager entityManager;
	
	public DesafioDAO() {
		this.entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("ccbh4851System");
		
		if (this.entityManager == null) {
			
			this.entityManager = fabrica.createEntityManager();
		}
		
		return this.entityManager;
	}
	
	public static DesafioDAO getInstancia() {
		
		if (instancia == null) {
			
			instancia = new DesafioDAO();
		}
		
		return instancia;
	}
	
	@Override
	public void Inserir(Desafio _desafio) throws SQLException {
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(_desafio);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Editar(Desafio _desafio) throws SQLException {
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.merge(_desafio);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Excluir(Desafio _desafio) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		Desafio desafio = entityManager.find(Desafio.class, _desafio.getId());
		
		this.entityManager.remove(desafio);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void ExcluirPorID(Long _id) throws SQLException {

		Desafio desafio = PegarPeloID(_id);
		
		Excluir(desafio);

	}

	@Override
	public Desafio PegarPeloID(Long _id) throws SQLException {

		Desafio desafio = entityManager.find(Desafio.class, _id);
		
		return desafio;
	}

	@Override
	public List<Desafio> listarTodos() throws SQLException {

		Query q = entityManager.createQuery("from Compound r", Desafio.class);
		
		@SuppressWarnings("unchecked")
		List<Desafio> desafios = q.getResultList();
		
		return desafios;
	}

}

package dados;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Especialidade;

public class EspecialidadeDAO implements EspecialidadeINTdao {

	private static EspecialidadeDAO instancia;
	protected EntityManager entityManager;
	
	public EspecialidadeDAO() {
		this.entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("ccbh4851System");
		
		if (this.entityManager == null) {
			
			this.entityManager = fabrica.createEntityManager();
		}
		
		return this.entityManager;
	}
	
	public static EspecialidadeDAO getInstancia() {
		
		if (instancia == null) {
			
			instancia = new EspecialidadeDAO();
		}
		
		return instancia;
	}
	
	@Override
	public void Inserir(Especialidade _especialidade) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(_especialidade);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Editar(Especialidade _especialidade) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		this.entityManager.merge(_especialidade);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Excluir(Especialidade _especialidade) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		Especialidade especialidade = entityManager.find(Especialidade.class, _especialidade.getId());
		
		this.entityManager.remove(especialidade);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void ExcluirPorID(Long _id) throws SQLException {

		Especialidade especialidade = PegarPeloID(_id);
		
		Excluir(especialidade);

	}

	@Override
	public Especialidade PegarPeloID(Long _id) throws SQLException {

		Especialidade especialidade = entityManager.find(Especialidade.class, _id);
		
		return especialidade;
	}

	@Override
	public List<Especialidade> listarTodos() throws SQLException {

		Query q = entityManager.createQuery("from Especialidade e", Especialidade.class);
		
		@SuppressWarnings("unchecked")
		List<Especialidade> especialidades = q.getResultList();
		
		return especialidades;
	}

}

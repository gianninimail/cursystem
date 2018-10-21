package dados;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Resposta;

public class RespostaDAO implements RespostaINTdao {

	private static RespostaDAO instancia;
	protected EntityManager entityManager;
	
	public RespostaDAO() {
		this.entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("ccbh4851System");
		
		if (this.entityManager == null) {
			
			this.entityManager = fabrica.createEntityManager();
		}
		
		return this.entityManager;
	}
	
	public static RespostaDAO getInstancia() {
		
		if (instancia == null) {
			
			instancia = new RespostaDAO();
		}
		
		return instancia;
	}
	
	@Override
	public void Inserir(Resposta _resposta) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(_resposta);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Editar(Resposta _resposta) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		this.entityManager.merge(_resposta);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void Excluir(Resposta _resposta) throws SQLException {

		this.entityManager.getTransaction().begin();
		
		Resposta resposta = entityManager.find(Resposta.class, _resposta.getId());
		
		this.entityManager.remove(resposta);
		
		this.entityManager.getTransaction().commit();

	}

	@Override
	public void ExcluirPorID(Long _id) throws SQLException {

		Resposta resposta = PegarPeloID(_id);
		
		Excluir(resposta);

	}

	@Override
	public Resposta PegarPeloID(Long _id) throws SQLException {

		Resposta resposta = entityManager.find(Resposta.class, _id);
		
		return resposta;
	}

	@Override
	public List<Resposta> listarTodos() throws SQLException {

		Query q = entityManager.createQuery("from Compound r", Resposta.class);
		
		@SuppressWarnings("unchecked")
		List<Resposta> respostas = q.getResultList();
		
		return respostas;
	}

}

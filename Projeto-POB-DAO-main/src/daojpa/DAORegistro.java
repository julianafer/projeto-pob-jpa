package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Registro;

public class DAORegistro extends DAO<Registro> {

	public Registro read (Object chave){
		try{
			int id = (int) chave;
			TypedQuery<Registro> q = manager.createQuery("select r from Registro r LEFT JOIN FETCH r.veiculo where r.id = :n ", Registro.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}

	public List<Registro> readAll(){
		TypedQuery<Registro> q = manager.createQuery("select r from Registro r LEFT JOIN FETCH r.veiculo order by r.id", Registro.class);
		return q.getResultList();
	}
	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public List<Registro> registrosData(String data){
		TypedQuery<Registro> q = manager.createQuery("select r from Registro r LEFT JOIN FETCH r.veiculo where r.datahora LIKE :d", Registro.class);
		q.setParameter("d", data+"%");
		return q.getResultList();
	}
	
}
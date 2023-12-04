package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Registro;

public class DAORegistro extends DAO<Registro> {

	public Registro read (Object chave){
		try{
			int id = (int) chave;
			TypedQuery<Registro> q = manager.createQuery("select r from Registro r where r.id = :n ",Registro.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Registro> readAll(){
		TypedQuery<Registro> q = manager.createQuery("select a from Aluguel a LEFT JOIN FETCH a.carro  JOIN FETCH a.cliente order by a.id", Registro.class);
		return  q.getResultList();
	}


	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public List<Registro> alugueisModelo(String modelo){
		//alugueis contendo carro de modelo 'palio'
		TypedQuery<Registro> q = manager.createQuery("select a from Aluguel a where a.carro.modelo = :x", Registro.class);
		q.setParameter("x", modelo);
		
		return  q.getResultList();
	}

	
	public List<Registro> alugueisFinalizados(){
		TypedQuery<Registro> q = manager.createQuery("select a from Aluguel a where a.finalizado = true", Registro.class);
		
		return  q.getResultList();
	}
	
}

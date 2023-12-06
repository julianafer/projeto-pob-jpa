package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Veiculo;

public class DAOVeiculo extends DAO<Veiculo> {

	public Veiculo read (Object chave){
		try{
			String placa = (String) chave;
			TypedQuery<Veiculo> q = manager.createQuery("select v from Veiculo v where v.placa=:pla",Veiculo.class);
			q.setParameter("pla", placa);
			Veiculo v =  q.getSingleResult();
			return v;
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Veiculo> readAll(){
		TypedQuery<Veiculo> query = manager.createQuery("select v from Veiculo v LEFT JOIN FETCH v.registros order by v.placa",Veiculo.class);
		return  query.getResultList();
	}
	
}

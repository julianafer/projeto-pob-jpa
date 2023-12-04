package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.TipoVeiculo;

public class DAOTipoVeiculo extends DAO<TipoVeiculo> {

	public TipoVeiculo read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<TipoVeiculo> q = manager.createQuery("select t from TipoVeiculo t where t.nome=:nome",TipoVeiculo.class);
			q.setParameter("nome", nome);
			TipoVeiculo tv =  q.getSingleResult();
			return tv;
		}catch(NoResultException e){
			return null;
		}
	}
	
}

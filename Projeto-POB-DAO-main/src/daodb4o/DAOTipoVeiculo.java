package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.TipoVeiculo;

public class DAOTipoVeiculo extends DAO<TipoVeiculo> {

	public TipoVeiculo read (Object chave){
		String nome = (String) chave;	
		Query q = manager.query();
		q.constrain(TipoVeiculo.class);
		q.descend("nome").constrain(nome);
		List<TipoVeiculo> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
}


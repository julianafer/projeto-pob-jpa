package daodb4o;

import java.util.List;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Veiculo;

public class DAOVeiculo extends DAO<Veiculo> {
	
	public Veiculo read (Object chave) {
		String placa = (String) chave;	
		Query q = manager.query();
		q.constrain(Veiculo.class);
		q.descend("placa").constrain(placa);
		List<Veiculo> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	// quais os veiculos contendo registro na data X
	public List<Veiculo> veiculosData(String data){
		Query q;
		q = manager.query();
		q.constrain(Veiculo.class);
		q.descend("registros").descend("datahora").constrain(data).contains();
		return q.execute();
	}
	
	// quais os veiculos contendo com mais de N registros
	public List<Veiculo> veiculosN(int n){
		Query q = manager.query();
		q.constrain(Veiculo.class);
		q.constrain(new Filtro(n));
		return q.execute();
	}
	
	// classe interna
	class Filtro implements Evaluation {
		private int n;
		public Filtro(int n) {
			this.n = n;
		}
		public void evaluate(Candidate candidate) {
			Veiculo veiculo = (Veiculo) candidate.getObject();
			if(veiculo.getRegistros().size() > n) 
				candidate.include(true); 
			else
				candidate.include(false);
		}
	}
	
}

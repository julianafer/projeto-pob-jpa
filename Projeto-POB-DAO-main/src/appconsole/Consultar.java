package appconsole;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

import modelo.Registro;
import modelo.Veiculo;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			
			Fachada.inicializar();
			
			System.out.println("consultas... \n");
			
			System.out.println("\nQuais os registros da data 11/02/2023");
			for (Registro registro : Fachada.registrosEmData("11/02/2023")) {
				System.out.println(registro);
			}
			
			System.out.println("\nQuais os veiculos contendo registro na data 12/02/2023");
			for (Veiculo veiculo : Fachada.veiculosEmData("12/02/2023")) {
				System.out.println(veiculo);
			}
			
			System.out.println("\nQuais os veículos contendo mais de 1 registro");
			for (Veiculo veiculo : Fachada.veiculosNRegistros(1)) {
				System.out.println(veiculo);
			}
			
			Fachada.finalizar();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

//classe interna
class Filtro1 implements Evaluation {
	public void evaluate(Candidate candidate) {
		Veiculo veiculo = (Veiculo) candidate.getObject();
		if(veiculo.getRegistros().size() > 1) 
			candidate.include(true); 
		else
			candidate.include(false);
	}
}

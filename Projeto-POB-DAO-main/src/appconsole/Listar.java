package appconsole;

import modelo.Registro;
import modelo.TipoVeiculo;
import modelo.Veiculo;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			
			System.out.println("\n---listagem de tipos de veículos:");
			for (TipoVeiculo tipo : Fachada.listarTipos()) {
				System.out.println(tipo);
			}

			System.out.println("\n---listagem de veículos:");
			for (Veiculo veiculo : Fachada.listarVeiculos()) {
				System.out.println(veiculo);
			}
			
			System.out.println("\n---listagem de registros:");
			for (Registro registro : Fachada.listarRegistros()) {
				System.out.println(registro);
			}
			
			Fachada.finalizar();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}

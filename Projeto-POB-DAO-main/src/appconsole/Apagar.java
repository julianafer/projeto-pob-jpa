package appconsole;

import regras_negocio.Fachada;

public class Apagar {

	public Apagar() {
		try {
			
			Fachada.inicializar();
			
			System.out.println("excluindo");
			Fachada.excluirVeiculo("KFC2002");
			System.out.println("\n... veiculo excluido");
			
			Fachada.finalizar();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Apagar();
	}
}

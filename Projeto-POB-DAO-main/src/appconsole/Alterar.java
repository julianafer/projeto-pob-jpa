package appconsole;

import regras_negocio.Fachada;

public class Alterar {

	public Alterar(){
		Fachada.inicializar();
		atualizar();
		Fachada.finalizar();
	}
 
	public void atualizar(){
		try {
					
			System.out.println("alterando veiculo");
			Fachada.alterarVeiculo("AAB1001", "ABC1234");
			Fachada.criarRegistro("13/02/2023 11:27", "ABC1234", "entrada");
			System.out.println("\n... veiculo alterado!");
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}


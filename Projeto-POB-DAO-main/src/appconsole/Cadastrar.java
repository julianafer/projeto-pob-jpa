package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			
			Fachada.inicializar();
			
			System.out.println("cadastrando...");
			
			Fachada.criarTipo("moto");
			Fachada.criarTipo("carro");
			
			Fachada.criarVeiculo("AAA1000", "moto");
			Fachada.criarVeiculo("AAB1001", "carro");
			Fachada.criarVeiculo("BCD1012", "carro");
			Fachada.criarVeiculo("KFC2002", "carro");
			
			Fachada.criarRegistro("11/02/2023 11:55", "AAA1000", "entrada");
			Fachada.criarRegistro("11/02/2023 11:58", "BCD1012", "entrada");
			Fachada.criarRegistro("11/02/2023 12:37", "AAA1000", "saida");
			Fachada.criarRegistro("12/02/2023 15:20", "AAB1001", "entrada");
			Fachada.criarRegistro("12/02/2023 18:50", "AAB1001", "saida");
			Fachada.criarRegistro("13/02/2023 14:00", "KFC2002", "entrada");
			
			Fachada.finalizar();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}

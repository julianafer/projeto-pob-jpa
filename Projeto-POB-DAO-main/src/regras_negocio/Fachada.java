package regras_negocio;

import daojpa.DAOVeiculo;

import java.util.List;

import daojpa.DAO;
import daojpa.DAORegistro;
import daojpa.DAOTipoVeiculo;
import modelo.Veiculo;
import modelo.Registro;
import modelo.TipoVeiculo;

public class Fachada {
	
	private static DAOVeiculo daoveiculo = new DAOVeiculo();  
	private static DAORegistro daoregistro = new DAORegistro(); 
	private static DAOTipoVeiculo daotipoveiculo = new DAOTipoVeiculo();
	
	public static void inicializar() {
		DAO.open();
	}
	
	public static void finalizar() {
		DAO.close();
	}
	
	// ---- criar
	
	public static TipoVeiculo criarTipo(String nome) throws Exception {
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo!=null)
			throw new Exception("Tipo de veiculo ja cadastrado: " + nome);
		tipo = new TipoVeiculo(nome);

		daotipoveiculo.create(tipo);
		DAO.commit();
		return tipo;
	}
	
	public static Veiculo criarVeiculo(String placa, String tipoVeiculo) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo!=null)
			throw new Exception("Veiculo ja cadastrado: " + placa);
		
		TipoVeiculo tipo = daotipoveiculo.read(tipoVeiculo);
		if (tipo==null)
			throw new Exception("tipo " + tipoVeiculo + " inexistente");
		veiculo = new Veiculo(placa, tipo);

		daoveiculo.create(veiculo);
		DAO.commit();
		return veiculo;
	}
	
	public static Registro criarRegistro(String datahora, String placa, String operacao) throws Exception {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("veiculo " + placa + " inexistente");
		
		if (operacao == null)
            throw new Exception("campo vazio");
		
		Registro registro = new Registro(datahora, veiculo, operacao);
		daoregistro.create(registro);
		Fachada.addRegistroEmVeiculo(veiculo, registro);
		daoveiculo.update(veiculo);
		DAO.commit();
		return registro;
	}
	
	public static void addRegistroEmVeiculo(Veiculo veiculo, Registro registro) { // teste
		DAO.begin();
		veiculo.getRegistros().add(registro);
		daoveiculo.update(veiculo);
		DAO.commit();
	}
	
	// ---- alterar
	
	public static void alterarTipo(String nome, String novonome) throws Exception{
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null) {
			DAO.rollback();
			throw new Exception("O tipo não existe");
		}
		tipo.setNome(novonome);
		daotipoveiculo.update(tipo);
		DAO.commit();	
	}
	
	public static void alterarVeiculo(String placa, String novaplaca) throws Exception  {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null) {
			DAO.rollback();
			throw new Exception("O veiculo não existe");
		}
		veiculo.setPlaca(novaplaca);
		daoveiculo.update(veiculo);
		DAO.commit();
	}
	
	// ---- excluir

	public static void excluirTipo(String nome) throws Exception {
	    DAO.begin();
	    TipoVeiculo tipo = daotipoveiculo.read(nome);
	    if (tipo == null) 
	        throw new Exception("Tipo " + nome + " incorreto para exclusao");

	    for (Veiculo v : daoveiculo.readAll()) {
	        if (v.getTipoveiculo().getNome().equals(tipo.getNome())) {
	            Fachada.excluirVeiculo(v.getPlaca());
	        }
	    }

	    // Verifique se a entidade está no contexto de persistência antes de excluir
	    tipo = daotipoveiculo.read(nome);

	    if (tipo != null) {
	        daotipoveiculo.delete(tipo);
	    }

	    DAO.commit();
	}
	
	public static void excluirVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo " + placa + " não cadastrado");

		for (Registro r : veiculo.getRegistros()) {
			daoregistro.delete(r);
		}
		
		daoveiculo.delete(veiculo);
		DAO.commit();
	}
	
	public static void excluirRegistro(int id) throws Exception{
		DAO.begin();
		Registro registro = daoregistro.read(id);
		if (registro==null)
			throw new Exception("Registro " + id + " não cadastrado");
		
		Veiculo v = registro.getVeiculo();

		daoregistro.delete(registro);
		daoveiculo.update(v);
		DAO.commit();
	}
	
	// ---- localizar
	
	public static TipoVeiculo localizarTipo(String nome) throws Exception{
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null)
			throw new Exception("Tipo: " + nome + " inexistente");
		DAO.commit();
		return tipo;
	}
	
	public static Veiculo localizarVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo da placa: " + placa + " não existe");
		DAO.commit();
		return veiculo;
	};

	public static Registro localizarRegistro(int id) throws Exception{
		DAO.begin();
		Registro registro = daoregistro.read(id);
		if (registro==null)
			throw new Exception("ID: " + id + " não existe");
		DAO.commit();
		return registro;
	}

	// ---- listar
	
	public static List<TipoVeiculo> listarTipos(){
		DAO.begin();
		List<TipoVeiculo> resultados =  daotipoveiculo.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Veiculo> listarVeiculos(){
		DAO.begin();
		List<Veiculo> resultados =  daoveiculo.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Registro> listarRegistros(){
		DAO.begin();
		List<Registro> resultados =  daoregistro.readAll();
		DAO.commit();
		return resultados;
	}
	
	// ---- Consultas
	
	public static List<Registro> registrosEmData(String data) {
		DAO.begin();
		List<Registro> resultado= daoregistro.registrosData(data);
		DAO.commit();
		return resultado;
	} 
	
	public static List<Veiculo> veiculosEmData(String data){
		DAO.begin();
		List<Veiculo> resultado= daoveiculo.veiculosData(data);
		DAO.commit();
		return resultado;
	}
	
	public static List<Veiculo> veiculosNRegistros(int n){
		DAO.begin();
		List<Veiculo> resultado= daoveiculo.veiculosN(n);
		DAO.commit();
		return resultado;
	}
	
}
package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String placa;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	private TipoVeiculo tipoveiculo; // carro, moto...
	
	@OneToMany(mappedBy = "veiculo", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	private List<Registro> registros = new ArrayList<>(); // iserir, remover e localizar
	
	public Veiculo(String placa, TipoVeiculo tipoveiculo) {
		super();
		this.placa = placa;
		this.tipoveiculo = tipoveiculo;
	}
	
	public Veiculo() { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getTipoveiculo() {
		return tipoveiculo;
	}

	public void setTipoveiculo(TipoVeiculo tipoveiculo) {
		this.tipoveiculo = tipoveiculo;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	public void inserirRegistro(Registro registro) {
		registros.add(registro);
	}
	
	public void removerRegistro(Registro registro) {
		registros.remove(registro);
	}
	
	public Registro localizarRegistro(int id) {
		for (Registro r : registros) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Veiculo: placa=" + placa + ", tipoveiculo=" + tipoveiculo.getNome() ;
	}
	
}
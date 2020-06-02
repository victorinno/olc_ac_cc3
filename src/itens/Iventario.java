package itens;

import itens.arma.Arma;

import java.util.Arrays;

public class Iventario {

	private Arma[] armas;
	private Armadura[] armaduras;
	private Acessorio[] acessorios;
	
	public Iventario() {
		// TODO Auto-generated constructor stub
	}

	public Iventario(Arma[] armas, Armadura[] armaduras, Acessorio[] acessorios) {
		super();
		this.armas = armas;
		this.armaduras = armaduras;
		this.acessorios = acessorios;
	}

	public Arma[] getArmas() {
		return armas;
	}

	public void setArmas(Arma[] armas) {
		this.armas = armas;
	}

	public Armadura[] getArmaduras() {
		return armaduras;
	}

	public void setArmaduras(Armadura[] armaduras) {
		this.armaduras = armaduras;
	}

	public Acessorio[] getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(Acessorio[] acessorios) {
		this.acessorios = acessorios;
	}

	@Override
	public String toString() {
		return "Iventorio [armas=" + Arrays.toString(armas) + ", armaduras="
				+ Arrays.toString(armaduras) + ", acessorios="
				+ Arrays.toString(acessorios) + "]";
	}
	
	
	
}

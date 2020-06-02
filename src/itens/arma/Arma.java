package itens.arma;

import cb2.Lutador;
import itens.Item;


public class Arma extends Item {

	public Arma() {

	}
	
	public void ativar(Lutador perso) {
		rolarCataque();
		rolarCdano();
	}

	public Arma(String nome, Integer cataque, Integer cdefesa, Integer cdano,
			Integer creducao, Integer cpup, Integer cdup) {
		
		super(cataque, cdefesa, cdano, creducao, cpup, cdup);
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Arma [nome=" + nome + ", ataque=" + ataque + ", dano=" + dano
				+ "]";
	}

}

package itens.arma;

import itens.TipoItem;
import cb2.Lutador;

public class Cajado extends Arma {
	
	

	public Cajado(String nome) {
		this.nome = nome;
		this.tipoItem = TipoItem.CAJADO;
		this.ataque = 1;
		this.defesa = 1;
		this.dano = 1;
		this.reducao = 1;
		this.pup = 0.01;
		this.dup = 0.01;
		this.cataque = 5;
		this.cdefesa = 10;
		this.cdano = 5;
		this.creducao = 5;
	}


	
	
	

}

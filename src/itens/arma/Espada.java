package itens.arma;

import itens.TipoItem;


public class Espada extends Arma {

	public Espada(String nome) {
		this.nome = nome;
		this.tipoItem = TipoItem.ESPADA;
		this.ataque = 1;
		this.defesa = 1;
		this.dano = 1;
		this.reducao = 1;
		this.pup = 0.01;
		this.dup = 0.01;
		this.cataque = 10;
		this.cdefesa = 5;
		this.cdano = 8;
		this.creducao = 2;
	
	}
	
}

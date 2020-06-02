package itens;

import cb2.Lutador;

public class Armadura extends Item {

	public void ativar(Lutador perso) {
		rolarCdefesa();
		rolarCreducao();	
	}
	
	public Armadura(String nome, Integer cataque, Integer cdefesa, Integer cdano,
			Integer creducao, Integer cpup, Integer cdup) {
		
		super(cataque, cdefesa, cdano, creducao, cpup, cdup);
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Armadura [nome=" + nome + ", defesa=" + defesa + ", reducao="
				+ reducao + "]";
	}
	
}

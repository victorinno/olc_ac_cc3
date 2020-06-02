package itens;

import cb2.Lutador;

public class Acessorio extends Item {

	public void ativar(Lutador perso) {
		rolarCdup();
		rolarCpup();
	}
	
	public Acessorio(String nome, Integer cataque, Integer cdefesa, Integer cdano,
			Integer creducao, Integer cpup, Integer cdup) {
		
		super(cataque, cdefesa, cdano, creducao, cpup, cdup);
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Acessorio [nome=" + nome + ", pup=" + pup + ", dup=" + dup
				+ "]";
	}

}

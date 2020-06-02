package itens;

public enum TipoItem {

	CAJADO("Cajado"), ESPADA("Espada"), FACA("Faca"), ARCO("Arco"), MACHADO("Machado"), LANCA("Lanca");
	
	private String nome;
	
	private TipoItem() {
		
	}

	private TipoItem(String nome) {
		this.nome = nome;
	} 
	

	
}

package itens;

import itens.arma.Arma;

import java.util.Scanner;

import cb2.Lutador;

public class MenuIventorio {

	private Lutador lutador;
	
	public MenuIventorio(Lutador lutador) {
		this.lutador = lutador;
		if (this.lutador.getIventorio() == null) {
			this.lutador.setIventorio(new Iventario(new Arma[1], new Armadura[1], new Acessorio[1]));
		}
	}
	
	public void show(){
		System.out.println("Escolha um opcao: \r\n1 - Criar\r\n2 - Listar");
		Scanner scanner = new Scanner(System.in);
		int escolha = scanner.nextInt(10);
		escolher(escolha);
	}

	private void escolher(int escolha) {
		if (escolha == 1) {
			criar();
		}else if (escolha == 2) {
			listar();
		}
	}
	
	public void criar(){
		System.out.println("Escolha :\r\n1 - Arma\r\n2 - Armadura\r\n3 - Acessorio");
		Scanner scanner = new Scanner(System.in);
		int escolha = scanner.nextInt(10);
		System.out.println("Digite o nome:");
		scanner = new Scanner(System.in);
		String nome = scanner.nextLine();
		if (escolha == 1) {
			lutador.getIventorio().getArmas()[0] = new Arma(nome, 5, 5, 5, 5, 5, 5);
		}else if (escolha == 2) {
			lutador.getIventorio().getArmaduras()[0] = new Armadura(nome, 5, 5, 5, 5, 5, 5);
		}else if (escolha == 3) {
			lutador.getIventorio().getAcessorios()[0] = new Acessorio(nome, 5, 5, 5, 5, 5, 5);
		}
	}
	
	public void listar(){
		System.out.println(lutador.getIventorio().toString());
	}
	
	
	
	
	
}

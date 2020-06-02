/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc3;

import cb2.ClasseLutadora;
import cb2.Defesa;
import cb2.Lutador;
import cb2.Poder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import itens.MenuIventorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author floriano
 */
public class CC3 {

	public static void main(String[] args) {
		FileReader fr = null;
		try {
			Scanner sc = new Scanner(System.in);
			Boolean atacante = null;
			CC3 a = new CC3();
			a.criarClasses();
			System.out.println("Digite um comando");
			System.out.println("1 - Criar Lutador:");
			System.out.println("2 - Lutador Existente:");
			sc = new Scanner(System.in);
			int opt = sc.nextInt();
			if (opt == 1) {

				a.criarNovoLutador();
			} else {

				System.out.println("Digite o nome do seu lutador: ");
				sc = new Scanner(System.in);
				String l = sc.nextLine();
				XStream xstream = new XStream(new DomDriver());
				File perArq = new File("C:/co1/lutadores/" + l + ".xml");
				fr = new FileReader(perArq);
				Lutador lutador = (Lutador) xstream.fromXML(fr);
				menuGeral(a, lutador);

			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void menuGeral(CC3 a, Lutador lutador) {
		Scanner sc;
		int opt;
		System.out.println("Digite um comando");
		System.out.println("1 - Ataque:");
		System.out.println("2 - Defesa:");
		System.out.println("3 - Poderes:");
		System.out.println("4 - Defesas:");
		System.out.println("5 - Status:");
		System.out.println("6 - Itens:");
		sc = new Scanner(System.in);
		opt = sc.nextInt();
		if (opt == 1) {
			a.menuAtaque(a, lutador);
		} else if (opt == 2) {
			a.menuDefesa(a, lutador);
		} else if (opt == 3) {
			a.menuPoderes(lutador);
		} else if (opt == 4) {
			try {
				a.menuDefesas(lutador);
			} catch (FileNotFoundException ex) {
				Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null,
						ex);
			} catch (IOException ex) {
				Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		} else if (opt == 5) {
			System.out.println(lutador.displayStatus());
		} else if (opt == 6) {
			new MenuIventorio(lutador).show();
			try {
				a.persistirLutador(lutador);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void menuDefesa(CC3 a, Lutador lutador) {
		Scanner sc;
		System.out.println("Digite o ataque do adversario: ");
		sc = new Scanner(System.in);
		String atk = sc.nextLine();
		a.calcularDefesa(atk, lutador);

	}

	public static void menuAtaque(CC3 a, Lutador lutador) {
		Scanner sc;
		a.atacar(lutador);
	}

	public void menuPoderes(Lutador lutador) {
		System.out.println("Escolha a baixo: ");
		System.out.println("1 - Criar poder: ");
		System.out.println("2 - Listar Poderes: ");
		System.out.println("3 - Usar Poderes: ");

		Scanner sc = new Scanner(System.in);
		int opt = sc.nextInt();
		if (opt == 1) {
			Writer writer = null;
			criarPoder(lutador);
		} else if (opt == 2) {
			for (Poder object : lutador.getPoderes()) {
				System.out.println(object.toString());
			}
		} else if (opt == 3) {
			for (int i = 0; i < lutador.getPoderes().size(); i++) {
				System.out.println((i + 1) + " - "
						+ lutador.getPoderes().get(i).toString());
			}
			sc = new Scanner(System.in);
			int p = sc.nextInt();
			usarPoder(p, lutador);
		}
	}

	public void menuDefesas(Lutador lutador) throws FileNotFoundException,
			IOException {
		System.out.println("Escolha a baixo: ");
		System.out.println("1 - Criar Defesa: ");
		System.out.println("2 - Listar Defesas: ");
		System.out.println("3 - Usar Defesas: ");

		Scanner sc = new Scanner(System.in);
		int opt = sc.nextInt();
		if (opt == 1) {
			Writer writer = null;
			criarDefesa(lutador);
		} else if (opt == 2) {
			if (lutador.getDefesas() == null) {
				lutador.setDefesas(new ArrayList<Defesa>());
				persistirLutador(lutador);
			}
			for (Defesa object : lutador.getDefesas()) {
				System.out.println(object.toString());
			}
		} else if (opt == 3) {
			for (int i = 0; i < lutador.getDefesas().size(); i++) {
				System.out.println((i + 1) + " - "
						+ lutador.getDefesas().get(i).toString());
			}
			System.out.println("Escolha sua defesa: ");
			sc = new Scanner(System.in);
			int p = sc.nextInt();
			System.out.println("Digite o ataque: ");
			sc = new Scanner(System.in);
			String ataque = sc.nextLine();
			usarDefesa(p, lutador, ataque);
		}

	}

	public void criarPoder(Lutador lutador) {
		Scanner sc;
		int opt;
		Writer writer = null;
		try {
			System.out.println("Escolha o tipo de poder: ");
			System.out.println("1 - Moderado");
			System.out.println("2 - Força");
			System.out.println("3 - Perícia");
			sc = new Scanner(System.in);
			opt = sc.nextInt();
			System.out.println("Digite um nome para o poder: ");
			sc = new Scanner(System.in);
			String nome = sc.nextLine();
			System.out.println("Digite um nivel: ");
			sc = new Scanner(System.in);
			int nivel = sc.nextInt();
			if (nivel <= 5 && nivel > 0) {

				Poder p = new Poder();
				if (opt == 1) {
					p = new Poder(nome, 1, 1, nivel, 5, 5);
				} else if (opt == 2) {
					p = new Poder(nome, 1, 1, nivel, 7, 3);
				} else if (opt == 3) {
					p = new Poder(nome, 1, 1, nivel, 3, 7);
				}
				List<Poder> ps = lutador.getPoderes();
				if (ps == null) {
					ps = new ArrayList<Poder>();
				}
				ps.add(p);
				lutador.setPoderes(ps);
				persistirLutador(lutador);
				System.out.println("Poder salvo com sucesso!");
			} else {
				System.out.println("Nivél máximo 5 e mínimo 1!");
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
	}

	public void criarDefesa(Lutador lutador) {
		Scanner sc;
		int opt;
		Writer writer = null;
		try {
			System.out.println("Escolha o tipo de defesa: ");
			System.out.println("1 - Moderada");
			System.out.println("2 - Tanker");
			System.out.println("3 - Veloz");
			sc = new Scanner(System.in);
			opt = sc.nextInt();
			System.out.println("Digite um nome para defesa: ");
			sc = new Scanner(System.in);
			String nome = sc.nextLine();
			System.out.println("Digite um nivel: ");
			sc = new Scanner(System.in);
			int nivel = sc.nextInt();
			if (nivel <= 5 && nivel > 0) {

				Defesa p = new Defesa();
				if (opt == 1) {
					p = new Defesa(nome, 1, 1, nivel, 5, 5);
				} else if (opt == 2) {
					p = new Defesa(nome, 1, 1, nivel, 3, 7);
				} else if (opt == 3) {
					p = new Defesa(nome, 1, 1, nivel, 7, 3);
				}
				List<Defesa> ps = lutador.getDefesas();
				if (ps == null) {
					ps = new ArrayList<Defesa>();
				}
				ps.add(p);
				lutador.setDefesas(ps);
				persistirLutador(lutador);
				System.out.println("Poder salvo com sucesso!");
			} else {
				System.out.println("Nivel maximo 5 e minimo 1!");
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
	}

	public void evolucao(Lutador a) {
		FileOutputStream fos = null;
		try {
			Random r = new Random();
			Integer cf = a.getCl().getForca();
			Integer rolagem = r.nextInt(101);
			
			if (rolagem <= cf) {
				a.setForca(a.getForca() + 1);
				System.out.println(a.getNome() + " aumentou sua forca");
			}
			Integer cp = a.getCl().getPericia();
			rolagem = r.nextInt(101);
			
			if (rolagem <= cp) {
				a.setPericia(a.getPericia() + 1);
				System.out.println(a.getNome() + " aumentou sua pericia");
			}
			Integer ca = a.getCl().getAgilidade();
			rolagem = r.nextInt(101);
			
			if (rolagem <= ca) {
				a.setAgilidade(a.getAgilidade() + 1);
				System.out.println(a.getNome() + " aumentou sua agilidade");
			}
			Integer cr = a.getCl().getResistencia();
			rolagem = r.nextInt(101);
			
			if (rolagem <= cr) {
				a.setResistencia(a.getResistencia() + 1);
				System.out.println(a.getNome() + " aumentou sua resistencia");
			}
			persistirLutador(a);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void persistirLutador(Lutador a) throws IOException,
			FileNotFoundException {
		XStream xstream = new XStream(new DomDriver());
		File perArq = new File("C:/co1/lutadores/" + a.getNome() + ".xml");
		Writer writer = new PrintWriter(perArq);
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xstream.toXML(a, writer);
	}

	public void atacar(Lutador a) {
		try {
			if (a.getPowerUp() == null) {
				a.setPowerUp(0);
			}
			a.getIventorio().getArmas()[0].ativar(a);
			Random r = new Random();
			Integer pp = +r.nextInt(3) + 1;
			System.out.println("PowerUP: " + pp);
			a.setPowerUp(a.getPowerUp() + pp);
			evolucao(a);
			persistirLutador(a);
			System.out.println((a.getPericia()
					+ a.getIventorio().getArmas()[0].getDano()) + "/" + (a.getForca() 
					+ a.getIventorio().getArmas()[0].getAtaque()) + "/" + a.getNome() + " ataca com " + a.getIventorio().getArmas()[0].getNome());
		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);

		}
	}

	public void usarPoder(Integer poder, Lutador a) {
		Poder p = a.getPoderes().get(poder - 1);
		if (a.getPowerUp() >= p.getNivel()) {
			
			a.getIventorio().getAcessorios()[0].ativar(a);
			int apup = a.getIventorio().getAcessorios()[0].pup();
			a.setPowerUp(p.getNivel() == 1 ? a.getPowerUp() - 0 : a
					.getPowerUp() - p.getNivel() + apup);
			System.out.println("Powerup - " + p.getNivel() + " = "
					+ a.getPowerUp() + apup);
			Integer pericia = a.getPericia() + (p.getPericia() * p.getNivel());
			Integer forca = a.getForca() + (p.getForca() * p.getNivel());
			System.out.println(pericia + "/" + forca + "/" + p.getNome());
			evolucaoPoder(poder, a);
			Random r = new Random();
			Integer pp = +r.nextInt(3) + 1;
			
			a.setPowerUp(a.getPowerUp() + pp);
			evolucao(a);
		} else {
			System.out.println("Voce nao pode usar este poder!");
			menuGeral(this, a);
		}
	}

	public void usarDefesa(Integer defesa, Lutador a, String ataque)
			throws FileNotFoundException, IOException {
		Defesa p = a.getDefesas().get(defesa - 1);
		if (a.getDefenseUp() >= p.getNivel()) {
			
			a.getIventorio().getAcessorios()[0].ativar(a);
			a.setDefenseUp(p.getNivel() == 1 ? a.getDefenseUp() : a
					.getDefenseUp() - p.getNivel());

			System.out.println("DefenseUp - " + p.getNivel() + " = "
					+ a.getDefenseUp());
			Integer agilidade = a.getAgilidade()
					+ (p.getAgilidade() * p.getNivel());
			Integer resistencia = a.getResistencia()
					+ (p.getResistencia() * p.getNivel());

			String[] atks = ataque.split("/");
			System.out.println(p.getNome() + " VS " + atks[2]);
			Integer per = new Integer(atks[0]);
			Integer chance = (int) ((per / agilidade) * 50 * (1.2 - (Math.random() * 0.40)));
			
			Random r = new Random();
			Integer rolagem = r.nextInt(101);
			if (chance > 95) {
				chance = 95;
			} else if (chance < 5) {
				chance = 5;
			}

			int adup = a.getIventorio().getAcessorios()[0].dup();
			Integer dp = r.nextInt(3) + 1 + adup;
			System.out.println("DefenseUP: +" + dp);
			a.setDefenseUp(a.getDefenseUp() + dp);
			evolucao(a);
			evolucaoDefesa(defesa, a);
			if (rolagem < chance) {
				System.out.println("Acertou");
				int cri = calcularMultiplicador(chance - 95);
				if (cri > 0) {
					System.out.println("Critico! " + cri);
				}
				Integer dano = (int) (new Integer(atks[1]) / resistencia + ((Math
						.random() * 4) + 1))
						+ cri;
				dano = dano < 1 ? 1 : dano;
				System.out.println("Dano: " + dano);
				a.setHp(a.getHp() - dano);
				persistirLutador(a);
			} else {
				System.out.println("Errou");
			}
			System.out.println("Vida: " + a.getHp() + "%");
			// System.out.println(agilidade + "/" + forca + "/" +p.getNome());
			// evolucaoPoder(poder, a);

		} else {
			System.out.println("Você não pode usar este poder!");
			menuGeral(this, a);
		}
	}

	public void evolucaoPoder(Integer poder, Lutador a) {
		Poder p = a.getPoderes().get(poder - 1);
		Random r = new Random();
		int cf = r.nextInt(101);
		int cp = r.nextInt(101);
	
		if (cf <= p.getChanceForca()) {
			p.setForca(p.getForca() + 1);
			System.out
					.println("Poder: " + p.getNome() + " aumentou em forca!");
		}

		if (cp <= p.getChancePericia()) {
			p.setPericia(p.getPericia() + 1);
			System.out.println("Poder: " + p.getNome()
					+ " aumentou em pericia!");
		}

	}

	public void evolucaoDefesa(Integer defesa, Lutador a) {
		Defesa p = a.getDefesas().get(defesa - 1);
		Random r = new Random();
		int cf = r.nextInt(101);
		int cp = r.nextInt(101);

		if (cf <= p.getChanceAgilidade()) {
			p.setAgilidade(p.getAgilidade() + 1);
			System.out
					.println("Poder: " + p.getNome() + " aumentou em agilidade!");
		}

		if (cp <= p.getChanceResistencia()) {
			p.setResistencia(p.getResistencia() + 1);
			System.out.println("Poder: " + p.getNome()
					+ " aumentou em resistencia!");
		}

	}

	public String calcularAcerto(String def, Lutador a) {
		if ("acertou".equals(def)) {
			evolucao(a);
			return "for:" + a.getForca();
		} else {
			evolucao(a);
			a.setAtacando(false);
			return "fim de turno";
		}
	}

	public void calcularDefesa(String ataque, Lutador a) {
		String[] atkS = ataque.split("/");
		a.getIventorio().getArmaduras()[0].ativar(a);
		System.out.println("O atacante usou: " + atkS[2]);
		System.out.println("Você se denfede com: " + a.getIventorio().getArmaduras()[0].getNome());
		Integer per = new Integer(atkS[0]);
		Integer chance = (int) (per / (a.getAgilidade() + a.getIventorio().getArmaduras()[0].getDefesa())) * 50;
		Random r = new Random();
		Integer rolagem = (int) (r.nextInt(101) * (1.2 - (Math.random() * 0.40)));
		Integer critico = chance - 95;
		if (chance > 95) {
			chance = 95;
		} else if (chance < 5) {
			chance = 5;
		}

		//System.out.println("Chance: " + chance);
		//System.out.println("Rolagem: " + rolagem);
		Integer dp = r.nextInt(3) + 1;
		System.out.println("DefenseUP: +" + dp);
		a.setDefenseUp(a.getDefenseUp() + dp);
		evolucao(a);
		if (rolagem < chance) {
			System.out.println("Acertou");
			int multi = calcularMultiplicador(critico);
			Integer dano = new Integer(atkS[1]);

			calcularDano(dano.toString(), a, multi);
		} else {
			System.out.println("Errou");
		}

	}

	private Integer calcularMultiplicador(Integer criticalChance) {
		int i = 0;
		int multi = 0;
		int baseCriticalChance = criticalChance;

		while (baseCriticalChance > 95) {
			i++;
			baseCriticalChance -= 95;

		}

		if (i > 0) {
			Integer[][] mapaCritico = new Integer[i][2];

			i = 0;
			int base = 2;
			while (criticalChance > 95) {

				mapaCritico[i][0] = base;
				mapaCritico[i][1] = criticalChance >= 95 ? 95 : criticalChance;
				criticalChance -= 95;
				i++;
				base++;
			}
			Boolean cont = true;
			Random r = new Random();

			for (int loc = 0; loc < i; loc++) {
				int rolagem = r.nextInt(101);

				if (rolagem <= mapaCritico[loc][1]) {
					multi = mapaCritico[loc][0];

				} else {
					cont = false;
					return multi == 0 ? 1 : multi;
				}
			}

			System.out.println("Critico!! " + multi + "X Dano!");
		}
		return multi == 0 ? 1 : multi;
	}

	public void calcularDano(String forca, Lutador a, Integer multi) {
		Writer writer = null;
		try {
			Integer forc = new Integer(forca);
			Integer dano = (forc / a.getResistencia()) < 1 ? 1 : (forc / a
					.getResistencia());
			
			Integer danoTotal = (int) (dano + multi - a.getIventorio().getArmaduras()[0].getReducao() + ((Math
					.random() * 4) + 1));
			System.out.println("Dano: " + danoTotal);
			a.setHp(a.getHp() - danoTotal);
			System.out.println("Vida: " + a.getHp() + "%");
			persistirLutador(a);
			if (a.getHp() <= 0) {
				System.out.println("fim da luta");
			} else {
				System.out.println("proximo");
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void criarClasses() {
		ClasseLutadora guerreiro = new ClasseLutadora("Guerreiro", 6, 2, 2, 6);
		ClasseLutadora duelista = new ClasseLutadora("Duelista", 6, 6, 2, 2);
		ClasseLutadora ninja = new ClasseLutadora("Ninja", 6, 2, 6, 2);
		ClasseLutadora artistaMarcial = new ClasseLutadora("Artista Marcial",
				2, 2, 6, 6);
		ClasseLutadora estudante = new ClasseLutadora("Estudante", 4, 4, 4, 4);
		ClasseLutadora mago = new ClasseLutadora("Mago", 10, 2, 2, 2);
		ClasseLutadora semideus = new ClasseLutadora("Semi Deus", 2, 2, 2, 10);
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		Writer writer = null;
		try {
			File pastas = new File("/classes");
			File pastas2 = new File("/lutadores");
			pastas.mkdirs();
			pastas2.mkdirs();

			File arquivo = new File("/classes/" + guerreiro.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(guerreiro, writer);

			arquivo = new File("/classes/" + duelista.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(duelista, writer);

			arquivo = new File("/classes/" + ninja.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(ninja, writer);

			arquivo = new File("/classes/" + artistaMarcial.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(artistaMarcial, writer);

			arquivo = new File("/classes/" + estudante.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(estudante, writer);

			arquivo = new File("/classes/" + mago.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(mago, writer);

			arquivo = new File("/classes/" + semideus.getNome() + ".xml");
			arquivo.createNewFile();
			writer = new PrintWriter(arquivo);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(semideus, writer);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void criarNovoLutador() {
		System.out.println("Escolha a classe: ");
		System.out.println("Guerreiro: ForÃ§a e ResistÃªncia");
		System.out.println("Duelista: ForÃ§a e Pericia");
		System.out.println("Ninja: ForÃ§a e Agilidade");
		System.out.println("Artista Marcial: Agilidade e ResistÃªncia");
		System.out.println("Estudante: equilibrado");
		System.out.println("Mago: Super forte");
		System.out.println("Semi Deus: Quase imortal");
		Scanner sc = new Scanner(System.in);
		String op = sc.nextLine();

		System.out.println("Digite um nome:");
		Scanner sn = new Scanner(System.in);
		String nome = sc.nextLine();

		XStream xstream = new XStream(new DomDriver("UTF-8"));
		Writer writer = null;
		try {
			File arquivo = new File("/classes/" + op + ".xml");
			FileReader fr = new FileReader(arquivo);
			ClasseLutadora cl = (ClasseLutadora) xstream.fromXML(fr);
			Lutador l = new Lutador(nome, cl, new Integer("1"),
					new Integer("1"), new Integer("1"), new Integer("1"));

			File perArq = new File("C:/co1/lutadores/" + nome + ".xml");
			// FileOutputStream fos = new FileOutputStream(perArq);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			writer = new PrintWriter(perArq);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(l, writer);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			Logger.getLogger(CC3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

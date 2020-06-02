/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cb2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
public class CB2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            Scanner sc = new Scanner(System.in);
            Boolean atacante = null;
            CB2 a = new CB2();
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
                File perArq = new File("c:/co1/lutadores/" + l + ".xml");
                fr = new FileReader(perArq);
                Lutador lutador = (Lutador) xstream.fromXML(fr);
                menuGeral(a, lutador);


            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public static void menuGeral(CB2 a, Lutador lutador) {
        Scanner sc;
        int opt;
        System.out.println("Digite um comando");
        System.out.println("1 - Atacar:");
        System.out.println("2 - Defender:");
        System.out.println("3 - Poderes:");
        System.out.println("4 - Defesas:");
        System.out.println("5 - Status:");
        sc = new Scanner(System.in);
        opt = sc.nextInt();
        if (opt == 1) {
            menuAtaque(a, lutador);
        } else if (opt == 2) {
            menuDefesa(a, lutador);
        } else if (opt == 3) {
            a.menuPoderes(lutador);
        } else if(opt == 4){
            try {
                a.menuDefesas(lutador);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(opt == 5){
            System.out.println(lutador.displayStatus());
        }
    }

    public static void menuDefesa(CB2 a, Lutador lutador) {
        Scanner sc;
        System.out.println("Digite o ataque do adversário: ");
        sc = new Scanner(System.in);
        String atk = sc.nextLine();
        a.calcularDefesa(atk, lutador);


    }

    public static void menuAtaque(CB2 a, Lutador lutador) {
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
                System.out.println((i + 1) + " - " + lutador.getPoderes().get(i).toString());
            }
            sc = new Scanner(System.in);
            int p = sc.nextInt();
            usarPoder(p, lutador);
        }
    }

    public void menuDefesas(Lutador lutador) throws FileNotFoundException, IOException {
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
            if(lutador.getDefesas() == null){
                lutador.setDefesas(new ArrayList<Defesa>());
                persistirLutador(lutador);
            }
            for (Defesa object : lutador.getDefesas()) {
                System.out.println(object.toString());
            }
        } else if (opt == 3) {
            for (int i = 0; i < lutador.getDefesas().size(); i++) {
                System.out.println((i + 1) + " - " + lutador.getDefesas().get(i).toString());
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
                    p = new Poder(nome, 1, 1, nivel, 7, 2);
                } else if (opt == 3) {
                    p = new Poder(nome, 1, 1, nivel, 2, 7);
                }
                List<Poder> ps = lutador.getPoderes();
                if (ps == null) {
                    ps = new ArrayList<Poder>();
                }
                ps.add(p);
                lutador.setPoderes(ps);
                XStream xstream = new XStream(new DomDriver());
                File perArq = new File("c:/co1/lutadores/" + lutador.getNome() + ".xml");
                writer = new PrintWriter(perArq);
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
                xstream.toXML(lutador, writer);
                System.out.println("Poder salvo com sucesso!");
            } else {
                System.out.println("Nivél máximo 5 e mínimo 1!");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
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
                    p = new Defesa(nome, 1, 1, nivel, 2, 7);
                } else if (opt == 3) {
                    p = new Defesa(nome, 1, 1, nivel, 7, 2);
                }
                List<Defesa> ps = lutador.getDefesas();
                if (ps == null) {
                    ps = new ArrayList<Defesa>();
                }
                ps.add(p);
                lutador.setDefesas(ps);
                XStream xstream = new XStream(new DomDriver());
                File perArq = new File("c:/co1/lutadores/" + lutador.getNome() + ".xml");
                writer = new PrintWriter(perArq);
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
                xstream.toXML(lutador, writer);
                System.out.println("Poder salvo com sucesso!");
            } else {
                System.out.println("Nivél máximo 5 e mínimo 1!");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void evolucao(Lutador a) {
        FileOutputStream fos = null;
        try {
            Random r = new Random();
            Integer cf = a.getCl().getForca();
            Integer rolagem = r.nextInt(101);
            System.out.println("Rolagem Força: " + rolagem);
            if (rolagem <= cf) {
                a.setForca(a.getForca() + 1);
                System.out.println(a.getNome() + " aumentou sua força");
            }
            Integer cp = a.getCl().getPericia();
            rolagem = r.nextInt(101);
            System.out.println("Rolagem Perícia: " + rolagem);
            if (rolagem <= cp) {
                a.setPericia(a.getPericia() + 1);
                System.out.println(a.getNome() + " aumentou sua perícia");
            }
            Integer ca = a.getCl().getAgilidade();
            rolagem = r.nextInt(101);
            System.out.println("Rolagem Agilidade: " + rolagem);
            if (rolagem <= ca) {
                a.setAgilidade(a.getAgilidade() + 1);
                System.out.println(a.getNome() + " aumentou sua agilidade");
            }
            Integer cr = a.getCl().getResistencia();
            rolagem = r.nextInt(101);
            System.out.println("Rolagem Resistência: " + rolagem);
            if (rolagem <= cr) {
                a.setResistencia(a.getResistencia() + 1);
                System.out.println(a.getNome() + " aumentou sua resistência");
            }
            persistirLutador(a);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void persistirLutador(Lutador a) throws IOException, FileNotFoundException {
        XStream xstream = new XStream(new DomDriver());
        File perArq = new File("c:/co1/lutadores/" + a.getNome() + ".xml");
        Writer writer = new PrintWriter(perArq);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xstream.toXML(a, writer);
    }

    public void atacar(Lutador a) {
        try {
            if (a.getPowerUp() == null) {
                a.setPowerUp(0);
            }
            Random r = new Random();
            Integer pp = + r.nextInt(3) + 1;
            System.out.println("PowerUP: " + pp);
            a.setPowerUp(a.getPowerUp() + pp);
            evolucao(a);
            persistirLutador(a);
            System.out.println(a.getPericia() + "/" + a.getForca() + "/ataque");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void usarPoder(Integer poder, Lutador a) {
        Poder p = a.getPoderes().get(poder - 1);
        if (a.getPowerUp() <= p.getNivel()) {
            a.setPowerUp(a.getPowerUp() - p.getNivel());
            System.out.println("Powerup - " + p.getNivel() + " = " + a.getPowerUp());
            Integer pericia = a.getPericia() + (p.getPericia() * p.getNivel());
            Integer forca = a.getForca() + (p.getForca() * p.getNivel());
            System.out.println(pericia + "/" + forca + "/" + p.getNome());
            evolucaoPoder(poder, a);
            evolucao(a);
        } else {
            System.out.println("Você não pode usar este poder!");
            menuGeral(this, a);
        }
    }

    public void usarDefesa(Integer defesa, Lutador a, String ataque) throws FileNotFoundException, IOException {
        Defesa p = a.getDefesas().get(defesa - 1);
        if (a.getDefenseUp() <= p.getNivel()) {
            a.setDefenseUp(a.getDefenseUp() - p.getNivel());
            System.out.println("DefenseUp - " + p.getNivel() + " = " + a.getDefenseUp());
            Integer agilidade = a.getAgilidade() + (p.getAgilidade() * p.getNivel());
            Integer resistencia = a.getResistencia() + (p.getResistencia() * p.getNivel());

            String[] atks = ataque.split("/");
            Integer per = new Integer(atks[0]);
            Integer chance = (per / agilidade) * 50;
            Random r = new Random();
            Integer rolagem = r.nextInt(101);
            if (chance > 95) {
                chance = 95;
            } else if (chance < 5) {
                chance = 5;
            }
            System.out.println("Ataque:" + atks[2]);
            System.out.println("Chance: " + chance);
            System.out.println("Rolagem: " + rolagem);
            evolucao(a);
            if (rolagem < chance) {
                System.out.println("Acertou");
                Integer dano = new Integer(atks[1]) / resistencia;
                System.out.println("Dano: " + dano);
                a.setHp(a.getHp() - dano);
                persistirLutador(a);
            } else {
                System.out.println("Errou");
            }
            //System.out.println(agilidade + "/" + forca + "/" +p.getNome());
            //evolucaoPoder(poder, a);
            evolucao(a);
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
            System.out.println("Poder: " + p.getNome() + " aumentou em força!");
        }
        if (cp <= p.getChancePericia()) {
            p.setPericia(p.getPericia() + 1);
            System.out.println("Poder: " + p.getNome() + " aumentou em perícia!");
        }

    }
    
    public void evolucaoDefesa(Integer defesa, Lutador a) {
        Defesa p = a.getDefesas().get(defesa - 1);
        Random r = new Random();
        int cf = r.nextInt(101);
        int cp = r.nextInt(101);
        if (cf <= p.getChanceAgilidade()) {
            p.setAgilidade(p.getAgilidade() + 1);
            System.out.println("Poder: " + p.getNome() + " aumentou em força!");
        }
        if (cp <= p.getChanceResistencia()) {
            p.setResistencia(p.getResistencia() + 1);
            System.out.println("Poder: " + p.getNome() + " aumentou em perícia!");
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
        System.out.println("O atacante usou: " + atkS[2]);
        Integer per = new Integer(atkS[0]);
        Integer chance = (per / a.getAgilidade()) * 50;
        Random r = new Random();
        Integer rolagem = r.nextInt(101);
        if (chance > 95) {
            chance = 95;
        } else if (chance < 5) {
            chance = 5;
        }
        System.out.println("Chance: " + chance);
        System.out.println("Rolagem: " + rolagem);
        Integer dp = r.nextInt(3) + 1;
        System.out.println("DefenseUP: +" + dp);
        a.setDefenseUp(a.getDefenseUp() + dp);
        evolucao(a);
        if (rolagem < chance) {
            System.out.println("Acertou");
            calcularDano(atkS[1], a);
        } else {
            System.out.println("Errou");
        }

    }

    public void calcularDano(String forca, Lutador a) {
        Writer writer = null;
        try {
            Integer forc = new Integer(forca);
            Integer dano = (forc / a.getResistencia());
            System.out.println("Dano: " + dano);
            a.setHp(a.getHp() - dano);
            File perArq = new File("c:/co1/lutadores/" + a.getNome() + ".xml");
            writer = new PrintWriter(perArq);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            XStream xstream = new XStream(new DomDriver());
            xstream.toXML(a, writer);
            if (a.getHp() <= 0) {
                System.out.println("fim da luta");
            } else {
                System.out.println("proximo");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criarClasses() {
        ClasseLutadora guerreiro = new ClasseLutadora("Guerreiro", 5, 1, 1, 5);
        ClasseLutadora duelista = new ClasseLutadora("Duelista", 5, 5, 1, 1);
        ClasseLutadora ninja = new ClasseLutadora("Ninja", 5, 1, 5, 1);
        ClasseLutadora artistaMarcial = new ClasseLutadora("Artista Marcial", 1, 1, 5, 5);
        ClasseLutadora estudante = new ClasseLutadora("Estudante", 3, 3, 3, 3);
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        Writer writer = null;
        try {
            File pastas = new File("c:/co1/classes");
            File pastas2 = new File("c:/co1/lutadores");
            pastas.mkdirs();
            pastas2.mkdirs();

            File arquivo = new File("c:/co1/classes/" + guerreiro.getNome() + ".xml");
            arquivo.createNewFile();
            writer = new PrintWriter(arquivo);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(guerreiro, writer);

            arquivo = new File("c:/co1/classes/" + duelista.getNome() + ".xml");
            arquivo.createNewFile();
            writer = new PrintWriter(arquivo);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(duelista, writer);

            arquivo = new File("c:/co1/classes/" + ninja.getNome() + ".xml");
            arquivo.createNewFile();
            writer = new PrintWriter(arquivo);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(ninja, writer);

            arquivo = new File("c:/co1/classes/" + artistaMarcial.getNome() + ".xml");
            arquivo.createNewFile();
            writer = new PrintWriter(arquivo);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(artistaMarcial, writer);

            arquivo = new File("c:/co1/classes/" + estudante.getNome() + ".xml");
            arquivo.createNewFile();
            writer = new PrintWriter(arquivo);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(estudante, writer);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criarNovoLutador() {
        System.out.println("Escolha a classe: ");
        System.out.println("Guerreiro: Força e Resistência");
        System.out.println("Duelista: Força e Pericia");
        System.out.println("Ninja: Força e Agilidade");
        System.out.println("Artista Marcial: Agilidade e Resistência");
        System.out.println("Estudante: equilibrado");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();

        System.out.println("Digite um nome:");
        Scanner sn = new Scanner(System.in);
        String nome = sc.nextLine();

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        Writer writer = null;
        try {
            File arquivo = new File("c:/co1/classes/" + op + ".xml");
            FileReader fr = new FileReader(arquivo);
            ClasseLutadora cl = (ClasseLutadora) xstream.fromXML(fr);
            Lutador l = new Lutador(nome, cl, new Integer("1"), new Integer("1"), new Integer("1"), new Integer("1"));

            File perArq = new File("c:/co1/lutadores/" + nome + ".xml");
            //FileOutputStream fos = new FileOutputStream(perArq);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            writer = new PrintWriter(perArq);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            xstream.toXML(l, writer);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(CB2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

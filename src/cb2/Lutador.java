/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cb2;

import itens.Iventario;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrador
 */
public class Lutador {
    
    private String nome;
    
    private ClasseLutadora cl;
    
    private Integer forca;
    
    private Integer pericia;
    
    private Integer agilidade;
    
    private Integer resistencia;
    
    private Integer powerUp;
    
    private Integer defenseUp;
    
    private Double hp;
    
    private Integer quantidadeDeLutas;
    
    private Boolean atacando;
    
    private List<Poder> poderes;
    
    private List<Defesa> defesas;
    
    private Iventario iventorio;

    public Lutador() {
    }

    public Lutador(String nome, ClasseLutadora cl, Integer forca, Integer pericia, Integer agilidade, Integer resistencia) {
        this.nome = nome;
        this.cl = cl;
        this.forca = forca;
        this.pericia = pericia;
        this.agilidade = agilidade;
        this.resistencia = resistencia;
        this.hp = 100.00;
        this.atacando = false;
        this.powerUp = 1;
        this.defenseUp = 1;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public ClasseLutadora getCl() {
        return cl;
    }

    public void setCl(ClasseLutadora cl) {
        this.cl = cl;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPericia() {
        return pericia;
    }

    public void setPericia(Integer pericia) {
        this.pericia = pericia;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }

    public Integer getQuantidadeDeLutas() {
        return quantidadeDeLutas;
    }

    public void setQuantidadeDeLutas(Integer quantidadeDeLutas) {
        this.quantidadeDeLutas = quantidadeDeLutas;
    }

    public Boolean getAtacando() {
        return atacando;
    }

    public void setAtacando(Boolean atacando) {
        this.atacando = atacando;
    }

    public List<Poder> getPoderes() {
        return poderes;
    }

    public void setPoderes(List<Poder> poderes) {
        this.poderes = poderes;
    }

    public Integer getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(Integer powerUp) {
        this.powerUp = powerUp;
    }

    public Integer getDefenseUp() {
        return defenseUp;
    }

    public void setDefenseUp(Integer defenseUp) {
        this.defenseUp = defenseUp;
    }

    public List<Defesa> getDefesas() {
        return defesas;
    }

    public void setDefesas(List<Defesa> defesas) {
        this.defesas = defesas;
    }
    
    public Iventario getIventorio() {
		return iventorio;
	}

	public void setIventorio(Iventario iventorio) {
		this.iventorio = iventorio;
	}

	public String displayStatus(){
        StringBuilder sb = new StringBuilder();
        char[] sinal = {new Character('+')};
        sb.append("\r\n");
        sb.append("+"+StringUtils.rightPad("Status", 80, "-")+"+");
        sb.append("\r\n");
        sb.append("+Nome........: " + this.nome );
        //sb.insert(80, sinal);
        sb.append("\t\t\t");
        sb.append("+Classe......: " + this.cl.getNome());
        //sb.insert(80, sinal);
        sb.append("\r\n");
        sb.append("+Forca.......: " + this.forca);
        //sb.insert(80, sinal);
        sb.append("\t\t\t");
        sb.append("+Pericia.....: " + this.pericia);
       // sb.insert(80, sinal);
        sb.append("\r\n");
        sb.append("+Agilidade...: " + this.agilidade);
       // sb.insert(80, sinal);
        sb.append("\t\t\t");
        sb.append("+Resistencia.: " + this.resistencia);
        //sb.insert(80, sinal);
        
        sb.append("\r\n");
        sb.append("+PUP.........: " + this.powerUp);
        
        sb.append("\t\t\t");
        sb.append("+DUP.........: " + this.defenseUp);
        
        sb.append("\r\n");
        sb.append("+"+StringUtils.rightPad("Poderes", 80, "-")+"+");
        sb.append("\r\n");
        for (Poder p : this.poderes) {
            sb.append(p.toString());
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append("+"+StringUtils.rightPad("Defesas", 80, "-")+"+");
        sb.append("\r\n");
        for (Defesa d : this.defesas) {
            sb.append(d.toString());
            sb.append("\r\n");
        }
        sb.append("+"+StringUtils.rightPad("Iventorio", 80, "-")+"+");
        sb.append("Arma: " + this.iventorio.getArmas()[0].toString()+ "\r\n");
        sb.append("Armadura: " + this.iventorio.getArmaduras()[0].toString()+"\r\n");
        sb.append("Acessorio: " + this.iventorio.getAcessorios()[0].toString()+"\r\n");
        return sb.toString();
    }
    
}

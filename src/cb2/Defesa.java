/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cb2;

/**
 *
 * @author floriano
 */
public class Defesa {
    
    private String nome;
    
    private Integer agilidade;
    
    private Integer resistencia;
    
    private Integer nivel;
    
    private Integer chanceAgilidade;
    
    private Integer chanceResistencia;

    public Defesa() {
    }

    public Defesa(String defesa, Integer agilidade, Integer resistencia, Integer nivel, Integer chanceAgilidade, Integer chanceResistencia) {
        this.nome = defesa;
        this.agilidade = agilidade;
        this.resistencia = resistencia;
        this.nivel = nivel;
        this.chanceAgilidade = chanceAgilidade;
        this.chanceResistencia = chanceResistencia;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getChanceAgilidade() {
        return this.nivel > 1 ? chanceAgilidade * this.nivel  : chanceAgilidade;
    }

    public void setChanceAgilidade(Integer chanceAgilidade) {
        this.chanceAgilidade = chanceAgilidade;
    }

    public Integer getChanceResistencia() {
        return this.nivel > 1 ?  chanceResistencia * this.nivel  : chanceResistencia;
    }

    public void setChanceResistencia(Integer chanceResistencia) {
        this.chanceResistencia = chanceResistencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }

    @Override
    public String toString() {
        return "Defesa{" + "defesa=" + nome + ", agilidade=" + agilidade + ", resistencia=" + resistencia + ", nivel=" + nivel + ", chanceAgilidade=" + chanceAgilidade + ", chanceResistencia=" + chanceResistencia + '}';
    }
    
    
    
}

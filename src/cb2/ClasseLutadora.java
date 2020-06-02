/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cb2;

import itens.Iventario;

/**
 *
 * @author Administrador
 */
public class ClasseLutadora {
    
    private String nome;
    
    private Integer forca;
    
    private Integer pericia;
    
    private Integer agilidade;
    
    private Integer resistencia;
    
   

    public ClasseLutadora() {
    }

    public ClasseLutadora(String nome, Integer forca, Integer pericia, Integer agilidade, Integer reistencia) {
        this.nome = nome;
        this.forca = forca;
        this.pericia = pericia;
        this.agilidade = agilidade;
        this.resistencia = reistencia;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
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
    
    
    
    
}

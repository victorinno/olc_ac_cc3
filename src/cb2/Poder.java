/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cb2;

/**
 *
 * @author floriano
 */
public class Poder {
    
    private String nome;
    
    private Integer forca;
    
    private Integer pericia;
    
    private Integer nivel;
    
    private Integer chanceForca;
    
    private Integer chancePericia;

    public Poder() {
    }

    public Poder(String nome, Integer forca, Integer pericia, Integer nivel, Integer chanceForca, Integer chancePericia) {
        this.nome = nome;
        this.forca = forca;
        this.pericia = pericia;
        this.nivel = nivel;
        this.chanceForca = chanceForca;
        this.chancePericia = chancePericia;
    }

    public Integer getChanceForca() {
        return this.nivel > 1 ? chanceForca * this.nivel : chanceForca;
    }

    public void setChanceForca(Integer chanceForca) {
        this.chanceForca = chanceForca;
    }

    public Integer getChancePericia() {
        return this.nivel > 1 ? chancePericia * this.nivel  : chancePericia;
    }

    public void setChancePericia(Integer chancePericia) {
        this.chancePericia = chancePericia;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
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

    @Override
    public String toString() {
        return "Poder{" + "nome=" + nome + ", forca=" + forca + ", pericia=" + pericia + ", nivel=" + nivel + ", chanceForca=" + chanceForca + ", chancePericia=" + chancePericia + '}';
    }
    
    public String displayPoder(){
        StringBuilder sb = new StringBuilder();
        String linha = "+------------------------------------------------------------------------------+";
        Integer tamanhoNome = this.nome.length();
        String regex = "";
        StringBuilder sb1 = new StringBuilder();
        sb1.append("[");
        for (int i = 0; i < tamanhoNome; i++) {
            sb1.append("-");
        }
        sb1.append("]");
        regex = sb1.toString();
        String linhaNome = "+Poder:-----------------------------------------------------------------+";
        linhaNome = linhaNome.replaceFirst(regex, this.nome);
        sb.append("\n");
        sb.append(linhaNome);
        sb.append("+Nivel....: " + this.nivel);
        sb.append("\n");
        sb.append("+Foca.....: " + this.forca);
        sb.append("\n");
        sb.append("+Pericia..: " + this.pericia);
        sb.append("\n");
        sb.append(linha);
        return sb.toString();
    }
    
}

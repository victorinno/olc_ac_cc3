package itens;

import cb2.Lutador;

public abstract class Item {
	
	protected String nome;
	protected TipoItem tipoItem;

	protected Integer ataque;
	protected Integer defesa;
	protected Integer dano;
	protected Integer reducao;
	protected Double pup;
	protected Double dup;

	protected Integer cataque;
	protected Integer cdefesa;
	protected Integer cdano;
	protected Integer creducao;
	protected Integer cpup;
	protected Integer cdup;

	public Item() {

	}

	public Item(Integer cataque, Integer cdefesa, Integer cdano,
			Integer creducao, Integer cpup, Integer cdup) {
		super();
		this.ataque = 1;
		this.defesa = 1;
		this.dano = 1;
		this.reducao = 1;
		this.pup = 0.01;
		this.dup = 0.01;
		this.cataque = cataque;
		this.cdefesa = cdefesa;
		this.cdano = cdano;
		this.creducao = creducao;
		this.cpup = cpup;
		this.cdup = cdup;
	}
	
	public int pup(){
		double c = Math.random() * 100;
		if (c < this.pup) {
			return 1;
		}
		return 0;
	}
	
	public int dup(){
		double c = Math.random() * 100;
		if (c < this.dup) {
			return 1;
		}
		return 0;
	}

	public Item(String nome,Integer ataque, Integer defesa, Integer dano, Integer reducao,
			Double pup, Double dup, Integer cataque, Integer cdefesa,
			Integer cdano, Integer creducao, Integer cpup, Integer cdup) {
		super();
		this.nome = nome;
		this.ataque = ataque;
		this.defesa = defesa;
		this.dano = dano;
		this.reducao = reducao;
		this.pup = pup;
		this.dup = dup;
		this.cataque = cataque;
		this.cdefesa = cdefesa;
		this.cdano = cdano;
		this.creducao = creducao;
		this.cpup = cpup;
		this.cdup = cdup;
	}



	public void ativar(Lutador perso) {
		rolarCataque();
		rolarCdefesa();
		rolarCdano();
		rolarCreducao();
		rolarCdup();
		rolarCpup();
		
	}

	protected void rolarCataque(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.cataque) {
			this.ataque += 1;
			System.out.println(this.nome + " aumentou o ataque!");
		}
	}
	
	protected void rolarCdefesa(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.cdefesa) {
			this.defesa += 1;
			System.out.println(this.nome + " aumentou a defesa!");
		}
	}
	
	protected void rolarCdano(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.cdano) {
			this.dano += 1;
			System.out.println(this.nome + " aumentou o dano!");
		}
	}
	
	protected void rolarCreducao(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.creducao) {
			this.reducao += 1;
			System.out.println(this.nome + " aumentou a reducao!");
		}
	}
	
	protected void rolarCdup(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.cdup) {
			this.dup += 0.01;
			System.out.println(this.nome + " aumentou o defense up!");
		}
	}
	
	protected void rolarCpup(){
		int c = (int) ((Math.random() * 100) + 1);
		if (c <= this.cpup) {
			this.pup += 0.01;
			System.out.println(this.nome + " aumentou o power up!");
		}
	}

	@Override
	public String toString() {
		return "Item [nome=" + nome + ", ataque=" + ataque + ", defesa="
				+ defesa + ", dano=" + dano + ", reducao=" + reducao
				+ ", cataque=" + cataque + ", cdefesa=" + cdefesa + ", cdano="
				+ cdano + ", creducao=" + creducao + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getDano() {
		return dano;
	}

	public void setDano(Integer dano) {
		this.dano = dano;
	}

	public Integer getReducao() {
		return reducao;
	}

	public void setReducao(Integer reducao) {
		this.reducao = reducao;
	}

	public Double getPup() {
		return pup;
	}

	public void setPup(Double pup) {
		this.pup = pup;
	}

	public Double getDup() {
		return dup;
	}

	public void setDup(Double dup) {
		this.dup = dup;
	}

	public Integer getCataque() {
		return cataque;
	}

	public void setCataque(Integer cataque) {
		this.cataque = cataque;
	}

	public Integer getCdefesa() {
		return cdefesa;
	}

	public void setCdefesa(Integer cdefesa) {
		this.cdefesa = cdefesa;
	}

	public Integer getCdano() {
		return cdano;
	}

	public void setCdano(Integer cdano) {
		this.cdano = cdano;
	}

	public Integer getCreducao() {
		return creducao;
	}

	public void setCreducao(Integer creducao) {
		this.creducao = creducao;
	}

	public Integer getCpup() {
		return cpup;
	}

	public void setCpup(Integer cpup) {
		this.cpup = cpup;
	}

	public Integer getCdup() {
		return cdup;
	}

	public void setCdup(Integer cdup) {
		this.cdup = cdup;
	}
	
	
	
}

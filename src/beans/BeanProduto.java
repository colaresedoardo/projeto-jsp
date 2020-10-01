package beans;

public class BeanProduto {
	private Long id;
	private String nome;
	private double qtd;
	private double preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		if(qtd > 0) {
			this.qtd = qtd;	
		}
		
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if(preco > 0) {
			this.preco = preco;	
		}
		
	}

}

abstract class Variaveis{
	
	abstract public void Varstring();
	abstract public void Varint();
	abstract public void Vardouble();
	
	private String nome;
	private double valor;
	
	public Variaveis(String n, double v){
		this.valor = v;
		this.nome = n;
	}
	public Variaveis(String n){
		this.nome = n;
		this.valor = 0;
	}

	public String getNome(){
		return this.nome;
	}
	public double getValor(){
		return this.valor;
	}
	public void setNome(String n){
		this.nome = n;
	}
	public void setValor(double a){
		this.valor = a;
	}
	

}

class Variaveis{
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
	
	
	// criar um metodo para retornar se existe a variavel, nao precisando toda vez buscar...
	
	
	
	
	
	//public boolean TestaVariavel(String s, Variaveis[] v){
	//	for(int i = 0; v[i] != null; i++){
	//		if(v[i].nome.equals(s)) return false;
	//	}
	//	return true;
	//}


}

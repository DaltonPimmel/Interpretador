class Variaveis{
	private String nome;
	private double valor;
	
	public void CriarVariavel(String n, int v){
		this.valor = v;
		this.nome = n;
	}
	public void CriarVariavel(String n){
		this.nome = n;
		this.valor = 0;
	}

	public String getNome(){
		return this.nome;
	}
	public double getValor(){
		return this.valor;
	}
	public boolean TestaVariavel(String s, Variaveis[] v){
		for(int i = 0; v[i] != null; i++){
			if(v[i].nome.equals(s)) return false;
		}
		return true;
	}


}

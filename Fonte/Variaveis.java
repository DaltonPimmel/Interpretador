//Dalton Luiz Pimmel
//dalton.cco1@gmail.com]

// Classe Variaveis, possui o nome, o tipo e o valor quando na declaração de uma variavel.

public class Variaveis{
	
	
	private String nome;
	private String tipo;
	private Object valor;
	
	public void setNome(String n){
		this.nome = n;
	}
	public void setTipo(String t){
		this.tipo = t;
	}
	public void setValor(Object v){
		this.valor = v;
	}
	
	public String getNome(){
		return this.nome;
	}
	public String getTipo(){
		return this.tipo;
	}
	public Object getValor(){
		return this.valor;
	}

}

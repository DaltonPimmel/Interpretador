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

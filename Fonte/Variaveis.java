public abstract class Variaveis{
	
	
	public abstract VarInt Varint(String n, int b, String t);
	public abstract VarInt Vardouble(String f, double v, String t);
	public abstract VarInt VarString(String n, String a, String t);
	
	
	private String nome;
	private double Vdouble;
	private int Vint;
	private double valor;
	private String Vstring;
	private String tipo;
	
	public void setInt(String n, int v, String t){ // cria variavel do tipo int.
		this.Vint = v;
		this.nome = n;
		this.tipo = t;
	}
	public void setString(String n, String a, String t){
		this.nome = n;
		this.Vstring = a;
		this.tipo = t;
	}
	public void setDouble(String a, double b, String t){ // cria variavel do tipo double.
		this.nome = a;
		this.Vdouble = b;
		this.tipo = t;
	}

	public String getNome(){ // retorna o nome da variavel
		return this.nome;
	}
	//public double getValor(){
	//	return this.valor;
	//}
	public int getVint(){ // retorna o valor da variavel do tipo int.
		return this.Vint;
	}
	public String getTipo(){ // retorna o tipo da variavel.
		return this.tipo;
	}
	public String getVstring(){ // retorna o valor da variavel do tipo String.
		return this.Vstring;
	}
	public void setNome(String n){ 
		this.nome = n;
	}
	public void setValor(double a){ 
		this.valor = a;
	}
	public double getVdouble(){ // retorna o valor da variavel do tipo double.
		return this.Vdouble;
	}
	

}

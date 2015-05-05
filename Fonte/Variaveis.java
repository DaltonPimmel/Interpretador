public abstract class Variaveis{
	
	//public  void Varstring();
	public abstract VarInt Varint(String n, int b, String t);
	//public  void Vardouble();
	
	private String nome;
	private double Vdouble;
	private int Vint;
	private double valor;
	private String Vstring;
	private String tipo;
	
	public void setInt(String n, int v, String t){
		this.Vint = v;
		this.nome = n;
		this.tipo = t;
	}
	public void setString(String n){
		this.nome = n;
		this.valor = 0;
	}
	public void setDouble(String a, double b){
		this.nome = a;
		this.valor = b;
	}

	public String getNome(){
		return this.nome;
	}
	public double getValor(){
		return this.valor;
	}
	public int getVint(){
		return this.Vint;
	}
	public String getTipo(){
		return this.tipo;
	}
	public String getVstring(){
		return this.Vstring;
	}
	public void setNome(String n){
		this.nome = n;
	}
	public void setValor(double a){
		this.valor = a;
	}
	

}

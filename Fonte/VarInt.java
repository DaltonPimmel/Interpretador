// verificar ...

class VarInt extends Variaveis{
		
	public VarInt Varint(String n, int b, String t){
		VarInt c = new VarInt();
		c.setInt(n ,b, t); // criacao de variavel do tipo int.
		return c;
	}
	
	
	public VarInt Vardouble(String f, double v, String t){
		VarInt g = new VarInt();
		g.setDouble(f, v, t);
		return g;		
	}
	
	public VarInt VarString(String n, String b, String t){
		VarInt a = new VarInt();
		a.setString(n, b, t);
		return a;
		
	}

}

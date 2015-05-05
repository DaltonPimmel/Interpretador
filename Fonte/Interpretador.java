

class Interpretador{
	
	
	
	Declaracao d;
	Variaveis[] v;
	Imprime im;
	Operacoes op;
	InicioFim t;
	Comentarios com;
	CondicaoSe se;
	Logico log;
	Enquanto enq;
	LerTeclado ler;
	VarInt varint;
	//VarDouble vardouble;
	//VarString varstring;
	
	private boolean con = true, verdadeiro = false, condd = false;
	private int cond, p;
	
	public Interpretador(){
		this.d = new Declaracao(this);
		this.v = new Variaveis[1000];
		this.im = new Imprime(this);
		this.op = new Operacoes();
		this.t = new InicioFim(this);
		this.com = new Comentarios();
		this.se = new CondicaoSe(this);
		this.log = new Logico();
		//this.enq = new Enquanto(this);
		this.ler = new LerTeclado(this);
		varint = new VarInt();
		//vardouble = new VarDouble();
		//varstring = new VarString();
	}
	
    public void interpreta(String l[]) {	
		Interpretador in = new Interpretador();
		int Lfim = 0;
		
		while(con){  // testa se avhou o inicio e o fim do programa.
			Lfim = t.Comeco(l);
			con = false;
		}
		
		String[] tok;
		String a;
		for(int cont = 0; cont <= l.length && l[cont] != null; cont++){ 
			l[cont] = l[cont].trim();
			if(l[cont].length() > 1 && l[cont] != null){
				l[cont] = com.Comentario(l[cont]);
				if(l[cont].contains("//")) continue;
				tok = l[cont].trim().split(" ");
				a = tok[0];
				
				switch(a){
					
					case "inteiro":	
						d.Declarar(tok);
					break;
					
					case "imprime":
						im.Imprimir(tok);
					break;
						
					case "se":
						verdadeiro = true; // verifica se pode utilizar o senao.
						cond = cont;
						cont = se.Se(l, cont, Lfim);
						if(cont == 0){
							System.out.println("Problema na hora de utilizar se na linha : " + (cond + 1));
							System.exit(0);
						}
					break;
					
					case "senao":
						if(verdadeiro){ // se verdadeiro for treu, pode-se utilizar o senao
							cont = se.Se(l, cont, Lfim);
							verdadeiro = false;
						}else{
							System.out.println("Problema na hora de utilizar o senao, nao he posivel utilizar antes do se : Linha : " + (cond + 1));
							System.exit(0);
						}
						if(cont == 0){
							System.out.println("erroooooooo no senao");
							System.exit(0);
						}//else{
						//	System.out.println("Imposivel usuar o senao antes do se dsddsdsdsdsdeded");
						//	System.exit(0);
						//}
					break;
					
					case "enquanto":
						 //p = cont;
						//if(enq.Enquan(l, cont)) condd = true;
						//else{ // se retornar falso, pula as linhas até a achar o fim enquanto.
						//	while(!l[cont].equals("fim enquanto")){
								//cont++;
						//	}
						//}
					break;
					
					case "fim":
					//	if(l[cont].equals("fim enquanto")){
					//		if(condd) cont = p; // se for verdadeiro retorna aonde achou o enquanto.
					//		continue;
					//	}
					break;
					
					case "leia":
						if(ler.Leia(tok, cont));
					break;
					
					default:
						if(l[cont].equals("inicio programa()") || l[cont].equals("fim se") || l[cont].equals("fim senao") || l[cont].equals("fim enquanto") || l[cont].equals("fim programa")) continue;
						d.Declarar(tok);
					
					break;
					
				}
			}
			
		}
	}
	
	
	public boolean VerificaVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return true;
		}
		return false;
	}
	
	public double getValor(String n){ 
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)){
				if(v[i].getTipo().equals("inteiro")) return v[i].getVint();
				if(v[i].getTipo().equals("double")) return v[i].getValor();
				//if(v[i].getNome().equals("string")) return v[i].getVstring();
			}
		}
		return 0;
	}
	
	// testa se existe a variavel e retorna a posicao do vetor.
	public int getVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return i; 
		}
		return 1000;
	}
	// retorna a primeira posicao de v que esta null para criar a variavel, se nao estiver mais espaço retorna 1000.
	public boolean CriarVariavelInt(String n, int a, String tipo){
		//System.out.println(a);
		for(int i = 0; i < v.length; i++){
			if(v[i] == null){
				if(tipo.equals("inteiro")){
					v[i] = new VarInt();
					v[i] = varint.Varint(n, a, tipo);
					//v[i] = varint.Varint(n, a);
					//System.out.println(v[i].getVint());
					//System.out.println(v[i].getValor());
					return true;
				}
			}
		}
		return false;
	}
	
	// teste se é um numero ou um digito
	public boolean TestaString(String s){  
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  
        }  
        return true;  
    }
    
    // testa se a variavel é do tipo inteira na hora da declaração.
    public boolean isInt(String v) {  
    try {  
        Integer.parseInt(v);  
        return true;  
    } catch (Exception e) {  
        return false;  
    }  
}  

	
}
    

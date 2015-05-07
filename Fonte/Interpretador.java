

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
	Erros erro;
	//CondParada conp;
	//VarDouble vard;
	//VarString varstring;
	
	public boolean con = true, verdadeiro = false, condd = false;
	private int cond, p, f;
	
	public Interpretador(){
		this.d = new Declaracao(this);
		this.v = new Variaveis[1000];
		this.im = new Imprime(this);
		this.op = new Operacoes();
		this.t = new InicioFim(this);
		this.com = new Comentarios();
		this.se = new CondicaoSe(this);
		this.log = new Logico();
		this.enq = new Enquanto(this);
		this.ler = new LerTeclado(this);
		this.varint = new VarInt();
		this.erro = new Erros();
		
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
						d.Declarar(tok, cont);
					break;
					
					case "double":
						d.Declarar(tok, cont);
					break;
					
					case "string":
						d.Declarar(tok, cont);
					break;
					
					case "imprime":
						im.Imprimir(tok, cont);
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
							//verdadeiro = false;
						}else{
							System.out.println("111Problema na hora de utilizar o senao, nao he posivel utilizar antes do se : Linha : " + (cond + 1));
							System.exit(0);
						}
						if(cont == 0){
							System.out.println("Nao foi localizado o fim do senao");
							System.exit(0);
						}//else{
						//	System.out.println("Imposivel usuar o senao antes do se dsddsdsdsdsdeded");
						//	System.exit(0);
						//}
					break;
					
					case "enquanto":
						 p = cont;
						 con = true; // o break ou o continue pode ser usado.
						if(enq.Enquan(l, cont)){
							 condd = true;
							 continue;
						 }
						else{ // se retornar falso, pula as linhas até a achar o fim enquanto.
							while(!l[cont].equals("fim enquanto")){
								cont++;
							}
							condd = false;
						}
					break;
					
					case "fim":
						if(l[cont].equals("fim enquanto")){
							con = false;
							if(condd) cont = p - 1; // se for verdadeiro retorna aonde achou o enquanto.
							continue;
						}
					break;
					
					case "leia":
						if(ler.Leia(tok, cont));
					break;
					
					case "break":
						if(!con) System.exit(0);
						while(!l[cont].equals("fim enquanto")){
							cont++;
						}
					break;
					
					case "continue":
						if(!con) System.exit(0);
						cont = p; // aonde começa o enquanto;
					break;
					
					default:
						if(l[cont].equals("inicio programa()") || l[cont].equals("fim se") || l[cont].equals("fim senao") || l[cont].equals("fim enquanto") || l[cont].equals("fim programa")) continue;
						d.Declarar(tok, cont);
					
					break;
					
				}
			}
			
		}
	}
	
	// verifica se a variavel existe.
	public boolean VerificaVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return true;
		}
		return false;
	}
	
	//public double getValor(String n){ 
	//	for(int i = 0; v[i] != null; i++){    // verifcar este metos nas outras classes esta com problema.
	//		if(v[i].getNome().equals(n)){
		//		if(v[i].getTipo().equals("inteiro")) return v[i].getVint();
			//	if(v[i].getTipo().equals("double")) return v[i].getVdouble();
			//	if(v[i].getTipo().equals("string")) return v[i].getVstring();
			//}
		//}
	//	return 0;
//	}
	
	// testa se existe a variavel e retorna a posicao do vetor.
	public int getVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return i; 
		}
		return 1000;
	}
	
	public int PosicaVetor(){
		for(int i = 0; i < v.length; i++){
			if(v[i] == null) return i;
		}
		return 1000;
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
	public boolean isDouble(String n){
		try{
			Double.parseDouble(n);
			return true;
		} catch (Exception e){
			return false;
		}
	}
		
} 

	

    

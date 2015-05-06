

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
	//CondParada conp;
	//VarDouble vard;
	//VarString varstring;
	
	private boolean con = true, verdadeiro = false, condd = false;
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
		//this.conp = new CondParada();
		//this.vard = new VarDouble();
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
						d.Declarar(tok, cont);
					break;
					
					case "double":
						d.Declarar(tok, cont);
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
	
	public double getValor(String n){ 
		for(int i = 0; v[i] != null; i++){    // verifcar este metos nas outras classes esta com problema.
			if(v[i].getNome().equals(n)){
				if(v[i].getTipo().equals("inteiro")) return v[i].getVint();
				if(v[i].getTipo().equals("double")) return v[i].getVdouble();
			//	if(v[i].getTipo().equals("string")) return v[i].getVstring();
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

	public boolean CriarVariavelInt(String[] linhas){
		if(linhas[0].equals("inteiro")){ // criando variavel do tipo inteiro
			if(linhas.length > 3 && linhas.length < 5 && !TestaString(linhas[1]) && linhas[2].equals("=")){  //declaracao com atribuição.
				String nome = linhas[1];
				String tipo = linhas[0];
				if(!isInt(linhas[3])){  // testa se é um numero inteiro.
					System.out.println("Declaracao errada!!!");
					System.exit(0);
				}
				int rec = Integer.parseInt(linhas[3]);
				int a = getVariavel(nome);		
				if(a != 1000){ // se retornar outro valor, a variavel nome ja existe.
					System.out.println("Variavel ja declarada");
					System.exit(0);
				}
				int d = PosicaVetor();
				if(d == 1000) System.exit(0);
				v[d] = new VarInt();
				v[d] = varint.Varint(nome, rec, tipo);
				return true;
			}else if(linhas.length < 3 &&  !TestaString(linhas[1]) ){ // declaração de variavel sem atribução.
				String nome = linhas[1];
				int a = getVariavel(nome);
				if(a != 1000){
					System.out.println("Variavel ja foi declarada " + nome);
					System.exit(0);
				}
				String tipo = linhas[0];
				int d = PosicaVetor();
				if(d == 1000) System.exit(0);
				v[d] = new VarInt();
				v[d] = varint.Varint(nome, 0, tipo);
				return true;
			}
			
		}else if(linhas[0].equals("double")){ // criando variavel do tipo double.
			if(linhas.length > 3 && linhas.length < 5 && !TestaString(linhas[1]) && linhas[2].equals("=")){ 
				String nome = linhas[1];
				String tipo = linhas[0];
				double a = Double.parseDouble(linhas[3]);
				int t = PosicaVetor();
				if(t == 1000) System.exit(0);
				v[t] = new VarInt();
				v[t] = varint.Vardouble(nome, a, tipo);
				return true;
			}else if(linhas.length < 3 && !TestaString(linhas[1])){ // declaração de variavel do tipo double sem atribuição.
				String nome = linhas[1];
				String tipo = linhas[0];
				int t = PosicaVetor();
				if(t == 1000) System.exit(0);
				v[t] = new VarInt();
				v[t] = varint.Vardouble(nome, 0.0, tipo);
				return true;
			}			
		}else if(linhas[0].equals("string")){
			if(linhas.length > 3 && linhas.length < 5 && !TestaString(linhas[1]) && linhas[2].equals("=")){
				String nome = linhas[1];
				String tipo = linhas[0];
				String a = linhas[3];
				int t = PosicaVetor();
				if(t == 1000) System.exit(0);
				v[t] = new VarInt();
				v[t] = varint.VarString(nome, a, tipo);
				return true;
			}
			
		}
		return false;
	
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

	
}
    

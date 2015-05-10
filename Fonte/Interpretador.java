

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
	Erros erro;
	
	public boolean con = true, verdadeiro = false, teste = true;
	private int cond, p, Lfim = 0;
	
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
		this.erro = new Erros();
		
	}
	
    public void interpreta(String l[]) {	
		
		Interpretador in = new Interpretador();
		
		while(con){  // testa se avhou o inicio e o fim do programa.
			Lfim = t.Comeco(l);
			con = false;
		}
		
		String[] tok;
		String a;
		for(int cont = 0; cont <= l.length && l[cont] != null; cont++){ 
			l[cont] = l[cont].trim();
			if(l[cont].length() > 1 && l[cont] != null){
				if(cont > Lfim) erro.Erro10();
				l[cont] = com.Comentario(l[cont]);
				if(l[cont].contains("//")) continue;
				tok = l[cont].trim().split(" ");
				a = tok[0];
				
				switch(a){
					
					case "int":	
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
						if(cont == 0) erro.Erro16(cont);
					break;
					
					case "senao":
						if(verdadeiro) cont = se.Se(l, cont, Lfim); // se verdadeiro for treu, pode-se utilizar o senao	
						else erro.Erro15(cont);
						if(cont == 0) erro.Erro14(cont);		
					break;
					
					case "enquanto":
						if(teste){
							p = cont;
							teste = false;
						}
						cont = enq.Enquan(l, cont);
					break;
					
					case "fim":
						if(l[cont].equals("fim enquanto")){
							if(enq.Fim(cont)){
								cont  = p;
								teste = true;		
							}
							continue;
						}
						
					break;
					
					case "leia":
						ler.Leia(tok, cont);
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
	
	
	// testa se existe a variavel e retorna a posicao do vetor.
	public Variaveis getVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return v[i]; 
		}
		return null;
	}
	
	public Variaveis PosicaVetor(){
		for(int i = 0; i < v.length; i++){
			if(v[i] == null) return v[i];
		}
		return null;
	}
	// cria a variavel.
	public void AdicionaVar(Variaveis a){
		for(int i = 0; i < v.length; i++){
			if(v[i] == null){
				//v[i] = new Variaveis();
				v[i] = a;
				break;
			}
		}
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
	
	public Object Teste(String linhas){
		Variaveis a = getVariavel(linhas);
		if(a == null) erro.Erro5(linhas, 2);
		if(a.getTipo().equals("string")) erro.Erro2(linhas, 2);
		return a.getValor();
	}
	
	// testa se a variavel existe e faz as conversao e retorna um double.
	public double RetornaValor(String nome, int cont){
		int p;
		double g;
		Variaveis b = getVariavel(nome);
		if(b == null) erro.Erro5(nome, cont);
		if(b.getTipo().equals("string")) erro.Erro2(nome, cont);
		if(b.getValor() instanceof Integer){
			p = (int)b.getValor();
			g = p;
		}
		 else g = (double) b.getValor();
		 return g;	
	}
		
} 

	

    

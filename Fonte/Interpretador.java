

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
	
	public boolean con = true, verdadeiro = false;
	private int cond, p, Lfim = 0;
	private String[] tok;
	private String a;
	
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
						d.Declarar(l, cont);
					break;
					
					case "double":
						d.Declarar(l, cont);
					break;
					
					case "string":
						d.Declarar(l, cont);
					break;
					
					case "imprime":
						im.Imprimir(l, cont);
					break;
						
					case "se":
						verdadeiro = true; // verifica se pode utilizar o senao.
						cond = cont;
						cont = se.Se(l, cont, Lfim);
					break;
					
					case "senao":
						if(verdadeiro) cont = se.Se(l, cont, Lfim); // se verdadeiro for treu, pode-se utilizar o senao	
						else erro.Erro15(cont);		
					break;
					
					case "enquanto":
						con = true;
						cont = enq.Enquan(l, cont);
					break;
					
					case "fim":
						if(l[cont].equals("fim enquanto")){
							cont = enq.Fim(cont);
							con = false;
							System.out.println(l[cont]);
						}
					break;
					
					case "leia":
						ler.Leia(l, cont);
					break;
					
					case "break":
						if(!con) erro.Erro20("break", cont);
						cont = enq.Break();	
					break;
					
					case "continue":
						if(!con) erro.Erro20("continue", cont);
						cont = enq.Continue();
					break;
					
					default:
						if(l[cont].equals("inicio programa()") || l[cont].equals("fim se") || l[cont].equals("fim senao") || l[cont].equals("fim enquanto") || l[cont].equals("fim programa")) continue;
						d.Declarar(l, cont);
					
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
	
	// testa se a variavel existe e faz as conversao e retorna um double.
	public double RetornaValor(String nome, int cont){
		int p;
		double g = 0;
		Variaveis b = getVariavel(nome);
		if(b == null) erro.Erro5(nome, cont);
		if(b.getTipo().equals("string")) erro.Erro2(nome, cont);
		if(b.getValor() == null) erro.Erro18(nome, cont);
		if(b.getValor() instanceof Integer){
			p = (int)b.getValor();
			g = p;
		}
		else if(b.getValor() instanceof Double) g = (double)b.getValor();
		return g;	
	}
	
	// metodo que elemina os espaços em branco
	public String EspacoEmBranco(String linh){
		String lin = " ";
		String[] linha = linh.trim().split(" ");
		for(int l = 0; l < linha.length; l++){
			if(linha[l].length() != 0){		
				lin += " " + linha[l]; 
			}
		}
		return lin;
	}
		
} 

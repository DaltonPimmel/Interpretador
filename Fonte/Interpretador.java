

class Interpretador{
	
	
	
	Declaracao d;
	Variaveis[] v;
	Imprime im;
	Operacoes op;
	InicioFim t;
	Comentarios com;
	CondicaoSe se;
	Logico log;
	
	private boolean con = true, verdadeiro = false;
	private int cond;
	
	public Interpretador(){
		this.d = new Declaracao(this);
		this.v = new Variaveis[1000];
		this.im = new Imprime(this);
		this.op = new Operacoes();
		this.t = new InicioFim(this);
		this.com = new Comentarios();
		this.se = new CondicaoSe(this);
		this.log = new Logico();
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
						if(verdadeiro){
							cont = se.Se(l, cont, Lfim);
							verdadeiro = false;
						}else{
							System.out.println("Problema na hora de utilizar o senao, nao he posivel utilizar antes do se : Linha : " + (cond + 1));
							System.exit(0);
						}
						if(cont == 0){
							System.out.println("erroooooooo");
							System.exit(0);
						}//else{
						//	System.out.println("Imposivel usuar o senao antes do se dsddsdsdsdsdeded");
						//	System.exit(0);
						//}
					break;
					
					default:
						if(l[cont].equals("inicio programa()") || l[cont].equals("fim se") || l[cont].equals("fim senao")) continue;
						d.Declarar(tok);
					
					break;
					
				}
			}
			
		}
	}
	
	
	public double VerificaVariavel(String n){ 
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return v[i].getValor();
		}
		return 01010112;
	}
	
	// testa se existe a variavel e retorna a posicao do vetor.
	public int getVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return i; 
		}
		return 1000;
	}
	// retorna a primeira posicao de v que esta null para criar a variavel, se nao estiver mais espaço retorna 1000.
	public boolean CriarVariavel(String n, double a){
		for(int i = 0; i < v.length; i++){
			if(v[i] == null){
				v[i] = new Variaveis(n, a);
				return true;
			}
		}
		return false;
	}
	
	public boolean TestaString(String s){  
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  
        }  
        return true;  
    }
	
}
    

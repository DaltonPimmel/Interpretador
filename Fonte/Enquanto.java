
class Enquanto{

	Interpretador in;
	private int k;
	private String[] tok;
	private int cont;
	boolean h = false;
	int c = 0, test = 0;
	
	public Enquanto(Interpretador i){
		this.in = i;
	}
	
	public int Enquan(String[] linha, int l){
		String nome;
		double e = 0, d = 0;
		//int test = 0;
		
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
				if(linha[k].length() > 1 && linha[k] != null){
					linha[k] = linha[k].trim();
					tok = linha[k].trim().split(" ");
					//System.out.println(linha[k]);
					if(tok[0].equals("enquanto")){  // verificar ........................
						//System.out.println("teste04");
						c++;
					}
					if(linha[k].equals("fim enquanto")){
						if(c != 0){
							cont = k;
							test = cont;
							c--;
							continue;
						}else if(c == 0){
							cont = k;
							if(k > cont){
								test = k; // tem a ultima ocorrencia.
							}
							break;
						}
					}
				}
			}
					//	c = 0;
					//System.out.println(tok[1]);
						if(in.TestaString(tok[1])) d = Double.parseDouble(tok[1]);				
						else{
							nome = linha[3];
							Variaveis a = in.getVariavel(nome);
							if(a == null) in.erro.Erro5(nome, cont);
							if(a.getTipo().equals("string")) in.erro.Erro2(nome, cont);
							d = (double) a.getValor();
							
						}	
						if(in.TestaString(tok[3])) e = Double.parseDouble(tok[3]);		
						else{
							nome = linha[3];
							Variaveis b = in.getVariavel(nome);
							if(b == null) in.erro.Erro5(nome, cont);
							if(b.getTipo().equals("string")) in.erro.Erro2(nome, cont);
							e = (double) b.getValor();
							}
						boolean t = in.log.Log(tok, d, e);
						if(t){
						//	System.out.println("teste");
							 return l;
						 }
						if(k < test) return k;
						return test;
						
						
	}

	
	public boolean Fim(int con){
		//System.out.println(con);
		if(con == test){
		//	System.out.println("teste21");
		//	c = 0;
			return true;
		}
		System.out.println(tok);
		return false;
	}
	

// verificar os operadores, se colocar um operador que nao existe retorna 0

}

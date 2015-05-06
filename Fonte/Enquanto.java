
class Enquanto{

	Interpretador in;
	private int k;
	private String[] tok;
	
	public Enquanto(Interpretador i){
		this.in = i;
	}
	
	public boolean Enquan(String[] linha, int l){
		k = l + 1;
		int c = 0;
		double e = 0, d = 0;
		tok = linha[l].trim().split(" ");
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
				if(linha[k].length() > 1 && linha[k] != null){
					linha[k] = linha[k].trim();
					//if(linha[k].equals("enquanto")) c++;
					if(linha[k].equals("fim enquanto")){
						if(in.TestaString(tok[1])){
							d = Double.parseDouble(tok[1]);	
						}
						else{
							int a = in.getVariavel(tok[1]);
							if(a == 1000) System.exit(0);
							if(in.v[a].getTipo().equals("inteiro"))  d = in.v[a].getVint();
							else{
								d = in.v[a].getVdouble();
								//d = (int)ce;
							}
							
						}	
						if(in.TestaString(tok[3])){
							e = Double.parseDouble(tok[3]);
						}	
						else{
							int a = in.getVariavel(tok[3]);
							if(a == 1000) System.exit(0);
							if(in.v[a].equals("inteiro")) e = in.v[a].getVint();
							else{
								e = in.v[a].getVdouble();
								//e = (int)de;
							}
						}
						boolean t = in.log.Log(tok, d, e);
						if(t)return true;
						return false;
					}
				}
		}
		
			System.out.println("Erro de sintaxe no hora de utilizar o enquanto na linha " + l + " , verificar manual da linguagem!!!");
			System.exit(0);
		
		return false;
	}
	

// verificar os operadores, se colocar um operador que nao existe retorna 0

}


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
		//System.out.println(linha[1]);
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
				if(linha[k].length() > 1 && linha[k] != null){
					linha[k] = linha[k].trim();
					//if(linha[k].equals("enquanto")) c++;
					if(linha[k].equals("fim enquanto")){
						System.out.println(tok[3]);
						if(in.TestaString(tok[1])){
							d = Double.parseDouble(tok[1]);	
						}
						else{
							if(in.VerificaVariavel(tok[1])) d = in.getValor(tok[1]);
							else{
								System.out.println("Variavel nao localizada11111111111!!!"); System.exit(0);
							}
						
						}	
						if(in.TestaString(tok[3])){
							e = Double.parseDouble(tok[3]);
						}	
						else{
							if(in.VerificaVariavel(tok[3])) e = in.getValor(tok[3]);
							else{
								System.out.println("Variavel nao localizada!!!"); System.exit(0);
							}		
						}
						if(in.log.Log(tok, d, e)){
							return true;
						}
						return false;
					}
				}
		}
		
			System.out.println("Erro de sintaxe no hora de utilizar o enquanto na linha " + l + " , verificar manual da linguagem!!!");
			System.exit(0);
		
		return false;
	}
	

// verificar os operadores, se colocar umoperador que nao existe retorna 0

}

//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Classe das condiçoes logicas, teste a sintaxe, e retorna a linha a aonde deve - se continuar o codigo, dependendo da 
//condição se é verdadeira ou falsa.


class CondicaoSe{
	
	private Interpretador in;
	private int  c = 0, k,  y, pp = 0, r;
	private double e, f, g;
	private String[] l, contro;
	boolean te;
	LinhaCondicoes[] log = new LinhaCondicoes[100];
	
	public CondicaoSe(Interpretador i){
		this.in = i;
	}

	public int Se(String[] linhas, int cont, int fim){
		
		String lin = linhas[cont].replaceAll("\\s+"," "); // retirando os espaços em branco
		l = lin.trim().split(" ");
		pp = cont;
		
			for(k = cont + 1; k < linhas.length && linhas[k] != null; k++){
				
				if(linhas[k].length() > 1 && linhas[k] != null){
					linhas[k] = linhas[k].replaceAll("\\s+"," ");
					linhas[k] = linhas[k].trim();
					pp++;
					contro = linhas[k].trim().split(" ");
					if(contro[0].equals("se")) c++;
					if(linhas[k].equals("fim se")){
						r = pp;
						if(c != 0){ 
							c--;
							continue;
						}
						break;
					}
				}
			}
			if(c != 0) in.erro.Erro16(cont);; // não achou o final do se.
			c = 0;
		
		// teste do mod dentro do se.
		if(l.length > 3 && l.length < 7 && l[2].equals("%")){
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	 
			 else f = in.RetornaValor(l[1], cont);
			 
			 if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);		
			 else g = in.RetornaValor(l[3], cont);
			 
			 if(in.TestaString(l[5])) e = Double.parseDouble(l[5]);		 
			 else e = in.RetornaValor(l[5], cont);
			 
			 te = in.op.Mod(f, g, e);
			 
			 		
		}
		// verifica se o se é verdade ou nao.
		else if(l.length > 3 && l.length < 5 && l[2].equals("==") || l[2].equals(">=") || l[2].equals("<=") || l[2].equals(">") || l[2].equals("<") || l[2].equals("!=")){ 
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	
			else f = in.RetornaValor(l[1], cont);
			
			if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);	 
			else g = in.RetornaValor(l[3], cont);
			
			te = in.log.Log(l, f, g);
			
		}else in.erro.Erro3(cont); // erro
		
		
		 //caso não haja um senao na proxima linha, ele não cria o obejto.
			 for(int kg = r + 1; kg < linhas.length; kg++){
				 linhas[kg] = linhas[kg].replaceAll("\\s+"," ");
				 if(linhas[kg].length() > 1){
					 linhas[kg] = linhas[kg].trim();
					 if(!linhas[kg].equals("senao")){
						 if(te) return cont;
						 return r;
					 }
					 break;
				 }
			 }
		
		//System.out.println("mod");
		for(int rr = 0; rr < log.length; rr++){
			if(log[rr] == null){
				log[rr] = new LinhaCondicoes(cont, r, te);
				if(te) return cont;
				return r;	
			}
		}	
		return 0;
	}
	// Metodo do senao
	public int Senao(String[] linhas, int cont){
			int l = cont, kk = 0, c = 0;
			boolean b = false;
	
			for(int y = cont + 1; y < linhas.length && linhas[y] != null; y++){
				linhas[y] = linhas[y].replaceAll("\\s+"," "); // retirando as linhas em branco
				linhas[y] = linhas[y].trim();
			
				if(linhas[y].length() > 1){
					if(linhas[y].equals("fim senao")){
						kk = y; c++;
						break;
					}
				}		
			}
			if(c == 0) in.erro.Erro14(cont);
			c = 0;
			for( int i = 0; i < log.length && log[i] != null; i++){ // se log na posicao 0 for null, nao foi utilzado um se antes, retorna erro
				if(cont < log[i].getFim()){
					for(int t = 0; t < log.length; t++){  // se o log da posicao 1 for null ele pega a 0
						if(log[t] == null){
							  b = log[t - 1].getVer();  // quando ele for null e estiver dentro de um se principal, ele pega a posicao anterior que seria o se anterior
							  break;
						  }	
					}
					
				}else{
					b = log[0].getVer();   // se ele estiver fora de uma laço principal, ele pega a condicao da primeira condicao, e depois coloca todos como null de novo.
					for(int q = 0; log[q] != null; q++) log[q] = null;
				}
				 
				if(b) return (kk - 1);
				return cont;		
			}
			in.erro.Erro15(cont); // caso a primeira posicao seja null.
			return 0;		
	}
	
}

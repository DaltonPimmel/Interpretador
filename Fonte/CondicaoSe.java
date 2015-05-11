
class CondicaoSe{
	
	private Interpretador in;
	private int fi = 0, c = 0, k, teste = 0, test = 0, y;
	private String[] l, tok, contro;
	boolean cond = false, verdadeiro = false, te;
	String nome, tipo;
	
	public CondicaoSe(Interpretador i){
		this.in = i;
	}

	public int Se(String[] linhas, int cont, int fim){
		
		tok = linhas[cont].trim().split(" ");
		if(tok[0].equals("se")){
			for(k = cont + 1; k < linhas.length && linhas[k] != null; k++){
				if(linhas[k].length() > 1 && linhas[k] != null){
					linhas[k] = linhas[k].trim();
					contro = linhas[k].trim().split(" ");
					if(contro[0].equals("se")) c++;
					if(linhas[k].equals("fim se")){
						teste++; // para verifcar se achou o final do se.
						if(c != 0){ 
							c--;
							continue;
						}
						fi = k;
						if(fi > test){
							test = fi;
							break;
						}
						if(c == 0) break;
					}
				}
			}
			if(teste == 0) in.erro.Erro16(cont);; // não achou o final do se.
			teste = 0; c = 0; // zera as variaveis de controle.
		}else{	
			for(y = cont + 1; y < linhas.length && linhas[y] != null; y++){
				if(linhas[y].length() > 1 && linhas[y] != null){
					linhas[y] = linhas[y].trim();
					if(linhas[y].equals("fim senao")){
						if(y < test) in.verdadeiro = true; // testa se o senao esta dentro do laço principal.
						else in.verdadeiro = false; // altera a a variavel do interpretador. Caso tenho um senao depois outro, e um senao estiver dentro laço principal.
						if(!cond && y < test) return cont; // se ela achar o senao dentro do loco principal, ele exucuta se o se anterior for falso.
						else if(cond && y > fi) return y;  // se o se anterior for verdadeiro, e achar o senao dentro do lsaço principal, ele pula para o fim senao
						else if(y > fi && !cond){ 	
							if(!verdadeiro){ // quando a mais se dentro de outro.
								fi = 0;
								teste = 0;
								verdadeiro = true;
								return cont; 
							}
						}	
						return y;
					 }	
				}
			}
			in.erro.Erro14(cont); // se nao achar o final do senao retorna 0.
		}
		if(fi == 0) return 0;
		l = linhas[cont].trim().split(" ");
		double f = 0, g = 0, e = 0;
		
		// teste do mod dentro do se.
		if(l.length > 3 && l.length < 7 && l[2].equals("%")){
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	 
			 else f = in.RetornaValor(l[1], cont);
			 
			 if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);		
			 else g = in.RetornaValor(l[3], cont);
			 
			 if(in.TestaString(l[5])) e = Double.parseDouble(l[5]);		 
			 else e = in.RetornaValor(l[5], cont);
			 
			 if(in.op.Mod(f, g, e)){
				verdadeiro = true;
				cond = true;
				return cont; // se for verdadeiro a condicao
			  }
			 cond = false; // caso o mod seja falso.
			 verdadeiro = false;
			 return k;		
		}
		// verifica se o se é verdade ou nao.
		else if(l.length > 3 && l.length < 5 && l[2].equals("==") || l[2].equals(">=") || l[2].equals("<=") || l[2].equals(">") || l[2].equals("<") || l[2].equals("!=")){ 
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	
			else f = in.RetornaValor(l[1], cont);
			
			if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);	 
			else g = in.RetornaValor(l[3], cont);
	
		}else in.erro.Erro3(cont); // erro
		
		if(in.log.Log(l, f, g)){ //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
			verdadeiro = true;
			cond = true;
			return cont; // se for verdadeiro o se.
		}else if(k < test){
			verdadeiro = true;
			cond = false;
			return k;
		}
		verdadeiro = false;
		cond = false; // se o fi for maior ele ha mais laços dentro para testar, cond recebe falso, podendo exetador algum se nao dentro do laço.
		return test;
		
	
	}
	
	
}

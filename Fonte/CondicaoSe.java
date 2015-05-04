
class CondicaoSe{
	
	private Interpretador in;
	private int fi = 0, c = 0, dd = 0, k, teste = 0, test, y;
	private boolean te;
	private String[] l, tok;
	private String[] contro;
	boolean cond = false, verdadeiro = false;;
	
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
					if(contro[0].equals("se")){ // testa se tem um se dentro do outro.
						dd++; // variaveis de controle.
						c++;
					 }
					if(linhas[k].equals("fim se")){
						teste++; // para verifcar se achou o final do se.
						if(c == 0 && dd >= fi){ 
							fi = k; 
							test = fi;
							break;
						}else if(c == 0){
							break;
						}
						c--;
					}
				}
			}
			if(teste == 0) return 0; // não achou o final do se.
			teste  = 0;
			dd = 0; // zera as variaveis de controle.
			c = 0;
		}else{	
			for(y = cont + 1; y < linhas.length; y++){
				if(linhas[y].length() > 1 && linhas[y] != null){
					linhas[y] = linhas[y].trim();
					if(linhas[y].equals("fim senao")){		
						if(!cond && y < fi) return cont; // se ela achar o senao dentro do loco principal, ele exucuta se o se anterior for falso.
						else if(cond && y > fi) return y;  // se o se anterior for verdadeiro, e achar o senao dentro do lsaço principal, ele pula para o fim senao.
						else if(cond && y < fi) return y;
						else if(y > fi && !cond){ 	
							if(!verdadeiro){ // quando a mais se dentro de outro.
								fi = 0;
								dd = 0;
								teste = 0;
								verdadeiro = true;
								return cont; 
							}
						}	
						return y;
					 }	
				}
			}
			return 0; // se nao achar o final do senao retorna 0.
		}
		if(fi == 0) return 0;
		l = linhas[cont].trim().split(" ");
		double f = 0, g = 0, e = 0;
		
		// teste do mod dentro do se.
		if(l.length > 3 && l.length < 7 && l[2].equals("%")){
			if(in.TestaString(l[1])){
				 f = Double.parseDouble(l[1]);
			 }else{
				 f = in.VerificaVariavel(l[1]);
				 if(f == 01010112) return 0;	// testa se a variavel existe, se retornar 0 a variavel nao existe.	 
			 }
			 if(in.TestaString(l[3])){
				g = Double.parseDouble(l[3]);
			 }else{
				 g = in.VerificaVariavel(l[1]);
				 if(g == 01010112) return 0;
			 }
			 if(!in.TestaString(l[5])){
				   e = in.VerificaVariavel(l[5]);
				   if(e == 01010112) return 0;
			}else{
				e = Double.parseDouble(l[5]);
			}
			 if(in.op.Mod(f, g, e)) return cont;
			 cond = false; // caso o mod seja falso.
			 verdadeiro = false;
			 return k;		
		}
			// verifica se o se é verdade ou nao.
		else if(l.length > 3 && l.length < 5){ 
			if(in.TestaString(l[1])){   // testa se é u numero ou uma variavel...
				f = Double.parseDouble(l[1]);
			}else{
				f = in.VerificaVariavel(l[1]); // se a variavel existir vai receber o valor, se nao recebe -010.
			}
			if(in.TestaString(l[3])){
				 g = Double.parseDouble(l[3]);
			}else{
				g = in.VerificaVariavel(l[3]);
			}	
		}else{
			System.out.println("erro na hora de utilizar a logica se "); System.exit(0);
		}
		if(f == 01010112 || g == 01010112) { System.out.println("erro na hora de utilizar o se "); System.exit(0); }
		te = in.log.Log(l, f, g); //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
		if(te){
			verdadeiro = true;
			cond = true;
			return cont; // se for verdadeiro o se.
		}
		else if(k > fi){ // caso termine os laços dentro do outro.
			verdadeiro = false;
			cond = false;
			return k; // se o fi for maior que o k ele esta no final do ultimo se, e executa o senao.
		}
		cond = false; // se o fi for maior ele ha mais laços dentro para testar, cond recebe falso, podendo exetador algum se nao dentro do laço.
		return k;
		
	
	}
}

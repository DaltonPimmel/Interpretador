
class CondicaoSe{
	
	private Interpretador in;
	private int fi = 0, c = 0, dd = 0, k, teste = 0, test;
	private boolean te;
	private String[] l, tok;
	private String[] contro;
	boolean cond = false, verdadeiro = false;;
	
	public CondicaoSe(Interpretador i){
		this.in = i;
	}

	public int Se(String[] linhas, int cont, int fim){
		//System.out.println(fi);
		tok = linhas[cont].trim().split(" ");
		if(tok[0].equals("se")){
			for(k = cont + 1; k < linhas.length && linhas[k] != null; k++){
				if(linhas[k].length() > 1 && linhas[k] != null){
					linhas[k] = linhas[k].trim();
					contro = linhas[k].trim().split(" ");
					if(contro[0].equals("se")){
						dd++;
						c++;
					 }
					if(linhas[k].equals("fim se")){
						teste++; // para verifcar se achou o final do se.
						if(c == 0 && dd >= fi){ 
							fi = k; // zerar o fi no ultimo senao.
							test = fi;
							break;
						}else if(c == 0){
							break;
						}
						c--;
					}
				}
			}
			if(teste == 0) return 0;
			teste  = 0;
			dd = 0;
			c = 0;
		}else{	
			for(int y = cont + 1; y < linhas.length; y++){
				if(linhas[y].length() > 1 && linhas[y] != null){
					linhas[y] = linhas[y].trim();
					if(linhas[y].equals("fim senao")){		
						if(!cond && y < fi){ // se ela achar o senao dentro do loco principal, ele exucuta se o se anterior for falso.
							System.out.println("teste");
							cond = true;
							return cont;
						}
						else if(cond && y < fi){  // se o se anterior for verdadeiro, e achar o senao dentro do lsaço principal, ele pula para o fim senao.
							cond = false;
							System.out.println("teste1");
							return y;
						}else if(!cond){
							System.out.println("teste1");
							 return cont;
						 }
						else if(y > fi){	
							if(!verdadeiro){
								fi = 0;
								dd = 0;
								teste = 0;
								verdadeiro = true;
								cond = false;
								return cont; 
							}
							return y;
						  }
							
					 }	
				}
			}
			return 0;
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
			 if(in.op.Mod(f, g, e)) return (cont + 1);
			// return 0;		
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
		else if(k > fi){
			//System.out.println("aqui esta o erro");
			 return k; // se o fi for maior que o k ele esta no final do ultimo se, e executa o senao.
		//System.out.println(fi);
		}
		cond = false; // se o fi for maior ele ha mais laços dentro para testar, cond recebe falso, podendo exetador algum se nao dentro do laço.
		return k;
		
		
	//	if(!te && cont >= fi){ // testa nao estar dentro do laco o senao.
	//		cond = false;
	//		return fi; // retorna o indice que acgou o fim se.
	//	}else if(!te && cont < fi){ // testa se esta dentro do laco principal, para poder utilizar o senao, por que se tiver 3 se dentro de um princial se o ultimo fosse falso ele caia no sanao.
	//		cond = true;
	//		return fi;
	//	}
	//	return 0;
	}
}

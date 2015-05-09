
class CondicaoSe{
	
	private Interpretador in;
	private int fi = 0, c = 0, dd = 0, k, teste = 0, test = 0, y;
	private boolean te;
	private String[] l, tok;
	private String[] contro;
	boolean cond = false, verdadeiro = false, uti = false;;
	String nome, tipo;
	
	public CondicaoSe(Interpretador i){
		this.in = i;
	}

	public int Se(String[] linhas, int cont, int fim){
		tok = linhas[cont].trim().split(" ");
		if(tok[0].equals("se")){
		//	uti = true;
			for(k = cont + 1; k < linhas.length && linhas[k] != null; k++){
				if(linhas[k].length() > 1 && linhas[k] != null){
					linhas[k] = linhas[k].trim();
					contro = linhas[k].trim().split(" ");
					if(contro[0].equals("se")){ // testa se tem um se dentro do outro.
						dd++; // variaveis de controle.
						c++;
					 }
					if(linhas[k].equals("fim se")){
						//in.verdadeiro = true; // verificar esta dando probelam na hora que tem somente o se sem o sebnao, no meio da caminho esta ocorrendo um problema.
						teste++; // para verifcar se achou o final do se.
						if(c == 0 && dd >= fi){ 
							fi = k; 
							if(fi > test){
								test = fi;
								break;
							}
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
			for(int t = cont - 1; t != 0; t--){  // faz o teste se a linha anterior é um fim se.
				linhas[t] = linhas[t].trim();
				if(linhas[t].length() > 1 && linhas[t] != null){
					if(!linhas[t].equals("fim se")){
						in.erro.Erro1(); //erros
					 }
					break;
				}
			}
			for(y = cont + 1; y < linhas.length && linhas[y] != null; y++){
				if(linhas[y].length() > 1 && linhas[y] != null){
					linhas[y] = linhas[y].trim();
					if(linhas[y].equals("fim senao")){
						if(y <= test) in.verdadeiro = true; // testa se o senao esta dentro do laço principal.
						else in.verdadeiro = false; // altera a a variavel do interpretador. Caso tenho um senao depois outro, e um senao estiver dentro laço principal.
						if(!cond && y < test) return cont; // se ela achar o senao dentro do loco principal, ele exucuta se o se anterior for falso.
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
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	 
			 else{
				 nome = linhas[1];
				 Variaveis a = in.getVariavel(nome);
				 if(a == null) in.erro.Erro5(nome, cont);
				 if(a.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				 f = (double)(a.getValor());
			 }
			 if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);		
			 else{
				 nome = linhas[3];
				 Variaveis b = in.getVariavel(nome);
				 if(b == null) in.erro.Erro10();
				 if(b.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				 g = (double)(b.getValor());
			 }
			 if(in.TestaString(l[5])) e = Double.parseDouble(l[5]);		 
			 else{
				nome = linhas[5];
				Variaveis c = in.getVariavel(nome);
				if(c == null) in.erro.Erro5(nome, cont);
				if(c.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				e = (double)(c.getValor());
			 }
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
		else if(l.length > 3 && l.length < 5){ 
			if(in.TestaString(l[1])) f = Double.parseDouble(l[1]);	
			else{
				 nome = linhas[1];
				 Variaveis a = in.getVariavel(nome);
				 if(a == null) in.erro.Erro5(nome, cont);
				 if(a.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				 f = (double)a.getValor();
			}
			if(in.TestaString(l[3])) g = Double.parseDouble(l[3]);	 
			else{
				 nome = linhas[3];
				 Variaveis b = in.getVariavel(nome);
				 if(b == null) in.erro.Erro5(nome, cont);
				 if(b.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				 g = (double) b.getValor();
			}	
		}else{
			in.erro.Erro3(cont); // erro
		}
		
		if (in.log.Log(l, f, g)){ //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
			verdadeiro = true;
			cond = true;
			return cont; // se for verdadeiro o se.
		}
		verdadeiro = false;
		cond = false; // se o fi for maior ele ha mais laços dentro para testar, cond recebe falso, podendo exetador algum se nao dentro do laço.
		return k;
		
	
	}
}

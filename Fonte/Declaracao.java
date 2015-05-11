
class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linha, int cont){

		String tipo, nome;
		Object valor = new Object();
		double qe = 0, ses = 0;
		int p = 0;
		String[] linhas;

		linha[cont] = inter.EspacoEmBranco(linha[cont]); // retirando os espaços em branco.
		linhas = linha[cont].trim().split(" ");
		
		if(linhas.length < 4 && linhas.length > 1 && linhas[1].equals("=")){
			nome = linhas[0];
			Variaveis a = inter.getVariavel(nome);
			if(a == null) inter.erro.Erro5(nome, cont);
				if(inter.isInt(linhas[2]) || inter.isDouble(linhas[2])){
					 qe = Double.parseDouble(linhas[2]); // se for um numero ele recebe em um double, depois testa para fazer a conversao.
					if(a.getTipo().equals("int")){
						p = (int)qe; // se o tipo for int converte para int.
						valor = p;
					}else if(a.getTipo().equals("double")) valor = qe;	 // se for um double	
					else valor = linhas[2];	 // se for uma string recebe qualquer valor.
					a.setValor(valor);
			}else {
				Variaveis b = inter.getVariavel(linhas[2]);
				if(b == null) inter.erro.Erro5(nome, cont); // string, aceita qualquer valor.
				else if(a.getTipo().equals(b.getTipo())) valor = b.getValor(); // teste para ver os parametros de atribuicao,
				else if(a.getTipo().equals("int") && b.getTipo().equals("double")) valor = b.getValor();
				else if(a.getTipo().equals("double") && b.getTipo().equals("int")) valor = b.getValor();
				else if(a.getTipo().equals("string")) valor = b.getValor(); // recebe quanquer coisa
				else inter.erro.Erro17(cont);  // se nao for esses parametros da erro.
				if(valor instanceof Integer && a.getTipo().equals("int")) a.setValor(valor); 
				if(valor instanceof Double && a.getTipo().equals("double")) a.setValor(valor);
				if(valor instanceof Integer && a.getTipo().equals("double")){ // conversões de valores para tipos diferentes.
					p = (int)valor;
					qe = (double)p; a.setValor(qe);
				}else if(valor instanceof Double && a.getTipo().equals("int")){
					qe = (double)valor;
					p = (int)qe; a.setValor(p);
				}else if(a.getTipo().equals("string")) a.setValor(valor);// uma string recebe qualquer valor, nao precisa de parametros.		
			}
		}
		// Atruição de variavel, ex: a--, a++
		else if(linhas.length > 0 && linhas.length < 2){
			p = linhas[0].length(); // recebe o tamanho da string.
			String n = linhas[0].substring(1, p);
			nome = linhas[0].substring(0, 1); // recebe a variavel
			Variaveis a = inter.getVariavel(nome);
			if(a == null) inter.erro.Erro5(linhas[0], cont);
			if(a.getTipo().equals("string")) inter.erro.Erro2(nome, cont);
			ses = inter.RetornaValor(nome, cont); // recebe um valor double.
			if(a.getValor() instanceof Integer){ // testa os tipos de variaveis para posiveis conversoes.
				p = (int)ses;
				if(n.equals("--")) a.setValor((p - 1));
				else if(n.equals("++")) a.setValor((p + 1));
				else inter.erro.Erro3(cont); 
			}else if (a.getValor() instanceof Double){
				if(n.equals("--")) a.setValor((ses - 1));
				else if(n.equals("++")) a.setValor((ses + 1));
				else inter.erro.Erro3(cont); 
			}
			else inter.erro.Erro1();
		}
			
		// criacao de variaveis.
		else if(linhas[0].equals("int") || linhas[0].equals("double") || linhas[0].equals("string")){
		
			if(!linhas[0].equals("string")){
				if(linhas.length > 1 && linhas.length < 5 && !inter.TestaString(linhas[1])){
					nome = linhas[1];
					tipo = linhas[0];
					Variaveis a = inter.getVariavel(nome);
					if(a != null) inter.erro.Erro8(nome, cont); // variavel existe.
					a = new Variaveis();
					if(linhas.length == 2){ // variavel sem atribuição
						a.setNome(nome);
						a.setTipo(tipo);
						inter.AdicionaVar(a);
					}else if(linhas.length > 2 && linhas[2].equals("=") && linhas.length < 5){ // variavel com atribuição,
						if(inter.isInt(linhas[3]) || inter.isDouble(linhas[3])){
							ses = Double.parseDouble(linhas[3]);
							if(linhas[0].equals("int")){
								p = (int)ses;  // caso a variavel seja int ele converte.
								valor = p; 
							}else valor = ses;
						}
						a.setNome(nome); // setando os valores.
						a.setTipo(tipo);
						a.setValor(valor);
						inter.AdicionaVar(a); // criando a variavel.			
					}
				}		
			}else {  //declaracao de strings.
				if(!inter.TestaString(linhas[1])){
					nome = linhas[1];
					tipo = linhas[0];
					Variaveis a = inter.getVariavel(nome);
					if(a != null) inter.erro.Erro8(nome, cont); // variavel existe.
					a = new Variaveis();
					if(linhas.length > 2 && linhas[2].equals("=")){
						String lin = " ";
						int f = linhas.length;
						for(int y = 3; y < f; y++) lin += " " + linhas[y].trim();	
						lin = lin.trim();
						String ch = lin.substring(0, 1); int d = lin.length();
						String ch1 = lin.substring((d - 1), d);
						if(ch.equals("\'") && ch1.equals("\'")){		
							lin = lin.replace("\'", " "); lin = lin.trim();
							a.setNome(nome);
							a.setTipo(linhas[0]);
							a.setValor(lin);
							inter.AdicionaVar(a);
						}else inter.erro.Erro3(cont);
					}	
					else if(linhas.length == 2 && !inter.TestaString(linhas[1])){
						a.setNome(nome);
						a.setTipo(tipo);
						inter.AdicionaVar(a);
					}
				}else inter.erro.Erro3(cont);
			}
		}
		//atribuicao para variaveis com operadores, ex: a = a + b.
		else if(linhas.length > 2 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && ( linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/") || linhas[2].equals("+"))){
			//double se = 0, re = 0 ; // poderia calcular chamandos os metodos com os valores, porem nao tem como saber se é uma variavel ou não.
			String op = linhas[3];
			Variaveis a = inter.getVariavel(linhas[0]);
			if(a == null) inter.erro.Erro1();
			if(a.getTipo().equals("int") || a.getTipo().equals("double")){
				
				if(inter.TestaString(linhas[2])) ses = Double.parseDouble(linhas[2]);
				else ses = inter.RetornaValor(linhas[2], cont);

				if(inter.TestaString(linhas[4])) qe = Double.parseDouble(linhas[4]);
				else qe = inter.RetornaValor(linhas[4], cont);
	
				double res = inter.op.operacoes(op, ses, qe);
				
				if(a.getValor() instanceof Integer){
					 p = (int)res;
					 a.setValor(p);
				 }else a.setValor(res);
				
			}else if(a.getTipo().equals("string") && linhas[2].equals("+")){ // juntando duas strings
				String h = (String)a.getValor();
				Variaveis b = inter.getVariavel(linhas[3]);
				if(inter.isInt(linhas[3]) || inter.isDouble(linhas[3])) a.setValor(h + linhas[3]);	
				else if(b != null) a.setValor(h += " " + (b.getValor())); // junta duas variaveis
				else{
					String j = " "; // se não for uma variavel, testas os parametros.
					for(int y = 3; y < linhas.length; y++) j += " " + linhas[y]; 
					j = j.trim();
					String ch = j.substring(0, 1); int d = j.length();
					String ch1 = j.substring((d - 1), d);
					if(ch.equals("\'") && ch1.equals("\'")){
						j = j.replace("\'", " "); 
						a.setValor((a.getValor() + j));
					}else inter.erro.Erro3(cont);
					
				}
				
			}
		}
		//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			Variaveis b = inter.getVariavel(linhas[0]);
			if(b == null) inter.erro.Erro5(linhas[0], cont);
			if(b.getTipo().equals("string")) inter.erro.Erro2(linhas[0], cont);
			if(inter.isDouble(linhas[3]) || inter.isInt(linhas[3])){
				 qe = Double.parseDouble(linhas[3]);
				 if(b.getTipo().equals("int")) b.setValor((int)(inter.op.RaizQuadrada(qe)));
				 else b.setValor(inter.op.RaizQuadrada(qe));
			}
			else{
				Variaveis a = inter.getVariavel(linhas[3]);
				if(a == null) inter.erro.Erro5(linhas[3], cont);
				valor = a.getValor(); // nao sabe o que vai receber.
				if(valor instanceof Double && b.getTipo().equals("int")){
					qe = (double)valor;
					p = (int)inter.op.RaizQuadrada(qe); // se quem chamou for int, e o object valor for double, ele converte para int
					b.setValor(p);
				}else if(valor instanceof Integer && b.getTipo().equals("int")){
					p = (int)valor; // se os dois for int.
					b.setValor((int)inter.op.RaizQuadrada(p)); 
				}else if(valor instanceof Integer){
					p = (int)valor;
					b.setValor(inter.op.RaizQuadrada(p));
				}
			} 
		}
		else inter.erro.Erro3(cont);	
	
	}
}


class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linhas, int cont){

		String tipo;
		String nome;
		Object valor = new Object();
		boolean ar = false;
		double qe = 0;
		double ses = 0;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas.length > 1 && linhas[1].equals("=")){
			nome = linhas[0];
			Variaveis a = inter.getVariavel(nome);
			if(a == null) inter.erro.Erro5(nome, cont);
				if(inter.isInt(linhas[2]) || inter.isDouble(linhas[2])){
					 qe = Double.parseDouble(linhas[2]); // se for um numero ele recebe em um double, depois testa para fazer a conversao.
					if(a.getTipo().equals("int")){
						int p = (int)qe; // se o tipo for int converte para int.
						valor = p;
					}else if(a.getTipo().equals("double")) valor = qe;	 // se for um double	
					else valor = linhas[2];	 // se for uma string
					a.setValor(valor);
			}else {
				Variaveis b = inter.getVariavel(linhas[2]);
				if(b == null){ // se b for null, se o tio da variavel for != de int ou double, ele é umastring, aceita qualquer valor.
					if(a.getTipo().equals("int") || a.getTipo().equals("double")) inter.erro.Erro3(cont);
					valor = linhas[2]; //caso o b nao seje uma variavel, ele é uma string.
					a.setValor(valor);
				}
				else if(a.getTipo().equals(b.getTipo())) valor = b.getValor();
				else if(a.getTipo().equals("int") && b.getTipo().equals("double")) valor = b.getValor();
				else if(a.getTipo().equals("double") && b.getTipo().equals("int")) valor = b.getValor();
				else if(a.getTipo().equals("stirng")) valor = b.getValor();
				else inter.erro.Erro1(); 
				if(valor instanceof Integer || valor instanceof Double){
					double rr = (double)valor;
					 a.setValor(rr);
				}else if(valor instanceof String){
					String ss = (String)valor;
					a.setValor(ss);
				}
			}
		}
		// Atruição de variavel, ex: a--, a++
		else if(linhas.length > 0 && linhas.length < 2){
			String n = linhas[0].substring(1, 3);
			nome = linhas[0].substring(0, 1); // recebe a variavel
			Variaveis a = inter.getVariavel(nome);
			if(a == null) inter.erro.Erro5(nome, cont);
			if(a.getTipo().equals("string")) inter.erro.Erro2(nome, cont);
			double yy = (double)(a.getValor());
			if(n.equals("--")) a.setValor((yy - 1));
			else if(n.equals("++")) a.setValor((yy + 1));
			else inter.erro.Erro1();
		}
			
		// criacao de variaveis.
		else if(linhas[0].equals("int") || linhas[0].equals("double") || linhas[0].equals("string")){
			double teste = 0;
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
						 double tt = Double.parseDouble(linhas[3]);
						 if(linhas[0].equals("int")){
							  int y = (int)tt;  // caso a variavel seja int ele converte.
							  valor = y; 
						  }
						 else valor = tt; // se o a string linhas[3] for um numero, o objeto valor recebe Double.
					}else{
						 String jj = linhas[3]; // se a linhas[3] for uma string.
						 valor = jj;
					 }
					a.setNome(nome); // setando os valores.
					a.setTipo(tipo);
					a.setValor(valor);
					inter.AdicionaVar(a); // criando a variavel.			
				}		
			}
		}
		//atribuicao para variaveis com operadores.
		else if(linhas.length > 2 && linhas.length < 6 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && ( linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/") || linhas[2].equals("+"))){
			double se = 0, re = 0 ; // poderia calcular chamandos os metodos com os valores, porem nao tem como saber se é uma variavel ou não.
			String op = linhas[3];
			Variaveis a = inter.getVariavel(linhas[0]);
			if(a == null) inter.erro.Erro1();
			if(a.getTipo().equals("int") || a.getTipo().equals("double")){
				if(inter.TestaString(linhas[2])) se = Double.parseDouble(linhas[2]);
				else{
					Variaveis b = inter.getVariavel(linhas[2]);
					if(b == null) inter.erro.Erro5(linhas[2], cont);
					if(b.getTipo().equals("string")) inter.erro.Erro2(linhas[2], cont);
					se = (double) b.getValor();
				}
				if(inter.TestaString(linhas[4])) re = Double.parseDouble(linhas[4]);
				else{
					Variaveis c = inter.getVariavel(linhas[4]);
					if(c == null) inter.erro.Erro5(linhas[4], cont);
					if(c.getTipo().equals("string")) inter.erro.Erro2(linhas[4], cont);
					re = (double) c.getValor();
				}
				double res = inter.op.operacoes(op, se, re);
				a.setValor(res);
			}else if(a.getTipo().equals("string") && linhas[2].equals("+")){ // juntando duas strings
				String h = (String)a.getValor();
				h += " " + linhas[3];
				a.setValor(h); 
			} else inter.erro.Erro3(cont);
	
		}
		//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(inter.TestaString(linhas[3])) qe = Double.parseDouble(linhas[3]);		
			else{
				//Variaveis a = inter.getVariavel(linhas[3]);
				//if(a == null) inter.erro.Erro5(linhas[3], cont);
				//if(a.getTipo().equals("string")) inter.erro.Erro2(linhas[3], cont);
				qe = (double)inter.Teste(linhas);
			}
			Variaveis b = inter.getVariavel(linhas[0]);
			if(b == null) inter.erro.Erro5(linhas[0], cont);
			if(b.getTipo().equals("string")) inter.erro.Erro2(linhas[0], cont);
			b.setValor(inter.op.RaizQuadrada(qe));	
		}
		else inter.erro.Erro3(cont);	
	
	}
}


class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linhas, int cont){
		String tipo;
		boolean ar = false;
		double qe = 0;
		double ses = 0;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas.length > 1 && linhas[1].equals("=")){
			int d = inter.getVariavel(linhas[0]);
			int a = inter.getVariavel(linhas[2]);
			if(d == 1000) inter.erro.Erro5(linhas[0], cont);
			if(inter.v[d].getTipo().equals("inteiro")){
				if(a != 1000){ //caso for uma variavel, teste 	ue tipo de variavel é
					if(inter.v[a].getTipo().equals("string")) inter.erro.Erro7(linhas[0], cont);
					if(inter.v[a].getTipo().equals("double")){ // converte para int.
						double r = inter.v[a].getVdouble();
						int t = (int)r;
						inter.v[d].setIn(t);	
					}else{
						int t = Integer.parseInt(linhas[2]);
						inter.v[d].setIn(t);
					}
				}else{
					if(inter.isInt(linhas[2])){
						int e = Integer.parseInt(linhas[2]);
						inter.v[d].setIn(e);
					}else if(inter.isDouble(linhas[2])){
						double r = Double.parseDouble(linhas[2]);
						int b = (int)r;
						inter.v[d].setIn(b);
					}else inter.erro.Erro7(linhas[0], cont);
					
				}
			}else if(inter.v[d].getTipo().equals("double")){
				double r;
				if(a != 1000){ // caso for uma variavel, teste 	ue tipo de variavel é
					if(inter.v[a].getTipo().equals("string")) inter.erro.Erro7(linhas[0], cont);
					if(inter.v[a].getTipo().equals("inteiro"))  r = inter.v[a].getVint(); // se for int ele converte para double.
					else  r = inter.v[a].getVdouble();
					inter.v[d].setDou(r);
				}else{
					double p = 0;
					if(inter.isDouble(linhas[2])) p = Double.parseDouble(linhas[2]);
					if(inter.isInt(linhas[2])) p = Double.parseDouble(linhas[2]);
					else inter.erro.Erro7(linhas[0], cont);
					inter.v[d].setDou(p);
				}
			}else if(inter.v[d].getTipo().equals("string")){
				String s;
				if(a != 1000){ // caso for uma variavel, teste 	ue tipo de variavel é
					if(inter.v[a].getTipo().equals("inteiro")){	
						 int q = inter.v[a].getVint();
						 s = Integer.toString(q); // convertendo para string.
					 }
					if(inter.v[a].getTipo().equals("double")){
						 double h = inter.v[a].getVdouble();
						 s = Double.toString(h);
					 }
					else s = inter.v[a].getVstring();
					inter.v[d].setStrin(s);
				}else{
					 s = linhas[2]; // se nao for uma variavel a string recebe qualquer valor.
					 inter.v[d].setStrin(s);
				}
				
			}
		}
		// Atruição de variavel, ex: a--, a++
		else if(linhas.length > 0 && linhas.length < 2){
			String n = linhas[0].substring(1, 3);
			String nome = linhas[0].substring(0, 1); // recebe a variavel
			int d = inter.getVariavel(nome); // retorna a posicao da variavel.
			if(d == 1000) inter.erro.Erro5(linhas[2], cont);
			if(inter.v[d].getTipo().equals("string")) inter.erro.Erro2(linhas[0], cont);	
			if(inter.v[d].getTipo().equals("inteiro")){
				int a = inter.v[d].getVint();
				if(n.equals("--")) inter.v[d].setIn((a - 1));
				else if(n.equals("++")) inter.v[d].setIn((a + 1));
			}else{
				double a = inter.v[d].getVdouble(); 
				if(n.equals("--")) inter.v[d].setDou((a - 1));
				else if(n.equals("++")) inter.v[d].setDou((a + 1));
			}
		}
				// continuar ...
		// criacao de variaveis.
		else if(linhas[0].equals("inteiro") || linhas[0].equals("double") || linhas[0].equals("string")){
			//inter.CriarVariavelInt(linhas);
			
			if(linhas[0].equals("inteiro")){ // criando variavel do tipo inteiro
				if(linhas.length > 3 && linhas.length < 5 && !inter.TestaString(linhas[1]) && linhas[2].equals("=")){  //declaracao com atribuição.
					String nome = linhas[1];
					tipo = linhas[0];
					if(!inter.isInt(linhas[3])) inter.erro.Erro7(linhas[0], cont); // testa se é um numero inteiro.
					int rec = Integer.parseInt(linhas[3]);
					int a = inter.getVariavel(nome);		
					if(a != 1000) inter.erro.Erro8(nome, cont); // se retornar outro valor, a variavel nome ja existe.
					int d = inter.PosicaVetor(); // recebe o primeira posicao null.
					if(d == 1000) System.exit(0);
					inter.v[d] = new VarInt();
					inter.v[d] = inter.varint.Varint(nome, rec, tipo);
					//return true;
				}else if(linhas.length < 3 &&  !inter.TestaString(linhas[1]) ){ // declaração de variavel sem atribução.
					String nome = linhas[1];
					int a = inter.getVariavel(nome);
					if(a != 1000) inter.erro.Erro8(nome, cont);
					tipo = linhas[0];
					int d = inter.PosicaVetor();
					if(d == 1000) inter.erro.Erro9();
					inter.v[d] = new VarInt();
					inter.v[d] = inter.varint.Varint(nome, 0, tipo);
				//	return true;
				}
			
			}else if(linhas[0].equals("double")){ // criando variavel do tipo double.
				if(linhas.length > 3 && linhas.length < 5 && !inter.TestaString(linhas[1]) && linhas[2].equals("=")){ 
					String nome = linhas[1];
					tipo = linhas[0];
					double a = Double.parseDouble(linhas[3]);
					int t = inter.PosicaVetor();
					if(t == 1000) inter.erro.Erro9();
					inter.v[t] = new VarInt();
					inter.v[t] = inter.varint.Vardouble(nome, a, tipo);
					//return true;
				}else if(linhas.length < 3 && !inter.TestaString(linhas[1])){ // declaração de variavel do tipo double sem atribuição.
					String nome = linhas[1];
					tipo = linhas[0];
					int t = inter.PosicaVetor();
					if(t == 1000) inter.erro.Erro9();
					inter.v[t] = new VarInt();
					inter.v[t] = inter.varint.Vardouble(nome, 0.0, tipo);
				//	return true;
				}			
			}else if(linhas[0].equals("string")){
				if(linhas.length > 3 && linhas.length < 5 && !inter.TestaString(linhas[1]) && linhas[2].equals("=")){
					String nome = linhas[1];
					tipo = linhas[0];
					String a = linhas[3];
					int t = inter.PosicaVetor();
					if(t == 1000) inter.erro.Erro9();
					inter.v[t] = new VarInt();
					inter.v[t] = inter.varint.VarString(nome, a, tipo);
					//return true;
				}	
			}	
	
		}
		//atribuicao para variaveis com operadores.
		else if(linhas.length > 3 && linhas.length < 6 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
			double se = 0, re = 0 ;
			ar = false;
			int a = inter.getVariavel(linhas[0]); // linha[0] variavel que vai receber o valor, a tem a posicao.
			if(a == 1000) inter.erro.Erro5(linhas[0], cont);
			if(inter.v[a].getTipo().equals("string")) inter.erro.Erro2(linhas[0], cont); // testa se nao é uma string.
			if(inter.v[a].getTipo().equals("inteiro")) ar = true;
			if(inter.TestaString(linhas[2]))  se = Double.parseDouble(linhas[2]); // se for numero
			else{
				int b = inter.getVariavel(linhas[2]);
				if(b == 1000) inter.erro.Erro5(linhas[2], cont);
				if(inter.v[b].getTipo().equals("string")) inter.erro.Erro2(linhas[2], cont);
				if(inter.v[b].getTipo().equals("inteiro")){
					se = inter.v[b].getVint(); 
				}
				else se = inter.v[b].getVdouble();
			}
			if(inter.TestaString(linhas[4])) re = Integer.parseInt(linhas[4]);
			else{
				int b = inter.getVariavel(linhas[4]); // se a variavel existir retorna a posicao do vetor que ela esta.
				if(b == 1000) inter.erro.Erro5(linhas[4], cont);
				if(inter.v[b].getTipo().equals("string")) inter.erro.Erro2(linhas[4], cont);		
				if(inter.v[b].getTipo().equals("inteiro")){
					re = inter.v[b].getVint();
				}
				else re = inter.v[b].getVdouble();
			}
			String nome = linhas[3]; // linhas[3] tem o operador.
			if(ar){ // se a primeira variavel for int
				int d;
				double rec = inter.op.operacoes(nome, se, re); 
				d = (int)rec; // convertendo o double em um int.
				inter.v[a].setIn(d); 
			}else{ // se a primeira variavel for double.
				double rec = inter.op.operacoes(nome, se, re);
				inter.v[a].setDou(rec);
			}
	
		}
		//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(inter.TestaString(linhas[3])) qe = Double.parseDouble(linhas[3]);		
			else{
				int d = inter.getVariavel(linhas[3]);
				if(d == 1000) inter.erro.Erro5(linhas[3], cont);
				if(inter.v[d].getTipo().equals("string")) inter.erro.Erro2(linhas[3], cont);
				if(inter.v[d].getTipo().equals("inteiro")) qe = inter.v[d].getVint();
				else qe = inter.v[d].getVdouble();
			}
			int d = inter.getVariavel(linhas[0]);
			if(d == 1000) inter.erro.Erro5(linhas[0], cont);
			if(inter.v[d].getTipo().equals("inteiro")){
				double p = inter.op.RaizQuadrada(qe);
				int a = (int)p; // conversao para int se caso a variavel que vai receber a rais for int.
				inter.v[d].setIn(a);
			}
			else inter.v[d].setDou(inter.op.RaizQuadrada(qe));
		}else{
			inter.erro.Erro6(cont);
		}
	
	}
}

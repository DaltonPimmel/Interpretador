
class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linhas){
		String tipo;
		double qe = 0;
		double ses = 0;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas[1].equals("=")){
			if(inter.TestaString(linhas[2])){
				ses = Double.parseDouble(linhas[2]);
			}else{
				if(inter.VerificaVariavel(linhas[2])) ses = inter.getValor(linhas[2]);
				else{
					System.out.println("problema na hora da atribuicao"); System.exit(0);
				}
			}
			int c = inter.getVariavel(linhas[0]);
			if(c == 1000){ // get variavel verifica se a variavel existe e retorna o indice.
				System.out.println("Variavel " + linhas[0] + " nao existe");
				System.exit(0);
			}	
			inter.v[c].setValor(ses);
		}	
		else if(linhas[0].equals("inteiro") || linhas[0].equals("double") || linhas[0].equals("string")){
			inter.CriarVariavelInt(linhas);
		}
		//atribuicao para variaveis com operadores.
		else if(linhas.length > 3 && linhas.length < 6 && !inter.TestaString(linhas[0]) && linhas[3].equals("=") || linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
			double se = -5555, re = -5555 ;
			boolean ar = false;
			int a = inter.getVariavel(linhas[0]); // linha[0] variavel que vai receber o valor, a tem a posicao.
			if(a == 1000 || inter.v[a].getTipo().equals("string")){ // testa se nao é uma string.
				System.out.println("Variavel nao existe");
				System.exit(0);
			}
			if(inter.TestaString(linhas[2])){ // se for numero
				 se = Double.parseDouble(linhas[2]);
			}
			else{
				int b = inter.getVariavel(linhas[2]);
				if(b == 1000 || inter.v[b].getTipo().equals("string")){
					System.out.println("Variavel nao existe");
					System.exit(0);
				}
				if(inter.v[b].getTipo().equals("inteiro")) se = inter.v[b].getVint(); // recebe o valor da variavel da linha[2]
				else{
					se = inter.v[b].getVdouble();
				}
			}
			if(inter.TestaString(linhas[4])){
				 re = Double.parseDouble(linhas[4]);
			}
			else{
				int b = inter.getVariavel(linhas[4]); // se a variavel existir retorna a posicao do vetor que ela esta.
				if(b == 1000 || inter.v[b].getTipo().equals("string")){
					System.out.println("Variavel nao existe");
					System.exit(0);
				}
				if(inter.v[b].getTipo().equals("inteiro")) re = inter.v[b].getVint(); // re recebe o valor da variavel linha[4].
				else{
					re = inter.v[b].getVdouble();
				}
			}
			String nome = linhas[3]; // linhas[3] tem o operador.
			double rec = inter.op.operacoes(nome, se, re); 
			inter.v[a].setValor(rec); // a é o indice da variavel que vai receber o valor.
	
	}
			//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(inter.TestaString(linhas[3])){
				qe = Double.parseDouble(linhas[3]);
			}else{
				if(inter.VerificaVariavel(linhas[3])) qe = inter.getValor(linhas[3]);
				else{
					System.out.println("Problema na hora calcular a raiz quadrada, variavel nao existe");
				}
			}
			int c = inter.getVariavel(linhas[0]);
			if(c == 1000){
				System.out.println("Variavel " + linhas[0] + " nao existe");
				System.exit(0);
			} 
			inter.v[c].setValor(inter.op.RaizQuadrada(qe)); // v[c] vai receber a valor do calcula da raiz quadrada.
		}
	
	}
}

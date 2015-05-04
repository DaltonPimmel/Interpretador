
class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linhas){
	
		double qe;
		double ses;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas[1].equals("=")){
			if(inter.TestaString(linhas[2])){
				ses = Double.parseDouble(linhas[2]);
			}else{
				ses = inter.VerificaVariavel(linhas[2]);
				if(ses == 0){
					System.out.println("Variavel nao existe...");
					System.exit(0);
				}
			}
			int c = inter.getVariavel(linhas[0]);
			if(c == 1000){
				System.out.println("Variavel " + linhas[0] + " nao existe");
				System.exit(0);
			}	
			inter.v[c].setValor(ses);
		}	
		

		
		// criando variavel com atribuição. Somente com numeros.
		if(linhas.length > 3 && linhas.length < 5 && !inter.TestaString(linhas[1]) && linhas[2].equals("=")){
			String nome = linhas[1];
			double rec = Double.parseDouble(linhas[3]);
			int a = inter.getVariavel(nome);		
			if(a != 1000){ // se retornar outro valor, a variavel nome ja existe.
				 System.out.println("errooooooooooooooo");
				 System.exit(0);
			}
			inter.CriarVariavel(nome, rec); // retorna o primeiro indice de v que esta vazio para criar uma nova variavel.	
			
			// criacao de variavel sem atrubuicao.	
		}else if(linhas.length < 3 &&  !inter.TestaString(linhas[1]) ){
			if(!linhas[0].equals("inteiro")) System.exit(0);
			String nome = linhas[1];
			int a = inter.getVariavel(nome);
			if(a != 1000){
				System.out.println("Variavel ja foi declarada " + nome);
				System.exit(0);
			}
			inter.CriarVariavel(nome, 0);
			
		}

		//atribuicao para variaveis com operadores.
//		}else if(linhas.length > 3 && linhas.length < 6 && !TestaString(linhas[0]) && linhas[3].equals("=") || linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
//			double se = -5555, re = -5555 ;
//			boolean ar = false;
//			int a = getVariavel(linhas[0]); // linha[0] variavel que vai receber o valor, a tem a posicao.
//			if(a == 1000) return false; // variavel na linhas[0] nao exite
//			if(TestaString(linhas[2])){ // se for numero
//				 se = Double.parseDouble(linhas[2]);
//			}
//			else{
//				int b = getVariavel(linhas[2]);
//				if(b == 1000) return false; // variavel nao existe.
//				se = VerificaVariavel(linhas[2]); // recebe o valor da variavel da linha[2]
//			}
//			if(TestaString(linhas[4])){
//				 re = Double.parseDouble(linhas[4]);
//			}
//			else{
//				int b = getVariavel(linhas[4]); // se a variavel existir retorna a posicao do vetor que ela esta.
//				if(b == 1000) return false; // variavel nao existe
//				re = VerificaVariavel(linhas[4]); // re recebe o valor da variavel linha[4].
//			}
//			String nome = linhas[3]; // linhas[3] tem o operador.
//			double rec = op.operacoes(nome, se, re); 
//			v[a].setValor(rec); // a é o indice da variavel que vai receber o valor.
//			return true;
//		}

			//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(inter.TestaString(linhas[3])){
				qe = Double.parseDouble(linhas[3]);
			}else{
				qe = inter.VerificaVariavel(linhas[3]);
				if(qe == 0){
					System.out.println("Variavel " + linhas[3] + " nao existe");
					System.exit(0);
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

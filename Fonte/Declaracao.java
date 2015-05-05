
class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linhas){
		String tipo;
		boolean ar = false;
		double qe = 0;
		double ses = 0;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas[1].equals("=")){
			if(inter.TestaString(linhas[2])){
				ses = Double.parseDouble(linhas[2]);
			}else{
				int d = inter.getVariavel(linhas[2]);
				if(d == 1000 || inter.v[d].getTipo().equals("string")){
					System.out.println("Variavel nao declarada!!!"); System.exit(0);
				}else{
					if(inter.v[d].getTipo().equals("inteiro")) ses = inter.v[d].getVint();
					else ses = inter.v[d].getVdouble();
				}
			}
			int d = inter.getVariavel(linhas[0]);
			if(d == 1000 || inter.v[d].getTipo().equals("string")){
				System.out.println("Problema na hora de fazer a atribuicao!!!"); System.exit(0);
			}
			if(inter.v[d].getTipo().equals("inteiro")){
				qe = inter.v[d].getVint();
				ar = true;
			}
			else qe = inter.v[d].getVdouble();
			if(ar){
				int r = (int)ses;
				inter.v[d].setIn(r);
			}else{
				inter.v[d].setDou(ses);
			}
		}
				
		// criacao de variaveis.
		else if(linhas[0].equals("inteiro") || linhas[0].equals("double") || linhas[0].equals("string")){
			inter.CriarVariavelInt(linhas);
		}
		//atribuicao para variaveis com operadores.
		else if(linhas.length > 3 && linhas.length < 6 && !inter.TestaString(linhas[0]) && linhas[1].equals("=") || linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
			double se = 0, re = 0 ;
			ar = false;
			int a = inter.getVariavel(linhas[0]); // linha[0] variavel que vai receber o valor, a tem a posicao.
			if(a == 1000 || inter.v[a].getTipo().equals("string")){ // testa se nao é uma string.
				System.out.println("Variavel nao existe");
				System.exit(0);
			}
			if(inter.v[a].getTipo().equals("inteiro")) ar = true;
			if(inter.TestaString(linhas[2]))  se = Double.parseDouble(linhas[2]); // se for numero
			else{
				int b = inter.getVariavel(linhas[2]);
				if(b == 1000 || inter.v[b].getTipo().equals("string")){
					System.out.println("Variavel nao existe");
					System.exit(0);
				}
				if(inter.v[b].getTipo().equals("inteiro")){
					se = inter.v[b].getVint(); 
				}
				else se = inter.v[b].getVdouble();
			}
			if(inter.TestaString(linhas[4])) re = Integer.parseInt(linhas[4]);
			else{
				int b = inter.getVariavel(linhas[4]); // se a variavel existir retorna a posicao do vetor que ela esta.
				if(b == 1000 || inter.v[b].getTipo().equals("string")){
					System.out.println("Variavel nao existe");
					System.exit(0);
				}
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

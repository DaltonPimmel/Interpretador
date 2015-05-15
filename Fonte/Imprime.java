//Dalton Luiz Pimmel
// dalton.cco1@gmail.com

// classe imprime, quando chamada verifica a sintaxe para impressão de informações na tela.

class Imprime{
	
	Interpretador in;
	String[] linhas;
	Object o = new Object();
	
	public Imprime(Interpretador i){
		this.in = i;
	}
		
	public void Imprimir(String[] linha, int cont){
		String rec = linha[cont];
		//rec = rec.replaceAll("\\s+"," "); 
		linhas = rec.trim().split(" ");
		
		// imprimindo Strings.
		if(linhas.length > 1 && linhas[1].contains("'") ){ 
			String s = " ";
			for(int i = 1; i < linhas.length; i++){
				s = s + linhas[i] + " ";
			}
			int a = s.trim().length();
			if(s.charAt(a) != '\'') in.erro.Erro11(cont); // teste se na ultima posicao tem aspas.
			String r = s.substring(2, (s.length() - 2)); // eliminando as ''
			System.out.println(r);
		}
		// impressao de variaveis	
		else if(linhas.length == 2){
			if(in.TestaString(linhas[1])) System.out.println(linhas[1]);
			else{
				Variaveis a = in.getVariavel(linhas[1]);
				if(a == null) in.erro.Erro5(linhas[1], cont);
				o = a.getValor();	
				System.out.println(o);
			}
				
		}
		// impressão com operadores.
		else if(linhas.length > 3 && linhas.length < 5 && ( linhas[2].equals("+") ||  linhas[2].equals("-") || linhas[2].equals("*") || linhas[2].equals("/"))){
			double num = 0, num1 = 0;
			int ds = 0;
			if(in.TestaString(linhas[1])) num = Double.parseDouble(linhas[1]);  // testa se é um numero ou uma variavel, se nao for vai verificar nas variaveis se exite.	
			else num = in.RetornaValor(linhas[1], cont);
				
			if(in.TestaString(linhas[3])) num1 = Double.parseDouble(linhas[3]);	
			else num1 = in.RetornaValor(linhas[3],cont);	
			
			double res = in.op.operacoes(linhas[2], num, num1); // chama o metodo das operacoes para imprimir.
			System.out.println(res);
									
		}else in.erro.Erro5(linhas[0], cont);
					
	}
}

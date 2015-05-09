
class Imprime{
	
	Interpretador in;
	String nome;
	Object o = new Object();
	
	public Imprime(Interpretador i){
		this.in = i;
	}
		

	public void Imprimir(String[] linhas, int cont){
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
		else if(linhas.length > 3 && linhas.length < 5 && linhas[2].equals("+") || linhas[2].equals("-") || linhas[2].equals("*") || linhas[2].equals("/")){
			double num = 0, num1 = 0;
			int ds = 0;
			if(in.TestaString(linhas[1])) num = Double.parseDouble(linhas[1]);  // testa se é um numero ou uma variavel, se nao for vai verificar nas variaveis se exite.	
			else{
				nome = linhas[1];
				Variaveis a = in.getVariavel(nome);
				if(a == null) in.erro.Erro5(nome, cont);
				if(a.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				if(a.getValor() instanceof Integer){
					ds = (int) a.getValor();
					num = (double)ds;
				}
				else num = (double) a.getValor();
			}
			if(in.TestaString(linhas[3])) num1 = Double.parseDouble(linhas[3]);	
			else{
				nome = linhas[3];
				Variaveis b = in.getVariavel(nome);
				if(b == null) in.erro.Erro5(nome, cont);
				if(b.getTipo().equals("string")) in.erro.Erro2(nome, cont);
				num1 = (double) b.getValor();	
			}
			double res = in.op.operacoes(linhas[2], num, num1); // chama o metodo das operacoes para imprimir.
			System.out.println(res);
									
		}
					
	}
}

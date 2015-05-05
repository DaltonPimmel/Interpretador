
class Imprime{
	
	Interpretador in;
	
	public Imprime(Interpretador i){
		this.in = i;
	}
		

	public void Imprimir(String[] linhas){
		// imprimindo Strings.
		if(linhas.length > 1 && linhas[1].contains("'") ){ 
			String s = " ";
			for(int i = 1; i < linhas.length; i++){
				s = s + linhas[i] + " ";
			}
			int a = s.trim().length();
			if(s.charAt(a) != '\''){ // teste se na ultima posicao tem aspas.
				System.out.println("Erro na impressao da String, Para impressao de String utiliza-se ' String ' ");
				System.exit(0);
			}
			String r = s.substring(2, (s.length() - 2)); // eliminando as ''
			System.out.println(r);
		}
		// impressao de variaveis	
		else if(linhas.length == 2){
			if(in.TestaString(linhas[1])) System.out.println(linhas[1]);
			else{
				//System.out.println(in.getValor(linhas[1]));
				int d = in.getVariavel(linhas[1]);
				if(d == 1000){
					System.out.println("Variavel nao existe!!!");
					System.exit(0);
				}
				if(in.v[d].getTipo().equals("inteiro")){
					int a = in.v[d].getVint();
					System.out.println(a);
				}else if(in.v[d].getTipo().equals("double")){
					double a = in.v[d].getVdouble();
					System.out.println(a);
				}else if(in.v[d].getTipo().equals("string")){
					String a = in.v[d].getVstring();
					System.out.println(a);
				}
			}
			
			
			
				
		}
			//if(linhas[0].equals("string")){
			//	System.out.pirntln(linhas[1]);
			//}else if(linhas[0].equals("inteiro")){
			//	int a = Integer.parseInt(linhas[1]);
			//	System.out.println()
			//} 
			//double h = 0;
			//if(in.TestaString(linhas[1])){ // chama o TestaString para verificar se um numero.
			//	System.out.println(linhas[1]);
			//}else{
			//	if(in.VerificaVariavel(linhas[1]))   h = in.getValor(linhas[1]); // se não for um numero, chama o verificaVariavel que retornar o valor.
			//	else{
				//	System.out.println("problema na imopressao, variavel nao localizada"); System.exit(0);
				////}
			//	System.out.println(h);
			//}
		//}
		// impressão com operadores.
		else if(linhas.length > 3 && linhas.length < 5 && linhas[2].equals("+") || linhas[2].equals("-") || linhas[2].equals("*") || linhas[2].equals("/")){
			double num = -1, num1 = -1;
			if(in.TestaString(linhas[1])){   // testa se é um numero ou uma variavel, se nao for vai verificar nas variaveis se exite.
				num = Double.parseDouble(linhas[1]);
			}else{
				if(in.VerificaVariavel(linhas[1])) num = in.getValor(linhas[1]);
				else{
					System.out.println("problema na imopressao, variavel nao localizada"); System.exit(0);
				}
			}
			if(in.TestaString(linhas[3])){
				num1 = Double.parseDouble(linhas[3]);
			}else{
				if(in.VerificaVariavel(linhas[3])) num1 = in.getValor(linhas[3]);
				else{
					System.out.println("problema na imopressao, variavel nao localizada"); System.exit(0);
				}
				
			}
			double res = in.op.operacoes(linhas[2], num, num1); // chama o metodo das operacoes para imprimir.
			System.out.println(res);
									
		}
					
	}
}

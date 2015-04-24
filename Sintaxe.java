import java.util.Scanner;
import java.io.*;

class Sintaxe{
	Variaveis[] v = new Variaveis[100];
	Logico log = new Logico();
	Operacoes op = new Operacoes();
	
	public boolean se(String[] linhas){
		boolean te = false;
		int i = 0;
		double f = -1, g = -1, e = -1;
		while(i < linhas.length){
			// teste do mod dentro do se.
			if(linhas.length > 3 && linhas.length < 7 && linhas[2].equals("%")){
				if(TestaString(linhas[1])){
					 f = Double.parseDouble(linhas[1]);
				 }else{
					 f = VerificaVariavel(linhas[1]);
					 if(f == -010) return false;
					 
				 }
				 if(TestaString(linhas[3])){
					 g = Double.parseDouble(linhas[3]);
				 }else{
					 g = VerificaVariavel(linhas[1]);
					 if(g == -010) return false;
				 }
				 if(!TestaString(linhas[5])){
					   e = VerificaVariavel(linhas[5]);
				}else{
					e = Double.parseDouble(linhas[5]);
				}
				 if(e == -010) return false;
				 if(op.Mod(f, g, e)) return true;
				 return false;
				
			}
			// verifica se o se é verdade ou nao.
			if(linhas.length > 3 && linhas.length < 5){ 
				if(TestaString(linhas[1])){   // testa se é u numero ou uma variavel...
					f = Double.parseDouble(linhas[1]);
				}else{
					f = VerificaVariavel(linhas[1]);
				}
						
				if(TestaString(linhas[3])){
					 g = Double.parseDouble(linhas[3]);
				}else{
					g = VerificaVariavel(linhas[1]);
				}	
			}else{
				System.out.println("erro na hora de utilizar a logica se "); System.exit(0);
			}
			if(f == -010 && g == -010) { System.out.println("erro na hora de utilizar o se "); System.exit(0); }
			te = log.logico(linhas, f, g); //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
			return te;
		
		}
		return te;
	}
	
	public boolean Imprime(String[] linhas){
		// imprimindo Strings.
		if(linhas.length > 1 && linhas[1].contains("'") ){ 
			String s = " ";
			for(int i = 1; i < linhas.length; i++){
				s = s + linhas[i] + " ";
			}
			int a = s.trim().length();
			if(s.charAt(a) != '\'') return false; // testa se na ultima posicao existe um '.
			String r = s.substring(2, (s.length() - 2)); // eliminando as ''
			System.out.println(r);
			return true;
			
		}
		// impressao 
//		if(linhas.length > 3 && linhas[1].contains("'")){
//			String r = " ";
//			for(int i = 1; i < linhas.length; i++){  // tratar erro de impressão... falta
//				r = r + linhas[i] + " ";
//			}
//			int a = r.trim().length();
//			if(r.charAt(a) != '\'') return false;
//			String im = r.substring(2, (r.length() - 2));
//			System.out.println(im);
//			return true;
//		}

		// impressao de variaveis	
		else if(linhas.length == 2){ 
			if(TestaString(linhas[1])){ // é um numero.
				System.out.println(linhas[1]);
			}else{
				double a = VerificaVariavel(linhas[1]);
				if(a == -010) return false;
				System.out.println(a);
				return true;
			}
		}
		// impressão com operadores.
		else if(linhas.length > 3 && linhas.length < 5 && linhas[2].equals("+") || linhas[2].equals("-") || linhas[2].equals("*") || linhas[2].equals("/")){
			double num = -1, num1 = -1;
			if(TestaString(linhas[1])){   // testa se é um numero ou uma variavel, se nao for vai verificar nas variaveis se exite.
				num = Double.parseDouble(linhas[1]);
			}else{
				num = VerificaVariavel(linhas[1]);
				if(num == -010) return false;
			}
			if(TestaString(linhas[3])){
				num1 = Double.parseDouble(linhas[3]);
			}else{
				num1 = VerificaVariavel(linhas[3]);
				if(num1 == -010) return false;
			}
			double res = op.operacoes(linhas[2], num, num1); // chama o metodo das operacoes para imprimir.
			System.out.println(res);
			return true;
									
		}
		return false;
					
	}
	
	public boolean Variavel(String[] linhas){
		double qe = 0;
		double ses = -5555;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas[1].equals("=")){
			if(TestaString(linhas[2])){
				ses = Double.parseDouble(linhas[2]);
			}else{
				ses = VerificaVariavel(linhas[2]);
				if(ses == -010) return false;
			}
			for(int i = 0; v[i] != null; i++){
				if(v[i].getNome().equals(linhas[0])){
					v[i].setValor(ses);
					return true;
				}
			}
			return false;		
		}
		//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(TestaString(linhas[3])){
				qe = Double.parseDouble(linhas[3]);
			}else{
				qe = VerificaVariavel(linhas[3]);
				if(qe == -010) return false;
			}
				for(int k = 0; v[k] != null; k++){  // testa para verificar se na linhas[0] é uma variavel.
					if(v[k].getNome().equals(linhas[0])){
						v[k].setValor(op.RaizQuadrada(qe));
						return true;
					}
				}
			return false;
		}
		// criando variavel com atribuição. Somente com numeros.
		if(linhas.length > 3 && linhas.length < 5 && !TestaString(linhas[1]) && linhas[2].equals("=")){
			for(int i = 0; i < v.length; i++){
				if(v[i] == null){
					String nome = linhas[1];
					double rec = Double.parseDouble(linhas[3]); 
					for(int t = 0; v[t] != null; t++){
						if(v[t].getNome().equals(nome)){  // chama o va que é do tipo variavel para testar se ja existe alguma variavel com aquele nome.
							return false;
						}
					}
					v[i] = new Variaveis();
					v[i].CriarVariavel(nome, rec);
					return true;
				}
			}
			// criacao de variavel sem atrubuicao.	
			}else if(linhas.length < 3 &&  !TestaString(linhas[1]) ){
				if(!linhas[0].equals("inteiro")) return false;
				for(int i = 0; i < v.length; i++){
					if(v[i] == null){
						String nome = linhas[1];
						for(int t = 0; v[t] != null; t++){
							if(v[t].getNome().equals(nome)){
								return false;
							}
						}
						v[i] = new Variaveis();
						v[i].CriarVariavel(nome);
						return true;
					}
						
				}
				return false;
				
				//atribuicao para variaveis com operadores.
			}else if(linhas.length > 3 && linhas.length < 6 && !TestaString(linhas[0]) && linhas[3].equals("=") || linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
			//	System.out.println("teste teste");
				double se = -5555, re = -5555 ;
				boolean ar = false;
				int k;
				for(k = 0; v[k] != null; k++){ // testa se existe a variavel.
					if(v[k].getNome().equals(linhas[0])){
						ar = true;
						break;
					}
				}
				if(ar){
					if(TestaString(linhas[2])){ se = Double.parseDouble(linhas[2]);}
					else{
						for(int l = 0; v[l] != null; l++){
							if(v[l].getNome().equals(linhas[2])){
								se = v[l].getValor();
							}
						}
					}
					if(TestaString(linhas[4])){ re = Double.parseDouble(linhas[4]);}
					else{
						for(int l = 0; v[l] != null; l++){
							if(v[l].getNome().equals(linhas[4])){
								re = v[l].getValor();
							}
						}
					}
				}else{
					return false;
				}
				if(se != -5555 && re != -5555){
					String nome = linhas[3];
					double rec = op.operacoes(nome, se, re); 
					v[k].setValor(rec); // k tem a posicao da variavel da linha[0], que vai receber o valor.
					return true;
				}
				return false;		
			}
			return false;
	}
	
	public boolean Leia(String[] linhas){
		Scanner in = new Scanner(System.in);
		if(linhas.length > 1 && !TestaString(linhas[1])){ //testa se nao é um numero;
			for(int j = 0; v[j] != null; j++){
				if(v[j].getNome().equals(linhas[1])){
					v[j].setValor(in.nextDouble());
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	public boolean TestaString(String s){  // testa se a string que receber é um numero ou nao.
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  // se false ele não for numero.
        }  
        return true;  
    }
    
    public double VerificaVariavel(String n){   // verifica se uma variavel existe.
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)){
				return v[i].getValor();
			}
		}
		return -010;
		
	}
    
}
		

import java.util.Scanner;
import java.io.*;

class Sintaxe{
	Variaveis[] v = new Variaveis[100];
	Logico log = new Logico();
	Operacoes op = new Operacoes();
	
	public boolean se(String[] linhas){
		boolean te = false;
		double f = 0, g = 0, e = 0;
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
				   if(e == -010) return false;
			}else{
				e = Double.parseDouble(linhas[5]);
			}
			 if(op.Mod(f, g, e)) return true;
			 return false;		
		}
			// verifica se o se é verdade ou nao.
		else if(linhas.length > 3 && linhas.length < 5){ 
			if(TestaString(linhas[1])){   // testa se é u numero ou uma variavel...
				f = Double.parseDouble(linhas[1]);
			}else{
				f = VerificaVariavel(linhas[1]); // se a variavel existir vai receber o valor, se nao recebe -010.
			}
			if(TestaString(linhas[3])){
				 g = Double.parseDouble(linhas[3]);
			}else{
				g = VerificaVariavel(linhas[3]);
			}	
		}else{
			System.out.println("erro na hora de utilizar a logica se "); System.exit(0);
		}
		if(f == -010 || g == -010) { System.out.println("erro na hora de utilizar o se "); System.exit(0); }
		te = log.logico(linhas, f, g); //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
		return te; // retorna se a condicao é verdadeir ou falsa.
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
		// impressao de variaveis	
		else if(linhas.length == 2){ 
			if(TestaString(linhas[1])){ // chama o TestaString para verificar se um numero.
				System.out.println(linhas[1]);
			}else{
				double a = VerificaVariavel(linhas[1]); // se não for um numero, chama o verificaVariavel que retornar o valor.
				if(a == -010) return false;	// se a variavel não existir retorna -010 dando false.
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
		double qe;
		double ses;
		
		// atribuição de uma variavel para outra, ex: a = b
		if(linhas.length < 4 && linhas[1].equals("=")){
			if(TestaString(linhas[2])){
				ses = Double.parseDouble(linhas[2]);
			}else{
				ses = VerificaVariavel(linhas[2]);
				if(ses == -010) return false;
			}
			int c = getVariavel(linhas[0]);
			if(c == 1000) return false;	
			v[c].setValor(ses);
			return true;
		}	
		
		//chama o metodo para calcular a raiz quadradra.
		else if(linhas.length < 5 && !TestaString(linhas[0]) && linhas[1].equals("=") && linhas[2].equals("#")){
			if(TestaString(linhas[3])){
				qe = Double.parseDouble(linhas[3]);
			}else{
				qe = VerificaVariavel(linhas[3]);
				if(qe == -010) return false;
			}
			int c = getVariavel(linhas[0]);
			if(c == 0) return false;
			v[c].setValor(op.RaizQuadrada(qe)); // v[c] vai receber a valor do calcula da raiz quadrada.
			return true;
		}
		
		// criando variavel com atribuição. Somente com numeros.
		if(linhas.length > 3 && linhas.length < 5 && !TestaString(linhas[1]) && linhas[2].equals("=")){
			String nome = linhas[1];
			double rec = Double.parseDouble(linhas[3]);
			int a = getVariavel(nome);		
			if(a != 1000) return false; // se retornar outro valor, a variavel nome ja existe.
			int c = CriarVariavel(); // retorna o primeiro indice de v que esta vazio para criar uma nova variavel.	
			if(c == 1000) return false; // se retornar 1000 ja terminou as posiçoes de v.
			v[c] = new Variaveis(nome, rec);
			return true;
			
			// criacao de variavel sem atrubuicao.	
		}else if(linhas.length < 3 &&  !TestaString(linhas[1]) ){
			if(!linhas[0].equals("inteiro")) return false;
			String nome = linhas[1];
			int a = getVariavel(nome);
			if(a != 1000) return false;
			int c = CriarVariavel();
			if(c == 1000) return false;
			v[c] = new Variaveis(nome);
			return true;

		//atribuicao para variaveis com operadores.
		}else if(linhas.length > 3 && linhas.length < 6 && !TestaString(linhas[0]) && linhas[3].equals("=") || linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
			double se = -5555, re = -5555 ;
			boolean ar = false;
			int a = getVariavel(linhas[0]); // linha[0] variavel que vai receber o valor, a tem a posicao.
			if(a == 1000) return false; // variavel na linhas[0] nao exite
			if(TestaString(linhas[2])){ // se for numero
				 se = Double.parseDouble(linhas[2]);
			}
			else{
				int b = getVariavel(linhas[2]);
				if(b == 1000) return false; // variavel nao existe.
				se = VerificaVariavel(linhas[2]); // recebe o valor da variavel da linha[2]
			}
			if(TestaString(linhas[4])){
				 re = Double.parseDouble(linhas[4]);
			}
			else{
				int b = getVariavel(linhas[4]); // se a variavel existir retorna a posicao do vetor que ela esta.
				if(b == 1000) return false; // variavel nao existe
				re = VerificaVariavel(linhas[4]); // re recebe o valor da variavel linha[4].
			}
			String nome = linhas[3]; // linhas[3] tem o operador.
			double rec = op.operacoes(nome, se, re); 
			v[a].setValor(rec); // a é o indice da variavel que vai receber o valor.
			return true;
		}
		return false;
	}
	// metodo para ler informações do teclado.
	public boolean Leia(String[] linhas){
		Scanner in = new Scanner(System.in);
		if(linhas.length > 1 && !TestaString(linhas[1])){ //testa se nao é um numero;
			int a = getVariavel(linhas[1]); // recebe o indice ao esta a variavel.
			if(a == 1000) return false; // variavel linhas[1] nao existe 
			v[a].setValor(in.nextDouble()); // v[a] recebe a entrada do teclado.
			return true;
		}
		return false;
	}
	// testa se a string é um numero ou um digito, se retornar true é um numero.
	public boolean TestaString(String s){  
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  
        }  
        return true;  
    }
    //Retornar o valor de quem chamou, se nao encontrar a variavel retorna -010.
    public double VerificaVariavel(String n){  
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return v[i].getValor();
		}
		return -010;
	}
	// testa se existe a variavel e retorna a posicao do vetor.
	public int getVariavel(String n){
		for(int i = 0; v[i] != null; i++){
			if(v[i].getNome().equals(n)) return i; 
		}
		return 1000;
	}
	// retorna a primeira posicao de v que esta null para criar a variavel, se nao estiver mais espaço retorna 1000.
	public int CriarVariavel(){
		for(int i = 0; i < v.length; i++){
			if(v[i] == null) return i;
		}
		return 1000;
	}		
}

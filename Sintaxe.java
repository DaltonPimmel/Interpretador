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
			
			if(linhas.length > 3 && linhas.length < 7 && linhas[2].equals("%")){
				if(testaVariavel(linhas[1])){
					 f = Double.parseDouble(linhas[1]);
				 }else{
					 for(i = 0; v[i] != null; i++){
						 if(v[i].getNome().equals(linhas[1])){
							 f = v[i].getValor();
							 break;
						 }
					 }
				 }
				 if(testaVariavel(linhas[3])){
					 g = Double.parseDouble(linhas[3]);
				 }else{
					 for(i = 0; v[i] != null; i++){
						 if(v[i].getNome().equals(linhas[3])){
							 g = v[i].getValor();
							 break;
						 }
					 }
				 }
				 if(g != -1 && f != -1){
					 e = Double.parseDouble(linhas[5]);
					 if(op.Mod(f, g, e)) return true;
					 return false;
				 }
				 return false;
				
			}
			
			if(linhas.length > 3 && linhas.length < 5){ // alterado de 5 para 7
				if(testaVariavel(linhas[1])){   // testa se é u numero ou uma variavel...
					f = Double.parseDouble(linhas[1]);
				}else{
					for(i = 0; v[i] != null; i++){
						if(v[i].getNome().equals(linhas[1])){
							f = v[i].getValor();
							break;
						}
					}
				}
						
				if(testaVariavel(linhas[3])){
					 g = Double.parseDouble(linhas[3]);
				}else{
					for(i = 0; v[i] != null; i++){
						if(v[i].getNome().equals(linhas[3])){
							g = v[i].getValor();
							//System.out.println(g);
							break;
						}
					}
				}	
				}else{
					System.out.println("erro na logica de prog..."); System.exit(0);
				}
				if(f != -1.0 && g != -1.0){
					te = log.logico(linhas, f, g); //retorna false ou verdade para seguir para a proxima linha. se ele passar por todas as condiçoes retorna verdadeiro.
					return te;
				}else{
					System.out.println("variavel nao declarada!!!"); System.exit(0);
				}
		}
		return te;
	}
	
	public boolean Imprime(String[] linhas){
		
		if(linhas.length < 3 && linhas[1].contains("'")){ // impressão de string
				String r = linhas[1].substring(1, linhas[1].length() - 1); // eliminando as ''
				System.out.println(r);
				return true;
		}
		if(linhas.length > 3 && linhas[1].contains("'")){
			String r = "";
			for(int i = 1; i < linhas.length; i++){  // tratar erro de impressão... falta
				r = r + linhas[i] + " ";
			}
			String im = r.substring(1, r.length() - 2);
			System.out.println(im);
			return true;
		}
			if(linhas.length == 2){ 
				if(!testaVariavel(linhas[1])){ // nao é um numero.
					for(int i = 0; v[i] != null; i++){
						if(linhas[1].equals(v[i].getNome())){
							System.out.println(v[i].getValor());
							return true;
						}
					//return false;
					}
				}else if(testaVariavel(linhas[1])){
					System.out.println(linhas[1]);
					return true;
				}
				return false;
			}
			if(linhas.length > 3 && linhas.length < 5 && linhas[2].equals("+") || linhas[2].equals("-") || linhas[2].equals("*") || linhas[2].equals("/")){
				double num = -1, num1 = -1;
				if(testaVariavel(linhas[1])){   // testa se é um numero ou uma variavel, se nao for vai verificar nas variaveis se exite.
					num = Double.parseDouble(linhas[1]);
				}else{
					for(int i = 0; v[i] != null; i++){
						if(linhas[1].equals(v[i].getNome())){
							num = v[i].getValor();
						}
					}
				}
				if(testaVariavel(linhas[3])){
					num1 = Double.parseDouble(linhas[3]);
				}else{
					for(int i = 0; v[i] != null; i++){
						if(linhas[3].equals(v[i].getNome())){
							num1 = v[i].getValor(); // getValor, retorna o valor da variavel se encontrar.
						}
					}
				}
				if(num != -1 && num1 != -1 ){
					double res = op.operacoes(linhas[2], num, num1);
					System.out.println(res);
					return true;
				}	
						
			}
			return false;
					
	}
	
	public boolean Variavel(String[] linhas){
		
		if(linhas.length > 3 && linhas.length < 5 && !testaVariavel(linhas[1]) && linhas[2].equals("=")){
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
			}else if(linhas.length > 1 && linhas.length < 4 &&  !testaVariavel(linhas[1]) ){
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
			}else if(linhas.length > 3 && linhas.length < 6 && !testaVariavel(linhas[0]) && linhas[3].equals("+") || linhas[3].equals("-") || linhas[3].equals("*") || linhas[3].equals("/")){
				double se = -5555, re = -5555 ;
				boolean ar = false;
				int k;
				for(k = 0; v[k] != null; k++){
					if(v[k].getNome().equals(linhas[0])){
						ar = true;
						break;
					}
				}
				if(ar){
					if(testaVariavel(linhas[2])){ se = Double.parseDouble(linhas[2]);}
					else{
						for(int l = 0; v[l] != null; l++){
							if(v[l].getNome().equals(linhas[2])){
								se = v[l].getValor();
							}
						}
					}
					if(testaVariavel(linhas[4])){ re = Double.parseDouble(linhas[4]);}
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
			}
			return false;
	}
	
	public boolean Leia(String[] linhas){
		Scanner in = new Scanner(System.in);
		if(linhas.length > 1 && !testaVariavel(linhas[1])){ //testa se nao é um numero;
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
	
	public static boolean testaVariavel(String s){  // testa se a string que receber é um numero ou nao.
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  // se false ele não for numero.
        }  
        return true;  
    }
    
}
		

import java.util.Scanner;
import java.io.*;

class Sintaxe{
	Variaveis[] v = new Variaveis[100];
	Variaveis va = new Variaveis();
	Logico log = new Logico();
	Operacoes op = new Operacoes();
	
	public boolean sintaxe(String l, int lin){
		//System.out.println(l);
		String[] linhas;
		boolean te = true;
		linhas = l.trim().split(" ");
			String a = linhas[0];
			switch(a){
			
				case "inteiro":
					if(linhas.length > 3 && linhas.length < 5 && !testaVariavel(linhas[1]) && linhas[2].equals("=")){
						for(int i = 0; i < v.length; i++){
							if(v[i] == null){
								String nome = linhas[1];
								double rec = Double.parseDouble(linhas[3]); 
								if(va.TestaVariavel(nome, v)){  // chama o va que é do tipo variavel para testar se ja existe alguma variavel com aquele nome.
									v[i] = new Variaveis();
									v[i].CriarVariavel(nome, rec);
									break;
								}
								System.out.println("erroooo variavel '" + nome + "' foi criada duas vezes na linha " + (lin + 1)); System.exit(0);
							}
						}
					
					}else if(linhas.length < 3 && linhas.length > 1 && !testaVariavel(linhas[1])){
						for(int i = 0; i < v.length; i++){
							if(v[i] == null){
								String nome = linhas[1];
								if(va.TestaVariavel(nome, v)){
									v[i] = new Variaveis();
									v[i].CriarVariavel(nome);
									break;
								}
								System.out.println("erroooo variavel '" + nome + "' foi criada duas vezes na linha " + (lin + 1 )); System.exit(0);
							}
						
						}
					}else{
						System.out.println("erro de sintaxe na declaracao da variavel!!!"); System.exit(0);
					}
				break;
				
				case "se":
					double f = -1, g = -1;
					if(linhas.length > 3 && linhas.length < 5){
						if(testaVariavel(linhas[1])){   // testa se é u numero ou uma variavel...
							 f = Double.parseDouble(linhas[1]);
						}else{
								for(int i = 0; v[i] != null; i++){
									if(v[i].getNome().equals(linhas[1])){
										f = v[i].getValor();
										//System.out.println(f);
										//System.out.println(linhas[3]);
										break;
									}
								}
						}
						
						if(testaVariavel(linhas[3])){
							 g = Double.parseDouble(linhas[3]);
						}else{
							for(int i = 0; v[i] != null; i++){
								if(v[i].getNome().equals(linhas[3])){
									g = v[i].getValor();
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
						System.out.println("variavel nao declaada!!!"); System.exit(0);
					}
				break;
				
				case "imprime":
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
						if(!testaVariavel(linhas[1])){
							for(int i = 0; v[i] != null; i++){
								if(linhas[1].equals(v[i].getNome())){
									System.out.println(v[i].getValor());
									return true;
								}
							}
						}else if(testaVariavel(linhas[1])){
							System.out.println(linhas[1]);
							return true;
						}
						System.out.println("errooooo na impressao da variavel " + (lin + 1) + " !!!");
						System.exit(0);
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
						return true;
					}
					System.out.println("erroooo na funcao imprime na linha " + (lin + 2) + ("!!!")); System.exit(0);
					
				break;
				
				default:
					double li;
					for(int i = 0; v[i] != null; i++){
						if(a.equals(v[i].getNome())){
							if(linhas.length > 0 && linhas.length < 4 && linhas[1].equals("=")){
								if(testaVariavel(linhas[2])){
									li = Double.parseDouble(linhas[2]);
									v[i].setValor(li); 
									//System.out.println(v[i].getNome() + v[i].getValor());
									return true;
								}
							}  // falta fazer se for uma variavel...
						}
					}
				break;
			}
			return te;
	}
			
		
		
	
	public void imprime(){  // utilizado para teste.
		for(int i = 0; v[i] != null; i++){
			System.out.println(v[i].getNome());
		}
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
		
	//for(int i = 0; i < l.length; i++) System.out.println(l[i]);
		//if(l[4].equals(",")){
			//String[] l1 = substring(4)// verificar

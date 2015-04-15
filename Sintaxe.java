import java.util.Scanner;
import java.io.*;

class Sintaxe{
	Variaveis[] v = new Variaveis[100];
	Variaveis va = new Variaveis();
	Logico log = new Logico();
	
	public boolean sintaxe(String l){
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
								int rec = Integer.parseInt(linhas[3]); 
								if(va.TestaVariavel(nome, v)){
									v[i] = new Variaveis();
									v[i].CriarVariavel(nome, rec);
									break;
								}
								System.out.println("variavel '" + nome + "' foi criada duas vezes"); System.exit(0);
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
								System.out.println("variavel '" + nome + "' foi criada duas vezes"); System.exit(0);
							}
						
						}
					}else{
						System.out.println("erro de sintaxe na declaracao da variavel!!!"); System.exit(0);
					}
				break;
				
				case "se":
					double f = -1, g = -1;
					if(linhas.length > 3 && linhas.length < 5){
						if(testaVariavel(linhas[1])){
							 f = Double.parseDouble(linhas[1]);
						}else{
								for(int i = 0; v[i] != null; i++){
									if(v[i].getNome().equals(linhas[1])){
										f = v[i].getValor();
										System.out.println(f);
										System.out.println(linhas[3]);
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
			}
			return te;
	}
			
		
		
	
	public void imprime(){
		for(int i = 0; v[i] != null; i++){
			System.out.println(v[i].getNome());
		}
	}
	
	public static boolean testaVariavel(String s){
		  try {  
            Long.parseLong (s);  
        } catch (NumberFormatException ex) {  
            return false;  
        }  
        return true;  
    }
    
}
		
	//for(int i = 0; i < l.length; i++) System.out.println(l[i]);
		//if(l[4].equals(",")){
			//String[] l1 = substring(4)// verificar

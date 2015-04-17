

class Interpretador {
	private String[] linhas;
    private Sintaxe sinta = new Sintaxe();
    private String[] tok;
    private String[] aux;
    private String a;
   
    
    public void interpreta(String l[]) {
		int rec = 0;
		for(int i = 0; i < l.length; i++){
			if(l[i] != null && l[i].equals(" ")){
				linhas = l[i].split(" ");
				if(!linhas[0].equals("inicio") && !linhas[1].equals("programa()")){
					System.out.println("Nao foi posivel localizar o inicio do programa!!!");
					System.exit(0);
				}
				break;
			}
		}
		
		for(int j = 0; j < l.length && l[j] != null; j++){
			if(l[j].length() > 1){
				rec++;
			}
		}
		//System.out.println(rec);
		for(int cont = 0; cont <= l.length && l[cont] != null; cont++){
			if(l[cont].length() > 1){
				tok = l[cont].trim().split(" ");
				a = tok[0];
				switch(a){
				
					case "se":
						for(int i = 0; i < l.length && l[i] != null; i++){
							aux = l[i].trim().split(" ");
							if(aux.length > 1 && aux[0].equals("fim") && aux[1].equals("se")){
								System.out.println("deu certo o se");
								if(sinta.se(tok)){
									break;
								}else{
									cont = i;
									System.out.println(cont);
									System.out.println(rec);
									break;
								}				
							}
							else{
								if(cont > rec){
									System.out.println(cont);
									System.out.println(rec);
									System.out.println("errooooooooooooooooooo");
									System.exit(0);
								}
								
							}
			
						}
					break;
					
					case "imprime":
							if(sinta.Imprime(tok)){
								break;
							}else{
								System.out.println("erro na impressao!!!"); System.exit(0);
							}
					break;
				}
					
				
			}
			
		}
	}
		
	/*	
		this.linhas = l;
		int i;
		for(i = 0; i < linhas.length; i++){
			if(this.linhas[i] != null && !this.linhas[i].equals(" ")){
				String token = linhas[i].trim();
				int ind = i;
				int rec = 0;
				//if(sinta.sintaxe(token, ind)){   // na logica vai tem que voltar o verdadeiro o falso, para não executar a linha de baixo, por exemplo se der falso ate nao acar a linha que tem o fim se nao executa
					tok = linhas[i].trim().split(" ");
					System.out.println(tok[0]);
					System.out.println(l.length);
					
					for(i = 0; l[i] != null; i++){
						 rec++;
					}
					i = 0;
					while(i < l.length && l[i] != null){
					
						if(tok[0].equals("se") && linhas[i].contains("fim se")){ 	// problema no se, testar....
							System.out.println("deu certo o se, encontrou o fim");
							break;
						}
						else if(tok[0].equals("enquanto") && linhas[i].equals("fim enquanto")){
							System.out.println("deu certo logica enquanto...");
							break;
						}
						else if(tok[0].equals("escolha") && linhas[i].equals("fim escolha")){
							System.out.println("deu certo funcao escolha...");
							break;
						}
							
						else{
							i++;
						}
						
					}
					if(i != rec){
						if(sinta.sintaxe(token, ind))
						continue;
					}
				
			//	}
				//else{
			//		continue;
				//}
			}
				
		}
		//sinta.imprime();
		*/
    }
    



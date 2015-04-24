

class Interpretador {
	private String[] linhas;
    private Sintaxe sinta = new Sintaxe();
    private String[] tok;
    private String[] aux;
    private String a, fim, ch;
   	int teste = 0;
    boolean re, ree = false, loc = false, enq = false;
    int ve = 0, te = 1, tes = 0, q, qe, recs = 1, fi, qa;
    private boolean na = true, sn = false, verdadeiro = false;
   
    public void interpreta(String l[]) {
		int rec = 0;
	
		for(int i = 0; i < l.length; i++){  // testa se a primeira linha é o inicio do programa.
			if(l[i] != null && l[i].equals(" ")){
				linhas = l[i].split(" ");
				if(!linhas[0].equals("inicio") && !linhas[1].equals("programa()")){
					System.out.println("Nao foi posivel localizar o inicio do programa!!!");
					System.exit(0);
				}
				break;
			}
		}
		
		for(int j = 0; j < l.length && l[j] != null; j++){  // testa se a acha o fim do programa.
			if(l[j].length() > 1){ 
				rec++;
				if(l[j].equals("fim programa")){
					fim = l[j];
					fi = j;
					break;
				}
			}
		}
		
		if(fim == null){
			System.out.println("nao foi localizado o final do programa!!!"); System.exit(0);
		}
		
		for(int cont = 0; cont <= l.length && l[cont] != null; cont++){ // cont na linha que esta rodando o programa
			l[cont] = l[cont].trim();
			if(l[cont].length() > 1 && l[cont] != null){
				//System.out.println(l[cont]);
				if(l[cont].contains("//")){	
					ch = l[cont].substring(0, 2);
					if(ch.equals("//")){
						continue;
					}		// comentarios ok.
					qa = l[cont].indexOf("//");
					l[cont] = l[cont].substring(0, qa);
				}
				tok = l[cont].trim().split(" ");
				a = tok[0];
				
				switch(a){
				
					case "se":
						//System.out.println(cont);
						for(int i = cont; i <= l.length && l[i] != null; i++){
							l[i] = l[i].trim();
							if(l[i].equals("fim se")){	
								loc = true; // controla se achou o se.
								na = false; // controla o senao.
								if(sinta.se(tok)){	
									verdadeiro = true;
								
									break;
								}else{
									cont = i;
									verdadeiro = false;
									break;
								}
							}	
						}
						if(!loc){
							System.out.println("erro no se!!!"); System.exit(0);
						}
						loc = false;
						
					break;
					
					case "senao":
						int i;
						if(na){ // se o na for verdadeiro, ele nao achou um se antes.
							System.out.println(" testettestet nao he posivel utilizar o senao sem o se antes!!!");
							System.exit(0);
						}else{
						for( i = cont; i < fi && l[i] != null; i++){
							l[i] = l[i].trim();
							if(l[i].equals("fim senao")){
								sn = true;
								if(verdadeiro){
									verdadeiro = false; // se for verdadeiro nao vai para o senao
									cont = i;	// se for verdadeiro ele nao executa o senao ele pula para a linha que encontrou o fim senao
									break;
								}	
								break;						
							}
						}
					}
					if(!sn){
						System.out.println("nao foi localizado o fim do senao!!!");
						System.exit(0);
					}
					sn = false;
					na = true;
					
					break;
					
					case "imprime":
							if(sinta.Imprime(tok)){
								break;
							}else{
								System.out.println("erro na impressao!!!"); System.exit(0);
							}
					break;
					
					case "inteiro":
						if(sinta.Variavel(tok)){
							break;
						}else{
							System.out.println("Erro na criação da variavel, ou variavel ja foi criada!!!"); System.exit(0);
						}
						
					break;
					
					case "enquanto":
						q = cont;
						for(int p = cont; p < fi; p++){
						//	System.out.println(l[p]);
							l[p] = l[p].trim();
							if(l[p].equals("fim enquanto")){  // o else esta dentro do laço, por isso que esta dando problema.
								qe = p;
								enq = true; // controla se achou o final do enquanto.
								if(sinta.se(tok)){
									ree = true; // se retornas verdadeiro continua executando.
									break;
								}else{
									ree = false;
									cont = p;
									break;
								}	
							}
						}
						if(!enq){
							System.out.println("Nao localizado o fim do enquanto na linha" + (cont + 1)); System.exit(0);
						}
						enq = false;
					break;
					
					case "fim":
						if(l[cont].equals("fim enquanto")){
							if(ree){
								cont = q - 1;  // se retornar o verdadeiro o enquanto ele executa para baixo até achar o fim enquando, voltando para a linha do enquanto, pois q tem a posicao do enquanto..
							}
							continue;
						}else if(l[cont].equals("fim se") || l[cont].equals("fim senao")){
							break;
						}
					break;
					
					case "escolha":
					
					break;
					
					case "leia":
						if(sinta.Leia(tok))break;
						System.out.println("Erro na hora de ler do teclado"); System.exit(0);
					break;
					
					default:
						if(!a.equals("inicio")){  // se nao achar nenhum dos case, cai no default, tirando somente o inicio que estava caindo junto no default.
							if(sinta.Variavel(tok)){
								break;
							}else{
								System.out.println("Erro de sintaxe na linhas!!!" + (cont + 1)); System.exit(0);
							}
						}
					break;
				}
			}
			
		}
	}
	
}
    

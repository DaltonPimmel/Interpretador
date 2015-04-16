

class Interpretador {
	private String[] linhas;
    private Sintaxe sinta = new Sintaxe();
    private String[] tok;
    private int i;
    
    public void interpreta(String l[]) {
		
		for(i = 0; i < l.length; i++){
			if(l[i] != null && l[i].equals(" ")){
				linhas = l[i].split(" ");
				if(!linhas[0].equals("inicio") && !linhas[1].equals("programa()")){
					System.out.println("Nao foi posivel localizar o inicio do programa!!!");
					System.exit(0);
				}
				break;
			}
		}
		
		
		this.linhas = l;
		for(i = 0; i < linhas.length; i++){
			if(this.linhas[i] != null && !this.linhas[i].equals(" ")){
				String token = linhas[i].trim();
				int ind = i;
				if(sinta.sintaxe(token, ind)){   // na logica vai tem que voltar o verdadeiro o falso, para não executar a linha de baixo, por exemplo se der falso ate nao acar a linha que tem o fim se nao executa
					continue;
				}else{
					tok = linhas[i].trim().split(" ");
					System.out.println(tok[0]);
					System.out.println(linhas.length);
					while(i < linhas.length){
						if(linhas[i] == null){
							 i++;
							 if(i + 1 == linhas.length){
								 System.out.println("errooooo!!!"); System.exit(0);
							 }
							 continue;
						 }
						if(tok[0].equals("se") && linhas[i].equals("fim se")){ 
							System.out.println(tok[0]);
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
				}
			}
				
		}
		sinta.imprime();
    }
    
}


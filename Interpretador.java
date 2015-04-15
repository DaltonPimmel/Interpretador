

class Interpretador {
	private String[] linhas;
    private Sintaxe sinta = new Sintaxe();
    
    public void interpreta(String l[]) {
		
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
		
		
		this.linhas = l;
		for(int i = 0; i < linhas.length; i++){
			if(this.linhas[i] != null && !this.linhas[i].equals(" ")){
				String token = linhas[i].trim();
				if(sinta.sintaxe(token));   // na logica vai tem que voltar o verdadeiro o falso, para não executar a linha de baixo, por exemplo se der falso ate nao acar a linha que tem o fim se nao executa
			}
				
		}
		sinta.imprime();
    }
    
}


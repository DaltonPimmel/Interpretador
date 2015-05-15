// Dalton Luiz Pimmel
// dalton.cco1@gmail.com

// Classe InicioFim, testa a sintaxe do inicio e o fim programa, retorna a quantidade linhas.

class InicioFim{
	
	private Interpretador c;
	private int fi;
	private String fim = null;
	String tok;
	
	public InicioFim(Interpretador in){
		this.c = in;
	}
	
	public int Comeco(String[] l){
		String[] linhas;
		for(int i = 0; i < l.length; i++){  // testa se a primeira linha é o inicio do programa.
			if(l[i] != null && l[i].length() > 1){
				l[i] = l[i].replaceAll("\\s+"," ");
				l[i] = l[i].trim();
				tok = l[i].substring(0, 2); // recebe os dois primeiros caracteres, se for //, ignora a linha.
				if(tok.equals("//")) continue;
				if(!l[i].equals("inicio programa()")) c.erro.Erro12();
				break;
			}
		}
		for(int j = 0; j < l.length && l[j] != null; j++){  // testa se a acha o fim do programa.
			if(l[j].length() > 1){ 
				l[j] = l[j].replaceAll("\\s+"," ");
				l[j] = l[j].trim();
				if(l[j].equals("fim programa")){
					fim = l[j];
					fi = j;
					break;
				}
			}
		}

		if(fim == null) c.erro.Erro13();	
		return fi; // fi aonde encontrou o final do programa;
	}
}

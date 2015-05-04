
class InicioFim{
	
	private Interpretador c;
	private int fi;
	private String fim = null;
	
	public InicioFim(Interpretador in){
		this.c = in;
	}
	
	public int Comeco(String[] l){
		String[] linhas;
		for(int i = 0; i < l.length; i++){  // testa se a primeira linha é o inicio do programa.
			if(l[i] != null && l[i].length() > 1){
				l[i] = l[i].trim();
				if(!l[i].equals("inicio programa()")){
					System.out.println("Nao foi posivel localizar o inicio do programa!!! 'inicio programa()'");
					System.exit(0);
				}
				break;
			}
		}
		for(int j = 0; j < l.length && l[j] != null; j++){  // testa se a acha o fim do programa.
			if(l[j].length() > 1){ 
				if(l[j].equals("fim programa")){
					fim = l[j];
					fi = j;
					break;
				}
			}
		}

		if(fim == null){
			System.out.println("nao foi localizado o final do programa!!! 'fim programa'"); System.exit(0);
		}
		return fi; // fi aonde encontrou o final do programa;
	}
}

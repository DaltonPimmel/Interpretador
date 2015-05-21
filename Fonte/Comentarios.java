//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Classe Comentarios, caso n classe interpretador seja encontrado as barras (//) ele chama a classe para verificar a sintaxe,
//eliminado a linha toda se o // estiver no inicio, ou parcial se estiver no meio da lado. 

class Comentarios{
	
	private String ch;
	private int posicao;
	
	public String Comentario(String l){
		if(l.contains("//")){	
			ch = l.substring(0, 2);
			if(ch.equals("//")){
				return l ;
			}	
			posicao = l.indexOf("//"); // se o comentario estiver no meio da linha.
			l = l.substring(0, posicao);
			return l;
		}
		return l;
	}			
}

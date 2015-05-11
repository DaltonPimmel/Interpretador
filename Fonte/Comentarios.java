
class Comentarios{
	
	private String ch;
	private int qa;
	
	public String Comentario(String l){
		if(l.contains("//")){	
			ch = l.substring(0, 2);
			if(ch.equals("//")){
				return l ;
			}	
			qa = l.indexOf("//"); // se o comentario estiver no meio da linha.
			l = l.substring(0, qa);
			return l;
		}
		return l;
	}			
}

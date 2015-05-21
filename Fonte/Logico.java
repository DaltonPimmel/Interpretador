//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Armazena os operadores logicos, que são utilizados na classe Enquanto e CondicaoSe, recebe dois valores e uma string contendo
//o tipo de operação logica, e retorna a condição.

class Logico{
	
	public boolean Log(String[] linhas, double a, double b){
		
		if(linhas[2].equals(">")){
			if(a > b) return true;
			return false;
		}
		if(linhas[2].equals("<")){
			if(a < b) return true;
			return false;
		}
		if(linhas[2].equals("==")){
			if(a == b) return true;
			return false;
		}
		if(linhas[2].equals("!=")){
			if(a != b) return true;
			return false;
		}
		if(linhas[2].equals(">=")){
			if(a >= b) return true;
			return false;
		}
		if(linhas[2].equals("<=")){
			if(a <= b) return true;
			return false;
		}
		return false;
	}


}

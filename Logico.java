class Logico{
	
	public boolean logico(String[] linhas, double a, double b){
	
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

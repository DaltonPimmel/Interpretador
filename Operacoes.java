class Operacoes{

	public double operacoes(String n, double a, double b){
		if(n.equals("+")){
			return a + b;
		}
		if(n.equals("-")){
			return a - b;
		}
		if(n.equals("*")){
			return a * b;
		}
		if(n.equals("/")){
			return a / b;
		}
		
		return 0;
	}
	
	public boolean Mod(double a, double b, double c){
		if(a % b == c)return true;
		return false;
	}

}

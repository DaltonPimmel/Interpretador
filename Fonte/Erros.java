
class Erros{

	public void Erro1(int cont){
		System.out.println("Problema na hora de utilizar o senao, nao he posivel utilizar o senao separado do se, erro na linhas " + (cont + 1)); System.exit(0);	
	}
	public void Erro2(String l, int cont){
		System.out.println("Erro na linha " + (cont + 1) + " Variavel '" + l + " ' he uma string"); System.exit(0);
	}
	public void Erro3(int cont){
		System.out.println("Erro de sintaxe na linha ' " + (cont + 1) + " ' na hora de utilizar a condicao 'se'"); System.exit(0);
	}
	public void Erro4(String l, int cont){
		System.out.println("Problema na hora calcular a raiz quadrada, variavel nao existe");
	}
	public void Erro5(String l, int cont){
		System.out.println("Erro na linha " + (cont + 1) + " Variavel '" + l + "' nao existe"); System.exit(0);
	}
	public void Erro6(int cont){
		System.out.println("Erro de sintaxe na linha " + (cont + 1)); System.exit(0);	
	}
	public void Erro7(String n, int cont){
		System.out.println("Erro na declaracao da variavel ' " + n + " ' valor nao corresponde ao tipo de variavel, linha: " + (cont + 1));
		System.exit(0);
	}
	public void Erro8(String n, int cont){
		System.out.println("Variavel ' " + n + " ' ja declarada, linha: " + (cont + 1)); System.exit(0);
	}
	public void Erro9(){
		System.out.println("Limetes para declaracao de variaveis esgotado"); System.exit(0);
	}
	
	
	


}

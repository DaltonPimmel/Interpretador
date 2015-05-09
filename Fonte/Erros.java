
class Erros{

	public void Erro1(){
		System.out.println("Problema na hora de utilizar o senao, nao he posivel utilizar o senao separado do se, erro na linhas "); System.exit(0);	
	}
	public void Erro2(String n, int cont){
		System.out.println("Erro na linha " + (cont + 1) + " variavel '" + n + "' he uma string"); System.exit(0);
	}
	public void Erro3(int cont){
		System.out.println("Erro de sintaxe na linha " + (cont + 1)); System.exit(0);
	}
	public void Erro4(){
		System.out.println("Problema na hora calcular a raiz quadrada, variavel nao existe");
	}
	public void Erro5(String n, int cont){
		System.out.println("Erro na linha " + (cont + 1) + " Variavel '" + n + "' nao existe"); System.exit(0);
	}
	public void Erro6(){
		System.out.println("Erro de sintaxe na linha "); System.exit(0);	
	}
	public void Erro7(){
		System.out.println("Erro na declaracao da varivalor nao corresponde ao tipo de variavel, linha: ");
		System.exit(0);
	}
	public void Erro8(String n, int cont){
		System.out.println("Erro na linha " + (cont + 1) + " Variavel '" + n + "' ja declarada" ); System.exit(0);
	}
	public void Erro9(){
		System.out.println("Limetes para declaracao de variaveis esgotado"); System.exit(0);
	}
	public void Erro10(){
		System.out.println("Nao he posivel continuar o programa depois do 'fim programa'\n Erro na linha:"); System.exit(0);
	}
	public void Erro11(int cont){
		System.out.println("Erro na impressao da String na linha " + (cont + 1) + ", Para impressao de String utiliza-se ' String ' "); System.exit(0);
	}
	public void Erro12(){
		System.out.println("Nao foi posivel localizar o inicio do programa!!! 'inicio programa()'"); System.exit(0);			
	}
	public void Erro13(){
		System.out.println("nao foi localizado o final do programa!!! 'fim programa'"); System.exit(0);
	}
	public void Erro14(int cont){
		System.out.println("Erro na linha " + (cont + 1) + " nao foi localizado o final do senao"); System.exit(0);
	}
	public void Erro15(int cont){
		System.out.println("Erro na linha " + (cont + 1) + "nao he posivel utilizar o senao antes do se"); System.exit(0);
	}
	public void Erro16(int cont){
		System.out.println("Erro na linha " + (cont + 1) + "nao foi localizado o final do se"); System.exit(0);
	}

}

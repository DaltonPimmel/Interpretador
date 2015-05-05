import java.util.Scanner;

class LerTeclado{
	
	Interpretador i;
	
	public LerTeclado(Interpretador a){
		this.i = a;
	}

	public boolean Leia(String[] linhas, int cont){
		Scanner in = new Scanner(System.in);
		if(linhas.length > 1 && linhas.length < 3 && !i.TestaString(linhas[1])){ //testa se nao é um numero;
			int a = i.getVariavel(linhas[1]); // recebe o indice ao esta a variavel.
			if(a == 1000){
				System.out.println("Nao foi imposivel encontrar a variavel ' " + linhas[1] + " ' na linha " + (cont + 1));
				System.exit(0);
			} // variavel linhas[1] nao existe 
			i.v[a].setValor(in.nextDouble()); // v[a] recebe a entrada do teclado.
			return true;
		}
		System.out.println("Erro de sintaze na linhas " + (cont + 1) + " , Verificar manual da linguagem");
		System.exit(0);
		return false;
	}
	
}

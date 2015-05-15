//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Classe LerTeclado, lê informações do teclado, e verifica a sintaxe e se a variavel que ira arzanenar o valor existe

import java.util.Scanner;

class LerTeclado{
	
	Interpretador i;
	
	public LerTeclado(Interpretador a){
		this.i = a;
	}

	public void Leia(String[] linhas, int cont){
		Scanner in = new Scanner(System.in);
		
		String rec = linhas[cont].replaceAll("\\s+"," ");
		linhas = rec.trim().split(" ");
	
		
		if(linhas.length > 1 && linhas.length < 3 && !i.TestaString(linhas[1])){ //testa se nao é um numero;
			Variaveis a = i.getVariavel(linhas[1]);
			if(a == null) i.erro.Erro5(linhas[1], cont);
			if(a.getTipo().equals("int")){
				 double f = (in.nextDouble()); // se for um int, e receber um double ele converte para int.
				 int p = (int)f;  // no caso de digitar um double em uma variavel que é do tipo int, ele converte para int
				 a.setValor(p);
			 }
			else if(a.getTipo().equals("double")) a.setValor(in.nextDouble());
			else a.setValor(in.nextLine());
		}else i.erro.Erro5(linhas[1], cont);
		
	}
	
}

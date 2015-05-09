import java.util.Scanner;

class LerTeclado{
	
	Interpretador i;
	
	public LerTeclado(Interpretador a){
		this.i = a;
	}

	public void Leia(String[] linhas, int cont){
		Scanner in = new Scanner(System.in);
		if(linhas.length > 1 && linhas.length < 3 && !i.TestaString(linhas[1])){ //testa se nao é um numero;
			Variaveis a = i.getVariavel(linhas[1]);
			if(a == null) i.erro.Erro7();
			if(a.getTipo().equals("int")) a.setValor(in.nextInt());
			else if(a.getTipo().equals("double")) a.setValor(in.nextDouble());
			else a.setValor(in.nextLine());
		}else i.erro.Erro7();
		
	}
	
}

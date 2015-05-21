//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Classe responsavel na criação das variaveis, ele tem a classe Variaveis, onde ira armazenar um nome, um tipo e um valor. A classe Declaração 'tem' AtribuiçãoVariaveis, pois quando a uma atribuição de variaveis 
// para o interpretador interpretar, ele não sabe o que significa, pois não é uma palavra reservada, neste caso ele chama a classe Declarar, que no caso se não for uma declaração, chama a classe 
// AtribuicaoVariaveis.


class Declaracao{
	
	Interpretador inter;
	
	public Declaracao(Interpretador i){
		this.inter = i;
	}

	public void Declarar(String[] linha, int cont){

		String tipo, nome;
		Object valor = new Object();
		double qe = 0, ses = 0;
		int p = 0;
		String[] linhas;
		
		
		linhas = linha[cont].trim().split(" ");
			
		// criacao de variaveis.
		if(linhas[0].equals("int") || linhas[0].equals("double") || linhas[0].equals("string")){
		
			if(!linhas[0].equals("string")){
				if(linhas.length > 1 && linhas.length < 5 && !inter.TestaString(linhas[1])){
					nome = linhas[1];
					tipo = linhas[0];
					Variaveis a = inter.getVariavel(nome);
					if(a != null) inter.erro.Erro8(nome, cont); // variavel existe.
					a = new Variaveis();
					if(linhas.length == 2){ // variavel sem atribuição
						a.setNome(nome);
						a.setTipo(tipo);
						inter.AdicionaVar(a);
					}else if(linhas.length > 2 && linhas[2].equals("=") && linhas.length < 5){ // variavel com atribuição,
						if(inter.isInt(linhas[3]) || inter.isDouble(linhas[3])){
							ses = Double.parseDouble(linhas[3]);
							if(linhas[0].equals("int")){
								p = (int)ses;  // caso a variavel seja int ele converte.
								valor = p; 
							}else valor = ses;
						}
						a.setNome(nome); // setando os valores.
						a.setTipo(tipo);
						a.setValor(valor);
						inter.AdicionaVar(a); // criando a variavel.			
					}
				}		
			}else {  //declaracao de strings.
				if(!inter.TestaString(linhas[1])){
					nome = linhas[1];
					tipo = linhas[0];
					Variaveis a = inter.getVariavel(nome);
					if(a != null) inter.erro.Erro8(nome, cont); // variavel existe.
					a = new Variaveis();
					if(linhas.length > 2 && linhas[2].equals("=")){
						String lin = " ";
						int f = linhas.length;
						for(int y = 3; y < f; y++) lin += " " + linhas[y].trim();	
						lin = lin.trim();
						String ch = lin.substring(0, 1); int d = lin.length();
						String ch1 = lin.substring((d - 1), d);
						if(ch.equals("\'") && ch1.equals("\'")){		
							lin = lin.replace("\'", " "); lin = lin.trim();
							a.setNome(nome);
							a.setTipo(linhas[0]);
							a.setValor(lin);
							inter.AdicionaVar(a);
						}else inter.erro.Erro3(cont);
					}	
					else if(linhas.length == 2 && !inter.TestaString(linhas[1])){
						a.setNome(nome);
						a.setTipo(tipo);
						inter.AdicionaVar(a);
					}
				}else inter.erro.Erro3(cont);
			}
		}
	
	}
}

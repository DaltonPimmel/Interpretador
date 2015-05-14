//Dalton Luiz Pimmel
//dalton.cco1@gmail.com

// Classe dos laços, recebe as linhas e testas se as condições são verdasdeira ou falsa, e retorna a linha aonde deve -se continuar
// o cidigo. 

class Enquanto{

	Interpretador in;
	private int k;
	private String[] tok;
	private int cont;
	boolean h = false;
	int c = 0, test = 0, fi = 0, lt, ly, r = 0, tt = 0;
	LogEnq[] log = new LogEnq[1000000]; 
	
	public Enquanto(Interpretador i){
		this.in = i;
	}
	
	public int Enquan(String[] linha, int l){
		
		String rec = " ";
		rec = in.EspacoEmBranco(linha[l]); // metodo para tirar os espaços em branco
		c = 0;
		int pp = l;;
		String nome;
		double e = 0, d = 0;
		String[] aux;
		aux = rec.trim().split(" ");
		ly = l;
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
				linha[k] = in.EspacoEmBranco(linha[k]); // passa no laço retirando os espaçoes em branco.
				linha[k] = linha[k].trim();
				pp++;
				if(linha[k].length() > 1 && linha[k] != null){	
					tok = linha[k].trim().split(" ");
					if(tok[0].equals("enquanto")) c++;
					if(linha[k].equals("fim enquanto")){
						r = pp;
						tt++;
						if(c != 0){
							c--;
							continue;
						}
						break;		
					}
				}
			}
			if(tt == 0) in.erro.Erro19(l);	
			c = 0;
			tt = 0;
			if(in.isInt(aux[1]) || in.isDouble(aux[1])) d = Double.parseDouble(aux[1]);		
			else d = in.RetornaValor(aux[1], l);
				
			if(in.TestaString(aux[3])) e = Double.parseDouble(aux[3]);		
			else e = in.RetornaValor(aux[3], l);
		
			boolean t = in.log.Log(aux, d, e);
			int gg;
			for(gg = 0; gg < log.length; gg++){
				if(log[gg] == null){
					log[gg] = new LogEnq(l, r, t);
					break;
			}
			
				
				
		}	
				if(t) return ly;
				return r;				
	}

	// quando achou o fim enquanto, o metodo fim recebe a linha, e verifica a posicao que tem que é igual a que recebeu de parametro, e verifica se é verdadeiro ou falso,e retorna aonde deve continuar.
	public int Fim(int con){
		for(int i = 0; i < log.length && log[i] != null; i++){
			if(con == log[i].getFim()){
				if(log[i].getVer()) return log[i].getInicio() - 1;
				return (log[i].getFim());
			}
		}
		in.erro.Erro19(con);
		return 0;
	}
	// Metodo da Função Break
	public int Break(int cont, String[] l){
		int n = cont;
		int controle = 0;
		// laço para verificar se o break esta dentro de algum laço.
		for(int rt = 0; log[rt] != null; rt++){
			if(cont > log[rt].getInicio() && cont < log[rt].getFim()){
				controle++;
				break;
			}
		}
		if(controle == 0) in.erro.Erro1(cont, l[cont]);
		// procura o primeiro fim enquanto, depois do break.
		for(int o = cont + 1; o < l.length; o++){	
			if(l[o].length() > 0 && l[o] != null){
				n++;  // recebe o primeri fim enquanto
				l[o] = in.EspacoEmBranco(l[o]);
				l[o] = l[o].trim();
				if(l[o].equals("fim enquanto")) break;
			}
		}
		// procura até achar o getFim que corresponde ao n.
		for(int i = 0; log[i] != null; i++){
			if(log[i].getFim() == n) return log[i].getFim();
		}
		return 0;
	}
	
	public int Continue(){
		return (cont - 1);
	}

}

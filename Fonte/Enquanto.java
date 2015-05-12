
class Enquanto{

	Interpretador in;
	private int k;
	private String[] tok;
	private int cont;
	boolean h = false;
	int c = 0, test = 0, fi = 0, lt, ly, r = 0;
	
	public Enquanto(Interpretador i){
		this.in = i;
	}
	
	public int Enquan(String[] linha, int l){
		
		String rec = " ";
		rec = in.EspacoEmBranco(linha[l]); // metodo para tirar os espaços em branco
		
		String nome;
		double e = 0, d = 0;
		String[] aux;
		aux = rec.trim().split(" ");
		ly = l;
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
				if(linha[k].length() > 1 && linha[k] != null){
					linha[k] = in.EspacoEmBranco(linha[k]); // passa no laço retirando os espaçoes em branco.
					linha[k] = linha[k].trim();
					tok = linha[k].trim().split(" ");
					if(tok[0].equals("enquanto")) c++;
					if(linha[k].equals("fim enquanto")){
						r++;
						if(c != 0){
							c--;
							continue;
						}
						fi = k;
						if(fi > test){
							 test = fi;
							 lt = l;
						 }
						break;	
					}
				}
			}
			if(r == 0) in.erro.Erro19(l);	
			r = 0;	
			if(in.isInt(aux[1]) || in.isDouble(aux[1])) d = Double.parseDouble(aux[1]);		
			else d = in.RetornaValor(aux[1], l);
				
			if(in.TestaString(aux[3])) e = Double.parseDouble(aux[3]);		
			else e = in.RetornaValor(aux[3], l);
			
			boolean t = in.log.Log(aux, d, e);
			if(t){
				cont = l;
				h = true;
				return l;
			}
			else if(k < test){
				cont = k;
				h = false;
				return k;
			}
			h = false;
			return test;
						
						
	}

	
	public int Fim(int con){
		if(con < test && h) return (ly - 1);
		else if(cont < test && !h) return (lt - 1);
		else if(con > test) return cont;
		return (cont - 1);
	}
	public int Break(){
		return (k + 1);
	}
	public int Continue(){
		return (cont - 1);
	}

}


class Enquanto{

	Interpretador in;
	private int k;
	private String[] tok;
	private int cont;
	boolean h = false;
	int c = 0, test = 0, fi = 0, lt, ly, r = 0;
	LogEnq[] log = new LogEnq[1000]; 
	
	public Enquanto(Interpretador i){
		this.in = i;
	}
	
	public int Enquan(String[] linha, int l){
		//System.out.println(linha[l]);
		String rec = " ";
		rec = in.EspacoEmBranco(linha[l]); // metodo para tirar os espaços em branco
		c = 1;
		String nome;
		double e = 0, d = 0;
		String[] aux;
		aux = rec.trim().split(" ");
		ly = l;
			for(k = l + 1; k < linha.length && linha[k] != null; k++){
			//	System.out.println(linha[k]);
				if(linha[k].length() > 1 && linha[k] != null){
					linha[k] = in.EspacoEmBranco(linha[k]); // passa no laço retirando os espaçoes em branco.
					linha[k] = linha[k].trim();
					tok = linha[k].trim().split(" ");
					if(tok[0].equals("enquanto")) c++;
					if(linha[k].equals("fim enquanto")){
						r = k;
						if(c != 0){
							c--;
							continue;
						}
						break;		
					}
				}
			}
			if(c != 0) in.erro.Erro19(l);	
			c = 1;
			if(in.isInt(aux[1]) || in.isDouble(aux[1])) d = Double.parseDouble(aux[1]);		
			else d = in.RetornaValor(aux[1], l);
				
			if(in.TestaString(aux[3])) e = Double.parseDouble(aux[3]);		
			else e = in.RetornaValor(aux[3], l);
			System.out.println(aux);
			System.out.println(d);
			System.out.println(e);
			boolean t = in.log.Log(aux, d, e);
			System.out.println("errooooooooooooooooooo");
			System.out.println(t);
			for(int gg = 0; gg < log.length; gg++){
				if(log[gg] == null){
					log[gg] = new LogEnq(l, r, t);
					//System.out.println(l);
					//System.out.println(r);
				}
				if(l == log[gg].getInicio()){
					//System.out.println("teste");
					if(log[gg].getVer()) return (log[gg].getInicio() + 1);
					return (log[gg].getFim()) + 1;
				}
			}
		return 0;				
	}

	
	public int Fim(int con){
		for(int i = 0; i < log.length && log[i] != null; i++){
			if(con == log[i].getFim()){
				if(log[i].getVer()){
					System.out.println(log[i].getInicio());
					//System.exit(0);
					 return log[i].getInicio() - 3;
				 }
				return (log[i].getFim());
			}
		}
		return 0;
	}
	public int Break(){
		return (k + 1);
	}
	public int Continue(){
		return (cont - 1);
	}

}

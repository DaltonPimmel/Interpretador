//DaltonPimmel
//dalton.cco1@gmail.com

// Classe LogEnq, armazena as linhas de inicio e fim e a condição das classes Enquanto, e CondiçaoSe.


class LinhaCondicoes{

	private int i;
	private int f;
	private boolean v;
	
	public LinhaCondicoes(int t, int a, boolean b){
		this.i = t;
		this.f = a;
		this.v = b;
	}
	
	public void setInicio(int li){
		this.i = li;
	}
	public void setFim(int fi){
		this.f = fi;
	}
	public void setVerd(boolean ve){
		this.v = ve;
	}
	
	public int getInicio(){
		return this.i;
	}
	public int getFim(){
		return this.f;
	}
	public boolean getVer(){
		return this.v;
	}

}

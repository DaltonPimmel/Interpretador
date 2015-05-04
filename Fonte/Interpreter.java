
import java.util.Scanner;
import java.io.*;

class Interpreter {
	
    public static void main(String args[]) throws Exception {	
		
		
        File f;
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000]; // arquivo pode ter, no máximo, 2000 linhas.

        f = new File(args[0]);
        s = new Scanner(f);

        b = new Interpretador();
        
        int i = 0;
        while(s.hasNext()) {    // pegando o numero de linhas
            linhas[i] = s.nextLine();
            i++;
        }
		
        b.interpreta(linhas);  // chamado o metodo do interpretador com a quantidade de linhas para imprimir.
    }
    
    
}

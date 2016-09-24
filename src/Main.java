// Autor: João Vicente Souto
// Disciplina: Sistemas Digitais / UFSC
// Algoritmo para encontrar um aglomerado de pixels que formam um ponto.

public class Main {

	public static void main(String[] args) {
		
		int menorI, menorJ, maiorI = 0, maiorJ = 0;
		int contaJ;
		int primeiroPixel;
		boolean pixelPreto, acheiPonto;
		
		int[][] matriz = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,1,0,0},
						  {0,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
						  {0,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
						  {0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0,0,0,1,0},
						  {0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		// O primeiro preto que ele encontrar ele considera como ponto e
		// ja era para qualquer outros valores que não estjam aglomerado a ele.
		menorI = matriz.length-1;
		menorJ = matriz[0].length-1;
		maiorI = 0;
		maiorJ = 0;
		primeiroPixel = 0;
		acheiPonto = false;
		for(int i = 0; i<matriz.length; i++) {
			contaJ = 1;
			for(int j = 0; j<matriz[0].length; j++) {
				
				if(matriz[i][j] == 1) {
					if(contaJ != 0) {
						pixelPreto = true;
					} else {
						pixelPreto = false;
					}
					
					primeiroPixel++;
					if(contaJ != 0) {
						contaJ++;	
					}
					
				} else {
					if(contaJ != 1) {
						contaJ = 0;
					}
					pixelPreto = false;
				}
				
				if(primeiroPixel == 1) {
					menorI = i;
					maiorI = i;
					menorJ = j;
					maiorJ = j;
					primeiroPixel = 4;
					acheiPonto = true;
				}
				
				if(j == matriz[0].length-1 && contaJ == 1) {
					acheiPonto = false;
				}
				
				if(pixelPreto && acheiPonto) { // Para o i
					
					if(i < menorI) { // Primeiro pixel, nunca vai ter um i menor
						menorI = i;
					} 
					
					if(i > maiorI) {
						if((i == maiorI+1) && (j >= menorJ-1 && j <= maiorJ+1)) {
							maiorI = i;
						}
					}
					
					primeiroPixel--;
				}
				
				if(pixelPreto && acheiPonto) { // Para o j
					
					if(j < menorJ) {
						if(j == menorJ-1 && i <= maiorI+1) {
							menorJ = j;
						}
						contaJ--; // Se encontrar um antes do ponto em si
					}
					if(j > maiorJ) {
						if(j == maiorJ+1 && i <= maiorI+1) {
							maiorJ = j;
						}
					}
				}
			}
		}
		
		System.out.println("Menor i = " + menorI + "\n"
						 + "Menor j = " + menorJ + "\n"
						 + "Maior i = " + maiorI + "\n"
						 + "Maior J = " + maiorJ + "\n"
						 + "Sup-Esq(" + menorI + "," +  menorJ + ")"
						 + " - (" + maiorI + "," +  maiorJ + ")Inf-Dir");
		
		String concatena = "";
		for(int i = 0; i < matriz.length; i++) {
			concatena += "\n";
			for(int j = 0; j < matriz.length; j++) {
				concatena += matriz[i][j] + "";
			}
		}
		System.out.println(concatena);

	}

}

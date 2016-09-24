import java.util.Arrays;

// Autores: João Vicente Souto / Valdivino Morais
// Disciplina: Sistemas Digitais / UFSC
// Algoritmo para encontrar um aglomerado de pixels que formam um ponto.

public class Main {

	public static void main(String[] args) {

		int menorI, menorJ, maiorI, maiorJ, contaJ, primeiroPixel;
		boolean pixelPreto, acheiPonto, desenhaQuadrado;

		int [][] matriz = new int[30][30];

		//Zerando a matriz
		for(int i = 0; i<matriz.length; i++) {
			Arrays.fill(matriz[i], 0);
		}

		//Desenhando o ponto
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				int inicio;
				if(i == 0 || i == 4) {
					inicio = 9; //set onde quer começar- linhas menores
					if(j == 3) {break;}
				} else {
					inicio = 8; //set um a menos anterior- linhas maiores
				}
				matriz[i+8][j+inicio] = 1; // mude o 8 para a linha inicial
			}
		}
		// Aleatórios após o primeiro pixel.
		matriz[9][16] = 1;
		matriz[10][5] = 1;
		matriz[17][5] = 1;

		// O primeiro preto que ele encontrar ele considera como ponto e
		// ja era para qualquer outros valores que não estjam aglomerado a ele.
		menorI = matriz.length-1;
		menorJ = matriz[0].length-1;
		maiorI = 0;
		maiorJ = 0;
		primeiroPixel = 0;
		acheiPonto = false;
		desenhaQuadrado = false;

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
					if(acheiPonto) {
						desenhaQuadrado = true;
					}
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

				//Desenhando o quadrado depois de ter achado o Ponto
				// Como a matriz de pixels tem pixels a mais (fora dos 480x640),
				// acredito que não vamos nos preocupar com arrayoutofbound.
				if(desenhaQuadrado) {
					for(int linha = menorI-2; linha<=maiorI+2; linha++) {
						matriz[linha][menorJ-2] = 2;
						matriz[linha][maiorJ+2] = 2;
					}
					for(int coluna = menorJ-2; coluna<=maiorJ+2; coluna++) {
						matriz[menorI-2][coluna] = 2;
						matriz[maiorI+2][coluna] = 2;
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

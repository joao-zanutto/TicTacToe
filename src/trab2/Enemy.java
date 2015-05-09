package trab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Classe responsável por fazer a passagem e recebimento de informações
// entre os jogadores
public class Enemy implements Runnable{
	private static Socket enemy;
	
	public Enemy(Socket sock){
		enemy = sock;
	}
	
	// Método que passa ao inimigo a jogada
	public static void passaJogada(int xPos, int yPos){
		try {
			PrintWriter saida = new PrintWriter(enemy.getOutputStream(), true);
			String x = "" + xPos;
			saida.println(x);
			String y = "" + yPos;
			saida.println(y);
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro!");
		}
	}

	// Thread que espera e recebe a jogada do inimigo
	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(enemy.getInputStream()));
			while(EndGameWindow.getEnd() == 0){	
				int x = Integer.parseInt(entrada.readLine());
				int y = Integer.parseInt(entrada.readLine());
				GameWindow.gameButton[x][y].fazerJogada(x,y);
			}
			entrada.close();
		} catch (IOException e) {
			System.out.println("Um errro ocorreu!");
		}
	}
}
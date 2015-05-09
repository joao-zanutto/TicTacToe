package trab2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Classe responsável por fechar o Socket e se necessário
// o ServerSocket
public class Closer implements Runnable{
	private static Socket sock;
	private static ServerSocket serv;
	
	public Closer(Socket sock){
		Closer.sock = sock;
	}
	
	public Closer(Socket sock, ServerSocket serv){
		Closer.sock = sock;
		Closer.serv = serv;
	}
	
	public void run() {
		while(true){
			if(EndGameWindow.getEnd() != 0){
				try {
					sock.close();
				} catch (IOException e) {
					System.out.println("Um erro ocorreu!");
				}
				
				if(serv != null){
					try {
						serv.close();
					} catch (IOException e) {
						System.out.println("Um erro ocorreu!");
					}
				}
			}
		}
	}
}

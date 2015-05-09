package trab2;

import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Classe responsável pela janela do jogo
public class GameWindow extends Application{
	private static Socket sock;
	private static int player;
	private static int turn = 1;
	
	static GraphicButton[][] gameButton = new GraphicButton[3][3];
	
	public GameWindow(Socket sock, int player){
		GameWindow.sock = sock;
		GameWindow.player = player;
	}
	
	public void start(Stage gameStage) throws Exception {
		gameStage.setTitle("Tic-Tac-Toe");
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 720, 600);
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		GridPane game = new GridPane();
		grid.add(game, 18, 15);
		game.setHgap(5);
		game.setVgap(5);
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				gameButton[i][j] = new GraphicButton();
				game.add(gameButton[i][j].returnGraphicButton(100, 100, i, j), j, i);
			}
		}
		
		Enemy e = new Enemy(sock);
		Thread t = new Thread(e);
		t.start();
		
		gameStage.setScene(scene);
		gameStage.show();

	}
	
	public static int getTurn(){
		return GameWindow.turn;
	}
	
	public static void setTurn(int Turn){
		GameWindow.turn = Turn;
	}
	
	public static int getPlayer(){
		return GameWindow.player;
	}
	
	public static Socket getSocket(){
		return GameWindow.sock;
	}
}
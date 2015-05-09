package trab2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Classe simples responsavel pelo fim do jogo,
// cria uma janela informando o resultado do jogo
public class EndGameWindow extends Application{
	private static int hasEnded = 0;
	private int whoWon = 0;
	
	public EndGameWindow(int hasEnded, int whoWon){
		EndGameWindow.hasEnded = hasEnded;
		this.whoWon = whoWon;
	}
	
	public static int getEnd(){
		return hasEnded;
	}
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fim de Jogo");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		Scene scene = new Scene(grid, 200, 100);
		
		if(whoWon == 1){
			Text scenetitle = new Text("Você Ganhou!");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 5, 3, 1, 1);
		} else if(whoWon == 2) {
			Text scenetitle = new Text("Você Perdeu!");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 5, 3, 1, 1);
		} else {
			Text scenetitle = new Text("Empate!");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 5, 3, 1, 1);
		}
			
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

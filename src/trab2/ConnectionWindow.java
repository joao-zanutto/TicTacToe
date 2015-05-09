package trab2;

import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Classe responsável pela janela de recebimento de IP e porta
public class ConnectionWindow extends Application{

	// Criação da GUI da janela inicial
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Conexão");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		Scene scene = new Scene(grid, 500, 200);
		
		Text scenetitle = new Text("Tic-Tac-Toe");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 5, 1, 1, 1);
		
		Label ip = new Label("IP");
		grid.add(ip, 5, 3);
		TextField ipField = new TextField();
		grid.add(ipField, 6, 3);
		
		Label porta = new Label("Porta");
		grid.add(porta, 5, 4);
		TextField portaField = new TextField();
		grid.add(portaField, 6, 4);
		
		Button connect = new Button("Conectar");
		grid.add(connect, 5, 5);
		Button wait = new Button("Esperar");
		grid.add(wait, 6, 5);
		
		// Evento do botão do cliente
		connect.setOnAction(event->{			
			try{
				Socket sock = new Socket(ipField.getText(), Integer.parseInt(portaField.getText()));
				GameWindow player2 = new GameWindow(sock, 2);
				player2.start(primaryStage);
				new Thread(new Closer(sock)).start();
			} catch(Exception e) {
				Text error = new Text("Ocorreu um erro!");
				grid.add(error, 7, 6);
			}
		});
		
		// Evento do botão do servidor
		wait.setOnAction(event->{
			try{
				ServerSocket serv = new ServerSocket(Integer.parseInt(portaField.getText()));
				Socket sock = serv.accept();
				GameWindow player1 = new GameWindow(sock, 1);
				player1.start(primaryStage);
				new Thread(new Closer(sock, serv)).start();
			} catch(Exception e) {
				Text error = new Text("Ocorreu um erro!");
				grid.add(error, 7, 6);
			}
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
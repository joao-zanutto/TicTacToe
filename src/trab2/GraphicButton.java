package trab2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Classe responsável pelos botões e pela maior parte da ação do jogo
public class GraphicButton {
	Canvas can;
	GraphicsContext gc;
	
	int possession = 0;
	
	public Canvas returnGraphicButton(double x, double y, int xPos, int yPos){
		this.can = new Canvas(x, y);
		this.gc = can.getGraphicsContext2D();
		
		gc.setLineWidth(3);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, 100, 100);
		
		// Evento do mouse entrando em um botão
		can.setOnMouseEntered(event->{
			gc.setStroke(Color.AQUA);
			gc.strokeRect(0, 0, 100, 100);
		});
		
		// Evento do mouse saindo do botão
		can.setOnMouseExited(event->{
			gc.setStroke(Color.BLACK);
			gc.strokeRect(0, 0, 100, 100);
		});
		
		// Evento do clique do botão, faz a jogada do player,
		// passa o turno e passa as informações para o socket
		can.setOnMouseClicked(event->{
			if(GameWindow.getPlayer() == GameWindow.getTurn()){
				if(GameWindow.getPlayer() == 1){
					gc.setStroke(Color.RED);
					gc.strokeOval(25, 25, 50, 50);
					GameWindow.setTurn(2);
					this.possession = 1;
				} else {
					gc.setStroke(Color.BLUE);
					gc.strokeLine(25, 25, 75, 75);
					gc.strokeLine(25, 75, 75, 25);
					GameWindow.setTurn(1);
					this.possession = 2;
				}
				Enemy.passaJogada(xPos, yPos);
				if(gameEnd(xPos, yPos) == 1){
					try {
						new EndGameWindow(1, 1).start(new Stage());
					} catch (Exception e) {
						System.out.println("Um erro ocorreu");
					}
				} else if (gameEnd(xPos, yPos) == 3){
					try {
						new EndGameWindow(1, 3).start(new Stage());
					} catch (Exception e) {
						System.out.println("Um erro ocorreu");
					}
				}
				can.setOnMouseClicked(null);
			}
		});
		return can;
	}
	
	// Faz a jogada do jogador adversário no computador atual
	// básicamente faz a mesma coisa que o evento de clique no botão
	public void fazerJogada(int xPos, int yPos){
		if(GameWindow.getPlayer() == 2){
			gc.setStroke(Color.RED);
			gc.strokeOval(25, 25, 50, 50);
			GameWindow.setTurn(2);
			this.possession = 1;
		} else {
			gc.setStroke(Color.BLUE);
			gc.strokeLine(25, 25, 75, 75);
			gc.strokeLine(25, 75, 75, 25);
			GameWindow.setTurn(1);
			this.possession = 2;
		}
		if(EndGameWindow.getEnd() != 0 && EndGameWindow.getEnd() != 3){
			try {
				new EndGameWindow(1, 2).start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu");
			}
		} else if (EndGameWindow.getEnd() == 3){
			try {
				new EndGameWindow(3, 3).start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu");
			}
		}
		can.setOnMouseClicked(null);
	}
	
	// Função repetitiva que verifica o tabuleiro do jogo
	// caso haja algum vencedor
	public int gameEnd(int xPos, int yPos){
		outer: for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(GameWindow.gameButton[i][j].possession == 0){
					break outer;
				}
				if(i == 2 && j == 2 && GameWindow.gameButton[i][j].possession != 0){
					return 3;
				}
			}
		}
		if(xPos == 1 && yPos == 1){
			if(GameWindow.gameButton[1][0].possession == GameWindow.gameButton[1][2].possession && this.possession == GameWindow.gameButton[1][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][0].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][2].possession == GameWindow.gameButton[2][0].possession && this.possession == GameWindow.gameButton[2][0].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][1].possession == GameWindow.gameButton[2][1].possession && this.possession == GameWindow.gameButton[2][1].possession){
				return 1;
			}
		}
		
		if(xPos == 0 && yPos == 1){
			if(GameWindow.gameButton[0][0].possession == GameWindow.gameButton[0][2].possession && this.possession == GameWindow.gameButton[0][0].possession){
				return 1;
			}
			if(GameWindow.gameButton[1][1].possession == GameWindow.gameButton[2][1].possession && this.possession == GameWindow.gameButton[2][1].possession){
				return 1;
			}
		}
		
		if(xPos == 2 && yPos == 1){
			if(GameWindow.gameButton[2][0].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][1].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
		}
		
		if(xPos == 1 && yPos == 0){
			if(GameWindow.gameButton[1][2].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][0].possession == GameWindow.gameButton[2][0].possession && this.possession == GameWindow.gameButton[2][0].possession){
				return 1;
			}
		}
		
		if(xPos == 1 && yPos == 2){
			if(GameWindow.gameButton[1][0].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
			if(GameWindow.gameButton[2][0].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
		}
		
		if(xPos == 0 && yPos == 0){
			if(GameWindow.gameButton[0][1].possession == GameWindow.gameButton[0][2].possession && this.possession == GameWindow.gameButton[0][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[1][0].possession == GameWindow.gameButton[2][0].possession && this.possession == GameWindow.gameButton[2][0].possession){
				return 1;
			}
			if(GameWindow.gameButton[1][1].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
		}
		
		if(xPos == 0 && yPos == 2){
			if(GameWindow.gameButton[0][0].possession == GameWindow.gameButton[0][1].possession && this.possession == GameWindow.gameButton[0][1].possession){
				return 1;
			}
			if(GameWindow.gameButton[1][2].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[2][0].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
		}
		
		if(xPos == 2 && yPos == 0){
			if(GameWindow.gameButton[2][1].possession == GameWindow.gameButton[2][2].possession && this.possession == GameWindow.gameButton[2][2].possession){
				return 1;
			}
			if(GameWindow.gameButton[1][0].possession == GameWindow.gameButton[0][0].possession && this.possession == GameWindow.gameButton[0][0].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][2].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
		}
		
		if(xPos == 2 && yPos == 2){
			if(GameWindow.gameButton[0][0].possession == GameWindow.gameButton[1][1].possession && this.possession == GameWindow.gameButton[1][1].possession){
				return 1;
			}
			if(GameWindow.gameButton[2][0].possession == GameWindow.gameButton[2][1].possession && this.possession == GameWindow.gameButton[2][1].possession){
				return 1;
			}
			if(GameWindow.gameButton[0][2].possession == GameWindow.gameButton[1][2].possession && this.possession == GameWindow.gameButton[1][2].possession){
				return 1;
			}
		}
		
		return 2;
	}

}
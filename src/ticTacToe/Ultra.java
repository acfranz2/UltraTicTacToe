package ticTacToe;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.input.*;

public class Ultra extends Application {

	private double h;
	private Button[][][][][][] btn;
	private Circle[][][][][][] crcs;
	private X[][][][][][] exes;
	private X[][][][] bigX;
	private Circle[][][][] bigCrc;
	private Circle[][] giantCrc;
	private X[][] giantX;
	private int[][][][][][] score = new int[3][3][3][3][3][3];
	private int[][][][] bigScore = new int[3][3][3][3];
	private int[][] giantScore = new int[3][3];
	private int count = 0;
	private Rectangle[][][][][][] playable;
	private Rectangle[][][][][][] playTo;
	private double sceneWidth;
	
	public void start(Stage primaryStage) throws Exception {
		
	  
	  
		Stage opening = new Stage();
		BorderPane choices = new BorderPane();
		Scene menu = new Scene(choices, 300, 300); 
		
		Button start = new Button("New Game");
		start.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        primaryStage.show();
		        opening.close();
		    }
		});
		
		
		choices.setCenter(start);
		opening.setTitle("Menu");
		opening.setScene(menu);
		opening.show();
		
		Group root = new Group();
		GridPane btns = new GridPane();
		Scene scene = new Scene(root, 700, 700);
		
		h = scene.getHeight();
		double buf = h/100;
		double sBuf = h/200;
		
		playable = new Rectangle[3][3][3][3][3][3];
		for(int gRR = 0; gRR < playable.length; gRR++)
			for(int gCR = 0; gCR < playable.length; gCR++)
				for(int bRR = 0; bRR < playable.length; bRR++)
					for(int bCR = 0; bCR < playable.length; bCR++)
						for(int rR = 0; rR < playable.length; rR++)
							for(int cR = 0; cR < playable.length; cR++) {
								playable[gRR][gCR][bRR][bCR][rR][cR] = new Rectangle(h/27, h/27);
								playable[gRR][gCR][bRR][bCR][rR][cR].setFill(Color.GREENYELLOW);
								root.getChildren().add(playable[gRR][gCR][bRR][bCR][rR][cR]);
								
								playable[gRR][gCR][bRR][bCR][rR][cR].setLayoutX((cR*h/27)+(bCR*h/9)+(gCR*h/3));
								playable[gRR][gCR][bRR][bCR][rR][cR].setLayoutY((rR*h/27)+(bRR*h/9)+(gRR*h/3));
								
								playable[gRR][gCR][bRR][bCR][rR][cR].setVisible(false);
		}
		
		playTo = new Rectangle[3][3][3][3][3][3];
		for(int gRR = 0; gRR < playTo.length; gRR++)
			for(int gCR = 0; gCR < playTo.length; gCR++)
				for(int bRR = 0; bRR < playTo.length; bRR++)
					for(int bCR = 0; bCR < playTo.length; bCR++)
						for(int rR = 0; rR < playTo.length; rR++)
							for(int cR = 0; cR < playTo.length; cR++) {
								playTo[gRR][gCR][bRR][bCR][rR][cR] = new Rectangle(h/27, h/27);
								playTo[gRR][gCR][bRR][bCR][rR][cR].setFill(Color.ORANGERED);
								root.getChildren().add(playTo[gRR][gCR][bRR][bCR][rR][cR]);
								
								playTo[gRR][gCR][bRR][bCR][rR][cR].setLayoutX((cR*h/27)+(bCR*h/9)+(gCR*h/3));
								playTo[gRR][gCR][bRR][bCR][rR][cR].setLayoutY((rR*h/27)+(bRR*h/9)+(gRR*h/3));
								
								playTo[gRR][gCR][bRR][bCR][rR][cR].setVisible(false);
		}
		
		Line[][] horiz = new Line[6][3];
		for(int row = 0, spot = 1; row < horiz.length; row += 2, spot += 3)
			for(int col = 0; col < horiz[0].length; col++) {
					horiz[row][col] = new Line(col*h/3 + buf, (spot)*(h/9), (col+1)*h/3 - buf, (spot)*(h/9));
					horiz[row+1][col] = new Line(col*h/3 + buf, (spot+1)*(h/9), (col+1)*h/3 - buf, (spot+1)*(h/9));
					
					horiz[row][col].setStroke(Color.DARKBLUE);
					horiz[row+1][col].setStroke(Color.DARKBLUE);
					
					root.getChildren().addAll(horiz[row+1][col], horiz[row][col]);
		}
		
		Line[][] vert = new Line[3][6];
		for(int row = 0; row < vert.length; row++)
			for(int col = 0, spot = 1; col < vert[0].length; col += 2, spot += 3) {
					vert[row][col] = new Line((spot)*(h/9), row*h/3 + buf, (spot)*(h/9), (row+1)*h/3 - buf);
					vert[row][col+1] = new Line((spot+1)*(h/9), row*h/3 + buf, (spot+1)*(h/9), (row+1)*h/3 - buf);
					
					vert[row][col].setStroke(Color.DARKBLUE);
					vert[row][col+1].setStroke(Color.DARKBLUE);
					
					root.getChildren().addAll(vert[row][col], vert[row][col+1]);
		}
		
		Line leftLine = new Line(h/3, 0, h/3, h);
		Line rightLine = new Line(2*h/3, 0, 2*h/3, h); 
		Line topLine = new Line(0, h/3, h, h/3);
		Line botLine = new Line(0, 2*h/3, h, 2*h/3);
		
		root.getChildren().addAll(leftLine, rightLine, topLine, botLine, btns);
		
		btn = new Button[3][3][3][3][3][3];
		for(int giantRow = 0; giantRow < btn.length; giantRow++)
			for(int giantCol = 0; giantCol < btn.length; giantCol++)
				for(int bigRow = 0; bigRow < btn.length; bigRow++)
					for(int bigCol = 0; bigCol < btn.length; bigCol++)
						for(int row = 0; row < btn.length; row++)
							for(int col = 0; col < btn.length; col++) {
								btn[giantRow][giantCol][bigRow][bigCol][row][col] = new UCell(giantRow, giantCol, bigRow, bigCol, row, col);
								btn[giantRow][giantCol][bigRow][bigCol][row][col].setMinHeight(1);
								btn[giantRow][giantCol][bigRow][bigCol][row][col].setPrefSize(h/27, h/27);
								btn[giantRow][giantCol][bigRow][bigCol][row][col].setOpacity(.4);
		
								btns.add(btn[giantRow][giantCol][bigRow][bigCol][row][col], (int)((col*h/27)+(bigCol*h/9)+(giantCol*h/3)), (int)((row*h/27)+(bigRow*h/9)+(giantRow*h/3)));
								
								btn[giantRow][giantCol][bigRow][bigCol][row][col].setOnAction(e -> clicked(e));
								
								btn[giantRow][giantCol][bigRow][bigCol][row][col].setOnMouseEntered(t -> hover(t));
		}
		///////////////////////////////////////////////////////Circle
		crcs = new Circle[3][3][3][3][3][3];
		for(int giantRowC = 0; giantRowC < crcs.length; giantRowC++)
			for(int giantColC = 0; giantColC < crcs.length; giantColC++)
				for(int bigRowC = 0; bigRowC < crcs.length; bigRowC++)
					for(int bigColC = 0; bigColC < crcs.length; bigColC++)
						for(int rowC = 0; rowC < crcs.length; rowC++)
							for(int colC = 0; colC < crcs.length; colC++) {
								
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC] = new Circle(h/54 - sBuf);
								
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setFill(Color.TRANSPARENT);
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setStroke(Color.BLACK);
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setStrokeWidth(1);
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setVisible(false);
								
								root.getChildren().add(crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC]);
								
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setLayoutX((colC*h/27)+(bigColC*h/9)+(giantColC*h/3) + h/54);
								crcs[giantRowC][giantColC][bigRowC][bigColC][rowC][colC].setLayoutY((rowC*h/27)+(bigRowC*h/9)+(giantRowC*h/3) + h/54);						
							
		}
		
		bigCrc = new Circle[3][3][3][3];
		for(int giantRBC = 0; giantRBC < bigCrc.length; giantRBC++)
			for(int giantCBC = 0; giantCBC < bigCrc.length; giantCBC++)
				for(int bigRBC = 0; bigRBC < bigCrc.length; bigRBC++)
					for(int bigCBC = 0; bigCBC < bigCrc.length; bigCBC++) {
						
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC] = new Circle(h/18 - buf);
						
						
						root.getChildren().add(bigCrc[giantRBC][giantCBC][bigRBC][bigCBC]);
						
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setLayoutX((bigCBC*h/9)+(giantCBC*h/3) + (h/18));
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setLayoutY((bigRBC*h/9)+(giantRBC*h/3) + (h/18));
						
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setFill(Color.TRANSPARENT);
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setStroke(Color.DARKMAGENTA);
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setStrokeWidth(2);
						
						bigCrc[giantRBC][giantCBC][bigRBC][bigCBC].setVisible(false);
						
						
		}
		
		giantCrc = new Circle[3][3];
		for(int giantRGC = 0; giantRGC < giantCrc.length; giantRGC++)
			for(int giantCGC = 0; giantCGC < giantCrc.length; giantCGC++) {
				
				giantCrc[giantRGC][giantCGC] = new Circle(h/6 - buf);
				
				giantCrc[giantRGC][giantCGC].setFill(Color.TRANSPARENT);
				giantCrc[giantRGC][giantCGC].setStrokeWidth(3);
				giantCrc[giantRGC][giantCGC].setStroke(Color.INDIANRED);
				
				root.getChildren().add(giantCrc[giantRGC][giantCGC]);
				
				giantCrc[giantRGC][giantCGC].setLayoutX((giantCGC*h/3) + (h/6));
				giantCrc[giantRGC][giantCGC].setLayoutY((giantRGC*h/3) + (h/6));
				
				giantCrc[giantRGC][giantCGC].setVisible(false);
		}
		
		///////////////////////////////////////////////////////X
		exes = new X[3][3][3][3][3][3];
		for(int giantRowX = 0; giantRowX < exes.length; giantRowX++)
			for(int giantColX = 0; giantColX < exes.length; giantColX++)
				for(int bigRowX = 0; bigRowX < exes.length; bigRowX++)
					for(int bigColX = 0; bigColX < exes.length; bigColX++)
						for(int rowX = 0; rowX < exes.length; rowX++)
							for(int colX = 0; colX < exes.length; colX++) {
								
								exes[giantRowX][giantColX][bigRowX][bigColX][rowX][colX] = new X(h/27-2*sBuf, (colX*h/27)+(bigColX*h/9)+(giantColX*h/3) + sBuf, (rowX*h/27)+(bigRowX*h/9)+(giantRowX*h/3) + sBuf, 1, Color.BLACK);
								
								root.getChildren().add(exes[giantRowX][giantColX][bigRowX][bigColX][rowX][colX].draw());
								
								exes[giantRowX][giantColX][bigRowX][bigColX][rowX][colX].vis(false);
								
		}
		
		bigX = new X[3][3][3][3];
		for(int giantRBX = 0; giantRBX < bigX.length; giantRBX++)
			for(int giantCBX = 0; giantCBX < bigX.length; giantCBX++)
				for(int bigRBX = 0; bigRBX < bigX.length; bigRBX++)
					for(int bigCBX = 0; bigCBX < bigX.length; bigCBX++) {
						bigX[giantRBX][giantCBX][bigRBX][bigCBX] = new X(h/9-2*buf, (bigCBX*h/9)+(giantCBX*h/3) + buf, (bigRBX*h/9)+(giantRBX*h/3) + buf, 2, Color.DARKMAGENTA);	
						root.getChildren().add(bigX[giantRBX][giantCBX][bigRBX][bigCBX].draw());
						bigX[giantRBX][giantCBX][bigRBX][bigCBX].vis(false);
		}
		
		giantX = new X[3][3];
		for(int giantRGX = 0; giantRGX < giantX.length; giantRGX++)
			for(int giantCGX = 0; giantCGX < giantX.length; giantCGX++) {
				giantX[giantRGX][giantCGX] = new X(h/3-2*buf, giantCGX*h/3 + buf, giantRGX*h/3 + buf, 3, Color.INDIANRED);
				root.getChildren().add(giantX[giantRGX][giantCGX].draw());
				giantX[giantRGX][giantCGX].vis(false);
				
		}
		
		
		leftLine.toFront(); rightLine.toFront(); topLine.toFront(); botLine.toFront(); 
		leftLine.setStroke(Color.AQUA); rightLine.setStroke(Color.AQUA); topLine.setStroke(Color.AQUA); botLine.setStroke(Color.AQUA);
		leftLine.setStrokeWidth(2); rightLine.setStrokeWidth(2); topLine.setStrokeWidth(2); botLine.setStrokeWidth(2);
		
		primaryStage.setTitle("Ultra TicTacToe");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	}
	
	public void clicked(ActionEvent e) {
		count++;
		
		UCell temp = (UCell)e.getSource();
		int gr = temp.getGR();
		int gc = temp.getGC();
		int br = temp.getBR();
		int bc = temp.getBC();
		int r = temp.getR();
		int c = temp.getC();
		
		if(count % 2 == 0) {
			score[gr][gc][br][bc][r][c] = 1;
			crcs[gr][gc][br][bc][r][c].setVisible(true);
		}
		if(count % 2 == 1) {
			score[gr][gc][br][bc][r][c] = 2;
			exes[gr][gc][br][bc][r][c].vis(true);
		}
		
		if(check(gr, gc, br, bc) == 1) {
			bigScore[gr][gc][br][bc] = 1;
			bigCrc[gr][gc][br][bc].setVisible(true);
		}
		if(check(gr, gc, br, bc) == 2) {
			bigScore[gr][gc][br][bc] = 2;
			bigX[gr][gc][br][bc].vis(true);
		}
		if(check(gr, gc, br, bc) == 3) {
			bigScore[gr][gc][br][bc] = 3;
		}
		if(bigCheck(gr, gc) == 1) {
			giantScore[gr][gc] = 1;
			giantCrc[gr][gc].setVisible(true);
		}
		if(bigCheck(gr, gc) == 2) {
			giantScore[gr][gc] = 2;
			giantX[gr][gc].vis(true);
		}
		if(bigCheck(gr, gc) == 3) {
			giantScore[gr][gc] = 3;
		}
		
		for(int GR = 0; GR < btn.length; GR++)
			for(int GC = 0; GC < btn.length; GC++)
				for(int BR = 0; BR < btn.length; BR++)
					for(int BC = 0; BC < btn.length; BC++)
						for(int R = 0; R < btn.length; R++)
							for(int C = 0; C < btn.length; C++) {
								if(giantScore[br][bc] == 0) {
									if(bigScore[br][bc][r][c] == 0) {
										if(GR == br && GC == bc && BR == r && BC == c) {
											if(score[GR][GC][BR][BC][R][C] != 0) 
												btn[GR][GC][BR][BC][R][C].setDisable(true);
											else
												btn[GR][GC][BR][BC][R][C].setDisable(false);
										}
										else
											btn[GR][GC][BR][BC][R][C].setDisable(true);
									}
									else {
										if(GR == br && GC == bc) {
											btn[GR][GC][BR][BC][R][C].setDisable(false);
											if(score[GR][GC][BR][BC][R][C] != 0 || bigScore[GR][GC][BR][BC] != 0) {
												btn[GR][GC][BR][BC][R][C].setDisable(true);
											}
											else {
												btn[GR][GC][BR][BC][R][C].setDisable(false);
											}
										}
										else {
											btn[GR][GC][BR][BC][R][C].setDisable(true);
										}
										
									}
								}
								else {
									if(GR == br && GC == bc || score[GR][GC][BR][BC][R][C] != 0 || bigScore[GR][GC][BR][BC] != 0 || giantScore[GR][GC] != 0)
										btn[GR][GC][BR][BC][R][C].setDisable(true);
									else
										btn[GR][GC][BR][BC][R][C].setDisable(false);
								}
								
								if(!btn[GR][GC][BR][BC][R][C].isDisabled()) {
									playable[GR][GC][BR][BC][R][C].setVisible(true);
								}
								else {
									playable[GR][GC][BR][BC][R][C].setVisible(false);
								}
								
		}
		
		if(giantCheck() != 0) {
			Stage secondStage = new Stage();
			
			Group text = new Group();
			
			Label lbl = new Label("Game Over");
            text.getChildren().add(lbl);
			
			Scene winner = new Scene(text, 30, 30);
			
			secondStage.setScene(winner);
			secondStage.show();
		}
	}
	
	
	public void hover(MouseEvent t) {
		
		UCell temp = (UCell)t.getSource();
		
		int row = temp.getR();
		int col = temp.getC();
		int bigR = temp.getBR();
		int bigC = temp.getBC();
		
		for(int gr = 0; gr < btn.length; gr++)
			for(int gc = 0; gc < btn.length; gc++)
				for(int br = 0; br < btn.length; br++)
					for(int bc = 0; bc < btn.length; bc++)
						for(int r = 0; r < btn.length; r++)
							for(int c = 0; c < btn.length; c++) {
								if(giantScore[bigR][bigC] == 0) {
									if(bigScore[bigR][bigC][row][col] == 0) {
										if(gr == bigR && gc == bigC && br == row && bc == col) {
											if(score[gr][gc][br][bc][r][c] != 0) 
												playTo[gr][gc][br][bc][r][c].setVisible(false);
											else
												playTo[gr][gc][br][bc][r][c].setVisible(true);
										}
										else
											playTo[gr][gc][br][bc][r][c].setVisible(false);
									}
									else {
										if(gr == bigR && gc == bigC) {
											playTo[gr][gc][br][bc][r][c].setVisible(true);
											if(score[gr][gc][br][bc][r][c] != 0 || bigScore[gr][gc][br][bc] != 0) {
												playTo[gr][gc][br][bc][r][c].setVisible(false);
											}
											else {
												playTo[gr][gc][br][bc][r][c].setVisible(true);
											}
										}
										else {
											playTo[gr][gc][br][bc][r][c].setVisible(false);
										}
										
									}
								}
								else {
									if(gr == bigR && gc == bigC || score[gr][gc][br][bc][r][c] != 0 || bigScore[gr][gc][br][bc] != 0 || giantScore[gr][gc] != 0) {
										playTo[gr][gc][br][bc][r][c].setVisible(false);
									}
									else
										playTo[gr][gc][br][bc][r][c].setVisible(true);
								}
		}
	}
	
	
	//////////////////////////////////////////////////Score Checking
	public int check(int giantR, int giantC, int bigR, int bigC) {
		return genCheck(score[giantR][giantC][bigR][bigC]);
	}
	
	public int bigCheck(int giantR, int giantC) {
		return genCheck(bigScore[giantR][giantC]);
	}
	
	public int giantCheck() {
		return genCheck(giantScore);
	}

	private int genCheck(int[][] score){
		if(score[0][0] == score[0][1] && score[0][0] == score[0][2] && score[0][0] == 1 ||
				score[1][0] == score[1][1] && score[1][0] == score[1][2] && score[1][0] == 1 ||
				score[2][0] == score[2][1] && score[2][0] == score[2][2] && score[2][0] == 1 ||
				score[0][0] == score[1][0] && score[0][0] == score[2][0] && score[0][0] == 1 ||
				score[0][1] == score[1][1] && score[0][1] == score[2][1] && score[0][1] == 1 ||
				score[0][2] == score[1][2] && score[0][2] == score[2][2] && score[0][2] == 1 ||
				score[0][0] == score[1][1] && score[0][0] == score[2][2] && score[0][0] == 1 ||
				score[0][2] == score[1][1] && score[0][2] == score[2][0] && score[0][2] == 1)		
			return 1;
		else if(score[0][0] == score[0][1] && score[0][0] == score[0][2] && score[0][0] == 2 ||
				score[1][0] == score[1][1] && score[1][0] == score[1][2] && score[1][0] == 2 ||
				score[2][0] == score[2][1] && score[2][0] == score[2][2] && score[2][0] == 2 ||
				score[0][0] == score[1][0] && score[0][0] == score[2][0] && score[0][0] == 2 ||
				score[0][1] == score[1][1] && score[0][1] == score[2][1] && score[0][1] == 2 ||
				score[0][2] == score[1][2] && score[0][2] == score[2][2] && score[0][2] == 2 ||
				score[0][0] == score[1][1] && score[0][0] == score[2][2] && score[0][0] == 2 ||
				score[0][2] == score[1][1] && score[0][2] == score[2][0] && score[0][2] == 2)		
			return 2;
		else if(score[0][0] != 0 && score[0][1] != 0 && score[0][2] != 0 &&
			score[1][0] != 0 && score[1][1] != 0 && score[1][2] != 0 &&
			score[2][0] != 0 && score[2][1] != 0 && score[2][2] != 0)
			return 3;	
		return 0;
	}

	public static void main(String args[]){   
		launch(args);
	}
}

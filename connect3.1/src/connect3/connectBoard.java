package connect3;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class connectBoard {
	
	int height = 6;
	int width = 7;
	String[][]board = new String[height][width];
	String currentPlayer = "X"; // Starting player
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(new GridLayout(6, 7, 10, 10));
	
	boolean buttonClicked;
	int clickedCol;
	
	
	public connectBoard() {
		
		initializeBoard(board);
		
		frame.setTitle("connect3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		
		
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	
            	if( i == 0 ) {

                	final JButton firstButton = new JButton((j+1) + " " + board[i][j]);
                	panel.add(firstButton);
                	
                	class ButtonClickListener implements ActionListener {
                    	
                        public void actionPerformed(ActionEvent e) {
                        	
                           clickedCol = Integer.parseInt(String.valueOf(firstButton.getText().charAt(0))) - 1;
                           connect3.clickedCol = Integer.parseInt(String.valueOf(firstButton.getText().charAt(0)));
                           buttonClicked = true;
                           connect3.buttonClicked = true;
                           
                        }
                        
                    }
                    firstButton.addActionListener(new ButtonClickListener());
                	
            	}
            	
            	else {

                	JButton button = new JButton(board[i][j]);
                	panel.add(button);
                	
            	}

            }
		}
              
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void initializeBoard(String[][] board) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = "-";
            }
        }
    }
	
	public void drop(int move) {
		
		for(int i = 0; i < 5; i++) {
			if(board[i+1][move] == "-") {
				
				board[i+1][move] = currentPlayer;
				board[i][move] = "-";
				
			}
		}
		
	}
	
	public boolean checkForConnect3Win() {
		
		boolean won = false;
		
		for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 6; j++) {
                if (board[i][j] == currentPlayer) {
                	
                	boolean realWonAcross = true;
                	boolean realWonDown = true;
                	boolean realWonDiagonal = true;
                	boolean realWonMinusDiagonal = true;
                	
                	for (int k = -1; k < 2; k++) {
                		
                		if (board[i][j+k] != currentPlayer) {
                			realWonAcross = false;
                		}
                		if (board[i+k][j] != currentPlayer) {
                			realWonDown = false;
                		}
                		if (board[i+k][j+k] != currentPlayer) {
                			realWonDiagonal = false;
                		}
                		if (board[i+k][j-k] != currentPlayer) {
                			realWonMinusDiagonal = false;
                		}
                		
                    }
                	
                	boolean realWon = realWonAcross||realWonDown||realWonDiagonal||realWonMinusDiagonal;
                	won = realWon;
                	
                	if(won) {
                		return won;
                	}
                	
                }
            }
        }
		
		for (int i = 0; i < 4; i++) {
			
			if (board[i][0] == currentPlayer) {
				
				boolean realWonDown = true;
				
				for (int k = 0; k < 3; k++) {
					
            		if (board[i+k][0] != currentPlayer) {
            			realWonDown = false;
            		}
            		
                }
				
				won = realWonDown;
            	
            	if(won) {
            		return won;
            	}
            	
			}
				
			if (board[i][6] == currentPlayer) {
				
				boolean realWonDown = true;
				
				for (int k = 0; k < 3; k++) {
					
            		if (board[i+k][6] != currentPlayer) {
            			realWonDown = false;
            		}
            		
                }
				
				won = realWonDown;
            	
            	if(won) {
            		return won;
            	}
			
			}
	
		}
		
		for (int i = 0; i < 5; i++) {
			
			if (board[0][i] == currentPlayer) {
				
				boolean realWonAcross = true;
				
				for (int k = 0; k < 3; k++) {
					
            		if (board[0][i+k] != currentPlayer) {
            			realWonAcross = false;
            		}
            		
                }
				
				won = realWonAcross;
            	
            	if(won) {
            		return won;
            	}
            	
			}
				
			if (board[5][i] == currentPlayer) {
				
				boolean realWonAcross = true;
				
				for (int k = 0; k < 3; k++) {
					
            		if (board[5][i+k] != currentPlayer) {
            			realWonAcross = false;
            		}
            		
                }
				
				won = realWonAcross;
            	
            	if(won) {
            		return won;
            	}
			
			}
	
		}
		
		return won;

	}
	
	public boolean isConnect3BoardFull() {
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == "-") {
                    return false;
                }
            }
        }
        return true;
	}
	
	public void changeConnectPlayer() {
		if(currentPlayer == "X") {
			currentPlayer = "Y";
		}
		else if(currentPlayer == "Y") {
			currentPlayer = "Z";
		}
		else if(currentPlayer == "Z") {
			currentPlayer = "X";
		}
	}
	
	public void play() {
		
		Scanner playScanner = new Scanner(System.in);
        
        while (true) {
        	printConnectBoard();
			printConnectBoardFrame();
            System.out.println("Player " + currentPlayer + ", enter your move (pick a column): ");
            boolean colUpdated = false;
			int col = 10;
			
			while(!colUpdated) {
				
				buttonClicked = false;
				int scannerInput = 10;
				
				try {
					
					if (System.in.available() > 0) {
						
						scannerInput = playScanner.nextInt();
						
	                }
					
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
				
				if((scannerInput >= 0) && (scannerInput <= 6) ) {
					
					col = scannerInput-1;
					colUpdated = true;
					break;
					
				}
				
				else if(buttonClicked){
					
					col = clickedCol;
					colUpdated = buttonClicked;
					
				}
				
			}

            if (col < 0 || col >= width || board[0][col] != "-") {
                System.out.println("This move is not valid");
                continue;
            }
            
            board[0][col] = currentPlayer;
            drop(col);

            if (checkForConnect3Win()) {
            	printConnectBoard();
				printConnectBoardFrame();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isConnect3BoardFull()) {
            	printConnectBoard();
				printConnectBoardFrame();
                System.out.println("It's a tie!");
                break;
            }

            changeConnectPlayer();
        }
        playScanner.close();
		
	}
	public void play(int vsCpuInt) {
	
		Scanner playScanner = new Scanner(System.in);
    
		while (true) {

			printConnectBoard();
			printConnectBoardFrame();
			System.out.println("Player " + currentPlayer + ", enter your move (pick a column): ");
			
			boolean colUpdated = false;
			int col = 10;
			
			while(!colUpdated) {
				
				buttonClicked = false;
				int scannerInput = 10;
				
				try {
					
					if (System.in.available() > 0) {
						
						scannerInput = playScanner.nextInt();
						
	                }
					
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
				
				if((scannerInput >= 0) && (scannerInput <= 6) ) {
					
					col = scannerInput-1;
					colUpdated = true;
					break;
					
				}
				
				else if(buttonClicked){
					
					col = clickedCol;
					colUpdated = buttonClicked;
					
				}
				
			}

			if (col < 0 || col >= width || board[0][col] != "-") {
				System.out.println("This move is not valid");
				continue;
			}
        
			board[0][col] = currentPlayer;
			drop(col);

			if (checkForConnect3Win()) {
				printConnectBoard();
				printConnectBoardFrame();
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}

			if (isConnect3BoardFull()) {
				printConnectBoard();
				printConnectBoardFrame();
				System.out.println("It's a tie!");
            	break;
			}
			
			changeConnectPlayer();
			
			if(vsCpuInt == 2) {

				printConnectBoard();
				printConnectBoardFrame();
				System.out.println("Player " + currentPlayer + ", enter your move (pick a column): ");
				colUpdated = false;
				
				while(!colUpdated) {
					
					buttonClicked = false;
					int scannerInput = 10;
					
					try {
						
						if (System.in.available() > 0) {
							
							scannerInput = playScanner.nextInt();
							
		                }
						
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
					
					if((scannerInput >= 0) && (scannerInput <= 6) ) {
						
						col = scannerInput-1;
						colUpdated = true;
						break;
						
					}
					
					else if(buttonClicked){
						
						col = clickedCol;
						colUpdated = buttonClicked;
						
					}
					
				}

				if (col < 0 || col >= width || board[0][col] != "-") {
					System.out.println("This move is not valid");
					continue;
				}
	        
				board[0][col] = currentPlayer;
				drop(col);

				if (checkForConnect3Win()) {
					printConnectBoard();
					printConnectBoardFrame();
					System.out.println("Player " + currentPlayer + " wins!");
					break;
				}

				if (isConnect3BoardFull()) {
					printConnectBoard();
					printConnectBoardFrame();
					System.out.println("It's a tie!");
	            	break;
				}
				
			}
			
			else if(vsCpuInt == 1) {
				
				col = (int)((Math.random()*(7)));
	        	
	        	while(col < 0 || col >= 7 || board[0][col] != "-") {
	        		
	                col = (int)((Math.random()*(7)));

	        	}
	        	
	            board[0][col] = currentPlayer;
	            drop(col);

	            if (checkForConnect3Win()) {
					printConnectBoard();
					printConnectBoardFrame();
					System.out.println("Player " + currentPlayer + " wins!");
					break;
				}

				if (isConnect3BoardFull()) {
					printConnectBoard();
					printConnectBoardFrame();
					System.out.println("It's a tie!");
	            	break;
				}

			}

			changeConnectPlayer();
        	
            col = (int)((Math.random()*(7)));
        	
        	while(col < 0 || col >= 7 || board[0][col] != "-") {
        		
                col = (int)((Math.random()*(7)));

        	}
        	
            board[0][col] = currentPlayer;
            drop(col);

            if (checkForConnect3Win()) {
				printConnectBoard();
				printConnectBoardFrame();
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}

			if (isConnect3BoardFull()) {
				printConnectBoard();
				printConnectBoardFrame();
				System.out.println("It's a tie!");
            	break;
			}

			changeConnectPlayer();
		}
		playScanner.close();
	
	}

	public void printConnectBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
				System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

	public void printConnectBoardFrame() {
		
		
        System.out.println("Current Board:");
            	
        panel.removeAll();
        
        Color currentPlayerColor = new Color(255, 0, 0);;
        
        if(currentPlayer == "X") {
        	currentPlayerColor = new Color(255, 0, 0);
        }
        
        else if(currentPlayer == "Y") {
        	currentPlayerColor = new Color(0, 255, 0);
        }
        
        else if(currentPlayer == "Z") {
        	currentPlayerColor = new Color( 0, 0, 255);
        }
        
        else {
        	currentPlayerColor = new Color( 0, 0, 0);
        }
        
        
        for (int i = 0; i < height; i++) {
        	
        	for (int j = 0; j < width; j++) {
            	
            	if( i == 0 ) {
            		
            		final JButton firstButton = new JButton((j+1) + " " + board[i][j]);
            		
            		if(board[i][j] == "X") {
                    	currentPlayerColor = new Color(255, 0, 0);
                    }
                    
                    else if(board[i][j] == "Y") {
                    	currentPlayerColor = new Color(0, 255, 0);
                    }
                    
                    else if(board[i][j] == "Z") {
                    	currentPlayerColor = new Color( 0, 0, 255);
                    }
            		
                    else {
                    	currentPlayerColor = new Color( 0, 0, 0);
                    }
            		
            		firstButton.setBackground(currentPlayerColor);
            		firstButton.setOpaque(true);
                	panel.add(firstButton);
                	
                	class ButtonClickListener implements ActionListener {
                    	
                        public void actionPerformed(ActionEvent e) {
                        	
                           clickedCol = Integer.parseInt(String.valueOf(firstButton.getText().charAt(0))) - 1;
                           
                           buttonClicked = true;
                           
                        }
                        
                    }
                    firstButton.addActionListener(new ButtonClickListener());
                	
                	
            	}
            	
            	else {

                	JButton button = new JButton(board[i][j]);
                	
                	if(board[i][j] == "X") {
                    	currentPlayerColor = new Color(255, 0, 0);
                    }
                    
                    else if(board[i][j] == "Y") {
                    	currentPlayerColor = new Color(0, 255, 0);
                    }
                    
                    else if(board[i][j] == "Z") {
                    	currentPlayerColor = new Color( 0, 0, 255);
                    }
                	
                    else {
                    	currentPlayerColor = new Color( 0, 0, 0);
                    }
                	
                	button.setBackground(currentPlayerColor);
                	button.setOpaque(true);
                	panel.add(button);
                	
            	}
            	
            }
            
		}
        
        frame.pack();
		frame.setVisible(true);
        
    }
	
}

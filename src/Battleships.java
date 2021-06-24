/*Battleships Test Console only version 
  Glenn Kendall 
  08/02/2021
*/

import java.util.Scanner;
import java.util.Random; 
import java.util.ArrayList; 

public class Battleships {
	
	public static void main (final String[]args) {
		
		Grid p1Grid = new Grid(); 
		Grid cpuGrid = new Grid();
				
		boolean gameOver = false; 
		Scanner tInput = new Scanner(System.in);	 
		ArrayList<String> p1Guesses = new ArrayList<String>(); 
		ArrayList<String> cpuGuesses = new ArrayList<String>(); 
		p1Grid.setPlayer(1);
		cpuGrid.setPlayer(2);
		int playerTurn = 1;
		boolean validGuess = false;
		boolean shipsSet = false; 
	
		
		while (gameOver==false) {
			
			
			
			while(shipsSet==false) {
				System.out.println("SELECT YOUR STARTING POSITION FOR YOUR BATTLESHIP (5 GRIDS WIDE) =");
				String battleStart = tInput.next(); 
				System.out.println("TRIGGERS CHECK THAT THIS CAN BE ALLOCATED"); 
				
				System.out.println("SELECT YOUR STARTING POSITION FOR YOUR FIRST DESTROYER SHIP (4 GRIDS WIDE) =");
				String dest1Start = tInput.next(); 
				System.out.println("TRIGGERS CHECK THAT THIS CAN BE ALLOCATED"); 
				
				System.out.println("SELECT YOUR STARTING POSITION FOR YOUR SECOND DESTROYER SHIP (4 GRIDS WIDE) =");
				String dest2Start = tInput.next(); 
				System.out.println("TRIGGERS CHECK THAT THIS CAN BE ALLOCATED\n----------------------------------");
				
				Grid.SetPosition(battleStart, dest1Start, dest2Start, p1Grid, cpuGrid); 
				
					
				
				shipsSet = true; 
			}
			
//TURNS 		
		if (p1Grid.isActive==true&&cpuGrid.isActive==true) {
			 
			
			while (playerTurn==1){
				
				String choice = null; 
				validGuess = false; 
				
				
				if (validGuess==false) {
					
				System.out.println("Player "+p1Grid.player+" \nPlease select a square to attack: ");
				choice = tInput.next(); 
				
				validGuess = guessValid(validGuess,p1Guesses, cpuGuesses, choice, playerTurn);
				}
				
	
				
				
				if(validGuess==true) {
				p1result(choice,cpuGrid,p1Grid,gameOver,playerTurn, p1Guesses);
					playerTurn++; 
				}
			}
			
			
			if (gameOver==false) {	
				
				while (playerTurn==2){
					
				String choice = null; 
				validGuess = false; 
				
				if (validGuess==false) {
			
				
				choice = Grid.randomGrid(cpuGrid); 
				
				validGuess = guessValid(validGuess,p1Guesses, cpuGuesses, choice, playerTurn);
		
				}
				
				if(validGuess==true) {
					System.out.print("CPU\nPlease select a square to attack: ");
					System.out.print(choice.toUpperCase()+"\n");
					cpuResult(choice,p1Grid,cpuGrid,gameOver,playerTurn, cpuGuesses);	
					playerTurn--; 
				}
			}
				
			}
			
		}
				
	}
		tInput.close();
	}
//
	
	
	
	
	public static void p1result(String choice, Grid cpuGrid, Grid p1Grid, boolean gameOver, int playerTurn, ArrayList<String> p1Guesses) {
		
		boolean btlHit = false;
		int hitCounter = 0; 
		 
		String btlTarg[] = {cpuGrid.getBattlePos()[0].toString(),cpuGrid.getBattlePos()[1].toString(),cpuGrid.getBattlePos()[2].toString(),
				cpuGrid.getBattlePos()[3].toString(),cpuGrid.getBattlePos()[4].toString()};
		int turns = p1Guesses.size()+1; 
		String newGuess = choice; 
		p1Guesses.add(newGuess); 
		
		
		
		for(int i=0; i<btlTarg.length; i++) {
			if (choice.equalsIgnoreCase((btlTarg[i]))) {
				System.out.println("Hit!!!\n");	
				hitCounter(hitCounter, cpuGrid, p1Grid, playerTurn); 
				System.out.println("TESTING CPU HIT COUNTER: "+cpuGrid.battleStatus);
				btlHit = true; 
			}
		}
		
	
		
		if (btlHit ==false) {
			System.out.println("Miss.\n");
		}
		
		
		
		if(cpuGrid.isActive==false) {
			endGame(p1Grid, cpuGrid, gameOver,playerTurn,turns); 
		}
		
	}
		
	
	
	
	public static void cpuResult(String choice, Grid p1Grid, Grid cpuGrid, boolean gameOver, int playerTurn,ArrayList<String> cpuGuesses) {
	
		boolean btlHit = false;
		int hitCounter = 0; 
		 
		String btlTarg[] = {p1Grid.getBattlePos()[0].toString(),p1Grid.getBattlePos()[1].toString(),p1Grid.getBattlePos()[2].toString(),
				p1Grid.getBattlePos()[3].toString(),p1Grid.getBattlePos()[4].toString()};
		int turns = cpuGuesses.size()+1; 
		String newGuess = choice; 
		cpuGuesses.add(newGuess); 
		
		
		
		for(int i=0; i<btlTarg.length; i++) {
			if (choice.equalsIgnoreCase((btlTarg[i]))) {
				System.out.println("Hit!!!\n");	
				hitCounter(hitCounter, cpuGrid, p1Grid, playerTurn); 
				System.out.println("TESTING P1 HIT COUNTER: "+p1Grid.battleStatus);
				btlHit = true; 
			}
		}
		
		if (btlHit == false) {
			System.out.println(choice.toUpperCase()+" - Miss.\n");
		
		}
		
		if(p1Grid.isActive==false) {
			endGame(p1Grid, cpuGrid, gameOver, playerTurn, turns); 
		}
			
	}
	
	
	
	
//End of program, triggered when a player loses all of their ships 	
	public static void endGame(Grid p1Grid, Grid cpuGrid, boolean gameOver, int playerTurn,int turns) {

		if (playerTurn ==1) {
			System.out.print("----------Player 1 wins!!!----------\nGuesses to win: "+turns);		
		
			
			gameOver= true; 
			System.exit(0);
		}
		
		if (playerTurn ==2) {
			System.out.print("CPU wins!!!\nGuesses to win: "+turns);
			gameOver= true;
			System.exit(0);
		}
	}
	
	
	public static int hitCounter(int hitCounter, Grid cpuGrid, Grid p1Grid, int playerTurn) {
		
		if (playerTurn==1) {
			if(cpuGrid.battleStatus<5) {
				cpuGrid.battleStatus++;  
			}
	
			if(cpuGrid.battleStatus==5&&cpuGrid.dest1Status==4&&cpuGrid.dest2Status==4) {
				cpuGrid.isActive=false; 
			}
		}
		
		else if (playerTurn==2) {
			if(p1Grid.battleStatus<5) {
				p1Grid.battleStatus++;  
			}
	
			if(p1Grid.battleStatus==5&&p1Grid.dest1Status==4&&p1Grid.dest2Status==4) {
				p1Grid.isActive=false; 
			}
		}
		
		return hitCounter; 
	}
	

//Determines if the guess can go ahead, or if the user has already tried this selection 	
	public static Boolean guessValid(boolean validGuess,ArrayList<String> p1Guesses,ArrayList<String> cpuGuesses, String choice, int playerTurn)
	{
		
		if(playerTurn==1) {
			
				if(p1Guesses.size()==0) {
					validGuess = true; 
		    }
			
			else if(p1Guesses.size()>0) {
	
				for(int i=0; i<p1Guesses.size(); i++) {
		
					if(choice.equalsIgnoreCase(p1Guesses.get(i))) {					
						System.out.println("You have already tried this square. Try again.");
						validGuess = false;
						break;
					}
					
					else 
						validGuess = true; 
				}
			}
		}
		
		
		else if(playerTurn==2) {
		
			
			if(cpuGuesses.size()==0) {
				validGuess = true; 
		    }
			
			else if(cpuGuesses.size()>0) {
				
				for(int i=0; i<cpuGuesses.size(); i++) {
		
					if(choice.equalsIgnoreCase(cpuGuesses.get(i))) {
						validGuess = false;
						break;
					}
					else 
					validGuess = true; 		
				}
					
			}
		}
			return validGuess; 
	}

	
}

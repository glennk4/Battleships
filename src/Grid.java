import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	public boolean isActive =true;
	public int player; 
	public String [] blankRow = {"a","b","c","d","e","f","g","h","i","j"}; 
	public int [] blankCol = {1,2,3,4,5,6,7,8,9,10}; 
 	public String battlePos[] = new String[5]; 
 	public String dest1Pos[] = new String[4]; 
 	public String dest2Pos[] = new String[4]; 
	public int battleStatus;  
	public int dest1Status;
	public int dest2Status;
	public String[] guesses; 

	
//Getters & Setters 	
	public int getPlayer() {
		return player;
	}
	
	public void setPlayer(int player) {
		this.player = player; 
	}
	
	public boolean getActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String[] getBlankRow() {
		return blankRow;
	}

	public void setBlankRow(String[] blankRow) {
		this.blankRow = blankRow;
	}

	public int[] getBlankCol() {
		return blankCol;
	}

	public void setBlankCol(int[] blankCol) {
		this.blankCol = blankCol;
	}

	public String[] getBattlePos() {
		return battlePos;
	}

	public void setBattlePos(String[] battlePos) {
		this.battlePos = battlePos;
	}

	public String[] getDest1Pos() {
		return dest1Pos;
	}

	public void setDest1Pos(String[] dest1Pos) {
		this.dest1Pos = dest1Pos;
	}

	public String[] getDest2Pos() {
		return dest2Pos;
	}

	public void setDest2Pos(String[] dest2Pos) {
		this.dest2Pos = dest2Pos;
	}

	public int getBattleStatus() {
		return battleStatus;
	}

	public void setBattleStatus(int battleStatus) {
		this.battleStatus = battleStatus;
	}

	public int getDest1Status() {
		return dest1Status;
	}

	public void setDest1Status(int dest1Status) {
		this.dest1Status = dest1Status;
	}

	public int getDest2Status() {
		return dest2Status;
	}

	public void setDest2Status(int dest2Status) {
		this.dest2Status = dest2Status;
	}
	
	public String[] guesses(String[] guesses) {
		return guesses; 
	}
	
	public void setGuesses (String[] guesses) {
		this.guesses = guesses; 
	}
	
	
	
	public static void SetPosition(String battleStart, String dest1Start, String dest2Start, Grid p1Grid, Grid cpuGrid) {
		
		
//Sets the battleShip Positions - Still not coded above column 6   
				String cpuBattle = randomGrid(cpuGrid); 
				System.out.println("TESTING CPU START POS : "+cpuBattle.toUpperCase());
				

				int bStartRow = Character.getNumericValue(battleStart.charAt(1)); 
				int bStartCol = battleStart.charAt(0); 
				
//this seems wasted, should really be a smaller loop 
				if(bStartRow<=6&&battleStart.length()==2) {
				
					int battleNumbers[] = {Character.getNumericValue(battleStart.charAt(1)), Character.getNumericValue(battleStart.charAt(1)+1),
							Character.getNumericValue(battleStart.charAt(1)+2),Character.getNumericValue(battleStart.charAt(1)+3),
							Character.getNumericValue(battleStart.charAt(1)+4)};
					
					String battlePos[] = {battleStart, battleStart.substring(0, 1)+battleNumbers[1],battleStart.substring(0, 1)+battleNumbers[2],
							battleStart.substring(0, 1)+battleNumbers[3],battleStart.substring(0, 1)+battleNumbers[4]}; 
					
					int cpuBtNumbers[] = {Character.getNumericValue(cpuBattle.charAt(1)), Character.getNumericValue(cpuBattle.charAt(1)+1),
							Character.getNumericValue(cpuBattle.charAt(1)+2),Character.getNumericValue(cpuBattle.charAt(1)+3),
							Character.getNumericValue(cpuBattle.charAt(1)+4)};
					
					String cpuBtPos[] = {cpuBattle, cpuBattle.substring(0, 1)+cpuBtNumbers[1],cpuBattle.substring(0, 1)+cpuBtNumbers[2],
							cpuBattle.substring(0, 1)+cpuBtNumbers[3],cpuBattle.substring(0, 1)+cpuBtNumbers[4]};
				
					p1Grid.setBattleStatus(0);
					cpuGrid.setBattleStatus(0);
	
			
				
					p1Grid.setBattlePos(battlePos);			
					cpuGrid.setBattlePos(cpuBtPos);
					
					System.out.println("TESTING CPU POS: "); 
						
						
				}
				
				else if(bStartRow>6&&bStartRow<=11&&battleStart.length()==2){
						
					int index = 1; 
					int battleVals[] = new int[5]; 
					battleVals[0]=bStartCol; 
					
					while (index<5) {
					
						battleVals[index] = battleVals[index-1];
						battleVals[index]++; 
						index++; 
					}
					char battleChars[] = {(char)battleVals[0],(char)battleVals[1],(char)battleVals[2],(char)battleVals[3],(char)battleVals[4]}; 
					
					String columnString = String.valueOf(bStartRow); 
					String battlePos[] = {battleStart, battleChars[1]+columnString,battleChars[2]+columnString,
							battleChars[3]+columnString,battleChars[4]+columnString};
					
					int cpuBtNumbers[] = {Character.getNumericValue(cpuBattle.charAt(1)), Character.getNumericValue(cpuBattle.charAt(1)+1),
							Character.getNumericValue(cpuBattle.charAt(1)+2),Character.getNumericValue(cpuBattle.charAt(1)+3),
							Character.getNumericValue(cpuBattle.charAt(1)+4)};

					String cpuBtPos[] = {cpuBattle, cpuBattle.substring(0, 1)+cpuBtNumbers[1],cpuBattle.substring(0, 1)+cpuBtNumbers[2],
							cpuBattle.substring(0, 1)+cpuBtNumbers[3],cpuBattle.substring(0, 1)+cpuBtNumbers[4]};
			
					p1Grid.setBattleStatus(0);
					cpuGrid.setBattleStatus(0);
	
			
				
					p1Grid.setBattlePos(battlePos);			
					cpuGrid.setBattlePos(cpuBtPos);
					
				}
				
			
		//CODE DESTROYERS HERE
				
				
//Set to 4 temporarily, will be 0 when fixed 
				p1Grid.setDest1Status(4);
				p1Grid.setDest2Status(4);
				cpuGrid.setDest1Status(4);
				cpuGrid.setDest2Status(4);
				
				
				
				
			}
	
	
	//Random Grid generator, used for CPU guesses and allocating position of CPU ship placement
		public static String randomGrid(Grid cpuGrid) {
			
			String randomGrid = null; 
			Random randInt = new Random();
			int randomColumn = randInt.nextInt(10)+1;
			int randomRow = randInt.nextInt(10); 
			randomGrid = cpuGrid.blankRow[randomRow]+randomColumn; 
			return randomGrid;
		}
		
	
	
	
	

}
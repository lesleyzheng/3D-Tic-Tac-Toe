//interface and class respectively used as one of the data structures
import oc.MyGenericDS;

public class Faces{
	
	//2D array used to store the value of each square (O or X)
	private static char[][][][] FaceArray=new char[6][3][3][2];

	//Statement of whether the game is still live, where 0 - alive, 1 - x wins, 2 - o wins, 3 - draw
	protected static int GameOver=0;

	//Counter for the number of turns passed, used to track whose turn it is
	protected static int TurnCount=0;

	//Value of the current players piece (O or X)
	protected static char Move;
	
	//DataStructure used to keep track of the moves already played
	protected static MyGenericDS<Character> Used=new MyGenericDS<Character>();

	protected static int JChange=0;
	protected static int IChange=0;
	
	//Counters used to keep track of the O's and X's in a row
	static protected int XCount=0;
	static protected int OCount=0;

	//Method used to initialize the board in a null state orientated the way it should be on the cube
	protected static char[][][][] FaceInitiation(int Orientation, int FaceNumber){

		//Declared again to reset the values if they were changed in the last calling of this method.
		IChange=0;
		JChange=0;
		
		//Changes the appropriate values such that the face is rotated appropriately
		if(Orientation==180){
			JChange=2;
			IChange=2;
		}
		
		//Gives the board numeric values (for testing without graphics) in the correct orientation
		char Initial='1';
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				FaceArray[FaceNumber][Math.abs(IChange-i)][Math.abs(JChange-j)][0]=Initial;
				Initial=(char)(Initial+1);
			}
		}

		return FaceArray;
	}
	
	//Methods that replaces the chosen space to move into with the current players respective value, 
	//depending on where on the board depends on which faces are altered
	protected static void UpdateBoard(int Inp, int FaceNumber){
		
		//Adds the integer to the list of used integers
		Used.append((char)('0'+Inp));
		Used.attach((char)('0'+FaceNumber));
		
		//Detects the orientation of the face
		if (FaceNumber%2==1){
			IChange=2;
			JChange=2;
		}
		else{
			IChange=0;
			JChange=0;
		}

		if (Inp==1){
			FaceArray[FaceNumber][Math.abs(IChange-0)][Math.abs(JChange-0)][0]=Move;
			if (FaceNumber%2==0){
				FaceArray[(FaceNumber+5)%6][0][2][0]=Move;
				Used.append((char)('7'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
				FaceArray[(FaceNumber+4)%6][2][2][0]=Move;
				Used.append((char)('9'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+1)%6][2][0][0]=Move;
				Used.append((char)('7'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
				FaceArray[(FaceNumber+2)%6][0][0][0]=Move;
				Used.append((char)('9'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
			}
		}
		if (Inp==2){
			FaceArray[FaceNumber][Math.abs(IChange-0)][Math.abs(JChange-1)][0]=Move;
				if(FaceNumber%2==0){
				FaceArray[(FaceNumber+4)%6][1][2][0]=Move;
				Used.append((char)('6'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+2)%6][1][0][0]=Move;
				Used.append((char)('6'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
			}
		}
		if (Inp==3){
			FaceArray[FaceNumber][Math.abs(IChange-0)][Math.abs(JChange-2)][0]=Move;
				if (FaceNumber%2==0){
				FaceArray[(FaceNumber+4)%6][0][2][0]=Move;
				Used.append((char)('3'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
				FaceArray[(FaceNumber+2)%6][0][2][0]=Move;
				Used.append((char)('3'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+2)%6][2][0][0]=Move;
				Used.append((char)('3'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
				FaceArray[(FaceNumber+4)%6][2][0][0]=Move;
				Used.append((char)('3'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
			}
		}
		if (Inp==4){
			FaceArray[FaceNumber][Math.abs(IChange-1)][Math.abs(JChange-0)][0]=Move;
			if(FaceNumber%2==0){
				FaceArray[(FaceNumber+5)%6][1][2][0]=Move;
				Used.append((char)('4'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+1)%6][1][0][0]=Move;
				Used.append((char)('4'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
			}
		}
		if (Inp==5){
			FaceArray[FaceNumber][Math.abs(IChange-1)][Math.abs(JChange-1)][0]=Move;
		}
		if (Inp==6){
			FaceArray[FaceNumber][Math.abs(IChange-1)][Math.abs(JChange-2)][0]=Move;
				if(FaceNumber%2==0){
				FaceArray[(FaceNumber+2)%6][0][1][0]=Move;
				Used.append((char)('2'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+4)%6][2][1][0]=Move;
				Used.append((char)('2'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
			}
		}
		if (Inp==7){
			FaceArray[FaceNumber][Math.abs(IChange-2)][Math.abs(JChange-0)][0]=Move;
			if (FaceNumber%2==0){
				FaceArray[(FaceNumber+5)%6][2][2][0]=Move;
				Used.append((char)('1'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
				FaceArray[(FaceNumber+1)%6][0][0][0]=Move;
				Used.append((char)('9'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+1)%6][0][0][0]=Move;
				Used.append((char)('1'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
				FaceArray[(FaceNumber+5)%6][2][2][0]=Move;
				Used.append((char)('9'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
			}
		}
		if (Inp==8){
			FaceArray[FaceNumber][Math.abs(IChange-2)][Math.abs(JChange-1)][0]=Move;
			if(FaceNumber%2==0){
				FaceArray[(FaceNumber+1)%6][0][1][0]=Move;
				Used.append((char)('8'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+5)%6][2][1][0]=Move;
				Used.append((char)('8'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
			}
		}
		if(Inp==9){
			FaceArray[FaceNumber][Math.abs(IChange-2)][Math.abs(JChange-2)][0]=Move;
				if (FaceNumber%2==0){
				FaceArray[(FaceNumber+1)%6][0][2][0]=Move;
				Used.append((char)('7'));
				Used.attach((char)('0'+(FaceNumber+1)%6));
				FaceArray[(FaceNumber+2)%6][0][0][0]=Move;
				Used.append((char)('1'));
				Used.attach((char)('0'+(FaceNumber+2)%6));
			}
			else if (FaceNumber%2==1){
				FaceArray[(FaceNumber+5)%6][2][0][0]=Move;
				Used.append((char)('7'));
				Used.attach((char)('0'+(FaceNumber+5)%6));
				FaceArray[(FaceNumber+4)%6][2][2][0]=Move;
				Used.append((char)('1'));
				Used.attach((char)('0'+(FaceNumber+4)%6));
			}
		}
	//Many statements used to detect the input and alter the board accordingly (used more in debugging for any input since the cube click input is much more restricted but still utilised to update the board there)
		
	//Prints an updated copy of the board with the new move in play and the remaining spaces shown
		PrintBoard();
		
	}
	
	//Method used to determine the state of the game (over via win, over via draw or not over)
	protected static void CheckGameStatus(){

		//Method that runs through all of the columns and determines whether there has been a win
		for (int FaceNumber=0; FaceNumber<6; FaceNumber++){
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					if (FaceArray[FaceNumber][i][j][0]=='O'){
						OCount=OCount+1;
					}
					else if (FaceArray[FaceNumber][i][j][0]=='X'){
						XCount=XCount+1;
					}
					if (OCount==3){
						GameOver=2;
						System.out.println("Player 2 wins!");
					}
					else if (XCount==3){
						GameOver=1;
						System.out.println("Player 1 wins!");
					}
				}
				OCount=0;
				XCount=0;
			}

			//Method that runs through all of the rows and determines whether there has been a win
			for (int j=0; j<3; j++){
				for (int i=0; i<3; i++){
					if (FaceArray[FaceNumber][i][j][0]=='O'){
						OCount=OCount+1;
					}
					else if (FaceArray[FaceNumber][i][j][0]=='X'){
						XCount=XCount+1;
					}
					if (OCount==3){
						GameOver=2;
						System.out.println("Player 2 wins!");
					}
					else if (XCount==3){
						GameOver=1;
						System.out.println("Player 1 wins!");
					}
				}
				OCount=0;
				XCount=0;
			}

			//Method that runs through the main diagonal and determines whether there has been a win
			for (int i=0; i<3; i++){
				if (FaceArray[FaceNumber][i][i][0]=='O'){
					OCount=OCount+1;
				}
				else if (FaceArray[FaceNumber][i][i][0]=='X'){
					XCount=XCount+1;
				}
				if (OCount==3){
					GameOver=2;
					System.out.println("Player 2 wins!");
				}
				else if (XCount==3){
					GameOver=1;
					System.out.println("Player 1 wins!");
				}
			}
			XCount=0;
			OCount=0;
			
			//Method that runs through the off diagonal and determines whether there has been a win
			for (int i=0; i<3; i++){
				if (FaceArray[FaceNumber][i][2-i][0]=='O'){
					OCount=OCount+1;
				}
				else if (FaceArray[FaceNumber][i][2-i][0]=='X'){
					XCount=XCount+1;
				}
				if (OCount==3){
					GameOver=2;
					System.out.println("Player 2 wins!");
				}
				else if (XCount==3){
					GameOver=1;
					System.out.println("Player 1 wins!");
				}
			}
			XCount=0;
			OCount=0;
		}
		
		////Method that determines whether the game is over via draw
		if (Used.length()==54 && GameOver==0){
			System.out.println("It's a Draw!");
			GameOver=3;
		}
		
		//Calls to the popup window when a game is over
		if(GameOver!=0) {
			createPopup.display();
		}
	}
	
	//Method that prints the board in the cmd line
	protected static void PrintBoard(){
		
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print(FaceArray[1][j][i][0]);
			}
			System.out.println();
		}
		for (int i=0; i<3; i++){
			for (int k=0; k<2; k++){
				for (int j=0; j<3; j++){
					System.out.print(FaceArray[k+2][j][i][0]);
				}
			}
			System.out.println();
		}
		for (int i=0; i<3; i++){
			for (int k=0; k<3; k++)
				for (int j=0; j<3; j++){
				if (k<1){
					System.out.print(" ");
				}
				else{
					System.out.print(FaceArray[k+3][j][i][0]);
				}
			}
			System.out.println();
		}
		for (int i=0; i<3; i++){
			for (int k=0; k<3; k++)
				for (int j=0; j<3; j++){
				if (k<2){
					System.out.print(" ");
				}
				else{
					System.out.print(FaceArray[0][j][i][0]);
				}
			}
			System.out.println();
		}
	}
}
//https://www.tutorialspoint.com/java/lang/math_abs_int.htm (Used to figure how to take the absolute value of a number)
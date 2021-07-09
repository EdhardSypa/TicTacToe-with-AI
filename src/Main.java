import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	static final char[][] FIELD = new char[3][3] ;
    static String state = "Game not finished";
    static ArrayList<Point> xCoords = new ArrayList<>();
    static ArrayList<Point> oCoords = new ArrayList<>();

    static void fillField(String arrange) { 							// Method for filling a field with received data from a variable "arrange"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FIELD[i][j] = arrange.charAt(3 * i + j);
                if (FIELD[i][j] == 'X') {                   
                    xCoords.add(new Point(i, j));
                } else if (FIELD[i][j] == 'O') {
                    oCoords.add(new Point(i, j));
                }
            }
        }
    }

    static void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%c ", FIELD[i][j]);
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }
    
    static void enterCoords() {
        int x, y;
        var scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        try {
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            enterCoords();
            return;
        }

        if (x > 2 || y > 2 || x < 0 || y < 0) {								// Ñheck the validity of the entered coordinates
            System.out.println("Coordinates should be from 1 to 3!");
            enterCoords();
            return;
        }

        if (FIELD[x][y] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            enterCoords();
            return;
        }

        if (xCoords.size() <= oCoords.size()) {							// Check whose move and fill in the field
            FIELD[x][y] = 'X';
            xCoords.add(new Point(x, y));
        } else {
            FIELD[x][y] = 'O';
            oCoords.add(new Point(x, y));
        }

    }
    
    static void printState() {											// Method for print the winner
        if (isWinner(FIELD, 'X')) {
            state = "X wins";
        } else if (isWinner(FIELD, 'O')) {
            state = "O wins";
        } else if (xCoords.size() + oCoords.size() == 9) {
            state = "Draw";
        }
     System.out.println(state);
    }
    
    static boolean isWinner(char[][] field, char symbol) {						// Method for checking the winner, it's check coordinates and symbol 'X' or 'O'
    	if (field[0][0] == field[0][1] && field[0][2] == field[0][1]) {
            return true;
        } else if (field[1][0] == field[1][1] && field[1][2] == field[1][1] && field[1][0] == symbol) {
        	return true;
        } else if (field[2][0] == field[2][1] && field[2][2] == field[2][1] && field[2][0] == symbol) {
        	return true;
        } else if (field[0][0] == field[1][0] && field[2][0] == field[1][0] && field[0][0] == symbol) {
        	return true;
        } else if (field[0][1] == field[1][1] && field[2][1] == field[1][1] && field[0][1] == symbol) {
        	return true;
        } else if (field[0][2] == field[1][2] && field[2][2] == field[1][2] && field[0][2] == symbol) {
        	return true;
        } else if (field[0][0] == field[1][1] && field[2][2] == field[1][1] && field[0][0] == symbol) {
        	return true;
        } else if (field[0][2] == field[1][1] && field[2][0] == field[1][1] && field[0][2] == symbol) {
        	return true;
        } 
    	
    	return false;
    }
    
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        String arrange = scanner.nextLine();
        fillField(arrange.replace("_", " ")); 										//Fill in the field with the received value from the variable arrange

        printField();
        enterCoords();
        printField();
        printState();
    }
}

class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }
}
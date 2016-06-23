package Assignment2.UI;

import Assignment2.core.Coordinate;
import Assignment2.core.Map;
import Assignment2.core.Square;
import java.util.Scanner;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameMenu {
    private static final int MIN_MENU_CHOICE = 1;
    private static final int MAX_MENU_CHOICE = 6;
    private static final char FIRST_LETTER = 'A';
    private Scanner scanner;
    private GameEngine logic;

    public GameMenu(){
        this.scanner = new Scanner(System.in);
        logic = new GameEngine();
    }

    public void start(){
        displayMap();

        while (makeMenuSelection()) {
            displayMap();

            if (logic.wonGame()){
                System.out.println("YOU WIN!!");
                break;
            }

            logic.enemyAttack();
            System.out.printf("Enemies attacked for %d damage! \n", logic.getEnemyAttackPower());
            System.out.printf("Health remaining: %d \n", logic.getRemainingHealth());

            if(logic.lostGame()){
                System.out.println("Game Over! You Lost!");
                displayClearedMap();
                break;
            }
        }
    }

    private void displayMap() {
        showMap(false);
    }

    private void displayClearedMap(){
        showMap(true);
    }
    
    private void showMap(boolean clearedMap){
        Map map = logic.getMap();
        int numRow = logic.getNumRow();
        int numCol = logic.getNumCol();

        String result = " ";
        for(int i = 1; i <= numCol; i++){
            result += " " + i + " ";
        }
        result += "\n";
        String horizontal = "";

        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                Square square = map.getSquare(new Coordinate(i, j));
                if (clearedMap){
                    square.attack();
                }
                horizontal += " " + square + " ";
            }
            result += (char)(FIRST_LETTER + i) + horizontal + "\n";
            horizontal = "";
        }

        System.out.println(result + "\n");
    }

    private boolean makeMenuSelection(){
        while (true) {
            printMenu();
            switch (getChoice()) {
                case 1:
                    boolean invalid = true;
                    Coordinate coords = null;
                    while (invalid) {
                        String input = getInput(logic.getNumRow(), logic.getNumCol());
                        coords = new Coordinate(input);

                        if (!logic.isClearSquare(coords)) {
                            System.out.println("Choose another location, you already hit this spot");
                            continue;
                        }
                        invalid = false;
                    }

                    logic.attack(coords);
                    return true;
                case 2:
                    System.out.printf("Health remaining: %d \n", logic.getRemainingHealth());
                    break; //Repeats selection
                case 3:
                    System.out.printf("Number of enemies remaining: %d \n", logic.numEnemiesRemaining());
                    break; //Repeats selection
                case 4:
                    System.out.printf("Enemies now do %d damage per turn \n", logic.getEnemyAttackPower());
                    break; //Repeats selection
                case 5:
                    System.out.println("You surrender!!! DEFEAT...");
                    return false;
                case 6:
                    System.out.println("Good bye...");
                    return false;
                default:
                    assert true;
            }
        }
    }

    private void printMenu(){
        System.out.println("\nGame Menu:");
        System.out.println("************************\n");
        System.out.println("Choose Option:\n");
        System.out.println("1. Attack the enemy! ");
        System.out.println("2. Display remaining health");
        System.out.println("3. Display number of enemies remaining");
        System.out.println("4. Display enemy attack power");
        System.out.println("5. Surrender");
        System.out.println("6. Quit Game\n");

    }

    private int getChoice(){
        boolean invalidInput = true;
        String choiceStr;
        int choice = 1;
        while (invalidInput) {
            choiceStr = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (Exception e){
                System.out.println("Only integers!");
                continue;
            }
            if(choice < MIN_MENU_CHOICE || choice > MAX_MENU_CHOICE){
                System.out.printf(
                        "Invalid choice, please select a choice between %d and %d%n",
                        MIN_MENU_CHOICE, MAX_MENU_CHOICE);
            } else {
                invalidInput = false;
            }
        }
        return choice;
    }

    private String getInput(int mapNumRow, int mapNumCol){
        boolean invalidInput = true;
        String target = null;

        while (invalidInput) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which Location to attack?");
            target = scanner.nextLine();
            boolean inputOK = verifyInput(target, mapNumRow, mapNumCol);

            if(inputOK) {
                invalidInput = false;
            } else {
                System.out.printf(
                        "Invalid coordinates please enter a coordinate between A1 and %c%d\n",
                        'A' + mapNumRow - 1, mapNumCol);
            }
        }
        return target;
    }

    private boolean verifyInput(String input, int mapNumRow, int mapNumCol){
        if (input.length() != 2 && input.length() != 3){
            return false;
        }
        Coordinate coordinate = new Coordinate(input);
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        boolean validRow = (0 <= row) && (row < mapNumRow);
        boolean validCol = (0 <= col) && (col < mapNumCol);

        return validCol && validRow;
    }
}

package Assignment2.UI;

import Assignment2.core.Coordinate;
import java.util.Scanner;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameMenu {
    private static final int MIN_MENU_CHOICE = 1;
    private static final int MAX_MENU_CHOICE = 6;
    private Scanner scanner;

    public GameMenu(){
        this.scanner = new Scanner(System.in);
    }

    public int main(){
        printMenu();
        return getChoice();
    }

    public String getInput(int mapNumRow, int mapNumCol){
        boolean invalidInput = true;
        String target = null;

        while (invalidInput) {
            System.out.println("Which Location to attack?");
            target = scanner.nextLine();
            boolean inputOK = verifyInput(target, mapNumRow, mapNumCol);

            if(inputOK) {
                invalidInput = false;
            } else {
                System.out.printf(
                        "Invalid coordinates please enter a coordinate between A1 and %c%d",
                        'A' + mapNumRow - 1, mapNumCol);
            }
        }
        return target;
    }

    private void printMenu(){
        System.out.println("Game Menu:");
        System.out.println("************************\n");
        System.out.println("Choose Option:\n\n");
        System.out.println("1. Attack the enemy! ");
        System.out.println("2. Display remaining health");
        System.out.println("3. Display number of enemies remaining");
        System.out.println("4. Display enemy attack power");
        System.out.println("5. Surrender");
        System.out.println("6. Quit Game\n\n");

    }

    private int getChoice(){
        boolean invalidInput = true;
        int choice = 0;
        while (invalidInput) {
            choice = scanner.nextInt();
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

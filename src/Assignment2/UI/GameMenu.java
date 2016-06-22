package Assignment2.UI;

import java.util.Scanner;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameMenu {
    public int main(){
        printMenu();
        return getChoice();
    }
    public GameMenu(){

    }

    public String attackMenu(int mapSize){
        boolean invalid = true;
        String target = null;
        while (invalid) {
            System.out.println("Which Location to attack?");
            Scanner scanner = new Scanner(System.in);
            target = scanner.nextLine();
            if (target.length() > 2 || target.charAt(0) < 'A' || target.charAt(0) > 'A' + mapSize || target.charAt(1) < 1 || target.charAt(1) > mapSize){
                System.out.printf("Invalid coordinates please enter a coordinate between A1 and %c%d", 'A' + mapSize, mapSize);
            }


            invalid = false;

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
        System.out.println("6. Quit Game");

    }

    private int getChoice(){
        boolean invalid = true;
        int choice = 0;
        while (invalid) {
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            if(choice < 1 || choice > 6){
                System.out.println("Invalid choice, please select a choice between 1 and 6");
            }
            invalid = false;
            scanner.close();
        }
        return choice;
    }



}

package Assignment2.UI;

import Assignment2.core.Coordinate;
import Assignment2.core.Fortress;
import Assignment2.UI.GameMenu;
import Assignment2.core.Map;
import Assignment2.logic.TankManager;

import java.util.Scanner;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameEngine {
    private static final int DEFAULT_NUM_TANKS = 5;
    private static final int DEFAULT_TANK_SIZE = 4;
    private static final int DEFAULT_MAP_ROW = 10;
    private static final int DEFAULT_MAP_COL = 10;

    private Fortress fortress;
    private GameMenu gameMenu;
    private TankManager tankManager;
    private Map map;

    public GameEngine(){
        fortress = new Fortress();
        gameMenu = new GameMenu();
        tankManager  = new TankManager(
                DEFAULT_MAP_ROW, DEFAULT_MAP_COL,
                DEFAULT_NUM_TANKS, DEFAULT_TANK_SIZE);
        this.map = tankManager.getMap();
    }


    public void run(){
        displayMap();

        while (makeSelection()) {
            displayMap();

            if (tankManager.noTanks()){
                System.out.println("YOU WIN!!");
                break;
            }

            enemyAttack();
            System.out.printf("Enemies attacked for %d damage! \n", getEnemyAttackPower());
            displayHealth();

            if(fortress.isRuined()){
                System.out.println("Game Over! You Lost!");
                break;
            }
        }
    }

    private boolean makeSelection(){
        while (true) {
            switch (gameMenu.main()) {
                case 1:
                    String input = gameMenu.getInput(DEFAULT_MAP_ROW, DEFAULT_MAP_COL);
                    Coordinate coords = new Coordinate(input);
                    boolean hasFog = map.getSquare(coords).getFog();

                    if (!hasFog) {
                        System.out.println("Repeat your choice, you already hit this spot");
                        continue;
                    }

                    attack(coords);
                    return true;
                case 2:
                    displayHealth();
                    break; //Repeats selection
                case 3:
                    numEnemiesRemaining();
                    break; //Repeats selection
                case 4:
                    System.out.printf("Enemies now do %d damage per turn \n", getEnemyAttackPower());
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

    private void enemyAttack(){
        fortress.decHealth(tankManager.getTotalDamage());
    }

    private void displayHealth(){
        System.out.printf("Health remaining: %d \n", fortress.getHealth());
    }

    private void numEnemiesRemaining(){
        System.out.printf("Number of enemies remaining: %d \n", tankManager.getNumTanks());
    }

    private boolean attack(Coordinate coords){
        if(!map.getSquare(coords).attack()){
            tankManager.revealTank(coords);
            return true;
        } else {
            return false;
        }
    }

    private int getEnemyAttackPower(){
        return tankManager.getTotalDamage();
    }

    private void displayMap() {
        //TODO map -----> toString displays maps that are 10x10 only and with reviled squares
        System.out.println(map);
    }
}





































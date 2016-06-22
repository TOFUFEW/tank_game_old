package Assignment2.logic;

import Assignment2.core.Coordinate;
import Assignment2.core.Fortress;
import Assignment2.core.GameMenu;
import Assignment2.core.Map;
import Assignment2.core.GameMenu;

import java.util.Scanner;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameEngine {
    private static final int DEFAULT_NUM_TANKS = 5;
    private static final int DEFAULT_TANK_SIZE = 4;
    private static final int DEFAULT_MAP_SIZE = 10;

    private Fortress fortress = new Fortress();
    private GameMenu gameMenu = new GameMenu();
    private Map map = new Map(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE);
    private TankManager tankManager = new TankManager(map, map.get_dimensionRow(), map.get_dimensionRow(), DEFAULT_NUM_TANKS, DEFAULT_TANK_SIZE);



    public void GameEngine(){
        boolean game_on = true;
        while (game_on) {
            map.printMap();
            boolean choosing = true;

            while (choosing) {
                switch (gameMenu.main()) {
                    case 1:
                        Coordinate coords = new Coordinate(gameMenu.attackMenu(DEFAULT_MAP_SIZE));
                        attack(coords);
                        choosing = false;
                        break;
                    case 2:
                        displayHealth();
                        break;
                    case 3:
                        numEnemiesRemaining();
                        break;
                    case 4:
                        System.out.printf("Enemies now do %d damage per turn \n", getEnemyAttackPower());
                        break;
                    case 5:
                }
            }
            if (tankManager.getNumTanks() <= 0){
                System.out.println("YOU WIN!!");
                map.printMap();
                break;
            }
            enemyAttack();
            System.out.printf("Enemies attacked for %d damage! \n", getEnemyAttackPower());
            displayHealth();
            if(fortress.getHealth() <= 0){
                System.out.println("Game Over! You Lost!");
                game_on = false;
            }
        }
        System.out.println("press any key to quit");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        scanner.close();
        return;

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



}





































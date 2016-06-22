package Assignment2.logic;

import Assignment2.core.Coordinate;
import Assignment2.core.Fortress;
import Assignment2.core.Map;

/**
 * Created by Daniel on 2016-06-21.
 */
public class GameEngine {
    private static final int DEFAULT_NUM_TANKS = 5;
    private static final int DEFAULT_TANK_SIZE = 4;
    private Fortress fortress = new Fortress();

    private Map map = new Map(10,10);

    private TankManager tankManager = new TankManager(map, map.get_dimensionRow(), map.get_dimensionRow(), DEFAULT_NUM_TANKS, DEFAULT_TANK_SIZE);
    public void GameEngine(){
//        GameMenu.main();
//        OptionsMenu.main();


    }

    private void enemyAttack(){
        int damage = tankManager.getTotalDamage();
        fortress.decHealth(damage);
    }

    private void displayHealth(){
        System.out.printf("Health remaining: %d", fortress.getHealth() + "\n");
    }

    private void numEnemiesRemaining(){
        System.out.printf("Number of enemies remaining: %d", tankManager.getNumTanks() + "\n");
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





































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
    private TankManager tankManager;
    private Map map;
    private int numRow;
    private int numCol;

    public GameEngine() {
        fortress = new Fortress();
        map = new Map(DEFAULT_MAP_ROW, DEFAULT_MAP_COL);
        tankManager = new TankManager(
                map,
                DEFAULT_MAP_ROW, DEFAULT_MAP_COL,
                DEFAULT_NUM_TANKS, DEFAULT_TANK_SIZE);
    }

    public GameEngine(int numRow, int numCol, int numTanks, int tankSize) {
        this.numRow = numRow;
        this.numCol = numCol;
        fortress = new Fortress();
        map = new Map(numRow, numCol);
        tankManager = new TankManager(
                map,
                numRow, numCol,
                numTanks, tankSize);
    }

    public Map getMap(){
        return map;
    }

    public int getNumRow(){
        return DEFAULT_MAP_ROW;
    }

    public int getNumCol(){
        return DEFAULT_MAP_COL;
    }

    public boolean isClearSquare(Coordinate coordinate) {
        return map.getSquare(coordinate).getFog();
    }

    public boolean attack(Coordinate coords){
        if(!map.getSquare(coords).attack()){
            tankManager.revealTank(coords);
            return true;
        } else {
            return false;
        }
    }

    public void enemyAttack() {
        fortress.decHealth(tankManager.getTotalDamage());
    }

    public int getRemainingHealth() {
        return fortress.getHealth();
    }

    public int numEnemiesRemaining(){
        return tankManager.getNumTanks();
    }

    public int getEnemyAttackPower(){
        return tankManager.getTotalDamage();
    }

    public boolean lostGame() {
        return fortress.isRuined();
    }

    public boolean wonGame() {
        return tankManager.noTanks();
    }

}





































package Assignment2.logic;

import Assignment2.core.Coordinate;
import Assignment2.core.Square;
import Assignment2.core.Tank;
import Assignment2.core.Coordinate;
import Assignment2.core.Map;
import java.util.List;


/**
 * Created by constantin on 21/06/16.
 */
public class TankManager {
    private Map map;
    private List<Tank> tetromino;

    public TankManager(int dimensionRow, int dimensionCol, int numTanks){
        map = new Map(dimensionRow, dimensionCol);
        generateTetrominos(numTanks);
    }

    public boolean revealTank(Coordinate coordinate) {
        Square square = map.getSquare(coordinate);
        if (square.attack()) {
            //Cell is opened now and there is a tank
            damageTank(coordinate);
            map.setSquare(coordinate, square);
            return true;
        }
        return false;
    }

    public int getTotalDamage(){
        int total = 0;
        for(Tank tank : tetromino){
            total += tank.getDamage();
        }
        return total;
    }

    //----------------------------
    // ----- Private methods -----
    //----------------------------

    private void generateTetrominos(int numTanks){
        //Initialise all tanks
    }

    private void setTank(Coordinate coordinate) {
        Square square = new Square();
        square.set_tank();
        map.setSquare(coordinate, square);
    }

    private void finishInitialisation(int dimensionRow, int dimensionCol){
        for(int i = 0; i < dimensionRow; i++){
            for(int j = 0; j < dimensionCol; j++){
                Coordinate coordinate = new Coordinate(i,j);
                if(map.getSquare(coordinate) == null) {
                    map.setSquare(coordinate, new Square());
                }
            }
        }
    }

    private void damageTank(Coordinate coordinate){
        for(Tank tank : tetromino){
            if (tank.isUnderAttack(coordinate)){
                //Tank's health was decreased
                return;
            }
        }
    }
}

package Assignment2.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Assignment2.core.Coordinate;
import Assignment2.core.Square;
import Assignment2.core.Tank;
import Assignment2.core.Map;


/**
 * Created by constantin on 21/06/16.
 */
public class TankManager {
    private Map map;
    private List<Tank> tetromino;

    public TankManager(int dimensionRow, int dimensionCol, int numTanks, int sizeTanks){
        map = new Map(dimensionRow, dimensionCol);
        generateTetrominos(dimensionRow, dimensionCol, numTanks, sizeTanks);
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

    private void generateTetrominos(int dimensionRow, int dimensionCol, int numTanks, int sizeTanks) {
        for(int i = 0; i < numTanks; i++){
            createTank(dimensionRow, dimensionCol, sizeTanks);
        }
    }

    private void createTank(int dimensionRow, int dimensionCol, int sizeTanks){
        Random random = new Random();
        Coordinate nextSquare;
        boolean emptySquare;

        do {
            int row = random.nextInt(dimensionRow);
            int col = random.nextInt(dimensionCol);

            nextSquare = new Coordinate(row, col);
            emptySquare = map.getSquare(nextSquare).getIfEmpty();
        } while (!emptySquare);

        List<Coordinate> tankPositions = new ArrayList<>();
        tankPositions.set(0, nextSquare);

        for (int i = 1; i < sizeTanks; i++) {
            List<Coordinate> possiblePositions = new ArrayList<>();
            possiblePositions.addAll(searchForAvailableSpot(nextSquare)); //TODO Add positions that didnt repeat!!!

            int index = random.nextInt(possiblePositions.size());
            nextSquare = possiblePositions.get(index);
            
            setOccupied(nextSquare);
            tankPositions.set(i, nextSquare);
        }

        Tank newTank = new Tank(tankPositions);
        tetromino.add(newTank);
    }

    private List<Coordinate> searchForAvailableSpot(Coordinate coordinate){
        return new ArrayList<>(); //TODO
    }

    private void setOccupied(Coordinate coordinate) {
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

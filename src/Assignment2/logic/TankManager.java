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
    private static int dimensionRow;
    private static int dimensionCol;

    public TankManager(int dimensionRow, int dimensionCol, int numTanks, int sizeTanks){
        map = new Map(dimensionRow, dimensionCol);
        tetromino = new ArrayList<>();
        this.dimensionRow = dimensionRow;
        this.dimensionCol = dimensionCol;

        generateTetrominos(numTanks, sizeTanks);
        finishMapInitialisation();
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

    public int getNumTanks(){
        int count = 0;
        for(Tank tank : tetromino){
            if(tank.is_dead()){
                count++;
            }
        }
        return tetromino.size() - count;
    }

    public boolean noTanks(){
        return getNumTanks() <= 0;
    }

    public Map getMap(){
        return map;
    }

    //----------------------------
    // ----- Private methods -----
    //----------------------------

    private void generateTetrominos(int numTanks, int sizeTanks) {
        for(int i = 0; i < numTanks; i++){
            createTank(sizeTanks);
        }
    }

    private void createTank(int sizeTanks){
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
        setOccupied(nextSquare);
        tankPositions.add(nextSquare);

        for (int i = 1; i < sizeTanks; i++) {
            List<Coordinate> possiblePositions = new ArrayList<>();
            possiblePositions.addAll(searchForAvailableSpot(nextSquare));

            int index = random.nextInt(possiblePositions.size());
            nextSquare = possiblePositions.get(index);

            setOccupied(nextSquare);
            tankPositions.add(nextSquare);
        }

        Tank newTank = new Tank(tankPositions);
        tetromino.add(newTank);
    }

    private List<Coordinate> searchForAvailableSpot(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        List<Coordinate> positions = new ArrayList<>();
        Coordinate coor;

        coor = new Coordinate(row + 1, col);
        if(validCoordinate(coor) && map.getSquare(coor).getIfEmpty()){
            positions.add(coor);
        }
        coor = new Coordinate(row - 1, col);
        if(validCoordinate(coor) && map.getSquare(coor).getIfEmpty()){
            positions.add(coor);
        }
        coor = new Coordinate(row, col + 1);
        if(validCoordinate(coor) && map.getSquare(coor).getIfEmpty()){
            positions.add(coor);
        }
        coor = new Coordinate(row, col - 1);
        if(validCoordinate(coor) && map.getSquare(coor).getIfEmpty()){
            positions.add(coor);
        }

        return positions;
    }

    private boolean validCoordinate(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        boolean validRow = 0 <= row && row < dimensionRow;
        boolean validCol = 0 <= col && col < dimensionCol;

        return validRow && validCol;
    }

    private void setOccupied(Coordinate coordinate) {
        Square square = new Square();
        square.set_tank();
        map.setSquare(coordinate, square);
    }

    private void finishMapInitialisation(){
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

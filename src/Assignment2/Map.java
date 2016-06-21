package Assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016-06-18.
 */
public class Map {
    private Square[][] map;
    private int dimensionRow;
    private int dimensionCol;

    public Map(int dimensionRow, int dimensionCol){
        map = new Square[dimensionRow][dimensionCol];
        this.dimensionRow = dimensionRow;
        this.dimensionCol = dimensionCol;
    }

    public Square getSquare(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return map[row][col];
    }

    public void setSquare(Coordinate coordinate, Square square){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        map[row][col] = square;
    }
}

package Assignment2.core;

/**
 * Created by Daniel on 2016-06-18.
 */
public class Map {
    private Square[][] map;
    private int dimensionRow;
    private int dimensionCol;

    public Map(int dimensionRow, int dimensionCol) {
        map = new Square[dimensionRow][dimensionCol];
        for(int i = 0; i < dimensionRow; i++){
            for(int j = 0; j < dimensionCol; j++){
                map[i][j] = new Square();
            }
        }
        this.dimensionRow = dimensionRow;
        this.dimensionCol = dimensionCol;
    }

    public Square getSquare(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return map[row][col];
    }

    public void setSquare(Coordinate coordinate, Square square) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        map[row][col] = square;
    }


    @Override
    public String toString(){
        String result = "\t\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\n";
        String horizontal = "";
        for(int i = 0; i < dimensionRow; i++){
            for(int j = 0; j < dimensionCol; j++){
                horizontal = horizontal + map[i][j];
            }
            result = result + (char)('A' + i) + "\t|" + horizontal + "\n";
            horizontal = "";
        }
        return result;
    }
}

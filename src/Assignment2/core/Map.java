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
    public int get_dimensionRow(){
        return dimensionRow;
    }
    public int get_dimensionCol(){
        return dimensionCol;
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

    public void printMap(){
        for (int i = 0; i < dimensionCol; i++){
            System.out.printf("");
        }
    }

    @Override
    public String toString(){
        String result = "Z    1  2  3  4  5  6  7  8  9  10\n";
        String horizontal = "";
        for(int i = 0; i < dimensionRow; i++){
            for(int j = 0; j < dimensionCol; j++){
                horizontal = horizontal + map[i][j];
            }
            result = result + (i + 1) + " | " + horizontal + "\n";
            horizontal = "";
        }
        return result;
    }
}

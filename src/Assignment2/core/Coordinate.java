package Assignment2.core;

/**
 * Created by constantin on 18/06/16.
 */
public class Coordinate {
    private static final char FIRST_LETTER_CHAR = 'A';
    private static final char FIRST_NUMBER_CHAR = '1';
    private int col;
    private int row;

    public Coordinate(String point){
        this.row = (int) point.charAt(0) - FIRST_LETTER_CHAR;
        if(point.length() == 3){
            char first = point.charAt(1);
            char second = point.charAt(2);
            String number = first + "" + second;
            this.col = Integer.parseInt(number) - 1;
        } else {
            this.col = (int) point.charAt(1) - FIRST_NUMBER_CHAR;
        }
    }

    public Coordinate(int row , int col){
        this.row = row;
        this.col = col;
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            //The same address
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            //Not this class or null pointer
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return (coordinate.getCol() == this.getCol()) && (coordinate.getRow() == this.getRow());
    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString(){
        return "Row: " + row + ", Col:" + col;
    }
}
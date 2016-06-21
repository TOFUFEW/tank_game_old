import Assignment2.Coordinate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by constantin on 21/06/16.
 */
public class TestCoordinate {
    @Test
    public void testCoordinateInput(){
        String invalidInput1 = "Z-";
        Assert.assertEquals(verifyInput(invalidInput1), false);

        String invalidInput2 = "Hello";
        Assert.assertEquals(verifyInput(invalidInput2), false);


        String invalidInput3 = "";
        Assert.assertEquals(verifyInput(invalidInput3), false);


        String validInput1 = "C8";
        Assert.assertEquals(verifyInput(validInput1), true);

    }


    private static final int ROW_MAX = 10;
    private static final int COL_MAX = 10;
    public boolean verifyInput(String input){
        if (input.length() != 2){
            return false;
        }
        Coordinate coordinate = new Coordinate(input);
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        boolean validRow = (0 <= row) && (row < ROW_MAX);
        boolean validCol = (0 <= col) && (col < COL_MAX);

        return validCol && validRow;
    }

}

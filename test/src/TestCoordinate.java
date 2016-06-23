import Assignment2.core.Coordinate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by constantin on 21/06/16.
 */
public class TestCoordinate {
    @Test
    public void testCoordinateInput(){
        String invalidInput1 = "Z-";
        Assert.assertEquals(verifyInput(invalidInput1, 10, 10), false);

        String invalidInput2 = "Hello";
        Assert.assertEquals(verifyInput(invalidInput2, 10, 10), false);


        String invalidInput3 = "";
        Assert.assertEquals(verifyInput(invalidInput3, 10, 10), false);


        String validInput1 = "C8";
        Assert.assertEquals(verifyInput(validInput1, 10, 10), true);


        String validInput2 = "C10";
        Assert.assertEquals(verifyInput(validInput2, 10, 10), true);

    }

    private boolean verifyInput(String input, int mapNumRow, int mapNumCol){
        if (input.length() > 3 || input.length() < 1){
            return false;
        }
        Coordinate coordinate = new Coordinate(input);
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        boolean validRow = (0 <= row) && (row < mapNumRow);
        boolean validCol = (0 <= col) && (col < mapNumCol);

        return validCol && validRow;
    }
}

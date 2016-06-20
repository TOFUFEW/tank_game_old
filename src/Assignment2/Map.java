package Assignment2;

import java.util.ArrayList;
/**
 * Created by Daniel on 2016-06-18.
 */
public class Map {
    public Map(int size){
        ArrayList<ArrayList<Square>> map = new ArrayList<ArrayList<Square>>(size);
        for (int i = 0; i < size; i++){
            map.add(new ArrayList<Square>(size));
            for (int j = 0; j < size; i++){
                map.get(0).add(new Square());
            }
        }


    }

}

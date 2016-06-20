package Assignment2;

/**
 * Created by Daniel on 2016-06-18.
 */
public class Square {
    private static boolean is_empty;
    private static boolean fog;

    public Square(){
        is_empty = true;
        fog = true;
    }

    public static boolean getIfEmpty(){
        return is_empty;
    }

    public static boolean getFog(){
        return fog;
    }

    public static boolean attack(){
        fog = false;
        if (!is_empty){
            return true;
        }
        return false;
    }
    public static boolean set_tank(){
        if (is_empty) {
            is_empty = false;
            return true;
        } else {
            return false;
        }
    }
}

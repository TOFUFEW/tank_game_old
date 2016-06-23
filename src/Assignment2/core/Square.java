package Assignment2.core;

/**
 * Created by Daniel on 2016-06-18.
 */
public class Square {
    private boolean is_empty;
    private boolean fog;

    public Square() {
        is_empty = true;
        fog = true;
    }

    public boolean getIfEmpty() {
        return is_empty;
    }

    public boolean getFog() {
        return fog;
    }

    public boolean attack() {
        fog = false;
        return !is_empty;
    }

    public boolean set_tank() {
        if (is_empty) {
            is_empty = false;
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString(){
        if(fog){
            return "~";
        }
        if(is_empty){
            return ".";
        } else {
            return "X";
        }
    }
}

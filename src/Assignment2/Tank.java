package Assignment2;

import java.util.List;

/**
 * Created by constantin on 18/06/16.
 */
public class Tank {
    private static final int NO_DAMAGE = 0;
    private static final int DAMAGE_LVL_1 = 1;
    private static final int DAMAGE_LVL_2 = 2;
    private static final int DAMAGE_LVL_3 = 5;
    private static final int DAMAGE_LVL_4 = 20;

    private int health;
    private List<Coordinate> location;
    private int damage;

    public Tank(List<Coordinate> location){
        this.location = location;
        this.health = location.size();
        changeDamage();
    }

    public boolean isUnderAttack(Coordinate coordinate){
        for(Coordinate current : location){
            if(current.equals(coordinate)){
                health--;
                location.remove(current);
                changeDamage();
                return true;
            }
        }
        return false;
    }

    public int getDamage(){
        return damage;
    }

    private void changeDamage(){
        switch(health){
            case 0: damage = NO_DAMAGE;
                break;
            case 1: damage = DAMAGE_LVL_1;
                break;
            case 2: damage = DAMAGE_LVL_2;
                break;
            case 3: damage = DAMAGE_LVL_3;
                break;
            case 4: damage = DAMAGE_LVL_4;
                break;
            default: damage = -1;
                break;
        }
    }
}

package Assignment2.core;

/**
 * Created by constantin on 18/06/16.
 */
public class Fortress {
    //Some comment for the fortress
    public static final int DEFAULT_HEALTH = 1500;
    private int health;

    public Fortress(){
        this.health = DEFAULT_HEALTH;
    }

    public int getHealth(){
        return health;
    }

    public void decHealth(int damage){
        this.health -= damage;
    }

    public boolean isRuined(){
        return health <= 0;
    }
}
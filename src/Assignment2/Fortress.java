package Assignment2;

/**
 * Created by constantin on 18/06/16.
 */
public class Fortress {
    public static final int DEFAULT_HEALTH = 1500;
    private int health;

    public Fortress(){
        this.health = DEFAULT_HEALTH;
    }

    public int getHealth(){
        return health;
    }

    public void getDamage(int damage){
        this.health -= damage;
    }

    public boolean isRuined(){
        return health <= 0;
    }
}

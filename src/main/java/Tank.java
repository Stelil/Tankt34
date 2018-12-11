import org.json.JSONArray;
import org.json.JSONObject;

public class Tank {
    private float health;
    private float damage;
    private String strategy;
    private int x;
    private int y;

    Tank(float health, float damage, String strategy, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.strategy = strategy;
        this.x = x;
        this.y = y;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public int getMove(String moving){
        int move;
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject(this.strategy);
        move = Integer.valueOf(String.valueOf(obj.get(moving)));
        return move;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

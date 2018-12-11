import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public void main(String[] args) {

        Tank firstTank = new Tank(80,
                30,
                String.valueOf(constructorStrategy(1,2,3,4)),
                3,
                3);

        Tank secondTank = new Tank(90,
                25,
                String.valueOf(constructorStrategy(1,1,3,1)),
                7,
                7);

        for (int x = 0; 10 > x; x++) {
            for (int y = 1; 10 >= y; y++) {
                if (firstTank.getX() == x && firstTank.getY() == y) {
                    System.out.print("|1");
                } else if (x == 0) {
                    System.out.print(" -");
                } else if (y < 10) {
                    System.out.print("|_");
                } else {
                    System.out.print("|");

                }
            }
            System.out.println();


            secondTank.setStrategy();
            break;
        }
    }

    private JSONObject constructorStrategy(int left_move, int right_move, int up_move, int down_move) {
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("left_move", 2);
        obj.put("right_move", 1);
        obj.put("up_move", 1);
        obj.put("down_move", 3);
        ar.put(obj);
        return obj;
    }
}












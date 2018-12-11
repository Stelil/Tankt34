import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static Tank firstTank, secondTank;

    public static void main(String[] args) {

        firstTank = new Tank(80,
                30,
                String.valueOf(constructorStrategy(1, 2, 3, 4)),
                3,
                3);

        secondTank = new Tank(90,
                25,
                String.valueOf(constructorStrategy(1, 1, 3, 1)),
                7,
                7);

        int k = 0;
        while (k++ < 4) {
            picture(k);
        }

    }

    private static JSONObject constructorStrategy(int left_move, int right_move, int up_move, int down_move) {
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("left_move", 2);
        obj.put("right_move", 1);
        obj.put("up_move", 1);
        obj.put("down_move", 3);
        ar.put(obj);
        return obj;
    }

    private static void picture(int i) {
        for (int x = 10; x-- > 0; ) {
            for (int y = 10; y-- > 0;) {
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
        }

        switch (i) {
            case 1:
                int getX = firstTank.getX();
                int getMove = firstTank.getMove("left_move");
                getX -= getMove;
                firstTank.setX(getX);
                break;
            case 2:
                getX = firstTank.getX();
                getMove = firstTank.getMove("right_move");
                getX += getMove;
                firstTank.setX(getX);
                break;
            case 3:
                int getY = firstTank.getY();
                getMove = firstTank.getMove("up_move");
                getY += getMove;
                firstTank.setY(getY);
                break;
            case 4:
                getY = firstTank.getY();
                getMove = firstTank.getMove("down_move");
                getY -= getMove;
                firstTank.setY(getY);
                break;

        }
    }

}












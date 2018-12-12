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
        while (k++ < 5) {
            picture(k);
        }

    }

    private static JSONObject constructorStrategy(int left_move, int right_move, int up_move, int down_move) {
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("left_move", 1);
        obj.put("right_move", 2);
        obj.put("up_move", 1);
        obj.put("down_move", 3);
        ar.put(obj);
        return obj;
    }

    private static void picture(int i) {
        System.out.println("Проход " + i);
        for (int x = 1; x<11;x++ ) {
            for (int y = 1; y<11; y++) {
                if (firstTank.getX() == x && firstTank.getY() == y) {
                    System.out.print("|▲");
                } else if (x == 1) {
                    System.out.print(" -");
                } else if (y < 10) {
                    System.out.print("|_");
                } else {
                    System.out.print("|");

                }
            }
            System.out.println();
        }
        System.out.println();

        switch (i) {
            case 1:
                int getY = firstTank.getY();
                int getMove = firstTank.getMove("left_move");
                if (getY - getMove != 0){
                    
                } else {
                    firstTank.setY(getY);
                }

                break;
            case 2:
                getY = firstTank.getY();
                getMove = firstTank.getMove("right_move");
                getY += getMove;
                firstTank.setY(getY);
                break;
            case 3:
                int getX = firstTank.getX();
                getMove = firstTank.getMove("up_move");
                getX -= getMove;
                firstTank.setX(getX);
                break;
            case 4:
                getX = firstTank.getX();
                getMove = firstTank.getMove("down_move");
                getX += getMove;
                firstTank.setX(getX);
                break;
            default:

        }
    }

}












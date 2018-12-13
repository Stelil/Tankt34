import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static Tank firstTank, secondTank;
    static int nowLine = 0;
    static int lengthMapX = 10;
    static int lengthMapY = 10;

    public static void main(String[] args) {

        firstTank = new Tank("java",
                80,
                30,
                String.valueOf(constructorStrategy(90, 90, 1, 1)),
                3,
                3,
                constructorStrategy(90, 90, 3, 4).length(),
                0);

        secondTank = new Tank("c#",
                80,
                30,
                String.valueOf(constructorStrategy(90, 90, 1, 1)),
                3,
                8,
                constructorStrategy(90, 90, 3, 4).length(),
                270);

        game();
    }

    private static JSONObject constructorStrategy(int left_turn, int right_turn, int up_move, int down_move) {
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("left_turn", left_turn);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("up_move", up_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("down_move", down_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("right_turn", right_turn);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("strategy", ar);

        return obj;
    }

    private static void game() {
        int k = 0;
        boolean game = true;
        while (game) {
            k++;
            System.out.println("STEP " + k);
            System.out.println("1 health " + firstTank.getHealth());
            System.out.println("2 health " + secondTank.getHealth());

            new Action().action(firstTank, secondTank);
            new Action().action(secondTank, firstTank);

            int[][] sh;
            sh = new Action().shoot(firstTank, secondTank, lengthMapX + 1, lengthMapY + 1);

            for (int x = 1; x < lengthMapX + 1; x++) {
                for (int y = 1; y < lengthMapY + 1; y++) {
                    if (firstTank.getX() == x && firstTank.getY() == y) {
                        switch (firstTank.getDeg()) {
                            case 0:
                                System.out.print("|▲");
                                break;
                            case 90:
                                System.out.print("|▶");
                                break;
                            case 180:
                                System.out.print("|▼");
                                break;
                            case 270:
                                System.out.print("|◀");
                                break;
                        }
                    } else if (secondTank.getX() == x && secondTank.getY() == y) {
                        switch (secondTank.getDeg()) {
                            case 0:
                                System.out.print("|▲");
                                break;
                            case 90:
                                System.out.print("|▶");
                                break;
                            case 180:
                                System.out.print("|▼");
                                break;
                            case 270:
                                System.out.print("|◀");
                                break;
                        }
                    } else if (sh[x][y] != 0) {
                        System.out.print("|" + sh[x][y]);
                    } else if (y < lengthMapX) {
                        System.out.print("|_");
                    } else {
                        System.out.print("|");

                    }
                }
                System.out.println();
            }
            System.out.println();

            if (firstTank.getHealth() <= 0 || secondTank.getHealth() <= 0) {
                game = false;
            }
        }
    }

}
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Main {

    static Tank firstTank, secondTank;
    static int nowLine = 0;

    public static void main(String[] args) {

        firstTank = new Tank(80,
                30,
                String.valueOf(constructorStrategy(90, 90, 3, 4)),
                3,
                3,
                constructorStrategy(90, 90, 3, 4).size(),
                0);

        int k = 0;
        while (k++ < 3) {
            picture();
        }
    }

    private static HashMap constructorStrategy(int left_turn, int right_turn, int up_move, int down_move) {
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
        obj.put("up_move", up_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("left_turn", 180);
        ar.put(obj);

        HashMap map = new HashMap();
        map.put("strategy", ar);

        return map;
    }

    private static void picture() {
        nowLine++;

        if (nowLine > firstTank.getLengthStrategy()) {
            nowLine = 1;
        }

        for (int x = 1; x < 11; x++) {
            for (int y = 1; y < 11; y++) {
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

        org.json.JSONObject obj = new org.json.JSONObject(firstTank.getStrategy());
        JSONArray array = obj.getJSONArray("strategy");
        org.json.JSONObject getObj = array.getJSONObject(nowLine);

        String str = getObj.toString();

        str.toLowerCase().contains("left_turn".toLowerCase()){
            int param = getObj.getInt("left_turn");
        }

        boolean bool = true;
        while (bool) {
            if (nowLine == 5)
                nowLine = 1;
            switch (nowLine) {
                case 1:
                    int getDeg = firstTank.getDeg();
                    //System.out.println(getDeg);
                    int turn = firstTank.getMove("left_turn");
                    //System.out.println(turn);
                    if (getDeg - turn <= 0) {
                        getDeg = getDeg - turn * -1;
                        System.out.println(getDeg);
                        firstTank.setDeg(getDeg);
                    } else {
                        getDeg -= turn;
                        firstTank.setDeg(getDeg);
                    }
                    bool = false;
                    break;
                case 2:
                    getDeg = firstTank.getDeg();
                    turn = firstTank.getMove("right_turn");
                    if (getDeg + turn >= 360) {
                        getDeg = getDeg % turn;
                        firstTank.setDeg(getDeg);
                    } else {
                        getDeg += turn;
                        firstTank.setDeg(getDeg);
                    }
                    bool = false;
                    break;
                case 3:
                    int getX = firstTank.getX();
                    int getMove = firstTank.getMove("up_move");
                    if (getX - getMove > 0) {
                        getX -= getMove;
                        firstTank.setX(getX);
                        bool = false;
                    } else {
                        nowLine++;
                    }
                    break;
                case 4:
                    getX = firstTank.getX();
                    getMove = firstTank.getMove("down_move");
                    if (getX + getMove < 11) {
                        getX += getMove;
                        firstTank.setX(getX);
                        bool = false;
                    } else {
                        nowLine++;
                    }
                    break;
                default:
                    nowLine++;
                    break;
            }
            System.out.println("get " + firstTank.getDeg());
        }

    }

}












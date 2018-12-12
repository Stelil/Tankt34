import org.json.JSONArray;

import java.util.Random;

public class Action {

    int lengthMapX = 10;
    int lengthMapY = 10;

    public void action(Tank tank) {
        Random random = new Random();
        int line = random.nextInt(tank.getLengthStrategy());

        System.out.println(tank.getName());

        org.json.JSONObject obj = new org.json.JSONObject(tank.getStrategy());
        JSONArray array = obj.getJSONArray("strategy");
        org.json.JSONObject getObj = array.getJSONObject(line);
        String str = getObj.toString();

        //поворот против часовой стрелки
        if (str.toLowerCase().contains("left_turn".toLowerCase())) {
            System.out.println("left");
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
            int turn = getObj.getInt("left_turn");
            int getDeg = tank.getDeg();
            if (getDeg - turn < 0) {
                getDeg = 360 - turn;
                tank.setDeg(getDeg);
            } else {
                getDeg -= turn;
                tank.setDeg(getDeg);
            }
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
        }

        //поворот по часовой стрелки
        if (str.toLowerCase().contains("right_turn".toLowerCase())) {
            System.out.println("right");
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
            int turn = getObj.getInt("right_turn");
            int getDeg = tank.getDeg();
            if (getDeg + turn >= 360) {
                getDeg = getDeg % turn;
                tank.setDeg(getDeg);
            } else {
                getDeg += turn;
                tank.setDeg(getDeg);
            }
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
        }

        // движение вперед
        if (str.toLowerCase().contains("up_move".toLowerCase())) {
            int getMove = getObj.getInt("up_move");
            int getX = tank.getX();
            int getY = tank.getY();

            System.out.println("up");
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
            int[] res = moving(tank.getDeg(), getX, getY, getMove);
            getX = res[0];
            tank.setX(getX);
            getY = res[1];
            tank.setY(getY);
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());

        }

        //движение назад
        if (str.toLowerCase().contains("down_move".toLowerCase())) {
            int getMove = getObj.getInt("down_move");
            int getX = tank.getX();
            int getY = tank.getY();

            System.out.println("down");
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());
            int[] res = moving(tank.getDeg(), getX, getY, -getMove);
            getX = res[0];
            tank.setX(getX);
            getY = res[1];
            tank.setY(getY);
            System.out.println(tank.getX() + " " + tank.getY() + " " + tank.getDeg());

        }


    }

    private int[] moving(int deg, int x, int y, int move) {
        System.out.println(deg + " " + x + " " + y + " " + move);
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;

        switch (deg) {
            case 0:
                if (x - move > 0 && x - move < lengthMapX) {
                    res[0] = x - move;
                    res[1] = y;
                } else {
                    break;
                }
                if (x + move > 0 && x + move < lengthMapX) {
                    res[0] = x - move;
                    res[1] = y;
                }
                break;
            case 90:
                if (y - move > 0 && y - move < lengthMapY) {
                    res[0] = x;
                    res[1] = y - move;
                } else {
                    break;
                }
                if (y + move > 0 && y + move < lengthMapY) {
                    res[0] = x;
                    res[1] = y - move;
                }
                break;
            case 180:
                if (x - move > 0 && x - move < lengthMapX) {
                    res[0] = x - move;
                    res[1] = y;
                } else {
                    break;
                }
                if (x + move > 0 && x + move < lengthMapX) {
                    res[0] = x - move;
                    res[1] = y;
                }
                break;
            case 270:
                if (y - move > 0 && y - move < lengthMapY) {
                    res[0] = x;
                    res[1] = y - move;
                } else {
                    break;
                }
                if (y + move > 0 && y + move < lengthMapY) {
                    res[0] = x;
                    res[1] = y - move;
                }
                break;
        }

        return res;
    }

}

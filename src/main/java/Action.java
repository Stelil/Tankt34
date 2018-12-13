import org.json.JSONArray;

import java.util.Random;

public class Action {

    int lengthMapX = 10;
    int lengthMapY = 10;

    public void action(Tank tank, Tank enemy) {
        Random random = new Random();
        int line = random.nextInt(tank.getLengthStrategy());

        System.out.println(tank.getName());

        org.json.JSONObject obj = new org.json.JSONObject(tank.getStrategy());
        JSONArray array = obj.getJSONArray("strategy");
        org.json.JSONObject getObj = array.getJSONObject(line);
        String str = getObj.toString();
        //поворот против часовой стрелки
        if (str.toLowerCase().contains("left_turn".toLowerCase())) {
            int turn = getObj.getInt("left_turn");
            int getDeg = tank.getDeg();
            if (getDeg - turn < 0) {
                getDeg = 360 - turn;
                tank.setDeg(getDeg);
            } else {
                getDeg -= turn;
                tank.setDeg(getDeg);
            }
        }
        //поворот по часовой стрелки
        if (str.toLowerCase().contains("right_turn".toLowerCase())) {
            int turn = getObj.getInt("right_turn");
            int getDeg = tank.getDeg();
            if (getDeg + turn >= 360) {
                getDeg = getDeg % turn;
                tank.setDeg(getDeg);
            } else {
                getDeg += turn;
                tank.setDeg(getDeg);
            }
        }
        // движение вперед
        if (str.toLowerCase().contains("up_move".toLowerCase())) {
            int getMove = getObj.getInt("up_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(tank.getDeg(), getX, getY, getMove);
            getX = res[0];
            tank.setX(getX);
            getY = res[1];
            tank.setY(getY);
        }
        //движение назад
        if (str.toLowerCase().contains("down_move".toLowerCase())) {
            int getMove = getObj.getInt("down_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(tank.getDeg(), getX, getY, -getMove);
            getX = res[0];
            tank.setX(getX);
            getY = res[1];
            tank.setY(getY);
        }

    }

    private int[] moving(int deg, int x, int y, int move) {
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

    public int[][] shoot(Tank firstTank, Tank secondTank, int lengthX, int lengthY) {
        int[][] see = new int[lengthX][lengthY];
        for (int x = 1; x < lengthX; x++) {
            for (int y = 1; y < lengthY; y++) {
                see[x][y] = 0;
            }
        }

        if (firstTank.getX() == secondTank.getX() || firstTank.getY() == secondTank.getY()) {
            if (firstTank.getX() < secondTank.getX() && firstTank.getDeg() == 180) {
                for (int i = firstTank.getX(); i <= secondTank.getX(); i++) {
                    see[i][firstTank.getY()] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getX() > secondTank.getX() && firstTank.getDeg() == 0) {
                for (int i = firstTank.getX(); i >= secondTank.getX(); i--) {
                    see[i][firstTank.getY()] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getY() < secondTank.getY() && firstTank.getDeg() == 90) {
                for (int i = firstTank.getY(); i <= secondTank.getY(); i++) {
                    see[firstTank.getX()][i] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getY() > secondTank.getY() && firstTank.getDeg() == 270) {
                for (int i = firstTank.getY(); i >= secondTank.getY(); i--) {
                    see[firstTank.getX()][i] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            }
        }

        if (secondTank.getX() == firstTank.getX() || secondTank.getY() == firstTank.getY()) {
            if (secondTank.getX() < firstTank.getX() && secondTank.getDeg() == 180) {
                for (int i = secondTank.getX(); i <= firstTank.getX(); i++) {
                    see[i][secondTank.getY()] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getX() > firstTank.getX() && secondTank.getDeg() == 0) {
                for (int i = secondTank.getX(); i >= firstTank.getX(); i--) {
                    see[i][secondTank.getY()] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getY() < firstTank.getY() && secondTank.getDeg() == 90) {
                for (int i = secondTank.getY(); i <= firstTank.getY(); i++) {
                    see[secondTank.getX()][i] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getY() > firstTank.getY() && secondTank.getDeg() == 270) {
                for (int i = secondTank.getY(); i >= firstTank.getY(); i--) {
                    see[secondTank.getX()][i] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            }
        }

        return see;
    }
}

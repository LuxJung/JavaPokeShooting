package game;

public class GameSet extends Thread{
    int x;
    int y;
    static int enemy_speed = 7;
    GameSet(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

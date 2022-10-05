package game.set;

public class GameSet extends Thread{
    public int x;
    public int y;
    protected static int enemy_speed = 7;
    protected GameSet(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

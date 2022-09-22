import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Item  extends GameSet{
    int item_speed; // 객체 생성시 속도 값을 받을 수 있다.
    int x;
    int y;
    Missile ms;
    GameFrame gf;

    Item(int x, int y, int speed) { // 적좌표를 받아 객체화 시키기 위한 메소드
        super(x, y);
        this.x = x;
        this.y = y;
        this.item_speed = speed;

    }

    public void moveitem() { // x좌표 enemy_speed 만큼 이동 시키는 명령 메소드
        x -= item_speed;
    }

    public void firespeed() { // x좌표 enemy_speed 만큼 이동 시키는 명령 메소드
        if (GameFrame.fire_Speed == 3) {
            GameFrame.fire_Speed = 3;
        } else {
            GameFrame.fire_Speed -= 1;
        }
    }

}

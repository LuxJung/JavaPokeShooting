package game.set.element;

import game.GameScreen;
import game.set.GameSet;

import javax.swing.*;
import java.awt.*;

public class Item  extends GameSet {
    public int item_speed; // 객체 생성시 속도 값을 받을 수 있다.
    public int x;
    public int y;
    public Image[] item_img;// 아이템 이미지를 받아들일 이미지 변수
    public Image[] item2_img;
    public Image[] item3_img;
    public Image[] iteminfo_img; // 아이템 이미지를 받아들일 이미지 변수
    public Image[] iteminfo2_img; // 아이템 이미지를 받아들일 이미지 변수
    public Image[] iteminfo3_img; // 아이템 이미지를 받아들일 이미지 변수
    Missile ms;
    GameScreen gf;

    public Item(int x, int y, int speed) { // 적좌표를 받아 객체화 시키기 위한 메소드
        super(x, y);
        this.x = x;
        this.y = y;
        this.item_speed = speed;

        item_img = new Image[6];// 아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < item_img.length; ++i) {
            item_img[i] = new ImageIcon(
                    "src/img/item_" + i + ".png").getImage();
        }
        item2_img = new Image[6];//아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < item2_img.length; ++i) {
            item2_img[i] = new ImageIcon(
                    "src/img/item_" + i + ".png").getImage();
        }
        item3_img = new Image[6];//아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < item3_img.length; ++i) {
            item3_img[i] = new ImageIcon(
                    "src/img/3item_" + i + ".png").getImage();
        }
        iteminfo_img = new Image[6];// 아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < iteminfo_img.length; ++i) {
            iteminfo_img[i] = new ImageIcon(
                    "src/img/iteminfo_" + i + ".png").getImage();
        }
        iteminfo2_img = new Image[6];// 아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < iteminfo2_img.length; ++i) {
            iteminfo2_img[i] = new ImageIcon(
                    "src/img/2iteminfo_" + i + ".png").getImage();
        }
        iteminfo3_img = new Image[6];// 아이템 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < iteminfo3_img.length; ++i) {
            iteminfo3_img[i] = new ImageIcon(
                    "src/img/3iteminfo_" + i + ".png").getImage();
        }
    }

    public void moveitem() { // x좌표 enemy_speed 만큼 이동 시키는 명령 메소드
        x -= item_speed;
    }

    public void firespeed() { // x좌표 enemy_speed 만큼 이동 시키는 명령 메소드
        if (GameScreen.fire_Speed == 3) {
            GameScreen.fire_Speed = 3;
        } else {
            GameScreen.fire_Speed -= 1;
        }
    }


}

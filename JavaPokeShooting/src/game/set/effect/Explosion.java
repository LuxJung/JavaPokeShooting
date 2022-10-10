package game.set.effect;

import game.set.GameSet;

import javax.swing.*;
import java.awt.*;

//폭발 처리효과를 위한 클래스
public class Explosion extends GameSet {
    // 여러개의 폭발 이미지를 그리기위해 클래스를 추가하여 객체관리

    public int x; // 이미지를 그릴 x 좌표
    public int y; // 이미지를 그릴 y 좌표
    public int ex_cnt; // 이미지를 순차적으로 그리기 위한 카운터
    public int damage; // 이미지 종류를 구분하기 위한 변수값
    public Image[] Explo_img; // 폭발이펙트용 이미지배열
    public Explosion(int x, int y, int damage) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.damage = damage;
        ex_cnt = 0;

        Explo_img = new Image[4];// 폭발 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < Explo_img.length; ++i) {
            Explo_img[i] = new ImageIcon(
                    "src/img/explo_" + i + ".png").getImage();
        }
    }

    public void effect() {
        ex_cnt++; // 해당 메소드 호출 시 카운터를 +1 시킨다.
    }
}
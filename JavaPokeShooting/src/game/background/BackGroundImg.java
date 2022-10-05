package game.background;

import javax.swing.*;
import java.awt.*;

public class BackGroundImg {

    public Image backGround1; // 스테이지 1 이미지
    public Image backGround2;// 스테이지2 이미지
    public Image gameClear;// 게임클리어 이미지
    public Image gameOver; // 게임오버 이미지
    public Image bossText;// 보스등장 텍스트 이미지

    public BackGroundImg(){
        this.backGround1 = new ImageIcon("src/img/백그라운드.png").getImage();
        this.backGround2 = new ImageIcon("src/img/백그라운드2.png").getImage();// 스테이지2 이미지
        this.gameClear = new ImageIcon("src/img/gameclear.png").getImage();// 게임클리어 이미지
        this.gameOver = new ImageIcon("src/img/gameover.png").getImage(); // 게임오버 이미지
        this.bossText = new ImageIcon("src/img/bosstext.png").getImage(); // 보스등장 텍스트 이미지

    }
}
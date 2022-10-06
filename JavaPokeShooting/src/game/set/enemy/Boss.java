package game.set.enemy;

import game.set.effect.Explosion;
import game.GameScreen;
import game.set.element.Missile;
import game.set.GameSet;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Boss extends GameSet implements Runnable {
    // int x;
    // int y;
    // int speed; // 객체 생성시 속도 값을 받을 수 있다.
    int count;
    // int boss_Hp;
    int speed;
    int enemy_Hp;
    Boss bs;
    Enemy en;
    GameScreen gf;
    public Image[] Boss_img;
    public int getEnemy_Hp() {
        return enemy_Hp;
    }

    public void setEnemy_Hp(int enemy_Hp) {
        this.enemy_Hp = enemy_Hp;
    }
    /*
     * public int getBoss_Hp() { return boss_Hp; }
     *
     * public void setBoss_Hp(int boss_Hp) { this.boss_Hp = boss_Hp; }
     */

    public Boss(int x, int y, int speed, int enemy_Hp) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.speed = GameSet.enemy_speed;
        this.enemy_Hp = enemy_Hp;
        if(GameScreen.enemy_kill==20){
        Boss_img = new Image[4];// 폭발 애니메이션 표현을 위해 이미지를 배열로 받음
            for (int i = 0; i < Boss_img.length; ++i) {
                Boss_img[i] = new ImageIcon(
                        "src/img/프리더_" + i + ".png")
                        .getImage();
            }
        }else if(GameScreen.enemy_kill==40){
            Boss_img = new Image[4];// 폭발 애니메이션 표현을 위해 이미지를 배열로 받음
            for (int i = 0; i < Boss_img.length; ++i) {
                Boss_img[i] = new ImageIcon(
                        "src/img/리자몽_" + i + ".png")
                        .getImage();
            }
        }
    }

    public void move() { // x좌표 -적의 속도 만큼 이동 시키는 명령 메소드
        x -= speed;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public void appear_Boss() {
        if(GameScreen.enemy_kill == 20 ) {
            GameScreen.Enemy_List.clear();
            GameScreen.EMissile_List.clear();
            boss_timer.schedule(boss_task, 3000);
        }
        if(GameScreen.enemy_kill == 40 ) {
            GameScreen.Enemy_List.clear();
            GameScreen.EMissile_List.clear();
            boss_timer.schedule(boss_task, 3000);
        }
    }

    Timer boss_timer = new Timer();
    TimerTask boss_task = new TimerTask() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            process_Boss();
        }

    };

    public void process_Boss() {
        if (GameScreen.CNT % 30 == 0) {
            if (GameScreen.enemy_kill == 20 || GameScreen.enemy_kill == 40) {

                GameScreen.bs = new Boss(1000, 250, enemy_speed, getEnemy_Hp());
                if (GameScreen.Boss_List.size() == 0) {

                    GameScreen.Boss_List.add(GameScreen.bs);
                }else {
                    enemy_Hp=0;
                }
            } else {
                GameScreen.Boss_List.clear();
            }
            // 보스 행동
            for (int i = 0; i < GameScreen.Boss_List.size(); ++i) {
                GameScreen.bs = (Boss) (GameScreen.Boss_List.get(i));// 배열에 적이 생성되어있을 때 해당되는 적을 판별

                if (GameScreen.CNT % 10 == 0) {// 확인된적의위치에미사일생성
                    GameScreen.bms = new Missile(GameScreen.bs.x, GameScreen.bs.y+75, 180, GameScreen.Emissile_Speed, 2);
                    // 왼쪽부터미사일x좌표, y좌표, 미사일진행방향,
                    // 미사일속도, 미사일종류
                    // 미사일종류0 : 플레이어가발사하는미사일,
                    // 1 : 적이발사하는미사일
                    GameScreen.BMissile_List.add(GameScreen.bms);
                    // 생성된미사일을객체로배열에추가
                }
                if (GameScreen.Crash(GameScreen.x, GameScreen.y, GameScreen.bs.x, GameScreen.bs.y, GameScreen.Player_img[0],
                        Boss_img[0])) {// 플레이어와 적의 충돌을 판정하여
                    // boolean값을 리턴 받아 true면 아래를 실행합니다.
                    GameScreen.player_Hp--; // 플레이어 체력을 1깍습니다.
                    // game_Score += 10;
                    // 제거된 적으로 게임스코어를 10 증가시킵니다.

                    GameScreen.ex = new Explosion(GameScreen.bs.x + GameScreen.bs.Boss_img[0].getWidth(null) / 2,
                            GameScreen.bs.y + Boss_img[0].getHeight(null) / 2, 0);
                    // 적이 위치해있는 곳의 중심 좌표 x,y 값과
                    // 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                    // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                    GameScreen.Explosion_List.add(GameScreen.ex);// 제거된 적위치에 폭발 이펙트를 추가합니다.
                }
            }
        }
    }

}

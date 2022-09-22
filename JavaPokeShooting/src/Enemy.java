
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Enemy extends GameSet implements Runnable {
    //int x;
    //int y;
    int speed; // 객체 생성시 속도 값을 받을 수 있다.
    static Runnable Etrd; // 스레드
    GameFrame gf;
    int enemy_Hp;
    Enemy en;
    // 다수의 적을 등장 시켜야 하므로 배열을 이용.
    // 에너미 클래스 접근

    Enemy(int x, int y, int speed,int enemy_Hp) { // 적좌표를 받아 객체화 시키기 위한 메소드
        super(x, y);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.enemy_Hp = 3;
        if(GameFrame.enemy_kill>21) {
            this.enemy_Hp = 6;
        }
    }

    public void move() { // x좌표 -적의 속도 만큼 이동 시키는 명령 메소드
        x -= speed;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Process_Enemy();
                Thread.sleep(17);// 20 milli sec 로 스레드 돌리기
                GameFrame.cnt++;
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

    }

    public void Process_Enemy() {
        if (GameFrame.cnt % 200 == 0) { // 루프 카운트 200회 마다

            if (GameFrame.enemy_kill != 20 && GameFrame.enemy_kill != 40) {
                if (GameFrame.enemy_kill > 40) {
                    GameFrame.Enemy_List.clear();
                } else {
                    int num = (int) (Math.random() * 3) + 1;
                    if (num == 1) {// 적 움직임 속도를 추가로 받아 적을 생성한다. 3, 4 ,5마리
                        int zen = (int) ((Math.random() * 140) + 80);
                        int zen1 = (int) ((Math.random() * 60) + 260);
                        int zen2 = (int) ((Math.random() * 60) + 380);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen1,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen2,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                    } else if (num == 2) {
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 140,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 260,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 380,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 500,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                    } else {
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 80,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 200,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 320,
                                GameFrame.enemy_speed,getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 440,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                        GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 560,
                                GameFrame.enemy_speed, getEnemy_Hp());
                        GameFrame.Enemy_List.add(GameFrame.en);
                    }
                }
            }
            if (GameFrame.enemy_kill == 20 && GameFrame.enemy_kill == 40) {
                GameFrame.Enemy_List.clear();
            }

        }

        // 일반 적 행동
        for (int i = 0; i < GameFrame.Enemy_List.size(); ++i) {
            GameFrame.en = (Enemy) (GameFrame.Enemy_List.get(i));// 배열에 적이 생성되어있을 때 해당되는 적을 판별
            GameFrame.en.move(); // 해당 적을 이동시킨다.
            if (GameFrame.en.x < -200) { // 적의 좌표가 화면 밖으로 넘어가면
                GameFrame.Enemy_List.remove(i);
            }

            if (GameFrame.cnt % 150 == 0) {// 확인된적의위치에미사일생성
                GameFrame.ems = new Missile(GameFrame.en.x, GameFrame.en.y + 25, 180, GameFrame.Emissile_Speed, 1);
                // 왼쪽부터미사일x좌표, y좌표, 미사일진행방향,
                // 미사일속도, 미사일종류
                // 미사일종류0 : 플레이어가발사하는미사일,
                // 1 : 적이발사하는미사일

                GameFrame.EMissile_List.add(GameFrame.ems);
                // 생성된미사일을객체로배열에추가
            }
            if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.en.x, GameFrame.en.y, GameFrame.Player_img[0],
                    GameFrame.Enemy_img[0])) {// 플레이어와 적의 충돌을 판정하여
                // boolean값을 리턴 받아 true면 아래를 실행합니다.
                GameFrame.player_Hp --;//플레이어 체력을 1깍습니다.
                GameFrame.Enemy_List.remove(i); // 적을 제거합니다.
                // game_Score += 10;
                // 제거된 적으로 게임스코어를 10 증가시킵니다.

                GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
                        GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 0);
                // 적이 위치해있는 곳의 중심 좌표 x,y 값과
                // 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                GameFrame.Explosion_List.add(GameFrame.ex);// 제거된 적위치에 폭발 이펙트를 추가합니다.
            }
        }

    }

    public  int getEnemy_Hp() {
        return enemy_Hp;
    }

    public void setEnemy_Hp(int enemy_Hp) {
        this.enemy_Hp = enemy_Hp;
    }

}

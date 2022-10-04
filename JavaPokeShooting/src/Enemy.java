public class Enemy extends GameSet implements Runnable {
    //int x;
    //int y;
    //핟원에서 열었을때 여기가 바꼈을까?
    int speed; // 객체 생성시 속도 값을 받을 수 있다.
    static Runnable Etrd; // 스레드
    GameScreen gf;
    int enemy_Hp;
    Enemy en;
    // 다수의 적을 등장 시켜야 하므로 배열을 이용.
    // 에너미 클래스 접근

    Enemy(int x, int y, int speed,int enemy_Hp) { // 적좌표를 받아 객체화 시키기 위한 메소드
        super(x, y);
        this.x = x;
        this.y = y;
        this.speed = GameSet.enemy_speed;
        this.enemy_Hp = 3;
        if(GameScreen.enemy_kill>21) {
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
                GameScreen.CNT++;
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

    }

    public void Process_Enemy() {
        if (GameScreen.CNT % 200 == 0) { // 루프 카운트 200회 마다

            if (GameScreen.enemy_kill != 20 && GameScreen.enemy_kill != 40) {
                if (GameScreen.enemy_kill > 40) {
                    GameScreen.Enemy_List.clear();
                } else {
                    int num = (int) (Math.random() * 3) + 1;
                    if (num == 1) {// 적 움직임 속도를 추가로 받아 적을 생성한다. 3, 4 ,5마리
                        int zen = (int) ((Math.random() * 140) + 80);
                        int zen1 = (int) ((Math.random() * 60) + 260);
                        int zen2 = (int) ((Math.random() * 60) + 380);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen1,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen2,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                    } else if (num == 2) {
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 140,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 260,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 380,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 500,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                    } else {
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 80,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 200,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 320,
                                enemy_speed,getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 440,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                        GameScreen.en = new Enemy(GameScreen.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 560,
                                enemy_speed, getEnemy_Hp());
                        GameScreen.Enemy_List.add(GameScreen.en);
                    }
                }
            }
            if (GameScreen.enemy_kill == 20 && GameScreen.enemy_kill == 40) {
                GameScreen.Enemy_List.clear();
            }

        }

        // 일반 적 행동
        for (int i = 0; i < GameScreen.Enemy_List.size(); ++i) {
            GameScreen.en = (Enemy) (GameScreen.Enemy_List.get(i));// 배열에 적이 생성되어있을 때 해당되는 적을 판별
            GameScreen.en.move(); // 해당 적을 이동시킨다.
            if (GameScreen.en.x < -200) { // 적의 좌표가 화면 밖으로 넘어가면
                GameScreen.Enemy_List.remove(i);
            }

            if (GameScreen.CNT % 150 == 0) {// 확인된적의위치에미사일생성
                GameScreen.ems = new Missile(GameScreen.en.x, GameScreen.en.y + 25, 180, GameScreen.Emissile_Speed, 1);
                // 왼쪽부터미사일x좌표, y좌표, 미사일진행방향,
                // 미사일속도, 미사일종류
                // 미사일종류0 : 플레이어가발사하는미사일,
                // 1 : 적이발사하는미사일

                GameScreen.EMissile_List.add(GameScreen.ems);
                // 생성된미사일을객체로배열에추가
            }
            if (GameScreen.Crash(GameScreen.x, GameScreen.y, GameScreen.en.x, GameScreen.en.y, GameScreen.Player_img[0],
                    GameScreen.Enemy_img[0])) {// 플레이어와 적의 충돌을 판정하여
                // boolean값을 리턴 받아 true면 아래를 실행합니다.
                GameScreen.player_Hp --;//플레이어 체력을 1깍습니다.
                GameScreen.Enemy_List.remove(i); // 적을 제거합니다.
                // game_Score += 10;
                // 제거된 적으로 게임스코어를 10 증가시킵니다.

                GameScreen.ex = new Explosion(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null) / 2,
                        GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2, 0);
                // 적이 위치해있는 곳의 중심 좌표 x,y 값과
                // 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                GameScreen.Explosion_List.add(GameScreen.ex);// 제거된 적위치에 폭발 이펙트를 추가합니다.
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

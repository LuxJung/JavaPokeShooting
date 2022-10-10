package game;

import game.background.BackGroundImg;
import game.set.effect.Explosion;
import game.set.element.Item;
import game.set.element.Missile;
import game.set.enemy.Boss;
import game.set.enemy.Enemy;
import key.event.KeyEvent;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

//프레임을 만들기 위한 클래스 입니다.
//Game.GameScreen 에서는 그리는 역할만 할수 있도록 변경해주기
@SuppressWarnings("serial")
// 						              프레임 생성                         키보드 이벤트 처리    스레드 위함
public class GameScreen extends JFrame implements KeyListener, Runnable {
    public static final int SCREEN_WIDTH = 1280; // 프레임 넓이
    public static final int SCREEN_HEIGHT = 720; // 프레임 높
    Image buffImage; // 더블 버퍼링용(화면이 반짝거리지 않도록)
    Graphics buffg; // 더블 버퍼링용(화면이 반짝거리지 않도록)
    Toolkit tk = Toolkit.getDefaultToolkit();
    public static Image[] Player_img; // 플레이어 이미지 변수
    static Image[] Player_imgatk;

    static Image[] Explo_img; // 폭발이펙트용 이미지배열
    public static Image[] Missile_img; // 미사일 이미지 변수
    public static Image[] EMissile_img; // 적 미사일 이미지 변수
    public static int x, y; // 좌표 변수
    // 키보드 입력
    KeyEvent key = new KeyEvent();

    public static int CNT; // 각종 타이밍 조절을 무한 루프를 카운터


    //int boss_Hp; // 유저의 움직이는 속도

    public static Missile ms; // 미사일 클래스 접근 키
    public static Missile ems;
    public static Missile bms;
    public static Enemy en; // 적 접근 키
    public static Boss bs; // 보스 접근 키
    public static Explosion ex; // 폭발 이펙트용 클래스 접근 키
    public static Item itm;
    public static Item itm2;
    public static Item itm3;

    public static int missile_status = 0;
    public static int missile_Speed; // 미사일 속도
    public static int Emissile_Speed; // 적미사일 속

    public static int fire_Speed; // 미사일 연사 속도 조절

    static int player_Speed; // 유저의 움직이는 속도
    public static int player_Hp; // 유저의 움직이는 속도
    public static int player_Status = 0;// 유저 캐릭터 상태 체크 변수 0 : 평상시, 1: 미사일발사, 2: 충돌

    public static int item_speed; // 아이템 속도 조절

    public static int enemy_kill;
    int enemy_Hp; // 유저의 움직이는 속도

    int[] cx = { 0, 0, 0 }; // 배경 스크롤 속도 제어용 변수
    int bx = 0; // 전체 배경 스크롤 용 변수



    static Thread trd; // 스레드
    static Thread trd2; // 스레드


    public static ArrayList<Missile> BMissile_List = new ArrayList<>();
    public static ArrayList<Missile> EMissile_List = new ArrayList<>();
    public static ArrayList<Missile> Missile_List = new ArrayList<>();

    public static ArrayList<Explosion> Explosion_List = new ArrayList<>();

    public static ArrayList<Boss> Boss_List = new ArrayList<>();
    public static ArrayList<Enemy> Enemy_List = new ArrayList<>();

    public static ArrayList<Item> Item_List = new ArrayList<>();
    public static ArrayList<Item> Item2_List = new ArrayList<>();
    public static ArrayList<Item> Item3_List = new ArrayList<>();

    public BackGroundImg bg ;

    GameScreen() {// 프레임 생성
        init();
        start();
        setDefaultCloseOperation(GameScreen.EXIT_ON_CLOSE);

        setTitle("슈우잉겜");// 프레임 이름
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);// 프레임 크기설정

        // 프레임이 윈도우에 표시될때 위치를 세팅하기 위해 현재 모니터의 해상도 값을 받아옴.
        Dimension screen = tk.getScreenSize();

        // 프레임을 모니터 화면 정중앙에 배치시키기 위해 좌표 값을 계산합니다.
        int f_xpos = (int) (screen.getWidth() / 2 - SCREEN_WIDTH / 2);
        int f_ypos = (int) (screen.getHeight() / 2 - SCREEN_HEIGHT / 2);

        setLocation(f_xpos, f_ypos);// 프레임을 화면에 배치
        setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
        setVisible(true); // 프레임을 눈에 보이게 띄웁니다.

        if (key.KEY_ENTER) {

        }

    }

    public void init() {
        bg = new BackGroundImg(); //백그라운드 및 텍스트 이미지
        x = 100;// 캐릭터의 최초 좌표
        y = 400;// 캐릭터의 최초 좌표

        Player_img = new Image[4];// 플레이어 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < Player_img.length; ++i) {
            Player_img[i] = new ImageIcon(
                    "src/img/피카츄resize_" + i + ".png").getImage();
        }
        Player_imgatk = new Image[4];// 플레이어 공격 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < Player_imgatk.length; ++i) {
            Player_imgatk[i] = new ImageIcon(
                    "src/img/new피카츄atk_" + i + ".png").getImage();
        }
        Missile_img = new Image[4];// 전기구체 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < Missile_img.length; ++i) {
            Missile_img[i] = new ImageIcon(
                    "src/img/Missile_" + i + ".png").getImage();
        }
        EMissile_img = new Image[4];// 적 미사일 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < EMissile_img.length; ++i) {
            EMissile_img[i] = new ImageIcon(
                    "src/img/Missl_" + i + ".png").getImage();
        }
        Explo_img = new Image[4];// 폭발 애니메이션 표현을 위해 이미지를 배열로 받음
        for (int i = 0; i < Explo_img.length; ++i) {
            Explo_img[i] = new ImageIcon(
                    "src/img/explo_" + i + ".png").getImage();
        }

        player_Hp = 10;
        player_Speed = 10; // 유저 캐릭터 움직이는 속도 설정
        missile_Speed = 11; // 미사일 움직임 속도 설정
        Emissile_Speed = 11; // 미사일 움직임 속도 설정
        fire_Speed = 20; // 미사일 연사 속도 설정

        item_speed = 8;
        enemy_kill = 0;

    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame창 종료시 프로그램 종료
        addKeyListener(this); // 키보드 이벤트

        trd = new Thread(this);
        trd.start();
    }

    @Override
    public void run() {// 스레드 무한루프
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Enemy en = new Enemy(0,0,0,0);
                Boss bs = new Boss(0,0,0,0);
                en.setEnemy_Hp(3);
                if (enemy_kill == 40) {
                    bs.setEnemy_Hp(200);
                }else{
                    bs.setEnemy_Hp(100);
                }
                key.KeyProcess();// 키보드 입력처리를 하여 x,y 갱신
                en.Process_Enemy(); // 적 행동 메소드 실행
                //bs.process_Boss();
                bs.appear_Boss();
                Missile.Process_Missile(key); // 미사일 처리 메소드 실행
                Process_Item();
                Process_Explosion();// 폭파처리 메소드 실행
                // Process_Player();
                repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
                Thread.sleep(17);// 20 milli sec 로 스레드 돌리기

                CNT++;
                /* 나중에사용
                if (key.KEY_ENTER) {
                    GameStatus gs = null;
                    gs.option();
                    trd.suspend();
                }*/
                if(player_Hp ==0||enemy_kill==41){
                    Thread.sleep(2000);
                    trd.interrupt();//스레드 종료
                }
            }
            dispose();//스레드 종료후 화면 닫고
            new GameIntro();//새 화면을 띄운다
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        try {
            buffImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);// 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
            buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
            screenDraw(g);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void screenDraw(Graphics g) throws InterruptedException {
        Draw_Background();
        Draw_Bosstext();
        Draw_ItemInfo();
        Draw_Score();
        Draw_Player();// 실제로 그려진 그림을 가져온다
        Draw_Enemy(); // 적을 그린다
        Draw_Missile(); // 그려진 미사일 가져와 실행
        Draw_Item();
        Draw_Explosion(); // 폭발이펙트그리기 메소드 실행
        Draw_GamePlayState();
        g.drawImage(buffImage, 0, 0, this);// 화면에 버퍼에 그린 그림을 가져와 그리기
    }

    public void Draw_Player() { // 실제로 그림들을 그릴 부분
        switch (player_Status) {
            case 0: // 평상시
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(Player_img[0], x, y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(Player_img[1], x, y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(Player_img[2], x, y, this);
                } else {
                    buffg.drawImage(Player_img[3], x, y, this);
                }
                break;

            case 1: // 전기발사
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(Player_imgatk[0], x, y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(Player_imgatk[1], x, y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(Player_imgatk[2], x, y, this);
                } else {
                    buffg.drawImage(Player_imgatk[3], x, y, this);
                } // 미사일을 쏘는듯한 이미지를 번갈아 그려준다.
                player_Status = 0;// 미사일 쏘기가 끝나면 플레이어 상태를 0으로 돌린다.
                break;
            case 2: // 충돌
                break;
        }
    }

    /*********************************************************************/
    /*------------------- M I S S I L E -------------------*/
    public void Draw_Missile() { // 미사일 그리는 메소드
        for (int i = 0; i < Missile_List.size(); ++i) {// 미사일 존재 유무를 확인한다.
            ms = (Missile) (Missile_List.get(i));// 미사일 위치값을 확인
            if (ms.who == 0)
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(Missile_img[0], ms.x, ms.y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(Missile_img[1], ms.x, ms.y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(Missile_img[2], ms.x, ms.y, this);
                } else {
                    buffg.drawImage(Missile_img[3], ms.x, ms.y, this);
                }
            // ms.move();// 그려진 미사일을 정해진 숫자만큼 이동시키기
            if (ms.x > SCREEN_WIDTH) { // 미사일이 화면 밖으로 나가면
                Missile_List.remove(i); // 미사일 지우기
            }

            // 현재 좌표에 미사일 그리기.
            // 이미지 크기를 감안한 미사일 발사 좌표는 수정됨.

        }
        for (int k = 0; k < BMissile_List.size(); ++k) {// 미사일 존재 유무를 확인한다.
            bms = (Missile) (BMissile_List.get(k));
            if (bms.who == 2)
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(EMissile_img[0], bms.x, bms.y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(EMissile_img[1], bms.x, bms.y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(EMissile_img[2], bms.x, bms.y, this);
                } else {
                    buffg.drawImage(EMissile_img[3], bms.x, bms.y, this);
                }
            // bms.move();// 그려진 미사일을 정해진 숫자만큼 이동시키기
            if (bms.x < -200) {
                BMissile_List.remove(k);// 발사체 삭제
            }
        }
        for (int j = 0; j < EMissile_List.size(); ++j) {// 미사일 존재 유무를 확인한다.
            ems = (Missile) (EMissile_List.get(j));
            if (ems.who == 1)
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(EMissile_img[0], ems.x, ems.y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(EMissile_img[1], ems.x, ems.y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(EMissile_img[2], ems.x, ems.y, this);
                } else {
                    buffg.drawImage(EMissile_img[3], ems.x, ems.y, this);
                }
            // ems.move();// 그려진 미사일을 정해진 숫자만큼 이동시키기
            if (ems.x < -200) {
                EMissile_List.remove(j);// 발사체 삭제
            }
        }

    }
    /*------------------- M I S S I L E -------------------*/
    /*********************************************************************/
    /*------------------- E N E M Y  -------------------*/
    public void Draw_Enemy() {
        //if(System.currentTimeMillis() == StartGame.end) {
        if (enemy_kill == 20 || enemy_kill == 40 ) {
            for (int i = 0; i < Boss_List.size(); ++i) {
                bs = (Boss) (Boss_List.get(i));
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(bs.Boss_img[0], bs.x, bs.y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(bs.Boss_img[1], bs.x, bs.y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(bs.Boss_img[2], bs.x, bs.y, this);
                } else {
                    buffg.drawImage(bs.Boss_img[3], bs.x, bs.y, this);
                }
            }
        }  else {// 적 이미지를 그리는 부분Logstone_img

            for (int i = 0; i < Enemy_List.size(); ++i) {
                en = (Enemy) (Enemy_List.get(i));
                // buffg.drawImage(Enemy_img, en.x, en.y, this);
                if ((CNT / 4 % 4) == 1) {
                    buffg.drawImage(en.Enemy_img[0], en.x, en.y, this);
                } else if ((CNT / 4 % 4) == 2) {
                    buffg.drawImage(en.Enemy_img[1], en.x, en.y, this);
                } else if ((CNT / 4 % 4) == 3) {
                    buffg.drawImage(en.Enemy_img[2], en.x, en.y, this);
                } else {
                    buffg.drawImage(en.Enemy_img[3], en.x, en.y, this);
                } // 배열에 생성된 각 적을 판별하여 이미지 그리기
            }
        }

    }

    /*------------------- E N E M Y  -------------------*/
    /*********************************************************************/
    /*------------------- I T E M -------------------*/
    public void Draw_Item() {// 아이템 이미지를 그리는 부분

        for (int i = 0; i < Item_List.size(); ++i) {
            itm = (Item) Item_List.get(i);
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm.item_img[0], itm.x, itm.y, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm.item_img[1], itm.x, itm.y, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm.item_img[2], itm.x, itm.y, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm.item_img[3], itm.x, itm.y, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm.item_img[4], itm.x, itm.y, this);
            } else {
                buffg.drawImage(itm.item_img[5], itm.x, itm.y, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }

        for (int i = 0; i < Item2_List.size(); ++i) {
            itm2 = (Item) Item2_List.get(i);
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm2.item2_img[0], itm2.x, itm2.y, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm2.item2_img[1], itm2.x, itm2.y, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm2.item2_img[2], itm2.x, itm2.y, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm2.item2_img[3], itm2.x, itm2.y, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm2.item2_img[4], itm2.x, itm2.y, this);
            } else {
                buffg.drawImage(itm2.item2_img[5], itm2.x, itm2.y, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }

        for (int i = 0; i < Item3_List.size(); ++i) {
            itm3 = (Item) Item3_List.get(i);
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm3.item3_img[0], itm3.x, itm3.y, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm3.item3_img[1], itm3.x, itm3.y, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm3.item3_img[2], itm3.x, itm3.y, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm3.item3_img[3], itm3.x, itm3.y, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm3.item3_img[4], itm3.x, itm3.y, this);
            } else {
                buffg.drawImage(itm3.item3_img[5], itm3.x, itm3.y, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }
    }

    public void Process_Item() {
        for (int i = 0; i < Item_List.size(); ++i) {
            itm = (Item) Item_List.get(i);// 배열에 아이템이 생성되어있을 때 해당되는 아이템을 판별
            itm.moveitem(); // 아이템을 이동시킨다.
            if (x < -200) { // 적의 좌표가 화면 밖으로 넘어가면
                Item_List.remove(i); // 해당 아이템을 배열에서 삭제
            }

            if (Crash(x, y, itm.x, itm.y, Player_img[0], itm.item_img[0])) {// 플레이어와 아이템의 충돌을 판정하여
                // boolean값을 리턴 받아 true면 아래를 실행합니다.
                // player_Hitpoint --; //플레이어 체력을 1깍습니다.
                Item_List.remove(i); // 아이템을 제거합니다.
                itm.firespeed();
                // game_Score += 10;
                // 제거된 적으로 게임스코어를 10 증가시킵니다.

            }

        }
        for (int i = 0; i < Item2_List.size(); ++i) {
            itm2 = (Item) Item2_List.get(i);// 배열에 아이템이 생성되어있을 때 해당되는 아이템을 판별
            itm2.moveitem(); // 아이템을 이동시킨다.
            if (x < -200) { // 적의 좌표가 화면 밖으로 넘어가면
                Item2_List.remove(i); // 해당 아이템을 배열에서 삭제
            }
            if (Crash(x, y, itm2.x, itm2.y, Player_img[0], itm2.item2_img[0])) {// 플레이어와 아이템의 충돌을 판정하여
                // boolean값을 리턴 받아 true면 아래를 실행합니다.
                // player_Hitpoint --; //플레이어 체력을 1깍습니다.
                Item2_List.remove(i); // 아이템을 제거합니다.
                if (missile_status == 0) {
                    missile_status = 1;
                } else if (missile_status == 1) {
                    missile_status = 1;
                }
            }
        }
        for (int i = 0; i < Item3_List.size(); ++i) {
            itm3 = (Item) Item3_List.get(i);// 배열에 아이템이 생성되어있을 때 해당되는 아이템을 판별
            itm3.moveitem(); // 아이템을 이동시킨다.
            if (x < -200) { // 적의 좌표가 화면 밖으로 넘어가면
                Item3_List.remove(i); // 해당 아이템을 배열에서 삭제
            }
            if (Crash(x, y, itm3.x, itm3.y, Player_img[0], itm3.item3_img[0])) {// 플레이어와 아이템의 충돌을 판정하여
                // boolean값을 리턴 받아 true면 아래를 실행합니다.
                // player_Hitpoint --; //플레이어 체력을 1깍습니다.
                Item3_List.remove(i); // 아이템을 제거합니다.

                if (missile_status == 0) {
                    missile_status = 1;
                } else if (missile_status == 1) {
                    missile_status = 2;
                } else if (missile_status == 2) {
                    missile_status = 2;
                }

            }
        }

    }

    /*------------------- I T E M -------------------*/
    /*********************************************************************/

    public void Draw_Bosstext() {

        if (enemy_kill == 20 || enemy_kill == 40) {
            if(Boss_List.size()==0) {
                buffg.drawImage(bg.bossText, 50, 50, (ImageObserver) this);
            }
        }
    }



    /*------------------- B A C K G R O U N D -------------------*/
    public void Draw_GamePlayState() throws InterruptedException {
        if(enemy_kill==41) {
            buffg.drawImage(bg.gameClear, 165, 210, (ImageObserver) this);
        }else if (player_Hp<1) {
            buffg.drawImage(bg.gameOver, 165, 210, (ImageObserver) this);

        }
    }

    public void Draw_Background() {
        if (enemy_kill < 21) {
            buffg.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            // 화면 지우기 명령은 이제 여기서 실행합니다.
            if (bx > -4660) {
                // 기본 값이 0인 bx가 -3500 보다 크면 실행
                buffg.drawImage(bg.backGround1, bx, 0, this);
                bx -= 4;
                // bx를 0에서 -1만큼 계속 줄이므로 배경이미지의 x좌표는
                // 계속 좌측으로 이동한다. 그러므로 전체 배경은 천천히
                // 좌측으로 움직이게 된다.
            } else {
                bx = 0;
            }
        }
        if (enemy_kill >= 21) {
            buffg.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            if (bx > -4660) {
                // 기본 값이 0인 bx가 -3500 보다 크면 실행
                buffg.drawImage(bg.backGround2, bx, 0, this);
                bx -= 4;
                // bx를 0에서 -1만큼 계속 줄이므로 배경이미지의 x좌표는
                // 계속 좌측으로 이동한다. 그러므로 전체 배경은 천천히
                // 좌측으로 움직이게 된다.
            } else {
                bx = 0;
            }
        }
    }

    /*------------------- B A C K G R O U N D -------------------*/
    /*********************************************************************/
    /*------------------- E X P L O S i O N -------------------*/
    public void Draw_Explosion() {
        // 폭발 이펙트를 그리는 부분 입니다.

        for (int i = 0; i < Explosion_List.size(); ++i) {
            ex = (Explosion) Explosion_List.get(i);
            // 폭발 이펙트의 존재 유무를 체크하여 리스트를 받음.

            if (ex.damage == 0) {
                // 설정값이 0 이면 폭발용 이미지 그리기
                if (ex.ex_cnt < 7) {
                    buffg.drawImage(Explo_img[0], ex.x - Explo_img[0].getWidth(null) / 2,
                            ex.y - Explo_img[0].getHeight(null) / 2, this);
                } else if (ex.ex_cnt < 14) {
                    buffg.drawImage(Explo_img[1], ex.x - Explo_img[1].getWidth(null) / 2,
                            ex.y - Explo_img[1].getHeight(null) / 2, this);
                } else if (ex.ex_cnt < 21) {
                    buffg.drawImage(Explo_img[2], ex.x - Explo_img[2].getWidth(null) / 2,
                            ex.y - Explo_img[2].getHeight(null) / 2, this);
                } else if (ex.ex_cnt < 28) {
                    buffg.drawImage(Explo_img[3], ex.x - Explo_img[3].getWidth(null) / 2,
                            ex.y - Explo_img[3].getHeight(null) / 2, this);
                } else if (ex.ex_cnt > 28) {
                    Explosion_List.remove(i);
                    ex.ex_cnt = 0;
                    // 폭발은 따로 카운터를 계산하여
                    // 이미지를 순차적으로 그림.
                }
            } else { // 설정값이 1이면 단순 피격용 이미지 그리기
                if (ex.ex_cnt < 7) {
                    buffg.drawImage(Explo_img[0], ex.x + 100, ex.y + 38, this);
                } else if (ex.ex_cnt < 14) {
                    buffg.drawImage(Explo_img[1], ex.x + 60, ex.y + 25, this);
                } else if (ex.ex_cnt < 21) {
                    buffg.drawImage(Explo_img[2], ex.x + 5, ex.y + 5, this);
                } else if (ex.ex_cnt < 28) {
                    buffg.drawImage(Explo_img[3], ex.x + 25, ex.y + 50, this);
                } else if (ex.ex_cnt < 35) {
                    buffg.drawImage(Explo_img[1], ex.x - 15, ex.y + 13, this);
                }

                else if (ex.ex_cnt > 35) {
                    Explosion_List.remove(i);
                    ex.ex_cnt = 0;
                    // 단순 피격 또한 순차적으로 이미지를 그리지만
                    // 구분을 위해 약간 다른 방식으로 그립니다.
                }
            }
        }
    }

    public void Process_Explosion() {
        // 폭발 이펙트 처리용 메소드
        for (int i = 0; i < Explosion_List.size(); ++i) {
            ex = (Explosion) Explosion_List.get(i);
            ex.effect();
            // 이펙트 애니메이션을 나타내기위해
            // 이펙트 처리 추가가 발생하면 해당 메소드를 호출.
        }
    }

    public static boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2) {
        // 기존 충돌 판정 소스를 변경합니다.
        // 이제 이미지 변수를 바로 받아 해당 이미지의 넓이, 높이값을
        // 바로 계산합니다.

        boolean check = false;

        if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2
                + img1.getWidth(null) / 2)

                && Math.abs((y1 + img1.getHeight(null) / 2)
                - (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
            // 이미지 넓이, 높이값을 바로 받아 계산합니다.
            check = true;// 위 값이 true면 check에 true를 전달합니다.
        } else {
            check = false;
        }

        return check; // check의 값을 메소드에 리턴 시킵니다.
    }

    /*------------------- E X P L O S i O N -------------------*/
    /*********************************************************************/
    /*------------------- S C O R E -------------------*/
    public void Draw_Score() { // 상태 체크용 텍스트를 그립니다.

        buffg.setFont(new Font("Defualt", Font.BOLD, 20));
        // 폰트 설정을 합니다. 기본폰트, 굵게, 사이즈 20

        buffg.drawString("SCORE : " + enemy_kill, 1000, 70);
        // 좌표 x : 1000, y : 70에 스코어를 표시합니다.

        //buffg.drawString("Game.Boss Count : " + Boss_List.size(), 1000, 170);
        // 좌표 x : 1000, y : 90에 플레이어 체력을 표시합니다.

        //buffg.drawString("Game.Missile Count : " + Missile_List.size(), 1000, 190);
        // 좌표 x : 1000, y : 110에 나타난 미사일 수를 표시합니다.

        //buffg.drawString("Game.Enemy Count : " + Enemy_List.size(), 1000, 130);
        // 좌표 x : 1000, y : 130에 나타난 적의 수를 표시합니다.

       // buffg.drawString("EMissile Count : " + EMissile_List.size(), 1000, 150);
        // 좌표 x : 1000, y : 130에 나타난 적의 수를 표시합니다.

        buffg.drawString("피카츄 체력 : " + player_Hp, 1000, 110);


        if(Boss_List.size()==0) {
            buffg.drawString("적 체력 : " + en.getEnemy_Hp(), 1000, 90);
        }else {
            buffg.drawString("보스 체력 : " + bs.getEnemy_Hp(), 1000, 90);
        }

    }
    public void Draw_ItemInfo() {// 아이템 이미지를 그리는 부분
        List<Item> Iteminfo_List = new ArrayList<>();
        List<Item> Iteminfo2_List = new ArrayList<>();
        List<Item> Iteminfo3_List = new ArrayList<>();
        itm = new Item(0,0,0);
        Iteminfo_List.add(itm);
        for (Item item : Iteminfo_List) {
            itm = (Item) item;
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm.iteminfo_img[0], 10, 620, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm.iteminfo_img[1], 10, 620, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm.iteminfo_img[2], 10, 620, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm.iteminfo_img[3], 10, 620, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm.iteminfo_img[4], 10, 620, this);
            } else {
                buffg.drawImage(itm.iteminfo_img[5], 10, 620, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }
        itm2 = new Item(0,0,0);
        Iteminfo2_List.add(itm2);
        for (Item item : Iteminfo2_List) {
            itm2 = (Item) item;
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm.iteminfo2_img[0], 110, 620, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm.iteminfo2_img[1], 110, 620, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm.iteminfo2_img[2], 110, 620, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm.iteminfo2_img[3], 110, 620, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm.iteminfo2_img[4], 110, 620, this);
            } else {
                buffg.drawImage(itm.iteminfo2_img[5], 110, 620, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }
        itm3 = new Item(0,0,0);
        Iteminfo3_List.add(itm3);
        for (Item item : Iteminfo3_List) {
            itm3 = (Item) item;
            if ((CNT / 6 % 6) == 1) {
                buffg.drawImage(itm.iteminfo3_img[0], 210, 620, this);
            } else if ((CNT / 6 % 6) == 2) {
                buffg.drawImage(itm.iteminfo3_img[1], 210, 620, this);
            } else if ((CNT / 6 % 6) == 3) {
                buffg.drawImage(itm.iteminfo3_img[2], 210, 620, this);
            } else if ((CNT / 6 % 6) == 4) {
                buffg.drawImage(itm.iteminfo3_img[3], 210, 620, this);
            } else if ((CNT / 6 % 6) == 5) {
                buffg.drawImage(itm.iteminfo3_img[4], 210, 620, this);
            } else {
                buffg.drawImage(itm.iteminfo3_img[5], 210, 620, this);
            }
            // 배열에 생성된 각 적을 판별하여 이미지 그리기
        }
    }

    /*------------------- S C O R E -------------------*/
    /*********************************************************************/
    /*------------------- K E Y E V E N T -------------------*/
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        key.keyTyped(e);
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // 키보드가 눌러졌을때 이벤트 처리하는 곳
        key.keyPressed(e);
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        // 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
        key.keyReleased(e);
    }
/*
    public void KeyProcess() {
        // 실제로 캐릭터 움직임 실현을 위해
        // 위에서 받아들인 키값을 바탕으로
        // 키 입력시마다 5만큼의 이동을 시킨다.
        key.KeyProcess();

    }*/
    /*------------------- K E Y E V E N T -------------------*/

    void dispose1() {
        GameScreen gf = GameScreen.this;
        gf.dispose();
    }
    /*********************************************************************/

}

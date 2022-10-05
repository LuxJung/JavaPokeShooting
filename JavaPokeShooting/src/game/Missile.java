package game;

import game.set.GameSet;
import game.set.enemy.Boss;
import game.set.enemy.Enemy;
import key.event.KeyEvent;

public class Missile extends GameSet {

    int x;
    int y;
    int angle;
    int speed; // 미사일 스피드 변수를 추가.
    int who;// 미사일이발사한것이누군지구분하는변수추가

    public Missile(int x, int y, int angle, int speed, int who) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        // 객체 생성시 속도 값을 추가로 받습니다.
        this.who = who;// 추가된변수를받아옵니다.

    }

    public void move() {
        x += Math.cos(Math.toRadians(angle)) * speed;

        // 해당방향으로미사일발사.

        y += Math.sin(Math.toRadians(angle)) * speed;

        // 해당방향으로미사일발사.
    }

    public static void Process_Missile(KeyEvent key) {
        // 미사일 처리 메소드
        if (key.KEY_SPACE) { // 스페이스바 키 상태가 true 일때
            GameScreen.player_Status = 1;// 미사일 발사하면 플레이어 상태 1로변경
            if ((GameScreen.CNT % GameScreen.fire_Speed) == 0) {// 플레이어의 미사일 연사속도를 조절한다.

                switch (GameScreen.missile_status) {
                    case 0: // 아이템 획득 전
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 50, 0, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        break;

                    case 1: // 아이템 획득 후
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 50, 0, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 100, 0, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        break;

                    case 2: // 아이템 획득 후
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 0, 15, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 50, 0, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        GameScreen.ms = new Missile(GameScreen.x + 120, GameScreen.y + 100, 350, GameScreen.missile_Speed, 0);
                        GameScreen.Missile_List.add(GameScreen.ms);
                        break;

                }

            }
        }

        // 미사일과 적의 충돌판정
        for (int i = 0; i < GameScreen.Missile_List.size(); ++i) {
            GameScreen.ms = (Missile) GameScreen.Missile_List.get(i);// 발사체 추가
            GameScreen.ms.move();// 발사체 이동
            if (GameScreen.ms.x > GameScreen.SCREEN_WIDTH - 20) {
                GameScreen.Missile_List.remove(i);// 미사일 스크린 넘어갈 경우 삭제
            }

            if (GameScreen.Missile_List.size() != 0) {

                // 미사일과 쫄병들의 충돌판정
                for (int j = 0; j < GameScreen.Enemy_List.size(); ++j) {
                    GameScreen.en = (Enemy) GameScreen.Enemy_List.get(j);

                    if (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.en.x, GameScreen.en.y,
                            GameScreen.Missile_img[0], GameScreen.Enemy_img[0]) && GameScreen.ms.who == 0) {
                        // 미사일의 좌표 및 이미지파일, 적의 좌표및 이미지 파일을 받아
                        // 충돌판정 메소드로 넘기고 true,false값을리턴 받아 true면 아래를 실행합니다.
                        GameScreen.Missile_List.remove(i);
                        int hp = GameScreen.en.getEnemy_Hp();
                        hp--;
                        GameScreen.en.setEnemy_Hp(hp);
                        if (hp == 0) {
                            GameScreen.ex = new Explosion(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null)-50 / 2,
                                    GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2, 0);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameScreen.Explosion_List.add(GameScreen.ex);
                            GameScreen.Enemy_List.remove(j);
                            GameScreen.enemy_kill += 1; // 적 처치 +1

                            if ((int) (Math.round((Math.random() * 100))) > 90) {
                                GameScreen.itm = new Item(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null) / 2,
                                        GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                // 적이 위치해있는 곳의 중심 좌표 x,y 값과 아이템 이동속도
                                GameScreen.Item_List.add(GameScreen.itm);// 제거된 적위치에 폭발 이펙트를 추가합니다.
                            } else if ((int) (Math.round((Math.random() * 100))) > 95) {
                                GameScreen.itm2 = new Item(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null) / 2,
                                        GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                GameScreen.Item2_List.add(GameScreen.itm2);
                            } else if ((int) (Math.round((Math.random() * 100))) > 97) {
                                GameScreen.itm3 = new Item(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null) / 2,
                                        GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                GameScreen.Item3_List.add(GameScreen.itm3);
                            }
                        } else {
                            GameScreen.ex = new Explosion(GameScreen.en.x + GameScreen.Enemy_img[0].getWidth(null)-50 / 2,
                                    GameScreen.en.y + GameScreen.Enemy_img[0].getHeight(null) / 2, 1);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameScreen.Explosion_List.add(GameScreen.ex);
                        }
                    }
                    if (GameScreen.Enemy_List.isEmpty()) {
                        GameScreen.EMissile_List.clear();
                    }
                }

                // 플레이어 미사일 보스 어택
                for (int j = 0; j < GameScreen.Boss_List.size(); ++j) {
                    GameScreen.bs = (Boss) GameScreen.Boss_List.get(j);

                    if (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.bs.x, GameScreen.bs.y,
                            GameScreen.Missile_img[0], GameScreen.EnemyBoss_img[0]) && GameScreen.ms.who == 0) {
                        GameScreen.Missile_List.remove(i);
                        int hp = GameScreen.bs.getEnemy_Hp();
                        hp--;
                        GameScreen.bs.setEnemy_Hp(hp);
                        if (hp == 0) {
                            GameScreen.Boss_List.remove(j);
                            GameScreen.ex = new Explosion(GameScreen.bs.x + GameScreen.EnemyBoss_img[0].getWidth(null)-450 / 2,
                                    GameScreen.bs.y + GameScreen.EnemyBoss_img[0].getHeight(null) / 2, 0);
                            GameScreen.Explosion_List.add(GameScreen.ex);
                            GameScreen.enemy_kill += 1; // 적 처치 +1

                            if ((int) (Math.round((Math.random() * 100))) > 80) {
                                GameScreen.itm = new Item(GameScreen.bs.x + GameScreen.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameScreen.bs.y + GameScreen.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                // 적이 위치해있는 곳의 중심 좌표 x,y 값과 아이템 이동속도
                                GameScreen.Item_List.add(GameScreen.itm);// 제거된 적위치에 아이템 추가
                            } else if ((int) (Math.round((Math.random() * 100))) > 90) {
                                GameScreen.itm2 = new Item(
                                        GameScreen.bs.x + GameScreen.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameScreen.bs.y + GameScreen.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                GameScreen.Item2_List.add(GameScreen.itm2);
                            } else if ((int) (Math.round((Math.random() * 100))) > 95) {
                                GameScreen.itm3 = new Item(
                                        GameScreen.bs.x + GameScreen.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameScreen.bs.y + GameScreen.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameScreen.item_speed);
                                GameScreen.Item3_List.add(GameScreen.itm3);
                            }
                        } else {
                            GameScreen.ex = new Explosion(GameScreen.bs.x + GameScreen.EnemyBoss_img[0].getWidth(null)-450 / 2,
                                    GameScreen.bs.y + GameScreen.EnemyBoss_img[0].getHeight(null) / 2, 1);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameScreen.Explosion_List.add(GameScreen.ex);
                        }
                    }
                    if (GameScreen.Boss_List.isEmpty()) {
                        GameScreen.BMissile_List.clear();
                    }
                }
            }

            // 쫄 병 미 사 일
            // 상 쇄
            for (int k = 0; k < GameScreen.EMissile_List.size(); ++k) {
                GameScreen.ems = (Missile) GameScreen.EMissile_List.get(k);// 발사체 추가
                if (GameScreen.EMissile_List.size() != 0) {
                    if (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.ems.x, GameScreen.ems.y,
                            GameScreen.Missile_img[0], GameScreen.EMissile_img[0]) && GameScreen.ms.who == 0) {

                        if ((GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.ems.x, GameScreen.ems.y,
                                GameScreen.Missile_img[0], GameScreen.EMissile_img[0]) && GameScreen.ms.who == 0)
                                && (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.en.x, GameScreen.en.y,
                                GameScreen.Missile_img[0], GameScreen.Enemy_img[0]) && GameScreen.ms.who == 0)) {
                            GameScreen.ex = new Explosion(GameScreen.ms.x + GameScreen.Missile_img[0].getWidth(null)+20,
                                    GameScreen.ms.y + GameScreen.Missile_img[0].getHeight(null) / 2, 1);
                            // 플레이어자리에충돌용폭발이펙트객체생성
                            GameScreen.Explosion_List.add(GameScreen.ex);
                            // 생성한객체를배열로저장
                            if (GameScreen.Missile_List.size() != 0 && GameScreen.EMissile_List.size() != 0) {
                                try {
                                    GameScreen.Missile_List.remove(i);
                                    GameScreen.EMissile_List.remove(k);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println(e);
                                }
                            }
                        }
                        GameScreen.ex = new Explosion(GameScreen.ms.x + GameScreen.Missile_img[0].getWidth(null)+20,
                                GameScreen.ms.y + GameScreen.Missile_img[0].getHeight(null) / 2, 1);
                        // 플레이어자리에충돌용폭발이펙트객체생성
                        GameScreen.Explosion_List.add(GameScreen.ex);
                        // 생성한객체를배열로저장
                        if (GameScreen.Missile_List.size() != 0 && GameScreen.EMissile_List.size() != 0) {
                            try {
                                GameScreen.Missile_List.remove(i);
                                GameScreen.EMissile_List.remove(k);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(e);
                            }

                        }

                        // 해당되는적미사일삭제

                        // 미사일과 적이 겹쳐있을때
                    }
                }
            }

            // 보 스 미 사 일
            // 상 쇄
            for (int k = 0; k < GameScreen.BMissile_List.size(); ++k) {
                GameScreen.bms = (Missile) GameScreen.BMissile_List.get(k);// 발사체 추가
                if (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.bms.x, GameScreen.bms.y,
                        GameScreen.Missile_img[0], GameScreen.EMissile_img[0]) && GameScreen.ms.who == 0) {
                    // 적이발사한미사일이플레이어와충돌하는지여부를확인

                    if ((GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.bms.x, GameScreen.bms.y,
                            GameScreen.Missile_img[0], GameScreen.EMissile_img[0]) && GameScreen.ms.who == 0)
                            && (GameScreen.Crash(GameScreen.ms.x, GameScreen.ms.y, GameScreen.bs.x, GameScreen.bs.y,
                            GameScreen.Missile_img[0], GameScreen.EnemyBoss_img[0]) && GameScreen.ms.who == 0)) {
                        GameScreen.ex = new Explosion(GameScreen.ms.x + GameScreen.Missile_img[0].getWidth(null)+20,
                                GameScreen.ms.y + GameScreen.Missile_img[0].getHeight(null) / 2, 1);
                        // 플레이어자리에충돌용폭발이펙트객체생성
                        GameScreen.Explosion_List.add(GameScreen.ex);
                        // 생성한객체를배열로저장
                        if (GameScreen.Missile_List.size() != 0 && GameScreen.EMissile_List.size() != 0) {
                            try {
                                GameScreen.Missile_List.remove(i);
                                GameScreen.EMissile_List.remove(k);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(e);
                            }

                        }
                    }

                    GameScreen.ex = new Explosion(GameScreen.ms.x + GameScreen.Missile_img[0].getWidth(null)+20,
                            GameScreen.ms.y + GameScreen.Missile_img[0].getHeight(null) / 2, 1);
                    // 플레이어자리에충돌용폭발이펙트객체생성
                    GameScreen.Explosion_List.add(GameScreen.ex);
                    // 생성한객체를배열로저장
                    if (GameScreen.Missile_List.size() != 0 && GameScreen.BMissile_List.size() != 0) {
                        try {
                            GameScreen.Missile_List.remove(i);
                            GameScreen.BMissile_List.remove(k);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e);
                        }
                    }
                    // 해당되는적미사일삭제
                }
            }

        }

        // 쫄 병 의 미 사 일
        // 맞 았 을 때
        for (int i = 0; i < GameScreen.EMissile_List.size(); ++i) {
            GameScreen.ems = (Missile) GameScreen.EMissile_List.get(i);// 발사체 추가
            GameScreen.ems.move();// 발사체 이동
            if (GameScreen.ems.x < -200) {
                GameScreen.EMissile_List.remove(i);// 발사체 삭제
            }
            if (GameScreen.Crash(GameScreen.x, GameScreen.y, GameScreen.ems.x, GameScreen.ems.y, GameScreen.Player_img[0],
                    GameScreen.EMissile_img[0]) && GameScreen.ems.who == 1) {
                // 적이발사한미사일이플레이어와충돌하는지여부를확인
                GameScreen.player_Hp--;
                // 플레이어체력포인트를1삭감
                GameScreen.ex = new Explosion(GameScreen.x, GameScreen.y, 1);
                // 플레이어자리에충돌용폭발이펙트객체생성
                GameScreen.Explosion_List.add(GameScreen.ex);
                // 생성한객체를배열로저장
                GameScreen.EMissile_List.remove(i);
                // 해당되는적미사일삭제
            }
        }

        // 보 스 의 미 사 일
        // 맞 았 을 때
        for (int i = 0; i < GameScreen.BMissile_List.size(); ++i) {
            GameScreen.bms = (Missile) GameScreen.BMissile_List.get(i);// 발사체 추가
            GameScreen.bms.move();// 발사체 이동
            if (GameScreen.bms.x < -200) {
                GameScreen.BMissile_List.remove(i);// 발사체 삭제
            }
            if (GameScreen.Crash(GameScreen.x, GameScreen.y, GameScreen.bms.x, GameScreen.bms.y, GameScreen.Player_img[0],
                    GameScreen.EMissile_img[0]) && GameScreen.bms.who == 2) {
                // 적이발사한미사일이플레이어와충돌하는지여부를확인
                GameScreen.player_Hp--;
                // 플레이어체력포인트를1삭감
                GameScreen.ex = new Explosion(GameScreen.x, GameScreen.y, 1);
                // 플레이어자리에충돌용폭발이펙트객체생성
                GameScreen.Explosion_List.add(GameScreen.ex);
                // 생성한객체를배열로저장
                GameScreen.BMissile_List.remove(i);
                // 해당되는적미사일삭제
            }
        }

    }

}
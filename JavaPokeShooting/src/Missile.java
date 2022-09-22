import java.awt.Point;

public class Missile extends GameSet {

    int x;
    int y;
    int angle;
    int speed; // 미사일 스피드 변수를 추가.
    int who;// 미사일이발사한것이누군지구분하는변수추가

    Missile(int x, int y, int angle, int speed, int who) {
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

    public static void Process_Missile() {
        // 미사일 처리 메소드
        if (GameFrame.KeySpace) { // 스페이스바 키 상태가 true 일때
            GameFrame.player_Status = 1;// 미사일 발사하면 플레이어 상태 1로변경
            if ((GameFrame.cnt % GameFrame.fire_Speed) == 0) {// 플레이어의 미사일 연사속도를 조절한다.

                switch (GameFrame.missile_status) {
                    case 0: // 아이템 획득 전
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        break;

                    case 1: // 아이템 획득 후
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 100, 0, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        break;

                    case 2: // 아이템 획득 후
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 0, 15, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 100, 350, GameFrame.missile_Speed, 0);
                        GameFrame.Missile_List.add(GameFrame.ms);
                        break;

                }

            }
        }

        // 미사일과 적의 충돌판정
        for (int i = 0; i < GameFrame.Missile_List.size(); ++i) {
            GameFrame.ms = (Missile) GameFrame.Missile_List.get(i);// 발사체 추가
            GameFrame.ms.move();// 발사체 이동
            if (GameFrame.ms.x > StartGame.SCREEN_WIDTH - 20) {
                GameFrame.Missile_List.remove(i);// 미사일 스크린 넘어갈 경우 삭제
            }

            if (GameFrame.Missile_List.size() != 0) {

                // 미사일과 쫄병들의 충돌판정
                for (int j = 0; j < GameFrame.Enemy_List.size(); ++j) {
                    GameFrame.en = (Enemy) GameFrame.Enemy_List.get(j);

                    if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.en.x, GameFrame.en.y,
                            GameFrame.Missile_img[0], GameFrame.Enemy_img[0]) && GameFrame.ms.who == 0) {
                        // 미사일의 좌표 및 이미지파일, 적의 좌표및 이미지 파일을 받아
                        // 충돌판정 메소드로 넘기고 true,false값을리턴 받아 true면 아래를 실행합니다.
                        GameFrame.Missile_List.remove(i);
                        GameFrame.en.enemy_Hp--;
                        if (GameFrame.en.enemy_Hp == 0) {
                            GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null)-50 / 2,
                                    GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 0);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameFrame.Explosion_List.add(GameFrame.ex);
                            GameFrame.Enemy_List.remove(j);
                            GameFrame.enemy_kill += 1; // 적 처치 +1

                            if ((int) (Math.round((Math.random() * 100))) > 90) {
                                GameFrame.itm = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
                                        GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                // 적이 위치해있는 곳의 중심 좌표 x,y 값과 아이템 이동속도
                                GameFrame.Item_List.add(GameFrame.itm);// 제거된 적위치에 폭발 이펙트를 추가합니다.
                            } else if ((int) (Math.round((Math.random() * 100))) > 95) {
                                GameFrame.itm2 = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
                                        GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                GameFrame.Item2_List.add(GameFrame.itm2);
                            } else if ((int) (Math.round((Math.random() * 100))) > 97) {
                                GameFrame.itm3 = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
                                        GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                GameFrame.Item3_List.add(GameFrame.itm3);
                            }
                        } else {
                            GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null)-50 / 2,
                                    GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 1);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameFrame.Explosion_List.add(GameFrame.ex);
                        }
                    }
                    if (GameFrame.Enemy_List.isEmpty()) {
                        GameFrame.EMissile_List.clear();
                    }
                }

                // 플레이어 미사일 보스 어택
                for (int j = 0; j < GameFrame.Boss_List.size(); ++j) {
                    GameFrame.bs = (Boss) GameFrame.Boss_List.get(j);

                    if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bs.x, GameFrame.bs.y,
                            GameFrame.Missile_img[0], GameFrame.EnemyBoss_img[0]) && GameFrame.ms.who == 0) {
                        GameFrame.Missile_List.remove(i);
                        GameFrame.bs.enemy_Hp--;
                        if (GameFrame.bs.enemy_Hp == 0) {
                            GameFrame.Boss_List.remove(j);
                            GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null)-450 / 2,
                                    GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 0);
                            GameFrame.Explosion_List.add(GameFrame.ex);
                            GameFrame.enemy_kill += 1; // 적 처치 +1

                            if ((int) (Math.round((Math.random() * 100))) > 80) {
                                GameFrame.itm = new Item(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                // 적이 위치해있는 곳의 중심 좌표 x,y 값과 아이템 이동속도
                                GameFrame.Item_List.add(GameFrame.itm);// 제거된 적위치에 아이템 추가
                            } else if ((int) (Math.round((Math.random() * 100))) > 90) {
                                GameFrame.itm2 = new Item(
                                        GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                GameFrame.Item2_List.add(GameFrame.itm2);
                            } else if ((int) (Math.round((Math.random() * 100))) > 95) {
                                GameFrame.itm3 = new Item(
                                        GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
                                        GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
                                        GameFrame.item_speed);
                                GameFrame.Item3_List.add(GameFrame.itm3);
                            }
                        } else {
                            GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null)-450 / 2,
                                    GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 1);
                            // 적이 위치해있는 곳의 중심 좌표 x,y 에 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
                            // 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
                            GameFrame.Explosion_List.add(GameFrame.ex);
                        }
                    }
                    if (GameFrame.Boss_List.isEmpty()) {
                        GameFrame.BMissile_List.clear();
                    }
                }
            }

            // 쫄 병 미 사 일
            // 상 쇄
            for (int k = 0; k < GameFrame.EMissile_List.size(); ++k) {
                GameFrame.ems = (Missile) GameFrame.EMissile_List.get(k);// 발사체 추가
                if (GameFrame.EMissile_List.size() != 0) {
                    if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.ems.x, GameFrame.ems.y,
                            GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0) {

                        if ((GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.ems.x, GameFrame.ems.y,
                                GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0)
                                && (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.en.x, GameFrame.en.y,
                                GameFrame.Missile_img[0], GameFrame.Enemy_img[0]) && GameFrame.ms.who == 0)) {
                            GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
                                    GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
                            // 플레이어자리에충돌용폭발이펙트객체생성
                            GameFrame.Explosion_List.add(GameFrame.ex);
                            // 생성한객체를배열로저장
                            if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
                                try {
                                    GameFrame.Missile_List.remove(i);
                                    GameFrame.EMissile_List.remove(k);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println(e);
                                }
                            }
                        }
                        GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
                                GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
                        // 플레이어자리에충돌용폭발이펙트객체생성
                        GameFrame.Explosion_List.add(GameFrame.ex);
                        // 생성한객체를배열로저장
                        if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
                            try {
                                GameFrame.Missile_List.remove(i);
                                GameFrame.EMissile_List.remove(k);
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
            for (int k = 0; k < GameFrame.BMissile_List.size(); ++k) {
                GameFrame.bms = (Missile) GameFrame.BMissile_List.get(k);// 발사체 추가
                if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bms.x, GameFrame.bms.y,
                        GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0) {
                    // 적이발사한미사일이플레이어와충돌하는지여부를확인

                    if ((GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bms.x, GameFrame.bms.y,
                            GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0)
                            && (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bs.x, GameFrame.bs.y,
                            GameFrame.Missile_img[0], GameFrame.EnemyBoss_img[0]) && GameFrame.ms.who == 0)) {
                        GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
                                GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
                        // 플레이어자리에충돌용폭발이펙트객체생성
                        GameFrame.Explosion_List.add(GameFrame.ex);
                        // 생성한객체를배열로저장
                        if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
                            try {
                                GameFrame.Missile_List.remove(i);
                                GameFrame.EMissile_List.remove(k);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(e);
                            }

                        }
                    }

                    GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
                            GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
                    // 플레이어자리에충돌용폭발이펙트객체생성
                    GameFrame.Explosion_List.add(GameFrame.ex);
                    // 생성한객체를배열로저장
                    if (GameFrame.Missile_List.size() != 0 && GameFrame.BMissile_List.size() != 0) {
                        try {
                            GameFrame.Missile_List.remove(i);
                            GameFrame.BMissile_List.remove(k);
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
        for (int i = 0; i < GameFrame.EMissile_List.size(); ++i) {
            GameFrame.ems = (Missile) GameFrame.EMissile_List.get(i);// 발사체 추가
            GameFrame.ems.move();// 발사체 이동
            if (GameFrame.ems.x < -200) {
                GameFrame.EMissile_List.remove(i);// 발사체 삭제
            }
            if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.ems.x, GameFrame.ems.y, GameFrame.Player_img[0],
                    GameFrame.EMissile_img[0]) && GameFrame.ems.who == 1) {
                // 적이발사한미사일이플레이어와충돌하는지여부를확인
                GameFrame.player_Hp--;
                // 플레이어체력포인트를1삭감
                GameFrame.ex = new Explosion(GameFrame.x, GameFrame.y, 1);
                // 플레이어자리에충돌용폭발이펙트객체생성
                GameFrame.Explosion_List.add(GameFrame.ex);
                // 생성한객체를배열로저장
                GameFrame.EMissile_List.remove(i);
                // 해당되는적미사일삭제
            }
        }

        // 보 스 의 미 사 일
        // 맞 았 을 때
        for (int i = 0; i < GameFrame.BMissile_List.size(); ++i) {
            GameFrame.bms = (Missile) GameFrame.BMissile_List.get(i);// 발사체 추가
            GameFrame.bms.move();// 발사체 이동
            if (GameFrame.bms.x < -200) {
                GameFrame.BMissile_List.remove(i);// 발사체 삭제
            }
            if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.bms.x, GameFrame.bms.y, GameFrame.Player_img[0],
                    GameFrame.EMissile_img[0]) && GameFrame.bms.who == 2) {
                // 적이발사한미사일이플레이어와충돌하는지여부를확인
                GameFrame.player_Hp--;
                // 플레이어체력포인트를1삭감
                GameFrame.ex = new Explosion(GameFrame.x, GameFrame.y, 1);
                // 플레이어자리에충돌용폭발이펙트객체생성
                GameFrame.Explosion_List.add(GameFrame.ex);
                // 생성한객체를배열로저장
                GameFrame.BMissile_List.remove(i);
                // 해당되는적미사일삭제
            }
        }

    }

}
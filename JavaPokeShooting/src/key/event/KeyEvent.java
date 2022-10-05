package key.event;
import game.GameScreen;

import java.awt.event.KeyListener;

public class KeyEvent implements KeyListener{

    public boolean KEY_ENTER = false;
    public boolean KEY_UP = false;
    public boolean KEY_DOWN = false;
    public boolean KEY_LEFT = false;
    public boolean KEY_RIGHT = false;
    public boolean KEY_SPACE = false; // 미사일 발사를 위한 키보드 스페이스키 입력을 추가합니다.

    /*------------------- K E Y E V E N T -------------------*/
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // 키보드가 눌러졌을때 이벤트 처리하는 곳
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP:
                KEY_UP = true;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                KEY_DOWN = true;
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                KEY_LEFT = true;
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                KEY_RIGHT = true;
                break;
            case java.awt.event.KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                KEY_SPACE = true;
                break;
            case java.awt.event.KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                KEY_ENTER = true;
                break;
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        // 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP:
                KEY_UP = false;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                KEY_DOWN = false;
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                KEY_LEFT = false;
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                KEY_RIGHT = false;
                break;
            case java.awt.event.KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                KEY_SPACE = false;
                break;
            case java.awt.event.KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                KEY_ENTER = false;
                break;

        }
    }

    public void KeyProcess() {
        // 실제로 캐릭터 움직임 실현을 위해
        // 위에서 받아들인 키값을 바탕으로
        // 키 입력시마다 5만큼의 이동을 시킨다.
        if (KEY_UP)
            if (GameScreen.y > 80)
                GameScreen.y -= 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (KEY_DOWN)
            if (GameScreen.y + GameScreen.Player_img[0].getHeight(null) < GameScreen.SCREEN_HEIGHT - 60)
                GameScreen.y += 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (KEY_LEFT)
            if (GameScreen.x > 0)
                GameScreen.x -= 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (KEY_RIGHT)
            if (GameScreen.x + GameScreen.Player_img[0].getWidth(null) < GameScreen.SCREEN_WIDTH)
                GameScreen.x += 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.

        //if (GameFrame.KeyEnter == true);

    }

}

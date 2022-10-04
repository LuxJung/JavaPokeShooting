import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyevent implements KeyListener{


    /*------------------- K E Y E V E N T -------------------*/
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 키보드가 눌러졌을때 이벤트 처리하는 곳
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                GameScreen.KEY_UP = true;
                break;
            case KeyEvent.VK_DOWN:
                GameScreen.KEY_DOWN = true;
                break;
            case KeyEvent.VK_LEFT:
                GameScreen.KEY_LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
                GameScreen.KEY_RIGHT = true;
                break;
            case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                GameScreen.KEY_SPACE = true;
                break;
            case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                GameScreen.KEY_ENTER = true;



                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                GameScreen.KEY_UP = false;
                break;
            case KeyEvent.VK_DOWN:
                GameScreen.KEY_DOWN = false;
                break;
            case KeyEvent.VK_LEFT:
                GameScreen.KEY_LEFT = false;
                break;
            case KeyEvent.VK_RIGHT:
                GameScreen.KEY_RIGHT = false;
                break;
            case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                GameScreen.KEY_SPACE = false;
                break;
            case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                GameScreen.KEY_ENTER = false;
                break;

        }
    }

    public void KeyProcess() {
        // 실제로 캐릭터 움직임 실현을 위해
        // 위에서 받아들인 키값을 바탕으로
        // 키 입력시마다 5만큼의 이동을 시킨다.
        if (GameScreen.KEY_UP == true)
            if (GameScreen.y > 80)
                GameScreen.y -= 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameScreen.KEY_DOWN == true)
            if (GameScreen.y + GameScreen.Player_img[0].getHeight(null) < GameScreen.SCREEN_HEIGHT - 60)
                GameScreen.y += 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameScreen.KEY_LEFT == true)
            if (GameScreen.x > 0)
                GameScreen.x -= 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameScreen.KEY_RIGHT == true)
            if (GameScreen.x + GameScreen.Player_img[0].getWidth(null) < GameScreen.SCREEN_WIDTH)
                GameScreen.x += 5;
        GameScreen.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.

        //if (GameFrame.KeyEnter == true);


    }
    /*------------------- K E Y E V E N T -------------------*/
}

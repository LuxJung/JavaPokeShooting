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
                GameFrame.KeyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                GameFrame.KeyDown = true;
                break;
            case KeyEvent.VK_LEFT:
                GameFrame.KeyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                GameFrame.KeyRight = true;
                break;
            case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                GameFrame.KeySpace = true;
                break;
            case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                GameFrame.KeyEnter = true;



                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                GameFrame.KeyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                GameFrame.KeyDown = false;
                break;
            case KeyEvent.VK_LEFT:
                GameFrame.KeyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                GameFrame.KeyRight = false;
                break;
            case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
                GameFrame.KeySpace = false;
                break;
            case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
                GameFrame.KeyEnter = false;
                break;

        }
    }

    public void KeyProcess() {
        // 실제로 캐릭터 움직임 실현을 위해
        // 위에서 받아들인 키값을 바탕으로
        // 키 입력시마다 5만큼의 이동을 시킨다.
        if (GameFrame.KeyUp == true)
            if (GameFrame.y > 80)
                GameFrame.y -= 5;
        GameFrame.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameFrame.KeyDown == true)
            if (GameFrame.y + GameFrame.Player_img[0].getHeight(null) < StartGame.SCREEN_HEIGHT - 60)
                GameFrame.y += 5;
        GameFrame.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameFrame.KeyLeft == true)
            if (GameFrame.x > 0)
                GameFrame.x -= 5;
        GameFrame.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.
        if (GameFrame.KeyRight == true)
            if (GameFrame.x + GameFrame.Player_img[0].getWidth(null) < StartGame.SCREEN_WIDTH)
                GameFrame.x += 5;
        GameFrame.player_Status = 0;
        // 이동키가 눌려지면 플레이어 상태를 0으로 돌립니다.

        //if (GameFrame.KeyEnter == true);


    }
    /*------------------- K E Y E V E N T -------------------*/
}

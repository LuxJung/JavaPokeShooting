import game.GameIntro;

public class StartGame {
    //과연 바꼈을까???
    public static final long start = System.currentTimeMillis();
    public static final long end = start + 10*1000; // 60 seconds * 1000 ms/sec
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new GameIntro();
        //GameFrame fm= new GameFrame();

    }

}
//시작화면 구성
//이동 애니매이션 구현
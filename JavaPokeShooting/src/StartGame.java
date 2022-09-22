public class StartGame {

    public static final int SCREEN_WIDTH = 1280; // 프레임 넓이
    public static final int SCREEN_HEIGHT = 720; // 프레임 높이
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
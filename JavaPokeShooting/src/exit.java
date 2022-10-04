import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class exit extends WindowAdapter {

    exit(WindowEvent e){
        GameScreen gf = (GameScreen)e.getWindow();
        gf.dispose();
    }




}

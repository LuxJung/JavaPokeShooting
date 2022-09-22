import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class exit extends WindowAdapter {

    exit(WindowEvent e){
        GameFrame gf = (GameFrame)e.getWindow();
        gf.dispose();
    }




}

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStatus extends JFrame {
    private Image screenImage;
    private Graphics screenGraphic;
    GameScreen gf ;
    private ImageIcon optExitOn = new ImageIcon(
            "src/images/resize피카츄/옵션창xoff.png");
    private ImageIcon optExitOff = new ImageIcon(
            "src/images/resize피카츄/옵션창xon.png");

    private ImageIcon optReStartOn = new ImageIcon(
            "src/images/resize피카츄/옵션창reStart(on).png");
    private ImageIcon optReStartOff = new ImageIcon(
            "src/images/resize피카츄/옵션창reStart(off).png");

    private ImageIcon optGoMainOn = new ImageIcon(
            "src/images/resize피카츄/옵션창goMain(on).png");
    private ImageIcon optGoMainOff = new ImageIcon(
            "src/images/resize피카츄/옵션창goMain(off).png");

    private Image StatusBackground = new ImageIcon(
            "src/images/resize피카츄/옵션창.png").getImage();

    private JLabel optBar = new JLabel(
            new ImageIcon("src/images/resize피카츄/옵션창라벨.png"));

    private JButton optExit = new JButton(optExitOn);

    private JButton reStartButton = new JButton(optReStartOn);
    private JButton goMainButton = new JButton(optGoMainOn);

    private int mouseX, mouseY;

    Keyevent key;
    Toolkit tk = Toolkit.getDefaultToolkit();

    public GameStatus() {
        setUndecorated(true);
        setTitle("슈우우우티이잉겜");// 프레임 이름

        setSize(500, 500);// 프레임 크기설정
        setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
        setLocationRelativeTo(null);// 프레임을 중앙에 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame창 종료시 프로그램 종료
        setVisible(true); // 프레임을 눈에 보이게 띄웁니다.

        setBackground(new Color(0, 0, 0, 122));
        setLayout(null);

        // 메뉴바종료 관련
        optExit.setBounds(435, 20, 30, 30);
        optExit.setBorderPainted(false);
        optExit.setContentAreaFilled(false);
        optExit.setFocusPainted(false);
        optExit.addMouseListener(new MouseAdapter() {
            // 마우스가 x버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                optExit.setIcon(optExitOff);
                optExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 x버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                optExit.setIcon(optExitOn);
                optExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 x버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {
                optExit.addActionListener((ActionListener) new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        dispose();
                        GameScreen.trd.resume();
                        GameScreen.KEY_ENTER = false;
                    }
                });
            }
        });
        add(optExit);

        // 메뉴바 관련
        optBar.setBounds(0, 0, 500, 50);
        optBar.addMouseListener(new MouseAdapter() {
            // 마우스 위치를 받아옴
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        add(optBar);

        // 게임재시작
        reStartButton.setBounds(100, 100, 300, 100);
        reStartButton.setBorderPainted(false);
        reStartButton.setContentAreaFilled(false);
        reStartButton.setFocusPainted(false);
        reStartButton.addMouseListener(new MouseAdapter() {
            // 마우스가 x버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                reStartButton.setIcon(optReStartOff);
                reStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 x버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                reStartButton.setIcon(optReStartOn);
                reStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 x버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        add(reStartButton);

        // 게임GoMain관련
        goMainButton.setBounds(100, 300, 300, 100);
        goMainButton.setBorderPainted(false);
        goMainButton.setContentAreaFilled(false);
        goMainButton.setFocusPainted(false);
        goMainButton.addMouseListener(new MouseAdapter() {
            // 마우스가 GoMain버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                goMainButton.setIcon(optGoMainOff);
                goMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 GoMain버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                goMainButton.setIcon(optGoMainOn);
                goMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 GoMain버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {

                goMainButton.addActionListener((ActionListener) new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub

                        GameScreen gf = new GameScreen();
                        gf.dispose1();
                        GameScreen.trd.stop();
                    }
                });

                dispose();
                GameIntro gi= new GameIntro();
            }
        });
        add(goMainButton);

    }

    public void option() {
        setUndecorated(true);
        setTitle("슈우우우티이잉겜");// 프레임 이름

        setSize(500, 500);// 프레임 크기설정
        setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
        setLocationRelativeTo(null);// 프레임을 중앙에 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame창 종료시 프로그램 종료
        setVisible(true); // 프레임을 눈에 보이게 띄웁니다.

        setBackground(new Color(0, 0, 0, 122));
        setLayout(null);

        // 메뉴바종료 관련
        optExit.setBounds(435, 20, 30, 30);
        optExit.setBorderPainted(false);
        optExit.setContentAreaFilled(false);
        optExit.setFocusPainted(false);
        optExit.addMouseListener(new MouseAdapter() {
            // 마우스가 x버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                optExit.setIcon(optExitOff);
                optExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 x버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                optExit.setIcon(optExitOn);
                optExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 x버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {
                optExit.addActionListener((ActionListener) new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        dispose();
                        GameScreen.trd.resume();
                        GameScreen.KEY_ENTER = false;
                    }
                });
            }
        });
        add(optExit);

        // 메뉴바 관련
        optBar.setBounds(0, 0, 500, 50);
        optBar.addMouseListener(new MouseAdapter() {
            // 마우스 위치를 받아옴
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        add(optBar);

        // 게임재시작
        reStartButton.setBounds(100, 100, 300, 100);
        reStartButton.setBorderPainted(false);
        reStartButton.setContentAreaFilled(false);
        reStartButton.setFocusPainted(false);
        reStartButton.addMouseListener(new MouseAdapter() {
            // 마우스가 x버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                reStartButton.setIcon(optReStartOff);
                reStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 x버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                reStartButton.setIcon(optReStartOn);
                reStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 x버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        add(reStartButton);

        // 게임GoMain관련
        goMainButton.setBounds(100, 300, 300, 100);
        goMainButton.setBorderPainted(false);
        goMainButton.setContentAreaFilled(false);
        goMainButton.setFocusPainted(false);
        goMainButton.addMouseListener(new MouseAdapter() {
            // 마우스가 GoMain버튼에 올라갔을때 변화
            @Override
            public void mouseEntered(MouseEvent e) {
                goMainButton.setIcon(optGoMainOff);
                goMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // 마우스가 GoMain버튼에서 떨어졌을때 변화
            @Override
            public void mouseExited(MouseEvent e) {
                goMainButton.setIcon(optGoMainOn);
                goMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 GoMain버튼을 눌었을때 종료
            @Override
            public void mousePressed(MouseEvent e) {

                goMainButton.addActionListener((ActionListener) new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub

                        GameScreen gf = new GameScreen();
                        gf.dispose1();
                        GameScreen.trd.stop();
                    }
                });

                dispose();
                GameIntro gi= new GameIntro();
            }
        });
        add(goMainButton);
    }

    public void paint(Graphics g) {
        screenImage = createImage(500, 500);
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(StatusBackground, 0, 0, null);
        paintComponents(g);// 메뉴버튼
        this.repaint();
    }
/*
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
			GameFrame.KeyEnter = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER: // 엔터키 입력 처리 추가
			GameFrame.KeyEnter = false;
			break;
		}
	}*/
}

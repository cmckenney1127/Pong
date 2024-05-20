import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
public class Pong extends JFrame {
	GraphicsPanel gamePanel = new GraphicsPanel();
	static Rectangle2D.Double leftPaddle;
	static Rectangle2D.Double rightPaddle;
	static Rectangle2D.Double ball;
	static Rectangle2D.Double middleBorder;
	Color leftPaddleColor;
	Color rightPaddleColor;
	JLabel leftScoreLabelTop = new JLabel();
	JLabel rightScoreLabelTop = new JLabel();
	JLabel leftScoreLabelBottom = new JLabel();
	JLabel rightScoreLabelBottom = new JLabel();
	Color ballColor;
	final int LEFTPADDLEW = 45;
	final int LEFTPADDLEH = 150;
	final int RIGHTPADDLEW = 45;
	final int RIGHTPADDLEH = 150;
	final int BALLW = 30;
	final int BALLH = 30;
	
	int leftPaddleY;
	int rightPaddleY;
	int ballX;
	int ballY;
	int middleBorderX;
	int middleBorderY;
	
	boolean movingRight = false;
	boolean movingUp = true;
	
	JPanel gameScorePanel = new JPanel();
	int leftScore = 0;
	int rightScore = 0;
	
	Timer timer;
	
	boolean leftPaddleUp = false;
	boolean leftPaddleDown = false;
	boolean rightPaddleUp = false;
	boolean rightPaddleDown = false;
	
	File sound = new File("beep.WAV");
	boolean isSound;
	
	public Pong(Color leftPaddleColor, Color rightPaddleColor, Color ballColor, 
			int gameSpeed, boolean isSound) {
		setTitle("PONG!");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		GridBagConstraints constraints = new GridBagConstraints();
		getContentPane().setLayout(new GridBagLayout());
		
		/*
		 * setting up game background and 
		 * keyboard controls
		 */
		gamePanel.setLayout(new GridBagLayout());
		gamePanel.setPreferredSize(new Dimension(1000, 563));
		gamePanel.setBackground(Color.BLACK);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(gamePanel, constraints);
		gamePanel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_1) {
					leftPaddleUp = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_2) {
					leftPaddleDown = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					rightPaddleUp = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rightPaddleDown = true;
				}
			}
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_1) {
					leftPaddleUp = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_2) {
					leftPaddleDown = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					rightPaddleUp = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rightPaddleDown = false;
				}
			}
		});
		
		// assigning the colors, speed, and sound
		this.leftPaddleColor = leftPaddleColor;
		this.rightPaddleColor = rightPaddleColor;
		this.ballColor = ballColor;
		this.isSound = isSound;
		
		
		// creating a panel to hold game scores
		constraints = new GridBagConstraints();
		gameScorePanel.setLayout(new GridBagLayout());
		gameScorePanel.setBorder(BorderFactory.createTitledBorder("Game Scores"));
		constraints.gridx = 0;
		constraints.gridy = 0;
		getContentPane().add(gameScorePanel, constraints);
		
		// label to display left paddle's score
		leftScoreLabelTop.setText("Left Paddle's Score          ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		gameScorePanel.add(leftScoreLabelTop, constraints);
		
		// label to display score
		leftScoreLabelBottom.setText(String.valueOf(leftScore));
		constraints.gridx = 0;
		constraints.gridy = 1;
		gameScorePanel.add(leftScoreLabelBottom, constraints);
		
		// label to display right paddle's score
		rightScoreLabelTop.setText("          Right Paddle's Score");
		constraints.gridx = 1;
		constraints.gridy = 0;
		gameScorePanel.add(rightScoreLabelTop, constraints);
		
		// label to display score
		rightScoreLabelBottom.setText(String.valueOf(rightScore));
		constraints.gridx = 1;
		constraints.gridy = 1;
		gameScorePanel.add(rightScoreLabelBottom, constraints);
		
		
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5 * (screenSize.getWidth() - getWidth())), 
			(int) (0.5 * (screenSize.getHeight() - getHeight())), 
			getWidth(), getHeight());
		
		// setting the paddles in the middle of the y
		leftPaddleY = (int) (0.5 * (gamePanel.getHeight() - LEFTPADDLEH));
		rightPaddleY = (int) (0.5 * (gamePanel.getHeight() - RIGHTPADDLEH));
		
		// initializing the paddles on opposite sides of the screen
		leftPaddle = new Rectangle2D.Double(0, leftPaddleY, LEFTPADDLEW, LEFTPADDLEH);
		rightPaddle = new Rectangle2D.Double(955, rightPaddleY, RIGHTPADDLEW, RIGHTPADDLEH);
		
		// initializing the ball in the center of the game
		ballX = (int) (0.5 * (gamePanel.getWidth() - BALLW));
		ballY = (int) (0.5 * (gamePanel.getHeight() - BALLH));
		ball = new Rectangle2D.Double(ballX, ballY, BALLW, BALLH);
		
		// initializing the border in the center of the game
		middleBorderX = (int) (0.5*(gamePanel.getWidth() - 5));
		middleBorderY = 0;
		middleBorder = new Rectangle2D.Double(middleBorderX, middleBorderY, 5, 563);
		
		gamePanel.requestFocus();
		timer = new Timer(gameSpeed, taskPerformer);
		reset();
		timer.start();
	}
	// exits the application
	private void exitForm(WindowEvent e) {
		System.exit(0);
	}
	// refreshes the screen/panel
	private void drawPanel() {
		gamePanel.repaint();
	}
	// timer that refreshes, moves the ball, left paddle, and right paddle
	private ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			drawPanel();
			moveBall();
			moveLeftPaddle();
			moveRightPaddle();
			ball.setFrame(ballX, ballY, BALLW, BALLH);
			reset();
		}
	};
	// moves the left paddle
	private void moveLeftPaddle() {
		if (leftPaddleUp && leftPaddleY > 0) {
			leftPaddleY-= 2;
			leftPaddle.setFrame(0, leftPaddleY, LEFTPADDLEW, LEFTPADDLEH);
		} else if (leftPaddleDown && leftPaddleY < 413) {
			leftPaddleY+= 2;
			leftPaddle.setFrame(0, leftPaddleY, LEFTPADDLEW, LEFTPADDLEH);
		}
	}
	// moves the right paddle
	private void moveRightPaddle() {
		if (rightPaddleUp && rightPaddleY > 0) {
			rightPaddleY -= 2;
			rightPaddle.setFrame(955, rightPaddleY, RIGHTPADDLEW, RIGHTPADDLEH);
		} else if (rightPaddleDown && rightPaddleY < 413) {
			rightPaddleY += 2;
			rightPaddle.setFrame(955, rightPaddleY, RIGHTPADDLEW, RIGHTPADDLEH);
		}
	}
	// moves the ball
	private void moveBall() {
		if (checkLeftPaddleCollision()) {
			playSound(sound);
			movingRight = true;
			ballX+=3;
			if (movingUp) {
				ballY-=3;
			} else {
				ballY+=3;
			}
		} else if(checkRightPaddleCollision()){
			playSound(sound);
			movingRight = false;
			ballX-=3;
			if (movingUp) {
				ballY-=3;
			} else {
				ballY+=3;
			}
		} else if (ballX < 1) {
			playSound(sound);
			movingRight = true;
			timer.stop();
			rightScore++;
			rightScoreLabelBottom.setText(String.valueOf(rightScore));
		} else if (ballY < 1) {
			playSound(sound);
			movingUp = false;
			ballY+=3;
		} else if (ballX >= 970) {
			playSound(sound);
			movingRight = false;
			timer.stop();
			leftScore++;
			leftScoreLabelBottom.setText(String.valueOf(leftScore));
		} else if (ballY >= 523) {
			playSound(sound);
			movingUp = true;
			ballY-=3;
		} else if(movingUp && movingRight) {
			ballX+=3;
			ballY-=3;
		} else if(movingRight) {
			ballX+=3;
			ballY+=3;
		} else if(movingUp) {
			ballX-=3;
			ballY-=3;
		} else {
			ballX-=3;
			ballY+=3;
		}
	}
	// checks if the ball collided with the right paddle
	private boolean checkRightPaddleCollision() {
		Rectangle2D.Double collisionRect = (Rectangle2D.Double) rightPaddle.createIntersection(ball);
		return !collisionRect.isEmpty();
	}
	// checks if the ball collided with the left paddle
	private boolean checkLeftPaddleCollision() {
		Rectangle2D.Double collisionRect = (Rectangle2D.Double) leftPaddle.createIntersection(ball);
		return !collisionRect.isEmpty();
	}
	// resets the game if the timer is stopped
	private void reset() {
		if(!timer.isRunning()) {
			ballX = (int) (0.5 * (gamePanel.getWidth() - BALLW));
			ballY = (int) (0.5 * (gamePanel.getHeight() - BALLH));
			ball = new Rectangle2D.Double(ballX, ballY, BALLW, BALLH);
			
			leftPaddleY = (int) (0.5 * (gamePanel.getHeight() - LEFTPADDLEH));
			rightPaddleY = (int) (0.5 * (gamePanel.getHeight() - RIGHTPADDLEH));
			leftPaddle = new Rectangle2D.Double(0, leftPaddleY, LEFTPADDLEW, LEFTPADDLEH);
			rightPaddle = new Rectangle2D.Double(955, rightPaddleY, RIGHTPADDLEW, RIGHTPADDLEH);
			
			timer.setInitialDelay(2000);
			timer.start();
		}
	}
	// plays sound
	private void playSound(File Sound) {
		if (isSound) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(Sound));
				clip.start();
			} catch (Exception e) {
				
			}
		}
	}
	// class that paints the paddles and ball and border
	class GraphicsPanel extends JPanel {
		public GraphicsPanel() {
		}
		public void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			super.paintComponent(g2D);
			g2D.setPaint(rightPaddleColor);
			g2D.fill(rightPaddle);
			g2D.draw(rightPaddle);
			g2D.setPaint(leftPaddleColor);
			g2D.fill(leftPaddle);
			g2D.draw(leftPaddle);
			g2D.setPaint(ballColor);
			g2D.fill(ball);
			g2D.draw(ball);
			g2D.setPaint(Color.WHITE);
			g2D.fill(middleBorder);
			g2D.draw(middleBorder);
			g2D.dispose();
		}
	}
}

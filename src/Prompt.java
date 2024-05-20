import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Prompt extends JFrame {
	JLabel gameTitle = new JLabel();
	
	JPanel promptPanel = new JPanel();
	
	JLabel leftPaddleColorLabel = new JLabel();
	JComboBox leftPaddleColorComboBox = new JComboBox();
	JPanel leftPaddleColorPanel = new JPanel();
	
	JLabel rightPaddleColorLabel = new JLabel();
	JComboBox rightPaddleColorComboBox = new JComboBox();
	JPanel rightPaddleColorPanel = new JPanel();
	
	JLabel ballColorLabel = new JLabel();
	JComboBox ballColorComboBox = new JComboBox();
	JPanel ballColorPanel = new JPanel();
	
	JLabel gameSpeedLabel = new JLabel();
	JPanel gameSpeedPanel = new JPanel();
	ButtonGroup gameSpeedGroup = new ButtonGroup();
	JRadioButton gameSpeed1 = new JRadioButton();
	JRadioButton gameSpeed2 = new JRadioButton();
	JRadioButton gameSpeed3 = new JRadioButton();
	
	JLabel soundLabel = new JLabel();
	JPanel soundPanel = new JPanel();
	ButtonGroup soundGroup = new ButtonGroup();
	JRadioButton soundOn = new JRadioButton();
	JRadioButton soundOff = new JRadioButton();
	
	JButton startButton = new JButton();
	
	JButton aboutButton = new JButton();
	
	Color[] color = {Color.WHITE, Color.RED, Color.BLUE, Color.CYAN, 
			Color.GREEN, Color.ORANGE, Color.GRAY};
	
	Color leftPaddleColor = Color.WHITE;
	Color rightPaddleColor = Color.WHITE;
	Color ballColor = Color.WHITE;
	boolean isSound = true;
	
	int gameSpeed = 5;
	public static void main(String[] args) {
		new Prompt().setVisible(true);
	}
	public Prompt() {
		setTitle("Pong Prompt");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Title of the game
		gameTitle.setText("PONG!");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = constraints.CENTER;
		getContentPane().add(gameTitle, constraints);
		
		// Panel that holds in all of the user inputs
		promptPanel.setLayout(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = constraints.CENTER;
		getContentPane().add(promptPanel, constraints);
		
		// left paddle color label
		leftPaddleColorLabel.setText("Left Paddle Color");
		constraints.gridx = 0;
		constraints.gridy = 0;
		promptPanel.add(leftPaddleColorLabel, constraints);
		
		// left paddle color selection
		leftPaddleColorComboBox.setPreferredSize(new Dimension(100, 25));
		leftPaddleColorComboBox.setBackground(Color.WHITE);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 0, 10);
		promptPanel.add(leftPaddleColorComboBox, constraints);
		leftPaddleColorComboBox.addItem("White");
		leftPaddleColorComboBox.addItem("Red");
		leftPaddleColorComboBox.addItem("Blue");
		leftPaddleColorComboBox.addItem("Cyan");
		leftPaddleColorComboBox.addItem("Green");
		leftPaddleColorComboBox.addItem("Orange");
		leftPaddleColorComboBox.addItem("Gray");
		leftPaddleColorComboBox.setSelectedItem(0);
		leftPaddleColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftPaddleColorComboBoxActionPerformed(e);
			}
		});
		// left paddle color panel preview
		leftPaddleColorPanel.setPreferredSize(new Dimension(25, 25));
		leftPaddleColorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		leftPaddleColorPanel.setBackground(Color.WHITE);
		constraints.gridx = 2;
		constraints.gridy = 0;
		promptPanel.add(leftPaddleColorPanel, constraints);
		
		// right paddle color label
		rightPaddleColorLabel.setText("Right Paddle Color");
		constraints.gridx = 0;
		constraints.gridy = 1;
		promptPanel.add(rightPaddleColorLabel, constraints);
		
		// right paddle color selection
		rightPaddleColorComboBox.setPreferredSize(new Dimension(100, 25));
		rightPaddleColorComboBox.setBackground(Color.WHITE);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 0, 10);
		promptPanel.add(rightPaddleColorComboBox, constraints);
		rightPaddleColorComboBox.addItem("White");
		rightPaddleColorComboBox.addItem("Red");
		rightPaddleColorComboBox.addItem("Blue");
		rightPaddleColorComboBox.addItem("Cyan");
		rightPaddleColorComboBox.addItem("Green");
		rightPaddleColorComboBox.addItem("Orange");
		rightPaddleColorComboBox.addItem("Gray");
		rightPaddleColorComboBox.setSelectedItem(0);
		rightPaddleColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPaddleColorComboBoxActionPerformed(e);
			}
		});
		// right paddle color panel preview
		rightPaddleColorPanel.setPreferredSize(new Dimension(25, 25));
		rightPaddleColorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		rightPaddleColorPanel.setBackground(Color.WHITE);
		constraints.gridx = 2;
		constraints.gridy = 1;
		promptPanel.add(rightPaddleColorPanel, constraints);
		
		// ball color label
		ballColorLabel.setText("Ball Color");
		constraints.gridx = 0;
		constraints.gridy = 2;
		promptPanel.add(ballColorLabel, constraints);
		
		// ball color selection
		ballColorComboBox.setPreferredSize(new Dimension(100, 25));
		ballColorComboBox.setBackground(Color.WHITE);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		promptPanel.add(ballColorComboBox, constraints);
		ballColorComboBox.addItem("White");
		ballColorComboBox.addItem("Red");
		ballColorComboBox.addItem("Blue");
		ballColorComboBox.addItem("Cyan");
		ballColorComboBox.addItem("Green");
		ballColorComboBox.addItem("Orange");
		ballColorComboBox.addItem("Gray");
		ballColorComboBox.setSelectedItem(0);
		ballColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ballColorComboBoxActionPerformed(e);
			}
		});
		// ball color panel preview
		ballColorPanel.setPreferredSize(new Dimension(25, 25));
		ballColorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		ballColorPanel.setBackground(Color.WHITE);
		constraints.gridx = 2;
		constraints.gridy = 2;
		promptPanel.add(ballColorPanel, constraints);
		
		// game speed label
		gameSpeedLabel.setText("Game Speed");
		constraints.gridx = 0;
		constraints.gridy = 3;
		promptPanel.add(gameSpeedLabel, constraints);
		
		// game speed selection panel
		gameSpeedPanel.setLayout(new GridBagLayout());
		constraints.gridx = 1;
		constraints.gridy = 3;
		promptPanel.add(gameSpeedPanel, constraints);
		
		// normal game speed
		gameSpeedGroup.add(gameSpeed1);
		gameSpeed1.setSelected(true);
		gameSpeed1.setText("Normal");
		constraints.gridx = 0;
		constraints.gridy = 0;
		gameSpeedPanel.add(gameSpeed1, constraints);
		gameSpeed1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSpeedActionPerformed(e);
			}
		});
		
		// double game speed
		gameSpeedGroup.add(gameSpeed2);
		gameSpeed2.setText("2X");
		constraints.gridx = 1;
		constraints.gridy = 0;
		gameSpeedPanel.add(gameSpeed2, constraints);
		gameSpeed2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSpeedActionPerformed(e);
			}
		});
		
		// triple game speed
		gameSpeedGroup.add(gameSpeed3);
		gameSpeed3.setText("3X");
		constraints.gridx = 2;
		constraints.gridy = 0;
		gameSpeedPanel.add(gameSpeed3, constraints);
		gameSpeed3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSpeedActionPerformed(e);
			}
		});
		// sound label
		soundLabel.setText("Sound");
		constraints.gridx = 0;
		constraints.gridy = 4;
		promptPanel.add(soundLabel, constraints);
		
		// sound panel for radio buttons
		soundPanel.setLayout(new GridBagLayout());
		constraints.gridx = 1;
		constraints.gridy = 4;
		promptPanel.add(soundPanel, constraints);
		
		// creating sound on radio button
		soundGroup.add(soundOn);
		soundOn.setText("On");
		soundOn.setSelected(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		soundPanel.add(soundOn, constraints);
		soundOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundButtonActionPerformed(e);
			}
		});
		
		// creating sound off radio button
		soundGroup.add(soundOff);
		soundOff.setText("Off");
		constraints.gridx = 1;
		constraints.gridy = 0;
		soundPanel.add(soundOff, constraints);
		soundOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundButtonActionPerformed(e);
			}
		});
		
		// button to start the game
		startButton.setText("Start Game");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = constraints.CENTER;
		getContentPane().add(startButton, constraints);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonActionPerformed(e);
			}
		});
		
		// button that explains the game
		aboutButton.setText("About");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = constraints.CENTER;
		getContentPane().add(aboutButton, constraints);
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutButtonActionPerformed(e);
			}
		});
		
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5 * (screenSize.getWidth() - getWidth())), 
			(int) (0.5 * (screenSize.getHeight() - getHeight())), 
			getWidth(), getHeight());
		gameSpeed1.doClick();
	}
	// creates a pong object
	private void startButtonActionPerformed(ActionEvent e) {
		new Pong(leftPaddleColor, rightPaddleColor, ballColor, 
			gameSpeed, isSound).setVisible(true);
	}
	// sets color of left paddle
	private void leftPaddleColorComboBoxActionPerformed(ActionEvent e) {
		leftPaddleColor = color[leftPaddleColorComboBox.getSelectedIndex()];
		leftPaddleColorPanel.setBackground(color[leftPaddleColorComboBox.getSelectedIndex()]);
	}
	// sets color of right paddle
	private void rightPaddleColorComboBoxActionPerformed(ActionEvent e) {
		rightPaddleColor = color[rightPaddleColorComboBox.getSelectedIndex()];
		rightPaddleColorPanel.setBackground(color[rightPaddleColorComboBox.getSelectedIndex()]);
	}
	// sets color of ball color
	private void ballColorComboBoxActionPerformed(ActionEvent e) {
		ballColor = color[ballColorComboBox.getSelectedIndex()];
		ballColorPanel.setBackground(color[ballColorComboBox.getSelectedIndex()]);
	}
	// sets game speed
	private void gameSpeedActionPerformed(ActionEvent e) {
		if (gameSpeed1.isSelected()) {
			gameSpeed = 5;
		} else if (gameSpeed2.isSelected()) {
			gameSpeed = 3;
		} else if (gameSpeed3.isSelected()){
			gameSpeed = 1;
		}
	}
	// sets whether sound is on
	private void soundButtonActionPerformed(ActionEvent e) {
		if (soundOn.isSelected()) {
			isSound = true;
		} else {
			isSound = false;
		}
	}
	// displays JOptionPane explaining the game
 	private void aboutButtonActionPerformed(ActionEvent e) {
		String message = "";
		message += "In order to move the left paddle, press 1 to go up and press 2 to go down";
		message += "\nIn order to move the right, press the left arrow key to go up and the right arrow key to go down";
		message += "\n\nAt the start of every game and every point the ball gets thrown at the person who scored\nafter two seconds";
		JOptionPane.showConfirmDialog(null, message, "Controls", JOptionPane.CLOSED_OPTION);
		
	}
 	// exits the application
	private void exitForm(WindowEvent e) {
		System.exit(0);
	}
}

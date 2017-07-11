package crank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.*;

public class Game extends JPanel{
	private static Player player;
	private static LinkedList en;
	private static UpgradedEnemies uen;
	private int[] playerPosition;
	private static int score;
	private static boolean gameOver;
	private static LinkedList bs;
	 public Game() throws InterruptedException {
		 score = 0;
		 gameOver = false;
		 player = new Player();
		 System.out.println("Initiating...");
		 JFrame frame = new JFrame("");
		 frame.setVisible(true);
		 frame.setSize(800, 500);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		 frame.add(this);
		 setBackground(Color.BLACK);
		 makePlayerBinds();
		 playerPosition = player.getPosition();
		 en = new LinkedList<Enemy>();
		 int startingEnemies = 8;
		 uen = new UpgradedEnemies();
		 for (int i = 0; i < startingEnemies; i ++) {
			 en.add(new Enemy());
		 }
		 double start = System.currentTimeMillis();
		 int counter = 0;
		 bs = new LinkedList<Bullets>();
		 while(true) {
			 Thread.sleep(10);
			 player.move();
			 for (int i = 0; i < en.size();i++) {
				 ((Enemy) en.get(i)).move();
			 }
			 for (int i = 0; i < en.size();i++) {
				 if (((Enemy) en.get(i)).getY() > 500) {
					 en.remove(i);
					 en.add(new Enemy());
				 }
			 }
			 if (uen.getY() > 500) {
				 uen = new UpgradedEnemies();
			 }
			 if (System.currentTimeMillis() - start > 1000 && uen.getY() < 300) {
				 if (!gameOver)
					 score++;
				 if (score%5 == 0) {
					 en.add(new Enemy());
				 }
				 playerPosition = player.getPosition();
				 bs.add(uen.shoot(playerPosition));
				 counter++;
				 if (counter > 2) {
					 counter = 0;
				 }
				 start = System.currentTimeMillis();
			 }
			 for (int i=0; i < bs.size(); i++) {
				 ((Bullets)bs.get(i)).move();
			 }
			 uen.move();
			 this.checkCollisions();
			 this.repaint();
		 }
	 }
	 public static Player getPlayer() {
		 return player;
	 }
	 public static void restart() {
		 player = new Player();
		 en = new LinkedList<Enemy>();
		 for (int i = 0; i < 8; i++) {
			 en.add(new Enemy());
		 }
		 uen = new UpgradedEnemies();
		 gameOver = false;
		 score = 0;
		 bs = new LinkedList<Bullets>();
	 }
	 private void makePlayerBinds() {
			InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			ActionMap am = getActionMap();
			
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RightArrow");
		am.put("RightArrow", new ActionHandler("RightArrow"));
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RightArrowRelease");
		am.put("RightArrowRelease", new ActionHandler("RightArrowRelease"));
			
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DownArrow");
		am.put("DownArrow", new ActionHandler("DownArrow"));
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "DownArrowRelease");
		am.put("DownArrowRelease", new ActionHandler("DownArrowRelease"));
			
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LeftArrow");
		am.put("LeftArrow", new ActionHandler("LeftArrow"));
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LeftArrowRelease");
		am.put("LeftArrowRelease", new ActionHandler("LeftArrowRelease"));
			
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UpArrow");
		am.put("UpArrow", new ActionHandler("UpArrow"));
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "UpArrowRelease");
		am.put("UpArrowRelease", new ActionHandler("UpArrowRelease"));
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false), "f5");
		am.put("f5", new ActionHandler("f5"));
	}
	 public void checkCollisions() {
		 for(int i = 0;i < en.size(); i++) {
			 if (((Enemy) en.get(i)).getHit().intersects(player.getHit())) {
				 gameOver = true;
			 }
		 }
		 if (uen.getHit().intersects(player.getHit())) {
			 gameOver = true;
		 }
		 for (int i = 0; i < bs.size(); i++) {
			 if (((Bullets)bs.get(i)).getHitbox().intersects(player.getHit())) {
				 gameOver = true;
			 }
		 }
	 }
	 public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g.create();
			RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHints(hints);
			g2d.setBackground(Color.BLACK);
			g2d.setColor(Color.GREEN);
			super.paintComponent(g);
			player.paintPlayer(g2d);
			g2d.setFont(new Font("DialogInput", Font.BOLD, 30));
			g2d.setColor(Color.WHITE);
			g2d.drawString(Integer.toString(score), 8, 22);
			for (int i = 0; i < en.size(); i++) {
				((Enemy) en.get(i)).paintEnemy(g2d);
			}
			uen.paintEnemy(g2d);
			for (int i=0; i < bs.size(); i++) {
				 ((Bullets)bs.get(i)).paintBullet(g2d);
			 }
			if (gameOver) {
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("DialogInput", Font.BOLD, 80));
				g2d.drawString("GAME OVER", 180, 250);
				g2d.setFont(new Font("DialogInput", Font.BOLD, 40));
				g2d.drawString("Press F5 to Restart",168,300);
		}
	}
}

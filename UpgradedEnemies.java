package crank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class UpgradedEnemies {
	private double xc;
	private double yc;
	private int moveSpeed;
	private Rectangle hitbox;
	public UpgradedEnemies() {
		Random rand = new Random();
		xc = rand.nextDouble() * 800;
		yc = rand.nextDouble() * 100 - 200;
		moveSpeed = 2;
		hitbox = new Rectangle((int)xc,(int)yc,20,20);
	}
	public Rectangle getHit() {
		hitbox = new Rectangle((int)xc,(int)yc,20,20);
		return hitbox;
	}
	public int getY() {
		return (int)yc;
	}
	public void paintEnemy(Graphics2D g2d) {
		g2d.setColor(Color.YELLOW);
		g2d.fillRect((int)xc, (int)yc, 20, 20);
	}
	public void move() {
		yc += (int)moveSpeed;
	}
	public Bullets shoot(int[] playerPosition) {
		
		float xSpeed = -(int)((xc - (playerPosition[0] + 25)) / 100);
		float ySpeed = -(int)((yc - (playerPosition[1] + 25)) / 100);
		return new Bullets((int)xc,(int)yc,xSpeed, ySpeed);
	}
}

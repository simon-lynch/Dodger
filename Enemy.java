package crank;

import java.awt.*;
import java.util.Random;

public class Enemy {
	private double xc;
	private double yc;
	private int moveSpeed;
	private Rectangle hitbox;
	public Enemy() {
		Random rand = new Random();
		xc = rand.nextDouble() * 800;
		yc = rand.nextDouble() * 100 - 200;
		moveSpeed = (int) (rand.nextDouble() * 4 + 3);
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
		g2d.setColor(Color.RED);
		g2d.fillRect((int)xc, (int)yc, 20, 20);
	}
	public void move() {
		yc += (int)moveSpeed;
	}
}
package crank;

import java.awt.*;

public class Player {
	private int xc;
	private int yc;
	private int xpitch;
	private int moveSpeed;
	private Rectangle hitbox;
	private boolean exploded;
	public Player() {
		exploded = false;
		xc = 380;
		yc = 420;
		xpitch = 0;
		moveSpeed = 4;
		hitbox = new Rectangle(xc,yc,50,50);
	}
	public Rectangle getHit() {
		hitbox = new Rectangle(xc + 5,yc + 5,40,40);
		return hitbox;
	}
	public void paintPlayer(Graphics2D g2d) {
		if (!exploded)
			g2d.fillOval(xc,yc, 50, 50);

	}
	public int[] getPosition() {
		int[] position = {xc,yc};
		return position;
	}
	public void moveHoz(int factor) {
		factor *= moveSpeed;
		xpitch = factor;
	}
	public void move() {
		if ((xc + xpitch) > (800 - 53) || (xc + xpitch) < 0) {
			xpitch = 0;
		}
		xc += xpitch;
	}

}

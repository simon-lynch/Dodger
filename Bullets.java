package crank;
import java.awt.*;
public class Bullets {
	private int xc;
	private int yc;
	private float xspeed;
	private float yspeed;
	private Rectangle hitbox;
	public Bullets(int x, int y, float xv, float yv) {
		xc = x;
		yc = y;
		xspeed = xv;
		yspeed = yv;
	}
	public Rectangle getHitbox() {
		hitbox = new Rectangle(xc,yc,5,5);
		return hitbox;
	}
	public void move() {
		xc += xspeed;
		yc += yspeed;
	}
	public void paintBullet(Graphics g2d) {
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(xc, yc, 5, 5);
	}
}

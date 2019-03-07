package Game.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.BaseEntity;
import Game.Entities.Dynamics.Player;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class CaveBlockerEntity extends BaseStaticEntity {
	
	private Rectangle collision;
	private Animation blocker;
	private int width, height;
	
	public CaveBlockerEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, xPosition);
		width = 100;
		height = 100;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		blocker = new Animation(200, Images.caveBlocker);
		collision = new Rectangle();
	}
	
	@Override
	public void tick() {
		blocker.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(blocker.getCurrentFrame(), (int)(handler.getXInWorldDisplacement() + xPosition),(int)( handler.getYInWorldDisplacement() + yPosition), width, height, null);
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 35), (int)(handler.getYDisplacement() + yPosition + 50), width/4, height/2);
	}

	@Override
	public Rectangle getCollision() {
		return collision;
	}
	
	@Override
	public double getXOffset() {
		return xPosition;
	}
	

	@Override
	public double getYOffset() {
		return yPosition;
	}
	
	

	
}

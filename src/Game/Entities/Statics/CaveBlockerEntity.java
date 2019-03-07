package Game.Entities.Statics;


import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Resources.Images;

public class CaveBlockerEntity extends BaseStaticEntity{

	Rectangle collision;
	int width, height;
	
	public CaveBlockerEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		
		width = 50;
		height = 50;
		
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		collision = new Rectangle();
	}
	
	public void render(Graphics g) {
		g.drawImage(Images.caveBlocker, (int)(handler.getXDisplacement() + xPosition + 1060),(int)( handler.getYDisplacement() + yPosition - 540), width, height, null);
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 35 + 1060), (int)(handler.getYDisplacement() + yPosition - 540 + 50), width/4, height/2);
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
	
	@Override
	public double getXOffset() {
		return xPosition;
	}
	
	

}

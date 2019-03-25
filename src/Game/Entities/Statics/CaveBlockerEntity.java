package Game.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.World.WorldManager;
import Main.Handler;
import Resources.Images;

public class CaveBlockerEntity extends BaseStaticEntity{

	public static Rectangle collision;
	public static boolean itExists = true;
	int width, height;

	public CaveBlockerEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);

		width = 50;
		height = 50;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		collision = new Rectangle();
	}
	@Override
	public void tick() {
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 1060), 
				(int)(handler.getYDisplacement() + yPosition - 540), width, height);

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(!WorldManager.skillUnlocked) {
			g.drawImage(Images.caveBlocker, (int)(handler.getXDisplacement() + xPosition + 1060),
					(int)( handler.getYDisplacement() + yPosition - 540), width, height, null);		
		}


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

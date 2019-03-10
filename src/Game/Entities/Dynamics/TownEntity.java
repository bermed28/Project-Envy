package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TownEntity extends BaseDynamicEntity {

	private Rectangle collision;
	private Animation townEntity;
	private int width, height;
	private static final int DEFAULT_WIDTH = 70, DEFAULT_HEIGHT = 150;


	public TownEntity(Handler handler, int xPosition, int yPosition, BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition, animFrames);

		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;

		this.setYOffset(yPosition);
		this.setXOffset(xPosition);

		animFrames = Images.townEntity;
		townEntity = new Animation(200, animFrames);

		collision = new Rectangle();
	}

	@Override
	public void tick() {
		townEntity.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(townEntity.getCurrentFrame(),(int)(handler.getXInWorldDisplacement() + xPosition + 900),(int)( handler.getYInWorldDisplacement() + yPosition - 400),this.width,this.height,null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	@Override
	public Rectangle getCollision() {
		return collision;
	}

}

	


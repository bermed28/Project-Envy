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
		super.render(g);

		collision = new Rectangle((int)(handler.getXInWorldDisplacement() + xPosition + 780),
				(int)( handler.getYInWorldDisplacement() + yPosition - 350),this.width + 85,this.height  + 25);


		g.drawImage(townEntity.getCurrentFrame(),(int)(handler.getXInWorldDisplacement() + xPosition + 900),
				(int)( handler.getYInWorldDisplacement() + yPosition - 400),this.width * -1,this.height,null);


		if (collision.intersects(handler.getEntityManager().getPlayer().getCollision())) {
			g.setColor(Color.black);
			g.setFont(new Font("IMPACT", Font.BOLD, 25));
			g.drawString("Press 'E' to Interact", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
                    (int) (handler.getYInWorldDisplacement() +yPosition - 200));
			if(handler.getKeyManager().questKey) {
				handler.getKeyManager().questKey = !handler.getKeyManager().questKey;
				g.drawString("Key Pressed!", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
                        (int) (handler.getYInWorldDisplacement() +yPosition - 400));
			}
		}
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

	


package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TownEntity extends BaseDynamicEntity {

	public Animation PEnemyIdle;
	public BufferedImage[] frames;
	Rectangle townEntity;

	int speed = 2;
	protected boolean isMoving;


	//Where the player will stand
	protected Rectangle nextArea;
	public String facing = "Down";

	public TownEntity(Handler handler, int xPosition, int yPosition,BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition, animFrames);
		nextArea = new Rectangle();
		PEnemyIdle = new Animation(120, animFrames);
		frames = animFrames;
		
		this.setXOffset(xPosition);
        this.setYOffset(yPosition);
        townEntity = new Rectangle();

	}

	// if it moves and stuff then what methods should it have? 


	// OTHER FUNCTIONALITIES THAT A DYNAMIC ENTITY SHOULD HAVE?


	public void tick(){
		if(!PEnemyIdle.getCurrentFrame().equals(null)) {
			PEnemyIdle.tick();
		}
	}

	@Override
	public void render(Graphics g) {
        super.render(g);

        Graphics2D g2 = (Graphics2D) g;


        if(handler.getArea().equals(this.nextArea)) {
            if (!Player.checkInWorld) {
                townEntity = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
                        (int) (handler.getYDisplacement() + getYOffset()), 45, 45);

            } else {
                townEntity = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
                        (int) (handler.getYInWorldDisplacement() + getYOffset()), 70, 70);

            }

            g2.setColor(Color.black);

            g.drawImage(Images.townEntity,townEntity.x,townEntity.y,townEntity.width,townEntity.height,null);

            if (townEntity.intersects(handler.getEntityManager().getPlayer().getCollision())) {
                handler.getEntityManager().getPlayer().facing = "Left";
            }
        }


    }




/*		public BufferedImage getIdle(){
			if(!PEnemyIdle.getCurrentFrame().equals(null)) {
				return PEnemyIdle.getCurrentFrame();
			}else{
				return Images.PEnemyIdle[0];
			}
		} */

		public BufferedImage getCurrentAnimationFrame( Animation animDown, Animation animUp, Animation animLeft, Animation animRight, BufferedImage[] front,BufferedImage[] back,BufferedImage[] left,BufferedImage[] right) {
			BufferedImage frame = null;
			if(isMoving) {
				switch (facing) {
				case "Down":
					frame =  animDown.getCurrentFrame();
					break;
				case "Up":
					frame =  animUp.getCurrentFrame();
					break;
				case "Right":
					frame = animRight.getCurrentFrame();
					break;
				case "Left":
					frame = animLeft.getCurrentFrame();
					break;
				}
			}
			else {
				switch (facing) {
				case "Down":
					frame =  front[0];
					break;
				case "Up":
					frame =  back[0];
					break;
				case "Right":
					frame = right[0];
					break;
				case "Left":
					frame = left[0];
					break;
				}
			}
			return frame;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}
	}

package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;
import Game.World.InWorldAreas.TownArea;
import Game.GameStates.FightState;
import Game.GameStates.InWorldState;
import Game.GameStates.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TownEntity extends BaseDynamicEntity {
	

	private Rectangle townEntity;
	int width, height;
	
	private Animation anim;
	private int animWalkingSpeed = 150;
	
	public TownEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, yPosition, yPosition, null);

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		anim = new Animation(animWalkingSpeed, Images.townEntity);
		
		speed = 1;
		townEntity = new Rectangle();

	}
	
	@Override
	public void tick() { 
		
		if(Player.isinArea) {super.tick();}
		 
		
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(Player.isinArea) {
            townEntity = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
                    (int) (handler.getYDisplacement() + getYOffset()), 45, 45);

        } else {
            townEntity = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
                    (int) (handler.getYInWorldDisplacement() + getYOffset()), 70, 70);

        }
		
        g.drawImage(anim.getCurrentFrame(),townEntity.x,townEntity.y,townEntity.width,townEntity.height,null);
        
	}

}

	


package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Game.World.WorldManager;
import Game.World.InWorldAreas.TownArea;




public class TownEntity extends BaseDynamicEntity {

	private Rectangle collision;

	private Animation townEntity;
	public static boolean quest = false;
	public static boolean postBossQuest = false;
	private static final int DEFAULT_WIDTH = 70, DEFAULT_HEIGHT = 150;

	private String[] questDialogue = {"I am a shadow, the true self.", "To search for the truth,",
			"you need to face your own Shadow.", "There's another island, where a beast awaits you.", 
			"If you manage to face this beast and conquer your fears,", " you will be bestowed upon a great power as a reward. "
					+ "Come back once you have completed this task."};
	private String[] skillUnlock = {"You really are strong", "You have proven to be able to face your own fears", 
			"I reward you the power to wield the element of ice", "To fully unlock your true self", 
	"You must go to a cave nearby to face one final challenge with your new power"};
	private int width, height;

	private JFrame textFrame;
	private JPanel textPanel;
	private JLabel wizardInstructions;
	private JLabel wizardSkill;


	Color backgroundColor = new Color(152,200,120);

	public static boolean interaction = false;

	private JFrame textFrame1;

	private JPanel textPanel1;



	public static boolean postBossInteraction = false;

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

		if(interaction == true) {
			textQuest();
			interaction = false;
		} 

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		if(TownArea.isInTown) {
			collision = new Rectangle((int)(handler.getXInWorldDisplacement() + xPosition + 790),
					(int)( handler.getYInWorldDisplacement() + yPosition - 400),this.width + 125,this.height  + 65);

			g.drawImage(townEntity.getCurrentFrame(),(int)(handler.getXInWorldDisplacement() + xPosition + 850),
					(int)(handler.getYInWorldDisplacement() + yPosition - 400),this.width,this.height,null);
			if(collision.intersects(handler.getEntityManager().getPlayer().getCollision()) && !handler.getKeyManager().attbut && !quest && !Boss1.isDead){
				g.setColor(Color.black);
				g.setFont(new Font("IMPACT", Font.BOLD, 25));
				g.drawString("The Wizard has a message for you. Press 'E'", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
						(int) (handler.getYInWorldDisplacement() +yPosition - 200));

			} 
			if(collision.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().attbut == true && !quest && !Boss1.isDead) {
				interaction = true;
				quest = true;

			}
			if(collision.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().attbut == true && Boss1.isDead && !postBossInteraction) {
				g.setColor(Color.black);
				g.setFont(new Font("IMPACT", Font.BOLD, 25));
				g.drawString("You really are strong, your new power is the ability to wield Ice", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
						(int) (handler.getYInWorldDisplacement() +yPosition - 200));
				WorldManager.skillUnlocked = true;
				handler.getEntityManager().getPlayer().setSkill("Freeze");
				

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

	public void textQuest() {
		textFrame = new JFrame();
		textFrame.setIconImage(Images.townEntity[0]);
		textFrame.setTitle("Unknown Wizard");
		textFrame.setVisible(true);
		textFrame.setLocation(350,500);
		textFrame.setSize(750,200);
		textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textPanel = new JPanel();
		textPanel.setBackground(backgroundColor);

		for (int i = 0; i <= 5; i++) {
			wizardInstructions = new JLabel(questDialogue[i]);
			wizardInstructions.setFont(new Font("IMPACT",Font.ITALIC,15));
			textPanel.add(wizardInstructions);
		}
		textFrame.add(textPanel); 
	}

}




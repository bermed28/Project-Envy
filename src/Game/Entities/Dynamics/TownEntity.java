package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Game.World.InWorldAreas.TownArea;




public class TownEntity extends BaseDynamicEntity {

	private Rectangle collision;

	private Animation townEntity;
	public boolean quest = false;
	public boolean postBossQuest;
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

	private int count = 0;

	Color backgroundColor = new Color(152,200,120);

	private boolean interaction;

	private JFrame textFrame1;

	private JPanel textPanel1;

	private boolean postBossInteraction;

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
		if(interaction) {
			if(handler.getKeyManager().attbut) {
				quest = true;
				textQuest();
				interaction = false;
			} 
		} else {
			if(postBossInteraction) {
				if(handler.getKeyManager().attbut) {
					if(Boss1.isDead) {
						postBossQuest = true;
						textSkill();
						postBossInteraction = false;
						return;

					}
				}
			}
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

			if(collision.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getEntityManager().getPlayer().getLvl() < 2){
				if(!quest) {
					g.setColor(Color.black);
					g.setFont(new Font("IMPACT", Font.BOLD, 25));
					g.drawString("The Wizard has a message for you. Press 'E'", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
							(int) (handler.getYInWorldDisplacement() +yPosition - 200));
					interaction = true;
				}
			} else if(Boss1.isDead) {
				if(collision.intersects(handler.getEntityManager().getPlayer().getCollision())) {
					if(!postBossQuest) {
						g.setColor(Color.black);
						g.setFont(new Font("IMPACT", Font.BOLD, 25));
						g.drawString("The Wizard has another message for you. Press 'E'", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
								(int) (handler.getYInWorldDisplacement() +yPosition - 200));
						postBossInteraction = true;
					}
				} 
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
			wizardInstructions = new JLabel(questDialogue[count]);
			wizardInstructions.setFont(new Font("IMPACT",Font.ITALIC,15));
			textPanel.add(wizardInstructions);
			count++;
		}
		textFrame.add(textPanel); 
	}

	public void textSkill() {
		handler.getEntityManager().getPlayer().setSkill("Freeze");
		textFrame1 = new JFrame();
		textFrame1.setIconImage(Images.townEntity[0]);
		textFrame1.setTitle("Unknown Wizard");
		textFrame1.setVisible(true);
		textFrame1.setLocation(350,500);
		textFrame1.setSize(750,200);
		textFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textPanel1 = new JPanel();
		textPanel1.setBackground(backgroundColor);

		for (int i = 0; i <= 4; i++) {
			wizardSkill = new JLabel(skillUnlock[i]);
			wizardSkill.setFont(new Font("IMPACT",Font.ITALIC,15));
			textPanel1.add(wizardSkill);
		}
		textFrame1.add(textPanel1); 
	}
}




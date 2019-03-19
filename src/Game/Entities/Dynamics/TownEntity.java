package Game.Entities.Dynamics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;




public class TownEntity extends BaseDynamicEntity {

	private Rectangle collision;
	private Animation townEntity;
	public boolean quest = false;
	
	private static final int DEFAULT_WIDTH = 70, DEFAULT_HEIGHT = 150;
	
	private String[] questDialogue = {"I am a shadow, the true self.", "To search for the truth,",
	"you need to face your own Shadow.", "There's another island, where a beast awaits you.", 
	"If you manage to face this beast and conquer your fears,", " you will be bestowed upon a great power as a reward. "
			+ "Come back once you have completed this task."};
	private int width, height;
	
	private JFrame textFrame;
	private JPanel textPanel;
	private JLabel wizardInstructions;
	
	private int count = 0;
	
	Color backgroundColor = new Color(152,200,120);
	
	private boolean interaction;
	
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
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		collision = new Rectangle((int)(handler.getXInWorldDisplacement() + xPosition + 790),
				(int)( handler.getYInWorldDisplacement() + yPosition - 400),this.width + 125,this.height  + 65);


		g.drawImage(townEntity.getCurrentFrame(),(int)(handler.getXInWorldDisplacement() + xPosition + 850),
				(int)(handler.getYInWorldDisplacement() + yPosition - 400),this.width,this.height,null);

		if(collision.intersects(handler.getEntityManager().getPlayer().getCollision())) {
			if(!quest) {
				g.setColor(Color.black);
				g.setFont(new Font("IMPACT", Font.BOLD, 25));
				g.drawString("The Wizard has a message for you. Press 'E'", (int) (handler.getXInWorldDisplacement() + xPosition + 780),
                    (int) (handler.getYInWorldDisplacement() +yPosition - 200));
				interaction = true;
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
	


}

	


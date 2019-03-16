package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.Player;
import Main.Handler;
import Resources.Images;

public class CaveBlockerEntity extends BaseStaticEntity{

	public static Rectangle collision;
	private Player player;
	public static boolean itExists = true;
	private boolean skillUnlockQuest = false;
	private String[] blockText = {"To search for the truth", "you must accquire a power unknown to mankind.", 
			"Search upon this land a wizard that will help you along your quest."};
	private int count = 0;
	int width, height;
	private JFrame textFrame;
	private JLabel caveBlockIntructions;
	private boolean interaction;
	
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
		if(interaction) {
			if(handler.getKeyManager().attbut) {
				skillUnlockQuest = true;
				textQuest();	
			} 
		}
		interaction = false;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		
	
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 1060), 
				(int)(handler.getYDisplacement() + yPosition - 530), width, height + 45);
		
		g.drawImage(Images.caveBlocker, (int)(handler.getXDisplacement() + xPosition + 1060),
				(int)( handler.getYDisplacement() + yPosition - 540), width, height, null);
		
		g.drawRect((int)(handler.getXDisplacement() + xPosition + 1060), 
				(int)(handler.getYDisplacement() + yPosition - 530), width, height + 45);
		
		if(collision.intersects(handler.getEntityManager().getPlayer().getCollision())) {
			if(!skillUnlockQuest) {
				g.setColor(Color.black);
				g.setFont(new Font("IMPACT", Font.BOLD, 25));
				g.drawString("The Guard has a message for you. Press 'E'", (int) (handler.getXDisplacement() + xPosition + 1060),
                    (int) (handler.getYDisplacement() + yPosition - 640));
				interaction = true;
			} 
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
	public void textQuest() {
		textFrame = new JFrame();
		textFrame.setIconImage(Images.caveBlocker);
		textFrame.setTitle("Guard");
		textFrame.setVisible(true);
		textFrame.setLocation(350,500);
		textFrame.setSize(700,200);
		textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.CYAN);
		for (int i = 0; i <= 2; i++) {
			caveBlockIntructions = new JLabel(blockText[count]);
			caveBlockIntructions.setFont(new Font("IMPACT",Font.ITALIC,15));
			textPanel.add(caveBlockIntructions);
			count++;
		}
		textFrame.add(textPanel);

	}
	


}

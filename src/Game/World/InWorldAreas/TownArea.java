package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;

import Game.World.Walls;

public class TownArea extends BaseArea{
	Rectangle exit;
	Rectangle playerRect;
	
	public static boolean isInTown = false;
	
	private int imageWidth = 6080, imageHeight = 4480;
	public final static int playerXSpawn = 70, playerYSpawn = -1700;
	
	private Rectangle background = new Rectangle(3000,3000);
	
	public static ArrayList<InWorldWalls> townWalls;
	Color backgroundColor = new Color(152,200,120);
	
	public TownArea(Handler handler, EntityManager entityManager) {
		super(handler, entityManager);
		name = "Town";
		handler.setXInWorldDisplacement(playerXSpawn);
		handler.setYInWorldDisplacement(playerYSpawn);
		
        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);
        
        this.entityManager = entityManager;
        
        townWalls = new ArrayList<>();
        AddWalls();
	}
	
	public void tick() {
        super.tick();

        for (Walls w : townWalls) {
            w.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(backgroundColor);
        g2.fill(background);

        g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if(GameSetUp.DEBUGMODE) {
            for (Walls w : townWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.black);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }

        entityManager.render(g);
    }


	private void AddWalls() {
		townWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 15,2025, 100, 300, "Exit"));	// Exit at Start
      						

		
	}
	
}

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
		townWalls.add(new InWorldWalls(handler, 0, 1886, 1850, 15, "Wall"));
		townWalls.add(new InWorldWalls(handler, 1810, 0, 23, 1749, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 1730, 1740, 12, 82, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 1830, 1600, 75, 104, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 1890, 990, 350, 104, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 2300, 790, 10, 770, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 2150, 1340, 85, 234, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 2300, 1580, 1050, 15, "Wall"));
		townWalls.add(new InWorldWalls(handler, 3400, 790, 10, 770, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 3350, 1600, 1100, 105, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4250, 1970, 105, 204, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4390, 1360, 850,850 , "Wall"));	
		townWalls.add(new InWorldWalls(handler, 0, 4000, imageWidth, 10, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 5250, 0, 10, imageHeight, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4800, 3070, 450, 15, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4750, 3070, 10, 150, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4550, 3210, 250, 15, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4550, 3210, 15, 550, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4250, 3710, 310, 15, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4210, 3710, 10, 250, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 4052, 3860, 150, 250, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 1550, 3390, 910, 800, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 0, 2440, 1450, 720, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 1670, 2230, 150, 650, "Wall"));	//Middle of house
		townWalls.add(new InWorldWalls(handler, 1420, 2303,350, 580, "Wall"));	//Left of house
		townWalls.add(new InWorldWalls(handler, 1820, 2303, 320, 580, "Wall"));	//Right of house
		townWalls.add(new InWorldWalls(handler, 2110, 2880, 150,150, "Wall")); //*Light
		townWalls.add(new InWorldWalls(handler, 2180, 2680, 55,350, "Wall")); //**Post
		townWalls.add(new InWorldWalls(handler, 2180, 2590, 55,150, "Wall")); //**next to house**//	
		townWalls.add(new InWorldWalls(handler, 670, 3190, 15, 550, "Wall"));
		townWalls.add(new InWorldWalls(handler, 670, 3710, 950, 15, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 2770, 2830, 1300, 530, "Wall"));
		townWalls.add(new InWorldWalls(handler, 4010, 3210, 150, 150, "Wall"));
		townWalls.add(new InWorldWalls(handler, 4110, 3010, 15, 350, "Wall"));
		townWalls.add(new InWorldWalls(handler, 4070, 2890, 100, 150, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 3570, 2720, 250, 150, "Wall"));	//Middle House 2 
		townWalls.add(new InWorldWalls(handler, 3370, 2760, 450, 150, "Wall"));	//Left House 2
		townWalls.add(new InWorldWalls(handler, 3770, 2760, 250, 100, "Wall"));	//Right House 2
		townWalls.add(new InWorldWalls(handler, 2780, 2220, 370, 350, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 3150, 2220, 350, 150, "Wall"));	
		townWalls.add(new InWorldWalls(handler, 15,2025, 100, 300, "Exit"));	// Exit at Start
      						

		
	}
	
}

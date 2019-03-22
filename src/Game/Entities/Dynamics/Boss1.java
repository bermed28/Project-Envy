package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.GameStates.FightState;
import Game.GameStates.State;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

public class Boss1 extends BaseHostileEntity implements Fighter {

	Rectangle boss1;
	public static boolean isDead;
	
	public Boss1(Handler handler, int xPosition, int yPosition, String state, String name, String area,
			BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition, state, name, area, animFrames);
		type = "Boss1";
		this.foundState = state;
		boss1 = new Rectangle();
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
	}

	
	@Override
	public void tick() {
		if(Player.isinArea) {
			super.tick();
		}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (GameSetUp.DEBUGMODE) {
			g2.draw(nextArea);
			g2.draw(boss1);
		}
		
		if(handler.getArea().equals(this.Area)) {
            if (!Player.checkInWorld) {
                boss1 = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
                        (int) (handler.getYDisplacement() + getYOffset()), 60, 90);

            } else {
                boss1 = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
                        (int) (handler.getYInWorldDisplacement() + getYOffset()), 60, 90);

            }

            g2.setColor(Color.black);

            g.drawImage(Images.boss1OverWorld[0],boss1.x,boss1.y,60,90,null);

            if (boss1.intersects(handler.getEntityManager().getPlayer().getCollision())) {
                handler.getEntityManager().getPlayer().facing = "Left";
                State.setState(new FightState(handler, this, this.Area));
            }
        }

	}
	@Override
	public Rectangle getCollision() {
		return boss1;
	}
	
	//FIGHT STATS
	double health=200,mana=200,xp=0l,lvl=3,defense=36,str=24,intl=60, mr = 30,cons=60,acc=30,evs=3,initiative=3,maxHealth=200;
	String Class = "Boss",skill = "Freeze";
	String[] buffs = {},debuffs = {};

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}

	@Override
	public double getMaxMana() {
		return 200;
	}
	
	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void setHealth(double health) {
		this.health = health;
	}

	@Override
	public double getMana() {
		return mana;
	}

	@Override
	public void setMana(double mana) {
		this.mana = mana;
	}

	@Override
	public double getXp() {
		return xp;
	}

	@Override
	public void setXp(double xp) {
		this.xp = xp;
	}

	@Override
	public double getLvl() {
		return lvl;
	}

	@Override
	public void setLvl(double lvl) {
		this.lvl = lvl;
	}

	@Override
	public double getDefense() {

		return defense;
	}

	@Override
	public void setDefense(double defense) {
		this.defense = defense;
	}

	@Override
	public double getStr() {
		return str;
	}

	@Override
	public void setStr(double str) {
		this.str =str;
	}

	@Override
	public double getIntl() {
		return intl;
	}

	@Override
	public void setIntl(double intl) {
		this.intl = intl;

	}

	@Override
	public double getMr() {
		return mr;
	}

	@Override
	public void setMr(double mr) {
		this.mr = mr;

	}

	@Override
	public double getCons() {
		return cons;
	}

	@Override
	public void setCons(double cons) {
		this.cons = cons;
	}

	@Override
	public double getAcc() {
		return acc;
	}

	@Override
	public void setAcc(double acc) {
		this.acc = acc;
	}

	@Override
	public double getEvs() {
		return evs;
	}

	@Override
	public void setEvs(double evs) {
		this.evs = evs;
	}

	@Override
	public double getInitiative() {
		return initiative;
	}

	@Override
	public void setInitiative(double initiative) {
		this.initiative=initiative;
	}

	@Override
	public String getclass() {
		return Class;
	}

	@Override
	public void setClass(String aClass) {
		this.Class=aClass;
	}

	@Override
	public String getSkill() {
		return this.skill;
	}

	@Override
	public void setSkill(String skill) {
		this.skill=skill;
	}

	@Override
	public String[] getBuffs() {
		return buffs;
	}

	@Override
	public void setBuffs(String[] buffs) {
		this.buffs=buffs;
	}

	@Override
	public String[] getDebuffs() {
		return debuffs;
	}

	@Override
	public void setDebuffs(String[] debuffs) {
		this.debuffs=debuffs;
	}

	public void lvlAdjust() {
		if(lvl > 1) {
			health += 10 + 5*(lvl-1);
			maxHealth = health;
			mana += 10 + 5*(lvl-1);
			if(mana > 100)
				mana = 100;
			str += 1 + 1 *(int)((lvl - 1)/2);
			acc += 1 + 1 *(int)((lvl - 1)/2);
			defense += 1 + 1 *(int)((lvl - 1)/2);
			intl += 1 + 1 *(int)((lvl - 1)/2);
			mr += 1 + 1 *(int)((lvl - 1)/2);
			cons += 1 + 1 *(int)((lvl - 1)/2);
			if(lvl%4 ==0)
				evs += (lvl -lvl%4)/4;
			xp += 20 *(lvl);
		}
	}


	
	
}
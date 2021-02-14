package com.javakaian.game.controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.entity.Entity;
import com.javakaian.game.level.Level;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.BaseTower.TowerType;
import com.javakaian.game.towers.ElectricTower;
import com.javakaian.game.towers.FireTower;
import com.javakaian.game.towers.IceTower;
import com.javakaian.game.util.GameConstants;

public class TowerController implements Entity {

	private List<BaseTower> towerList;

	private BaseTower selectedTower;

	private Level level;

	public TowerController(Level level) {

		this.level = level;
		towerList = new ArrayList<BaseTower>();
	}

	@Override
	public void update(float deltaTime) {
		for (BaseTower tower : towerList) {
			tower.update(deltaTime);
		}
	}

	@Override
	public void render(ShapeRenderer sr) {

		for (BaseTower tower : towerList) {
			tower.render(sr);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		for (BaseTower tower : towerList) {
			tower.render(sb);
		}
	}

	/**
	 * This fuction builds a tower and returns the cost value according to the tower
	 * type. if building tower is not possible then it returns zero as a cost value.
	 **/
	public int buildTower(float x, float y, List<Enemy> enemyList, TowerType type, int money) {
		int cost = 0;
		switch (type) {
		case FIRE:
			cost = GameConstants.TOWER_PRICE;
			if (money >= cost) {
				return buildFireTower(x, y, enemyList);
			}
			break;
		case ICE:
			cost = GameConstants.TOWER_PRICE;
			if (money >= cost) {
				return buildIceTower(x, y, enemyList);
			}
			break;
		case ELECTRIC:
			cost = GameConstants.ELECTRIC_TOWER_PRICE;
			if (money >= cost) {
				return buildElectricTower(x, y, enemyList);
			}
			break;
		}
		return 0;

	}

	private int buildFireTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new FireTower(x, y, enemyList));
		return GameConstants.TOWER_PRICE;
	}

	private int buildIceTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new IceTower(x, y, enemyList));
		return GameConstants.TOWER_PRICE;
	}

	private int buildElectricTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new ElectricTower(x, y, enemyList));
		return GameConstants.ELECTRIC_TOWER_PRICE;
	}

	public List<BaseTower> getTowerList() {
		return towerList;
	}

	public BaseTower getTower(Vector2 center) {

		for (BaseTower tower : towerList) {
			tower.setSelected(false);
			if (tower.position.equals(center)) {
				selectedTower = tower;
				selectedTower.setSelected(true);
			}
		}
		return selectedTower;
	}

	public BaseTower getSelectedTower() {
		return selectedTower;
	}

	public void increaseAttack() {
		selectedTower.increaseDamage();
	}

	public void increaseRange() {
		selectedTower.increaseRange();
	}

	public void increaseSpeed() {
		selectedTower.increaseSpeed();
	}

	public void clearSelectedTower() {
		if (selectedTower != null) {
			selectedTower.setSelected(false);
			selectedTower = null;
		}
	}

	public void speed2xClicked() {
		// also consider towers which will be builded during the process.
		for (BaseTower baseTower : towerList) {
			baseTower.attackSpeed = baseTower.attackSpeed * 2;
		}
	}

	public void normalSpeedClicked() {

		for (BaseTower baseTower : towerList) {
			baseTower.attackSpeed = baseTower.attackSpeed / 2;
		}
	}

}

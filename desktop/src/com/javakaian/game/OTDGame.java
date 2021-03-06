package com.javakaian.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.states.State.StateEnum;
import com.javakaian.game.states.StateController;

public class OTDGame extends ApplicationAdapter {

	private StateController stateController;

	@Override
	public void create() {

		MusicHandler.init();
		MyAtlas.init();
		stateController = new StateController();
		stateController.setState(StateEnum.MenuState);
		MusicHandler.playMenuMusic();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateController.render();
		stateController.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {

		MyAtlas.dispose();
	}

}

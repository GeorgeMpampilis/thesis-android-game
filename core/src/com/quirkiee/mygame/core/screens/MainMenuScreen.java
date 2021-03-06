package com.quirkiee.mygame.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.quirkiee.mygame.core.MyGame;
import com.quirkiee.mygame.core.tools.OverlapTester;

public class MainMenuScreen implements Screen {

	private Viewport viewport;
	private Stage stage;
	private Texture startButton;
	private Texture menuBackground;
	private Rectangle playBounds;
	private OrthographicCamera guiCam;
	private Vector3 touchPoint;

	private MyGame game;

	public MainMenuScreen(MyGame game) {
		this.game = game;

		viewport = new StretchViewport(MyGame.V_WIDTH, MyGame.V_HEIGHT,
				new OrthographicCamera());

		guiCam = new OrthographicCamera(MyGame.V_WIDTH, MyGame.V_HEIGHT);
		guiCam.position.set(MyGame.V_WIDTH / 2, MyGame.V_HEIGHT / 2, 0);

		menuBackground = new Texture("images/menu/menuBackground.png");
		startButton = new Texture("images/menu/startButton.png");

		playBounds = new Rectangle((MyGame.V_WIDTH / 2)
				- (startButton.getWidth() / 2), (MyGame.V_HEIGHT / 2)
				- (startButton.getHeight() / 2), 210, 40);
		touchPoint = new Vector3();
	}

	public void update(float delta) {
		handleInput();

	}

	public void handleInput() {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			if (OverlapTester.pointInRectangle(playBounds, touchPoint.x,
					touchPoint.y)) {

				MyGame.manager.get("audio/sounds/menuButton.wav", Sound.class)
						.play();
				game.setScreen(new LevelSelectScreen(game));
				dispose();
				return;
			}
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();

		game.batch.setProjectionMatrix(guiCam.combined);

		game.batch.begin();
		game.batch.draw(menuBackground, 0, 0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
		game.batch.draw(startButton,
				(MyGame.V_WIDTH / 2) - (startButton.getWidth() / 2),
				(MyGame.V_HEIGHT / 2) - (startButton.getHeight() / 2));
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		startButton.dispose();
		menuBackground.dispose();
	}

}

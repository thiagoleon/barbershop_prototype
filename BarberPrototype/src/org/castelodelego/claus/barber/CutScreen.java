package org.castelodelego.claus.barber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CutScreen implements Screen {

	LevelContext c;
	BitmapFont scoreFont;
	SpriteBatch batch;
	PrototypeMain parent;
	
	public boolean initialized = false;
	
	public CutScreen(PrototypeMain p)
	{
		scoreFont = PrototypeMain.manager.get("joystix24.fnt", BitmapFont.class);
		batch = new SpriteBatch();
		parent = p;
	}
	
	public void init()
	{
		c = new LevelContext();
		initialized = true;
	}
	
	@Override
	/**
	 * Render and Update cycle (at least for now)
	 */
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		c.blade.playerAccel(Gdx.input.isTouched());
		
		c.blade.update(c, delta);
		c.skin.update(c, delta);
		c.blood.update(delta);
		c.hair.update(c, delta);
		c.update(delta);
		
		c.blade.draw(c);
		c.skin.draw(c);
		c.hair.draw(c);
		c.blood.draw(c);
		
		if (c.isTimeOver())
			parent.setScreen(parent.entryscreen);
		
		batch.begin();
		scoreFont.setColor(Color.GRAY);
		scoreFont.draw(batch, "Time: "+ String.format("%.2f", c.maxtime-c.curtime),10,780);
		scoreFont.draw(batch, "Score: "+ (int) Math.floor((double)parent.gamescreen.c.score), 250, 780);
		batch.end();	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		c.blade.init();
		c.skin.init();
		c.blood.init();
		c.hair.init();
		c.init();

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
		// TODO Auto-generated method stub

	}

}

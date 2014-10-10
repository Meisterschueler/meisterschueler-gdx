package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;

public class ParticleCache {

	public static final int FIRE = 0, SNOW = 1, BLOOD = 2, BUBBLES = 3, EXPL_FIRE = 4, FIREWORK_LARGE = 5;

	private static ParticleEffectPool pool_fire, pool_snow, pool_blood, pool_bubbles, pool_expl_fire, pool_firework_large;

	private static ParticleEffect fx_fire, fx_snow, fx_blood, fx_bubbles, fx_expl_fire, fx_firework_large;

	public static void Load() {

		fx_fire = new ParticleEffect();
		fx_fire.load(Gdx.files.internal("fx/fire.p"), Gdx.files.internal("fx"));
		pool_fire = new ParticleEffectPool(fx_fire, 0, 150);

		fx_snow = new ParticleEffect();
		fx_snow.load(Gdx.files.internal("fx/snow.p"), Gdx.files.internal("fx"));
		pool_snow = new ParticleEffectPool(fx_snow, 0, 150);

		fx_blood = new ParticleEffect();
		fx_blood.load(Gdx.files.internal("fx/blood.p"), Gdx.files.internal("fx"));
		pool_blood = new ParticleEffectPool(fx_blood, 0, 150);

		fx_bubbles = new ParticleEffect();
		fx_bubbles.load(Gdx.files.internal("fx/bubbles.p"), Gdx.files.internal("fx"));
		pool_bubbles = new ParticleEffectPool(fx_bubbles, 0, 150);

		fx_expl_fire = new ParticleEffect();
		fx_expl_fire.load(Gdx.files.internal("fx/explosion_fire.p"), Gdx.files.internal("fx"));
		pool_expl_fire = new ParticleEffectPool(fx_expl_fire, 0, 150);

		fx_firework_large = new ParticleEffect();
		fx_firework_large.load(Gdx.files.internal("fx/firework_large.p"), Gdx.files.internal("fx"));
		pool_firework_large = new ParticleEffectPool(fx_firework_large, 0, 150);
	}

	static PooledEffect effect;

	public static PooledEffect getParticleEffect(int type) {

		switch (type) {
		case FIRE: {
			effect = pool_fire.obtain();
			break;
		}
		case SNOW: {
			effect = pool_snow.obtain();
			break;
		}
		case BLOOD: {
			effect = pool_blood.obtain();
			break;
		}
		case BUBBLES: {
			effect = pool_bubbles.obtain();
			break;
		}
		case EXPL_FIRE: {
			effect = pool_expl_fire.obtain();
			break;
		}
		case FIREWORK_LARGE: {
			effect = pool_firework_large.obtain();
			break;
		}

		}
		effect.start();
		return effect;
	}
}

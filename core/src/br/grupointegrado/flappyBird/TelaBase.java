package br.grupointegrado.flappyBird;

import com.badlogic.gdx.Screen;

/**
 * Created by Elito Fraga on 28/09/2015.
 */
public abstract class TelaBase implements Screen{

    protected MainGame game;

    public TelaBase(MainGame game) {
        this.game = game;
    }

}

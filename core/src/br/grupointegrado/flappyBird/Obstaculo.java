package br.grupointegrado.flappyBird;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Elito Fraga on 26/10/2015.
 */
public class Obstaculo {
    private World mundo;
    private OrthographicCamera camera;
    private Body corpoCima, corpoBaixo;
    private float posX;
    private float posYCima, posYBaixo;
    private float largura, altura;
    private boolean passou;

    private Obstaculo ultimoObstaculo;
    public Obstaculo(World mundo, OrthographicCamera camera, Obstaculo ultimoObstaculo) {
        this.mundo = mundo;
        this.camera = camera;
        this.ultimoObstaculo = ultimoObstaculo;

        initPosicao();
        initCorpoCima();
        initCorpoBaixo();
    }

    private void initCorpoBaixo() {
        corpoBaixo = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, posX, posYBaixo);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, altura / 2);

        Util.criarForma(corpoBaixo, shape, "OBSTACULO_BAIXO");
        shape.dispose();
    }

    private void initCorpoCima() {
        corpoCima = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, posX, posYCima);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, altura / 2);

        Util.criarForma(corpoCima, shape, "OBSTACULO_CIMA");
        shape.dispose();
    }

    private void initPosicao() {
        largura = 40 / Util.PIXEL_METRO;
        altura = camera.viewportHeight / Util.PIXEL_METRO;

        float xInicial = largura;
            if (ultimoObstaculo != null) xInicial = ultimoObstaculo.getX();

        posX = xInicial + 8; // 4 e o espaço entre os obstaculos

        float parcela = (altura - Util.ALTURA_CHAO) / 6; // para encontrar a posicao y dos obstaculos

        int multiplicador = MathUtils.random(1, 3); // 2f entre os canos
        posYBaixo = Util.ALTURA_CHAO + (parcela * multiplicador) - (altura /  2);
        posYCima = posYBaixo + altura + 2f;

    }

    public float getX() {
        return this.posX;
    }

    public void remover() {
        mundo.destroyBody(corpoCima);
        mundo.destroyBody(corpoBaixo);
    }
}

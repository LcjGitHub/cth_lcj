package com.chetuhui.lcj.chezhubao_x.view.progressing.style;


import com.chetuhui.lcj.chezhubao_x.view.progressing.sprite.Sprite;
import com.chetuhui.lcj.chezhubao_x.view.progressing.sprite.SpriteContainer;

/**
 * @author vondear
 */
public class MultiplePulseRing extends SpriteContainer {

    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new PulseRing(),
                new PulseRing(),
                new PulseRing(),
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].setAnimationDelay(200 * (i + 1));
        }
    }
}

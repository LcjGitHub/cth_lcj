package com.chetuhui.lcj.chezhubao_x.view.progressing;


import com.chetuhui.lcj.chezhubao_x.view.progressing.sprite.Sprite;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.ChasingDots;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.Circle;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.CubeGrid;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.DoubleBounce;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.FadingCircle;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.FoldingCube;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.MultiplePulse;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.MultiplePulseRing;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.Pulse;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.PulseRing;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.RotatingCircle;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.RotatingPlane;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.ThreeBounce;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.WanderingCubes;
import com.chetuhui.lcj.chezhubao_x.view.progressing.style.Wave;

/**
 * @author vondear
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}

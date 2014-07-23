package com.ninetwozero.bf4intel.ui.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SimpleFadeInAnimation extends AlphaAnimation {
    public final View view;
    public SimpleFadeInAnimation(View inView) {
        super(0f, 1.0f);

        this.view = inView;
        setAnimationListener(
            new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    view.clearAnimation();
                    view.setClickable(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            }
        );
        setDuration(800L);
    }
}

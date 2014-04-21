package com.ninetwozero.bf4intel.ui.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SimpleFadeOutAnimation extends AlphaAnimation {
    public final View view;
    public SimpleFadeOutAnimation(View inView) {
        super(1.0f, 0f);

        this.view = inView;
        setAnimationListener(
            new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setClickable(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    view.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            }
        );
        setDuration(600L);
    }
}

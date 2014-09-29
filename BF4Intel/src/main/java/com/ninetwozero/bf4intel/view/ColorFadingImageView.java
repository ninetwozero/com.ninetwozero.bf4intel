package com.ninetwozero.bf4intel.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.ninetwozero.bf4intel.R;

public class ColorFadingImageView extends ImageView {
    private final int NO_IMAGE_SELECTED = -1;
    private final int[] COLORS = {
        android.R.color.holo_blue_dark,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_red_dark
    };

    private Animator fadeInAnimator;
    private Animator fadeOutAnimator;
    private Animator currentAnimator;

    private int currentIndex;
    private int customWhiteImage;
    private boolean firstRun = true;
    private boolean shouldSwapToWhiteDrawable = true;

    public ColorFadingImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ColorFadingImageView, 0, 0);
        customWhiteImage = typedArray.getResourceId(R.styleable.ColorFadingImageView_whiteImage, NO_IMAGE_SELECTED);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        if (fadeInAnimator == null) {
            fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 0.7f);
            fadeInAnimator.setDuration(1000);
            fadeInAnimator.setInterpolator(new AccelerateInterpolator());
            fadeInAnimator.addListener(
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(final Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    runAnimation(fadeOutAnimator);
                                }
                            }, 500
                        );
                    }

                    @Override
                    public void onAnimationCancel(final Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(final Animator animation) {

                    }
                }
            );
        }

        if (fadeOutAnimator == null) {
            fadeOutAnimator = ObjectAnimator.ofFloat(this, "alpha", 0.7f, 0f);
            fadeOutAnimator.setDuration(1000);
            fadeOutAnimator.setInterpolator(new AccelerateInterpolator());
            fadeOutAnimator.addListener(
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(final Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        if (shouldSwapToWhiteDrawable && customWhiteImage != NO_IMAGE_SELECTED) {
                            Drawable drawable = getResources().getDrawable(customWhiteImage).mutate();
                            setImageDrawable(drawable);
                            shouldSwapToWhiteDrawable = false;
                        }

                        getDrawable().setColorFilter(new LightingColorFilter(getCurrentColor(), 0));
                        postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    runAnimation(fadeInAnimator);
                                }
                            }, 700
                        );
                    }

                    @Override
                    public void onAnimationCancel(final Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(final Animator animation) {

                    }
                }
            );
        }

        if (firstRun) {
            runAnimation(fadeOutAnimator);
            firstRun = false;
        }
    }

    private int getCurrentColor() {
        if (currentIndex >= COLORS.length) {
            currentIndex = 0;
        }
        return getResources().getColor(COLORS[currentIndex++]);
    }

    @Override
    protected void onVisibilityChanged(final View changedView, final int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == View.VISIBLE) {
            if (fadeInAnimator != null) {
                runAnimation(fadeInAnimator);
            }
        } else {
            if (currentAnimator != null) {
                currentAnimator.cancel();
            }
        }
    }

    private void runAnimation(Animator animator) {
        currentAnimator = animator;
        animator.start();
    }

    public void stopAnimation() {
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }
    }
}

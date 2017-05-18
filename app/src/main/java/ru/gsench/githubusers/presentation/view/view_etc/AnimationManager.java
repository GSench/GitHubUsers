package ru.gsench.githubusers.presentation.view.view_etc;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.nineoldandroids.animation.AnimatorListenerAdapter;

import io.codetail.animation.ViewAnimationUtils;
import io.codetail.animation.arcanimator.ArcAnimator;
import io.codetail.animation.arcanimator.Side;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.utils.MyAnimationListener;
import ru.gsench.githubusers.presentation.utils.MyAnimatorListener;
import ru.gsench.githubusers.presentation.viewholder.MainViewHolder;

/**
 * Created by grish on 10.05.2017.
 */

public class AnimationManager {

    public static void openSearchView(final MainViewHolder mainViewHolder, final function<Void> onAnimationFinish){
        try {
            final View view = mainViewHolder.getBackground();
            final View searchImage = mainViewHolder.getSearchImage();

            // get the center for the clipping circle
            final int cx = (searchImage.getLeft() + searchImage.getRight()) / 2;
            final int cy = (searchImage.getTop() + searchImage.getBottom()) / 2;

            // get the radius for the clipping circle
            int dx = Math.max(cx, view.getWidth() - cx);
            int dy = Math.max(cy, view.getHeight() - cy);
            float startR = (float) Math.hypot(dx, dy);
            final float finalR = (float) searchImage.getWidth()*0.375f;

            Animator backgroundAnim = ViewAnimationUtils.createCircularReveal(view, cx, cy, startR, finalR);
            backgroundAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            backgroundAnim.setDuration(400);
            backgroundAnim.addListener(new MyAnimatorListener(){
                @Override
                public void onAnimationEnd(Animator animator) {
                    try {
                        mainViewHolder.getBackgroundFullContainer().setVisibility(View.GONE);
                        mainViewHolder.getBackgroundCircle().setX(cx-finalR);
                        mainViewHolder.getBackgroundCircle().setY(cy-finalR);
                        mainViewHolder.getBackgroundCircle().setVisibility(View.VISIBLE);
                        searchImage.setVisibility(View.GONE);
                        startArcMovingAnimation(mainViewHolder, onAnimationFinish);
                    } catch (Throwable t){}
                }
            });
            backgroundAnim.start();

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(searchImage, "scaleX", 0.375f);
            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(searchImage, "scaleY", 0.375f);
            scaleDownY.setDuration(400);
            scaleDownX.setDuration(400);
            AnimatorSet scaleDown = new AnimatorSet();
            scaleDown.play(scaleDownX).with(scaleDownY);
            scaleDown.start();

            AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
            animation1.setDuration(400);
            animation1.setAnimationListener(new MyAnimationListener(){
                @Override
                public void onAnimationEnd(Animation animation) {
                    try {
                        mainViewHolder.getHelloContent().setVisibility(View.GONE);
                    } catch (Throwable t){}
                }
            });
            mainViewHolder.getHelloContent().startAnimation(animation1);
        } catch (Throwable t){}
    }

    private static void startArcMovingAnimation(final MainViewHolder viewHolder, final function<Void> onAnimationFinish){
        int finalX = viewHolder.getBackgroundCircle().getWidth()/2;
        int finalY = viewHolder.getBackgroundCircle().getHeight()/2;

        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(viewHolder.getBackgroundCircle(), finalX, finalY, 0, Side.LEFT).setDuration(500);
        arcAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                try {
                    startSearchViewAnimation(viewHolder, onAnimationFinish);
                    super.onAnimationEnd(animation);
                } catch (Throwable t){}
            }
        });
        arcAnimator.start();
    }

    private static void startSearchViewAnimation(final MainViewHolder viewHolder, final function<Void> onAnimationFinish){
        int w = viewHolder.getBackgroundCircle().getWidth();
        int h = viewHolder.getBackgroundCircle().getHeight();
        int cx = w/2;
        int cy = h/2;
        viewHolder.getSearchViewContainer().addView(viewHolder.getSearchView());
        viewHolder.getSearchView().measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewHolder.getBackgroundContainer().setVisibility(View.GONE);
        float finalR = viewHolder.getSearchView().getMeasuredWidth();
        Animator searchViewAnim = ViewAnimationUtils.createCircularReveal(viewHolder.getSearchView(), cx, cy, w/2, finalR);
        searchViewAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        searchViewAnim.setDuration(400);
        searchViewAnim.addListener(new MyAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animator) {
                try {
                    onAnimationFinish.run();
                } catch (Throwable t){}
            }
        });
        searchViewAnim.start();
    }

    public static void fadeInAnimation(View v, final function<Void> onAnimationFinish){
        v.setVisibility(View.VISIBLE);
        AlphaAnimation animation1 = new AlphaAnimation(0f, 1f);
        animation1.setDuration(100);
        animation1.setAnimationListener(new MyAnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                onAnimationFinish.run();
            }
        });
        v.startAnimation(animation1);
    }
}

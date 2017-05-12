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
import ru.gsench.githubusers.presentation.utils.MyAnimationListener;
import ru.gsench.githubusers.presentation.utils.MyAnimatorListener;
import ru.gsench.githubusers.presentation.viewholder.MainViewHolder;

/**
 * Created by grish on 10.05.2017.
 */

public class AnimationManager {
    //TODO block ui during animation

    public static void openSearchView(final MainViewHolder mainViewHolder){
        final View view = mainViewHolder.background;
        final View searchImage = mainViewHolder.searchImage;

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
                mainViewHolder.backgroundFullContainer.setVisibility(View.GONE);
                mainViewHolder.backgroundCircle.setX(cx-finalR);
                mainViewHolder.backgroundCircle.setY(cy-finalR);
                mainViewHolder.backgroundCircle.setVisibility(View.VISIBLE);
                startArcMovingAnimation(mainViewHolder);
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
        scaleDown.addListener(new MyAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animator) {
                searchImage.setVisibility(View.GONE);
            }
        });

        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
        animation1.setDuration(400);
        animation1.setAnimationListener(new MyAnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                mainViewHolder.helloContent.setVisibility(View.GONE);
            }
        });
        mainViewHolder.helloContent.startAnimation(animation1);
    }

    private static void startArcMovingAnimation(final MainViewHolder viewHolder){
        int finalX = viewHolder.backgroundCircle.getWidth()/2;
        int finalY = viewHolder.backgroundCircle.getHeight()/2;

        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(viewHolder.backgroundCircle, finalX, finalY, 0, Side.LEFT).setDuration(500);
        arcAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                startSearchViewAnimation(viewHolder);
                super.onAnimationEnd(animation);
            }
        });
        arcAnimator.start();
    }

    private static void startSearchViewAnimation(final MainViewHolder viewHolder){
        int w = viewHolder.backgroundCircle.getWidth();
        int h = viewHolder.backgroundCircle.getHeight();
        int cx = w/2;
        int cy = h/2;
        viewHolder.searchViewContainer.addView(viewHolder.searchView);
        viewHolder.searchView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewHolder.backgroundContainer.setVisibility(View.GONE);
        float finalR = viewHolder.searchView.getMeasuredWidth();
        Animator searchViewAnim = ViewAnimationUtils.createCircularReveal(viewHolder.searchView, cx, cy, w/2, finalR);
        searchViewAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        searchViewAnim.setDuration(400);
        searchViewAnim.start();
    }
}

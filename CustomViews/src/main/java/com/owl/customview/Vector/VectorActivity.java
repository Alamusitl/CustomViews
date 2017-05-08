package com.owl.customview.Vector;

import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.owl.customview.R;


/**
 * Created by Alamusi on 2017/1/19.
 */

public class VectorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);

        ImageView mImageView = (ImageView) findViewById(R.id.id_iv_vector);
        AnimatedVectorDrawable mAnimatedVectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.heart_vector_animator);//得到对应的AnimatedVectorDrawable对象
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setImageDrawable(mAnimatedVectorDrawable);
            if (mAnimatedVectorDrawable != null) {
                mAnimatedVectorDrawable.start();
            }
        }
    }
}

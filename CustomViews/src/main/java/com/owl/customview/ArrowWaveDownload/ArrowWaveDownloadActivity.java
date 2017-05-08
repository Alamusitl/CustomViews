package com.owl.customview.ArrowWaveDownload;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.owl.customview.R;

import java.util.Timer;
import java.util.TimerTask;


public class ArrowWaveDownloadActivity extends Activity {

    private int mCount = 0;
    private int mProgress = 0;
    private ArrowWaveDownloadButton mDownloadingButton;
    private Timer mTimer;
    private TimerTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrow_wave_download);
        mDownloadingButton = (ArrowWaveDownloadButton) findViewById(R.id.arrow_download_button);
        mTimer = new Timer();
        mDownloadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mCount % 2) == 0) {
                    mDownloadingButton.startAnimating();
                    if (mTask == null) {
                        mTask = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println(mProgress);
                                        mProgress = mProgress + 1;
                                        mDownloadingButton.setProgress(mProgress);
                                        if (mProgress == 100) {
                                            mTask.cancel();
                                            mTask = null;
                                            mProgress = 0;
                                        }
                                    }
                                });
                            }
                        };
                    }
                    mTimer.schedule(mTask, 800, 20);
                } else {
                    mDownloadingButton.reset();
                    if (mTask != null) {
                        mTask.cancel();
                        mTask = null;
                    }
                }
                mCount++;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        super.onDestroy();
    }
}

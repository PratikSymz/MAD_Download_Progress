package com.vin_tourin.android.mad_download_progress;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(view -> download());
    }

    public void download() {
        // Setup Dialog and show
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("Download Status");
        mDialog.setMessage("Downloading...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // Set Properties
        mDialog.setMax(100);
        mDialog.setIndeterminate(false);
        mDialog.setProgress(0);
        mDialog.show();

        // Show progress
        final Thread thread = new Thread() {
            @Override
            public void run() {
                while (mDialog.getProgress() <= mDialog.getMax()) {
                    try {
                        Thread.sleep(200);
                        mDialog.incrementProgressBy(1);
                        if (mDialog.getProgress() == mDialog.getMax()) {
                            mDialog.dismiss();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }
}

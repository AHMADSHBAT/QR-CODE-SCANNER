package com.zl.tesseract.scanner.decode;

import android.os.Handler;
import android.os.Looper;

import com.zl.tesseract.scanner.ScannerActivity;

import java.util.concurrent.CountDownLatch;


final class DecodeThread extends Thread {

    private final ScannerActivity mActivity;
    private final CountDownLatch mHandlerInitLatch;
    private Handler mHandler;

    DecodeThread(ScannerActivity activity) {
        this.mActivity = activity;
        mHandlerInitLatch = new CountDownLatch(1);
    }

    Handler getHandler() {
        try {
            mHandlerInitLatch.await();
        } catch (InterruptedException ie) {
            // continue?
        }
        return mHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new DecodeHandler(mActivity);
        mHandlerInitLatch.countDown();
        Looper.loop();
    }
}

/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bentools.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Schedule a countdown until a time in the future, with regular notifications
 * on intervals along the way.
 * 
 * Example of showing a 30 second countdown in a text field:
 * 
 * <pre class="prettyprint">
 * new CountdownTimer(30000, 1000) {
 * 
 * 	public void onTick(long millisUntilFinished) {
 * 		mTextField.setText(&quot;seconds remaining: &quot; + millisUntilFinished / 1000);
 * 	}
 * 
 * 	public void onFinish() {
 * 		mTextField.setText(&quot;done!&quot;);
 * 	}
 * }.start();
 * </pre>
 * 
 * The calls to {@link #onTick(long)} are synchronized to this object so that
 * one call to {@link #onTick(long)} won't ever occur before the previous
 * callback is complete. This is only relevant when the implementation of
 * {@link #onTick(long)} takes an amount of time to execute that is significant
 * compared to the countdown interval.
 */
public abstract class MidAutumnCountDownTimer {

	/**
	 * Millis since epoch when alarm should stop.
	 */
	private final long mMillisInFuture;

	/**
	 * The interval in millis that the user receives callbacks
	 */
	private final long mCountdownInterval;

	private long mStopTimeInFuture;

	/**
	 * @param millisInFuture
	 *            The number of millis in the future from the call to
	 *            {@link #start()} until the countdown is done and
	 *            {@link #onFinish()} is called.
	 * @param countDownInterval
	 *            The interval along the way to receive {@link #onTick(long)}
	 *            callbacks.
	 */
	public MidAutumnCountDownTimer(long millisInFuture, long countDownInterval) {
		mMillisInFuture = millisInFuture;
		mCountdownInterval = countDownInterval;
	}

	/**
	 * Cancel the countdown.
	 */
	public final void cancel() {
		mHandler.removeMessages(MSG);
	}

	/**
	 * Start the countdown.
	 */
	private int position;

	public void setPosition(int position) {
		this.position = position;
	}

	public synchronized final MidAutumnCountDownTimer start(int position) {

		this.position = position;
		if (mMillisInFuture <= 0) {
			onFinish(position);
			return this;
		}
		mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
		mHandler.sendMessage(mHandler.obtainMessage(MSG));
		return this;
	}

	private String code;

	/*
	 * public synchronized final MidAutumnCountDownTimer start(String code) {
	 * 
	 * this.code = code; if (mMillisInFuture <= 0) { onFinish(position);
	 * onFinish(code); return this; } mStopTimeInFuture =
	 * SystemClock.elapsedRealtime() + mMillisInFuture;
	 * mHandler.sendMessage(mHandler.obtainMessage(MSG)); return this; }
	 */

	/**
	 * Callback fired on regular interval.
	 * 
	 * @param millisUntilFinished
	 *            The amount of time until finished.
	 */
	public abstract void onTick(long millisUntilFinished, int position);

	// public abstract void onTick(long millisUntilFinished, String code);
	/**
	 * Callback fired when the time is up.
	 */
	public abstract void onFinish(int position);

	/* public abstract void onFinish(String code); */
	private static final int MSG = 1;

	// handles counting down
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			synchronized (MidAutumnCountDownTimer.this) {
				final long millisLeft = mStopTimeInFuture
						- SystemClock.elapsedRealtime();

				if (millisLeft <= 0) {
					onFinish(position);
					// onFinish(code);
				} else if (millisLeft < mCountdownInterval) {
					// no tick, just delay until done
					sendMessageDelayed(obtainMessage(MSG), millisLeft);
				} else {
					long lastTickStart = SystemClock.elapsedRealtime();
					onTick(millisLeft, position);
					// onTick(millisLeft, code);
					// take into account user's onTick taking time to execute
					long delay = lastTickStart + mCountdownInterval
							- SystemClock.elapsedRealtime();

					// special case: user's onTick took more than interval to
					// complete, skip to next interval
					while (delay < 0)
						delay += mCountdownInterval;

					sendMessageDelayed(obtainMessage(MSG), delay);
				}
			}
		}
	};
}

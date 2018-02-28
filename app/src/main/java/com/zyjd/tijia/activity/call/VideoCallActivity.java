/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zyjd.tijia.activity.call;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.hyphenate.chat.EMCallManager.EMCameraDataProcessor;
import com.hyphenate.chat.EMVideoCallHelper;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.media.EMCallSurfaceView;
import com.hyphenate.util.EMLog;
import com.superrtc.sdk.VideoView;
import com.zyjd.tijia.R;

import java.io.File;

import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VideoCallActivity extends CallActivity {

    // 拒绝
    @BindView(R.id.btn_refuse_call)
    Button refuseBtn;

    // 接听
    @BindView(R.id.btn_answer_call)
    Button answerBtn;

    // 刮起
    @BindView(R.id.btn_hangup_call)
    Button hangupBtn;

    // 静音
    @BindView(R.id.iv_mute)
    ImageView muteImage;

    // 免提
    @BindView(R.id.iv_handsfree)
    ImageView handsFreeImage;

    // 调节器
    @BindView(R.id.seekbar_y_detal)
    SeekBar YDeltaSeekBar;

    // 昵称
    @BindView(R.id.tv_nick)
    TextView nickTextView;

    @BindView(R.id.local_surface)
    EMCallSurfaceView localSurface;

    @BindView(R.id.opposite_surface)
    EMCallSurfaceView oppositeSurface;

    @BindView(R.id.ll_coming_call)
    LinearLayout comingBtnContainer;

    @BindView(R.id.tv_call_state)
    TextView callStateTextView;

    @BindView(R.id.ll_voice_control)
    LinearLayout voiceContronlLayout;

    // 通话计时器
    @BindView(R.id.chronometer)
    Chronometer chronometer;

    // 网络状态指示
    @BindView(R.id.tv_network_status)
    TextView netwrokStatusVeiw;

    @BindView(R.id.root_layout)
    RelativeLayout rootContainer;

    @BindView(R.id.ll_bottom_container)
    LinearLayout bottomContainer;

    @BindView(R.id.ll_top_container)
    LinearLayout topContainer;

    private boolean isMuteState;
    private boolean isHandsfreeState;
    private boolean isAnswered;
    private boolean endCallTriggerByMe = false;

    private int surfaceState = -1;
    private Handler uiHandler;
    private boolean isInCalling;
    boolean isRecording = false;
    private EMVideoCallHelper callHelper;
    private BrightnessDataProcess dataProcessor = new BrightnessDataProcess();

    // 动态亮度调整
    class BrightnessDataProcess implements EMCameraDataProcessor {
        byte yDelta = 0;

        synchronized void setYDelta(byte yDelta) {
            Log.d("VideoCallActivity", "brigntness uDelta:" + yDelta);
            this.yDelta = yDelta;
        }

        // data size is width*height*2
        // the first width*height is Y, second part is UV
        // the storage layout detailed please refer 2.x demo CameraHelper.onPreviewFrame
        @Override
        public synchronized void onProcessData(byte[] data, Camera camera, final int width, final int height, final int rotateAngel) {
            int wh = width * height;
            for (int i = 0; i < wh; i++) {
                int d = (data[i] & 0xFF) + yDelta;
                d = d < 16 ? 16 : d;
                d = d > 235 ? 235 : d;
                data[i] = (byte) d;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_video_call);
        ButterKnife.bind(this);

        Helper.getInstance().isVideoCalling = true;
        callType = 1;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        uiHandler = new Handler();


        YDeltaSeekBar.setOnSeekBarChangeListener(new YDeltaSeekBarListener());
        msgid = UUID.randomUUID().toString();
        isInComingCall = getIntent().getBooleanExtra("isComingCall", false);
        username = getIntent().getStringExtra("username");

        nickTextView.setText(username);

        // 本地摄像头 surfaceview
        localSurface.setZOrderMediaOverlay(true);
        localSurface.setZOrderOnTop(true);

        // 设置通话状态监听
        addCallStateListener();

        // 呼出电话
        if (!isInComingCall) {
            soundPool = new SoundPool(1, AudioManager.STREAM_RING, 0);
            outgoing = soundPool.load(this, R.raw.em_outgoing, 1);
            comingBtnContainer.setVisibility(View.INVISIBLE);
            hangupBtn.setVisibility(View.VISIBLE);
            String st = getResources().getString(R.string.Are_connected_to_each_other);
            callStateTextView.setText(st);
            EMClient.getInstance().callManager().setSurfaceView(localSurface, oppositeSurface);
            handler.sendEmptyMessage(MSG_CALL_MAKE_VIDEO);
            handler.postDelayed(new Runnable() {
                public void run() {
                    streamID = playMakeCallSounds();
                }
            }, 300);
        }
        // 呼入电话
        else {

            callStateTextView.setText("Ringing");
            if (EMClient.getInstance().callManager().getCallState() == EMCallStateChangeListener.CallState.IDLE
                    || EMClient.getInstance().callManager().getCallState() == EMCallStateChangeListener.CallState.DISCONNECTED) {
                // 通话已结束
                finish();
                return;
            }
            voiceContronlLayout.setVisibility(View.INVISIBLE);
            localSurface.setVisibility(View.INVISIBLE);
            Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            audioManager.setMode(AudioManager.MODE_RINGTONE);
            audioManager.setSpeakerphoneOn(true);
            ringtone = RingtoneManager.getRingtone(this, ringUri);
            ringtone.play();
            EMClient.getInstance().callManager().setSurfaceView(localSurface, oppositeSurface);
        }

        final int MAKE_CALL_TIMEOUT = 50 * 1000;
        handler.removeCallbacks(timeoutHangup);
        handler.postDelayed(timeoutHangup, MAKE_CALL_TIMEOUT);

        // get instance of call helper, should be called after setSurfaceView was called
        callHelper = EMClient.getInstance().callManager().getVideoCallHelper();

        EMClient.getInstance().callManager().setCameraDataProcessor(dataProcessor);
    }


    // 拖动条监听
    class YDeltaSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            dataProcessor.setYDelta((byte) (20.0f * (progress - 50) / 50.0f));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }


    // 切换通话界面，这里就是交换本地和远端画面控件设置，以达到通话大小画面的切换
    private void changeCallView() {
        if (surfaceState == 0) {
            surfaceState = 1;
            EMClient.getInstance().callManager().setSurfaceView(oppositeSurface, localSurface);
        } else {
            surfaceState = 0;
            EMClient.getInstance().callManager().setSurfaceView(localSurface, oppositeSurface);
        }
    }

    // 设置通话状态监听
    void addCallStateListener() {
        callStateListener = new EMCallStateChangeListener() {

            @Override
            public void onCallStateChanged(final CallState callState, final CallError error) {
                switch (callState) {

                    case CONNECTING: // is connecting
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                callStateTextView.setText(R.string.Are_connected_to_each_other);
                            }

                        });
                        break;
                    case CONNECTED: // connected
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
//                            callStateTextView.setText(R.string.have_connected_with);
                            }

                        });
                        break;

                    case ACCEPTED: // call is accepted
                        surfaceState = 0;
                        handler.removeCallbacks(timeoutHangup);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    if (soundPool != null)
                                        soundPool.stop(streamID);
                                    EMLog.d("EMCallManager", "soundPool stop ACCEPTED");
                                } catch (Exception e) {
                                }
                                openSpeakerOn();
                                ((TextView) findViewById(R.id.tv_is_p2p)).setText(EMClient.getInstance().callManager().isDirectCall()
                                        ? R.string.direct_call : R.string.relay_call);
                                handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
                                isHandsfreeState = true;
                                isInCalling = true;
                                chronometer.setVisibility(View.VISIBLE);
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                // call durations start
                                chronometer.start();
                                nickTextView.setVisibility(View.INVISIBLE);
                                callStateTextView.setText(R.string.In_the_call);
//                            recordBtn.setVisibility(View.VISIBLE);
                                callingState = CallingState.NORMAL;
                            }

                        });
                        break;
                    case NETWORK_DISCONNECTED:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                netwrokStatusVeiw.setVisibility(View.VISIBLE);
                                netwrokStatusVeiw.setText(R.string.network_unavailable);
                            }
                        });
                        break;
                    case NETWORK_UNSTABLE:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                netwrokStatusVeiw.setVisibility(View.VISIBLE);
                                if (error == CallError.ERROR_NO_DATA) {
                                    netwrokStatusVeiw.setText(R.string.no_call_data);
                                } else {
                                    netwrokStatusVeiw.setText(R.string.network_unstable);
                                }
                            }
                        });
                        break;
                    case NETWORK_NORMAL:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                netwrokStatusVeiw.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case VIDEO_PAUSE:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VIDEO_PAUSE", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case VIDEO_RESUME:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VIDEO_RESUME", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case VOICE_PAUSE:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VOICE_PAUSE", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case VOICE_RESUME:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VOICE_RESUME", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case DISCONNECTED: // call is disconnected
                        handler.removeCallbacks(timeoutHangup);
                        @SuppressWarnings("UnnecessaryLocalVariable") final CallError fError = error;
                        runOnUiThread(new Runnable() {
                            private void postDelayedCloseMsg() {
                                uiHandler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        removeCallStateListener();
                                        saveCallRecord();
                                        Animation animation = new AlphaAnimation(1.0f, 0.0f);
                                        animation.setDuration(1200);
                                        rootContainer.startAnimation(animation);
                                        finish();
                                    }

                                }, 200);
                            }

                            @Override
                            public void run() {
                                chronometer.stop();
                                callDruationText = chronometer.getText().toString();
                                String s1 = getResources().getString(R.string.The_other_party_refused_to_accept);
                                String s2 = getResources().getString(R.string.Connection_failure);
                                String s3 = getResources().getString(R.string.The_other_party_is_not_online);
                                String s4 = getResources().getString(R.string.The_other_is_on_the_phone_please);
                                String s5 = getResources().getString(R.string.The_other_party_did_not_answer);

                                String s6 = getResources().getString(R.string.hang_up);
                                String s7 = getResources().getString(R.string.The_other_is_hang_up);
                                String s8 = getResources().getString(R.string.did_not_answer);
                                String s9 = getResources().getString(R.string.Has_been_cancelled);
                                String s10 = getResources().getString(R.string.Refused);

                                if (fError == CallError.REJECTED) {
                                    callingState = CallingState.BEREFUSED;
                                    callStateTextView.setText(s1);
                                } else if (fError == CallError.ERROR_TRANSPORT) {
                                    callStateTextView.setText(s2);
                                } else if (fError == CallError.ERROR_UNAVAILABLE) {
                                    callingState = CallingState.OFFLINE;
                                    callStateTextView.setText(s3);
                                } else if (fError == CallError.ERROR_BUSY) {
                                    callingState = CallingState.BUSY;
                                    callStateTextView.setText(s4);
                                } else if (fError == CallError.ERROR_NORESPONSE) {
                                    callingState = CallingState.NO_RESPONSE;
                                    callStateTextView.setText(s5);
                                } else if (fError == CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || fError == CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED) {
                                    callingState = CallingState.VERSION_NOT_SAME;
                                    callStateTextView.setText(R.string.call_version_inconsistent);
                                } else {
                                    if (isRefused) {
                                        callingState = CallingState.REFUSED;
                                        callStateTextView.setText(s10);
                                    } else if (isAnswered) {
                                        callingState = CallingState.NORMAL;
                                        if (endCallTriggerByMe) {
//                                        callStateTextView.setText(s6);
                                        } else {
                                            callStateTextView.setText(s7);
                                        }
                                    } else {
                                        if (isInComingCall) {
                                            callingState = CallingState.UNANSWERED;
                                            callStateTextView.setText(s8);
                                        } else {
                                            if (callingState != CallingState.NORMAL) {
                                                callingState = CallingState.CANCELLED;
                                                callStateTextView.setText(s9);
                                            } else {
                                                callStateTextView.setText(s6);
                                            }
                                        }
                                    }
                                }
                                Toast.makeText(VideoCallActivity.this, callStateTextView.getText(), Toast.LENGTH_SHORT).show();
                                postDelayedCloseMsg();
                            }

                        });

                        break;

                    default:
                        break;
                }

            }
        };
        EMClient.getInstance().callManager().addCallStateChangeListener(callStateListener);
    }

    // remove 通话状态监听
    void removeCallStateListener() {
        EMClient.getInstance().callManager().removeCallStateChangeListener(callStateListener);
    }

    @OnClick(R.id.local_surface)
    void onLocalSurfaceClick(View view) {
        changeCallView();
    }

    @OnClick(R.id.btn_refuse_call)
    void onRefuseCallBtnClick(View view) {
        isRefused = true;
        refuseBtn.setEnabled(false);
        handler.sendEmptyMessage(MSG_CALL_REJECT);
    }

    @OnClick(R.id.btn_answer_call)
    void onAnswerCallBtnClick(View view) {
        EMLog.d(TAG, "btn_answer_call clicked");
        answerBtn.setEnabled(false);
        openSpeakerOn();
        if (ringtone != null)
            ringtone.stop();

        callStateTextView.setText("answering...");
        handler.sendEmptyMessage(MSG_CALL_ANSWER);
        handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
        isAnswered = true;
        isHandsfreeState = true;
        comingBtnContainer.setVisibility(View.INVISIBLE);
        hangupBtn.setVisibility(View.VISIBLE);
        voiceContronlLayout.setVisibility(View.VISIBLE);
        localSurface.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_hangup_call)
    void onHangUpCallBtnClick(View view) {
        hangupBtn.setEnabled(false);
        chronometer.stop();
        endCallTriggerByMe = true;
        callStateTextView.setText(getResources().getString(R.string.hanging_up));
        if (isRecording) {
            callHelper.stopVideoRecord();
        }
        EMLog.d(TAG, "btn_hangup_call");
        handler.sendEmptyMessage(MSG_CALL_END);
    }

    @OnClick(R.id.iv_mute)
    void onMuteClick(View view) {
        if (isMuteState) {
            // resume voice transfer
            muteImage.setImageResource(R.drawable.em_icon_mute_normal);
            try {
                EMClient.getInstance().callManager().resumeVoiceTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
            isMuteState = false;
        } else {
            // pause voice transfer
            muteImage.setImageResource(R.drawable.em_icon_mute_on);
            try {
                EMClient.getInstance().callManager().pauseVoiceTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
            isMuteState = true;
        }
    }

    @OnClick(R.id.iv_handsfree)
    void onHandsfreeClick(View view) {
        if (isHandsfreeState) {
            // turn off speaker
            handsFreeImage.setImageResource(R.drawable.em_icon_speaker_normal);
            closeSpeakerOn();
            isHandsfreeState = false;
        } else {
            handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
            openSpeakerOn();
            isHandsfreeState = true;
        }
    }

    @OnClick(R.id.root_layout)
    void onRootLayoutClick(View view) {
        if (callingState == CallingState.NORMAL) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                bottomContainer.setVisibility(View.GONE);
                topContainer.setVisibility(View.GONE);
                oppositeSurface.setScaleMode(VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFill);

            } else {
                bottomContainer.setVisibility(View.VISIBLE);
                topContainer.setVisibility(View.VISIBLE);
                oppositeSurface.setScaleMode(VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFit);
            }
        }
    }

    @OnClick(R.id.btn_switch_camera)
    void onSwitchCameraClick(View view) {
        handler.sendEmptyMessage(MSG_CALL_SWITCH_CAMERA);
    }

    @OnClick(R.id.btn_capture_image)
    void onCaptureImageBtnClick(View view) {
        DateFormat df = new DateFormat();
        Date d = new Date();
        File storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        final String filename = storage.getAbsolutePath() + "/" + df.format("MM-dd-yy--h-mm-ss", d) + ".jpg";
        EMClient.getInstance().callManager().getVideoCallHelper().takePicture(filename);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(VideoCallActivity.this, "saved image to:" + filename, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        Helper.getInstance().isVideoCalling = false;
        if (isRecording) {
            callHelper.stopVideoRecord();
            isRecording = false;
        }
        localSurface.getRenderer().dispose();
        localSurface = null;
        oppositeSurface.getRenderer().dispose();
        oppositeSurface = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        callDruationText = chronometer.getText().toString();
        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (isInCalling) {
            try {
                EMClient.getInstance().callManager().pauseVideoTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isInCalling) {
            try {
                EMClient.getInstance().callManager().resumeVideoTransfer();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

}

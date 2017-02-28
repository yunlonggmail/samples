package com.yunlong.samples.systemfunction.tts;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

import java.util.Locale;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/28.
 * TTS主页面
 */

public class TTSActivity extends BaseActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    /**
     * 隐式意图Action
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.TTS";

    /**
     * 朗读
     */
    @Bind(R.id.btn_speech)
    Button btnSpeech;
    /**
     * 朗读文本
     */
    @Bind(R.id.et_tts_text)
    EditText etTTSText;

    private TextToSpeech mTextToSpeech;

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTextToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                speak("Language is NOT Available");
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_tts;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_tts);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnSpeech.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTextToSpeech = new TextToSpeech(mContext, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_speech:
                String text = etTTSText.getText().toString();
                if (StringsUtils.isEmpty(text)) {
                    speak("Please Input Speak World");
                    return;
                }
                speak(text);
                break;
        }
    }

    private void speak(String text) {
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

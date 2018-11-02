package com.example.zjf.mp3toaac;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btnAudioChange;
    private String[] paths = {Constants.getPath(MainActivity.this,"audio/","Charlie Puth - Look At Me Now.mp3")};
    private String[] aacPaths = {Constants.getPath(MainActivity.this,"audio/","Charlie Puth - Look At Me Now.m4a")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAudioChange = (Button)findViewById(R.id.audio_change);
        btnAudioChange.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.audio_change:
                Log.e(TAG,"点击了按钮");
                //读取指定文件夹
                String path = Constants.getPath(this,"raw/");
                File file = new File(path);
                if (file.exists() && file.isDirectory()){
                    File[] files = file.listFiles();
                    for (int i = 0;i < files.length;i++){
                        File file1 = files[i];
                        String name = file1.getName();
                        if (file1.isFile() && (name.endsWith(".mp3") || name.endsWith(".wav"))){
                            String simpleName = name.substring(0,name.indexOf("."));

                            final String pcmPath = Constants.getPath(this,"pcm/",simpleName + ".pcm");
                            final String aacPath = Constants.getPath(this,"audio/aac/",simpleName + ".m4a");
                            Log.e(TAG,"打印路径：" + file.getPath() + "          " + file.getAbsolutePath());

                            AudioCodec.getPCMFromAudio(file.getAbsolutePath() + "/" + name, pcmPath, new AudioCodec.AudioDecodeListener() {
                                @Override
                                public void decodeOver() {
                                    Log.e(TAG,"音频解码完成" + pcmPath);
                                    AudioCodec.PcmToAudio(pcmPath, aacPath, new AudioCodec.AudioDecodeListener() {
                                        @Override
                                        public void decodeOver() {
                                            Log.e(TAG,"音频编码完成");
                                        }

                                        @Override
                                        public void decodeFail() {

                                        }
                                    });
                                }

                                @Override
                                public void decodeFail() {

                                }
                            });
                        }
                    }
                }
                break;
                default:
                    break;
        }
    }
}

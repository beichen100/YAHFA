package lab.galaxy.yahfa.demoApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

import lab.galaxy.yahfa.HookMain;

public class MainActivity extends Activity {
    private static final String TAG = "origin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.hookBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method hook = Hook_Log_e.class.getDeclaredMethod("hook", String.class, String.class);
                    Method backup = Hook_Log_e.class.getDeclaredMethod("backup", String.class, String.class);
                    HookMain.findAndBackupAndHook(Log.class, Hook_Log_e.methodName, Hook_Log_e.methodSig, hook, backup);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                try {
                    SystemPropertyPook.HelloPookSystemProps(getApplication());
                    Log.e("xiehao:", "HelloPookSystemProps end");
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

                findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.getProperty("xxxxx");

                Log.e("xiehao", "test end  " + getPackageName());
            }
        });


    }
}

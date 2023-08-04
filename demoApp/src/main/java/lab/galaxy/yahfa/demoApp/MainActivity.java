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
                //                    Method hook = Hook_Log_e.class.getDeclaredMethod("hook", String.class, String.class);
//                    Method backup = Hook_Log_e.class.getDeclaredMethod("backup", String.class, String.class);
//                    HookMain.findAndBackupAndHook(Log.class, Hook_Log_e.methodName, Hook_Log_e.methodSig, hook, backup);

                HookMain.hook_all();


            }
        });



    }
}

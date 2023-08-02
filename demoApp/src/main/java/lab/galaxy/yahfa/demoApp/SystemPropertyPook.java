package lab.galaxy.yahfa.demoApp;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;
import lab.galaxy.yahfa.HookMain;

public class SystemPropertyPook {


    private static String getProperty_hook(String key, String value) {

        //android.net.Proxy.getHost()
        if (key != null && key.contains("proxyHost")) {
            key = "proxyHostppppp";
        }
        //android.net.Proxy.getHost()
        if (key != null && key.contains("proxyPort")) {
            key = "proxyHostppppp";
        }
        Log.e("Property_hook", "getProperty_hook hooked, key =:" + key);
        return getProperty_backup(key, value);
    }

    private static String getProperty_backup(String bArr, String ptr1) {

        return null;
    }

    public static void HelloPookSystemProps(Context contextsrc) {

        Class<?> systemcls = System.class;

        Method getProperty = Reflect.getDeclaredMethod(systemcls, "getProperty", String.class, String.class);
        Method getProperty_hook = Reflect.getDeclaredMethod(SystemPropertyPook.class, "getProperty_hook", String.class, String.class);
        Method getProperty_backup = Reflect.getDeclaredMethod(SystemPropertyPook.class, "getProperty_backup", String.class, String.class);

        Log.e("Property_hook", "getProperty = " + getProperty);
        Log.e("Property_hook", "getProperty_hook = " + getProperty_hook);
        Log.e("Property_hook", "getProperty_backup = " + getProperty_backup);

        String methodSig = "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;";
        HookMain.findAndBackupAndHook(systemcls, "getProperty", methodSig, getProperty_hook, getProperty_backup);

    }
}






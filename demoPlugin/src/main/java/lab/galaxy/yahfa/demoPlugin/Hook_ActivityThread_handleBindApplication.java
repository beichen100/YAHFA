package lab.galaxy.yahfa.demoPlugin;

import android.content.pm.ApplicationInfo;
import android.util.Log;

public class Hook_ActivityThread_handleBindApplication {
    public static String className = "android.app.ActivityThread";
    public static String methodName = "handleBindApplication";
    public static String methodSig = "(Landroid/app/ActivityThread$AppBindData;)V";
    private static String TAG;


    public static void hook(Object thiz, Object data) {
        Log.e(TAG, "in handleBindApplication " + String.valueOf(data));
        try {

            Class AppBindData = Class.forName("android.app.ActivityThread$AppBindData", false,
                    thiz.getClass().getClassLoader());
            ApplicationInfo applicationInfo = (ApplicationInfo) Reflect.getField(data, AppBindData, "appInfo");
            String packageName = applicationInfo.packageName.equals("android") ? "system" : applicationInfo.packageName;
            String processName = (String) Reflect.getField(data, AppBindData, "processName");
            Log.e(TAG,packageName +" ,"+processName);

            backup(thiz, data);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return;
    }

    public static void backup(Object thiz, Object data) {
        Log.w(TAG, "handleBindApplication should not be here");
        return;
    }
}

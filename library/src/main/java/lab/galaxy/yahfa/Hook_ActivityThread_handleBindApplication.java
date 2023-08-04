package lab.galaxy.yahfa;

import android.content.pm.ApplicationInfo;
import android.util.Log;

import java.util.List;

public class Hook_ActivityThread_handleBindApplication {
    public static String className = "android.app.ActivityThread";
    public static String methodName = "handleBindApplication";
    public static String methodSig = "(Landroid/app/ActivityThread$AppBindData;)V";
    public static String TAG = "hook handleBindApplication";


    public static void hook(Object thiz, Object data) {
        Log.e(TAG, "in handleBindApplication " + String.valueOf(data));
        try {

            Class AppBindData = Class.forName("android.app.ActivityThread$AppBindData", false,
                    thiz.getClass().getClassLoader());
            ApplicationInfo applicationInfo = (ApplicationInfo) ReflectUtils.getField(data, AppBindData, "appInfo");
            String packageName = applicationInfo.packageName.equals("android") ? "system" : applicationInfo.packageName;
            String processName = (String) ReflectUtils.getField(data, AppBindData, "processName");
            Log.e(TAG,packageName +" ,"+processName);

            //applyHookToApp(thiz,packageName);



            // 相当于handleBindApplication方法执行前拿到classloader
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



//
//    public static void applyHookToApp(Object thiz, String packageName) {
//        List list;
//        int i;
//        int i1;
//        List list1;
//        boolean b;
//        byte[] uobyteArray;
//        if (packageName.equals("com.android.phone") && cp7330.m1dd().m1e3()) {
//            cp853.hook(p0.getClass().getClassLoader());
//        }
//        if (cp7330.m11f().m122() && (cp7330.m11f().m124() && ((list = cp7330.m11f().m11d()) != null && (!list.isEmpty() && (list.contains(packageName) && cp853.isFuncAvailable(packageName, cp7330.m11f().m120(), "m")))))) {
//            cp844f.hookFileList(p0.getClass().getClassLoader());
//            if ((list = cp7330.m11f().m11e()) == null) {
//                uobyteArray = null;
//            }
//            if (cp7330.m11f().m123()) {
//                cp844f.hookHideXposed(p0.getClass().getClassLoader());
//            }
//            i = 1;
//            i1 = 1;
//        }else {
//            uobyteArray = null;
//            i = 0;
//            i1 = 0;
//        }
//        if (cp7330.mfe().m101() && ((list1 = cp7330.mfe().mfd()) != null && (!list1.isEmpty() && (list1.contains(packageName) && cp853.isFuncAvailable(packageName, cp7330.mfe().mff(), "k"))))) {
//            b = (cp853.isFuncAvailable(packageName, cp7330.mfe().mff(), "n"))? cp7330.mfe().m102(): false;
//            i = (i || b)? 1: 0;
//            b = 1;
//        }else {
//            b = 0;
//        }
//        if (cp7330.m1dd().m1e3() && (cp7330.m1dd().m1e4() && (cp853.isAllowMockPackage(packageName) && cp853.isFuncAvailable(packageName, cp7330.m1dd().m1e1(), "l")))) {
//            cp7330.hook(p1, p0.getClass().getClassLoader());
//        }
//        if (i1 || b) {
//            cp7330.hook(p0.getClass().getClassLoader());
//        }
//        if (b || i1) {
//            cp844f.hookSystemProperties(p0.getClass().getClassLoader());
//        }
//        if (i) {
//            LAntiDetect.md2(packageName, null, null);
//            p0 = new ArrayList();
//            if (list != null) {
//                p0.addAll(list);
//            }
//            p0.addAll(cp7330.f115);
//            String[] stringArray = new String[0];
//            LAntiDetect.setAntidetectFileNames(p0.toArray(stringArray));
//            LAntiDetect.setMocking(true);
//        }
//        return;
//    }


















}

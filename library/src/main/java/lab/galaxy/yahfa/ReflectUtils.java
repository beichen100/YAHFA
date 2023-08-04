package lab.galaxy.yahfa;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.String;
import java.lang.Class;
import java.lang.Boolean;
import java.lang.ClassLoader;
import java.lang.Object;
import java.lang.Exception;
import java.lang.ClassNotFoundException;
import java.lang.NoSuchFieldException;
import java.lang.NoSuchMethodException;
import java.util.Arrays;
import java.lang.NullPointerException;


/* renamed from: com.lerist.inject.utils.ނ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class ReflectUtils {

    /* renamed from: ֏  reason: contains not printable characters */
    private static Method f18;

    /* renamed from: ؠ  reason: contains not printable characters */
    private static Method f19;

    /* renamed from: ހ  reason: contains not printable characters */
    private static Method f20;

    static {
        try {
            f18 = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            f19 = Class.class.getDeclaredMethod("forName", String.class);
            f20 = Class.class.getDeclaredMethod("forName", String.class, Boolean.TYPE, ClassLoader.class);
        } catch (Exception unused) {
        }
    }

    /* renamed from: ֏  reason: contains not printable characters */
    public static Class m104(ClassLoader classLoader, String str) {
        return m106(str, true, classLoader);
    }

    /* renamed from: ؠ  reason: contains not printable characters */
    public static Class m105(String str) throws ClassNotFoundException {
        Method method = f19;
        if (method != null) {
            try {
                return (Class) method.invoke(null, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Class.forName(str);
    }

    /* renamed from: ހ  reason: contains not printable characters */
    public static Class m106(String str, boolean z, ClassLoader classLoader) {
        try {
            Method method = f20;
            if (method != null) {
                try {
                    return (Class) method.invoke(null, str, Boolean.valueOf(z), classLoader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return Class.forName(str, z, classLoader);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* renamed from: ށ  reason: contains not printable characters */
    public static <E> E getField(Object obj, Class cls, String str) throws NoSuchFieldException, IllegalAccessException {
        Class cls2 = cls;
        Field field = null;
        do {
            try {
                try {
                    field = cls2.getDeclaredField(str);
                    continue;
                } catch (NoSuchFieldException unused) {
                    cls2 = cls2.getSuperclass();
                    if (cls2 == null) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } while (field == null);
        if (field != null) {
            field.setAccessible(true);
            return (E) field.get(obj);
        }
        throw new NoSuchFieldException(cls.toString() + " " + str);
    }

    /* renamed from: ނ  reason: contains not printable characters */
    public static <E> E m108(Object obj, Class cls, String str, Class[] clsArr, Object[] objArr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        try {
            if (cls != null) {
                Method method = null;
                do {
                    try {
                        Method method2 = f18;
                        method = method2 != null ? (Method) method2.invoke(cls, str, clsArr) : cls.getDeclaredMethod(str, clsArr);
                        continue;
                    } catch (NoSuchMethodException unused) {
                        cls = cls.getSuperclass();
                        if (cls == null) {
                            break;
                        }
                    }
                } while (method == null);
                if (method != null) {
                    method.setAccessible(true);
                    return (E) method.invoke(obj, objArr);
                }
                throw new NoSuchMethodException(str + " " + Arrays.toString(clsArr));
            }
            throw new NullPointerException("ClassInvoker: Invoke class is null.");
        } catch (Exception e) {
            throw e;
        }
    }

    /* renamed from: ރ  reason: contains not printable characters */
    public static <E> E m109(Object obj, String str, String str2, Class[] clsArr, Object[] objArr) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return (E) m108(obj, m105(str), str2, clsArr, objArr);
    }

    /* renamed from: ބ  reason: contains not printable characters */
    public static <E> E m110(Class cls, Class[] clsArr, Object[] objArr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor declaredConstructor;
        try {
            declaredConstructor = cls.getConstructor(clsArr);
        } catch (NoSuchMethodException unused) {
            declaredConstructor = cls.getDeclaredConstructor(clsArr);
        }
        declaredConstructor.setAccessible(true);
        return (E) declaredConstructor.newInstance(objArr);
    }

    /* renamed from: ޅ  reason: contains not printable characters */
    public static void m111(Object obj, Class cls, String str, Object obj2) throws NoSuchFieldException, IllegalAccessException {
        Field field = null;
        Class cls2 = cls;
        do {
            try {
                try {
                    field = cls2.getDeclaredField(str);
                    continue;
                } catch (NoSuchFieldException unused) {
                    cls2 = cls2.getSuperclass();
                    if (cls2 == null) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } while (field == null);
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, obj2);
            return;
        }
        throw new NoSuchFieldException(cls.toString() + " " + str);
    }

    /* renamed from: ކ  reason: contains not printable characters */
    public static void m112(Object obj, Class cls, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException {
        Field field = null;
        Class cls2 = cls;
        do {
            try {
                try {
                    field = cls2.getDeclaredField(str);
                    continue;
                } catch (NoSuchFieldException unused) {
                    cls2 = cls2.getSuperclass();
                    if (cls2 == null) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } while (field == null);
        if (field == null) {
            throw new NoSuchFieldException(cls.toString() + " " + str);
        }
        field.setAccessible(true);
        Field declaredField = Field.class.getDeclaredField("accessFlags");
        declaredField.setAccessible(true);
        declaredField.setInt(field, field.getModifiers() & (-17));
        field.set(obj, obj2);
        declaredField.setInt(field, field.getModifiers() & (-17));
    }
}
//
// Created by BEICHEN on 2023/8/7.
//
#include <jni.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <dlfcn.h>


pthread_t gThread;

#if defined(__aarch64__) || defined(__x86_64__)
const char *libandroid_runtime_path = "/system/lib64/libandroid_runtime.so";
#else
const char *libandroid_runtime_path = "/system/lib/libandroid_runtime.so";
#endif

extern "C" __attribute__((visibility("default"))) int entry(char *so_parameter) {
    LOGE("[InjectModule] Inject_entry Func is called\n");
    pthread_create(&gThread, NULL, (void *(*)(void *)) _clientInit, (void *) so_parameter);

    return 0;
}


int _clientInit(const char *jarpath) {
    JNIEnv *testenv = NULL;
    void *handle;
    //依靠libandroid_runtime.so 找到JavaVM
    handle = dlopen_ex(libandroid_runtime_path, RTLD_NOW);
    LOGI("fake_dlopen for libandroid_runtime.so returned %p\n", handle);
    void *pVM = dlsym_ex(handle, "_ZN7android14AndroidRuntime7mJavaVME");

    JavaVM *javaVM = (JavaVM *) *(void **) pVM;
    LOGI("use mJavaVM returned %p\n", javaVM);
    if (javaVM) {
        jint result = javaVM->AttachCurrentThread(&testenv, 0);
        if ((result == JNI_OK) && (testenv != NULL)) {
            LOGI("attach ok. clientInit JavaVM : 0x%p, JNIEnv : 0x%p\n", javaVM, testenv);
            load_dex_and_run(testenv, jarpath);
            javaVM->DetachCurrentThread();
            LOGI("DetachCurrentThread all finished!");
        } else {
            LOGE("NOTE: attach of thread failed\n");
            return -1;
        }
    }

    return 0;
}
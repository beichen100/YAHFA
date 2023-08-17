#ifndef YAHFA_DLFUNC_H
#define YAHFA_DLFUNC_H

#include "../../../../../../AppData/Local/Android/Sdk/ndk/21.1.6352462/toolchains/llvm/prebuilt/windows-x86_64/sysroot/usr/include/jni.h"

// setup the env, must be called before the other functions
int dlfunc_init(JNIEnv* env);

// call dlopen(filename, flags)
void *dlfunc_dlopen(JNIEnv *env, const char *filename, int flags);

// call dlsym(handle, symbol)
void *dlfunc_dlsym(JNIEnv *env, void *handle, const char *symbol);

#endif //YAHFA_DLFUNC_H

#include <jni.h>

#ifndef _Included_serialport_SerialPort
#define _Included_serialport_SerialPort
#ifdef __cplusplus
extern "C" {
#endif



JNIEXPORT jobject JNICALL Java_dev_entao_app_serial_SerialPort_openfd
        (JNIEnv *, jclass, jstring, jint, jint);


JNIEXPORT void JNICALL Java_dev_entao_app_serial_SerialPort_closefd
        (JNIEnv *, jobject, jobject fdesc);

#ifdef __cplusplus
}
#endif
#endif

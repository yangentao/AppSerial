LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_LDLIBS    := -llog

TARGET_PLATFORM := android-22
LOCAL_MODULE    := serialport
LOCAL_SRC_FILES := SerialPort.c


include $(BUILD_SHARED_LIBRARY)

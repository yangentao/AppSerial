
package dev.entao.app.serial;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPort {

    private static final String TAG = "SerialPort";

    private FileDescriptor fd = null;
    private FileInputStream fileInputStream = null;
    private FileOutputStream fileOutputStream = null;


    @Nullable
    public InputStream getInputStream() {
        return fileInputStream;
    }

    @Nullable
    public OutputStream getOutputStream() {
        return fileOutputStream;
    }

    public boolean isOpen() {
        return fd != null && fileInputStream != null && fileOutputStream != null;
    }

    private boolean checkPrivilege(File device) {
        try {
            Process su = Runtime.getRuntime().exec("/system/bin/su");
            String cmd = "chmod 666 " + device.getAbsolutePath() + "\n" + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
            return (su.waitFor() == 0) && device.canRead() && device.canWrite();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean open(File device, int baudrate, int flags) throws SecurityException {
        if (!device.canRead() || !device.canWrite()) {
            if (!checkPrivilege(device)) return false;
        }
        fd = openfd(device.getAbsolutePath(), baudrate, flags);
        if (fd == null) {
            Log.e(TAG, "native open returns null");
            return false;
        }
        fileInputStream = new FileInputStream(fd);
        fileOutputStream = new FileOutputStream(fd);
        return true;
    }


    public void close() {
        try {
            if (fileInputStream != null) fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (fileOutputStream != null) fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        closefd(fd);
        fd = null;
        fileInputStream = null;
        fileOutputStream = null;
    }

    private native static FileDescriptor openfd(String path, int baudrate, int flags);

    private native void closefd(FileDescriptor fd);

    static {
        System.loadLibrary("serialport");
    }
}

package com.example.shining.libutils.utilslib.data;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by qibin on 2016/8/30.
 */

public class SerializeUtils {
    public static final int MKDIR_RETRY_COUNT = 3;

    private static ReentrantReadWriteLock sLock = new ReentrantReadWriteLock();

    public static void save(Serializable obj, String baseDir, String name) {
        sLock.writeLock().lock();
        String dir = getDir(baseDir);
        if (dir != null) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(dir + name));
                oos.writeObject(obj);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtils.closeGracefully(oos);
            }
        }

        sLock.writeLock().unlock();
    }

    public static <T> T get(String baseDir, String name) {
        sLock.readLock().lock();
        T res = null;
        String dir = getDir(baseDir);
        if (dir != null) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(dir + name));
                res = (T) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        sLock.readLock().unlock();
        return res;
    }

    public static String getDir(String baseDir) {
        File externalStorage = Environment.getExternalStorageDirectory();
        String dir = externalStorage + baseDir;
        File file = new File(dir);
        if (!new File(dir).exists()) {
            for (int current = 0; current < MKDIR_RETRY_COUNT; current++) {
                if (file.mkdirs()) { return dir;}
            }
        }

        return null;
    }
}

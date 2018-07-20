package com.example.shining.libutils.utilslib.device;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.IOException;

/**
 * 设备信息工具类
 * 
 */
public class SdcardUtil {

    /**
     * 获取sdcard剩余
     */
    @SuppressWarnings("deprecation")
	public static double getSdcardAvail() {
	if (checkCard() == 0) {
	    StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
	    long blockSize = sf.getBlockSize();
	    long availCount = sf.getAvailableBlocks();
	    return availCount * blockSize / 1024 / 1024;
	} else {
	    return 0;
	}
    }

    /**
     * 检查sdcard状态
     * 
     * @return
     */
    public static int checkCard() {
	if (!hasStorageCard()) {
	    return 1;
	}
	if (!hasStorageCardReadWritePermission()) {
	    return 2;
	}
	return 0;
    }

    /**
     * 存储卡是否已挂载
     * 
     * @return
     */
    public static boolean hasStorageCard() {
	return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 存储卡挂载成功环境下检查存储卡是否可写
     */
    public static boolean hasStorageCardReadWritePermission() {
	String directoryName = Environment.getExternalStorageDirectory().toString() + "/DCIM";
	File directory = new File(directoryName);
	if (!directory.isDirectory()) {
	    if (!directory.mkdirs()) {
		return false;
	    }
	}
	File f = new File(directoryName, ".probe");
	try {
	    // Remove stale file if any
	    if (f.exists()) {
		f.delete();
	    }
	    if (!f.createNewFile()) {
		return false;
	    }
	    f.delete();
	    return true;
	} catch (IOException ex) {
	    return false;
	}
    }

    /**
     * 获取Sdcard的状态] 0:已挂载可读写；1：未挂载；2已挂载不可读或写；3磁盘是否满
     * 
     * @return
     */
    public static int getSdcardStatus(long size) {
	if (!hasStorageCard()) {
	    return 1;
	} else {
	    if (!hasStorageCardReadWritePermission()) {
		return 2;
	    } else {
		SizeInfo memory = new SizeInfo();
		getStorageCardSize(memory);
		// 磁盘不足
		if (memory.availdSize < size) {
		    return 3;
		}
		return 0;
	    }
	}
    }

    /**
     * 外挂SD卡是否有足够空间
     * 
     * @param space
     *            （需要的空间）
     * @return
     */
    public static boolean hasSDSpace(long space) {
	SizeInfo memory = new SizeInfo();
	getStorageCardSize(memory);
	long mSDCardMemory = memory.availdSize;
	if (mSDCardMemory <= 0) {
	    return false;
	}
	if (mSDCardMemory < space) {
	    return false;
	}
	return true;
    }

    /**
     * 获取存储卡存储量
     */
    public static void getStorageCardSize(SizeInfo info) {
	if (hasStorageCard()) {
	    getSizeInfo(Environment.getExternalStorageDirectory(), info);
	} else {
	    info.availdSize = 0;
	    info.totalSize = 0;
	}
    }

    @SuppressWarnings("deprecation")
	public static void getSizeInfo(File path, SizeInfo info) {
	StatFs statfs = new StatFs(path.getPath());

	long blockSize = statfs.getBlockSize();// 获取block的SIZE
	info.availdSize = statfs.getAvailableBlocks() * blockSize;
	info.totalSize = statfs.getBlockCount() * blockSize;
    }

    public static class SizeInfo {
	/**
	 * 可用大小
	 */
	public long availdSize;

	/**
	 * 总共大小
	 */
	public long totalSize;

	/**
	 * 已用百分比
	 * 
	 * @return
	 */
	public int percent() {
	    int percent = 0;
	    if (totalSize > 0) {
		float hadused = (float) (totalSize - availdSize) / (float) totalSize;
		percent = (int) (hadused * 100);
	    }
	    return percent;
	}
    }

}

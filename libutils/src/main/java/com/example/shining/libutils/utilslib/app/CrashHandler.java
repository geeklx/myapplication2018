package com.example.shining.libutils.utilslib.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CrashHandler implements UncaughtExceptionHandler {

	public static final String DESCRIPTOR = "com.haier.smartfridgenation";
	public static final String DIR_LOG = "/haier/sfnation/log/";

	/**
	 * 是否开启日志输出,在Debug状态下开启, 在Release状态下关闭以提示程序性能
	 * */
	public static final boolean DEBUG = true;
	/** 系统默认的UncaughtException处理类 */
	private UncaughtExceptionHandler mDefaultHandler;
	/** CrashHandler实例 */
	private static CrashHandler INSTANCE;

	/** 使用Properties来保存设备的信息和错误堆栈信息 */
	private Properties mDeviceCrashInfo = new Properties();
	private static final String STACK_TRACE = "STACK_TRACE";
	/** 错误报告文件的扩展名 */
	private static final String CRASH_REPORTER_EXTENSION = ".log";

	/** 程序的Context对象 */
	 private Context mContext;
	private Class<? extends Activity> mLaunch;
	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		if (INSTANCE == null) {
			synchronized (CrashHandler.class) {
				if (INSTANCE == null) {
					INSTANCE = new CrashHandler();
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 * 
	 * @param ctx
	 */
	public void init(Context ctx, Class<? extends Activity> launch, AppCrashCallBack callback) {
		 mContext = ctx;
		mLaunch = launch;
        mCallback = callback;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {

		}

		mDefaultHandler.uncaughtException(thread, ex);

		// 在本类执行应用重启操作
		/*Intent intent = new Intent(mContext, ActivationActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
				Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
		android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
		*/
        mCallback.onAppCrashed();

		Intent intent = new Intent(mContext, mLaunch);
		PendingIntent restartIntent = PendingIntent.getActivity(
				mContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		//退出程序
		AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
				restartIntent);// 1秒钟后重启应用
//		mgr.setData(AlarmManager.RTC, System.currentTimeMillis() + 1000,
//				restartIntent); // 1秒钟后重启应用
		android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前


	}

	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return true;
		}

		new Thread() {
			@Override
			public void run() {
				saveCrashInfoToFile(ex);
			}

		}.start();
		addLog(ex);
		return true;
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 * @return
	 */
	private void addLog(Throwable ex) {
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);

		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}

		String result = info.toString();
		printWriter.close();

		MyLogUtil.d("addLog result " + result);
		// ReportUtil.action_crash(result);
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 * @return
	 */
	private String saveCrashInfoToFile(Throwable ex) {
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);

		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}

		String result = info.toString();
		printWriter.close();
		mDeviceCrashInfo.put(STACK_TRACE, result);

		FileOutputStream trace = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
			String fileName = DESCRIPTOR+"_crash_" + dateFormat.format(new Date()) + CRASH_REPORTER_EXTENSION;
			File cr = new File(Environment.getExternalStorageDirectory() + DIR_LOG, fileName);
			if (!cr.exists()) {
				File tempPath = cr.getParentFile();
				tempPath.mkdirs();
				cr.createNewFile();
			}
			trace = new FileOutputStream(cr);
			mDeviceCrashInfo.store(trace, "");
			trace.flush();
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (trace != null) {
				try {
					trace.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private AppCrashCallBack mCallback;

    public interface AppCrashCallBack{
        void onAppCrashed();
    }

}

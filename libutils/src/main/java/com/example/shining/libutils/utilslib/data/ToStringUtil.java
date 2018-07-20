package com.example.shining.libutils.utilslib.data;

import java.lang.reflect.Field;

public class ToStringUtil {
	public static String getString(Object object) {
		try {
			if (null == object) {
				return null;
			} else {
				// 获取此类所有声明的字段
				Field[] field = object.getClass().getDeclaredFields();
				// 用来拼接所需保存的字符串
				StringBuffer sb = new StringBuffer();
				// 循环此字段数组，获取属性的值
				for (int i = 0; i < field.length && field.length > 0; i++) {
					// 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查.
					field[i].setAccessible(true);
					// field[i].getName() 获取此字段的名称
					// field[i].getItem(object) 获取指定对象上此字段的值
					if (i == field.length - 1) {
						sb.append(field[i].getName() + ": "
								+ field[i].get(object) + ".");
					} else {
						sb.append(field[i].getName() + ": "
								+ field[i].get(object) + ", ");
					}
				}
				return sb.toString();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 用法
	@Override
	public String toString() {
		return ToStringUtil.getString(this);
	}*/
}

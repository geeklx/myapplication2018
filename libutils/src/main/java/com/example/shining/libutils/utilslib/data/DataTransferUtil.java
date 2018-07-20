package com.example.shining.libutils.utilslib.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataTransferUtil {

    public static final int COLOR_SIXTEEN = 0xFF000000;//色值

    public static int boolean2Int(boolean b){
        int i = 0;
        if(b){
            i = 1;
        }
        return i;
    }
    public static boolean int2Boolean(int i){
        boolean b = true;
        if(i == 0){
            b = false;
        }
        return b;
    }

    //add by shilin
    public static int bytestoInt(byte[] bytes)
    {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }

    public static float bytestoFloat(byte[] bytes)
    {
        return Float.intBitsToFloat(bytestoInt(bytes));
    }

    public static byte[] floattoBytes(float data)  //not Test1
    {
        int intBits = Float.floatToIntBits(data);
        byte[] b = new byte[4];
        for(int i = 0; i < 4; i++)
        {
            b[i] = (byte)(intBits >> i * 8);
        }
        return b;
    }

    public static String bytestoString(byte[] bytes)
    {
        String str = new String(bytes);
        return str;
    }

    public static int printHexString(byte data)
    {
        String hex = Integer.toHexString(data & 0xff);
//        MyLogUtil.i("0x" + hex);
        return 0;
    }
    //end
    //add by tuzhao
    public static short byte2Short(byte[] b){
        return (short) (((b[1] & 0xff) << 8) | (b[0] & 0xff));
    }
    public static int bytestoInt(byte[] bytes , int offset)
    {
        return (0xff & bytes[offset]) | (0xff00 & (bytes[offset+1] << 8)) | (0xff0000 & (bytes[offset+2] << 16)) | (0xff000000 & (bytes[offset+3] << 24));
    }

    public static float bytestoFloat(byte[] bytes , int offset)
    {
        int i= bytestoInt(bytes,offset);
        return Float.intBitsToFloat(i);
    }

    public static  Object deepCopy(Object o) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        return ois.readObject();
    }
    public static int getColor(String color){
        int result = Integer.parseInt(color.substring(1), 16);
        return COLOR_SIXTEEN + result;
    }
}

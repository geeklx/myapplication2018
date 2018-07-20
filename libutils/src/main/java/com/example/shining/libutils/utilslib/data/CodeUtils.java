package com.example.shining.libutils.utilslib.data;

import android.text.TextUtils;

import com.example.shining.libutils.utilslib.app.MyLogUtil;

public class CodeUtils {

    /**
     * 使用说明
     * //正确的二维码
     * String rightString = "1000100018F09FE5805F20B9F0FF1FE518F09FE51000100018F09FE5805F20B9F0FF1FE518F01BD";
     * QRcodeUtil qRcodeUtil = new QRcodeUtil(rightString);
     * if (qRcodeUtil.isRightCode()) {
     * Log.e("ShorrLog", "typeId=" + qRcodeUtil.getTypeId());
     * Log.e("ShorrLog", "Mac=" + qRcodeUtil.getMac());
     * Log.e("ShorrLog", "Vendor=" + qRcodeUtil.getVendor());
     * } else {
     * Log.e("ShorrLog", "二维码不符合规则");
     * }
     */
    private static final String TAG = "QRcodeUtil";

    //优悦二维码的总长度
    private static final int YY_CODE_LENGTH = 76;
    //北京二维码的总长度
    private static final int BJ_CODE_LENGTH = 79;

    //要解析的二维码字符串
    private String qrCodeString;
    //解析后的TypeId，Mac，厂商,校验值
    private String typeId;
    private String mac;
    private String vendor;
    private String checkNum;


    /**
     * 构造方法
     *
     * @param qrCodeString 扫描到的二维码
     */
    public CodeUtils(String qrCodeString) {
        this.qrCodeString = qrCodeString;
    }

    /**
     * 判断二维码是否符合规则
     *
     * @return true 符合规则,false 不符合规则
     */
    public boolean isRightCode() {

        //判断总长度是否正确
        if (!isTFTLength() && !isBJLength()) {
            return false;
        }

        if (isBJLength()) {
            //获取相应的字段值
            typeId = codeSubstring(0, 64);
            mac = codeSubstring(64, 76);
            vendor = codeSubstring(76, 77);
            checkNum = codeSubstring(77, 79);
            //判断校验码是否为正确的
            if (!isRightChecNum()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获取TypeId
     *
     * @return
     */
    public String getTypeId() {

        return typeId;
    }

    /**
     * 获取Mac
     *
     * @return
     */
    public String getMac() {

        return mac;
    }

    /**
     * 获取厂商代码
     *
     * @return
     */
    public String getVendor() {

        return vendor;
    }

    /**
     * 是否76位老版二维码
     *
     * @return
     */
    private boolean isTFTLength() {
        if (TextUtils.isEmpty(qrCodeString)) {
            return false;
        }
        //优悦的二维码
        if (qrCodeString.length() == YY_CODE_LENGTH) {
            // String wifiType = code.substring(0, 64);
            return true;
        }
        return false;
    }

    /**
     * 判断二维码的总长度是否正确
     *
     * @return
     */
    public boolean isBJLength() {
        if (TextUtils.isEmpty(qrCodeString)) {
            return false;
        }
        //北京的二维码
        if ((qrCodeString != null) && (qrCodeString.length() == BJ_CODE_LENGTH)) {
            return true;
        }

        return false;
    }

    /**
     * 获取二维码字符串分割的结果
     *
     * @param start 开始的位置
     * @param end   结束的位置
     * @return
     */
    private String codeSubstring(int start, int end) {
        //判断总长度是否正确
        if (!isBJLength()) {
            return null;
        }
        String subString = qrCodeString.substring(start, end);

        return subString;
    }

    /**
     * 判断校验和是否为正确的
     *
     * @return
     */
    private boolean isRightChecNum() {
        //二维码中各字节的和（校验码除外）
        int sum = 0;
        //把字符串的字节转化为整型并相加
        for (int i = 0; i < 38; i++) {
            String numString = qrCodeString.substring(i * 2, i * 2 + 2);
            try {
                int num = Integer.parseInt(numString, 16);
                sum = sum + num;
            } catch (NumberFormatException e) {
                MyLogUtil.e(TAG, "数据转化格式出错");
                return false;
            }
        }
        //再加上厂商的代表值
        int vendorNum = Integer.parseInt(vendor, 16);
        sum = sum + vendorNum;
        //计算出校验码的值
        int checkNum = 0x100 - sum % 256;

        try {
            //判断计算出的校验码的值是否与解析出的校验码匹配
            int codeCheckNum = Integer.parseInt(this.checkNum, 16);
            if (checkNum == codeCheckNum) {
                return true;
            }
        } catch (NumberFormatException e) {
            MyLogUtil.e(TAG, "数据转化格式出错");
            return false;
        }

        return false;
    }



}

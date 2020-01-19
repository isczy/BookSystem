package com.xst.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;

/**
 * ******************************************************************
 * 
 * @brief 公用的JAVA开发工具类,常用方法集整理
 * @version 0.1
 * @date 2019年12月4日 下午4:53:43
 * @author ChangZiYang
 *******************************************************************
 */
public class PublicUtil {

	private static Logger log = Logger.getLogger(PublicUtil.class);

	/************************************
	 * TODO 字符串
	 ************************************/

	/**
	 * ************************************************ 功能描述：判断字符串是否为字母
	 * 
	 * @param str
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static boolean isLetter(String str) {
		String[] array = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		for (String s : array) {
			if (StringUtils.equals(s, str))
				return true;
		}
		return false;
	}

	/**
	 * ************************************************ 功能描述：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static boolean isNumber(String str) {
		boolean bFlag = true;
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			bFlag = false;
		}
		if (!bFlag) {
			try {
				Float.parseFloat(str);
				return true;
			} catch (Exception e) {
				bFlag = false;
			}
		}
		return bFlag;
	}

	/**
	 * ************************************************ 功能描述：判断字符串是否为纯数字
	 * 
	 * @param aString
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static boolean isNumeric(String aString) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(aString);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * ************************************************ 功能描述：检验字符串是否为空
	 * 
	 * @param aString
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static boolean isEmpty(String aString) {
		boolean bResult = false;
		try {
			String strTemp = "";
			if (aString == null)
				bResult = true;
			else {
				strTemp = aString.trim();
				if (strTemp.equals("") || strTemp.equals("null"))
					bResult = true;
			}
		} catch (Exception e) {
			bResult = true;
			log.error("czy >> 检测字符串是否为空执行失败[isEmpty('" + aString + "')]，ERROR=" + e.getMessage());
		}
		return bResult;
	}

	/**
	 * ************************************************
	 * 功能描述：防止对象为空，若为空则替换为默认值,若不为空则转换对象为字符串
	 * 
	 * @param aObj
	 * @param aDefault
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringKillNull(Object aObj, String aDefault) {
		String newString = "";
		if (aObj != null) {
			newString = aObj.toString();
		}
		return isEmpty(newString) ? aDefault : newString;
	}

	/**
	 * ************************************************
	 * 功能描述：防止字符串为空（null视为空）,若为空则替换为默认值,若不为空则取原始值
	 * 
	 * @param aString
	 * @param aDefault
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringKillNull(String aString, String aDefault) {
		return isEmpty(aString) ? aDefault : aString;
	}

	/**
	 * ************************************************ 功能描述：将字符串每隔aLen位插入分节符
	 * 
	 * @param aString
	 * @param aSplitString
	 * @param aLen
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringFormatInsertSplit(String aString, String aSplitString, int aLen) {
		int start = 0;
		int end = start + aLen;
		String bStr = "";
		for (int i = 0; i < aString.length(); i++) {
			if (i % aLen == 0) {
				bStr += aString.substring(start, end) + aSplitString;
			}
			start++;
			end++;
			if (end > aString.length())
				end = aString.length();
		}
		if (!"".equals(bStr)) {
			bStr = bStr.substring(0, bStr.length() - 1);
		}

		return bStr;
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str        字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] stringToArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * ************************************************
	 * 功能描述：将字符串用aAppend在右侧补齐aLen位长度
	 * 
	 * @param aString
	 * @param aLen
	 * @param aAppend
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringFormatAppendRight(String aString, int aLen, String aAppend) {

		int blen = aString.length();
		if (aLen <= blen)
			return aString;
		for (int i = 0; i < aLen - blen; i++) {
			aString = aString + aAppend;
		}
		return aString;
	}

	/**
	 * ************************************************ 功能描述：从左侧开始移除字符串中包含的字符
	 * 
	 * @param aString
	 * @param aRemove
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringFormatRemoveLeft(String aString, String aRemove) {
		for (int i = 0; i < aString.length(); i++) {
			String temp = aString.substring(0, 1);
			if (temp.equals(aRemove)) {
				aString = aString.substring(1);
			} else {
				break;
			}
		}
		return aString;
	}

	/**
	 * ************************************************ 功能描述：从右侧开始移除字符串中包含的字符
	 * 
	 * @param aString
	 * @param aRemove
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String stringFormatRemoveRight(String aString, String aRemove) {
		for (int i = aString.length(); i >= 0; i--) {
			String temp = aString.substring(aString.length() - 1);
			if (aRemove.equals(temp)) {
				aString = aString.substring(0, aString.length() - 1);
			} else {
				break;
			}
		}
		return aString;
	}

	/**
	 * ************************************************ 功能描述：检查集合中是否有某一字符串
	 * 
	 * @param aVec-集合
	 * @param aStr-字符
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static boolean stringCheckVecValue(Vector<String> aVec, String aStr) {

		for (String tmp : aVec) {
			if (tmp.trim().equals(aStr.trim()))
				return true;
		}
		return false;
	}

	/**
	 * ************************************************ 功能描述：得到字符串中包含某个字符的数量
	 * 
	 * @param aString
	 * @param val
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static int stringHaveCharNumbers(String aString, String val) {
		if (isEmpty(aString) || isEmpty(val))
			return 0;
		int num = 0;
		for (int i = 0; i < aString.length(); i++) {
			if (String.valueOf(aString.charAt(i)).equals(val)) {
				num++;
			}
		}
		return num;
	}

	/**
	 * ************************************************
	 * 功能描述：获取字符串[str]从左往右数第n个[val]字符的索引位 n大于实际个数时返回最右侧的索引位
	 * 
	 * @param str 目标字符串
	 * @param val 指定的字符
	 * @param n   第n个
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static int stringGetIndexFromLeft(String str, String val, int n) {
		int num = 0;
		int index = -1;
		if (n < 1)
			return -1;
		for (int i = 0; i < str.length(); i++) {
			String temp = String.valueOf(str.charAt(i));
			if (temp.equals(val)) {
				num++;
				index = i;
				if (num == n) {
					return index;
				}
			}
			if (i == str.length() - 1 && num < n) {
				return index;
			}
		}
		return num;
	}

	/**
	 * ************************************************
	 * 功能描述：获取字符串[str]从右往左数第n个[val]字符的索引位 n大于实际个数时返回最左侧的索引位
	 * 
	 * @param str 目标字符串
	 * @param val 指定的字符
	 * @param n   第n个
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static int stringGetIndexFromRight(String str, String val, int n) {
		int num = 0;
		int index = -1;
		if (n < 1)
			return -1;
		for (int i = str.length() - 1; i >= 0; i--) {
			String temp = String.valueOf(str.charAt(i));
			if (temp.equals(val)) {
				num++;
				index = i;
				if (num == n) {
					return index;
				}
			}
			if (i == 0 && num < n) {
				return index;
			}
		}
		return num;
	}

	/**
	 * ************************************************ 功能描述：从JSON字符串中根据key取value值
	 * 把传入的字符串转换为json格式，根据key取出value返回 如果打不到相对应的KEY则返回null
	 * 
	 * @param key
	 * @param jsonString 需要转换成json的字符串
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月5日】
	 * @author modify:
	 */
	public static String getJsonValue(String key, String jsonString) {
		String value = "";
		try {
			JSONObject json = JSONObject.fromObject(jsonString);
			value = json.get(key).toString();
		} catch (Exception e) {
			log.error("czy >> 从JSON字符串中根据key取value值执行失败[getJsonValue('" + key + "','" + jsonString + "')]，ERROR="
					+ e.getMessage());
		}
		return value;
	}

	/************************************
	 * TODO 操作数字
	 ************************************/

	/**
	 * ************************************************ 功能描述：生成6位随机数
	 * 
	 * @return Integer
	 */
	public static Integer getRandomMath() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

	/**************************************************
	 * 功能描述：double数字转换成字符串, 保留2位小数，四舍五入
	 * 
	 * @param aDouble 要转换的数字
	 * @return String
	 */
	public static String getDoubleString4(double aDouble) {
		StringBuffer stringFormat = new StringBuffer("0");
		int aLength = 2;
		if (aLength > 0) {
			stringFormat.append(".");
			for (int i = 1; i <= aLength; i++) {
				stringFormat.append("0");
			}
		}
		DecimalFormat df = new DecimalFormat(stringFormat.toString());
		BigDecimal b = new BigDecimal(Double.toString(aDouble));
		BigDecimal one = new BigDecimal("1");
		return df.format(b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**************************************************
	 * 功能描述：double数字转换成字符串，保留2位小数，四舍五不入直接截去多余位数
	 * 
	 * @param aDouble 要转换的数字
	 * @return String
	 */
	public static String getDoubleString(double aDouble) {
		try {
			StringBuffer stringFormat = new StringBuffer("0");
			int aLength = 2;
			if (aLength > 0) {
				stringFormat.append(".");
				for (int i = 1; i <= aLength; i++) {
					stringFormat.append("0");
				}
			}
			DecimalFormat df = new DecimalFormat(stringFormat.toString());
			BigDecimal b = new BigDecimal(Double.toString(aDouble));
			BigDecimal one = new BigDecimal("1");
			return df.format(b.divide(one, 2, BigDecimal.ROUND_DOWN).doubleValue());
		} catch (Exception e) {
			log.error("czy >> double数字转换成字符串执行失败[getDoubleString(" + aDouble + ")]，ERROR=" + e.getMessage());
			return String.valueOf(aDouble);
		}
	}

	/**************************************************
	 * 功能描述：double数字转换成字符串, 保留aLength位小数，四舍五入 四舍五入取值并返回字符串
	 * 
	 * @param aDouble 要转换的数字
	 * @param aLength 小数点后保留位数
	 * @return String
	 */
	public static String getDoubleString(double aDouble, int aLength, boolean up) {
		try {
			StringBuffer stringFormat = new StringBuffer("0");
			if (aLength > 0) {
				stringFormat.append(".");
				for (int i = 1; i <= aLength; i++) {
					stringFormat.append("0");
				}
			}
			DecimalFormat df = new DecimalFormat(stringFormat.toString());
			BigDecimal b = new BigDecimal(Double.toString(aDouble));
			BigDecimal one = new BigDecimal("1");
			if (up) {
				return df.format(b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			} else {
				return df.format(b.divide(one, 2, BigDecimal.ROUND_DOWN).doubleValue());
			}
		} catch (Exception e) {
			log.error("czy >> double数字转换成字符串执行失败[getDoubleString(" + aDouble + ",'" + aLength + "','" + up
					+ "')]，ERROR=" + e.getMessage());
			return String.valueOf(aDouble);
		}
	}

	/**************************************************
	 * 功能描述：格式化Double类型数字为小数点后保留aLength位 四舍五入取值
	 * 
	 * @param aDouble 要转换的数字
	 * @param aLength 小数点后保留位数
	 * @return double
	 */
	public static double getDouble(double aDouble, int aLength, boolean up) {
		try {
			StringBuffer stringFormat = new StringBuffer("0");
			if (aLength > 0) {
				stringFormat.append(".");
				for (int i = 1; i <= aLength; i++) {
					stringFormat.append("0");
				}
			}
			DecimalFormat df = new DecimalFormat(stringFormat.toString());
			BigDecimal b = new BigDecimal(Double.toString(aDouble));
			BigDecimal one = new BigDecimal("1");
			if (up) {
				return Double.valueOf(df.format(b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue()));
			} else {
				return Double.valueOf(df.format(b.divide(one, 2, BigDecimal.ROUND_DOWN).doubleValue()));
			}
		} catch (Exception e) {
			log.error("czy >> 格式化Double类型数字为小数点后保留aLength位执行失败[getDouble(" + aDouble + ",'" + aLength + "','" + up
					+ "')]，ERROR=" + e.getMessage());
			return aDouble;
		}
	}

	private static DecimalFormat df1 = new DecimalFormat("#.0");
	private static DecimalFormat df2 = new DecimalFormat("#.00");

	/**************************************************
	 * 功能描述：格式化double字符串，小数点后保留一位，四舍五入
	 * 
	 * @param data
	 * @return double
	 */
	public static double doubleFormat1(String data) {
		return Double.parseDouble(df1.format(Double.parseDouble(data)));
	}

	/**************************************************
	 * 功能描述：格式化double数字，小数点后保留一位，四舍五入
	 * 
	 * @param data
	 * @return double
	 */
	public static double doubleFormat1(double data) {
		return Double.parseDouble(df1.format(data));
	}

	/**************************************************
	 * 功能描述：格式化double字符串，小数点后保留两位，四舍五入
	 * 
	 * @param data
	 * @return double
	 */
	public static double doubleFormat2(String data) {
		return Double.parseDouble(df2.format(Double.parseDouble(data)));
	}

	/**************************************************
	 * 功能描述：格式化double数字，小数点后保留两位，四舍五入
	 * 
	 * @param data
	 * @return double
	 */
	public static double doubleFormat2(double data) {
		return Double.parseDouble(df2.format(data));
	}

	/**************************************************
	 * 功能描述：字符串转数字整形
	 * 
	 * @param aString
	 * @param aDefault 转换失败后的默认值
	 * @return
	 */
	public static int stringFormatToInt(String aString, int aDefault) {
		int bResult = aDefault;
		try {
			if (aString != null && !aString.equals("")) {
				bResult = Integer.parseInt(aString);
			}
		} catch (Exception e) {
			log.error("czy >> 字符串转数字整形执行失败[stringFormatToInt('" + aString + "'," + aDefault + ")]，ERROR="
					+ e.getMessage());
			bResult = aDefault;
		}
		return bResult;
	}

	/**************************************************
	 * 功能描述：字符串转数字整形(四舍五入)
	 * 
	 * @param A
	 * @param aDefault 转换失败后的默认值
	 * @return
	 */
	public static int stringFormatToInt2(String aString, int aDefault) {
		if (isNumber(aString)) {
			return (int) Math.round(Double.parseDouble(aString));
		} else {
			log.error("czy >> 字符串转数字整形(四舍五入)执行失败[stringFormatToInt('" + aString + "'," + aDefault + ")]");
			return aDefault;
		}
	}

	/**************************************************
	 * 功能描述：精确乘法
	 * 
	 * @param d1 被除数
	 * @param d2 除数
	 * @return double
	 */
	public static double cheng(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}

	/**************************************************
	 * 功能描述：精确除
	 * 
	 * @param d1    被除数
	 * @param d2    除数
	 * @param scale 精确位数 建议10
	 * @return double
	 */
	public static double chu(double d1, double d2, int scale) {
		if (d2 == 0)
			return -1;
		if (scale < 0) {
			throw new IllegalArgumentException(" 除法执行失败，chu(" + d1 + "," + d2 + "," + scale + ") the scale must > 0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/************************************
	 * TODO 数据格式转换
	 ************************************/

	/**************************************************
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
	 * 
	 * @param value 要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**************************************************
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytesToInt2（）配套使用
	 */
	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**************************************************
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 * 
	 * @param src    byte数组
	 * @param offset 从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
				| ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	/**************************************************
	 * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
	 */
	public static int bytesToInt2(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 24) | ((src[offset + 1] & 0xFF) << 16) | ((src[offset + 2] & 0xFF) << 8)
				| (src[offset + 3] & 0xFF));
		return value;
	}

	/**************************************************
	 * 功能描述：十进制转换为十六进制编码
	 * 
	 * @param algorism
	 * @return String
	 */
	public static String intToHexString(int algorism) {

		String result = "";
		result = Integer.toHexString(algorism);
		if (result.length() % 2 == 1) {
			result = "0" + result;
		}
		result = result.toUpperCase();
		return result;
	}

	/**************************************************
	 * 功能描述：十六进制编码串装十进制
	 * 
	 * @param hex
	 * @return int
	 */
	public static int hexStringToInt(String hex) {

		hex = hex.toUpperCase();
		int max = hex.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = hex.charAt(i - 1);
			int algorism = 0;
			if (c >= '0' && c <= '9') {
				algorism = c - '0';
			} else {
				algorism = c - 55;
			}
			result += Math.pow(16, max - i) * algorism;
		}
		return result;
	}

	/**************************************************
	 * 功能描述：16进制转int 负数则获取补码
	 * 
	 * @param hex
	 * @return int
	 */
	public static int hexStringToInt2(String hex) {
		int bm = Integer.parseInt(hex, 16);
		if (bm >= 0x8000) {
			System.out.println("负数");
			bm = -Integer.parseInt(Integer.toBinaryString(~(bm - 1)).substring(16), 2);
		}
		return bm;
	}

	/**************************************************
	 * 功能描述：字符串转换为十六进制编码
	 * 
	 * @param aString
	 * @return String
	 */
	public static String stringToHexString(String aString) {

		return bytesToHexString(aString.getBytes());
	}

	/**************************************************
	 * 功能描述：十六进制编码转换为字符串
	 * 
	 * @param s
	 * @return String
	 */
	public static String hexStringToString(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				String tmp = s.substring(i * 2, i * 2 + 2);
				if (tmp.equals("00"))
					tmp = "20";
				baKeyword[i] = (byte) (0xff & Integer.parseInt(tmp, 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not gbk
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s.trim();
	}

	/**************************************************
	 * 功能描述：bytes转换成十六进制编码
	 * 
	 * @param b
	 * @return String
	 */
	public static String bytesToHexString(byte[] b) {

		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp + "");
			else
				hs.append(stmp + "");
			// if (n<b.length-1) hs=hs+":";
		}
		stmp = null;
		return hs.toString().toUpperCase();
	}

	/**************************************************
	 * 功能描述：十六进制编码转换成字节数组
	 * 
	 * @param hexString
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if ((hexString == null) || (hexString.equals(""))) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; ++i) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
		}
		return d;
	}

	/**************************************************
	 * 功能描述：十六进制编码转换为小端顺序
	 * 
	 * @param str
	 * @return String
	 */
	public static String hexStringToLittleString(String hexString) {

		List<String> list = new ArrayList<String>();
		int length = hexString.length();
		while (length > 0) {
			list.add(hexString.substring(hexString.length() - 2, hexString.length()));
			hexString = hexString.substring(0, hexString.length() - 2);
			length = hexString.length();
		}
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s);
		}

		return sb.toString();
	}

	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**************************************************
	 * 功能描述：生成图片默认支持JPG/PNG
	 * 
	 * @param str16    图片文件16进制码
	 * @param filePath 文件夹路径
	 * @param fileName 文件名称
	 */
	@SuppressWarnings("unused")
	public static void writeImage(String str16, String filePath, String fileName) throws Exception {
		FileOutputStream out = null;
		try {
			if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(fileName)) {
				File file = new File(filePath);
				if (!file.exists() && !file.isDirectory()) {
					PublicUtil.fileCreateAllFolder(filePath);
				}
				out = new FileOutputStream(filePath + "\\" + fileName);
				// FileChannel fc = out.getChannel();
				if (StringUtils.isEmpty(str16)) {
					log.error("ERROR >> 图片数据为空 [writeImage('" + filePath + "','" + fileName + "')]");
					return;
				}
				if (null == out) {
					log.error("ERROR >> 文件流为空 [writeImage('" + filePath + "','" + fileName + "')]");
				} else {
					out.write(PublicUtil.hexStringToBytes(str16));
				}
			}
		} catch (Exception e) {
			log.error("ERROR >> [writeImage('" + filePath + "','" + fileName + "')]" + e.getMessage());
			throw e;
		} finally {
			try {
				out.close();
				out = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**************************************************
	 * 功能描述：生成图片默认支持JPG/PNG
	 * 
	 * @param data     图片文件字节数组
	 * @param filePath 文件夹路径
	 * @param fileName 文件名称
	 */
	public static void writeImage(byte[] data, String filePath, String fileName) throws Exception {
		FileOutputStream out = null;
		try {
			if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(fileName)) {
				if (!StringUtils.equals("\\", filePath.substring(filePath.length() - 1))) {
					filePath = filePath + "\\";
				}
				File file = new File(filePath);
				if (!file.exists() && !file.isDirectory()) {
					PublicUtil.fileCreateAllFolder(filePath);
				}
				log.info("SYSTEM >> 保存图片" + filePath + fileName);
				out = new FileOutputStream(filePath + fileName);
				// FileChannel fc = out.getChannel();
				out.write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				out.close();
				out = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/************************************
	 * TODO 日期和时间
	 ************************************/

	/**
	 * ************************************************ 功能描述：时间戳转时间格式 yyyy-MM-dd
	 * HH:mm:ss
	 * 
	 * @param s
	 * @return String
	 */
	public static String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * ************************************************ 功能描述：获取系统当前时间戳
	 * @return Long
	 */
	public static Long getSystemTime() {
		return System.currentTimeMillis();
	}

	/**************************************************
	 * 功能描述：获取系统当前时间，默认标准格式
	 * 
	 * @return String
	 */
	public static String getNowTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new java.util.Date());

	}

	/**************************************************
	 * 功能描述：获取系统当前时间，默认标准格式
	 * 
	 * @return String
	 */
	public static String getNowTimeSSS() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		return formatter.format(new java.util.Date());

	}

	/**************************************************
	 * 功能描述：获取当前日期字符串
	 * 
	 * @param dateFormat 日期格式：1-yyyy-MM-dd 2-yyyy年mm月dd日
	 * @return String
	 */
	public static String getNowDate(int dateFormat) {

		Date date = new Date();
		String mm = "";
		String dd = "";
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DAY_OF_MONTH);
		if (m <= 9)
			mm = "0" + String.valueOf(m);
		else
			mm = String.valueOf(m);
		if (d <= 9)
			dd = "0" + String.valueOf(d);
		else
			dd = String.valueOf(d);
		if (dateFormat == 1)
			return String.valueOf(y) + "-" + mm + "-" + dd;
		else if (dateFormat == 2)
			return String.valueOf(y) + "年" + mm + "月" + dd + "日";
		else
			return String.valueOf(y) + mm + dd;
	}

	/**************************************************
	 * 功能描述：获取昨天日期的字符串
	 * 
	 * @return String
	 */
	public static String getYesterday() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date d = cal.getTime();
		SimpleDateFormat sp = new SimpleDateFormat("yyyyMMdd");
		String Yesterday = sp.format(d);// 获取昨天日期

		return Yesterday;
	}

	/**************************************************
	 * 功能描述：获取当前年份的字符串
	 * 
	 * @return String
	 */
	public static String getNowYear() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new java.util.Date());

	}

	/**************************************************
	 * 功能描述：获取当前日期字符串-20191205-yyyyMMdd 格式
	 * 
	 * @return String
	 */
	public static String getDayNumber() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date d = cal.getTime();
		SimpleDateFormat sp = new SimpleDateFormat("yyyyMMdd");
		String Yesterday = sp.format(d);// 获取昨天日期

		return Yesterday;
	}

	/**************************************************
	 * 功能描述：获取当前日期字符串-yyyy-MM-dd 格式
	 * 
	 * @return String
	 */
	public static String getDay() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date d = cal.getTime();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String Yesterday = sp.format(d);// 获取昨天日期

		return Yesterday;
	}

	/**************************************************
	 * 功能描述：获取日期对应的星期
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getWeekOfDate(Date aDate) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**************************************************
	 * 功能描述：获取日期字符串对应的星期
	 * 
	 * @param aDateString 日期字符串或时间字符串
	 * @return
	 */
	public static String getWeekOfDateString(String aDateString) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat datefmt = new SimpleDateFormat();
		datefmt.applyPattern("yyyy-MM-dd");// 把字符串转换为日期
		Date vdate;
		try {
			vdate = datefmt.parse(aDateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(vdate);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		} catch (ParseException e) {
			log.error("PublicUtil >> 获取日期对应的星期执行失败[getWeekOfDate('" + aDateString + "')]，ERROR=" + e.getMessage());
		}

		return null;

	}

	/**************************************************
	 * 功能描述：根据日期字符串获取日期加星期格式字符串
	 * 
	 * @param aDateString yyyy-MM-dd格式的日期字符串
	 * @return String
	 */
	public static String getDateStringAndWeek(String aDateString) {
		SimpleDateFormat datefmt = new SimpleDateFormat();
		String str = "";
		try {
			datefmt.applyPattern("yyyy-MM-dd");// 把字符串转换为日期
			Date vdate = datefmt.parse(aDateString);
			DateFormat fmt = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);// 把日期格式化为本地
			str = fmt.format(vdate);
		} catch (Exception e) {
			log.error("PublicUtil >> 获取日期加星期格式字符串执行失败[getDateStringAndWeek('" + aDateString + "')]，ERROR="
					+ e.getMessage());
		}
		return str;
	}

	/**************************************************
	 * 功能描述：根据日期字符串获取日期加星期格式字符串
	 * 
	 * @param aDateString yyyy-MM-dd格式的日期字符串
	 * @return String
	 */
	public static String getDateStringAndWeek(Date aDate) {
		String str = "";
		try {
			DateFormat fmt = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);// 把日期格式化为本地
			str = fmt.format(aDate);
		} catch (Exception e) {
			log.error("PublicUtil >> 获取日期加星期格式字符串执行失败[getDateStringAndWeek('" + aDate + "')]，ERROR=" + e.getMessage());
		}
		return str;
	}

	/**************************************************
	 * 获取两个时间相减差值
	 * 
	 * @param args
	 */
	public static int getTwoTimeMinus(String startTime, String endTime) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
		long result = 0;
		try {
			result = (d.parse(endTime).getTime() - d.parse(startTime).getTime()) / 1000;// 当前时间减去测试时间
																						// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
			// System.out.println("当前时间减去测试时间=" + result + "秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) result;
	}

	/**************************************************
	 * 功能描述：时间加减
	 * 
	 * @param aTimeString 时间字符串，精确到秒yyyy-MM-dd HH:mm:ss
	 * @param status      加减的类型0天 1小时 2分 3秒4月
	 * @param h           加减的数字可以为负数
	 * @return
	 */
	public static String timeAddOne(String aTimeString, String status, int h) {
		if (isEmpty(aTimeString)) {
			return null;
		}
		Date date = PublicUtil.stringFormatToTimestamp(aTimeString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (status.equals("0")) {// +-h天
			calendar.add(Calendar.DAY_OF_MONTH, h);
		} else if (status.equals("1")) {// +-h小时
			calendar.add(Calendar.HOUR_OF_DAY, h);
		} else if (status.equals("2")) {// +-h分钟
			calendar.add(Calendar.MINUTE, h);
		} else if (status.equals("3")) {// +-h秒
			calendar.add(Calendar.SECOND, h);
		} else if (status.equals("4")) {// +-h秒
			calendar.add(Calendar.MONTH, h);
		}
		date = calendar.getTime();
		return (formatDateToString(date, "yyyy-MM-dd HH:mm:ss"));
	}

	/**************************************************
	 * 功能描述：时间相减得到秒数
	 * 
	 * @param beginDateStr   开始时间
	 * @param endDateStr结束时间
	 * @return long
	 */
	public static long getSecondsSub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (1000);

		return day;
	}

	/**************************************************
	 * 功能描述：日期按照格式转字符串，如果格式为空则默认为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param formatDateToString
	 * @param aFormat            字符串格式 例如：yyyyMMddHHmmssSS
	 * @return
	 */
	public static String formatDateToString(Date aDate, String aFormat) {

		if (null != aDate) {
			try {
				if (isEmpty(aFormat))
					aFormat = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);// 设置日期格式
				return simpledateformat.format(aDate);
			} catch (Exception e) {
				log.error("PublicUtil >> 日期转字符串执行失败[formatDateToString('" + aDate + "'," + aFormat + ")]，ERROR="
						+ e.getMessage());
			}
		}
		return "";
	}

	/**************************************************
	 * 功能描述：时间字符串转换格式
	 * 
	 * @param aTimeString 时间字符串精确到秒
	 * @param aFormat
	 * @returnString
	 */
	public static String formatDateToString(String aTimeString, String aFormat) {

		if (StringUtils.isEmpty(aTimeString)) {
			return "";
		} else {
			try {
				SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sd2.parse(aTimeString);
				SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
				return simpledateformat.format(date);
			} catch (Exception e) {
				log.error("PublicUtil >> 字符串转日期格式执行失败[formatDateToString('" + aTimeString + "','" + aFormat
						+ "')]，ERROR=" + e.getMessage());
			}
			return "";
		}
	}

	/**************************************************
	 * 功能描述：字符串转日期时间-日期格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param aTimeString 日期字符串
	 * @return Timestamp
	 */
	public static Timestamp stringFormatToTimestamp(String aTimeString) {

		if (isEmpty(aTimeString)) {
			return null;
		}
		DateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dd.parse(aTimeString);
			String time = dd.format(date);
			Timestamp ts = Timestamp.valueOf(time);
			return ts;
		} catch (java.text.ParseException e) {
			log.error("PublicUtil >> 字符串转日期格式执行失败[stringFormatToTimestamp('" + aTimeString + "')]，ERROR="
					+ e.getMessage());
			return null;
		}

	}

	/**************************************************
	 * 功能描述：字符串转日期时间
	 * 
	 * @param aTimeString 日期字符串
	 * @return Date
	 */
	public static Date stringFormatToDate(String aTimeString) {
		if (isEmpty(aTimeString)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(aTimeString);
		} catch (ParseException e) {
			log.error("PublicUtil >> 字符串转日期格式执行失败[stringFormatToTimestamp('" + aTimeString + "')]，ERROR="
					+ e.getMessage());
		}
		return date;
	}

	/**************************************************
	 * 功能描述：是否闰年
	 * 
	 * @param year 格式- int-如：2019
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && ((year % 100 != 0) | (year % 400 == 0))) {
			return true;
		} else {
			return false;
		}
	}

	/**************************************************
	 * 功能描述：是否闰年
	 * 
	 * @param aDateString-日期字符串
	 * @return String 1-闰年 0-不是闰年 null-判断失败
	 */
	public static String isLeapYear(String aDateString) {
		try {
			int year = Integer.parseInt(formatDateToString(aDateString, "yyyyMMdd"));
			if ((year % 4 == 0) && ((year % 100 != 0) | (year % 400 == 0))) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			log.error("PublicUtil >> 是否闰年执行失败[isLeapYear('" + aDateString + "')]，ERROR=" + e.getMessage());
		}
		return null;
	}

	/**************************************************
	 * 功能描述：得到当前日期的下个月的当前日：YYYY-MM-DD 如果当前日期大于下月最后一天日期则取值下月最后一天
	 * 
	 * @return
	 */
	public static String getNextMonthDay() {
		Calendar riqi = new GregorianCalendar();
		int mm = (riqi.get(Calendar.MONTH) + 2) % 12;
		if (mm == 0)
			mm = 12;
		int dd = riqi.get(Calendar.DAY_OF_MONTH);
		if (mm == 2) {
			if (dd > 28)
				dd = 28;
		} else {
			switch (mm) {
			case 9:
			case 4:
			case 6:
			case 11:
				if (dd > 30)
					dd = 30;
				break;

			}
		}
		if (mm == 1) {
			return (riqi.get(Calendar.YEAR) + 1) + "-" + mm + "-" + dd;
		}
		return riqi.get(Calendar.YEAR) + "-" + mm + "-" + dd;
	}

	/**************************************************
	 * 功能描述：获取下一天的当前时间
	 * 
	 * @param aTimeString 时间字符串，如果为空则取当前时间
	 * @return
	 */
	public static String getNextDateTime(String aTimeString) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (isEmpty(aTimeString)) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date date = calendar.getTime();
			return sdf.format(date);
		} else {
			Date date = stringFormatToDate(aTimeString);
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			return sdf.format(calendar.getTime());
		}
	}

	/**************************************************
	 * 功能描述：工作日期判定 两个时间: 在同一天取当天,相邻两天比较时间长短,隔天取第一天
	 * 
	 * @param start 开始时间
	 * @param end   结束时间
	 */
	public static String checkDay(String start, String end) {

		try {
			if (start.compareTo(end) > 0) {
				String tmp = start;
				start = end;
				end = tmp;
			}
			if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
				if (start.substring(0, 10).equals(end.substring(0, 10))) {
					// 开始和结束时间在同一天
					return start.substring(0, 10);
				} else {
					// 开始和结束时间不在同一天
					if (PublicUtil.timeAddOne(start, "1", 24).compareTo(end) > 0) {
						// 开始和结束时间为相邻两天,没有隔天
						// 判断哪天所占比重较大
						SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						long timelong1 = (sd.parse(end.substring(0, 10) + " " + "00:00:00").getTime()
								- sd.parse(start).getTime()) / 1000;
						long timelong2 = (sd.parse(end).getTime()
								- sd.parse(end.substring(0, 10) + " " + "00:00:00").getTime()) / 1000;
						if (timelong1 < 0)
							timelong1 = timelong1 - timelong1 * 2;
						if (timelong2 < 0)
							timelong2 = timelong2 - timelong1 * 2;
						if (timelong1 >= timelong2) {
							// 前一天的比例小,判定为后一天
							System.out.println("前一天");
							return start.substring(0, 10);
						} else {
							// 后一天的比重小,判定为前一天
							System.out.println("后一天");
							return end.substring(0, 10);
						}
					} else {
						// 开始和结束时间隔了一天,
						// 取中间一天
						return start.substring(0, 10);//
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotEmpty(start))
			return start.substring(0, 10);
		else {
			if (StringUtils.isNotEmpty(end))
				return end.substring(0, 10);
			else
				return PublicUtil.getNowDate(1);
		}
	}

	/************************************
	 * TODO 文件流和图片
	 ************************************/

	/**************************************************
	 * 功能描述：读取properties配置文件键值
	 * 
	 * @param fileName 文件名称
	 * @param keyName  键名
	 * @return
	 */
	public static String getPropertiesValue(String fileName, String keyName) {
		InputStream is = PublicUtil.class.getClassLoader().getResourceAsStream(fileName + ".properties");
		Properties props = new Properties();
		try {
			if (null != is) {
				props.load(is);
			} else {
				File f = new File(System.getProperty("user.dir") + "/" + fileName + ".properties");
				is = new FileInputStream(f);
				props.load(is);
			}
			return props.getProperty(keyName);
		} catch (IOException e) {
			log.error("czy >> 读取配置文件键值执行失败[getPropertiesValue('" + fileName + ",'" + keyName + "'')]，ERROR="
					+ e.getMessage());
		}
		return null;
	}

	/**************************************************
	 * 功能描述：创建一级文件夹
	 * 
	 * @param path
	 */
	public static void fileCreateFolder(String path) {

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	/**************************************************
	 * 功能描述：创建多级文件夹
	 * 
	 * @param path
	 */
	public static void fileCreateAllFolder(String path) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		String[] list = path.split("\\\\");
		String title = list[0];
		path = title;
		for (int i = 1; i < list.length; i++) {
			path = path + "\\" + list[i];
			fileCreateFolder(path);
		}
	}

	/**************************************************
	 * 功能描述：递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir 将要删除的文件目录
	 */
	public static boolean fileDeleteAllFolder(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = fileDeleteAllFolder(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**************************************************
	 * 功能描述：递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir 将要删除的文件目录
	 */
	public static boolean fileDeleteAllFolder(String folderPath) {
		File dir = new File(folderPath);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = fileDeleteAllFolder(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/************************************
	 * TODO HTTP请求
	 ************************************/

	/**
	 * ************************************************ 
	 * 功能描述：向指定地址发送get请求，不带参数，获取返回结果
	 * 
	 * @param url
	 * @return
	 * @author create: 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月4日】
	 * @author modify:
	 * @throws IOException
	 */
	public static String get(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			// 开连接
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * ************************************************
	 * 功能描述：向指定地址发送post请求，带参数
	 * @param url
	 * @param data
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月6日】
	 * @author modify:
	 */
	public static String post(String url,String data) {
		StringBuilder sb = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			// 开连接
			URLConnection connection = urlObj.openConnection();
			//要发送数据出去，必须要设置为可发送数据状态
			connection.setDoOutput(true);
			//获取输出流
			OutputStream os = connection.getOutputStream();
			//写出数据
			os.write(data.getBytes());
			os.close();
			//获取返回的数据
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

package shiro.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author wyn
 * @email ning0930@gmail.com
 * @date 2018/6/5
 */

public class TranCharset {

    private static final String PRE_FIX_UTF = "&#x";
    private static final String POS_FIX_UTF = ";";

    public TranCharset() {
    }


    public static String XmlFormalize(String sTemp) {
        StringBuffer sb = new StringBuffer();

        if (sTemp == null || sTemp.equals("")) {
            return "";
        }
        String s = TranCharset.TranEncodeTOGB(sTemp);
        for (int i = 0; i < s.length(); i++) {
            char cChar = s.charAt(i);
            if (TranCharset.isGB2312(cChar)) {
                sb.append(PRE_FIX_UTF);
                sb.append(Integer.toHexString(cChar));
                sb.append(POS_FIX_UTF);
            } else {
                switch ((int) cChar) {
                    case 32:
                        sb.append("&#32;");
                        break;
                    case 34:
                        sb.append("&quot;");
                        break;
                    case 38:
                        sb.append("&amp;");
                        break;
                    case 60:
                        sb.append("&lt;");
                        break;
                    case 62:
                        sb.append("&gt;");
                        break;
                    default:
                        sb.append(cChar);
                }
            }
        }
        return sb.toString();
    }


    public static String TranEncodeTOGB(String str) {
        try {
            String strEncode = TranCharset.getEncoding(str);
            String temp = new String(str.getBytes(strEncode), "GB2312");
            return temp;
        } catch (IOException ex) {

            return null;
        }
    }


    public static boolean isGB2312(char c) {
        Character ch = new Character(c);
        String sCh = ch.toString();
        try {
            byte[]   bb = sCh.getBytes("gb2312");
            if (bb.length > 1) {
                return true;
            }
        } catch (java.io.UnsupportedEncodingException ex) {
            return false;
        }
        return false;
    }


    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }




    /**
     * BASE64Encoder 加密
     *
     * @param data
     *            要加密的数据
     * @return 加密后的字符串
     */
    public static String encryptBASE64(byte[] data) {
        // BASE64Encoder encoder = new BASE64Encoder();
        // String encode = encoder.encode(data);
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Encoder
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(data);
        return encode;
    }
    /**
     * BASE64Decoder 解密
     *
     * @param data
     *            要解密的字符串
     * @return 解密后的byte[]
     * @throws Exception
     */
    public static String decryptBASE64(String data) throws IOException {
        // BASE64Decoder decoder = new BASE64Decoder();
        // byte[] buffer = decoder.decodeBuffer(data);
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Decoder
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        return new String(buffer);
    }

    /**
     * 将中文与16进制转换
     * */
    private static String hexString = "0123456789ABCDEF";
    public static String enUnicode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }
    public static String deUnicode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2){
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        }
        return new String(baos.toByteArray());
    }

    /**
     * 对获得的16进制数据进行处理，高低位转换
     * */
    public static String swapHexHL(String temp){
        if (temp == null){
            return null;
        }
        String high = (String) temp.subSequence(0,2);
        String low = (String) temp.subSequence(2,4);
        String newString = low +high;
        return newString;
    }

    /**
     * 去掉XML不认可的字符0x0-0x20
     * */
    public static String delcode(String in) {
        // Used to hold the output.
        StringBuffer out = new StringBuffer();
        char current; // Used to reference the current character.
        if (in == null || ("".equals(in))){
            // vacancy test.
            return "";
        }

        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD)
                    || ((current > 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF))
                    || (current < 0x0)){
                out.append(current);
            }
        }
        return out.toString().trim();
    }


}
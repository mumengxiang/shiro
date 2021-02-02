package shiro.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author wyn
 * @email ning0930@gmail.com
 * @date 2018/4/8
 * 请求util
 */

public class RequestUtil {

    /**
     * 根据 requestParam 和 requestBody 内容生成
     * 但同时只能使用一种
     * 只有在requestParam 参数为空时 才会去requestBody中获取数据
     *
     * @param map
     * @param request
     * @retutn com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getInputParam(Map<String, String> map, HttpServletRequest request) throws Exception {
        JSONObject jsonObject;
        if (map == null || map.size() == 0) {
            jsonObject = getinputStreamParam(request, null);
        } else {
            jsonObject = new JSONObject();
            jsonObject.putAll(map);
        }
        return jsonObject;
    }

    /**
     * 自动判别字符并获取参数
     *
     * @param request
     * @retutn com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getInputParam(HttpServletRequest request) throws  Exception {
        return getInputParam(request, null);
    }

    /**
     * 根据request 分别获取 inputStream 和 queryString 的参数
     * 先获取 inputStream 如果有参数则进行返回操作 否则开始获取 queryString 参数
     * 参数会根据charset进行decode
     *
     * @param request
     * @retutn com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getInputParam(HttpServletRequest request, String charset) throws Exception {
        //inputStream
        try {
            return getinputStreamParam(request, charset);
        } catch (Exception e) {
        }

        //queryString
        try {
            JSONObject res = getqueryStringParam(request, charset);
            if (res.size() > 0) {
                return res;
            }
        } catch (Exception e) {
        }

        //ParameterMap
        if (request.getParameterMap().size() > 0) {
            return getInputStreamParamByParameterMap(request, charset);
        }

        return null;
    }

    public static JSONObject getInputStreamParamByParameterMap(HttpServletRequest request, String charset) {
        JSONObject json = new JSONObject();
        Enumeration keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            json.put(key, request.getParameter(key));
        }
        return json;
    }

    /**
     * getinputStreamParam
     *
     * @param request
     * @param charset
     * @retutn com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getinputStreamParam(HttpServletRequest request, String charset) throws Exception {
        JSONObject jsonObject;
        if (charset == null) {
            charset = "UTF-8";
        }
        java.io.InputStream dd = request.getInputStream();
        String tempRes = tgtools.util.StringUtil.parseInputStream(dd, charset);
        if (StringUtil.isNotEmpty(tempRes)) {
            jsonObject = JSON.parseObject(tempRes);
            return jsonObject;
        } else {
            throw new APPErrorException("参数错误");
        }
    }

    /**
     * getqueryStringParam
     *
     * @param request
     * @param charset
     * @retutn com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getqueryStringParam(HttpServletRequest request, String charset) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String tempRes = request.getQueryString() == null ? "" : request.getQueryString();
        if (charset == null) {
            charset = TranCharset.getEncoding(request.getQueryString());
        }
        String[] params = tempRes.split("&");
        for (String param : params) {
            String[] kv = param.split("=");
            if (kv.length < 1) {
                continue;
            }
            String value = "";
            if (kv.length >= 2) {
                value = URLDecoder.decode(kv[1], charset);
            }
            if (StringUtil.isNotEmpty(kv[0])) {
                jsonObject.put(kv[0], value);
            }

        }
        return jsonObject;
    }


}

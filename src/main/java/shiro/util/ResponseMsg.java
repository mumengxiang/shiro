package shiro.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 服务器响应消息
 * @author Falcon
 * @date 2018-04-18 14:21
 */
@Getter
@Setter
@ToString
public class ResponseMsg implements Serializable{

    private boolean status;
    private int code;

    private Object data;
    private int total;
    private String url;

    public static ResponseMsg success(Object result) {
        ResponseMsg res = new ResponseMsg();
        res.status = true;
        res.code = 0;
        res.data = result;
        return res;
    }

    public static ResponseMsg success(Object result, int total) {
        ResponseMsg res = new ResponseMsg();
        res.status = true;
        res.code = 0;
        res.data = result;
        res.total = total;
        return res;
    }

    public static ResponseMsg failure(int code, String comment) {
        ResponseMsg res = new ResponseMsg();
        res.status = false;
        res.code = code;
        res.data = comment;
        return res;
    }

    public static ResponseMsg failure(int code, String url, String comment) {
        ResponseMsg res = new ResponseMsg();
        res.status = false;
        res.code = code;
        res.data = comment;
        res.url = url;
        return res;
    }

}

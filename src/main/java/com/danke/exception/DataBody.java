package com.danke.exception;

import com.danke.bean.QQBean;
import com.danke.enums.CommonEnum;
import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2022/1/10 21:12
 * @description: TODO
 */
@Data
public class DataBody {

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public DataBody() {
    }

    public DataBody(BaseErrorInfoInterface errorInfo) {
        this.code = Integer.parseInt(errorInfo.getResultCode());
        this.msg = errorInfo.getResultMsg();
    }

    /**
     * 成功
     *
     * @return
     */
    public static DataBody success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static DataBody success(List<QQBean> data) {
        DataBody rb = new DataBody();
        rb.setCode(0);
        rb.setMsg(CommonEnum.SUCCESS.getResultMsg());
        rb.setData(data);
        rb.setCount(data.size());
        return rb;
    }

    /**
     * 失败
     */
    public static DataBody error(BaseErrorInfoInterface errorInfo) {
        DataBody rb = new DataBody();
        rb.setCode(Integer.parseInt(errorInfo.getResultCode()));
        rb.setMsg(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static DataBody error(int code, String message) {
        DataBody rb = new DataBody();
        rb.setCode(code);
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static DataBody error( String message) {
        DataBody rb = new DataBody();
        rb.setCode(-1);
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

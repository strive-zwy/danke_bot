package com.danke.exception;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/8/20 17:01
 * @Description : 自定义基础接口类
 */

public interface BaseErrorInfoInterface {
	/**
	 * 错误码
	 */
	String getResultCode();

	/**
	 * 错误描述
	 */
	String getResultMsg();
}
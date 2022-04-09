package com.le.oauth2.server.utils;


import com.alibaba.fastjson.JSON;
import com.le.oauth2.common.model.ResultMsg;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 结果封装类
 */
public class ResponseUtils {

    public static void result(HttpServletResponse response, ResultMsg msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(JSON.toJSONString(msg).getBytes("utf-8"));
        out.flush();
        out.close();
    }
}

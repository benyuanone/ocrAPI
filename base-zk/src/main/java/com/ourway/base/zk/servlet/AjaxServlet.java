package com.ourway.base.zk.servlet;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/10/22.
 */
public class AjaxServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*设置字符集为'UTF-8'*/
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String data = request.getParameter("data");
        String empId = request.getParameter("empId");
        String empLanguage = request.getParameter("empLanguage");
        String zkCookie = request.getParameter("zkCookie"); //为了与zk同一个session
        PublicData publicData = PublicData.instantce();
        publicData.setDeviceType("zkJs");//表示js端调用
        publicData.setMethod(method);
        publicData.setData(data);
        publicData.setCurrLanguage(empLanguage);
        publicData.setOpenId(empId);
        String result = "";
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false,zkCookie);
            pw.append(result);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != pw)
                pw.close();
        }
    }
}

package com.ourway.sys.api.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/19.
 */
@Controller
@RequestMapping("webSocketApi")
public class GarlicPriceController {
    @RequestMapping(value = "testWebSocket", method = {RequestMethod.POST, RequestMethod.GET})
    public String testWebSocket(HttpServletRequest request, Model model) throws IOException {
        model.addAttribute("test", "=============");
        return "testSocket";
    }
}

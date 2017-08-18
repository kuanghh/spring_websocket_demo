package com.khh.demo.sockJS.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 *
 */
@Controller
@RequestMapping(value = "/webSocket")
public class WebController {

    @RequestMapping(value = "/index")
    public String index(Model model) throws Exception{
        return "sockJS";
    }
}

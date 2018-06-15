package com.wl.controller;

import com.wl.cache.ExpressCache;
import com.wl.entity.Express;
import com.wl.entity.User;
import com.wl.service.ExpressService;
import com.wl.service.UserService;
import com.wl.socket.client.PackGetMsg;
import com.wl.socket.client.PackSendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExpressService expressService;

    @RequestMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<User> json() {
        return userService.getAllUsernames();
    }

    @RequestMapping(value = "/login"
            , method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("pwd");
        System.out.println(userName + "-----" + password);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        User user = userService.getByNameAndPwd(userName, password);
        if (user != null) {
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(user.getRole());
        } else {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "/upload"
            , method = RequestMethod.GET)
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*String barcode = request.getParameter("barcode");
        String smt = request.getParameter("smt");
        String exit = request.getParameter("exit");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        if (StringUtils.isEmpty(barcode)) {
            writer.write("条码缺失");
            return;
        }
        if (StringUtils.isEmpty(smt)) {
            writer.write("上件口缺失");
            return;
        }
        if (StringUtils.isEmpty(exit)) {
            writer.write("闸口缺失");
            return;
        }
        try {
            ClientSendMsg.sendMsgToPlc(barcode, smt, exit);
        } catch (Exception e) {
            writer.write("服务异常," + e.getMessage());
            return;
        }
        writer.write("上件成功");*/

        //expressService.save(new Express("123","231",1));
/*
Express express = expressService.getByBarCode("123");
        System.out.println("===>"+express.getHex());
*/
        expressService.saveWeight("123",2);

    }

    @RequestMapping(value = "/code"
            , method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String barcode = request.getParameter("barcode");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        Express express = null;
        try {
             express  = ExpressCache.getFromCache(barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(express==null) {
             express = expressService.getByBarCode(barcode);
        }
        if(express!=null){
           //String json = JSONObject.fromObject(express).toString();
           //writer.write(json);
        }else{
            writer.write("ERROR");
        }


    }



    @RequestMapping(value = "/express"
            , method = RequestMethod.GET)
    public void express(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String barcode = request.getParameter("barcode");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        try {

            Express express = expressService.getByBarCode("123");
            writer.write(express.getCode16() + "====>");
            new ExpressCache().loadData2Cache(expressService.findAll());


        } catch (Exception e) {
            writer.write("失败");
        }
    }
    @RequestMapping(value = "/express1"
            , method = RequestMethod.GET)
    public void express2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String barcode = request.getParameter("barcode");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        try {

            Express express = ExpressCache.getFromCache(barcode);
            writer.write(express.getCode16() + "====>");



        } catch (Exception e) {
            writer.write("失败");
        }
    }

    @RequestMapping(value = "/pack"
            , method = RequestMethod.GET)
    public void express1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String export = request.getParameter("export");

        String status = request.getParameter("status");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        try {
            Integer i = Integer.valueOf(export);
            PackSendMsg.setBytes(i, status.equals("1"));
            byte[] b = PackGetMsg.buf;

            //Integer c1 = Integer.parseInt(b[29]+"",16);
            Integer c = Integer.parseInt(b[29+i*2]+""+b[30+i*2],16);
            writer.write(c+"");
        } catch (Exception e) {
            writer.write("失败");
        }

    }


}


package com.example.demo.controller;

import com.example.demo.domain.model.DemoObj;
import com.example.demo.domain.model.DemoTest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController//相当于@Controller+@ResponseBody
public class DemoAnnoController {
//    @RequestMapping(produces = "text/plain;charset=UTF-8")
//    public String index(HttpServletRequest request) {
//        return "url" + request.getRequestURI() + " can access";
//    }

//    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
//    public String demoPathVar(@PathVariable String str, HttpServletRequest request) {
//        return "url" + request.getRequestURI() + " can access,str:" + str;
//    }
//
//    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
//    public String passRequestParam(Long id, HttpServletRequest request) {
//        return "url" + request.getRequestURI() + " can access " + id;
//    }
//
//    @RequestMapping(value = "/obj")
//    public String passObj(DemoObj obj, DemoTest test,String id, HttpServletRequest request) {
//        return "url" + request.getRequestURI() + " can access ,obj id: " + obj.getId() + " obj name: " + obj.getName()
//                +" test id:"+test.getId()+" test name:"+test.getchinesename()+" String id:"+id;
//    }
//
//    @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")
//    public String remove(HttpServletRequest request) {
//        return "url" + request.getRequestURI() + " can access ";
//    }
//
//    @RequestMapping(value="/getjson",produces = {"application/json;charset=UTF-8"})
//    public DemoObj getjson(DemoObj obj){
//        return new DemoObj(obj.getId(),obj.getName());
//    }
//
//    @RequestMapping(value="/getxml",produces = {"application/xml;charset=UTF-8"})
//    public DemoObj getxml(DemoObj obj,HttpServletResponse response){
//        response.setContentType("application/xml;charset=UTF-8");
//        return new DemoObj(obj.getId(),obj.getName());
    //}
}

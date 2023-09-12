package cn.lxmuuuu.typeformat.controller;

import cn.lxmuuuu.typeformat.request.TestRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "/test")
public class TestController {

    @GetMapping(value = "/testFormat")
    public TestRequest testFormat(){
        TestRequest request = new TestRequest();
        request.setId(11111111L);
        request.setTestDate(new Date());
        return request;
    }

}

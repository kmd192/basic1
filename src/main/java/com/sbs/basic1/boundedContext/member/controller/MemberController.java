package com.sbs.basic1.boundedContext.member.controller;

import com.sbs.basic1.boundedContext.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(){
        memberService = new MemberService();
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password){
        /*Map<String, Object> rsData = new LinkedHashMap<>(){{
            put("resultCode", "S-1");
            put("msg", "%s님 환영합니다.".formatted(username));
        }};*/
        return memberService.tryLogin(username, password);
    }
}
package com.volumeTest.volume.member.controller;


import com.volumeTest.volume.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member/*")
public class MemberController {


    private final MemberService memberService;

    //반환값을 어떻게 하실 것인지..??
    //@PostMapping("/signup")

}

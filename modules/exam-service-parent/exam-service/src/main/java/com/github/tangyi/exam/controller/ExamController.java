package com.github.tangyi.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 考试controller
 *
 * @author zdz
 * @date 2022/04/16 14:43
 */
@RestController
public class ExamController {

    @GetMapping("sayHello")
    public String sayHello(Principal principal, String name) {
        return "hello, " + name + ", principal: " + principal.toString();
    }

}

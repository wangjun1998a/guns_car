package cn.stylefeng.guns.modular.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author alin
 */
@RestController
@RequestMapping("/custom")
public class CustomController {
    private String PREFIX = "/transaction/transaction/";


    @GetMapping("/whoMade")
    public String hello() {
        return "done by WangJun";
    }

}

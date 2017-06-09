package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adaeng on 2017. 6. 9..
 */
@Controller
public class MyMainController {
    @RequestMapping("/hitest")
    public String hello(Model model) {
        model.addAttribute("hi", new String[] {"하이!!!"});
        return "hi";
    }
}

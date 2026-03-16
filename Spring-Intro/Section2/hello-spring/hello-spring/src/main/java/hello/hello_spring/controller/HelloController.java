package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // spring boot에서 자동으로 model을 만들어준다.
        model.addAttribute("data", "hello!!");
        return "hello"; // 여기서 return하는 hello는 resources/templates 아래에 있는 파일 중 hello.html을 찾아서 랜더링하게 된다.
    }
}

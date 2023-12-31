package shoppingproject.shop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/question")
    public String questionForm(){
        return "/customer/question";
    }
}

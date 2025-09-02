package desafio.transacao_api.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class RootController {
    @GetMapping("/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }

}

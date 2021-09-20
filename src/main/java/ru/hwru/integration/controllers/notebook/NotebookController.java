package ru.hwru.integration.controllers.notebook;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notebook")
public class NotebookController {


    @GetMapping
    public String index() {

        System.out.println("Hello 2");
        return "notebook/index";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

    }

}

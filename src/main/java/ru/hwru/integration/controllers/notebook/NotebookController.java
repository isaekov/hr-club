package ru.hwru.integration.controllers.notebook;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hwru.integration.entity.notebook.Notebook;
import ru.hwru.integration.service.notebook.NotebookInterfaceService;

@Controller
@RequestMapping("/notebook")
public class NotebookController {

    private final NotebookInterfaceService notebookInterfaceService;

    public NotebookController(NotebookInterfaceService notebookInterfaceService) {
        this.notebookInterfaceService = notebookInterfaceService;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("notes", notebookInterfaceService.getAll());
        model.addAttribute("notebook", new Notebook());
        return "notebook/index";
    }

    @PostMapping
    public String add(@ModelAttribute Notebook notebook) {
        notebookInterfaceService.addNote(notebook);
        return "redirect:/notebook";
    }

    @GetMapping("get/{id}")
    public String get(@PathVariable("id") int id, Model model) {

        model.addAttribute("notebook", notebookInterfaceService.getById(id));
        return "notebook/getone";
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//
//    }

}

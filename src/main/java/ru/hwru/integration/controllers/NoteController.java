package ru.hwru.integration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hwru.integration.entity.Note;
import ru.hwru.integration.repository.NoteRepository;
import ru.hwru.integration.validation.NoteFile;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/note")
    public String note(@RequestParam(name = "file", defaultValue = "0")  final int file, Model model) {
        List<Note> notes = noteRepository.findAllByTypeOrderByIdAsc(0);
        model.addAttribute("noteFile", new NoteFile());
        model.addAttribute("folders", notes);


        Map<Note, List<Note>> noteListMap = new LinkedHashMap<>();
        for (Note note : notes) {
            noteListMap.put(note, noteRepository.findAllByParentAndType(note.getId(), 1));
        }
        model.addAttribute("notes", noteListMap);
        Note note = null;
        if (file != 0) {
           note = noteRepository.findById(file);
        }
        model.addAttribute("note", note);
        model.addAttribute("paramId", file);

        return "note/index";
    }

    @PostMapping("/add-folder")
    public String addFolder(@Valid NoteFile noteFile, Principal principal) {

        Note note = new Note();
        note.setName(noteFile.getName());
        note.setType(0);
        note.setParent(noteFile.getParent());
        note.setUsername(principal.getName());
        noteRepository.saveAndFlush(note);
        return "redirect:/note";
    }

    @PostMapping("/add-file")
    public String addFile(@Valid NoteFile noteFile, Principal principal) {

        Note note = new Note();
        note.setName(noteFile.getName());
        note.setType(1);
        note.setParent(noteFile.getParent());
        note.setUsername(principal.getName());
        noteRepository.saveAndFlush(note);
        return "redirect:/note";

    }

    @PostMapping("note-save")
    public String saveNote() {
        return "redirect:/note";
    }

    @PostMapping("note/update/{id}")
    public String updateNote(@ModelAttribute("Note") Note note, @PathVariable int id, Principal principal) {
        var dbNote = noteRepository.findById(id);
        dbNote.setName(note.getName());
        dbNote.setContent(note.getContent());
        dbNote.setUsername(principal.getName());
        noteRepository.saveAndFlush(dbNote);
        return "redirect:/note?file=" + note.getId();
    }

}

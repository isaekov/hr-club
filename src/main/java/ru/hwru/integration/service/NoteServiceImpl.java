package ru.hwru.integration.service;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.Note;
import ru.hwru.integration.repository.NoteRepository;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public List<Note> allFolder() {
        return noteRepository.findAllByTypeOrderByIdAsc(0);
    }
}

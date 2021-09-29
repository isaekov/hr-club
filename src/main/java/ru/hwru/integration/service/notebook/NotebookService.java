package ru.hwru.integration.service.notebook;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.notebook.Notebook;
import ru.hwru.integration.repository.notebook.NotebookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotebookService implements NotebookInterfaceService{

    private final NotebookRepository notebookRepository;

    public NotebookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    @Override
    public void addNote(Notebook notebook) {
        notebookRepository.save(notebook);
    }

    @Override
    public List<Notebook> getAll() {
        return notebookRepository.findAll();
    }

    @Override
    public Notebook getById(int id) {
        return notebookRepository.findByIdOrError(id);
    }
}

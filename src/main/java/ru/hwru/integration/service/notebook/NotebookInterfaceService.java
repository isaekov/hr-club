package ru.hwru.integration.service.notebook;

import ru.hwru.integration.entity.notebook.Notebook;

import java.util.List;

public interface NotebookInterfaceService {

    public void addNote(Notebook notebook);

    public List<Notebook> getAll();

    public Notebook getById(int id);
}

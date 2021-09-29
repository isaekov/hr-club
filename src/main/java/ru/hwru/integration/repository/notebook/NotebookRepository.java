package ru.hwru.integration.repository.notebook;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.notebook.Notebook;

import javax.persistence.EntityNotFoundException;

public interface NotebookRepository extends JpaRepository<Notebook, Integer> {

    default Notebook findByIdOrError(Integer id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

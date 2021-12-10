package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hwru.integration.entity.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByTypeOrderByIdAsc(int type);

    List<Note> findAllByParentAndType(int parent, int type);

    Note findById(int id);

}

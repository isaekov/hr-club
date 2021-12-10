package ru.hwru.integration.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "notes")
@SequenceGenerator(name = "note_seq_gen", sequenceName = "note_seq", initialValue = 10, allocationSize = 1)

public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq_gen")
    @Column(name = "id")
    private int id;

    private String username;

    private String name;

    private int type;

    private int parent;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Note> notes;


    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

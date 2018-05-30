package com.keep.dao.entities;

import java.util.Objects;

/**
 * Note Entity
 */
public class Note {
    private long id;
    private String note;
    private Long user_id;
    private String createdDate;
    private String title;

    public Note() {
    }

    public Note(long id, String note, Long user_id, String createdDate, String title) {
        this.id = id;
        this.note = note;
        this.user_id = user_id;
        this.createdDate = createdDate;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", user_id=" + user_id +
                ", createdDate='" + createdDate + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return id == note1.id &&
                Objects.equals(note, note1.note) &&
                Objects.equals(user_id, note1.user_id) &&
                Objects.equals(createdDate, note1.createdDate) &&
                Objects.equals(title, note1.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, note, user_id, createdDate, title);
    }
}

package com.keep.dao.entities;

import java.util.Objects;

/**
 * Entity
 */
public class SharedNotes {
    private long id;
    private Long user_id;
    private Long note_id;

    public SharedNotes() {
    }

    public SharedNotes(long id, Long user_id, Long note_id) {
        this.id = id;
        this.user_id = user_id;
        this.note_id = note_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    @Override
    public String toString() {
        return "SharedNotes{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", note_id=" + note_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedNotes that = (SharedNotes) o;
        return id == that.id &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(note_id, that.note_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_id, note_id);
    }
}

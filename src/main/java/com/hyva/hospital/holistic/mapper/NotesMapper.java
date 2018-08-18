package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Notes;
import com.hyva.hospital.holistic.pojo.NotesPojo;

public class NotesMapper {

    public static Notes mapPojoToEntity(NotesPojo pojo){
        Notes notes=new Notes();
        notes.setNotes(pojo.getNotes());
        notes.setId(pojo.getId());
        return notes;
    }
}

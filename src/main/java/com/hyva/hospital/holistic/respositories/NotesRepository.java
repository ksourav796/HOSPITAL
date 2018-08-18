package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes,Long> {

    Notes findById(Long Id);
    Notes findByNotes(String Name);
    Notes findByNotesAndId(String Name,Long Id);
}

package ma.ecole.plagiat.mappers;

import ma.ecole.plagiat.dtos.ProfDTO;
import ma.ecole.plagiat.entities.Prof;
import ma.ecole.plagiat.reponses.ProfResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProfMapperTest {
    @InjectMocks
    private ProfMapper profMapper;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void toResponse() {
        Prof prof = new Prof("1", "Achraf Tarbi","achraf@gmail.com","IT", Collections.emptyList(), Collections.emptyList(),null, null);

        when(studentMapper.toResponse(null)).thenReturn(null);

        ProfResponse response = profMapper.toResponse(prof);

        assertEquals("Achraf Tarbi",response.name());
        assertEquals("achraf@gmail.com", response.email());
        assertEquals("IT", response.department());
        assertEquals(0, response.students().size());
        assertEquals(0, response.sujets().size());
    }

    @Test
    void toEntity() {
        ProfDTO profDTO = new ProfDTO("Achraf Tarbi","achraf@gmail.com","IT");

        Prof prof = profMapper.toEntity(profDTO);

        assertEquals("Achraf Tarbi", prof.getName());
        assertEquals("achraf@gmail.com", prof.getEmail());
        assertEquals("IT", prof.getDepartment());
        assertEquals(null, prof.getSujets());
        assertEquals(null, prof.getStudents());

    }
}
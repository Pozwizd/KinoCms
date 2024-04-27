package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;
import spacelab.kinocms.repository.SessionRepository;
import spacelab.kinocms.specification.SessionSpecification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImpTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionServiceImp sessionService;

    @BeforeEach
    public void setUp() {
        reset(sessionRepository);
    }

    @Test
    void testCreateSession() {
        Session session = new Session();
        sessionService.createSession(session);
        verify(sessionRepository).save(session);
    }

    @Test
    void testReadSession() {
        long id = 1L;
        Session expectedSession = new Session();
        when(sessionRepository.findById(id)).thenReturn(Optional.of(expectedSession));
        Session result = sessionService.readSession(id);
        assertEquals(expectedSession, result);
    }

    @Test
    void testGetAllSession() {
        List<Session> expectedSessions = Arrays.asList(new Session(), new Session(), new Session());
        when(sessionRepository.findAll()).thenReturn(expectedSessions);
        List<Session> result = sessionService.getAllSession();
        assertEquals(expectedSessions, result);
    }

    @Test
    void testUpdateSession() {
        Session session = new Session();
        sessionService.updateSession(session);
        verify(sessionRepository).save(session);
    }

    @Test
    void testDeleteSession() {
        long id = 1L;
        sessionService.deleteSession(Session.builder().id(id).build());
        verify(sessionRepository).deleteById(id);
    }


    @Test
    void testGetAllSessionByRequest() {
        Date dateSession = new Date(System.currentTimeMillis());
        Film film = new Film();
        Cinema cinema = new Cinema();
        Hall hall = new Hall();
        Boolean d3d = true;
        Boolean d2d = false;
        Boolean imax = true;
        List<Session> expectedSessions = new ArrayList<>();
        expectedSessions.add(new Session());
        expectedSessions.add(new Session());
        when(sessionRepository.findAll(any(SessionSpecification.class))).thenReturn(expectedSessions);
        List<Session> actualSessions = sessionService.getAllSessionByRequest(dateSession, film, cinema, hall, d3d, d2d, imax);
        assertEquals(expectedSessions, actualSessions);
    }
}
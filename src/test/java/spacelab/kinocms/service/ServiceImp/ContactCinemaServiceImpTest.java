package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.entity.page.ContactCinema;
import spacelab.kinocms.entity.page.ContactPage;
import spacelab.kinocms.repository.ContactCinemaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactCinemaServiceImpTest {

    @Mock
    private ContactCinemaRepository contactCinemaRepository;

    @InjectMocks
    private ContactCinemaServiceImp contactCinemaService;


    @BeforeEach
    public void setUp() {
        reset(contactCinemaRepository);
    }
    @Test
    void testSaveContactCinema() {
        ContactCinema contactCinema = new ContactCinema();
        contactCinemaService.saveContactCinema(contactCinema);
        verify(contactCinemaRepository).save(contactCinema);
    }

    @Test
    void testSaveContactCinemaDto() {
        ContactCinemaDto contactCinemaDto = new ContactCinemaDto();
        ContactPage contactPage = new ContactPage();
        MultipartFile mockFile = new MockMultipartFile("test.jpg", new byte[]{1, 2, 3});
        contactCinemaDto.setLogo(mockFile);
        ContactCinema existingContactCinema = new ContactCinema();
        when(contactCinemaRepository.findById(contactCinemaDto.getId())).thenReturn(Optional.of(existingContactCinema));
        contactCinemaService.saveContactCinemaDto(contactCinemaDto, contactPage);
        verify(contactCinemaRepository).saveAndFlush(existingContactCinema);
    }

    @Test
    void testGetContactCinemas() {
        List<ContactCinema> expectedContactCinemas = Arrays.asList(new ContactCinema(), new ContactCinema(), new ContactCinema());
        when(contactCinemaRepository.findAll()).thenReturn(expectedContactCinemas);
        List<ContactCinema> result = contactCinemaService.getContactCinemas();
        assertEquals(expectedContactCinemas, result);
    }

    @Test
    void testGetContactCinema() {
        long id = 1L;
        ContactCinema expectedContactCinema = new ContactCinema();
        when(contactCinemaRepository.findById(id)).thenReturn(Optional.of(expectedContactCinema));
        ContactCinema result = contactCinemaService.getContactCinema(id);
        assertEquals(expectedContactCinema, result);
    }

    @Test
    void testDeleteContactCinema() {
        long id = 1L;
        contactCinemaService.deleteContactCinema(id);
        verify(contactCinemaRepository).deleteById(id);
    }

    @Test
    void testUpdateContactCinema() {
        ContactCinema contactCinema = new ContactCinema();
        contactCinemaService.updateContactCinema(contactCinema);
        verify(contactCinemaRepository).save(contactCinema);
    }
}
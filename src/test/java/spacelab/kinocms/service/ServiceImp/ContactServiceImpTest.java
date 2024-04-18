package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.Dto.Page.ContactPageDto;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.repository.ContactPageRepository;
import spacelab.kinocms.service.ContactCinemaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceImpTest {

    @Mock
    private ContactPageRepository contactPageRepository;
    @Mock
    private ContactCinemaService contactCinemaService;
    @InjectMocks
    private ContactServiceImp contactService;

    @BeforeEach
    public void setUp() {
        reset(contactPageRepository);
    }

    @Test
    void testSaveContactPage() {
        ContactPageDto contactPageDto = new ContactPageDto();
        contactPageDto.setSeoUrl("seoUrl");
        contactPageDto.setSeoTitle("seoTitle");
        contactPageDto.setSeoDescription("seoDescription");
        contactPageDto.setSeoKeywords("seoKeywords");
        ContactCinemaDto contactCinemaDto = new ContactCinemaDto();
        contactCinemaDto.setAddress("address");
        contactCinemaDto.setId(1L);
        contactCinemaDto.setName("name");
        ContactCinemaDto contactCinemaDto1 = new ContactCinemaDto();
        contactCinemaDto1.setAddress("address");
        contactCinemaDto1.setId(2L);
        contactCinemaDto1.setName("name");
        List<ContactCinemaDto> contactCinemaDtoList = Arrays.asList(contactCinemaDto, contactCinemaDto1);
        contactPageDto.setContactCinemas(contactCinemaDtoList);
        contactService.saveContactPage(contactPageDto);
        verify(contactPageRepository, times(1)).save(any(ContactPage.class));
    }

    @Test
    public void getContactPageTest() {
        ContactPage expectedContactPage = new ContactPage();
        when(contactPageRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedContactPage));
        ContactPage actualContactPage = contactService.getContactPage();
        assertEquals(expectedContactPage, actualContactPage);
    }

    @Test
    public void deleteContactPageTest() {
        ContactPage contactPage = new ContactPage();
        contactPage.setId(1L);
        contactPage.setSeoUrl("seoUrl");
        contactService.deleteContactPage(contactPage.getId());
        verify(contactPageRepository).deleteById(contactPage.getId());
    }

    @Test
    public void updateContactPageTest() {
        ContactPage contactPage = new ContactPage();
        contactService.updateContactPage(contactPage);
        verify(contactPageRepository).save(contactPage);
    }
}
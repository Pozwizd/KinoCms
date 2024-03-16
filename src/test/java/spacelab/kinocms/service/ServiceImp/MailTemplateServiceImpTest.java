package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.MailTemplate;
import spacelab.kinocms.repository.MailTemplateRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailTemplateServiceImpTest {

    @Mock
    private MailTemplateRepository mailTemplateRepository;

    @InjectMocks
    private MailTemplateServiceImp mailTemplateService;


    @BeforeEach
    public void setUp() {
        reset(mailTemplateRepository);
    }


    @Test
    void getMailTemplateTest() {
        long id = 1L;
        MailTemplate expectedMailTemplate = new MailTemplate();
        when(mailTemplateRepository.getReferenceById(id)).thenReturn(expectedMailTemplate);
        MailTemplate actualMailTemplate = mailTemplateService.getMailTemplate(id);
        assertEquals(expectedMailTemplate, actualMailTemplate);
    }

    @Test
    void getLastMailTemplateTest() {
        MailTemplate expectedMailTemplate = new MailTemplate();
        when(mailTemplateRepository.getMailTemplateDesc()).thenReturn(expectedMailTemplate);
        MailTemplate actualMailTemplate = mailTemplateService.getLastMailTemplate();
        assertEquals(expectedMailTemplate, actualMailTemplate);
    }

    @Test
    void saveMailTemplateTest() {
        MailTemplate mailTemplate = new MailTemplate();
        mailTemplateService.saveMailTemplate(mailTemplate);
        verify(mailTemplateRepository, times(1)).save(mailTemplate);
    }

    @Test
    void getAllMailTemplateDecsTest() {
        List<MailTemplate> expectedMailTemplates = Arrays.asList(new MailTemplate(), new MailTemplate());
        when(mailTemplateRepository.findAllDesc()).thenReturn(expectedMailTemplates);

        List<MailTemplate> actualMailTemplates = mailTemplateService.getAllMailTemplateDecs();

        assertEquals(expectedMailTemplates, actualMailTemplates);
    }

    @Test
    void getAllMailTemplateTest() {
        List<MailTemplate> expectedMailTemplates = Arrays.asList(new MailTemplate(), new MailTemplate());
        when(mailTemplateRepository.findAll()).thenReturn(expectedMailTemplates);

        List<MailTemplate> actualMailTemplates = mailTemplateService.getAllMailTemplate();

        assertEquals(expectedMailTemplates, actualMailTemplates);
    }

    @Test
    void deleteMailTemplateTest() {
        long id = 1L;
        mailTemplateService.deleteMailTemplate(id);
        verify(mailTemplateRepository, times(1)).deleteById(id);
    }
}
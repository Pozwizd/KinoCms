package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.MailTemplate;
import spacelab.kinocms.repository.MailTemplateRepository;
import spacelab.kinocms.service.MailTemplateService;

import java.util.List;

@Service
@AllArgsConstructor
public class MailTemplateServiceImp implements MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;
    @Override
    public MailTemplate getMailTemplate(Long id) {
        return mailTemplateRepository.getReferenceById(id);
    }

    @Override
    public MailTemplate getLastMailTemplate() {
        return mailTemplateRepository.getMailTemplateDesc();
    }

    @Override
    public void saveMailTemplate(MailTemplate mailTemplate) {
        mailTemplateRepository.save(mailTemplate);
    }

    @Override
    public List<MailTemplate> getAllMailTemplateDecs() {
        return mailTemplateRepository.findAllDesc();
    }

    @Override
    public List<MailTemplate> getAllMailTemplate() {
        return mailTemplateRepository.findAll();
    }

    @Override
    public void deleteMailTemplate(Long id) {
        mailTemplateRepository.deleteById(id);
    }
}

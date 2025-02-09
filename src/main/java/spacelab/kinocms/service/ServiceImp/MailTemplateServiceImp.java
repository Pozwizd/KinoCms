package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.MailTemplate;
import spacelab.kinocms.repository.MailTemplateRepository;
import spacelab.kinocms.service.MailTemplateService;

import java.util.List;

@Service
@AllArgsConstructor
public class MailTemplateServiceImp implements MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;
    private static final Logger logger = LogManager.getLogger(ImageStockServiceImp.class);

    @Override
    public MailTemplate getMailTemplate(Long id) {
        logger.info("Get mail template by id: " + id);
        return mailTemplateRepository.getReferenceById(id);
    }

    @Override
    public MailTemplate getLastMailTemplate() {
        logger.info("Get last mail template");
        return mailTemplateRepository.getMailTemplateDesc();
    }

    @Override
    public void saveMailTemplate(MailTemplate mailTemplate) {
        logger.info("Save mail template: " + mailTemplate);
        mailTemplateRepository.save(mailTemplate);
    }

    @Override
    public List<MailTemplate> getAllMailTemplateDecs() {
        logger.info("Get all mail template");
        return mailTemplateRepository.findAllDesc();
    }

    @Override
    public List<MailTemplate> getAllMailTemplate() {
        logger.info("Get all mail template");
        return mailTemplateRepository.findAll();
    }

    @Override
    public void deleteMailTemplate(Long id) {
        logger.info("Delete mail template by id: " + id);
        mailTemplateRepository.deleteById(id);
    }
}

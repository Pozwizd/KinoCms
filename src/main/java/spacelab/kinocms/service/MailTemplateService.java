package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.MailTemplate;

import java.util.List;

@Service
public interface MailTemplateService {

    public MailTemplate getMailTemplate(Long id);

    public MailTemplate getLastMailTemplate();

    public void saveMailTemplate(MailTemplate mailTemplate);


    public List<MailTemplate> getAllMailTemplateDecs();

    public List<MailTemplate> getAllMailTemplate();

    public void deleteMailTemplate(Long id);
}

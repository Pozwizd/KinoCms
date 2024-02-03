package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.model.Dto.Page.ContactPageDto;

@Service
public interface ContactService {

    public void saveContactPage(ContactPageDto contactPage);
    public ContactPage getContactPage();
    public void deleteContactPage(long id);
    public void updateContactPage(ContactPage contactPage);

}

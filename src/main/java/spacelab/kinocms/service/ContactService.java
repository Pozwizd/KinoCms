package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.ContactPageDto;
import spacelab.kinocms.entity.page.ContactPage;

@Service
public interface ContactService {

    public void saveContactPage(ContactPageDto contactPage);
    public ContactPage getContactPage();
    public void deleteContactPage(long id);
    public void updateContactPage(ContactPage contactPage);

}

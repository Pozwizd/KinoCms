package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.User;
import spacelab.kinocms.model.page.ContactPage;

import java.util.List;

@Service
public interface ContactService {

    public void saveContactPage(ContactPage contactPage);
    public ContactPage getContactPage();
    public void deleteContactPage(long id);
    public void updateContactPage(ContactPage contactPage);

}

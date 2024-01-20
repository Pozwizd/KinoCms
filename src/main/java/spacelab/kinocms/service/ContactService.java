package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.model.User;
import spacelab.kinocms.model.page.ContactPage;

import java.util.List;

@Service
public interface ContactService {

    public void saveContactPage(ContactPage contactPage, List<MultipartFile> logoImage);
    public ContactPage getContactPage();
    public void deleteContactPage(long id);
    public void updateContactPage(ContactPage contactPage);

}

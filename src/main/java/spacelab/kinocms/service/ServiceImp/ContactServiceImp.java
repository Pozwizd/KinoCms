package spacelab.kinocms.service.ServiceImp;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.repository.ContactPageRepository;
import spacelab.kinocms.service.ContactService;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService {

    private final ContactPageRepository contactPageRepository;

    public ContactServiceImp(ContactPageRepository contactPageRepository) {
        this.contactPageRepository = contactPageRepository;
    }

    @Override
    public void saveContactPage(ContactPage contactPageDownload, List<MultipartFile> logoImage) {
        ContactPage contactPage = contactPageRepository.findById(1L).orElse(null);



        contactPage.setContactCinemas(contactPageDownload.getContactCinemas());
        contactPage.setSeoUrl(contactPageDownload.getSeoUrl());
        contactPage.setSeoTitle(contactPageDownload.getSeoTitle());
        contactPage.setSeoDescription(contactPageDownload.getSeoDescription());
        contactPage.setSeoKeywords(contactPageDownload.getSeoKeywords());

        contactPageRepository.save(contactPage);
    }

    @Override
    public ContactPage getContactPage() {
        return contactPageRepository.findById(1L).orElse(null);
    }

    @Override
    public void deleteContactPage(long id) {
        contactPageRepository.deleteById(id);
    }

    @Override
    public void updateContactPage(ContactPage user) {
        contactPageRepository.save(user);
    }
}

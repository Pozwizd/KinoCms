package spacelab.kinocms.service.ServiceImp;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.repository.ContactPageRepository;
import spacelab.kinocms.service.ContactService;

@Service
public class ContactServiceImp implements ContactService {

    private final ContactPageRepository contactPageRepository;

    public ContactServiceImp(ContactPageRepository contactPageRepository) {
        this.contactPageRepository = contactPageRepository;
    }

    @Override
    public void saveContactPage(ContactPage user) {
        contactPageRepository.save(user);
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

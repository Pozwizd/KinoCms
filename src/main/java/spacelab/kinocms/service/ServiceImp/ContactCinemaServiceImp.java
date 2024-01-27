package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ContactCinema;
import spacelab.kinocms.model.page.ContactCinemaDto;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.repository.ContactCinemaRepository;
import spacelab.kinocms.service.ContactCinemaService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactCinemaServiceImp implements ContactCinemaService {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";


    private final ContactCinemaRepository contactCinemaRepository;


    @Override
    public void saveContactCinema(ContactCinema contactCinema) {
        contactCinemaRepository.save(contactCinema);
    }

    @Override
    public void saveContactCinemaDto(ContactCinemaDto contactCinemaDto, ContactPage contactPage) {
        ContactCinema contactCinema = contactCinemaRepository.findById(contactCinemaDto.getId()).orElse(new ContactCinema());
        contactCinema.setId(contactCinemaDto.getId());
        contactCinema.setName(contactCinemaDto.getName());
        contactCinema.setStatus(contactCinemaDto.getStatus());
        contactCinema.setAddress(contactCinemaDto.getAddress());
        contactCinema.setLocation(contactCinemaDto.getLocation());
        contactCinema.setContactPage(contactPage);

        if(!Objects.equals(contactCinemaDto.getLogo().getOriginalFilename(), "")) {
            if (contactCinema.getLogo() != null) {
                String fileName = Paths.get("").toFile().getAbsolutePath() + contactCinema.getLogo();
                File file = new File(fileName);
                file.delete();
            }
            String fileName = UUID.randomUUID().toString() + "_" + contactCinemaDto.getLogo().getOriginalFilename();
            try {
                contactCinemaDto.getLogo().transferTo(new File(UPLOAD_FOLDER + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            contactCinema.setLogo("/images/" + fileName);
        }
        contactCinemaRepository.saveAndFlush(contactCinema);
    }

    @Override
    public List<ContactCinema> getContactCinemas() {
        return contactCinemaRepository.findAll();
    }

    @Override
    public ContactCinema getContactCinema(long id) {
        return contactCinemaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContactCinema(long id) {
        contactCinemaRepository.deleteById(id);
    }

    @Override
    public void updateContactCinema(ContactCinema contactCinema) {
        contactCinemaRepository.saveAndFlush(contactCinema);
    }
}

package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.page.ContactCinema;
import spacelab.kinocms.entity.page.ContactPage;
import spacelab.kinocms.repository.ContactCinemaRepository;
import spacelab.kinocms.service.ContactCinemaService;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ContactCinemaServiceImp implements ContactCinemaService {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";


    private final ContactCinemaRepository contactCinemaRepository;
    private final UploadFile uploadFile;

    private static final Logger logger = LogManager.getLogger(ContactCinemaServiceImp.class);

    @Override
    public void saveContactCinema(ContactCinema contactCinema) {
        contactCinemaRepository.save(contactCinema);
        logger.info("Save contact cinema: " + contactCinema);
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
            contactCinema.setLogo(uploadFile.uploadFile(contactCinemaDto.getLogo(), contactCinema.getLogo()));
        }
        contactCinemaRepository.saveAndFlush(contactCinema);
        logger.info("Save contact cinema: " + contactCinema);
    }

    @Override
    public List<ContactCinema> getContactCinemas() {
        logger.info("Get all contact cinemas");
        return contactCinemaRepository.findAll();
    }

    @Override
    public ContactCinema getContactCinema(long id) {
        logger.info("Get contact cinema by id: " + id);
        return contactCinemaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContactCinema(long id) {
        logger.info("Delete contact cinema by id: " + id);
        contactCinemaRepository.deleteById(id);
    }

    @Override
    public void updateContactCinema(ContactCinema contactCinema) {
        contactCinemaRepository.save(contactCinema);
        logger.info("Update contact cinema: " + contactCinema);
    }
}

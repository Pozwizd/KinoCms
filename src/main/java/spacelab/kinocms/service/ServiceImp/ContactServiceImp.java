package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.Dto.Page.ContactPageDto;
import spacelab.kinocms.entity.page.ContactCinema;
import spacelab.kinocms.entity.page.ContactPage;
import spacelab.kinocms.repository.ContactPageRepository;
import spacelab.kinocms.service.ContactCinemaService;
import spacelab.kinocms.service.ContactService;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImp implements ContactService {

    private final ContactPageRepository contactPageRepository;
    private final ContactCinemaService contactCinemaService;
    private static final Logger logger = LogManager.getLogger(CinemaServiceImp.class);

    @Override
    public void saveContactPage(ContactPageDto contactPageDownload) {
        ContactPage contactPage = contactPageRepository.findById(1L).orElse(null);
        if (contactPage != null) {
            contactPage.setSeoUrl(contactPageDownload.getSeoUrl());
            contactPage.setSeoTitle(contactPageDownload.getSeoTitle());
            contactPage.setSeoDescription(contactPageDownload.getSeoDescription());
            contactPage.setSeoKeywords(contactPageDownload.getSeoKeywords());
            List<ContactCinemaDto> contactCinemaDtoList = contactPageDownload.getContactCinemas();
            List<ContactCinema> contactCinemaListFromDB = contactCinemaService.getContactCinemas();
            for (ContactCinema contactCinema : contactCinemaListFromDB) {
                boolean existsInDtoList = false;

                for (ContactCinemaDto contactCinemaDto : contactCinemaDtoList) {
                    if (contactCinema.getId().equals(contactCinemaDto.getId())) {
                        existsInDtoList = true;
                        break;
                    }
                }
                if (!existsInDtoList) {
                    contactCinemaService.deleteContactCinema(contactCinema.getId());
                }
            }
            for (ContactCinemaDto contactCinemaDto : contactCinemaDtoList) {
                if (contactCinemaDto.getId() != null) {
                    contactCinemaService.saveContactCinemaDto(contactCinemaDto, contactPage);
                }
            }
        } else {
            contactPage = new ContactPage();
            contactPage.setSeoUrl(contactPageDownload.getSeoUrl());
            contactPage.setSeoTitle(contactPageDownload.getSeoTitle());
            contactPage.setSeoDescription(contactPageDownload.getSeoDescription());
            contactPage.setSeoKeywords(contactPageDownload.getSeoKeywords());
            List<ContactCinemaDto> contactCinemaDtoList = contactPageDownload.getContactCinemas();
            for (ContactCinemaDto contactCinemaDto : contactCinemaDtoList) {
                if (contactCinemaDto.getId() != null) {
                    contactCinemaService.saveContactCinemaDto(contactCinemaDto, contactPage);
                }
            }
        }
        List<ContactCinema> contactCinemaList = contactCinemaService.getContactCinemas();
        contactPage.setContactCinemas(contactCinemaList);
        contactPageRepository.save(contactPage);
        logger.info("Save contact page: " + contactPage.getName());
    }

    @Override
    public ContactPage getContactPage() {
        logger.info("Get default contact page");
        return contactPageRepository.findById(1L).orElse(null);
    }

    @Override
    public void deleteContactPage(long id) {
        contactPageRepository.deleteById(id);
        logger.info("Delete contact page with id: " + id);
    }

    @Override
    public void updateContactPage(ContactPage user) {
        logger.info("Update contact page: " + user.getName());
        contactPageRepository.save(user);
    }
}

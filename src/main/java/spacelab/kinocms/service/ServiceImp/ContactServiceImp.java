package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ContactCinema;
import spacelab.kinocms.model.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.model.Dto.Page.ContactPageDto;
import spacelab.kinocms.repository.ContactPageRepository;
import spacelab.kinocms.service.ContactCinemaService;
import spacelab.kinocms.service.ContactService;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImp implements ContactService {

    private final ContactPageRepository contactPageRepository;
    private final ContactCinemaService contactCinemaService;

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

        }

        contactPage.setContactCinemas(contactCinemaService.getContactCinemas());
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

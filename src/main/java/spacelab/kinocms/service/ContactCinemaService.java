package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ContactCinema;
import spacelab.kinocms.Dto.Page.ContactCinemaDto;
import spacelab.kinocms.model.page.ContactPage;

import java.util.List;

@Service
public interface ContactCinemaService {


    public void saveContactCinema(ContactCinema contactCinema);

    public void saveContactCinemaDto(ContactCinemaDto contactCinemaDto, ContactPage contactPage);

    public List<ContactCinema> getContactCinemas();

    public ContactCinema getContactCinema(long id);

    public void deleteContactCinema(long id);

    public void updateContactCinema(ContactCinema contactCinema);


}

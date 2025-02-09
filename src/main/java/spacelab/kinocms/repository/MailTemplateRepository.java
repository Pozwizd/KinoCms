package spacelab.kinocms.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.MailTemplate;

import java.util.List;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {


    @Query(value = "SELECT * FROM mail_template ORDER BY id DESC", nativeQuery = true)
    @NotNull
    List<MailTemplate> findAllDesc();

    @Query(value = "SELECT * FROM mail_template ORDER BY id DESC LIMIT 1", nativeQuery = true)
    @NotNull
    MailTemplate getMailTemplateDesc();
}
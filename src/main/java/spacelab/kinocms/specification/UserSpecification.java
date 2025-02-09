package spacelab.kinocms.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import spacelab.kinocms.entity.User;

public class UserSpecification implements Specification<User> {
    private final String searchValue;

    public UserSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<User> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        if (searchValue == null || searchValue.isEmpty()) {
            return null;
        }

        String lowerSearchValue = "%" + searchValue.toLowerCase() + "%";
        return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), lowerSearchValue),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), lowerSearchValue),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nickname")), lowerSearchValue),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), lowerSearchValue),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), lowerSearchValue),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), lowerSearchValue)
        );
    }
}
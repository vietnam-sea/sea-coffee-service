package org.vietnamsea.database.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.Predicate;

@NoRepositoryBean
public interface BaseSQLRepository <T extends BaseSQLEntity, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    default Page<T> searchByParameterDefault(Map<String, String> param, Pageable pageable) {
        Specification<T> spec = (root, query, criteriaBuilder) -> {
            if (param == null || param.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                List<Predicate> predicates = param.entrySet().stream()
                    .map(item -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(item.getKey()).as(String.class)), 
                            "%" + item.getValue().toLowerCase() + "%"
                        )
                    )
                    .collect(Collectors.toList());
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
        return findAll(spec,pageable);
    }

    default Page<T> searchByParameter(Map<String,String> param, Pageable pageable, Function<Map<String,String>, Specification<T>> query) {
        return findAll(query.apply(param),pageable);
    }
}

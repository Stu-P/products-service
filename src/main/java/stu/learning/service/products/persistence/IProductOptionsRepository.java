package stu.learning.service.products.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stu.learning.service.products.entities.ProductOption;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface IProductOptionsRepository extends JpaRepository<ProductOption, UUID> {
    Page<ProductOption> findByProductId(UUID productId, Pageable pageable);
    Optional<ProductOption> findByIdAndProductId(UUID id, UUID productId);
}
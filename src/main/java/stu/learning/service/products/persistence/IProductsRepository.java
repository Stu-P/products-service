package stu.learning.service.products.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stu.learning.service.products.entities.*;

import java.util.List;
import java.util.UUID;

// Ref# https://attacomsian.com/blog/derived-query-methods-spring-data-jpa#
// https://attacomsian.com/blog/spring-data-jpa-query-annotation
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#specifications


@Repository
public interface IProductsRepository extends JpaRepository<Product, UUID>
{
    List<Product> findByNameContaining(String name);

    /*
    // Examples of custom queries
   @Query("SELECT n FROM Note n WHERE n.title = ?1 AND n.featured = ?2")
    List<Note> findByTitleAndFeaturedPositionalBind(String title, boolean featured);

    // single named parameter
    @Query("SELECT n FROM Note n WHERE n.title = :title")
    List<Note> findByTitleNamedBind(@Param("title") String title);

    */

}

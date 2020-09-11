package pl.training.shop.products;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> findByDescription(String description) {
        Optional<Product> result;
        try {
            result = Optional.of(entityManager.createQuery("select p from Product p where p.description = :description", Product.class)
                    .setParameter("description", description)
                    .getSingleResult());
        } catch (NoResultException exception) {
            result = Optional.empty();
        }
        return result;
    }

}

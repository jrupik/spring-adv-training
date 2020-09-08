package pl.training.shop.products;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByType(ProductType productType) {
        return entityManager.createQuery("select p from Product p where p.type = :type", Product.class)
                .setParameter("type", productType)
                .getResultList();
    }

}

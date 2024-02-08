package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withTitleCont(params.getTitleCont())
                .and(withPriceLess(params.getPriceLt()))
                .and(withPriceGreater(params.getPriceGt()))
                .and(withRatingGreater(params.getRatingGt()))
                .and(withCategoryId(params.getCategoryId()));

    }

    private Specification<Product> withTitleCont(String titleCont) {
        return ((root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("title"), titleCont));
    }

    private Specification<Product> withPriceLess(Integer priceLt) {
        return ((root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.lessThan(root.get("price"), priceLt));
    }

    private Specification<Product> withPriceGreater(Integer priceGt) {
        return ((root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("price"), priceGt));
    }

    private Specification<Product> withRatingGreater(Double ratingGt) {
        return ((root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("rating"), ratingGt));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }
}

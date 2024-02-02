package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = " ")
    public List<Product> getByPrice(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        var productList = productRepository.findAll(Sort.by(Sort.Order.asc("price")));
        if(min != null && max == null){
            productList = productRepository.findProductsByPriceGreaterThanOrderByPriceAsc(min);
        } if (min == null && max != null){
            productList = productRepository.findProductsByPriceLessThanOrderByPriceAsc(max);
        } if (min != null && max != null){
            productList = productRepository.findProductsByPriceBetweenOrderByPriceAsc(min, max);
        }
        return productList;
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}

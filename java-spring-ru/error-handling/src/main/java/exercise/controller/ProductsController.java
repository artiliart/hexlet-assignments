package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper om;


    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Product findProductById(@PathVariable long id) throws ResourceNotFoundException{
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    @PutMapping(path = "/{id}")
    public Product updateProductById(@PathVariable long id, @RequestBody Product productNew) throws ResourceNotFoundException, JsonProcessingException {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productNew.getTitle() != null ? productNew.getTitle() : product.getTitle());
        product.setPrice(productNew.getPrice());
        return product;
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}

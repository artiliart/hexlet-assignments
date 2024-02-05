package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import java.util.stream.Collectors;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping(path = "")
    private List<ProductDTO> getAllProducts() {
        var listOfProducts = productRepository.findAll();
        return listOfProducts.stream().map(i -> productMapper.toProductDTO(i)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    private ProductDTO getProductById(@PathVariable Long id) {
        return productMapper.toProductDTO(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("1324134")));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    private Product createProduct(@RequestBody ProductCreateDTO request) {
        return productRepository.save(productMapper.toEntity(request));
    }

    @PutMapping(path = "/{id}")
    private Product updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO request) {
        return processUpdateProduct(id, request);
    }

    private Product processUpdateProduct(Long id, ProductUpdateDTO request) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("124124"));
        var mappedRequest = productMapper.toEntity(request);
        product.setCost(mappedRequest.getCost());
        return productRepository.save(product);
    }
}

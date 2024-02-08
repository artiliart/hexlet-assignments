package exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @GetMapping(path = "")
    private List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::map).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    private ProductDTO getProductById(@PathVariable Long id) {
        return productMapper.map(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("1234")));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    private ProductDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return productMapper.map(productRepository.save(productMapper.map(productCreateDTO)));
    }

    @PutMapping(path = "/{id}")
    private ProductDTO updateProduct(@PathVariable Long id , @RequestBody ProductUpdateDTO productUpdateDTO) {
        var changedProduct = productMapper.update(productUpdateDTO, productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("1241234")));
        return productMapper.map(productRepository.save(changedProduct));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}

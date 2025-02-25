package wesp.company.feirafacilapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wesp.company.feirafacilapi.domain.dtos.product.request.ProductRequest;
import wesp.company.feirafacilapi.domain.dtos.product.response.ProductResponse;
import wesp.company.feirafacilapi.domain.entities.Product;
import wesp.company.feirafacilapi.mapper.ProductMapper;
import wesp.company.feirafacilapi.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = ProductMapper.toEntity(productRequest);
        product = productRepository.save(product);
        return ProductMapper.toResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toResponseList(products);
    }

    public ProductResponse updateProduct(String code, ProductRequest productRequest) {
        Optional<Product> existingProduct = productRepository.findByCode(code);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productRequest.name());
            product.setPrice(productRequest.price());
            product.setQuantity(productRequest.quantity());
            product.setImageUrl(productRequest.imageUrl());
            product.setDescription(productRequest.description());
            product = productRepository.save(product);
            return ProductMapper.toResponse(product);
        }

        throw new IllegalArgumentException("Product not found");
    }

    public void deleteProduct(String code) {
        if (productRepository.findByCode(code).isPresent()) {
            var product = productRepository.findByCode(code).get();
            productRepository.delete(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public Product findByCode(Product product) {
        return productRepository.findByCode(product.getCode()).orElse(null);
    }
}
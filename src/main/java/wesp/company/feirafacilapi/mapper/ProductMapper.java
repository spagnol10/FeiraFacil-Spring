package wesp.company.feirafacilapi.mapper;

import wesp.company.feirafacilapi.domain.dtos.product.request.ProductRequest;
import wesp.company.feirafacilapi.domain.dtos.product.response.ProductResponse;
import wesp.company.feirafacilapi.domain.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getDescription()
        );
    }

    public static Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setCode(productRequest.code());
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setQuantity(productRequest.quantity());
        product.setImageUrl(productRequest.imageUrl());
        product.setDescription(productRequest.description());
        return product;
    }

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
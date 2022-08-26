package com.learning.ProductService.query.api.projection;

import com.learning.ProductService.command.api.data.Product;
import com.learning.ProductService.command.api.data.ProductRepository;
import com.learning.ProductService.command.api.model.ProductRestModel;
import com.learning.ProductService.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjections {
    private ProductRepository productRepository;

    public ProductProjections(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery query) {

        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModelList =
                products.stream().map(product -> ProductRestModel.builder()
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .name(product.getName())
                        .build())
                        .collect(Collectors.toList());
        return productRestModelList;
    }
}

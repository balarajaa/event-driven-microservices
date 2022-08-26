package com.learning.ProductService.command.api.controlller;

import com.learning.ProductService.command.api.commands.CreateProductCommand;
import com.learning.ProductService.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway gateway;

    public ProductCommandController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel model) {
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(model.getName())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();
        String result = gateway.sendAndWait(command);
       return result;
    }
}

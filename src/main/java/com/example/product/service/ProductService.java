package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .orElse(null);
    }
//old DTO reference
//    public ProductDTO getProductById(Long id) {
//        return productRepository.findById(id)
//                .map(this::convertToDTO)
//                .orElse(null);
//    }

//    private ProductDTO convertToDTO(Product product) {
//        ProductDTO dto = new ProductDTO();
//        dto.setId(product.getId());
//        dto.setName(product.getName());
//        dto.setDescription(product.getDescription());
//        dto.setPrice(product.getPrice());
//        return dto;
//    }
}

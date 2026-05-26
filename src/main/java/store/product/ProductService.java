package store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return StreamSupport.stream(
            productRepository.findAll().spliterator(),
            false
        ).map(ProductModel::to)
        .toList();
    }

    public Product getUmProduct(String id) {
        return productRepository.findById(id).orElse(null).to();
    }

    public Product registrarProduct(Product product) {
        return productRepository.save(
            new ProductModel(product)
        ).to();
    }

    public void deletarProduct(String id) {
        productRepository.deleteById(id);
    }
}
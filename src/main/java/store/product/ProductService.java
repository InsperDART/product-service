package store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        ProductModel product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return product.to();
    }

    public Product registrarProduct(Product product) {
        if (product.name() == "" || product.name() == null || product.price() < 0 || product.price() == null || product.unit() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return productRepository.save(
            new ProductModel(product)
        ).to();
    }

    public void deletarProduct(String id) {
        productRepository.deleteById(id);
    }
}
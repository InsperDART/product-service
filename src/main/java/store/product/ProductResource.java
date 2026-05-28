package store.product;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

@RestController
public class ProductResource implements ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Override
    public ResponseEntity<List<ProductOut>> findAll() {
        return ResponseEntity.ok(
            ProductParser.to(
                productService.getProducts()
            )
        );
    }

    @Override
    public ResponseEntity<ProductOut> findById(String id) {

        Product product = productService.getUmProduct(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ProductParser.to(product));
    }

    @Override
    public ResponseEntity<ProductOut> create(@Valid ProductIn in) {

        final Product product = productService.registrarProduct(
            ProductParser.to(in)
        );

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(product.id())
            .toUri();

        return ResponseEntity.created(location).body(ProductParser.to(product));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {

        Product product = productService.getUmProduct(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productService.deletarProduct(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
}

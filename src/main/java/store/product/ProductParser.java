package store.product;

import java.util.List;

public class ProductParser {

    public static ProductOut to(Product product) {
        return product == null ? null :
            ProductOut.builder()
                .id(product.id())
                .name(product.name())
                .price(product.price())
                .unit(product.unit())
                .build();
    }

    public static List<ProductOut> to(List<Product> l) {
        return l.stream()
                .map(ProductParser::to)
                .toList();
    }

    public static Product to(ProductIn inProduct) {
        return inProduct == null ? null :
            Product.builder()
                .name(inProduct.name())
                .price(inProduct.price())
                .unit(inProduct.unit())
                .build();
    }
}
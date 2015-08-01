package dimi.poc.elasticsearch.generator;

import dimi.poc.elasticsearch.model.Price;
import dimi.poc.elasticsearch.model.Product;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * Created by Dimitri on 1-8-2015.
 */
public class RandomProductGenerator {

    public static Product createProduct(){
        Price price = new Price();
        price.setPrice(10);
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setTitle(RandomStringUtils.randomAlphabetic(10));
        product.setDescription(RandomStringUtils.randomAlphabetic(100));
        product.setCategory(RandomStringUtils.randomAlphabetic(5));
        product.setPrice(price);
        return product;
    }
}

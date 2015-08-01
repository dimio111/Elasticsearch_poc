package dimi.poc.elasticsearch.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import dimi.poc.elasticsearch.generator.RandomProductGenerator;
import dimi.poc.elasticsearch.model.Product;
import dimi.poc.elasticsearch.service.product.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
@Api("/products")
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private ProductService productService;

    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Create a product.", response = Product.class)
    @POST
    @Path("/new")
    public boolean createProduct(Product product) {
        return productService.addProduct(product, "products", "test");
    }

    @ApiOperation("Get product.")
    @GET
    @Path("/{type}/{productId}")
    @Timed
    public Product getProduct(@PathParam("type") String type, @PathParam("productId") String productId) {
        return productService.getProduct("products", type, productId);
    }

    @ApiOperation("Create some product.")
    @GET
    @Path("/createRandomProducts/{amount}")
    @Timed
    public List<Product> createProducts(@PathParam("amount") int amount) {
        List<Product> products = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            Product product = RandomProductGenerator.createProduct();
            productService.addProduct(product, "products", product.getCategory());
            products.add(product);
        }
        return products;
    }
}

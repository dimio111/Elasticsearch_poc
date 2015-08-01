package dimi.poc.elasticsearch.resource;


import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import dimi.poc.elasticsearch.model.Product;
import dimi.poc.elasticsearch.service.product.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @ApiOperation(
            value = "Create a product.",
            response = Product.class)
    @POST
    @Path("/new")
    public boolean createProduct(Product product) {
        return productService.addProduct(product, "products", "test");
    }

    @ApiOperation("Get product.")
    @GET
    @Path("/{productId}")
    @Timed
    public Product getProduct(@PathParam("productId") String productId) {
        return productService.getProduct("products", "test", productId);
    }

    @ApiOperation("Create some product.")
    @GET
    @Path("/create")
    @Timed
    public Product getProduct() {
        Product product = new Product();
        product.setId("1");
        product.setTitle("itle1");
        product.setDescription("Desc1");
        productService.addProduct(product, "products", "test");
        return product;
    }

}

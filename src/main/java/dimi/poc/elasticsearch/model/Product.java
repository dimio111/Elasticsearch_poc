package dimi.poc.elasticsearch.model;

/**
 * Created by dimitrisaeys on 29/07/15.
 */
public class Product {

    private String elIndex;
    private String elType;
    private String id;
    private String title;
    private String description;
    private String category;
    private Price price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }


}

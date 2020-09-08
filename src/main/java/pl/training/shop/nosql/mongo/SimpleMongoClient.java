package pl.training.shop.nosql.mongo;

import com.mongodb.client.*;
import lombok.extern.java.Log;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

@Log
public class SimpleMongoClient implements Consumer<Document> {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("training");
        MongoCollection<Document> salesCollection = mongoDatabase.getCollection("sales");

        var printer = new SimpleMongoClient();
        salesCollection.find(eq("item", "abc")).forEach(printer);
        salesCollection.find(new Document("item", "abc").append("_id", 1)).forEach(printer);
    }

    @Override
    public void accept(Document document) {
        log.info(document.toJson());
    }

}

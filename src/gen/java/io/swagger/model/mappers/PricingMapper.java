package io.swagger.model.mappers;
import io.swagger.model.Pricing;


public interface PricingMapper {
    void createDatastoreObjectsIfAbsent();
    Pricing getPriceById(Long id);
}
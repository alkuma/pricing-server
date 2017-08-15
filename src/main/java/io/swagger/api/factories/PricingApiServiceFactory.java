package io.swagger.api.factories;

import io.swagger.api.PricingApiService;
import io.swagger.api.impl.PricingApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:10:27.835Z")
public class PricingApiServiceFactory {

   private final static PricingApiService service = new PricingApiServiceImpl();

   public static PricingApiService getPricingApi()
   {
      return service;
   }
}

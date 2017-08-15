package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PricingApiService;
import io.swagger.api.factories.PricingApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.ErrorModel;
import io.swagger.model.Pricing;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/pricing")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the pricing API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:10:27.835Z")
public class PricingApi  {
   private final PricingApiService delegate = PricingApiServiceFactory.getPricingApi();

    @GET
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "returns a price based on a product id", response = Pricing.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "pricing response", response = Pricing.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error", response = Pricing.class) })
    public Response findPriceById( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findPriceById(id,securityContext);
    }
}

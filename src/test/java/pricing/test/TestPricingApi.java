package pricing.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.api.PricingApi;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.jboss.resteasy.mock.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class TestPricingApi {

    @Test()
    public void testGetPricing() throws URISyntaxException {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(PricingApi.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        MockHttpRequest request = MockHttpRequest.get("/pricing/1");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        Assert.assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }
}

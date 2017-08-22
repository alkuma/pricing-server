package io.swagger.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.ErrorModel;
import io.swagger.model.Pricing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.swagger.api.NotFoundException;
import io.swagger.model.mappers.PricingMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:10:27.835Z")
public class PricingApiServiceImpl extends PricingApiService {
    @Override
    public Response findPriceById(Long id, SecurityContext securityContext)
            throws NotFoundException {
        Pricing pricing = getProductDetailsGivenId(id);

        try (SqlSession session = Datastore.openSession()) {
            PricingMapper mapper = session.getMapper(PricingMapper.class);
            Pricing p = mapper.getPriceById(id);
            if (null == p) {
                pricing.setCcy("???");
                pricing.setPrice(-1.0);
            } else {
                pricing.setCcy(p.getCcy());
                pricing.setPrice(p.getPrice());
            }
            System.out.println(pricing.toJson());
            return Response.ok().entity(pricing.toJson()).build();
        } catch (JsonProcessingException e) {
            return Response.serverError().entity(new ErrorModel(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()).toJson()).build();
        }
    }

    protected Pricing getProductDetailsGivenId(Long id) {
        Pricing pricing = new Pricing();
        pricing.setId(id);
        pricing.setName("coming soon");
        pricing.setProductType("coming soon");
        try {
            ServiceLocation sl = ServiceDiscovery.get("catalog");
            if(sl == null) {
                return pricing;
            }
            String baseUrl = sl.getBaseUrl();
            URL obj = new URL(baseUrl + "/products/" + String.valueOf(id));
            System.out.println("url is ["+obj+"]");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(1000);
            con.setReadTimeout(1000);

            con.setRequestMethod("GET");
            if (HttpServletResponse.SC_OK == con.getResponseCode()) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Response is ["+response+"]");
                JsonNode json = mapper.readTree(response.toString());
                if (json.has("name")) {
                    pricing.setName(json.get("name").asText());
                }
                if (json.has("product_type")) {
                    pricing.setProductType(json.get("product_type").asText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return pricing;
        }
        return pricing;
    }
}

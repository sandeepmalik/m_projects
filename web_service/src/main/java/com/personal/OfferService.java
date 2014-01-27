package com.personal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * @author smalik3
 * @date 1/26/14.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/offer")
public class OfferService {

    private static final Set<Offer> offers = new HashSet<Offer>();

    @POST
    @Path("/create")
    public Response create(Offer offer) {
        offer.setId(String.valueOf(System.currentTimeMillis()));
        offers.add(offer);
        return Response.ok(offer).build();
    }
}

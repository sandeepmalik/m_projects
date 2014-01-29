package com.personal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author smalik3
 * @date 1/26/14.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/offer")
public class OfferService {

    private static final Map<String, Offer> offers = new HashMap<String, Offer>();

    @POST
    @Path("/create")
    public Response create(Offer offer) {
        if (offer.getStartDate() != null) {
            if (offer.getEndDate() == null) {
                return Response.status(400).entity(new Error("endDate is needed if startDate is present.")).build();
            } else if (offer.getEndDate().before(offer.getStartDate())) {
                return Response.status(400).entity(new Error("endDate must be greater than startDate.")).build();
            }
        }
        offer.setId(String.valueOf(System.currentTimeMillis()));
        offers.put(offer.getId(), offer);
        return Response.ok(offer).build();
    }

    @GET
    public Response get(@QueryParam("id") String id) {
        if (!offers.containsKey(id)) {
            return Response.status(404).entity(new Error("Not Found")).build();
        } else {
            return Response.ok(offers.get(id)).build();
        }
    }

    @GET
    @Path("/delete")
    public Response delete(@QueryParam("id") String id) {
        if (!offers.containsKey(id)) {
            return Response.status(404).entity(new Error("Not Found")).build();
        } else {
            offers.remove(id);
            return Response.ok(offers.get(id)).build();
        }
    }
}

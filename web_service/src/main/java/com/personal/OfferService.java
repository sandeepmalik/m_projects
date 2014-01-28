package com.personal;

import javax.ws.rs.*;
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
        if (offer.getStartDate() != null) {
            if (offer.getEndDate() == null) {
                return Response.status(400).entity(new Error("endDate is needed if startDate is present.")).build();
            } else if(offer.getEndDate().before(offer.getStartDate())) {
                return Response.status(400).entity(new Error("endDate must be greater than startDate.")).build();
            }
        }
        offer.setId(String.valueOf(System.currentTimeMillis()));
        offers.add(offer);
        return Response.ok(offer).build();
    }
}

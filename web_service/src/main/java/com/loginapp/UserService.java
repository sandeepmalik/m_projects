package com.loginapp;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Author: Monika
 * Date  : 1/22/14
 * Time  : 4:36 PM
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserService {

    private static Map<String, String> valid = new HashMap<String, String>();

    private static final Set<User> users = new HashSet<User>();

    static {
        valid.put("sandeep.malik", "password1");
        valid.put("abir.malik", "password2");
        valid.put("monika.ahlawat", "password3");
    }

    @POST
    @Path("/create")
    public Response create(User user) {
        user.setId(String.valueOf(System.currentTimeMillis()));
        users.add(user);
        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        if (userId == null || password == null) {
            return Response.status(400).entity(new Gson().toJson(of("status", "User id and password are mandatory"))).build();
        } else {
            if (!valid.containsKey(userId)) {
                return Response.status(400).entity(new Gson().toJson(of("status", "Invalid user id"))).build();
            }
            if (!valid.containsValue(password)) {
                return Response.status(400).entity(new Gson().toJson(of("status", "Invalid password"))).build();
            }
            if (valid.containsKey(userId) && valid.get(userId).equals(password)) {
                return Response.ok(new Gson().toJson(of("status", "Success"))).build();
            } else {
                return Response.ok(new Gson().toJson(of("status", "User Id / Password don't match"))).build();
            }
        }
    }


}

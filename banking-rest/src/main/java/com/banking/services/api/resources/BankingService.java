package com.banking.services.api.resources;

import com.banking.services.api.providers.JwtAuth;
import com.banque.services.core.model.Client;
import com.banque.services.core.model.Compte;
import com.banque.services.core.service.CorebankingService;
import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.MessageContextImpl;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/api")
@JwtAuth
public class BankingService {

    @Context
    MessageContext messageContext;
    @Context
    HttpServletRequest httpServletRequest;
    @Autowired
    CorebankingService corebankingService;

    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    @JwtAuth
    public Response getClients() {
        messageContext = new MessageContextImpl(PhaseInterceptorChain.getCurrentMessage());
        httpServletRequest = messageContext.getHttpServletRequest();

        if (httpServletRequest.getHeader("Authorization") != null) {
            String authorization = httpServletRequest.getHeader("Authorization");

            String[] parts = authorization.split(" ");
            if (parts.length != 2 || !"Basic".equals(parts[0])) {
                return Response.status(401).header("WWW-Authenticate", "Basic").build();
            }
            String decodedValue = null;
            try {
                decodedValue = new String(Base64Utility.decode(parts[1]));
            } catch (Base64Exception ex) {
            }
            String[] namePassword = decodedValue.split(":");
            String username = namePassword[0];
            String password = namePassword[1];
            if (username != null && password != null) {
                // Traitement de la requete ici
                return Response.ok(corebankingService.clients()).build();
            } else {
                return Response.status(401).header("WWW-Authenticate", "Basic").build();
            }

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @JwtAuth
    public Response creerClient(Client client) {
        corebankingService.creerClient(client);
        return Response.ok(client).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @JwtAuth
    public Response supprimerClient(@PathParam("id") Integer id) {
        corebankingService.supprimerClient(id);
        return Response.ok().build();
    }


}

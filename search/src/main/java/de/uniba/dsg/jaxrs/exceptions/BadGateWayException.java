package de.uniba.dsg.jaxrs.exceptions;

import de.uniba.dsg.models.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BadGateWayException extends WebApplicationException {

    public BadGateWayException(ErrorMessage message) {
        super(Response.status(502).entity(message).build());
    }

}

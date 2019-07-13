package de.uniba.dsg.jaxrs.exceptions;

import de.uniba.dsg.models.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NoContentException extends WebApplicationException {

    public NoContentException(ErrorMessage message) {
        super(Response.status(204).entity(message).build());
    }
}

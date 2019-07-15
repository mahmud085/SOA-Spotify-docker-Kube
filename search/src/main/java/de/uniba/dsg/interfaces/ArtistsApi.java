package de.uniba.dsg.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author lleuschner
 */
@WebService
public interface ArtistsApi {

    @WebMethod(operationName = "search-artist")
    @WebResult(name = "artist-search-result")
    Response searchArtists(@WebParam(name = "artist") @XmlElement(required = true) String artist);
}

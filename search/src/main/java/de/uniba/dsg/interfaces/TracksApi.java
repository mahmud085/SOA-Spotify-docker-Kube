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
public interface TracksApi {

    @WebMethod(operationName = "search-tracks")
    @WebResult(name = "track-search-result")
    Response searchTrack(@WebParam(name = "title") @XmlElement(required = true) String title,
                         @WebParam(name = "artist") @XmlElement(required = true) String artist);
}

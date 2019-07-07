package de.uniba.dsg.models;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Picture implements Serializable {

    private String url;

    public Picture(String url) {
        this.url = url;
    }

    public Picture() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

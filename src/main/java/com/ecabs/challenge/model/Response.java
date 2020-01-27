package com.ecabs.challenge.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class Response implements Serializable {
    private static final long serialVersionUID = 7262714087701553678L;
    private int code;
    private String description;

}


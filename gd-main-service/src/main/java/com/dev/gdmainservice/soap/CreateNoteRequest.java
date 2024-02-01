package com.dev.gdmainservice.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "createNoteRequest")
@Getter
public class CreateNoteRequest {
    @XmlElement(required = true)
    protected String section;
    @XmlElement(required = true)
    protected String advice;
}

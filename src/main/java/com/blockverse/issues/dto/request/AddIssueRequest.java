package com.blockverse.issues.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddIssueRequest  implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long templateID;

    private String status;

    private String type;

    private Integer version;




}
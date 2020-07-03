package com.blockverse.issues.dto;

//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Response body for issue")
public class Issue  implements Serializable {

    @NotNull
    private Long id;

    private String dateCreated;

    private String dateModified;

    private String createdBy;

    private String modifiedBy;

    @NotNull
    private Integer deleted;

    @NotNull
    private String name;

    private String description;

    private String status;

    private String type;

    private Integer version;

    @NotNull
    private Long templateID;

}

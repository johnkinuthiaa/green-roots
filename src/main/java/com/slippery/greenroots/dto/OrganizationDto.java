package com.slippery.greenroots.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.greenroots.models.Organization;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationDto {
    private String message;
    private Integer statusCode;
    private Organization organization;
    private List<Organization> organizationList;
}

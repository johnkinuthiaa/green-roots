package com.slippery.greenroots.service;

import com.slippery.greenroots.dto.OrganizationDto;
import com.slippery.greenroots.models.Organization;

public interface OrganizationService {
    OrganizationDto createNewOrganization(Organization organization,Long userId);
    OrganizationDto updateOrganization(Organization organization,Long userId);
    OrganizationDto findOrganizationById(Long organizationId);
    OrganizationDto deleteOrganizationById(Long organizationId,Long creatorId);
    OrganizationDto deleteAll();
}

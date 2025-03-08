package com.slippery.greenroots.controller;

import com.slippery.greenroots.dto.OrganizationDto;
import com.slippery.greenroots.models.Organization;
import com.slippery.greenroots.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<OrganizationDto> createNewOrganization(@RequestBody Organization organization,@RequestParam Long userId) {
        return ResponseEntity.ok(service.createNewOrganization(organization, userId));
    }
    @PutMapping("/update")
    public ResponseEntity<OrganizationDto> updateOrganization(Organization organization, Long userId) {
        return null;
    }
    @GetMapping("/{organizationId}/get")
    public ResponseEntity<OrganizationDto> findOrganizationById(@PathVariable Long organizationId) {
        return ResponseEntity.ok(service.findOrganizationById(organizationId));
    }

    @DeleteMapping("/{organizationId}/delete")
    public ResponseEntity<OrganizationDto> deleteOrganizationById(@PathVariable Long organizationId,@RequestParam Long creatorId) {
        return ResponseEntity.ok(service.deleteOrganizationById(organizationId, creatorId));
    }

    public ResponseEntity<OrganizationDto> deleteAll() {
        return null;
    }
}

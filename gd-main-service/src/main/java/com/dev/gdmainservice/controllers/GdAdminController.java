package com.dev.gdmainservice.controllers;

import com.dev.gdmainservice.converters.GdAdminConverter;
import com.dev.gdmainservice.models.dto.GdAdminDto;
import com.dev.gdmainservice.models.entity.GdAdmin;
import com.dev.gdmainservice.services.GdAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class GdAdminController {
    private final GdAdminService adminService;

    @Autowired
    public GdAdminController(GdAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDto adminDto) {
        adminService.save(GdAdminConverter.convertToEntity(adminDto));
        return ResponseEntity.ok("Админ успешно создан");
    }

    @GetMapping("/{name}")
    public GdAdmin getAdmin(@PathVariable("name") String name) {
        return adminService.findByName(name);
    }
}

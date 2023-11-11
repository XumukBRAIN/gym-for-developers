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
    private final GdAdminService gdAdminService;
    private final GdAdminConverter gdAdminConverter;

    @Autowired
    public GdAdminController(GdAdminService gdAdminService, GdAdminConverter gdAdminConverter) {
        this.gdAdminService = gdAdminService;
        this.gdAdminConverter = gdAdminConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDto gdadminDTO) {
        gdAdminService.save(gdAdminConverter.convertToEntity(gdadminDTO));
        return ResponseEntity.ok("Админ успешно создан");
    }

    @GetMapping("/{name}")
    public GdAdmin getAdmin(@PathVariable("name") String name) {
        return gdAdminService.findByName(name);
    }

}

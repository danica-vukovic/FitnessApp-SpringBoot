package org.unibl.ip.ip.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.ip.ip.models.dto.Attribute;
import org.unibl.ip.ip.models.dto.AttributeValue;
import org.unibl.ip.ip.services.AttributeService;
import org.unibl.ip.ip.services.AttributeValueService;
import org.unibl.ip.ip.services.CategoryService;
import org.unibl.ip.ip.services.impl.AuthServiceImpl;

import java.io.Console;
import java.util.List;
@RequestMapping("/attributes")
@RestController
public class AttributeController {
    private final AttributeValueService attributeValueService;
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    public AttributeController( AttributeValueService attributeValueService){
        this.attributeValueService = attributeValueService;
    }
    @GetMapping("/{id}/values")
    public ResponseEntity<List<AttributeValue>> getAllValuesByAttributeId(@PathVariable Integer id) {
        List<AttributeValue> attributes = attributeValueService.getAllByAttributeId(id);
        logger.info(attributes);
        return ResponseEntity.ok(attributes);
    }
}

package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.*;
import org.unibl.ip.ip.models.enums.Level;
import org.unibl.ip.ip.models.enums.Location;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProgramRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Level level;
    @NotNull
    private Integer duration;
    @NotNull
    private Location location;
    @NotBlank
    private String instructorInformation;
    @NotBlank
    private String contact;

    private String videoUrl;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer userId;

    private List<ImageRequest> images;
}
package org.unibl.ip.ip.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.unibl.ip.ip.models.entities.*;
import org.unibl.ip.ip.models.enums.Level;
import org.unibl.ip.ip.models.enums.Location;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Program implements Serializable {

    private Integer idProgram;

    private String name;

    private String description;

    private BigDecimal price;

    private Level level;

    private Integer duration;

    private Location location;

    private String instructorInformation;

    private String contact;

    private User user;

    private String videoUrl;

    private Category category;
    private List<Image> images;
    @JsonIgnore
    private List<Comment> comments;

}

package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {

    private int id;
    private String name;
    private String description;
}

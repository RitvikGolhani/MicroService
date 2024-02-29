package com.userservice.entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Rating> rating=new ArrayList<>();
}

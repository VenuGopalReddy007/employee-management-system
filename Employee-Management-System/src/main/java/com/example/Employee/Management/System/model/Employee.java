package com.example.Employee.Management.System.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String department;
    private String designation;
    private LocalDate dateOfJoining;
    private double salary;
    private boolean active;


}

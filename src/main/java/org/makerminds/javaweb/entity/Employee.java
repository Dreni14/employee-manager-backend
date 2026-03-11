package org.makerminds.javaweb.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 🔹 KJO ËSHTË E RËNDËSISHME
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Adress is required.")
    @Column(name = "adress", nullable = false)
    private String adress;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Phone Number is required.")
    @Size(min = 12, max = 12, message = "Invalid Phone Number.")
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId", nullable = false, updatable = false)
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("employee")
    private List<Task> taskList;
}

package com.vulinh.model.secondary;

import com.vulinh.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
public class Employee extends AbstractEntity {

    private static final long serialVersionUID = 3624882108855436398L;

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public enum Gender {
        MALE, FEMALE
    }
}
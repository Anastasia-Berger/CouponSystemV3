package com.jb.csv3.beans;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by kobi Shasha
 */

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class Base {

    // @Temporal(value= TemporalType.TIMESTAMP) Java.util.Date only
    @CreationTimestamp
    @Column(name="CREATED_TIME")
    protected LocalDateTime creationTime;

    // @Temporal(value=TemporalType.TIMESTAMP) Java.util.Date only
    @UpdateTimestamp
    @Column(name="UPDATED_TIME")
    protected LocalDateTime updatedTime;

}

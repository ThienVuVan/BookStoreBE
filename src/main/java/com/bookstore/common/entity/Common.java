package com.bookstore.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class Common {
    @Column
    String CreateBy;
    @Column
    String UpdateBy;
    @Column
    LocalDate CreateDate;
    @Column
    LocalDate UpdateDate;
}

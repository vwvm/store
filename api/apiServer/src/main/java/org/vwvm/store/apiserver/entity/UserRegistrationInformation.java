package org.vwvm.store.apiserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserRegistrationInformation {

    @Id
    @GeneratedValue
    @Schema(defaultValue = "id")
    private Long id;

    @Schema(defaultValue = "name")
    private String  name;

    @Schema(defaultValue = "telephone")
    private String  telephone;

    @Schema(defaultValue = "profession")
    private String profession;

    @Schema(defaultValue = "albumOfPainting")
    private String albumOfPainting;

    @Schema(defaultValue = "zzExtra0")
    private String  zzExtra0;
    @Schema(defaultValue = "zzExtra1")
    private String  zzExtra1;
    @Schema(defaultValue = "zzExtra2")
    private String  zzExtra2;
    @Schema(defaultValue = "zzExtra3")
    private String  zzExtra3;
    @Schema(defaultValue = "zzExtra4")
    private String  zzExtra4;
    @Schema(defaultValue = "zzExtra5")
    private String  zzExtra5;
    @Schema(defaultValue = "zzExtra6")
    private String  zzExtra6;
    @Schema(defaultValue = "zzExtra7")
    private String  zzExtra7;
    @Schema(defaultValue = "zzExtra8")
    private String  zzExtra8;
    @Schema(defaultValue = "zzExtra9")
    private String  zzExtra9;
}

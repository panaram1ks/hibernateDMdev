package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LocaleInfo {

    private String lang;
    private String description;

    public static LocaleInfo of(String lang, String description){
        return new LocaleInfo(lang, description);
    }
}

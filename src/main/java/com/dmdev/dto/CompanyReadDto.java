package com.dmdev.dto;

import com.dmdev.entity.LocaleInfo;

import java.util.List;

public record CompanyReadDto(Long id,
                             String name,
                             List<LocaleInfo> locales) {
}

package com.kailash.BlogApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.*;
import static com.kailash.BlogApp.utils.Constants.RegexConstants.CATEGORY_NAME_REGEX;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Integer categoryId;

    @JsonProperty(required = true)
    @Pattern(regexp = CATEGORY_NAME_REGEX, message = CATEGORY_NAME_FORMAT_MESSAGE)
    @NotBlank(message = CATEGORY_NAME_CANNOT_BE_BLANK)
    @Size(min = 2, message = CATEGORY_NAME_SIZE_MUST_BE_AT_LEAST_2)
    @Size(max = 50, message = CATEGORY_NAME_SIZE_MUST_BE_AT_MOST_50)
    private String categoryName;

    @Size(max = 200, message = CATEGORY_DESCRIPTION_SIZE_MUST_BE_AT_MOST_200)
    private String categoryDescription;
}

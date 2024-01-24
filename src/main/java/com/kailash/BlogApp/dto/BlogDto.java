package com.kailash.BlogApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDto {

    private Integer blogId;

    @JsonProperty(required = true)
    @NotBlank(message = BLOG_TITLE_CANNOT_BE_BLANK)
    @Size(max = 120, message = BLOG_TITLE_SIZE_MUST_BE_AT_MOST_120)
    private String blogTitle;

    @JsonProperty(required = true)
    @NotBlank(message = BLOG_CONTENT_CANNOT_BE_BLANK)
    @Size(min = 200, message = BLOG_CONTENT_MUST_BE_AT_LEAST_200)
    @Size(max = 50000, message = BLOG_CONTENT_MUST_BE_AT_MOST_50000)
    private String blogContent;

    private String imageUrl;

    private Timestamp createdOn;

    private Timestamp updatedOn;

    private Integer createdBy;

    @JsonProperty(required = true)
    @NotEmpty(message = CATEGORIES_CANNOT_BE_EMPTY)
    private List<Integer> categories;
}

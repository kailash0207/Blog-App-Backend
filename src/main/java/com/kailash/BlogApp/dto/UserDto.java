package com.kailash.BlogApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kailash.BlogApp.utils.ValidEmail;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.*;
import static com.kailash.BlogApp.utils.Constants.RegexConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer userId;

    @JsonProperty(required = true)
    @NotBlank(message = USER_NAME_CANNOT_BE_BLANK)
    @Size(min = 3, message = USER_NAME_SIZE_MUST_BE_AT_LEAST_3)
    @Size(max = 50, message = USER_NAME_SIZE_MUST_BE_AT_MOST_50)
    @Pattern(regexp = USER_NAME_REGEX, message = USER_NAME_FORMAT_MESSAGE)
    private String userName;

    @JsonProperty(required = true)
    @ValidEmail
    private String emailId;

    @JsonProperty(required = true)
    @Size(min = 8, message = PASSWORD_SIZE_MUST_BE_AT_LEAST_8)
    @Size(max = 16, message = PASSWORD_SIZE_MUST_BE_AT_MOST_16)
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_FORMAT_MESSAGE)
    private String password;

    @Size(max = 500, message = ABOUT_USER_SIZE_MUST_BE_AT_MOST_500)
    private String about;

    private Timestamp createdOn;
    private Timestamp updatedOn;
}

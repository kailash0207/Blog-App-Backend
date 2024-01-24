package com.kailash.BlogApp.models;

import com.kailash.BlogApp.utils.EntityDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {

    private List<T> records;
    private PaginationInfo pagination;

    public static <S, U> PaginatedResponse<U> fromPage(Page<S> page, EntityDtoConverter<S, U> converter){
        List<U> contents = page.getContent().stream().map(converter::convertToDto).toList();
        PaginationInfo paginationInfo = PaginationInfo.fromPage(page);
        return new PaginatedResponse<>(contents, paginationInfo);
    }
}

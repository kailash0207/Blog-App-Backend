package com.kailash.BlogApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationInfo {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalRecords;
    private Boolean isLastPage;

    public static <T> PaginationInfo fromPage(Page<T> page){
        return PaginationInfo.builder()
                .pageNum(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalRecords(page.getTotalElements())
                .isLastPage(page.isLast())
                .build();
    }
}

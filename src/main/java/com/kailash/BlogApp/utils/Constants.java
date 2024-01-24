package com.kailash.BlogApp.utils;

public class Constants {
    public static class TableNames{
        public static final String USER = "user";
        public static final String CATEGORY = "category";
        public static final String BLOG = "blog";
        public static final String BLOG_CATEGORY = "blog_category";
    }

    public static class ColumnNames{
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
        public static final String EMAIL_ID = "email_id";
        public static final String PASSWORD = "password";
        public static final String ABOUT = "about";
        public static final String CREATED_ON = "created_on";
        public static final String UPDATED_ON = "updated_on";
        public static final String CATEGORY_ID = "category_id";
        public static final String CATEGORY_NAME = "category_name";
        public static final String CATEGORY_DESCRIPTION = "category_description";
        public static final String BLOG_ID = "blog_id";
        public static final String BLOG_TITLE = "blog_title";
        public static final String BLOG_CONTENT = "blog_content";
        public static final String CREATED_BY = "created_by";
        public static final String IMAGE_URL = "image_url";
        public static final String CATEGORIES = "categories";
        public static final String CREATED_BY_CAME_CASE = "createdBy";
    }
    

    public static class ErrorMessages{
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_NAME_CANNOT_BE_BLANK = "Username cannot be blank";
        public static final String USER_NAME_SIZE_MUST_BE_AT_LEAST_3 = "Username must be at least 3 characters long";
        public static final String USER_NAME_SIZE_MUST_BE_AT_MOST_50 = "Username must be at most 50 characters long";
        public static final String USER_NAME_FORMAT_MESSAGE = "Username must not start or end with whitespaces, should only contain alphabets, and should contain consecutive whitespaces";
        public static final String INVALID_EMAIL_ID = "Invalid Email ID";
        public static final String PASSWORD_SIZE_MUST_BE_AT_LEAST_8 = "Password must be at least 8 characters long";
        public static final String PASSWORD_SIZE_MUST_BE_AT_MOST_16 = "Password must be at most 16 characters long";
        public static final String PASSWORD_FORMAT_MESSAGE = "Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character and should not contain spaces";
        public static final String ABOUT_USER_SIZE_MUST_BE_AT_MOST_500 = "About length must be at most 500";
        public static final String CATEGORY_NAME_CANNOT_BE_BLANK = "Category name cannot be blank";
        public static final String CATEGORY_NAME_SIZE_MUST_BE_AT_LEAST_2 = "Category name must be at least 2 characters long";
        public static final String CATEGORY_NAME_SIZE_MUST_BE_AT_MOST_50 = "Category name must be at most 50 characters long";
        public static final String CATEGORY_NAME_FORMAT_MESSAGE = "Category name must start with alphanumeric character, should not contain consecutive whitespaces and should not end with whitespace";
        public static final String CATEGORY_DESCRIPTION_SIZE_MUST_BE_AT_MOST_200 = "Category description must be at most 200 characters long";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
        public static final String CATEGORY_NOT_FOUND = "Category not found";
        public static final String BLOG_TITLE_CANNOT_BE_BLANK = "Blog title cannot be blank";
        public static final String BLOG_TITLE_SIZE_MUST_BE_AT_MOST_120 = "Blog title must be at most 120 characters long";
        public static final String BLOG_CONTENT_CANNOT_BE_BLANK = "Blog content cannot be blank";
        public static final String BLOG_CONTENT_MUST_BE_AT_LEAST_200 = "Blog content must be at least 200 characters long";
        public static final String BLOG_CONTENT_MUST_BE_AT_MOST_50000 = "Blog content must be at most 50000 characters long";
        public static final String CATEGORIES_CANNOT_BE_EMPTY = "Categories cannot be empty";
        public static final String BLOG_NOT_FOUND = "Blog not found";
        public static final String BLOG_UPDATE_ALLOWED_TO_OWNER_ONLY = "Only author can update blog";
        public static final String BLOG_DELETION_ALLOWED_TO_OWNER_ONLY = "Only author can delete blog";
        public static final String INVALID_REQUEST = "Invalid Request";
        public static final String INVALID_FILE_TYPE = "Invalid FileType";
        public static final String FILE_NOT_FOUND = "File not found";
        public static final String ERROR_OCCURRED = "Error Occurred";
    }

    public static final class SuccessMessages{
        public static final String USER_DELETION_SUCCESSFUL = "User deleted successfully";
        public static final String CATEGORY_DELETION_SUCCESSFUL = "Category deleted successfully";
        public static final String BLOG_DELETION_SUCCESSFUL = "Blog deleted successfully";
        public static final String FILE_UPLOAD_SUCCESSFUL = "File uploaded successfully";
        public static final String FILE_FETCH_SUCCESSFUL = "File fetched successful";
    }

    public static final class RegexConstants{
        public static final String USER_NAME_REGEX = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
        public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?!\\s).*$";
        public static final String CATEGORY_NAME_REGEX = "^[a-zA-Z0-9](?!.*\\s{2,})[a-zA-Z0-9\\s\\S]*[^\\s]$";
    }

    public static class ApiConstants{
        public static final String USERS_API_PREFIX = "/users";
        public static final String CATEGORIES_API_PREFIX = "/categories";
        public static final String BLOGS_API_PREFIX = "/blogs";
        public static final String SEARCH = "/search";
        public static final String FILES_API_PREFIX = "/files";
        public static final String USER_ID_PATH_VARIABLE = "/{" + ColumnNames.USER_ID + "}";
        public static final String APPLICATION_JSON = "application/json";
        public static final String CATEGORY_ID_PATH_VARIABLE = "/{" + ColumnNames.CATEGORY_ID + "}";
        public static final String BLOG_ID_PATH_VARIABLE = "/{" + ColumnNames.BLOG_ID + "}";
        public static final String PAGE_NUMBER = "page";
        public static final String PAGE_SIZE = "size";
        public static final String SORT_BY = "sortBy";
        public static final String SORT_DIR = "sortDir";
        public static final String KEYWORD = "keyword";
        public static final String REQUEST_TYPE = "requestType";
        public static final String FILE_NAME = "fileName";
        public static final String FILE = "file";
        public static final String DESC = "desc";
        public static final String ASC = "asc";
        public static final String DEFAULT_PAGE_NUMBER = "0";
        public static final String DEFAULT_PAGE_SIZE = "10";
        public static final String DEFAULT_BLOG_SORT_FIELD = "createdOn";
        public static final String DEFAULT_CATEGORY_SORT_FIELD = "categoryId";
        public static final String DEFAULT_USER_SORT_FIELD = "userId";

    }

    public static class FileTypes{
        public static final String IMAGE = "image";
    }


}

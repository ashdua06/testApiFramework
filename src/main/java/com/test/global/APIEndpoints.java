package com.test.global;

public class APIEndpoints {
    public static class PET_STORE{
        public static final String CREATE_MULTIPLE_USERS="/v2/user/createWithArray";
        public static final String UPDATE_USERS="/v2/user/{username}";
        public static final String GET_USERS="/v2/user/{username}";
        public static final String CREATE_PET="/v2/pet";
        public static final String UPDATE_PET="/v2/pet";
        public static final String GET_PET_BY_STATUS="/v2/pet/findByStatus";
        public static final String DELETE_PET="/v2/pet/{petId}";
    }
}

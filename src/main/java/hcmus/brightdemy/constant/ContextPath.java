package hcmus.brightdemy.constant;

public class ContextPath {

    public static final String CROSS_PATH = "http://localhost:3000";
    public static class User {
        public static final String  PATH = "/api/user";
        public static final String CREATE_USER = "/create";
        public static final String LIST = "/list";

        public static final String REGISTER = "/register";
        public static final String SEARCH = "/search";

        public static final String LOGIN = "/login";

        public static final String DELETE = "/delete";
        public static final String GET_USER_BY_TOKEN = "/get-by-token";
    }

    public static class Role {
        public static final String PATH = "/api/role";
        public static final String CREATE_ROLE = "/create";
        public static final String LIST = "/list";

        public static final String DELETE = "/delete";

    }

    public static class Course {
        public static final String PATH = "api/course";
        public static final String LIST = "/list";
        public static final String CREATE = "create";

    }
}

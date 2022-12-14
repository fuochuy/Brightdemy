package hcmus.brightdemy.constant;

public class ContextPath {
    public static class User {
        public static final String  PATH = "/api/user";
        public static final String CREATE_USER = "/api/create-user";
        public static final String LIST = "/list";

        public static final String REGISTER = "/register";
        public static final String SEARCH = "/search";

        public static final String LOGIN = "/api/login";

        public static final String DELETE = "/delete";
    }

    public static class Role {
        public static final String PATH = "/api/role";
        public static final String CREATE_ROLE = "/api/create-role";
        public static final String LIST = "/api/list";

        public static final String DELETE = "/delete";
    }

    public static class Course {
        public static final String PATH = "api/course";
        public static final String LIST = "/list";

    }
}

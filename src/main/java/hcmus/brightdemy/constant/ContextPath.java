package hcmus.brightdemy.constant;

public class ContextPath {
    public static class User {
        public static final String PATH = "/user";
        public static final String CREATE_USER = "/create-user";
        public static final String LIST = "/list";

        public static final String REGISTER = "/register";
        public static final String SEARCH = "/search";

        public static final String LOGIN = "/login";

        public static final String DELETE = "/delete";
    }

    public static class Role {
        public static final String PATH = "/role";
        public static final String CREATE_ROLE = "/create-role";
        public static final String LIST = "/list";

        public static final String DELETE = "/delete";
    }

    public static class Course {
        public static final String PATH = "/course";
        public static final String LIST = "/list";

    }
}

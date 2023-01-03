package hcmus.brightdemy.constant;

public class ContextPath {

    public static final String CROSS_PATH = "http://localhost:3000";

    public static class Admin{
        public static final String CREATE_USER = "/admin/create";
        public static final String DELETE = "/delete";

        public static final String BLOCK = "/block";

        public static final String ACTIVE = "/active";
    }
    public static class User {
        public static final String  PATH = "/api";
        public static final String LIST = "/user/list";

        public static final String REGISTER = "/user/register";
        public static final String SEARCH = "/user/search";

        public static final String UPDATE ="/user/update";


        public static final String GET_USER_BY_TOKEN = "/user/get-by-token";
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
        public static final String CREATE = "/create";

        public static final String JOIN ="/join";

    }
    public static final String LOGIN = "/login";
}

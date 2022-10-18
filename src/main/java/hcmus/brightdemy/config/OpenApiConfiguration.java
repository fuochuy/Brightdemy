//package hcmus.brightdemy.config;
//
//import org.springframework.context.annotation.Bean;
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//public class OpenApiConfiguration {
//    @Bean
//    public OpenAPI getOpenApi() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Brightdemy Application")
//                        .description("This is a Information Systems Development course project.")
//                        .version("v1.0")
//                        .license(new License().name("NO LICENSE").url("https://www.linkedin.com/in/nguyen-phuoc-huy/"))
//                        .contact(new Contact()
//                                .email("phuochuynguyen.work@gmail.com")
//                                .name("Nguyen Phuoc Huy")
//                                .url("https://www.linkedin.com/in/nguyen-phuoc-huy/")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("Spring Documentation")
//                        .url("https://docs.spring.io/spring-framework/docs/current/reference/html/"));
//
//    }
//}
//

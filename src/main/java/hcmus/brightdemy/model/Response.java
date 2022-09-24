package hcmus.brightdemy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("code")
    @Builder.Default
    private Integer code = 0;

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("message")
    @Builder.Default
    private String message = "success";

    @JsonProperty("data")
    private Object data;
}

package socialmedia.server.email;

import lombok.Data;

import java.util.List;
@Data
public class EmailRequest {
    private List<String> recipients;
    private String subject;
    private String body;

}

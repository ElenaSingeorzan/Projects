package socialmedia.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import socialmedia.server.post.Post;
import socialmedia.server.post.PostRepository;
import socialmedia.server.post.PostService;
import socialmedia.server.post.Status;
import socialmedia.server.user.User;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService; // this is the service under test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


}

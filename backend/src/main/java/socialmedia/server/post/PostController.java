package socialmedia.server.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import socialmedia.server.JwtTokenUtil;
import socialmedia.server.user.User;
import socialmedia.server.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/posts")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> createPost(@RequestBody Post post, HttpServletRequest request) {
        String userId = null;
        String jwt = null;

        // Obține header-ul Authorization
        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println( "primit:  "+requestTokenHeader);
        // Verifică dacă header-ul este prezent și are formatul corect
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwt = requestTokenHeader.substring(7);
            try {
                userId = jwtTokenUtil.getUserIdFromToken(jwt); // Extrage userId din token
                System.out.println(userId);
            } catch (Exception e) {
                // Dacă token-ul nu este valid, returnează UNAUTHORIZED
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("JWT Token is invalid");
            }
        } else {
            // Returnează UNAUTHORIZED dacă token-ul lipsește sau are format incorect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("JWT Token is missing or invalid");
        }

        // Asociază utilizatorul postării cu userId extras
        post.setUser(new User(userId)); // Modifică aici conform modelului tău

        // Salvează postarea
        postService.create(post);
        return ResponseEntity.ok("Post created successfully!");
    }

    @GetMapping("/allPosts")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Postarea a fost ștearsă cu succes.");
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable int id,
            @RequestBody PostUpdateRequest request) {
        Post updatedPost = postService.updatePost(id, request.getTitle(), request.getContent(), request.getStatus());
        return ResponseEntity.ok(updatedPost);
    }
    @GetMapping("/users/posts")
    public ResponseEntity<?> getAllPublishedPostsForUser(HttpServletRequest request) {
        String userId = null;
        String jwt = null;

        // Obține header-ul Authorization
        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println( "primit:  "+requestTokenHeader);
        // Verifică dacă header-ul este prezent și are formatul corect
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwt = requestTokenHeader.substring(7);
            try {
                userId = jwtTokenUtil.getUserIdFromToken(jwt); // Extrage userId din token
                System.out.println(userId);
            } catch (Exception e) {
                // Dacă token-ul nu este valid, returnează UNAUTHORIZED
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("JWT Token is invalid");
            }
        } else {
            // Returnează UNAUTHORIZED dacă token-ul lipsește sau are format incorect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("JWT Token is missing or invalid");
        }

        List<Post> posts = postService.getAllPublishedPostsForUser(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPublishedPostsForOtherUsers(HttpServletRequest request) {
        String userId = null;
        String jwt = null;

        // Obține header-ul Authorization
        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println( "primit:  "+requestTokenHeader);
        // Verifică dacă header-ul este prezent și are formatul corect
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwt = requestTokenHeader.substring(7);
            try {
                userId = jwtTokenUtil.getUserIdFromToken(jwt); // Extrage userId din token
                System.out.println(userId);
            } catch (Exception e) {
                // Dacă token-ul nu este valid, returnează UNAUTHORIZED
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("JWT Token is invalid");
            }
        } else {
            // Returnează UNAUTHORIZED dacă token-ul lipsește sau are format incorect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("JWT Token is missing or invalid");
        }

        List<Post> posts = postService.getAllPublishedPostsForOtherUsers(userId);
        return ResponseEntity.ok(posts);
    }

    //Endpoint-uri pentru aplicatia android


    @GetMapping("/posts/search")
    public List<Post> getPostsContainingKeyword(@RequestParam("keyword") String keyword) {
        return postService.findPostsByKeyword(keyword);
    }

    @GetMapping("/pendingPosts")
    public List<Post> getPendingPosts() {
        return postService.getPendingPosts();
    }

    @PutMapping("/{postId}/approve")
    public ResponseEntity<Void> approvePost(@PathVariable int postId) {
        postService.approvePost(postId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/remove")
    public void removePost(@PathVariable int postId) {
        postService.removePost(postId);
    }
}

package socialmedia.server.comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import socialmedia.server.post.Post;
import socialmedia.server.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialmedia.server.post.PostService;
import socialmedia.server.user.User;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import socialmedia.server.JwtTokenUtil;
@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CommentController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        System.out.println("JwtTokenUtil injectat: " + (jwtTokenUtil != null));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> createComment(@PathVariable Integer postId, @RequestBody Comment comment, HttpServletRequest request) {
        System.out.println("Comentariu primit: " + comment);
        System.out.println("Content primit: " + comment.getContent());
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
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("JWT Token is invalid");
            }
        } else {
            // Returnează UNAUTHORIZED dacă token-ul lipsește sau are format incorect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("JWT Token is missing or invalid");
        }


        // Asociază utilizatorul postării cu userId extras
        comment.setUser(new User(userId));

        // Găsește post-ul pe baza ID-ului
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // Asociază post-ul cu comentariul
        comment.setPost(post);

        // Salvează comentariul
        Comment createdComment = commentService.createComment(comment);

        return ResponseEntity.ok("Comment created successfully!");
    }


    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            List<Comment> comments = commentService.findAllComments();
            return ResponseEntity.ok(comments);
        } catch (JpaObjectRetrievalFailureException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    // Global exception handler if needed
    @ExceptionHandler({JpaObjectRetrievalFailureException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Related Post entity not found: " + e.getMessage());
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comentariul a fost șters cu succes.");
    }
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable int id,
            @RequestBody Comment newContent) {
        Comment updatedComment = commentService.updateComment(id,  newContent);
        return ResponseEntity.ok(updatedComment);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<Comment>> getAllCommentsForPost(@PathVariable Integer postId) {
        List<Comment> comments = commentService.getAllCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comments/search")
    public List<Comment> getCommentsContainingKeyword(@RequestParam("keyword") String keyword) {
        return commentService.findCommentsByKeyword(keyword);
    }
}

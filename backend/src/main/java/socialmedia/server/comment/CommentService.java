package socialmedia.server.comment;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import socialmedia.server.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialmedia.server.post.PostRepository;
import socialmedia.server.post.Status;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Comment comment) {
        Post post = postRepository.findById(comment.getPost().getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // Asociem post-ul cu comentariul
        comment.setPost(post);

        // Salvăm comentariul
        return commentRepository.save(comment);
    }

    public List<Comment> findAllComments() {
        try {
            return commentRepository.findAll();
        } catch (JpaObjectRetrievalFailureException | EntityNotFoundException e) {
            System.err.println("Post entity not found for a comment: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteComment(int id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Comentariul cu id-ul " + id + " nu a fost găsit.");
        }
    }

    public Comment updateComment(int id, Comment newContent) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentariul cu id-ul " + id + " nu a fost găsit."));

        comment.setContent(newContent.getContent());
        return commentRepository.save(comment);
    }
    public List<Comment> getAllCommentsForPost(Integer postId) {
        return commentRepository.findAllCommentsForPost(postId);
    }

    public List<Comment> findCommentsByKeyword(String keyword) {
        return commentRepository.findByContentContainingIgnoreCase(keyword);
    }
}

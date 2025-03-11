package socialmedia.server.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import socialmedia.server.post.Post;
import socialmedia.server.post.Status;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
    List<Comment> findAllCommentsForPost(@Param("postId") Integer postId);

    List<Comment> findByContentContainingIgnoreCase(String keyword);

}


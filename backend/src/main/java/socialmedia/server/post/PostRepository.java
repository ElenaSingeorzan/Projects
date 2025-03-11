package socialmedia.server.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.id != :userId AND p.status = :status")
    List<Post> findAllPublishedPostsForOtherUsers(@Param("userId") String userId, @Param("status") Status status);
    List<Post> findByUserId(String userId);

    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword);

    List<Post> findByStatus(Status status);
}

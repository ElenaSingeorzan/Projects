package socialmedia.server.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Post create(Post myPost) {
        return postRepository.save(myPost);
    }
    @Transactional
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToPostDTO)
                .collect(Collectors.toList());
    }

    private PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        return postDTO;
    }
    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public void deletePost(int id) {
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new RuntimeException("Postarea cu id-ul " + id + " nu a fost găsită.");
        }
    }
    public Post updatePost(int id, String newTitle, String newContent, Status newStatus) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postarea cu id-ul " + id + " nu a fost găsită."));

        post.setTitle(newTitle);
        post.setContent(newContent);
        post.setStatus(newStatus);
        return postRepository.save(post);
    }
    public List<Post> getAllPublishedPostsForUser(String userId) {

        return  postRepository.findByUserId(userId);
    }
    public List<Post> getAllPublishedPostsForOtherUsers(String userId) {
        return postRepository.findAllPublishedPostsForOtherUsers(userId, Status.PUBLISHED);
    }


//servici pentru android


    public List<Post> findPostsByKeyword(String keyword) {
        return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }

    public List<Post> getPendingPosts() {
        return postRepository.findByStatus(Status.PENDING);
    }

    public void approvePost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus(Status.PUBLISHED);
        postRepository.save(post);
    }

    public void removePost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus(Status.REMOVED);
        postRepository.save(post);
    }
}

package socialmedia.server;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String requestPath = request.getServletPath();
        if (requestPath.equals("/users") || requestPath.equals("/login") ||requestPath.equals("/allPosts") || requestPath.equals("/posts/search")
                || requestPath.equals("/comments/search") ||  pathMatcher.match("/pendingPosts", requestPath) ||
                pathMatcher.match("/{postId}/approve", requestPath) || pathMatcher.match("/sendEmail", requestPath) ||
        pathMatcher.match("/{postId}/remove", requestPath))  {

            System.out.println("Bypassing JWT validation for: " + requestPath);
            chain.doFilter(request, response);  // Skip token validation for these paths
            return;
        }

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid.");
            return;
        }

        String jwtToken = authorizationHeader.substring(7);

        try {
            String userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
        } catch (MalformedJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
            return;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the token.");
            return;
        }

        chain.doFilter(request, response);
    }

}

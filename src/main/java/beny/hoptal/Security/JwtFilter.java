package beny.hoptal.Security;

import beny.hoptal.data.models.User;
import beny.hoptal.data.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Get Authorization header
        System.out.println("JwtFilter called for: " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth header: " + authHeader);
        // 2. If no token — skip filter, Spring Security will block if route is protected
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract token
        String token = authHeader.substring(7);
        String nomUtilisateur = jwtService.extractUsername(token);

        // 5. If username found and no authentication set yet
        if (nomUtilisateur != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Load user from database
            User user = userRepository.findByNomUtilisateur(nomUtilisateur)
                    .orElse(null);

            if (user != null && jwtService.isTokenValid(token, nomUtilisateur)) {

                // 7. Build authentication with role
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                List.of(new SimpleGrantedAuthority(
                                        "ROLE_" + user.getRole().getNom()))
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // 8. Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
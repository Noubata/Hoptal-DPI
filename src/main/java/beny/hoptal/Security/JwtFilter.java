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

        String authHeader = request.getHeader("Authorization");
        System.out.println("==> URI: " + request.getRequestURI());
        System.out.println("==> Auth header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("==> Pas de token — skip");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String nomUtilisateur = jwtService.extractUsername(token);
        System.out.println("==> Username extrait: " + nomUtilisateur);

        if (nomUtilisateur != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepository.findByNomUtilisateur(nomUtilisateur).orElse(null);
            System.out.println("==> User trouvé: " + (user != null ? user.getNomUtilisateur() : "NULL"));

            if (user != null && jwtService.isTokenValid(token, nomUtilisateur)) {
                System.out.println("==> Token valide — authentification OK");
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getNom()))
                        );
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("==> Token INVALIDE ou user NULL");
            }
        }

        filterChain.doFilter(request, response);
    }
}
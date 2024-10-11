package hr.autorepair.shop.secutiry;

import hr.autorepair.common.utils.JwtUtil;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        email = JwtUtil.extractEmail(jwt);

        // Check if the user is already authenticated
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = loadUserByUsername(email);

            // Validate the token
            if (JwtUtil.isTokenValid(jwt, userDetails)) {
                // Create an authentication token and set it in the SecurityContext
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails loadUserByUsername(String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Ne postoji korisnik za zadani email!"));

       return modelMapper.map(appUser, UserPrincipal.class);
    }


}

package com.zzx.authorization;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserIDAuditorBean implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return null;
        }
        if (ctx.getAuthentication() == null) {
            return null;
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return null;
        }
        Object principal = ctx.getAuthentication().getPrincipal();
        return String.valueOf(principal);
        
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//            } else {
//            String username = principal.toString();
//            }
    }

}

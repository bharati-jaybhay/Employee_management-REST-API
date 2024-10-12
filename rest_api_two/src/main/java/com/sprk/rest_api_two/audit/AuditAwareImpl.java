package com.sprk.rest_api_two.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
       return Optional.of("Guest");
    }
    

}

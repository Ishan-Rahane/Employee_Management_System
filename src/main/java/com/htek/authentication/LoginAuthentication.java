package com.htek.authentication;

import com.htek.email.EmailDetails;
import com.htek.model.Employee;
import com.htek.repository.EmployeeRepository;
import com.htek.security.BCryptPasswordEncoderService;
import com.htek.service.EmployeeService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.util.Collections;

@Singleton
public class LoginAuthentication implements AuthenticationProvider {

    @Inject
    private EmployeeService service;

    @Inject
    private EmployeeRepository repository;

    @Inject
    private BCryptPasswordEncoderService bCryptPasswordEncoderService;

    //For USER ROLE based authentication
    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            Object identity = authenticationRequest.getIdentity();
            Object secret = authenticationRequest.getSecret();
            EmailDetails user = repository.findByEmpEmail(identity.toString());
            boolean validateCredentials = user != null && bCryptPasswordEncoderService.matches(secret.toString(), user.getPassword());
            System.out.println(validateCredentials);
            Employee e = service.findByEmail(identity.toString());
            if (validateCredentials) {
                emitter.next(AuthenticationResponse.success(identity.toString(),
                        Collections.singletonList(e.getRole().toString())));
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}


    // For JWT
//    @Override
//    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
//        return Flux.create(emitter -> {
//            if(authenticationRequest.getIdentity().equals("ADMIN") && authenticationRequest.getSecret().equals("ADMIN"))
//            {
//                emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
//                emitter.complete();
//            }
//            else{
//                emitter.error(AuthenticationResponse.exception());
//            }
//        }, FluxSink.OverflowStrategy.ERROR);
//    }


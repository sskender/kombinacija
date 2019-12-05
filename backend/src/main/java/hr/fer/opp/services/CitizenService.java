package hr.fer.opp.services;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

public interface CitizenService {

    // add favorite container
    String testPingGet(Long containerId);
    String testPingPost(Long containerId);

    // remove favorite container

    // ping container

}

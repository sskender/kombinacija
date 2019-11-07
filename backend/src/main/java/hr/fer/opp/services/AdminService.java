package hr.fer.opp.services;

public interface AdminService {

    /**
     * Checks if there is an account with the given e-mail
     * in the admin table
     *
     * @param email the User received from the front-end
     * @return
     */
    boolean isAdmin(String email);

}

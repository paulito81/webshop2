package no.phasfjo.infrastructure.login;

import no.phasfjo.dto.login.Login;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public interface LoginDao {

    Login persist(Login login);
    Boolean update(Login login);

}

package no.phasfjo.infrastructure.account;

import no.phasfjo.dto.account.Account;

import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public interface AccountDao {

    Account persist(Account account);

    Boolean delete(int id);

    Account findById(int id);

    boolean update(Account account);

    List<Account> getAll();

}

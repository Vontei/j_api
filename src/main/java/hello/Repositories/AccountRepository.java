package hello.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hello.Models.Account;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends MongoRepository<Account, String> {

	List<Account> findByUserName(@Param("username") String userName);

	// List<Account> findById(@Param("id") String id);
	List<Account> findById(@Param("_id") Account id);

}
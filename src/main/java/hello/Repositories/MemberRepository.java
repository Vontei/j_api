package hello.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hello.Models.Member;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
public interface MemberRepository extends MongoRepository<Member, String> {

	List<Member> findByName(@Param("name") String name);

}
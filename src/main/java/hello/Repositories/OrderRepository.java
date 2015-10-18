package hello.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hello.Models.Order;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findBymemberId(@Param("memberId") String memberId);
	void delete(@Param("orderId") String orderId);
}
package za.co.rmb.repository.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.rmb.repository.domain.Order;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Long> {
}

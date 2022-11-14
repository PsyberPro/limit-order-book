package za.co.rmb.repository.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.rmb.common.model.Side;
import za.co.rmb.repository.domain.Order;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllBySide(final Side side);
}

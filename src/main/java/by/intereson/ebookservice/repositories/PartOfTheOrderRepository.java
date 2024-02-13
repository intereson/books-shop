package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.PartOfTheOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartOfTheOrderRepository extends JpaRepository<PartOfTheOrder, Long> {
    @Override
    void deleteAllById(Iterable<? extends Long> longs);
}

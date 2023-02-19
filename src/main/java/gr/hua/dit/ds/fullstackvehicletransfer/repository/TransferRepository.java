package gr.hua.dit.ds.fullstackvehicletransfer.repository;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

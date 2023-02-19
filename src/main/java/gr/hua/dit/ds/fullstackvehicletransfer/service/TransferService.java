package gr.hua.dit.ds.fullstackvehicletransfer.service;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;

import java.util.List;

public interface TransferService {

    List<Transfer> getTransfers();
    void cancelTransfer(Long id);

    void acceptTransfer(Long id);

    void rejectTransfer(Long id);

    void saveTransfer(Transfer transfer);
}

package com.app.dvm.service;

import com.app.dvm.records.DealerRecord;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public sealed interface DealerService permits DealerServiceImpl {
    //*****************************CRUD Operations for Dealer Entity***************************/
    List<DealerRecord> getAllDealers();
    DealerRecord getDealerById(Long id);
    DealerRecord createDealer(DealerRecord dealerRecord);
    DealerRecord updateDealer(Long id, DealerRecord dealerRecord);
    void deleteDealer(Long id);
    //**************************************** END *******************************************/

}

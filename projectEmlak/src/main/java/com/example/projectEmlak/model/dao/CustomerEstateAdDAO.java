package com.example.projectEmlak.model.dao;

import com.example.projectEmlak.model.CompanyEstateAd;
import com.example.projectEmlak.model.Customer;
import com.example.projectEmlak.model.CustomerEstateAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerEstateAdDAO extends JpaRepository<CustomerEstateAd, Long> {
    //Optional<CustomerEstateAd> findByEstateAdIDIgnoreCase(Long estateAdID);

    // @Query("SELECT ed FROM CustomerEstateAd ed WHERE ed.customer LIKE %:keyword% OR ed.estateType LIKE %:keyword% OR ed.sqrMeter LIKE %:keyword% OR ed.roomNum LIKE %:keyword% OR ed.floorNum LIKE %:keyword% OR ed.aptFloor LIKE %:keyword% OR ed.heatingType LIKE %:keyword%")
    //@Query("SELECT ed FROM CustomerEstateAd ed WHERE CONCAT(ed.customer, '',ed.estateType,'',ed.sqrMeter,'',ed.roomNum,'',ed.floorNum,'',ed.aptFloor,'',ed.heatingType) LIKE %?1%")
    @Query("SELECT ed FROM CustomerEstateAd ed WHERE CONCAT(ed.customer, '', ed.estateType, '', ed.sqrMeter, '', ed.roomNum, '', ed.floorNum, '', ed.aptFloor, '', ed.heatingType, '', ed.adAddress) LIKE %?1%")
    List<CustomerEstateAd> searchByKeyword(String keyword); //Optional<Customer> yapılamıyo mu

    @Override
    Optional<CustomerEstateAd> findById(Long aLong);

    Optional<CustomerEstateAd> findByEstateTypeIgnoreCase(String estateType);

    //Optional<CustomerEstateAd> findByCustomerIgnoreCase(Customer customer);

    Optional<CustomerEstateAd> findBySqrMeter(Integer sqrMeter);
    Optional<CustomerEstateAd> findByRoomNum(Integer roomNum);
    Optional<CustomerEstateAd> findByFloorNum(Integer floorNum);
    Optional<CustomerEstateAd> findByAptFloor(Integer aptFloor);
    Optional<CustomerEstateAd> findByHeatingTypeIgnoreCase(String heatingType);

    Optional<CompanyEstateAd> findByAdAddressIgnoreCase(String adAddress);
}

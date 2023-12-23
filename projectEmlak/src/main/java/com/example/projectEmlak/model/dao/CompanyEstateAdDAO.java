package com.example.projectEmlak.model.dao;

import com.example.projectEmlak.model.CompanyEstateAd;
import com.example.projectEmlak.model.CustomerEstateAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyEstateAdDAO extends JpaRepository<CompanyEstateAd, Long> {
    //Optional<CompanyEstateAd> findByEstateAdIDIgnoreCase(Long estateAdID);

    // @Query("SELECT ed FROM CustomerEstateAd ed WHERE ed.customer LIKE %:keyword% OR ed.estateType LIKE %:keyword% OR ed.sqrMeter LIKE %:keyword% OR ed.roomNum LIKE %:keyword% OR ed.floorNum LIKE %:keyword% OR ed.aptFloor LIKE %:keyword% OR ed.heatingType LIKE %:keyword%")
    //@Query("SELECT ed FROM CompanyEstateAd ed WHERE CONCAT(ed.estCompany, '',ed.estateType,'',ed.sqrMeter,'',ed.roomNum,'',ed.floorNum,'',ed.aptFloor,'',ed.heatingType) LIKE %?1%")
    @Query("SELECT ed FROM CompanyEstateAd ed WHERE CONCAT(ed.estCompany, '', ed.estateType, '', ed.sqrMeter, '', ed.roomNum, '', ed.floorNum, '', ed.aptFloor, '', ed.heatingType, '', ed.adAddress) LIKE %?1%")
    List<CompanyEstateAd> searchByKeyword(String keyword); //Optional<CompanyEstateAd> yapılamıyo mu

    @Override
    Optional<CompanyEstateAd> findById(Long aLong);

    Optional<CompanyEstateAd> findByEstateTypeIgnoreCase(String estateType);

    //Optional<CompanyEstateAd> findByEstCompanyIgnoreCase(CompanyEstateAd companyEstateAd);

    Optional<CompanyEstateAd> findBySqrMeter(Integer sqrMeter);
    Optional<CompanyEstateAd> findByRoomNum(Integer roomNum);
    Optional<CompanyEstateAd> findByFloorNum(Integer floorNum);
    Optional<CompanyEstateAd> findByAptFloor(Integer aptFloor);
    Optional<CompanyEstateAd> findByHeatingTypeIgnoreCase(String heatingType);

    Optional<CompanyEstateAd> findByAdAddressIgnoreCase(String adAddress);

    //TODO: Delete de yap
}
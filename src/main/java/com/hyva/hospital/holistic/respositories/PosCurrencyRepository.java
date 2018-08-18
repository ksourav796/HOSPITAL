package com.hyva.hospital.holistic.respositories;


import com.hyva.hospital.holistic.entities.Currency;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosCurrencyRepository extends JpaRepository<Currency,Long> {
    List<Currency> findAllByStatus(String status);
    List<Currency> findByCurrencyId(Long currencyId);
    Currency findByCurrencyName(String currencyName);
    Currency findByCurrencyNameAndCurrencyIdNotIn(String name, Long id);
    List<Currency> findAllByCurrencyNameContainingOrCurrencyCodeContaining(String typeName, String typeCode, Pageable pageable);
    List<Currency> findAllByCurrencyNameContainingOrCurrencyCodeContaining(String typeName, String typeCode);
    Currency findFirstByCurrencyNameContainingOrCurrencyCodeContaining(String typeName, String typeCode,Sort sort);
    Currency findFirstBy(Sort sort);
    List<Currency> findAllBy(Pageable pageable);

    void save(java.util.Currency currency);
}

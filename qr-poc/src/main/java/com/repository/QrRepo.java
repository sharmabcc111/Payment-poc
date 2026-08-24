package com.repository;

import com.entity.PaymentQr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrRepo extends JpaRepository<PaymentQr, Long> {

}

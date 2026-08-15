package com.repository;

import com.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepo extends JpaRepository<Organisation , Long> {
}

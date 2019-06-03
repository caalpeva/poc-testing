package org.caalpeva.report.repository;

import java.util.Optional;

import org.caalpeva.report.repository.model.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel, Integer> {
	public Optional<SalesChannel> findByName(String name);
}
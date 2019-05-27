package org.caalpeva.report.repository;

import org.caalpeva.report.model.SalesChannel;

public interface ISalesChannelRepository {
	public SalesChannel findOrSave(String sChannel);
}
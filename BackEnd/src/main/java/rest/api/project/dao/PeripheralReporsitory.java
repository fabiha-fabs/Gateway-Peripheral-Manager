package rest.api.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rest.api.project.models.Peripheral;

public interface PeripheralReporsitory extends CrudRepository<Peripheral, String> {

	public List<Peripheral> findByGatewaySerialNo(String serialNo);
}

package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.ShipperDTO;
import shopping.onlineshopping.mapper.ShipperMapper;
import shopping.onlineshopping.repository.ShipperRepository;

import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private ShipperMapper shipperMapper;

    public ShipperDTO getShipperById(Long id){
        return shipperMapper.toShipperDTO(shipperRepository.findById(id).get());
    }
    public ShipperDTO createShipper(ShipperDTO shipperDTO){
        return shipperMapper.toShipperDTO(shipperRepository.save(shipperMapper.toShipper(shipperDTO)));
    }
    public void deleteShipperById(Long id){
        shipperRepository.deleteById(id);
    }
    public ShipperDTO updateShipper(ShipperDTO shipperDTO){
        return shipperMapper.toShipperDTO(shipperRepository.save(shipperMapper.toShipper(shipperDTO)));
    }
    public List<ShipperDTO> getAllShippers(){
        return shipperRepository.findAll().stream().map(shipperMapper::toShipperDTO).toList();
    }
    public ShipperDTO getShipperByShipperName(String shipperName){
        return shipperMapper.toShipperDTO(shipperRepository.findByShipperShipperName(shipperName));
    }
    public ShipperDTO getShipperByShipperPhone(String phone){
        return shipperMapper.toShipperDTO(shipperRepository.findByShipperPhone(phone));
    }
}

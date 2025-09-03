package shopping.onlineshopping.service.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userdto.ShipperDTO;
import shopping.onlineshopping.mapper.usermapper.ShipperMapper;
import shopping.onlineshopping.repository.userrepository.ShipperRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipperService {
    private final ShipperRepository shipperRepository;
    private final ShipperMapper shipperMapper;

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
        return shipperMapper.toShipperDTO(shipperRepository.findByShipperName(shipperName));
    }
    public ShipperDTO getShipperByShipperPhone(String phone){
        return shipperMapper.toShipperDTO(shipperRepository.findByPhone(phone));
    }
}

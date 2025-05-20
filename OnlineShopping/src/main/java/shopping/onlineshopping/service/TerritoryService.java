package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.TerritoryDTO;
import shopping.onlineshopping.mapper.RegionMapper;
import shopping.onlineshopping.mapper.TerritoryMapper;
import shopping.onlineshopping.repository.TerritoryRepository;

import java.util.List;

@Service
public class TerritoryService {
    @Autowired
    private TerritoryRepository territoryRepository;
    @Autowired
    private TerritoryMapper territoryMapper;

    public void deleteTerritoryById(Long id){
        territoryRepository.deleteById(id);
    }
    public TerritoryDTO getTerritoryById(Long id){
        return territoryMapper.toTerritoryDTO(territoryRepository.findById(id).get());
    }
    public TerritoryDTO createTerritory(TerritoryDTO territoryDTO){
        return territoryMapper.toTerritoryDTO(territoryRepository.save(territoryMapper.toTerritory(territoryDTO)));
    }
    public TerritoryDTO updateTerritory(TerritoryDTO territoryDTO){
        return territoryMapper.toTerritoryDTO(territoryRepository.save(territoryMapper.toTerritory(territoryDTO)));
    }
    public List<TerritoryDTO> getAllTerritories(){
        return territoryRepository.findAll().stream().map(territoryMapper::toTerritoryDTO).toList();
    }
    public TerritoryDTO getTerritoriesByDescription(String description){
        return territoryMapper.toTerritoryDTO(territoryRepository.findByTerritoryDescription(description));
    }
    public TerritoryDTO getTerritoryByRegion(Long regionId){
        return territoryMapper.toTerritoryDTO(territoryRepository.findByRegionId(regionId));
    }
}

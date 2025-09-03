package shopping.onlineshopping.service;

import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.TerritoryDTO;
import shopping.onlineshopping.mapper.TerritoryMapper;
import shopping.onlineshopping.repository.TerritoryRepository;

import java.util.List;

@Service
public class TerritoryService {
    private final TerritoryRepository territoryRepository;
    private final TerritoryMapper territoryMapper;

    public TerritoryService(TerritoryRepository territoryRepository, TerritoryMapper territoryMapper) {
        this.territoryRepository = territoryRepository;
        this.territoryMapper = territoryMapper;
    }

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
    public List<TerritoryDTO> getTerritoriesByRegion(Long regionId){
        return territoryRepository.findAllByRegionRegionId(regionId)
                .stream()
                .map(territoryMapper::toTerritoryDTO)
                .toList();
    }
}

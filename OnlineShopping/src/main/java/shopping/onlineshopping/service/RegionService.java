package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.RegionDTO;
import shopping.onlineshopping.mapper.RegionMapper;
import shopping.onlineshopping.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private RegionMapper regionMapper;

    public RegionDTO getRegionById(Long id){
        return regionMapper.toRegionDTO(regionRepository.findById(id).get());
    }
    public List<RegionDTO> getAllRegions(){
        return regionRepository.findAll().stream().map(regionMapper::toRegionDTO).toList();
    }
    public RegionDTO getRegionByDescription(String description){
        return regionMapper.toRegionDTO(regionRepository.findByRegionDescription(description));
    }

}


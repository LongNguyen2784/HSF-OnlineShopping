package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.SupplierDTO;
import shopping.onlineshopping.mapper.SupplierMapper;
import shopping.onlineshopping.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    public List<SupplierDTO> getAllSuppliers(){
        return supplierRepository.findAll().stream().map(supplierMapper::toSupplierDTO).toList();
    }
    public SupplierDTO getSupplierById(Long id){
        return supplierMapper.toSupplierDTO(supplierRepository.findById(id).get());
    }
    public SupplierDTO createSupplier(SupplierDTO supplierDTO){
        return supplierMapper.toSupplierDTO(supplierRepository.save(supplierMapper.toSupplier(supplierDTO)));
    }
    public void deleteSupplierById(Long id){
        supplierRepository.deleteById(id);
    }
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO){
        return supplierMapper.toSupplierDTO(supplierRepository.save(supplierMapper.toSupplier(supplierDTO)));
    }
    public SupplierDTO getSupplierByCompanyName(String companyName){
        return supplierMapper.toSupplierDTO(supplierRepository.findByCompanyName(companyName));
    }
    public List<SupplierDTO> getAllSuppliersByCity(String city){
        return supplierRepository.findByCity(city).stream().map(supplierMapper::toSupplierDTO).toList();
    }
}

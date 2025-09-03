package shopping.onlineshopping.service.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userdto.SupplierApplicationRequest;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Status;
import shopping.onlineshopping.modal.user.Supplier;
import shopping.onlineshopping.modal.user.SupplierApplication;
import shopping.onlineshopping.repository.userrepository.AccountRepository;
import shopping.onlineshopping.repository.userrepository.SupplierApplicationRepository;
import shopping.onlineshopping.repository.userrepository.SupplierRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SupplierApplicationService {
    private final SupplierApplicationRepository applicationRepository;
    private final AccountRepository accountRepository;
    private final SupplierRepository supplierRepository;

    public void submitApplication(Long accountId, SupplierApplicationRequest request) {
        if (applicationRepository.existsByAccount_IdAndStatus(accountId, Status.INACTIVE)) {
            throw new RuntimeException("You already have a pending application");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        SupplierApplication application = new SupplierApplication();
        application.setAccount(account);
        application.setCompanyName(request.getCompanyName());
        application.setContactName(request.getContactName());
        application.setContactTitle(request.getContactTitle());
        application.setAddress(request.getAddress());
        application.setCity(request.getCity());
        application.setRegion(request.getRegion());
        application.setCountry(request.getCountry());
        application.setPostalCode(request.getPostalCode());
        application.setPhone(request.getPhone());
        application.setFax(request.getFax());
        application.setHomePage(request.getHomePage());

        applicationRepository.save(application);
    }

    public void approveApplication(Long applicationId, String adminNote) {
        SupplierApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(Status.INACTIVE);
        application.setReviewedAt(LocalDateTime.now());
        application.setAdminNote(adminNote);
        applicationRepository.save(application);

        Supplier supplier = new Supplier();
        supplier.setSupplierId(application.getAccount().getId());
        supplier.setCompanyName(application.getCompanyName());
        supplier.setContactName(application.getContactName());
        supplier.setContactTitle(application.getContactTitle());
        supplier.setAddress(application.getAddress());
        supplier.setCity(application.getCity());
        supplier.setRegion(application.getRegion());
        supplier.setCountry(application.getCountry());
        supplier.setPostalCode(application.getPostalCode());
        supplier.setPhone(application.getPhone());
        supplier.setFax(application.getFax());
        supplier.setHomePage(application.getHomePage());

        supplierRepository.save(supplier);
    }
}

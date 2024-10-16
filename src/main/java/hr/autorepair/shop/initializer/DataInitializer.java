package hr.autorepair.shop.initializer;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import hr.autorepair.shop.domain.workplace.repository.WorkplaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final WorkplaceRepository workplaceRepository;
    private final RepairRepository repairRepository;
    private final JobOrderRepository jobOrderRepository;
    private final ReceiptRepository receiptRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        AppUser appUser = new AppUser();
        appUser.setFirstName("Bruno");
        appUser.setLastName("Brnić");
        appUser.setEmail("bruno.brnic@gmail.com");
        appUser.setPassword(PasswordUtil.getEncodedPassword("Test"));
        appUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        String rola = RoleEnum.ADMIN.getName();
        role = roleRepository.findByName(rola)
                .orElseThrow(() -> new BadRequestException("Ne postoji rola koja se zove " + rola + "."));
        appUser.setRole(role);
        appUser.setIsActivated(true);

        appUserRepository.save(appUser);

        Workplace workplace = new Workplace();
        workplace.setName("Radionica 1");
        workplaceRepository.save(workplace);

        workplace = new Workplace();
        workplace.setName("Radionica 2");
        workplaceRepository.save(workplace);

        Repair repair = new Repair();
        repair.setName("Zamjena ulja motora");
        repair.setCost(BigDecimal.valueOf(60));
        repair.setRepairTime(2L);
        repairRepository.save(repair);

        JobOrder jobOrder = new JobOrder();
        jobOrder.setDescription("Nalog broj 1");
        jobOrder.setOrderDate(LocalDate.now());
        jobOrder.setTimeFrom(LocalTime.of(8,0));
        jobOrder.setTimeTo(LocalTime.of(10,0));
        jobOrder.setIsFinished(false);
        jobOrder.setWorkplace(workplace);
        jobOrder.setJobOrderAppUserEmployee(appUser);
        jobOrder.setJobOrderAppUserClient(appUser);

        Set<Repair> repairs = new HashSet<>();
        repairs.add(repair);
        jobOrder.setRepairs(repairs);
        jobOrderRepository.save(jobOrder);

        Receipt receipt = new Receipt();
        receipt.setCreatedAt(LocalDateTime.now());
        receipt.setJobOrder(jobOrder);
        receipt.setTotalCost(BigDecimal.valueOf(100));
        receiptRepository.save(receipt);

    }

}

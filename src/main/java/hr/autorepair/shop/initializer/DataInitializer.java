package hr.autorepair.shop.initializer;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
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
    private final CarRepository carRepository;

    @Override
    public void run(ApplicationArguments args) {
    //---------- ROLE ----------
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role employeeRole = new Role();
        employeeRole.setName("EMPLOYEE");
        roleRepository.save(employeeRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);
    //------------------------------------------------------------------------------------------------------------------
    //---------- USERI ----------
        AppUser damjan = new AppUser();
        damjan.setFirstName("Damjan");
        damjan.setLastName("Kovačev");
        damjan.setEmail("damjan356@gmail.com");
        damjan.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        damjan.setTstamp(new Timestamp(System.currentTimeMillis()));
        damjan.setRole(adminRole);
        damjan.setIsActivated(true);
        damjan.setIsDeleted(false);
        appUserRepository.save(damjan);

        AppUser damjanUser = new AppUser();
        damjanUser.setFirstName("Damjan_USER");
        damjanUser.setLastName("Kovačev");
        damjanUser.setEmail("damjan.kovacev01@hotmail.com");
        damjanUser.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        damjanUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        damjanUser.setRole(userRole);
        damjanUser.setIsActivated(false);
        damjanUser.setIsDeleted(false);
        appUserRepository.save(damjanUser);

        AppUser bruno = new AppUser();
        bruno.setFirstName("Bruno");
        bruno.setLastName("Brnić");
        bruno.setEmail("bruno.brnic@gmail.com");
        bruno.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        bruno.setTstamp(new Timestamp(System.currentTimeMillis()));
        bruno.setRole(employeeRole);
        bruno.setIsActivated(true);
        bruno.setIsDeleted(false);
        appUserRepository.save(bruno);

        AppUser maks = new AppUser();
        maks.setFirstName("Maks");
        maks.setLastName("Režek");
        maks.setEmail("maksrezek@gmail.com");
        maks.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        maks.setTstamp(new Timestamp(System.currentTimeMillis()));
        maks.setRole(userRole);
        maks.setIsActivated(true);
        maks.setIsDeleted(false);
        appUserRepository.save(maks);

    //------------------------------------------------------------------------------------------------------------------
    //---------- AUTI ---------- //dodavati samo za rolu USER
        Car car1 = new Car();
        car1.setMaker("Škoda");
        car1.setModel("Octavia III");
        car1.setCylinders(4L);
        car1.setDisplacement(BigDecimal.valueOf(1.6));
        car1.setYearOfProduction(2015L);
        car1.setFuelType("Diesel");
        car1.setCarOwner(damjanUser);
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setMaker("Renault");
        car2.setModel("Clio");
        car2.setCylinders(4L);
        car2.setDisplacement(BigDecimal.valueOf(1.5));
        car2.setYearOfProduction(2010L);
        car2.setFuelType("Diesel");
        car2.setCarOwner(maks);
        carRepository.save(car2);

        Car car3 = new Car();
        car3.setMaker("Opel");
        car3.setModel("Corsa");
        car3.setCylinders(4L);
        car3.setDisplacement(BigDecimal.valueOf(1.7));
        car3.setYearOfProduction(2005L);
        car3.setFuelType("Diesel");
        car3.setCarOwner(maks);
        carRepository.save(car3);

    //------------------------------------------------------------------------------------------------------------------
    //---------- RADIONICE ----------
        Workplace workplace1 = new Workplace();
        workplace1.setName("Radionica 1");
        workplace1.setIsDeleted(false);
        workplaceRepository.save(workplace1);

        Workplace workplace2 = new Workplace();
        workplace2.setName("Radionica 2");
        workplace2.setIsDeleted(false);
        workplaceRepository.save(workplace2);
    //------------------------------------------------------------------------------------------------------------------
    //---------- POPRAVCI ----------
        Repair repair = new Repair();
        repair.setName("Mali servis");
        repair.setCost(BigDecimal.valueOf(130));
        repair.setRepairTime(LocalTime.of(2,0));
        repair.setIsDeleted(false);
        repairRepository.save(repair);

        Repair repair0 = new Repair();
        repair0.setName("Veliki servis");
        repair0.setCost(BigDecimal.valueOf(500));
        repair0.setRepairTime(LocalTime.of(5,0));
        repair0.setIsDeleted(false);
        repairRepository.save(repair0);

        Repair repair00 = new Repair();
        repair00.setName("Pregled vozila");
        repair00.setCost(BigDecimal.valueOf(20));
        repair00.setRepairTime(LocalTime.of(0,30));
        repair00.setIsDeleted(false);
        repairRepository.save(repair00);

        Repair repair1 = new Repair();
        repair1.setName("Zamjena ulja motora");
        repair1.setCost(BigDecimal.valueOf(60));
        repair1.setRepairTime(LocalTime.of(2,0));
        repair1.setIsDeleted(false);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setName("Zamjena kočionih pločica");
        repair2.setCost(BigDecimal.valueOf(80));
        repair2.setRepairTime(LocalTime.of(3,0));
        repair2.setIsDeleted(false);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setName("Popravak brava vrata");
        repair3.setCost(BigDecimal.valueOf(40));
        repair3.setRepairTime(LocalTime.of(1,0));
        repair3.setIsDeleted(false);
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setName("Zamjena stakla prednjeg aranžmana");
        repair4.setCost(BigDecimal.valueOf(120));
        repair4.setRepairTime(LocalTime.of(4,0));
        repair4.setIsDeleted(false);
        repairRepository.save(repair4);

        Repair repair5 = new Repair();
        repair5.setName("Servis klima uređaja");
        repair5.setCost(BigDecimal.valueOf(100));
        repair5.setRepairTime(LocalTime.of(2,0));
        repair5.setIsDeleted(false);
        repairRepository.save(repair5);

        Repair repair6 = new Repair();
        repair6.setName("Zamjena filtera zraka");
        repair6.setCost(BigDecimal.valueOf(30));
        repair6.setRepairTime(LocalTime.of(1,0));
        repair6.setIsDeleted(false);
        repairRepository.save(repair6);

        Repair repair7 = new Repair();
        repair7.setName("Podešavanje svjetala");
        repair7.setCost(BigDecimal.valueOf(20));
        repair7.setRepairTime(LocalTime.of(1,0));
        repair7.setIsDeleted(false);
        repairRepository.save(repair7);

        Repair repair8 = new Repair();
        repair8.setName("Popravak ispušnog sustava");
        repair8.setCost(BigDecimal.valueOf(150));
        repair8.setRepairTime(LocalTime.of(3,0));
        repair8.setIsDeleted(false);
        repairRepository.save(repair8);

        Repair repair9 = new Repair();
        repair9.setName("Zamjena guma");
        repair9.setCost(BigDecimal.valueOf(200));
        repair9.setRepairTime(LocalTime.of(2,0));
        repair9.setIsDeleted(false);
        repairRepository.save(repair9);

        Repair repair10 = new Repair();
        repair10.setName("Popravak akumulatora");
        repair10.setCost(BigDecimal.valueOf(75));
        repair10.setRepairTime(LocalTime.of(2,0));
        repair10.setIsDeleted(false);
        repairRepository.save(repair10);

        Repair repair11 = new Repair();
        repair11.setName("Zamjena remena");
        repair11.setCost(BigDecimal.valueOf(90));
        repair11.setRepairTime(LocalTime.of(3,0));
        repair11.setIsDeleted(false);
        repairRepository.save(repair11);

        Repair repair12 = new Repair();
        repair12.setName("Servis mjenjača");
        repair12.setCost(BigDecimal.valueOf(250));
        repair12.setRepairTime(LocalTime.of(5,0));
        repair12.setIsDeleted(false);
        repairRepository.save(repair12);

        Repair repair13 = new Repair();
        repair13.setName("Zamjena diskova kočnica");
        repair13.setCost(BigDecimal.valueOf(180));
        repair13.setRepairTime(LocalTime.of(4,0));
        repair13.setIsDeleted(false);
        repairRepository.save(repair13);

        Repair repair14 = new Repair();
        repair14.setName("Preventivni pregled vozila");
        repair14.setCost(BigDecimal.valueOf(50));
        repair14.setRepairTime(LocalTime.of(1,0));
        repair14.setIsDeleted(false);
        repairRepository.save(repair14);

        Repair repair15 = new Repair();
        repair15.setName("Farbjanje karoserije");
        repair15.setCost(BigDecimal.valueOf(300));
        repair15.setRepairTime(LocalTime.of(7,0));
        repair15.setIsDeleted(false);
        repairRepository.save(repair15);
    //------------------------------------------------------------------------------------------------------------------
    //---------- NALOZI ----------
        JobOrder jobOrder1 = new JobOrder();
        jobOrder1.setDescription("Mali servis na vozilu");
        jobOrder1.setOrderDate(LocalDate.now());
        jobOrder1.setTimeFrom(LocalTime.of(8,0));
        jobOrder1.setTimeTo(LocalTime.of(10,0));
        jobOrder1.setIsFinished(false);
        jobOrder1.setWorkplace(workplace1);
        jobOrder1.setJobOrderAppUserEmployee(damjan);
        Set<Repair> repairs1 = new HashSet<>();
        repairs1.add(repair);
        jobOrder1.setRepairs(repairs1);
        jobOrder1.setCar(car1);
        jobOrderRepository.save(jobOrder1);

        JobOrder jobOrder2 = new JobOrder();
        jobOrder2.setDescription("Nalog broj 2 - veliki servis na vozilu i pregled vozila");
        jobOrder2.setOrderDate(LocalDate.now());
        jobOrder2.setTimeFrom(LocalTime.of(8,0));
        jobOrder2.setTimeTo(LocalTime.of(12,30));
        jobOrder2.setIsFinished(false);
        jobOrder2.setWorkplace(workplace1);
        jobOrder2.setJobOrderAppUserEmployee(bruno);
        Set<Repair> repairs2 = new HashSet<>();
        repairs2.add(repair0);
        repairs2.add(repair00);
        jobOrder2.setRepairs(repairs2);
        jobOrder2.setCar(car2);
        jobOrderRepository.save(jobOrder2);
    //------------------------------------------------------------------------------------------------------------------
    //---------- RACUNI ----------
        Receipt receipt = new Receipt();
        receipt.setCreatedAt(LocalDateTime.now());
        receipt.setJobOrder(jobOrder1);
        receipt.setTotalCost(BigDecimal.valueOf(100));
        receipt.setReceiptAppUserEmployee(damjan);
        receiptRepository.save(receipt);

    }

}

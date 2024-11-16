package hr.autorepair.shop.initializer;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.joborderpart.model.JobOrderPart;
import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import hr.autorepair.shop.domain.joborderstatus.repository.JobOrderStatusRepository;
import hr.autorepair.shop.domain.joborderstatus.util.JobOrderStatusEnum;
import hr.autorepair.shop.domain.part.model.Part;
import hr.autorepair.shop.domain.part.repository.PartRepository;
import hr.autorepair.shop.domain.payment.model.Payment;
import hr.autorepair.shop.domain.payment.repository.PaymentRepository;
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
    private final CarRepository carRepository;
    private final JobOrderStatusRepository jobOrderStatusRepository;
    private final PaymentRepository paymentRepository;
    private final PartRepository partRepository;

    @Override
    public void run(ApplicationArguments args) {
    //---------- ROLE ----------
        Role adminRole = new Role();
        adminRole.setName(RoleEnum.ADMIN.getName());
        roleRepository.save(adminRole);

        Role employeeRole = new Role();
        employeeRole.setName(RoleEnum.EMPLOYEE.getName());
        roleRepository.save(employeeRole);

        Role userRole = new Role();
        userRole.setName(RoleEnum.USER.getName());
        roleRepository.save(userRole);
    //------------------------------------------------------------------------------------------------------------------
    //---------- USERI ----------
        AppUser damjan = new AppUser();
        damjan.setFirstName("Damjan");
        damjan.setLastName("Kovačev");
        damjan.setEmail("damjan356@gmail.com");
        damjan.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        damjan.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        damjan.setRole(adminRole);
        damjan.setIsActivated(true);
        damjan.setIsDeleted(false);
        appUserRepository.save(damjan);

        AppUser damjanUser = new AppUser();
        damjanUser.setFirstName("Damjan_USER");
        damjanUser.setLastName("Kovačev");
        damjanUser.setEmail("damjan.kovacev01@hotmail.com");
        damjanUser.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        damjanUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        damjanUser.setRole(userRole);
        damjanUser.setIsActivated(false);
        damjanUser.setIsDeleted(false);
        appUserRepository.save(damjanUser);

        AppUser bruno = new AppUser();
        bruno.setFirstName("Bruno");
        bruno.setLastName("Brnić");
        bruno.setEmail("bruno.brnic@gmail.com");
        bruno.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        bruno.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bruno.setRole(employeeRole);
        bruno.setIsActivated(true);
        bruno.setIsDeleted(false);
        appUserRepository.save(bruno);

        AppUser maks = new AppUser();
        maks.setFirstName("Maks");
        maks.setLastName("Režek");
        maks.setEmail("maksrezek@gmail.com");
        maks.setPassword(PasswordUtil.getEncodedPassword("test1234"));
        maks.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        maks.setRole(userRole);
        maks.setIsActivated(true);
        maks.setIsDeleted(false);
        appUserRepository.save(maks);

    //------------------------------------------------------------------------------------------------------------------
    //---------- AUTI ---------- //dodavati samo za rolu USER
        Car car1 = new Car();
        car1.setRegistrationPlate("ZG-2953-A");
        car1.setMaker("Škoda");
        car1.setModel("Octavia III");
        car1.setCylinders(4L);
        car1.setDisplacement(BigDecimal.valueOf(1.6));
        car1.setYearOfProduction(2015L);
        car1.setFuelType("Diesel");
        car1.setIsDeleted(false);
        car1.setCarOwner(damjanUser);
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setRegistrationPlate("ZG-2953-A");
        car2.setMaker("Renault");
        car2.setModel("Clio");
        car2.setCylinders(4L);
        car2.setDisplacement(BigDecimal.valueOf(1.5));
        car2.setYearOfProduction(2010L);
        car2.setFuelType("Diesel");
        car2.setIsDeleted(false);
        car2.setCarOwner(maks);
        carRepository.save(car2);

        Car car3 = new Car();
        car3.setRegistrationPlate("ZG-2953-A");
        car3.setMaker("Opel");
        car3.setModel("Corsa");
        car3.setCylinders(4L);
        car3.setDisplacement(BigDecimal.valueOf(1.7));
        car3.setYearOfProduction(2005L);
        car3.setFuelType("Diesel");
        car3.setIsDeleted(false);
        car3.setCarOwner(maks);
        carRepository.save(car3);

        Car car4 = new Car();
        car4.setRegistrationPlate("RI-4586-B");
        car4.setMaker("Volkswagen");
        car4.setModel("Golf V");
        car4.setCylinders(4L);
        car4.setDisplacement(BigDecimal.valueOf(1.9));
        car4.setYearOfProduction(2008L);
        car4.setFuelType("Diesel");
        car4.setIsDeleted(false);
        car4.setCarOwner(damjanUser);
        carRepository.save(car4);

        Car car5 = new Car();
        car5.setRegistrationPlate("ST-2341-C");
        car5.setMaker("Toyota");
        car5.setModel("Corolla");
        car5.setCylinders(4L);
        car5.setDisplacement(BigDecimal.valueOf(1.8));
        car5.setYearOfProduction(2019L);
        car5.setFuelType("Petrol");
        car5.setIsDeleted(false);
        car5.setCarOwner(maks);
        carRepository.save(car5);

        Car car6 = new Car();
        car6.setRegistrationPlate("OS-9876-D");
        car6.setMaker("Ford");
        car6.setModel("Fiesta");
        car6.setCylinders(4L);
        car6.setDisplacement(BigDecimal.valueOf(1.5));
        car6.setYearOfProduction(2021L);
        car6.setFuelType("Hybrid");
        car6.setIsDeleted(false);
        car6.setCarOwner(bruno);
        carRepository.save(car6);
    //------------------------------------------------------------------------------------------------------------------
    //---------- RADIONICE ----------
        Workplace workplace1 = new Workplace();
        workplace1.setName("Workplace 1");
        workplace1.setIsDeleted(false);
        workplaceRepository.save(workplace1);

        Workplace workplace2 = new Workplace();
        workplace2.setName("Workplace 2");
        workplace2.setIsDeleted(false);
        workplaceRepository.save(workplace2);
    //------------------------------------------------------------------------------------------------------------------
    //---------- POPRAVCI ----------
        Repair repair00 = new Repair();
        repair00.setName("Vehicle inspection");
        repair00.setCost(BigDecimal.valueOf(20));
        repair00.setRepairTime(LocalTime.of(0,30));
        repair00.setIsDeleted(false);
        repairRepository.save(repair00);

        Repair repair1 = new Repair();
        repair1.setName("Engine oil change");
        repair1.setCost(BigDecimal.valueOf(60));
        repair1.setRepairTime(LocalTime.of(2,0));
        repair1.setIsDeleted(false);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setName("Brake pad replacement");
        repair2.setCost(BigDecimal.valueOf(80));
        repair2.setRepairTime(LocalTime.of(3,0));
        repair2.setIsDeleted(false);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setName("Door lock repair");
        repair3.setCost(BigDecimal.valueOf(40));
        repair3.setRepairTime(LocalTime.of(1,0));
        repair3.setIsDeleted(false);
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setName("Front window glass replacement");
        repair4.setCost(BigDecimal.valueOf(120));
        repair4.setRepairTime(LocalTime.of(4,0));
        repair4.setIsDeleted(false);
        repairRepository.save(repair4);

        Repair repair5 = new Repair();
        repair5.setName("Air conditioning service");
        repair5.setCost(BigDecimal.valueOf(100));
        repair5.setRepairTime(LocalTime.of(2,0));
        repair5.setIsDeleted(false);
        repairRepository.save(repair5);

        Repair repair6 = new Repair();
        repair6.setName("Air filter replacement");
        repair6.setCost(BigDecimal.valueOf(30));
        repair6.setRepairTime(LocalTime.of(1,0));
        repair6.setIsDeleted(false);
        repairRepository.save(repair6);

        Repair repair7 = new Repair();
        repair7.setName("Headlight adjustment");
        repair7.setCost(BigDecimal.valueOf(20));
        repair7.setRepairTime(LocalTime.of(1,0));
        repair7.setIsDeleted(false);
        repairRepository.save(repair7);

        Repair repair8 = new Repair();
        repair8.setName("Exhaust system repair");
        repair8.setCost(BigDecimal.valueOf(150));
        repair8.setRepairTime(LocalTime.of(3,0));
        repair8.setIsDeleted(false);
        repairRepository.save(repair8);

        Repair repair9 = new Repair();
        repair9.setName("Tire replacement");
        repair9.setCost(BigDecimal.valueOf(200));
        repair9.setRepairTime(LocalTime.of(2,0));
        repair9.setIsDeleted(false);
        repairRepository.save(repair9);

        Repair repair10 = new Repair();
        repair10.setName("Battery repair");
        repair10.setCost(BigDecimal.valueOf(75));
        repair10.setRepairTime(LocalTime.of(2,0));
        repair10.setIsDeleted(false);
        repairRepository.save(repair10);

        Repair repair11 = new Repair();
        repair11.setName("Belt replacement");
        repair11.setCost(BigDecimal.valueOf(90));
        repair11.setRepairTime(LocalTime.of(3,0));
        repair11.setIsDeleted(false);
        repairRepository.save(repair11);

        Repair repair12 = new Repair();
        repair12.setName("Transmission service");
        repair12.setCost(BigDecimal.valueOf(250));
        repair12.setRepairTime(LocalTime.of(5,0));
        repair12.setIsDeleted(false);
        repairRepository.save(repair12);

        Repair repair13 = new Repair();
        repair13.setName("Brake disc replacement");
        repair13.setCost(BigDecimal.valueOf(180));
        repair13.setRepairTime(LocalTime.of(4,0));
        repair13.setIsDeleted(false);
        repairRepository.save(repair13);

        Repair repair14 = new Repair();
        repair14.setName("Preventive vehicle inspection");
        repair14.setCost(BigDecimal.valueOf(50));
        repair14.setRepairTime(LocalTime.of(1,0));
        repair14.setIsDeleted(false);
        repairRepository.save(repair14);

        Repair repair15 = new Repair();
        repair15.setName("Body painting");
        repair15.setCost(BigDecimal.valueOf(300));
        repair15.setRepairTime(LocalTime.of(7,0));
        repair15.setIsDeleted(false);
        repairRepository.save(repair15);

        Repair repair16 = new Repair();
        repair16.setName("Suspension check");
        repair16.setCost(BigDecimal.valueOf(70));
        repair16.setRepairTime(LocalTime.of(2, 30));
        repair16.setIsDeleted(false);
        repairRepository.save(repair16);

        Repair repair17 = new Repair();
        repair17.setName("Wheel alignment");
        repair17.setCost(BigDecimal.valueOf(50));
        repair17.setRepairTime(LocalTime.of(1, 30));
        repair17.setIsDeleted(false);
        repairRepository.save(repair17);

        Repair repair18 = new Repair();
        repair18.setName("Windshield replacement");
        repair18.setCost(BigDecimal.valueOf(200));
        repair18.setRepairTime(LocalTime.of(4, 0));
        repair18.setIsDeleted(false);
        repairRepository.save(repair18);

        Repair repair19 = new Repair();
        repair19.setName("Transmission fluid change");
        repair19.setCost(BigDecimal.valueOf(100));
        repair19.setRepairTime(LocalTime.of(3, 0));
        repair19.setIsDeleted(false);
        repairRepository.save(repair19);
    //------------------------------------------------------------------------------------------------------------------
    //---------- DIJELOVI ----------
        Part part = new Part();
        part.setName("Oil filer");
        part.setCost(BigDecimal.valueOf(20));
        part.setIsDeleted(false);
        partRepository.save(part);

        Part part1 = new Part();
        part1.setName("Fuel filter");
        part1.setCost(BigDecimal.valueOf(15));
        part1.setIsDeleted(false);
        partRepository.save(part1);

        Part part2 = new Part();
        part2.setName("Oil 5W30");
        part2.setCost(BigDecimal.valueOf(7));
        part2.setIsDeleted(false);
        partRepository.save(part2);

        Part part3 = new Part();
        part3.setName("Oil 0W30");
        part3.setCost(BigDecimal.valueOf(7.5));
        part3.setIsDeleted(false);
        partRepository.save(part3);

        Part part4 = new Part();
        part4.setName("Timing belt");
        part4.setCost(BigDecimal.valueOf(40));
        part4.setIsDeleted(false);
        partRepository.save(part4);

        Part part5 = new Part();
        part5.setName("Timing chain");
        part5.setCost(BigDecimal.valueOf(150));
        part5.setIsDeleted(false);
        partRepository.save(part5);

        Part part6 = new Part();
        part6.setName("Brake fluid");
        part6.setCost(BigDecimal.valueOf(10));
        part6.setIsDeleted(false);
        partRepository.save(part6);

        Part part7 = new Part();
        part7.setName("Spark plugs");
        part7.setCost(BigDecimal.valueOf(8));
        part7.setIsDeleted(false);
        partRepository.save(part7);

        Part part8 = new Part();
        part8.setName("Windshield wiper");
        part8.setCost(BigDecimal.valueOf(15));
        part8.setIsDeleted(false);
        partRepository.save(part8);
    //------------------------------------------------------------------------------------------------------------------
    //---------- STATUSI NALOGA ----------
        JobOrderStatus created = new JobOrderStatus();
        created.setName(JobOrderStatusEnum.CREATED.getName());
        jobOrderStatusRepository.save(created);

        JobOrderStatus inProgress = new JobOrderStatus();
        inProgress.setName(JobOrderStatusEnum.IN_PROGRESS.getName());
        jobOrderStatusRepository.save(inProgress);

        JobOrderStatus finished = new JobOrderStatus();
        finished.setName(JobOrderStatusEnum.FINISHED.getName());
        jobOrderStatusRepository.save(finished);
    //------------------------------------------------------------------------------------------------------------------
    //---------- NALOZI ----------
        JobOrder jobOrder1 = new JobOrder();
        jobOrder1.setDescription("Oil change");
        jobOrder1.setOrderDate(LocalDate.now());
        jobOrder1.setTimeFrom(LocalTime.of(8,0));
        jobOrder1.setTimeTo(LocalTime.of(10,0));
        jobOrder1.setIsDeleted(false);
        jobOrder1.setWorkplace(workplace1);
        jobOrder1.setJobOrderAppUserEmployee(damjan);
        jobOrder1.setCar(car3);
        jobOrder1.setJobOrderStatus(finished);
        Set<Repair> repairs1 = new HashSet<>();
        repairs1.add(repair1);
        repairs1.add(repair2);
        repairs1.add(repair3);
        repairs1.add(repair4);
        repairs1.add(repair5);

        jobOrder1.setRepairs(repairs1);

        JobOrderPart jobOrderPart = new JobOrderPart();
        jobOrderPart.setPart(part);
        jobOrderPart.setQuantity(1);
        jobOrder1.addJobOrderPart(jobOrderPart);

        JobOrderPart jobOrderPart1 = new JobOrderPart();
        jobOrderPart1.setPart(part1);
        jobOrderPart1.setQuantity(1);
        jobOrder1.addJobOrderPart(jobOrderPart1);

        JobOrderPart jobOrderPart2 = new JobOrderPart();
        jobOrderPart2.setPart(part2);
        jobOrderPart2.setQuantity(5);
        jobOrder1.addJobOrderPart(jobOrderPart2);

        jobOrderRepository.save(jobOrder1);

        JobOrder jobOrder2 = new JobOrder();
        jobOrder2.setDescription("Timing belt change and oil change");
        jobOrder2.setOrderDate(LocalDate.now());
        jobOrder2.setTimeFrom(LocalTime.of(8,0));
        jobOrder2.setTimeTo(LocalTime.of(11,0));
        jobOrder2.setIsDeleted(false);
        jobOrder2.setWorkplace(workplace2);
        jobOrder2.setJobOrderAppUserEmployee(bruno);
        Set<Repair> repairs2 = new HashSet<>();
        repairs2.add(repair00);
        jobOrder2.setRepairs(repairs2);
        jobOrder2.setCar(car2);
        jobOrder2.setJobOrderStatus(inProgress);
        jobOrderRepository.save(jobOrder2);

        JobOrder jobOrder3 = new JobOrder();
        jobOrder3.setDescription("Suspension check and wheel alignment");
        jobOrder3.setOrderDate(LocalDate.now().plusDays(1));
        jobOrder3.setTimeFrom(LocalTime.of(9, 0));
        jobOrder3.setTimeTo(LocalTime.of(12, 0));
        jobOrder3.setIsDeleted(false);
        jobOrder3.setWorkplace(workplace1);
        jobOrder3.setJobOrderAppUserEmployee(damjan);
        jobOrder3.setCar(car4);
        jobOrder3.setJobOrderStatus(inProgress);

        Set<Repair> repairs3 = new HashSet<>();
        repairs3.add(repair16);
        repairs3.add(repair17);
        jobOrder3.setRepairs(repairs3);

        JobOrderPart jobOrderPart3 = new JobOrderPart();
        jobOrderPart3.setPart(part6);
        jobOrderPart3.setQuantity(2);
        jobOrder3.addJobOrderPart(jobOrderPart3);

        jobOrderRepository.save(jobOrder3);

        JobOrder jobOrder4 = new JobOrder();
        jobOrder4.setDescription("Windshield replacement and fluid change");
        jobOrder4.setOrderDate(LocalDate.now().plusDays(1));
        jobOrder4.setTimeFrom(LocalTime.of(10, 0));
        jobOrder4.setTimeTo(LocalTime.of(14, 0));
        jobOrder4.setIsDeleted(false);
        jobOrder4.setWorkplace(workplace2);
        jobOrder4.setJobOrderAppUserEmployee(bruno);
        jobOrder4.setCar(car6);
        jobOrder4.setJobOrderStatus(created);

        Set<Repair> repairs4 = new HashSet<>();
        repairs4.add(repair18);
        repairs4.add(repair19);
        jobOrder4.setRepairs(repairs4);

        JobOrderPart jobOrderPart4 = new JobOrderPart();
        jobOrderPart4.setPart(part8);
        jobOrderPart4.setQuantity(1);
        jobOrder4.addJobOrderPart(jobOrderPart4);

        jobOrderRepository.save(jobOrder4);

        JobOrder jobOrder5 = new JobOrder();
        jobOrder5.setDescription("Brake pad and disc replacement");
        jobOrder5.setOrderDate(LocalDate.now().plusDays(1));
        jobOrder5.setTimeFrom(LocalTime.of(10, 0));
        jobOrder5.setTimeTo(LocalTime.of(14, 0));
        jobOrder5.setIsDeleted(false);
        jobOrder5.setWorkplace(workplace1);
        jobOrder5.setJobOrderAppUserEmployee(bruno);
        jobOrder5.setCar(car5);
        jobOrder5.setJobOrderStatus(inProgress);

        Set<Repair> repairs5 = new HashSet<>();
        repairs5.add(repair2); // Brake pad replacement
        repairs5.add(repair13); // Brake disc replacement
        jobOrder5.setRepairs(repairs5);

        JobOrderPart jobOrderPart5 = new JobOrderPart();
        jobOrderPart5.setPart(part6); // Brake fluid
        jobOrderPart5.setQuantity(1);
        jobOrder5.addJobOrderPart(jobOrderPart5);

        jobOrderRepository.save(jobOrder5);

        JobOrder jobOrder6 = new JobOrder();
        jobOrder6.setDescription("Engine oil change and air filter replacement");
        jobOrder6.setOrderDate(LocalDate.now().plusDays(1));
        jobOrder6.setTimeFrom(LocalTime.of(10, 0));
        jobOrder6.setTimeTo(LocalTime.of(12, 30));
        jobOrder6.setIsDeleted(false);
        jobOrder6.setWorkplace(workplace2);
        jobOrder6.setJobOrderAppUserEmployee(damjan);
        jobOrder6.setCar(car4);
        jobOrder6.setJobOrderStatus(finished);

        Set<Repair> repairs6 = new HashSet<>();
        repairs6.add(repair1); // Engine oil change
        repairs6.add(repair6); // Air filter replacement
        jobOrder6.setRepairs(repairs6);

        JobOrderPart jobOrderPart6 = new JobOrderPart();
        jobOrderPart6.setPart(part2); // Oil 5W30
        jobOrderPart6.setQuantity(4);
        jobOrder6.addJobOrderPart(jobOrderPart6);

        jobOrderRepository.save(jobOrder6);

        JobOrder jobOrder7 = new JobOrder();
        jobOrder7.setDescription("Timing belt replacement");
        jobOrder7.setOrderDate(LocalDate.now().plusDays(3));
        jobOrder7.setTimeFrom(LocalTime.of(9, 0));
        jobOrder7.setTimeTo(LocalTime.of(13, 0));
        jobOrder7.setIsDeleted(false);
        jobOrder7.setWorkplace(workplace1);
        jobOrder7.setJobOrderAppUserEmployee(bruno);
        jobOrder7.setCar(car3);
        jobOrder7.setJobOrderStatus(created);

        Set<Repair> repairs7 = new HashSet<>();
        repairs7.add(repair11); // Belt replacement
        jobOrder7.setRepairs(repairs7);

        JobOrderPart jobOrderPart7 = new JobOrderPart();
        jobOrderPart7.setPart(part4); // Timing belt
        jobOrderPart7.setQuantity(1);
        jobOrder7.addJobOrderPart(jobOrderPart7);

        jobOrderRepository.save(jobOrder7);

        JobOrder jobOrder8 = new JobOrder();
        jobOrder8.setDescription("Headlight adjustment and air conditioning service");
        jobOrder8.setOrderDate(LocalDate.now().plusDays(2));
        jobOrder8.setTimeFrom(LocalTime.of(14, 0));
        jobOrder8.setTimeTo(LocalTime.of(17, 0));
        jobOrder8.setIsDeleted(false);
        jobOrder8.setWorkplace(workplace2);
        jobOrder8.setJobOrderAppUserEmployee(damjan);
        jobOrder8.setCar(car6);
        jobOrder8.setJobOrderStatus(finished);

        Set<Repair> repairs8 = new HashSet<>();
        repairs8.add(repair7); // Headlight adjustment
        repairs8.add(repair5); // Air conditioning service
        jobOrder8.setRepairs(repairs8);

        JobOrderPart jobOrderPart8 = new JobOrderPart();
        jobOrderPart8.setPart(part8); // Windshield wiper
        jobOrderPart8.setQuantity(2);
        jobOrder8.addJobOrderPart(jobOrderPart8);

        jobOrderRepository.save(jobOrder8);

        JobOrder jobOrder9 = new JobOrder();
        jobOrder9.setDescription("Full vehicle inspection and preventive maintenance");
        jobOrder9.setOrderDate(LocalDate.now().plusDays(5));
        jobOrder9.setTimeFrom(LocalTime.of(8, 30));
        jobOrder9.setTimeTo(LocalTime.of(15, 30));
        jobOrder9.setIsDeleted(false);
        jobOrder9.setWorkplace(workplace1);
        jobOrder9.setJobOrderAppUserEmployee(damjan);
        jobOrder9.setCar(car2);
        jobOrder9.setJobOrderStatus(inProgress);

        Set<Repair> repairs9 = new HashSet<>();
        repairs9.add(repair00); // Vehicle inspection
        repairs9.add(repair14); // Preventive vehicle inspection
        jobOrder9.setRepairs(repairs9);

        jobOrderRepository.save(jobOrder9);

        JobOrder jobOrder10 = new JobOrder();
        jobOrder10.setDescription("Tire replacement and transmission service");
        jobOrder10.setOrderDate(LocalDate.now().plusDays(1));
        jobOrder10.setTimeFrom(LocalTime.of(7, 0));
        jobOrder10.setTimeTo(LocalTime.of(12, 0));
        jobOrder10.setIsDeleted(false);
        jobOrder10.setWorkplace(workplace2);
        jobOrder10.setJobOrderAppUserEmployee(bruno);
        jobOrder10.setCar(car5);
        jobOrder10.setJobOrderStatus(created);

    //------------------------------------------------------------------------------------------------------------------
    //---------- NACINI PLACANJA ----------
        Payment card = new Payment();
        card.setName("CARD");
        card.setDiscount(BigDecimal.valueOf(0));
        paymentRepository.save(card);

        Payment cash = new Payment();
        cash.setName("CASH");
        cash.setDiscount(BigDecimal.valueOf(0.05));
        paymentRepository.save(cash);
    //------------------------------------------------------------------------------------------------------------------
    //---------- RACUNI ----------
        Receipt receipt = new Receipt();
        receipt.setCreatedAt(LocalDateTime.now());
        receipt.setLoyaltyDiscount(BigDecimal.valueOf(0.05));
        receipt.setAdditionalDiscount(BigDecimal.valueOf(0.03));
        receipt.setRepairCostSum(BigDecimal.valueOf(400));
        receipt.setPartsCostSum(BigDecimal.valueOf(70));
        receipt.setTotalCost(BigDecimal.valueOf(408.90));
        receipt.setIsDeleted(false);
        receipt.setPayment(cash);
        Set<JobOrder> jobOrderSet = new HashSet<>();
        jobOrderSet.add(jobOrder1);
        receipt.setJobOrders(jobOrderSet);
        receipt.setReceiptAppUserEmployee(damjan);
        receiptRepository.save(receipt);

        Receipt receipt2 = new Receipt();
        receipt2.setCreatedAt(LocalDateTime.now());
        receipt2.setLoyaltyDiscount(BigDecimal.valueOf(0.02));
        receipt2.setAdditionalDiscount(BigDecimal.valueOf(0.01));
        receipt2.setRepairCostSum(BigDecimal.valueOf(500));
        receipt2.setPartsCostSum(BigDecimal.valueOf(90));
        receipt2.setTotalCost(BigDecimal.valueOf(585.50));
        receipt2.setIsDeleted(false);
        receipt2.setPayment(cash);
        Set<JobOrder> jobOrderSet2 = new HashSet<>();
        jobOrderSet2.add(jobOrder3);
        receipt2.setJobOrders(jobOrderSet2);
        receipt2.setReceiptAppUserEmployee(damjan);
        receiptRepository.save(receipt2);

        Receipt receipt3 = new Receipt();
        receipt3.setCreatedAt(LocalDateTime.now());
        receipt3.setLoyaltyDiscount(BigDecimal.valueOf(0.03));
        receipt3.setAdditionalDiscount(BigDecimal.valueOf(0.02));
        receipt3.setRepairCostSum(BigDecimal.valueOf(350));
        receipt3.setPartsCostSum(BigDecimal.valueOf(50));
        receipt3.setTotalCost(BigDecimal.valueOf(391.50));
        receipt3.setIsDeleted(false);
        receipt3.setPayment(card);
        Set<JobOrder> jobOrderSet3 = new HashSet<>();
        jobOrderSet3.add(jobOrder4);
        receipt3.setJobOrders(jobOrderSet3);
        receipt3.setReceiptAppUserEmployee(bruno);
        receiptRepository.save(receipt3);
    }

}

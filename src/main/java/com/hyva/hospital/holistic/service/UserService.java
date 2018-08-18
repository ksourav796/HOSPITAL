package com.hyva.hospital.holistic.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyva.hospital.holistic.entities.*;
import com.hyva.hospital.holistic.entities.Currency;
import com.hyva.hospital.holistic.mapper.*;
import com.hyva.hospital.holistic.pojo.*;
import com.hyva.hospital.holistic.respositories.*;
import com.hyva.hospital.holistic.util.ObjectMapperUtils;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.sql.Date;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    GeneralSettingsRepository generalSettingsRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AdminRepository bsAdminRepository;
    @Autowired
    ProvidersRepository providersRepository;
    @Autowired
    WorkingPlanRepository workingPlanRepository;
    @Autowired
    ServicesListRepository servicesListRepository;
    @Autowired
    BreaksRepository breaksRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PosCurrencyRepository currencyRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    PosStateRepository posStateRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    PosFormSetupRepository posFormSetupRepository;
    @Autowired
    MailRepository mailRepository;
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    PosEmployeeRepository posEmployeeRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MedplusRepository medplusRepository;
    @Autowired
    ConsulatationRepository consulatationRepository;

    @Autowired
    SchedulerRepository schedulerRepository;
 @Autowired
 UOMRepository uomRepository;
    int paginatedConstants=5;


    public Users userValidate(UserPojo userPojo) {
        Users user = userRepository.findByUsernameAndPassword(
                userPojo.getUsername(), userPojo.getPassword());
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public Users saveUserDetails(UserPojo userPojo) {
        Users user = null;
        user = userRepository.findByEmail(userPojo.getEmail());
        if (user != null) {
            user = null;
        } else {
            user = UserMapper.mapPojoToEntity(userPojo);
            userRepository.save(user);
        }
        return user;
    }

    public ServiceCategories SaveCategories(CategoriesPojo details) throws JSONException {
        ServiceCategories serviceCategories = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getId() != null) {
            serviceCategories = categoriesRepository.findByNameAndId(details.getName(),details.getId());
//            if (serviceCategories != null) {
            serviceCategories = CategoriesMapper.mapPojoToEntity(details);
                categoriesRepository.save(serviceCategories);
                return serviceCategories;
//            } else {
//                return null;
//            }
        } else {
            serviceCategories = categoriesRepository.findByName(details.getName());
            if (serviceCategories == null) {
                serviceCategories = CategoriesMapper.mapPojoToEntity(details);
                categoriesRepository.save(serviceCategories);
                return serviceCategories;
            } else {
                return null;
            }
        }
    }

    public String getFormSetUpNo() throws JSONException {
        FormSetUp formSetUp =posFormSetupRepository.findOne(1L);
        int incValue = Integer.parseInt(formSetUp.getNextref());
        String formsetupNo =getNextRefInvoice(formSetUp.getTypeprefix(),String.format("%05d", ++incValue));
        return formsetupNo;
    }

    public Consultation SaveConsultation(ConsultationPojo details) throws JSONException {
        Consultation consultation = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getId() != null) {
            consultation = consulatationRepository.findByPatientNameAndId(details.getPatientName(),details.getId());
//            if (ea_service_categories != null) {
            consultation = ConsultationMapper.mapPojoToEntity(details);
            consulatationRepository.save(consultation);
                return consultation;
//            } else {
//                return null;
//            }
        } else {
            consultation = consulatationRepository.findByPatientName(details.getPatientName());
            if (consultation == null) {
                consultation = ConsultationMapper.mapPojoToEntity(details);
                consulatationRepository.save(consultation);
                return consultation;
            } else {
                return null;
            }
        }
    }




    public Medplus SaveMedplus(MedPlusPojo details) throws JSONException {
        Medplus medplus = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getId() != null) {
            medplus = medplusRepository.findByMedicineAndId(details.getMedicine(),details.getId());
//            if (ea_service_categories != null) {
            medplus = MedplusMapper.mapPojoToEntity(details);
            medplusRepository.save(medplus);
                return medplus;
//            } else {
//                return null;
//            }
        } else {
            medplus = medplusRepository.findByMedicine(details.getMedicine());
            if (medplus == null) {
                medplus = MedplusMapper.mapPojoToEntity(details);
                medplusRepository.save(medplus);
                return medplus;
            } else {
                return null;
            }
        }
    }


    public Notes SaveNotes(NotesPojo details) throws JSONException {
        Notes notes = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getId() != null) {
            notes = notesRepository.findByNotesAndId(details.getNotes(),details.getId());
//            if (ea_service_categories != null) {
            notes = NotesMapper.mapPojoToEntity(details);
            notesRepository.save(notes);
                return notes;
//            } else {
//                return null;
//            }
        } else {
            notes = notesRepository.findByNotes(details.getNotes());
            if (notes == null) {
                notes = NotesMapper.mapPojoToEntity(details);
                notesRepository.save(notes);
                return notes;
            } else {
                return null;
            }
        }
    }

    public UnitOfMeasurement SaveNewUOM(UnitOfMeasurementPojo details) throws JSONException {
        UnitOfMeasurement unitOfMeasurement = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getUnitOfMeasurementId() != null) {
            unitOfMeasurement = uomRepository.findByUnitOfMeasurementIdAndUnitOfMeasurementName(details.getUnitOfMeasurementId(),details.getUnitOfMeasurementName());
//            if (ea_service_categories != null) {
            unitOfMeasurement = UOMMapper.mapPojoToEntity(details);
            uomRepository.save(unitOfMeasurement);
                return unitOfMeasurement;
//            } else {
//                return null;
//            }
        } else {
            unitOfMeasurement = uomRepository.findByUnitOfMeasurementName(details.getUnitOfMeasurementName());
            if (unitOfMeasurement == null) {
                unitOfMeasurement = UOMMapper.mapPojoToEntity(details);
                uomRepository.save(unitOfMeasurement);
                return unitOfMeasurement;
            } else {
                return null;
            }
        }
    }

    public Medicine SaveMedicine(MedicinePojo details) throws JSONException {
        Medicine medicine = null;
        //findByGradeNameAndGradeIdNotIn
        if (details.getId() != null) {
            medicine = medicineRepository.findByMedicineNameAndId(details.getMedicineName(),details.getId());
//            if (medicine != null) {
            medicine = MedicineMapper.mapPojoToEntity(details);
            medicineRepository.save(medicine);
                return medicine;
//            } else {
//                return null;
//            }
        } else {
            medicine = medicineRepository.findByMedicineName(details.getMedicineName());
            if (medicine == null) {
                medicine = MedicineMapper.mapPojoToEntity(details);
                medicineRepository.save(medicine);
                return medicine;
            } else {
                return null;
            }
        }
    }
//    public Prescription savePrescription(PrescriptionPojo details) throws JSONException {
//        Prescription prescription = null;
//        //findByGradeNameAndGradeIdNotIn
//        if (details.getMedicine()!= null) {
//            prescription = prescriptionRepository.findByMedicine(details.getMedicine());
////            if (medicine != null) {
//            prescription = PrescriptionMapper.mapPojoToEntity(details);
//            prescriptionRepository.save(prescription);
//                return prescription;
////            } else {
////                return null;
////            }
//        } else {
//            prescription = prescriptionRepository.findByMedicine(details.getMedicine());
//            if (prescription == null) {
//                prescription = PrescriptionMapper.mapPojoToEntity(details);
//                prescriptionRepository.save(prescription);
//                return prescription;
//            } else {
//                return null;
//            }
//        }
//    }

    public List<CurrencyDTO> getCurrencyList(String status) {
        List<com.hyva.hospital.holistic.entities.Currency> list = new ArrayList<>();
        if (StringUtils.isEmpty(status)) {
            list = currencyRepository.findAll();
        } else {
            list = currencyRepository.findAllByStatus(status);
        }

        List<CurrencyDTO> currency = UserMapper.mapCurrencyEntityToPojo(list);
        return currency;
    }

    public Currency saveCurrency(CurrencyDTO currencyDTO) {
        Currency currencies = new Currency();
        if(currencyDTO.getCurrencyId()!=null){
            currencies=currencyRepository.findByCurrencyNameAndCurrencyIdNotIn(currencyDTO.getCurrencyName(),currencyDTO.getCurrencyId());

        }else{
            currencies=currencyRepository.findByCurrencyName(currencyDTO.getCurrencyName());
        }
        if(currencies==null){
            com.hyva.hospital.holistic.entities.Currency currency = UserMapper.mapCurrencyPojoToEntity(currencyDTO);
            currencyRepository.save(currency);
            return currency;
        }else{
            return  null;
        }

    }

    public void deleteCurrency(String  currencyName) {
        currencyRepository.delete(currencyRepository.findByCurrencyName(currencyName));
    }

//    public void deleteScheduler(String firstName) {
//        schedulerRepository.delete(schedulerRepository.findByFirstName(firstName));
//    }

    public CurrencyDTO editCurrency(String currencyName) {
        Currency currency = currencyRepository.findByCurrencyName(currencyName);
        List<Currency> currencies = new ArrayList<>();
        currencies.add(currency);
        CurrencyDTO countryDTO = UserMapper.mapCurrencyEntityToPojo(currencies).get(0);
        return countryDTO;
    }

    public StateDTO editState(String stateName) {
        State state = posStateRepository.findByStateName(stateName);
        List<State> states = new ArrayList<>();
        states.add(state);
        StateDTO stateDTO = UserMapper.mapStateEntityToPojo(states).get(0);
        return stateDTO;
    }

    public CityDTO editCity(String cityName) {
        City city = cityRepository.findByCityName(cityName);
        List<City> cityList = new ArrayList<>();
        cityList.add(city);
        CityDTO cityDTO = UserMapper.mapCityEntityToPojo(cityList).get(0);
        return cityDTO;
    }

    public Customers getPatientByName(String name) {
        Customers customers =new Customers();
           customers = customerRepository.findByFirstName(name).get(0);
        return customers;
    }


    public List<CountryDTO> getCountryList(String status) {
        List<Country> list = new ArrayList<>();
        if (StringUtils.isEmpty(status)) {
            list = countryRepository.findAll();
        } else {
            list = countryRepository.findAllByStatus(status);
        }
        List<CountryDTO> country = UserMapper.mapCountryEntityToPojo(list);
        return country;
    }

    public List<StateDTO> getStatesList(String status){
        List<State> list=new ArrayList<>();
        if (StringUtils.isEmpty(status)) {

            list=posStateRepository.findAll();
        }else{
            list=posStateRepository.findAllByStatus(status,null);
        }
        List<StateDTO> state=UserMapper.mapStateEntityToPojo(list);
        return  state;
    }

    public List<CityDTO> getCitiesList(String status){
        List<City> list=new ArrayList<>();
        if(StringUtils.isEmpty(status)){
            list=cityRepository.findAll();
        }else{
            list=cityRepository.findAllByStatus(status,null);
        }
        List<CityDTO> city=UserMapper.mapCityEntityToPojo(list);
        return city;
    }

    public Country saveCountry(CountryDTO countryDTO) {
        Country countries = new Country();
        if(countryDTO.getCountryId()!=null){
            countries=countryRepository.findByCountryNameAndCountryIdNotIn(countryDTO.getCountryName(),countryDTO.getCountryId());
        }
        else{
            countries=countryRepository.findByCountryName(countryDTO.getCountryName());
        }
        if(countries==null){
            Country country = UserMapper.mapCountryPojoToEntity(countryDTO);
            countryRepository.save(country);
            return country;
        }else{
            return null;
        }

    }


    public Student saveStudent(Studentpojo studentpojo) {
        Student student = new Student();
        if(studentpojo.getStudentId()!=null){
            student=studentRepository.findByStudentNameAndStudentIdNotIn(studentpojo.getStudentName(),studentpojo.getStudentId());
        }
        else{
            student=studentRepository.findByStudentName(studentpojo.getStudentName());
        }
        if(student==null){
            Student student1 = UserMapper.mapStudentPojoToEntity(studentpojo);
            studentRepository.save(student1);
            return student1;
        }else{
            return null;
        }

    }

    public BasePojo getStateList(String status,BasePojo basePojo,String searchText) {
        List<State> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<State> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = posStateRepository.findAllByStateCodeContainingOrStateNameContainingAndStatus(searchText,searchText,status);
            }else {
                list1=posStateRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        if(StringUtils.isEmpty(status)){
            status="Active";
        }
        State state=new State();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            state=posStateRepository.findFirstByStateCodeContainingOrStateNameContainingAndStatus(searchText,searchText,status,sort);
            list = posStateRepository.findAllByStateCodeContainingOrStateNameContainingAndStatus(searchText,searchText,status,pageable);
        } else {
            state=posStateRepository.findFirstByStatus(status,sort);
            list = posStateRepository.findAllByStatus(status,pageable);
        }
        if(list.contains(state)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<StateDTO> stateList = UserMapper.mapStateEntityToPojo(list);
        basePojo=calculatePagination(basePojo,stateList.size());
        basePojo.setList(stateList);
        return basePojo;
    }

//    public BasePojo getAppointmentList(String status,BasePojo basePojo,String searchText) {
//        List<Appointments> list = new ArrayList<>();
//        basePojo.setPageSize(10);
//        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"Name"));
//        if(basePojo.isLastPage()==true){
//            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"Name"));
//        }
//        if(StringUtils.isEmpty(status)){
//            status="Active";
//        }
//        Appointments ea_appointments=new Appointments();
//        Pageable pageable=new PageRequest(basePojo.getPageNo(),5,sort);
//        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
//            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"Name"));
//        }
//        if (!StringUtils.isEmpty(searchText)) {
//            ea_appointments=appointmentRepository.findFirstByAppointmentCodeContainingOrIdContainingAndStatus(searchText,searchText,status,sort);
//            list = appointmentRepository.findFirstByAppointmentCodeContainingOrIdContainingAndStatus(searchText,searchText,status,pageable);
//        } else {
//            ea_appointments=appointmentRepository.findFirstByStatus(status,sort);
//            list = appointmentRepository.findAllByStatus(status,pageable);
//        }
//        if(list.contains(ea_appointments)){
//            basePojo.setStatus(true);
//        }else {
//            basePojo.setStatus(false);
//        }
//        List<AppointmentPojo> appointmentPojoList = AppointmentsMapper.mapPojoToAppointEntity(list);
//        basePojo=calculatePagination(basePojo,stateList.size());
//        basePojo.setList(stateList);
//        return basePojo;
//    }

    public BasePojo getCityList(String status,BasePojo basePojo,String searchText) {
        List<City> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<City> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = cityRepository.findAllByCityCodeContainingOrCityNameContainingAndStatus(searchText,searchText,status);
            }else {
                list1=cityRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        if(StringUtils.isEmpty(status)){
            status="Active";
        }
        City city=new City();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            city=cityRepository.findFirstByCityCodeContainingOrStateNameContainingAndStatus(searchText,searchText,status,sort);
            list = cityRepository.findAllByCityCodeContainingOrCityNameContainingAndStatus(searchText,searchText,status,pageable);
        } else {
            city=cityRepository.findFirstByStatus(status,sort);
            list = cityRepository.findAllByStatus(status,pageable);
        }
        if(list.contains(city)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<CityDTO> cityList = UserMapper.mapCityEntityToPojo(list);
        basePojo=calculatePagination(basePojo,cityList.size());
        basePojo.setList(cityList);
        return basePojo;
    }

    public BasePojo calculatePagination(BasePojo basePojo,int size){
        if(basePojo.isLastPage()==true){
            basePojo.setFirstPage(false);
            basePojo.setNextPage(true);
            basePojo.setPrevPage(false);
        }else if(basePojo.isFirstPage()==true){
            basePojo.setLastPage(false);
            basePojo.setNextPage(false);
            basePojo.setPrevPage(true);
            if(basePojo.isStatus()==true){
                basePojo.setLastPage(true);
                basePojo.setNextPage(true);
            }
        }else if(basePojo.isNextPage()==true){
            basePojo.setLastPage(false);
            basePojo.setFirstPage(false);
            basePojo.setPrevPage(false);
            basePojo.setNextPage(false);
            if(basePojo.isStatus()==true){
                basePojo.setLastPage(true);
                basePojo.setNextPage(true);
            }
        }else if(basePojo.isPrevPage()==true){
            basePojo.setLastPage(false);
            basePojo.setFirstPage(false);
            basePojo.setNextPage(false);
            basePojo.setPrevPage(false);
            if(basePojo.isStatus()==true){
                basePojo.setPrevPage(true);
                basePojo.setFirstPage(true);
            }
        }
        if(size==0){
            basePojo.setLastPage(true);
            basePojo.setFirstPage(true);
            basePojo.setNextPage(true);
            basePojo.setPrevPage(true);
        }
        return basePojo;
    }

    public void deleteCountry(String  countryName) {
        countryRepository.delete(countryRepository.findByCountryName(countryName));
    }

    public CountryDTO editCountry(String countryName) {
        Country country = countryRepository.findByCountryName(countryName);
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        CountryDTO countryDTO = UserMapper.mapCountryEntityToPojo(countries).get(0);
        return countryDTO;
    }

    public State saveState(StateDTO stateDTO) {
        State state1=new State();
        if(stateDTO.getId()!=null){
            state1=posStateRepository.findByStateNameAndIdNotIn(stateDTO.getStateName(),stateDTO.getId());
        }else {
            state1=posStateRepository.findByStateName(stateDTO.getStateName());
        }
        if(state1==null) {
            State state = UserMapper.mapStatePojoToEntity(stateDTO);
            Country country = countryRepository.findByCountryName(stateDTO.getCountry());
            if(country!=null)
            state.setCountryName(country.getCountryName());
            posStateRepository.save(state);
            return state;
        }else {
            return null;
        }
    }

    public City saveCity(CityDTO cityDTO) {
        City city1=new City();
        if(cityDTO.getId()!=null){
            city1=cityRepository.findByCityNameAndCountryNameAndStateNameAndIdNotIn(cityDTO.getCityName(),cityDTO.getCountryName(),cityDTO.getState(),cityDTO.getId());
        }else {
            city1=cityRepository.findByCityNameAndCountryNameAndStateName(cityDTO.getCityName(),cityDTO.getCountryName(),cityDTO.getState());
        }
        if(city1==null) {
            City city = UserMapper.mapCityPojoToEntity(cityDTO);
            State state = posStateRepository.findByStateName(cityDTO.getState());
//            city.setStateName(state.getStateName());
            cityRepository.save(city);
            return city;
        }else {
            return null;
        }
    }

    public City saveplusCity(CityDTO cityDTO) {
        City city1=new City();
        if(cityDTO.getId()!=null){
            city1=cityRepository.findByCityNameAndCountryNameAndStateNameAndIdNotIn(cityDTO.getCityName(),cityDTO.getCountryName(),cityDTO.getState(),cityDTO.getId());
        }else {
            city1=cityRepository.findByCityNameAndCountryNameAndStateName(cityDTO.getCityName(),cityDTO.getCountryName(),cityDTO.getState());
        }
        if(city1==null) {
            City city = UserMapper.mapCityPojoToEntity(cityDTO);
            State state = posStateRepository.findByStateName(cityDTO.getState());
//            city.setStateName(state.getStateName());
            cityRepository.save(city);
            return city;
        }else {
            return null;
        }
    }

    public void deleteState(String stateName) {
        posStateRepository.delete(posStateRepository.findById(Long.valueOf(stateName)));
    }

//    public void deleteCity(String cityName) {
//        cityRepository.delete(cityRepository.findByCityName(cityName));
//    }


    public Patient savePatient(patientPojo details) throws JSONException {
        Patient patient = null;
        if (details.getId() != null) {
            patient = patientRepository.findById(details.getId());
            if (patient != null) {
//                Customers customers = customerRepository.findByFirstName(details.getC)
                patient = PatientMapper.mapPojoToEntity(details);
                patientRepository.save(patient);
                return patient;
            } else {
                return null;
            }
        } else {
            if (patient == null) {
                patient = PatientMapper.mapPojoToEntity(details);
                patientRepository.save(patient);
                return patient;
            } else {
                return null;
            }
        }
    }

    public Invoice saveInvoice(String invoicePojo,Long appointmentId ) throws JSONException {
        Invoice invoice =invoiceRepository.findByAppointmentId(appointmentId);
        if(invoice==null){
            invoice = new Invoice();
        }
        invoice.setName(invoicePojo);
        invoice.setAppointmentId(appointmentId);
                invoiceRepository.save(invoice);
                return invoice;

    }

    public Prescription savePrescription(Long appointmentId, String prescriptionPojo,String patientName ) throws JSONException {
        Prescription prescription = prescriptionRepository.findById(appointmentId);
        if(prescription==null){
            prescription=new Prescription();
        }
        prescription.setMedicine(prescriptionPojo);
        prescription.setPatient(patientName);
        prescription.setAppointmentId(appointmentId);
        prescriptionRepository.save(prescription);
        return prescription;

    }

    public List<StateDTO> getCountryState(String countryName) {
        List<State> states = posStateRepository.findAllByCountryNameAndStatus(countryName,"Active");
        List<StateDTO> stateList = UserMapper.mapStateEntityToPojo(states);
        return stateList;
    }

    public List<MedicinePojo> getMedicineUom(String medicineName) {
        List<Medicine> medicines = medicineRepository.findAllByMedicineName(medicineName);
        List<MedicinePojo> medicinePojos = MedicineMapper.mapMedicineEntityToPojo(medicines);
        return medicinePojos;
    }




//    public List<InvoicePojo> getInvoice(Long id){
//        List<Invoice> invoices=invoiceRepository.findByAppointmentId(id);
//        List<InvoicePojo> list=InvoiceMapper.mapEntityToPojo(invoices);
//        for(InvoicePojo invoicePojo:list){
//            Gson json = new Gson();
//            Type services = new TypeToken<ArrayList<invoiceListPojo>>() {
//            }.getType();
//            invoicePojo.setInvoiceList(json.fromJson(invoicePojo.getName(),services));
//        }
//        return list;
//
//    }
    public List<CityDTO> getStateCity(String stateName) {
        List<City> cities = cityRepository.findAllByStateName(stateName);
        List<CityDTO> cityList = UserMapper.mapCityEntityToPojo(cities);

        return cityList;
    }
    public List<InvoiceListPojo> getInvoiceDetails(Long appointmentId) {
        List<InvoiceListPojo> invoiceList = new ArrayList<>();
        InvoicePojo invoicePojo = new InvoicePojo();
        Invoice invoice =invoiceRepository.findByAppointmentId(appointmentId);
        if(invoice!=null) {
            Gson json = new Gson();
            Type servicePrice = new TypeToken<ArrayList<InvoiceListPojo>>() {
            }.getType();

            invoiceList = json.fromJson(invoice.getName(), servicePrice);
//            for (InvoiceListPojo inv : invoiceList) {
//                invoicePojo.setPrice(inv.getServicePrice() + invoicePojo.getPrice());
//            }
        }
        return  invoiceList;
    }
//    public PrescriptionPojo getPrescriptionDetails(Long appointmentId) {
//        PrescriptionPojo prescriptionPojo = new PrescriptionPojo();
//        Prescription prescription =prescriptionRepository.findByAppointmentId(appointmentId);
//        if(prescription!=null) {
//            Gson json = new Gson();
//            Type servicePrice = new TypeToken<ArrayList<InvoiceListPojo>>() {
//            }.getType();
//            List<MedicineListPojo> presList = new ArrayList<>();
//            presList = json.fromJson(prescription.getMedicine(), servicePrice);
////            for (InvoiceListPojo inv : invoiceList) {
////                invoicePojo.setPrice(inv.getServicePrice() + invoicePojo.getPrice());
////            }
//        }
//        return  prescriptionPojo;
//    }


    public FormSetUp saveFormSetup(FormsetupDTO formsetupDTO) {
        FormSetUp formSetUps =new FormSetUp();
        if(formsetupDTO.getFormsetupId()!=null){
            formSetUps=posFormSetupRepository.findAllByTypenameAndFormsetupIdNotIn(formsetupDTO.getTypename(),formsetupDTO.getFormsetupId());

        }else{
            formSetUps=posFormSetupRepository.findAllByTypename(formsetupDTO.getTypename());
        }
        if(formSetUps==null){
            FormSetUp formSetUp = UserMapper.mapFormSetupPojoToEntity(formsetupDTO);
            posFormSetupRepository.save(formSetUp);
            return formSetUp;
        }else{
            return null;
        }

    }

    public FormsetupDTO editFormsetupMethod(String formsetupName) {
        FormSetUp formSetUp = posFormSetupRepository.findAllByTypename(formsetupName);
        List<FormSetUp> formSetUpList = new ArrayList<>();
        formSetUpList.add(formSetUp);
        FormsetupDTO formsetupDTO = UserMapper.mapFormSetupEntityToPojo(formSetUpList).get(0);
        return formsetupDTO;
    }

    public static String getNextRefInvoice(String prefix, String nextRef) {
        StringBuilder sb = new StringBuilder();
        return sb.append(prefix).append(nextRef).toString();
    }
    public FormsetupDTO getFormSetup(String type) {
        FormSetUp formSetUp = posFormSetupRepository.findAllByTypename(type);
        if (formSetUp != null) {
            List<FormSetUp> formSetUpList = new ArrayList<>();
            formSetUpList.add(formSetUp);
            FormsetupDTO formsetupDTO = UserMapper.mapFormSetupEntityToPojo(formSetUpList).get(0);
            int incValue = Integer.parseInt(formSetUp.getNextref());
            formsetupDTO.setFormNo(getNextRefInvoice(formSetUp.getTypeprefix(), String.format("%05d", incValue)));
            return formsetupDTO;
        }
        return null;
    }

    public List<FormsetupDTO> getFormSetupList() {
        List<FormSetUp> list = new ArrayList<>();
        list = posFormSetupRepository.findAll();
        List<FormsetupDTO> formsetupDTOS = UserMapper.mapFormSetupEntityToPojo(list);
        return formsetupDTOS;
    }
    public BasePojo getPaginatedFormSetUpList(BasePojo basePojo,String searchText) {
        List<FormSetUp> list = new ArrayList<>();
        basePojo.setPageSize(5);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"typename"));
        if(basePojo.isLastPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"typename"));
        }
        FormSetUp formSetUp=new FormSetUp();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),5,sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"typename"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            formSetUp=posFormSetupRepository.findFirstByTypenameContaining(searchText,sort);
            list = posFormSetupRepository.findAllByTypenameContaining(searchText,pageable);
        } else {
            formSetUp=posFormSetupRepository.findFirstBy(sort);
            list = posFormSetupRepository.findAllBy(pageable);
        }
        if(list.contains(formSetUp)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<FormsetupDTO> formsetupDTOList = UserMapper.mapFormSetupEntityToPojo(list);
        basePojo=calculatePagination(basePojo,formsetupDTOList.size());
        basePojo.setList(formsetupDTOList);
        return basePojo;
    }

//    public Users SaveCustomers(UserPojo details) throws JSONException {
//        Users customer = null;
//        //findByGradeNameAndGradeIdNotIn
//        if (details.getId() != null) {
//            customer = userRepository.findById( details.getId());
//            if (customer != null) {
//                customer = UserMapper.mapPojoToEntity(details);
//                userRepository.save(customer);
//                return customer;
//            } else {
//                return null;
//            }
//        } else {
////            customer = userRepository.findByFirstName(details.getFirstName());
//            if (customer == null) {
//                customer = UserMapper.mapPojoToEntity(details);
//                userRepository.save(customer);
//                return customer;
//            } else {
//                return null;
//            }
//        }
//    }

    public Customers SaveCustomers(CustomersPojo customersPojo){
        List<Customers> list=new ArrayList<>();
        if(customersPojo.getId()!=0){
            list=customerRepository.findByFirstNameAndIdNotIn(customersPojo.getFirstName(),customersPojo.getId());
        }
        else {
            list=customerRepository.findByFirstName(customersPojo.getFirstName());
        }
        if(list.size()==0){
            Customers customers = null;
            customers = CustomerMapper.mapPojoToEntity(customersPojo);
            customerRepository.save(customers);
            return customers;
        }
        else{
            return null;
        }
    }



    public Users SaveCurrentUser(UserPojo details) throws JSONException {
        Users currentUser = null;

        List<Users> list = (List<Users>) userRepository.findAll();
        if (list.size() > 0) {
            details.setId(list.get(0).getId());
        }
        currentUser = UserMapper.mapPojoToEntity(details);
        userRepository.save(currentUser);
        return currentUser;

    }

        public Settings SaveGeneralSettings(GeneralSettingsPojo details) throws JSONException {
        Settings general = null;

            List<Settings> list = (List<Settings>) generalSettingsRepository.findAll();
            if (list.size() > 0) {
                details.setId(list.get(0).getId());
            }

            general = GeneralSettingsMapper.mapPojoToEntity(details);
            generalSettingsRepository.save(general);
            return general;

    }
//    public WorkingPlan saveNewWorkingPlan(WorkingPlanPojo details) {
//        WorkingPlan workingPlan = new WorkingPlan();
//        if(details.getId()!=null){
//            workingPlan=workingPlanRepository.findByDayAndIdNotIn(details.getDay(),details.getId());
//        }else {
//            workingPlan=workingPlanRepository.findByDay(details.getDay());
//        }
//        if(workingPlan==null) {
//            WorkingPlan working_plan = WorkingPlanMapper.mapPojoToEntity(details);
//            workingPlanRepository.save(working_plan);
//            return working_plan;
//        }
//        else{
//            return null;
//        }
//
//    }
    public WorkingPlan saveNewWorkingPlan(WorkingPlanPojo workingPlanPojo){
        List<WorkingPlan> list=new ArrayList<>();
        if(workingPlanPojo.getId()!=null){
            list=workingPlanRepository.findByDayAndIdNotIn(workingPlanPojo.getDay(),workingPlanPojo.getId());
        }
        else {
            list=workingPlanRepository.findByDay(workingPlanPojo.getDay());
        }
        if(list.size()==0){
            WorkingPlan workingPlan = null;
            workingPlan = WorkingPlanMapper.mapPojoToEntity(workingPlanPojo);
            workingPlanRepository.save(workingPlan);
            return workingPlan;
        }
        else{
            return null;
        }
    }
//    public Breaks saveNewBreaks(BreaksPojo details) throws JSONException {
//        Breaks Breaks = null;
//        WorkingPlan workingPlan = workingPlanRepository.findByDay(details.getDay());
//        Users users = userRepository.findById(Long.valueOf("1"));
//        details.setWorkingPlan(workingPlan);
//        details.setEa_users(users);
//        //findByGradeNameAndGradeIdNotIn
//        if (details.getId() != null) {
//            Breaks = breaksRepository.findById( details.getId());
//            if (Breaks != null) {
//                Breaks = BreaksMapper.mapPojoToEntity(details);
//                breaksRepository.save(Breaks);
//                return Breaks;
//            } else {
//                return null;
//            }
//        } else {
////            customer = customerRepository.findByFirstName(details.getFirstName());
//            if (Breaks == null) {
//                Breaks = BreaksMapper.mapPojoToEntity(details);
//                breaksRepository.save(Breaks);
//                return Breaks;
//            } else {
//                return null;
//            }
//        }
//    }

    public Breaks saveNewBreaks(BreaksPojo breaksPojo){
        List<Breaks> list=new ArrayList<>();
        if(breaksPojo.getId()!=null){
            list=breaksRepository.findByDayAndStartTimeAndEndTimeAndIdNotIn(breaksPojo.getDay(),breaksPojo.getStartTime(),breaksPojo.getEndTime(),breaksPojo.getId());
        }
        else {
            list=breaksRepository.findByDayAndStartTimeAndEndTime(breaksPojo.getDay(),breaksPojo.getStartTime(),breaksPojo.getEndTime());
        }
        if(list.size()==0){
            Breaks breaks = null;
            breaks = BreaksMapper.mapPojoToEntity(breaksPojo);
            breaksRepository.save(breaks);
            return breaks;
        }
        else{
            return null;
        }
    }



    public Users SaveAdmins(AdminsPojo details) throws JSONException {
        Users admin = null;

        if (details.getId() != null) {
            admin = bsAdminRepository.findById( details.getId());
            if (admin != null) {
                admin = AdminMapper.mapPojoToEntity(details);
                bsAdminRepository.save(admin);
                return admin;
            } else {
                return null;
            }
        } else {
//            customer = userRepository.findByFirstName(details.getFirstName());
            if (admin == null) {
                admin = AdminMapper.mapPojoToEntity(details);
                Users users =bsAdminRepository.save(admin);
                UserSettings userSettings = new UserSettings();
                userSettings.setUsername(details.getUserName());
                userSettings.setPassword(details.getPassword());
                ServicesProviders servicesProviders =new ServicesProviders();
                servicesProviders.setIdUsers(users);
//                if(details.getUserName()=="admin"){
//                    UserSettings ea_user_settings1 = holistic
//                    details.setId_roles('1');
//                }
                return admin;
            } else {
                return null;
            }
        }
    }

 public Appointments SaveAppointment(AppointmentPojo details) throws JSONException {
    if(details.getId()==null||details.getId()==0) {
        Customers customers = new Customers();
        customers.setFirstName(details.getFirstName());
        customers.setLastName(details.getLastName());
        customers.setEmail(details.getEmail());
        customers.setAddress(details.getAddress());
        customers.setStateName( details.getStateName() );
        customers.setCountryName( details.getCountryName() );
        customers.setCity(details.getCity());
        customers.setMobileNumber(details.getMobileNumber());
        customers.setGender(details.getGender());
        customers.setAge(details.getAge());
        customers.setStarttime(details.getStarttime());
        customers.setMaritalStatus(details.getStatus());
        customers.setOccupation(details.getOccupation());
        customers.setDate(details.getBook_datetime());
        customers.setUhid(details.getUhid());
        customers.setNotes(details.getNotes());
        customers.setHistory(details.getHistory());
        customers.setCheifComplaints(details.getCheifComplaints());
        customers.setZipCode(details.getZipCode());
        customers.setPhoneNumber(details.getPhoneNumber());
        customerRepository.save(customers);

        List<Customers> usersobj = customerRepository.findByOrderByIdDesc();
        details.setCustomers(usersobj.get(0));
        Appointments appointments = null;
        if (details.getId() != null) {
            appointments = appointmentRepository.findById(details.getId());
            if (appointments != null) {
                details.setCustomers(appointments.getCustomers());
                appointments = AppointmentsMapper.mapPojoToAppointEntity(details);
                appointments.getCustomers().setNotes(details.getNotes());
                customerRepository.save(appointments.getCustomers());
                appointmentRepository.save(appointments);
                return appointments;
            } else {
                return null;
            }
        } else {
//            customer = userRepository.findByFirstName(details.getFirstName());
            if (appointments == null) {
                Appointments appointments1 = new Appointments();
                FormSetUp formSetUp = posFormSetupRepository.findAllByTypename("AppointmentNumber");
                FormSetUp invoicenumber = posFormSetupRepository.findAllByTypename("InvoiceNumber");
                appointments1.setAppointmentNo(getNextRefInvoice(formSetUp.getTypeprefix(), formSetUp.getNextref()));
                appointments1.setInvoiceNo(getNextRefInvoice(invoicenumber.getTypeprefix(), invoicenumber.getNextref()));
                int incValue = Integer.parseInt(formSetUp.getNextref());
                int incValue1 = Integer.parseInt(invoicenumber.getNextref());
                details.setAppointmentNo(getNextRefInvoice(formSetUp.getTypeprefix(), String.format("%05d", ++incValue)));
                details.setInvoiceNo(getNextRefInvoice(invoicenumber.getTypeprefix(), String.format("%05d", ++incValue1)));
                formSetUp.setNextref(String.format("%05d", incValue));
                invoicenumber.setNextref(String.format("%05d", incValue1));
                posFormSetupRepository.save(formSetUp);
                posFormSetupRepository.save(invoicenumber);
                appointments = AppointmentsMapper.mapPojoToAppointEntity(details);
                appointmentRepository.save(appointments);
                return appointments;
            } else {
                return null;
            }
        }
    }else {
        Appointments appointments=appointmentRepository.findById(details.getId());
        appointments.setNotes(details.getNotes());
        appointmentRepository.save(appointments);
        return null;
    }
    }

    public Appointments saveOldAppointments(AppointmentPojo appointmentPojo){
        Appointments appointments = null;
        Appointments appointments1 = new Appointments();
        Customers customers=getPatientByName( appointmentPojo.getFirstName());
        appointments1.setCustomers( customers );
        FormSetUp formSetUp = posFormSetupRepository.findAllByTypename("AppointmentNumber");
        FormSetUp invoicenumber = posFormSetupRepository.findAllByTypename("InvoiceNumber");
        appointments1.setAppointmentNo(getNextRefInvoice(formSetUp.getTypeprefix(), formSetUp.getNextref()));
        appointments1.setInvoiceNo(getNextRefInvoice(invoicenumber.getTypeprefix(), invoicenumber.getNextref()));
        int incValue = Integer.parseInt(formSetUp.getNextref());
        int incValue1 = Integer.parseInt(invoicenumber.getNextref());
        appointmentPojo.setAppointmentNo(getNextRefInvoice(formSetUp.getTypeprefix(), String.format("%05d", ++incValue)));
        appointmentPojo.setInvoiceNo(getNextRefInvoice(invoicenumber.getTypeprefix(), String.format("%05d", ++incValue1)));
        formSetUp.setNextref(String.format("%05d", incValue));
        invoicenumber.setNextref(String.format("%05d", incValue1));
        posFormSetupRepository.save(formSetUp);
        posFormSetupRepository.save(invoicenumber);
        appointments = AppointmentsMapper.mapPojoToAppointEntity(appointmentPojo);
        appointments.setCustomers(customers);
        appointmentRepository.save(appointments);
        return appointments;
    }

    public List<AppointmentPojo> appointmentList() {
        List<Appointments> appointments = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        appointments = (List<Appointments>) appointmentRepository.findAll();

        List<AppointmentPojo> gdPojoList = AppointmentsMapper.mapEntiyToPojo(appointments);
        return gdPojoList;
    }

    public List<CategoriesPojo> categoriesList() {
        List<ServiceCategories> serviceCategories = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        serviceCategories = (List<ServiceCategories>) categoriesRepository.findAll();

        List<CategoriesPojo> gdPojoList = ObjectMapperUtils.mapAll(serviceCategories, CategoriesPojo.class);
        return gdPojoList;
    }

    public List<UnitOfMeasurementPojo> UOMList() {
        List<UnitOfMeasurement> unitOfMeasurements = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        unitOfMeasurements = (List<UnitOfMeasurement>) uomRepository.findAll();

        List<UnitOfMeasurementPojo> gdPojoList = ObjectMapperUtils.mapAll(unitOfMeasurements, UnitOfMeasurementPojo.class);
        return gdPojoList;
    }

    public List<MedicinePojo> medicineList() {
        List<Medicine> medicine = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        medicine = (List<Medicine>) medicineRepository.findAll();

        List<MedicinePojo> gdPojoList = ObjectMapperUtils.mapAll(medicine, MedicinePojo.class);
        return gdPojoList;
    }
    public List<BreaksPojo> breaksList() {
        List<Breaks> breaks = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        breaks = (List<Breaks>) breaksRepository.findAll();

        List<BreaksPojo> gdPojoList = ObjectMapperUtils.mapAll(breaks, BreaksPojo.class);
        return gdPojoList;
    }
//    public List<AppointmentPojo> appointmentList() {
//        List<Appointments> ea_appointments = new ArrayList<>();
//
////       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
//        ea_appointments = (List<Appointments>) appointmentRepository.findAll();
//
//        List<AppointmentPojo> appointmentList = ObjectMapperUtils.mapAll(ea_appointments, AppointmentPojo.class);
//        return appointmentList;
//    }

    public List<CustomersPojo> customerList() {
        List<Customers> customer = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();

        customer = (List<Customers>) customerRepository.findAll();
        List<CustomersPojo> gdPojoList = ObjectMapperUtils.mapAll(customer, CustomersPojo.class);

        for(CustomersPojo customers:gdPojoList) {
            Customers customers1=customerRepository.findById( customers.getId() );
            Appointments appointments = appointmentRepository.findAllByCustomers(customers1).get( 0 );
            String no=appointments.getAppointmentNo();
            customers.setStarttime( appointments.getStarttime() );
        }
        return gdPojoList;
    }
    public List<WorkingPlanPojo> getWorkingPlanList() {
        List<WorkingPlan> work = new ArrayList<>();

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        work = (List<WorkingPlan>) workingPlanRepository.findAll();

        List<WorkingPlanPojo> gdPojoList = ObjectMapperUtils.mapAll(work, WorkingPlanPojo.class);
        return gdPojoList;
    }
    public Users getCurrentUserList() {
        Users currentUser = null;

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        currentUser = userRepository.findById(Long.valueOf("1"));

//        UserPojo gdPojoList = ObjectMapperUtils.mapAll(currentUser, UserPojo.class);
        return currentUser;
    }
    public Patient getPatientList() {
        Patient patient = null;

//       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
        patient = patientRepository.findById(Long.valueOf("1"));

//        UserPojo gdPojoList = ObjectMapperUtils.mapAll(currentUser, UserPojo.class);
        return patient;
    }
    public Settings generalSettingsList() {
        Settings general=null;
                general = generalSettingsRepository.findById(Long.valueOf("1"));
//        GeneralSettingsPojo settingsPojo = new GeneralSettingsPojo();
//        settingsPojo.setId(general.getId());
//        settingsPojo.setCompanyName(general.getCompanyName());
//        settingsPojo.setCompanyEmail(general.getCompanyEmail());
//        settingsPojo.setGoogleAnalyticsID(general.getGoogleAnalyticsID());
//        settingsPojo.setCompanyLink(general.getCompanyLink());
//        settingsPojo.setDateFormat(general.getDateFormat());

//        List<GeneralSettingsPojo> gdPojoList = ObjectMapperUtils.mapAll(general, GeneralSettingsPojo.class);
        return general;
    }
//      public List<AdminsPojo> adminsList() {
//        List<Users> admin = new ArrayList<>();
//
////       grade = (List<GradeMaster>) bsGrademasterRepository.findAll();
//        admin = (List<Users>) bsAdminRepository.findAll();
//
//        List<AdminsPojo> gdPojoList = ObjectMapperUtils.mapAll(admin, AdminsPojo.class);
//        return gdPojoList;
//    }
//

//    ArrayList<OBJECT> yourArray = new Gson().fromJson(jsonString, new TypeToken<List<OBJECT>>(){}.getType());


    public List<ServicesPojo> servicesList() {
        List<Services> services = new ArrayList<>();
        services = (List<Services>) servicesRepository.findAll();
        List<ServicesPojo> gdPojoList = ObjectMapperUtils.mapAll(services, ServicesPojo.class);
        if (gdPojoList != null) {
            for (ServicesPojo studentPojo : gdPojoList) {
                if(studentPojo.getId_service_categories()!=null) {
                    ServiceCategories categories = categoriesRepository.findByName( studentPojo.getId_service_categories().getName() );
                    if (categories != null) {
                        studentPojo.setId_service_categories( categories );
                        studentPojo.setCategoryId( categories.getId() );
                    }
                }
            }
        }

        return gdPojoList;
    }
    public List<AdminsPojo> adminsList() {
        List<Users> eaUsers = new ArrayList<>();

        eaUsers = (List<Users>) userRepository.findAll();

        List<AdminsPojo> gdPojoList = ObjectMapperUtils.mapAll(eaUsers, AdminsPojo.class);
        return gdPojoList;
    }

    public List<ProvidersPojo> providersdetailsList() {

        List<ServicesProviders> eaUsers = new ArrayList<>();

        eaUsers = (List<ServicesProviders>) providersRepository.findAll();

        List<ProvidersPojo> gdPojoList = ProvidersMapper.mapEntityToPojo(eaUsers);

        return gdPojoList;
    }

    public ServicesProviders SaveProvidersdetails(ProvidersPojo details) throws JSONException {
        ServicesProviders ea_service_providers = new ServicesProviders();
        if (details.getId() != null) {
            ea_service_providers=providersRepository.findById(details.getId());
            if(ea_service_providers!=null) {
                Users users = ea_service_providers.getIdUsers();
//            users.setId(details.getId());
                users.setUsername( details.getUsername() );
                users.setPassword( details.getPassword() );
                users.setFirstName( details.getFirstName() );
                users.setLastName( details.getLastName() );
                users.setEmail( details.getEmail() );
                users.setPhoneNumber( details.getPhoneNumber() );
                users.setMobileNumber( details.getMobileNumber() );
                users.setCity( details.getCity() );
                users.setState( details.getState() );
                users.setCountry( details.getCountryName() );
                users.setZipCode( details.getZipCode() );
                users.setCalenderType( details.getCalenderType() );
                users.setRetypepassword( details.getRetypepassword() );
                users.setAddress( details.getAddress() );
                users.setNotes( details.getNotes() );
                users.setFlagType( details.getFlagType() );
                if (details.getFlagType() == "Providers") {
                    users.setFlag( Integer.parseInt( "2" ) );
                }
                userRepository.save( users );
                details.setId_users( users );
            }

//            for (String serviceslist:details.getServiceslist()) {
//                ServiesProviderslist serviesProvidersList =new ServiesProviderslist();
//
//                Services ea_services = servicesRepository.findByName(serviceslist);
//                serviesProvidersList.setServices(ea_services);
//                serviesProvidersList.setUsers(users);
//                servicesListRepository.save(serviesProvidersList);
//
//            }
////            Services ea_services = servicesRepository.findByName(details.getServiceName());
//            List<Users> eauserslist = userRepository.findByOrderByIdDesc();
//            details.setIdusers(eauserslist.get(0));
//            details.setId_services(ea_services);
            ea_service_providers = ProvidersMapper.mapPojoToEntity(details);
            providersRepository.save(ea_service_providers);
            return ea_service_providers;
        }
        else {
//            ea_service_providers= providersRepository.findById(details.getId());
            Users users = new Users();
            users.setId(details.getId());
            users.setUsername(details.getUsername());
            users.setPassword(details.getPassword());
            users.setFirstName(details.getFirstName());
            users.setLastName(details.getLastName());
            users.setEmail(details.getEmail());
            users.setPhoneNumber(details.getPhoneNumber());
            users.setMobileNumber(details.getMobileNumber());
            users.setCity(details.getCity());
            users.setState(details.getState());
            users.setCountry(details.getCountryName());
            users.setZipCode(details.getZipCode());
            users.setCalenderType(details.getCalenderType());
            users.setRetypepassword(details.getRetypepassword());
            users.setAddress(details.getAddress());
            users.setNotes(details.getNotes());
            userRepository.save(users);
//            for (String serviceslist:details.getServiceslist()) {
//                ServiesProviderslist serviesProvidersList =new ServiesProviderslist();
//
//                Services ea_services = servicesRepository.findByName(serviceslist);
//                serviesProvidersList.setServices(ea_services);
//                serviesProvidersList.setUsers(users);
//                servicesListRepository.save(serviesProvidersList);
//
//            }
//            Services ea_services = servicesRepository.findByName(details.getServiceName());
            List<Users> userslist = userRepository.findByOrderByIdDesc();
            details.setId_users(userslist.get(0));
//            details.setId_services(ea_services);
            ea_service_providers = ProvidersMapper.mapPojoToEntity(details);
            providersRepository.save(ea_service_providers);
            return ea_service_providers;
        }
    }

    public List<StateDTO> getStateListBasedOnCountry(String country){
        List<State> designations=posStateRepository.findAllByCountryNameAndStatus(country,"Active");
        List<StateDTO> stateDTOS=UserMapper.mapStateEntityToPojo(designations);
        return stateDTOS;
    }

//    public List<UnitOfMeasurementPojo> getUOMListBasedOnMedicine(String medicine){
//        List<UnitOfMeasurement> designations=medicineRepository.findAllByMedicineName(medicine);
//        List<UnitOfMeasurementPojo> stateDTOS=UOMMapper.mapEntityToPojo(designations);
//        return stateDTOS;
//    }
////

   public List<CityDTO> getCityListBasedOnState(String state){
        List<City> designations=cityRepository.findAllByStateName(state);
        List<CityDTO> cityDTOS=UserMapper.mapCityEntityToPojo(designations);
        return cityDTOS;
    }

    public Services SaveServices(ServicesPojo details) throws JSONException {
        Services services = null;
        if (details.getId() != null) {
            services = servicesRepository.findById(details.getId());
            if (services != null) {
                ServiceCategories cateobj= categoriesRepository.findById(details.getCategoryId());
                details.setId_service_categories(cateobj);
                services = ServiceMapper.mapPojoToEntity(details);
                servicesRepository.save(services);
                return services;
            } else {
                return null;
            }
        } else {
            services = servicesRepository.findByNameNotInAndId(details.getName(),details.getId());
            if (services == null) {
                ServiceCategories cateobj= categoriesRepository.findById(details.getCategoryId());
                details.setId_service_categories(cateobj);
                services = ServiceMapper.mapPojoToEntity(details);
                servicesRepository.save(services);
                return services;
            } else {
                return null;
            }
        }
    }

//    public Invoice SaveInvoice(InvoicePojo details) throws JSONException {
//        Invoice invoice = new Invoice();
//        if(details.getAppointmentId()!=null){
//            invoice=invoiceRepository.findByAppointmentId(details.getAppointmentId());
//        }
//        else{
//             invoice = new Invoice();
//        }
//        if (details.getId() != null) {
//            invoice = invoiceRepository.findByNameNotInAndId(details.getName(), details.getId());
//            if (invoice != null) {
//                Services serviceObj= servicesRepository.findById(details.getServiceId());
//                details.setInvoice(serviceObj);
//                invoice = InvoiceMapper.mapPojoToEntity(details);
//                invoiceRepository.save(invoice);
//                return invoice;
//            } else {
//                return null;
//            }
//        } else {
//            invoice = invoiceRepository.findByName(details.getName());
//            if (invoice == null) {
//                Services serviceObj= servicesRepository.findById(details.getServiceId());
//                details.setInvoice(serviceObj);
//                invoice = InvoiceMapper.mapPojoToEntity(details);
//                invoiceRepository.save(invoice);
//                return invoice;
//            } else {
//                return null;
//            }
//        }
//    }



    public List<PrescriptionPojo> getPrescription(Long appointmentId){
        List<Prescription> prescriptions=new ArrayList<>();
        prescriptions=prescriptionRepository.findByAppointmentId(appointmentId);
        List<PrescriptionPojo> list=PrescriptionMapper.mapEntityToPojo(prescriptions);
        if(prescriptions.size()>0) {
            for (PrescriptionPojo prescription : list) {
                Gson json = new Gson();
                Type medicines = new TypeToken<ArrayList<MedicineListPojo>>() {
                }.getType();
                prescription.setMedicineList(json.fromJson(prescription.getMedicine(), medicines));
            }
            return list;
            }
        else
        {
            return null;
        }
    }


    public List<InvoiceListPojo> invoiceList(Long id) throws JSONException {
        Invoice invoice = new Invoice();
        invoice=invoiceRepository.findByAppointmentId(id);
        if(invoice!=null) {
            Type type = new TypeToken<ArrayList<InvoiceListPojo>>() {
            }.getType();
            Gson json1 = new Gson();
            List<InvoiceListPojo> invoiceList = json1.fromJson(invoice.getName(), type);
            return invoiceList;
        }
        else
        {
            return null;
        }

    }
   public String remarksList(Long id) throws JSONException {
        Appointments appointments = new Appointments();
        appointments=appointmentRepository.findById(id);
        if(appointments!=null) {
            String notes= appointments.getCustomers().getNotes();
            return notes;
        }
        else
        {
            return null;
        }

    }


    public List<timeSlotPojo> getallocatedTimeSlots(Date date){
    List<java.sql.Time> intervals = new ArrayList<>(25);
    List<String> intervalsList = new ArrayList<>();
    List<timeSlotPojo> timeSlotPojoList =new ArrayList<>(  );
    // These constructors are deprecated and are used only for example
    java.sql.Time startTime = new java.sql.Time(9, 0, 0);
    java.sql.Time endTime = new java.sql.Time(17, 0, 0);
        intervals.add(startTime);
    Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        while (cal.getTime().before(endTime)) {
        cal.add(Calendar.MINUTE, 15);
        intervals.add(new java.sql.Time(cal.getTimeInMillis()));
    }
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (java.sql.Time time : intervals) {
        String val=sdf.format(time);
        intervalsList.add(val);
    }
    List<Appointments> list = appointmentRepository.findAllByBookDatetime( date );
        for(String var:intervalsList){
            timeSlotPojo timeSlotPojo=new timeSlotPojo();
            timeSlotPojo.setTime( var );
            timeSlotPojo.setStatus( "Active" );
            timeSlotPojoList.add( timeSlotPojo );
        }
        for(timeSlotPojo time:timeSlotPojoList){
            for(Appointments appointments:list){
                if(StringUtils.equalsIgnoreCase(appointments.getStarttime(),time.getTime())){
                    time.setStatus("InActive");
                }
            }
        }
        return timeSlotPojoList;
    }
    public Boolean deleteServices(ServicesPojo details) {
            servicesRepository.delete(details.getId());
            return true;
    }
    public void deleteappointment(Long id){
        appointmentRepository.delete(id);
        customerRepository.delete( id );
    }
    public Boolean deleteCategoriesObj(CategoriesPojo details) {
            categoriesRepository.delete(details.getId());
            return true;
    }
    public Boolean deleteScheduler(SchedulerPojo details) {
            schedulerRepository.delete(details.getId());
            return true;
    }
    public Boolean deleteMedicine(MedicinePojo details) {
            medicineRepository.delete(details.getId());
            return true;
    }
    public Boolean deleteWorkingPlan(WorkingPlanPojo details) {
            workingPlanRepository.delete(details.getId());
            return true;
    }
    public Boolean deleteBreaks(BreaksPojo details) {
            breaksRepository.delete(details.getId());
            return true;
    }
    public Boolean deleteProviders(Long providersId) {
        ServicesProviders servicesProviders=providersRepository.findById(providersId);
        providersRepository.delete(servicesProviders);
        userRepository.delete(servicesProviders.getIdUsers());
        return true;
    }

    public BasePojo getPaginatedCountryList(String status,BasePojo basePojo,String searchText) {
        List<Country> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"countryId"));
        if(basePojo.isLastPage()==true){
            List<Country> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = countryRepository.findAllByCountryNameContaining(searchText);
            }else {
                list1=countryRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Country country=new Country();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"countryId"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            country=countryRepository.findFirstByCountryNameContainingAndStatus(searchText,sort,status);
            list = countryRepository.findAllByCountryNameContainingAndStatus(searchText,pageable,status);
        } else {
            country=countryRepository.findFirstByStatus(status,sort);
            list = countryRepository.findAllByStatus(status,pageable);
        }
        if(list.contains(country)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<CountryDTO> countryDtoList = UserMapper.mapCountryEntityToPojo(list);
        basePojo=calculatePagination(basePojo,countryDtoList.size());
        basePojo.setList(countryDtoList);
        return basePojo;
    }

    public BasePojo getPaginatedCategoryList(BasePojo basePojo,String searchText) {
        List<ServiceCategories> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<ServiceCategories> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = categoriesRepository.findAllByNameContaining(searchText);
            }else {
                list1=categoriesRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        ServiceCategories ctgry=new ServiceCategories();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            ctgry=categoriesRepository.findFirstByNameContaining(searchText,sort);
            list = categoriesRepository.findAllByNameContaining(searchText,pageable);
        } else {
            ctgry=categoriesRepository.findFirstBy(sort);
            list = categoriesRepository.findAllBy(pageable);
        }
        if(list.contains(ctgry)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<CategoriesPojo> countryDtoList = CategoriesMapper.mapCategoryEntityToPojo(list);
        basePojo=calculatePagination(basePojo,countryDtoList.size());
        basePojo.setList(countryDtoList);
        return basePojo;
    }


    public BasePojo getPaginatedCurrencyList(BasePojo basePojo,String searchText) {
        List<Currency> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"currencyId"));
        if(basePojo.isLastPage()==true){
            List<Currency> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = currencyRepository.findAllByCurrencyNameContainingOrCurrencyCodeContaining(searchText,searchText);
            }else {
                list1=currencyRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Currency currency=new Currency();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"currencyId"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            currency=currencyRepository.findFirstByCurrencyNameContainingOrCurrencyCodeContaining(searchText,searchText,sort);
            list = currencyRepository.findAllByCurrencyNameContainingOrCurrencyCodeContaining(searchText,searchText,pageable);
        } else {
            currency=currencyRepository.findFirstBy(sort);
            list = currencyRepository.findAllBy(pageable);
        }
        if(list.contains(currency)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<CurrencyDTO> currencyDTOList = UserMapper.mapCurrencyEntityToPojo(list);
        basePojo=calculatePagination(basePojo,currencyDTOList.size());
        basePojo.setList(currencyDTOList);
        return basePojo;
    }


    public BasePojo getPaginatedServicesList(BasePojo basePojo,String searchText) {
        List<Services> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<Services> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = servicesRepository.findAllByNameContaining(searchText);
            }else {
                list1=servicesRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Services services=new Services();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            services=servicesRepository.findFirstByNameContaining(searchText,sort);
            list = servicesRepository.findAllByNameContaining(searchText,pageable);
        } else {
            services=servicesRepository.findFirstBy(sort);
            list = servicesRepository.findAllBy(pageable);
        }
        if(list.contains(services)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<ServicesPojo> pojo = ServiceMapper.mapServiceEntityToPojo(list);
        for(ServicesPojo servicesPojo:pojo){
            servicesPojo.setCategoryId(servicesPojo.getCategoryId());
        }
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }



    public BasePojo getPaginatedMedicineList(BasePojo basePojo,String searchText) {
        List<Medicine> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<Medicine> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = medicineRepository.findAllByMedicineNameContaining(searchText);
            }else {
                list1=medicineRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Medicine medicine=new Medicine();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            medicine=medicineRepository.findFirstByMedicineNameContaining(searchText,sort);
            list = medicineRepository.findAllByMedicineNameContaining(searchText,pageable);
        } else {
            medicine=medicineRepository.findFirstBy(sort);
            list = medicineRepository.findAllBy(pageable);
        }
        if(list.contains(medicine)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<MedicinePojo> pojo = MedicineMapper.mapMedicineEntityToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }


    public BasePojo getPaginatedProvidersList(BasePojo basePojo,String searchText) {
        List<Users> users=userRepository.findByFirstNameContaining(searchText);
        List<ServicesProviders> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<ServicesProviders> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = providersRepository.findAllByIdUsersIn(users);
            }else {
                list1=providersRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        ServicesProviders providers=new ServicesProviders();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            providers=providersRepository.findFirstByIdUsersIn(users,sort);
            list = providersRepository.findAllByIdUsersIn(users,pageable);
        } else {
            providers=providersRepository.findFirstBy(sort);
            list = providersRepository.findAllBy(pageable);
        }
        if(list.contains(providers)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<ProvidersPojo> pojo = ProvidersMapper.mapEntityToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }


    public BasePojo getPaginatedUomList(BasePojo basePojo,String searchText) {
        List<UnitOfMeasurement> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"unitOfMeasurementId"));
        if(basePojo.isLastPage()==true){
            List<UnitOfMeasurement> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = uomRepository.findAllByUnitOfMeasurementNameContaining(searchText);
            }else {
                list1=uomRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        UnitOfMeasurement uom=new UnitOfMeasurement();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"unitOfMeasurementId"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            uom=uomRepository.findFirstByUnitOfMeasurementNameContaining(searchText,sort);
            list = uomRepository.findAllByUnitOfMeasurementNameContaining(searchText,pageable);
        } else {
            uom=uomRepository.findFirstBy(sort);
            list = uomRepository.findAllBy(pageable);
        }
        if(list.contains(uom)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<UnitOfMeasurementPojo> pojo = UserMapper.mapUomEntityToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }


    public List<MailDTO> getMailList() {
        List<Mail> list = new ArrayList<>();
        list = mailRepository.findAll();
        List<MailDTO> mailDTOList = MailMapper.mapMailEntityToPojo(list);
        return mailDTOList;
    }
    public MailDTO editMail(String name) {
        Mail mail = mailRepository.findByUserName(name);
        List<Mail> mails = new ArrayList<>();
        mails.add(mail);
        MailDTO mailDTO = MailMapper.mapMailEntityToPojo(mails).get(0);
        return mailDTO;
    }


    public void deleteMail(String  userName) {
        mailRepository.delete(mailRepository.findByUserName(userName));
    }
    public MailDTO createSaveMailDetails(MailDTO saveMailDetails) {
        Mail mail =mailRepository.findOne(1L);
        if(mail==null)
        {
            mail=new Mail();
        }
        mail.setUserName(saveMailDetails.getUserName());
        mail.setPassword(saveMailDetails.getPassword());
        mail.setPort(saveMailDetails.getPort());
        mail.setSmtpHost(saveMailDetails.getSmtpHost());
        mail.setForMail(saveMailDetails.getForMail());
        mail.setStatus("Active");
        mailRepository.save(mail);
        return saveMailDetails;
    }

    public void saveMailSchedule(MailSchedulerData mailSchedulerData) throws Exception {
        Mail mailServerPops = mailRepository.findAll().get(0);
        mailSchedulerData.setFromMail(mailServerPops);
        String filename = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.isNotEmpty(mailSchedulerData.getReportName()))
            switch (mailSchedulerData.getReportName()) {
                case "appointmentRpt":
                    if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(mailSchedulerData.getReportType(), "application/pdf")) {
                        downloadAppointmentReportPdf(outputStream);
                        filename = "AppntReport.pdf";
                    } else {
                        downloadAppointmentReportExcel(outputStream);
                        filename = "AppntReport.xls";
                    }
                    break;
            }
        if (StringUtils.isEmpty(mailSchedulerData.getBody())) {
            mailSchedulerData.setBody("");
        }
        MailService.sendMailWithAttachment(mailSchedulerData.getFromMail(),
                mailSchedulerData.getToEmail(), "", mailSchedulerData.getSubject(),
                mailSchedulerData.getBody(), mailSchedulerData.getReportType(),
                outputStream.toByteArray(), filename);
    }


    public static BaseFont getcustomfont() {
        String relativeWebPath = "fonts/arial.ttf";
        return FontFactory.getFont("arial", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK).getBaseFont();
    }


    @Transactional
    public void downloadAppointmentReportPdf(OutputStream outputStream) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstApntReport();
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public PdfPTable createFirstApntReport() {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable tab = new PdfPTable(1);
        Font f = new Font(getcustomfont(), 15, Font.BOLD, Color.WHITE);
        PdfPTable table = new PdfPTable(a + 6);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("AppointmentReport", f));
        p.setBackgroundColor(myColor);
        tab.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Name", font));
        pc2.setBackgroundColor(myColor);
        PdfPCell pc3 = new PdfPCell(new Phrase("AppointmentNo", font));
        pc3.setBackgroundColor(myColor);
        PdfPCell pc4 = new PdfPCell(new Phrase("Email", font));
        pc4.setBackgroundColor(myColor);
        PdfPCell pc5 = new PdfPCell(new Phrase("Address", font));
        pc5.setBackgroundColor(myColor);
        PdfPCell pc6 = new PdfPCell(new Phrase("PhoneNumber", font));
        pc6.setBackgroundColor(myColor);
        PdfPCell pc7 = new PdfPCell(new Phrase("AppointmentDate", font));
        pc7.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        table.addCell(pc4);
        table.addCell(pc5);
        table.addCell(pc6);
        table.addCell(pc7);
        List<AppointmentPojo> appList =appointmentList();
        for (AppointmentPojo list : appList) {
            table.addCell(new Phrase(list.getCustomers().getFirstName() + "", font1));
            table.addCell(new Phrase(list.getAppointmentNo() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getEmail() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getAddress() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getPhoneNumber() + "", font1));
            table.addCell(new Phrase(list.getBook_datetime() + "", font1));
        }
        tab.addCell(table);
        return tab;
    }


    @Transactional
    public void downloadAppointmentReportExcel(OutputStream out) {
        try {
            List<AppointmentPojo> appList =appointmentList();
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            headerRow.createCell(0).setCellValue("AppointmentReport ");
            HSSFRow headerRow1 = sheet.createRow(1);
            headerRow1.createCell(0).setCellValue("Name");
            headerRow1.createCell(1).setCellValue("AppointmentNo");
            headerRow1.createCell(2).setCellValue("Email");
            headerRow1.createCell(3).setCellValue("Address");
            headerRow1.createCell(4).setCellValue("PhoneNo");
            headerRow1.createCell(5).setCellValue("AppointmentDate");
            int i = 1;
            for (AppointmentPojo list : appList) {
                HSSFRow row = sheet.createRow(++i);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row.createCell(0).setCellValue(list.getCustomers().getFirstName());
                row.createCell(1).setCellValue(list.getAppointmentNo());
                row.createCell(2).setCellValue(list.getCustomers().getEmail());
                row.createCell(3).setCellValue(list.getCustomers().getAddress());
                row.createCell(4).setCellValue(list.getCustomers().getPhoneNumber());
                row.createCell(5).setCellValue(dateFormat.format(list.getBook_datetime()));
            }
            hwb.write(out);
            out.close();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }

    @Transactional
    public void downloadProvidersPdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstProviders();
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public PdfPTable createFirstProviders() {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable tab = new PdfPTable(1);
        Font f = new Font(getcustomfont(), 15, Font.BOLD, Color.WHITE);
        PdfPTable table = new PdfPTable(a + 8);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("providersReport", f));
        p.setBackgroundColor(myColor);
        tab.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("First Name", font));
        pc2.setBackgroundColor(myColor);
        PdfPCell pc3 = new PdfPCell(new Phrase("Last Name", font));
        pc3.setBackgroundColor(myColor);
        PdfPCell pc4 = new PdfPCell(new Phrase("Email", font));
        pc4.setBackgroundColor(myColor);
        PdfPCell pc5 = new PdfPCell(new Phrase("Address", font));
        pc5.setBackgroundColor(myColor);
        PdfPCell pc6 = new PdfPCell(new Phrase("PhoneNumber", font));
        pc6.setBackgroundColor(myColor);
        PdfPCell pc7 = new PdfPCell(new Phrase("State", font));
        pc7.setBackgroundColor(myColor);
        PdfPCell pc8 = new PdfPCell(new Phrase("City", font));
        pc8.setBackgroundColor(myColor);
        PdfPCell pc9 = new PdfPCell(new Phrase("Zipcode", font));
        pc9.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        table.addCell(pc4);
        table.addCell(pc5);
        table.addCell(pc6);
        table.addCell(pc7);
        table.addCell(pc8);
        table.addCell(pc9);
        List<ProvidersPojo> appList =providersdetailsList();
        for (ProvidersPojo list : appList) {
            table.addCell(new Phrase(list.getId_users().getFirstName() + "",font1));
            table.addCell(new Phrase(list.getId_users().getLastName() + "",font1));
            table.addCell(new Phrase(list.getId_users().getEmail() + "",font1));
            table.addCell(new Phrase(list.getId_users().getAddress() + "",font1));
            table.addCell(new Phrase(list.getId_users().getPhoneNumber() + "",font1));
            table.addCell(new Phrase(list.getId_users().getState() + "",font1));
            table.addCell(new Phrase(list.getId_users().getCity() + "",font1));
            table.addCell(new Phrase(list.getId_users().getZipCode() + "",font1));
        }
        tab.addCell(table);
        return tab;
    }


    @Transactional
    public void downloadProvidersReportExcel(OutputStream out, String searchText) {
        try {
            List<ProvidersPojo> appList =providersdetailsList();
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            headerRow.createCell(0).setCellValue("ProvidersReport ");
            HSSFRow headerRow1 = sheet.createRow(1);
            headerRow1.createCell(0).setCellValue("FirstName");
            headerRow1.createCell(1).setCellValue("LastName");
            headerRow1.createCell(2).setCellValue("Email");
            headerRow1.createCell(3).setCellValue("Address");
            headerRow1.createCell(4).setCellValue("PhoneNo");
            headerRow1.createCell(5).setCellValue("State");
            headerRow1.createCell(6).setCellValue("City");
            headerRow1.createCell(7).setCellValue("Zipcode");
            int i = 1;
            for (ProvidersPojo list : appList) {
                HSSFRow row = sheet.createRow(++i);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row.createCell(0).setCellValue(list.getId_users().getFirstName());
                row.createCell(1).setCellValue(list.getId_users().getLastName());
                row.createCell(2).setCellValue(list.getId_users().getEmail());
                row.createCell(3).setCellValue(list.getId_users().getAddress());
                row.createCell(4).setCellValue(list.getId_users().getPhoneNumber());
                row.createCell(5).setCellValue(list.getId_users().getState());
                row.createCell(6).setCellValue(list.getId_users().getCity());
                row.createCell(7).setCellValue(list.getId_users().getZipCode());


            }
            hwb.write(out);
            out.close();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }


    public BasePojo getPaginatedWorkPlanList(BasePojo basePojo,String searchText) {
        List<WorkingPlan> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<WorkingPlan> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = workingPlanRepository.findAllByDayContaining(searchText);
            }else {
                list1=workingPlanRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        WorkingPlan plan=new WorkingPlan();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            plan=workingPlanRepository.findFirstByDayContaining(searchText,sort);
            list = workingPlanRepository.findAllByDayContaining(searchText,pageable);
        } else {
            plan=workingPlanRepository.findFirstBy(sort);
            list = workingPlanRepository.findAllBy(pageable);
        }
        if(list.contains(plan)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<WorkingPlanPojo> pojo = WorkingPlanMapper.mapEntityToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }


    public BasePojo getPaginatedBreaksList(BasePojo basePojo,String searchText) {
        List<Breaks> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<Breaks> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = breaksRepository.findAllByDayContaining(searchText);
            }else {
                list1=breaksRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Breaks plan=new Breaks();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            plan=breaksRepository.findFirstByDayContaining(searchText,sort);
            list = breaksRepository.findAllByDayContaining(searchText,pageable);
        } else {
            plan=breaksRepository.findFirstBy(sort);
            list = breaksRepository.findAllBy(pageable);
        }
        if(list.contains(plan)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<BreaksPojo> pojo = BreaksMapper.mapEntityToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if(employeeDTO.getEmployeeId()!=null){
            employee=posEmployeeRepository.findByEmployeeNameAndEmployeeIdNotIn(employeeDTO.getEmployeeName(),employeeDTO.getEmployeeId());
        }
        else{
            employee=posEmployeeRepository.findByEmployeeName(employeeDTO.getEmployeeName());
        }
        if(employee==null) {
            Employee employes = UserMapper.mapEmployeePojoToEntity(employeeDTO);
            posEmployeeRepository.save(employes);
            return employes;
        }else{
            return null;
        }
    }

    public Scheduler saveScheduler(SchedulerPojo schedulerPojo) {
        Scheduler scheduler = new Scheduler();
        if(schedulerPojo.getId()!=null){
            scheduler=schedulerRepository.findByFirstNameAndIdNotIn(schedulerPojo.getFirstName(),schedulerPojo.getId());
        }
        else{
            scheduler=schedulerRepository.findByFirstName(schedulerPojo.getFirstName());
        }
        if(scheduler==null) {
            Scheduler scheduler1 = SchedulerMapper.mapPojoToEntity(schedulerPojo);
            schedulerRepository.save(scheduler1);
            return scheduler1;
        }else{
            return null;
        }
    }



    public BasePojo getPaginatedEmployeeList(String status,BasePojo basePojo,String searchText) {
        List<Employee> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.DESC,"employeeId"));
        if(basePojo.isLastPage()==true){
            List<Employee> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = posEmployeeRepository.findAllByEmployeeNameContainingAndStatus(searchText,status);
            }else {
                list1=posEmployeeRepository.findAllByStatus(status);
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        if(StringUtils.isEmpty(status)){
            status="Active";
        }
        Employee employee=new Employee();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.ASC,"employeeId"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            employee=posEmployeeRepository.findFirstByEmployeeNameAndStatus(searchText,status,sort);
            list = posEmployeeRepository.findAllByEmployeeNameContainingAndStatus(searchText,status,pageable);
        } else {
            employee=posEmployeeRepository.findFirstByStatus(status,sort);
            list = posEmployeeRepository.findAllByStatus(status,pageable);
        }
        if(list.contains(employee)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<EmployeeDTO> employeeDTOList = UserMapper.mapEmployeeEntityToPojo(list);
        basePojo=calculatePagination(basePojo,employeeDTOList.size());
        basePojo.setList(employeeDTOList);
        return basePojo;
    }

    public BasePojo getPaginatedSchedularList(String status,BasePojo basePojo,String searchText) {
        List<Scheduler> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        if(basePojo.isLastPage()==true){
            List<Scheduler> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = schedulerRepository.findAllByFirstNameContainingAndStatus(searchText,status);
            }else {
                list1=schedulerRepository.findAllByStatus(status);
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        if(StringUtils.isEmpty(status)){
            status="Active";
        }
        Scheduler scheduler=new Scheduler();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.ASC,"Id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            scheduler=schedulerRepository.findFirstByFirstNameAndStatus(searchText,status,sort);
            list = schedulerRepository.findAllByFirstNameContainingAndStatus(searchText,status,pageable);
        } else {
            scheduler=schedulerRepository.findFirstByStatus(status,sort);
            list = schedulerRepository.findAllByStatus(status,pageable);
        }
        if(list.contains(scheduler)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<SchedulerPojo> schedulerPojoList = SchedulerMapper.mapSchedularEntityToPojo(list);
        basePojo=calculatePagination(basePojo,schedulerPojoList.size());
        basePojo.setList(schedulerPojoList);
        return basePojo;
    }



    public BasePojo getPaginatedAppointmentList(BasePojo basePojo,String searchText) {
       List<Customers> customers=customerRepository.findAllByFirstNameContaining(searchText);

        List<Appointments> list = new ArrayList<>();
        basePojo.setPageSize(paginatedConstants);
        Sort sort=new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        if(basePojo.isLastPage()==true){
            List<Appointments> list1=new ArrayList<>();
            if (!StringUtils.isEmpty(searchText)) {
                list1 = appointmentRepository.findAllByCustomersIn(customers);
            }else {
                list1=appointmentRepository.findAll();
            }
            int size=list1.size()%paginatedConstants;
            int pageNo=list1.size()/paginatedConstants;
            if(size!=0){
                basePojo.setPageNo(pageNo);
            }else {
                basePojo.setPageNo(pageNo-1);
            }
        }
        Appointments appointments=new Appointments();
        Pageable pageable=new PageRequest(basePojo.getPageNo(),basePojo.getPageSize(),sort);
        if(basePojo.isNextPage()==true || basePojo.isFirstPage()==true){
            sort=new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        }
        if (!StringUtils.isEmpty(searchText)) {
            if(customers.size()>0) {
                appointments = appointmentRepository.findFirstByCustomersIn( customers.get( 0 ), sort );
                list = appointmentRepository.findAllByCustomersIn( customers, pageable );
            }
        } else {
            appointments=appointmentRepository.findFirstBy(sort);
            list = appointmentRepository.findAllBy(pageable);
        }
        if(list.contains(appointments)){
            basePojo.setStatus(true);
        }else {
            basePojo.setStatus(false);
        }
        List<AppointmentPojo> pojo = AppointmentsMapper.mapEntiyToPojo(list);
        basePojo=calculatePagination(basePojo,pojo.size());
        basePojo.setList(pojo);
        return basePojo;
    }


    public String getFormSetUpNo1() throws JSONException {
        FormSetUp formSetUp =posFormSetupRepository.findAllByTypename("AppointmentNumber");
        int incValue = Integer.parseInt(formSetUp.getNextref());
        String formsetupNo =getNextRefInvoice(formSetUp.getTypeprefix(),String.format("%05d", ++incValue));
            return formsetupNo;
    }

    @Transactional
    public void downloadCountryPdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableCountry(searchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private PdfPTable createFirstTableCountry(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 2);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Country", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Country Name", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("Status", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
        List<CountryDTO> countryList = getCountryList(searchText);
        table.setWidthPercentage(100);
        for (CountryDTO countryPojo : countryList) {
            table.addCell(new Phrase(countryPojo.getCountryName() + "", font1));
            table.addCell(new Phrase(countryPojo.getStatus() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public void downloadCountryExcelSheet(ByteArrayOutputStream outputStream,  String searchText) {
        try {
            List<CountryDTO> countryList = getCountryList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Country Name");
            headerRow1.createCell(1).setCellValue("Status");
            int i = 0;
            for (CountryDTO country : countryList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(country.getCountryName());
                row.createCell(1).setCellValue(country.getStatus());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }


    @Transactional
    public void downloadCurrencyPdf(OutputStream outputStream,String currencySearchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableCurrency(currencySearchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private PdfPTable createFirstTableCurrency(String currencySearchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 3);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Currency", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Currency Code", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("Currency Word", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
        PdfPCell pc4 = new PdfPCell(new Phrase("Currency Symbol", font));
        pc4.setBackgroundColor(myColor);
        table.addCell(pc4);
        List<CurrencyDTO> currencyList = getCurrencyList(currencySearchText);
        table.setWidthPercentage(100);
        for (CurrencyDTO currencyDTO : currencyList) {
            table.addCell(new Phrase(currencyDTO.getCurrencyCode() + "", font1));
            table.addCell(new Phrase(currencyDTO.getCurrencyName() + "", font1));
//            table.addCell(new Phrase(currencyDTO.getCurrencyDescription() + "", font1));
            table.addCell(new Phrase(currencyDTO.getCurrencySymbol() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public void downloadCurrencyExcelSheet(ByteArrayOutputStream outputStream,  String currencySearchText) {
        try {
            List<CurrencyDTO> currencyList = getCurrencyList(currencySearchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Currency Code");
            headerRow1.createCell(1).setCellValue("Currency Word");
            headerRow1.createCell(2).setCellValue("Currency Symbol");
            int i = 0;
            for (CurrencyDTO currency : currencyList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(currency.getCurrencyCode());
                row.createCell(1).setCellValue(currency.getCurrencyName());

                row.createCell(2).setCellValue(currency.getCurrencySymbol());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }

    @Transactional
    public void downloadMedicinePdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableMedicine(searchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private PdfPTable createFirstTableMedicine(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 3);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Medicines", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Medicine Name", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("Medicine Code", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
        PdfPCell pc4 = new PdfPCell(new Phrase("UOM", font));
        pc4.setBackgroundColor(myColor);
        table.addCell(pc4);
        List<MedicinePojo> medicineList = getMedicineList(searchText);
        table.setWidthPercentage(100);
        for (MedicinePojo medicinePojo : medicineList) {
            table.addCell(new Phrase(medicinePojo.getMedicineName() + "", font1));
            table.addCell(new Phrase(medicinePojo.getCode() + "", font1));
//            table.addCell(new Phrase(currencyDTO.getCurrencyDescription() + "", font1));
            table.addCell(new Phrase(medicinePojo.getUnitOfMeasurement() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public List<MedicinePojo> getMedicineList(String searchText ){
        List<Medicine> list = new ArrayList<>();
        if (!StringUtils.isEmpty(searchText)) {
            list =medicineRepository .findAllByMedicineName(searchText);
        } else {
            list = medicineRepository.findAll();
        }
        List<MedicinePojo> priceManagerPojoList =MedicineMapper.mapMedicineEntityToPojo(list);
        return priceManagerPojoList;
    }

    public void downloadMedicineExcelSheet(ByteArrayOutputStream outputStream,  String searchText) {
        try {
            List<MedicinePojo> medicinePojo = getMedicineList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Medicine Name");
            headerRow1.createCell(1).setCellValue("Medicine Code");
            headerRow1.createCell(2).setCellValue("UOM");
            int i = 0;
            for (MedicinePojo medicinePojo1 : medicinePojo) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(medicinePojo1.getMedicineName());
                row.createCell(1).setCellValue(medicinePojo1.getCode());
//                row.createCell(2).setCellValue(currency.getCurrencyDescription());
                row.createCell(2).setCellValue(medicinePojo1.getUnitOfMeasurement());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }

  @Transactional
    public void downloadUomPdf(OutputStream outputStream,String UOMSearchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableUom(UOMSearchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private PdfPTable createFirstTableUom(String UOMSearchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 2);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("UOM", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("UOM Name", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("UOM Description", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
//        PdfPCell pc4 = new PdfPCell(new Phrase("UOM", font));
//        pc4.setBackgroundColor(myColor);
//        table.addCell(pc4);
        List<UnitOfMeasurementPojo> unitOfMeasurementList = getUomList(UOMSearchText);
        table.setWidthPercentage(100);
        for (UnitOfMeasurementPojo unitOfMeasurementPojo : unitOfMeasurementList) {
            table.addCell(new Phrase(unitOfMeasurementPojo.getUnitOfMeasurementName() + "", font1));
            table.addCell(new Phrase(unitOfMeasurementPojo.getUnitOfMeasurementDescription() + "", font1));
//            table.addCell(new Phrase(currencyDTO.getCurrencyDescription() + "", font1));
//            table.addCell(new Phrase(unitOfMeasurementPojo.getUnitOfMeasurement() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public List<UnitOfMeasurementPojo> getUomList(String UOMSearchText ){
        List<UnitOfMeasurement> list = new ArrayList<>();
        if (!StringUtils.isEmpty(UOMSearchText)) {
            list =uomRepository .findAllByUnitOfMeasurementNameContaining(UOMSearchText);
        } else {
            list = uomRepository.findAll();
        }
        List<UnitOfMeasurementPojo> unitOfMeasurementPojoList =UOMMapper.mapEntityToPojo(list);
        return unitOfMeasurementPojoList;
    }

    public void downloadUomExcelSheet(ByteArrayOutputStream outputStream,  String searchText) {
        try {
            List<UnitOfMeasurementPojo> unitOfMeasurementList = getUomList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("UOM Name");
            headerRow1.createCell(1).setCellValue("UOM Description");
//            headerRow1.createCell(2).setCellValue("UOM");
            int i = 0;
            for (UnitOfMeasurementPojo unitOfMeasurementPojo : unitOfMeasurementList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(unitOfMeasurementPojo.getUnitOfMeasurementName());
                row.createCell(1).setCellValue(unitOfMeasurementPojo.getUnitOfMeasurementDescription());
//                row.createCell(2).setCellValue(currency.getCurrencyDescription());
//                row.createCell(3).setCellValue(unitOfMeasurementPojo.getUnitOfMeasurement());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }



    @Transactional
    public void downloadEmployeePdf(OutputStream outputStream,String employeeSearchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableEmployee(employeeSearchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private PdfPTable createFirstTableEmployee(String employeeSearchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 8);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Employees", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Employee Name", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("Address", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
        PdfPCell pc4 = new PdfPCell(new Phrase("Employee Code ", font));
        pc4.setBackgroundColor(myColor);
        table.addCell(pc4);
        PdfPCell pc5 = new PdfPCell(new Phrase("Employee Phone", font));
        pc5.setBackgroundColor(myColor);
        table.addCell(pc5);
        PdfPCell pc6 = new PdfPCell(new Phrase("Gender", font));
        pc6.setBackgroundColor(myColor);
        table.addCell(pc6);
        PdfPCell pc7 = new PdfPCell(new Phrase("DOB", font));
        pc7.setBackgroundColor(myColor);
        table.addCell(pc7);
        PdfPCell pc8 = new PdfPCell(new Phrase("DOJ", font));
        pc8.setBackgroundColor(myColor);
        table.addCell(pc8);
        PdfPCell pc9 = new PdfPCell(new Phrase("Status", font));
        pc9.setBackgroundColor(myColor);
        table.addCell(pc9);

//        PdfPCell pc4 = new PdfPCell(new Phrase("UOM", font));
//        pc4.setBackgroundColor(myColor);
//        table.addCell(pc4);
        List<EmployeeDTO> employeeList = getEmployeeList(employeeSearchText);
        table.setWidthPercentage(100);
        for (EmployeeDTO employeeDTO : employeeList) {
            table.addCell(new Phrase(employeeDTO.getEmployeeName() + "", font1));
            table.addCell(new Phrase(employeeDTO.getEmployeeAddr() + "", font1));
            table.addCell(new Phrase(employeeDTO.getEmployeeCode() + "", font1));
            table.addCell(new Phrase(employeeDTO.getEmployeePhone() + "", font1));
            table.addCell(new Phrase(employeeDTO.getGender() + "", font1));
            table.addCell(new Phrase(employeeDTO.getEmployeeDOB() + "", font1));
            table.addCell(new Phrase(employeeDTO.getEmployeeDOJ() + "", font1));
            table.addCell(new Phrase(employeeDTO.getStatus() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public List<EmployeeDTO> getEmployeeList(String employeeSearchText ){
        List<Employee> list = new ArrayList<>();
        if (!StringUtils.isEmpty(employeeSearchText)) {
            list =posEmployeeRepository .findAllByStatus(employeeSearchText);
        } else {
            list = posEmployeeRepository.findAll();
        }
        List<EmployeeDTO> employeeDTOList =UserMapper.mapEmployeeEntityToPojo(list);
        return employeeDTOList;
    }

    public void downloadEmployeeExcelSheet(ByteArrayOutputStream outputStream,  String employeeSearchText) {
        try {
            List<EmployeeDTO> employeeList = getEmployeeList(employeeSearchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Employee Name");
            headerRow1.createCell(1).setCellValue("Address");
            headerRow1.createCell(2).setCellValue("Employee Code");
            headerRow1.createCell(3).setCellValue("Employee Phone");
            headerRow1.createCell(4).setCellValue("Gender");
            headerRow1.createCell(5).setCellValue("DOB");
            headerRow1.createCell(6).setCellValue("DOJ");
            headerRow1.createCell(7).setCellValue("Status");
//            headerRow1.createCell(2).setCellValue("UOM");
            int i = 0;
            for (EmployeeDTO EmployeeDTO : employeeList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(EmployeeDTO.getEmployeeName());
                row.createCell(1).setCellValue(EmployeeDTO.getEmployeeAddr());
                row.createCell(2).setCellValue(EmployeeDTO.getEmployeeCode());
                row.createCell(3).setCellValue(EmployeeDTO.getEmployeePhone());
                row.createCell(4).setCellValue(EmployeeDTO.getGender());
                row.createCell(5).setCellValue(EmployeeDTO.getEmployeeDOB().toString());
                row.createCell(6).setCellValue(EmployeeDTO.getEmployeeDOJ().toString());
//                if(EmployeeDTO.getEffectiveDate()!=null) {
//                    row.createCell(7).setCellValue(EmployeeDTO.getEffectiveDate().toString());
//                }else {
//                    row.createCell(7).setCellValue("");
//                }
                row.createCell(7).setCellValue(EmployeeDTO.getStatus());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }


    @Transactional
    public void downloadServicePdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableService(searchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private PdfPTable createFirstTableService(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 9);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Services", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Category", font));
        pc2.setBackgroundColor(myColor);
        table.addCell(pc2);
        PdfPCell pc3 = new PdfPCell(new Phrase("Service Name", font));
        pc3.setBackgroundColor(myColor);
        table.addCell(pc3);
        PdfPCell pc4 = new PdfPCell(new Phrase("Service Price", font));
        pc4.setBackgroundColor(myColor);
        table.addCell(pc4);
        PdfPCell pc5 = new PdfPCell(new Phrase("Duration", font));
        pc5.setBackgroundColor(myColor);
        table.addCell(pc5);
        PdfPCell pc6 = new PdfPCell(new Phrase("Currency", font));
        pc6.setBackgroundColor(myColor);
        table.addCell(pc6);
        PdfPCell pc7 = new PdfPCell(new Phrase("Availabilities Type", font));
        pc7.setBackgroundColor(myColor);
        table.addCell(pc7);
        PdfPCell pc8 = new PdfPCell(new Phrase("Attendants Number", font));
        pc8.setBackgroundColor(myColor);
        table.addCell(pc8);
        PdfPCell pc9 = new PdfPCell(new Phrase("Flag", font));
        pc9.setBackgroundColor(myColor);
        table.addCell(pc9);
        PdfPCell pc10 = new PdfPCell(new Phrase("Description", font));
        pc10.setBackgroundColor(myColor);
        table.addCell(pc10);
//        PdfPCell pc4 = new PdfPCell(new Phrase("UOM", font));
//        pc4.setBackgroundColor(myColor);
//        table.addCell(pc4);
        List<ServicesPojo> serviceList = getServiceList(searchText);
        table.setWidthPercentage(100);
        for (ServicesPojo servicesPojo : serviceList) {
            table.addCell(new Phrase(servicesPojo.getId_service_categories().getName() + "", font1));
            table.addCell(new Phrase(servicesPojo.getName() + "", font1));
            table.addCell(new Phrase(servicesPojo.getDuration() + "", font1));
            table.addCell(new Phrase(servicesPojo.getPrice() + "", font1));
            table.addCell(new Phrase(servicesPojo.getCurrency() + "", font1));
            table.addCell(new Phrase(servicesPojo.getAvailabilities_type() + "", font1));
            table.addCell(new Phrase(servicesPojo.getAttendants_number() + "", font1));
            table.addCell(new Phrase(servicesPojo.getFlag() + "", font1));
            table.addCell(new Phrase(servicesPojo.getDescription() + "", font1));

//            table.addCell(new Phrase(currencyDTO.getCurrencyDescription() + "", font1));
//            table.addCell(new Phrase(unitOfMeasurementPojo.getUnitOfMeasurement() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }

    public List<ServicesPojo> getServiceList(String searchText ){
        List<Services> list = new ArrayList<>();
        if (!StringUtils.isEmpty(searchText)) {
            list =servicesRepository .findAllByNameContaining(searchText);
        } else {
            list = servicesRepository.findAll();
        }
        List<ServicesPojo> servicesPojoList =ServiceMapper.mapServiceEntityToPojo(list);
        return servicesPojoList;
    }

    public void downloadServiceExcelSheet(ByteArrayOutputStream outputStream,  String searchText) {
        try {
            List<ServicesPojo> servicesPojoList = getServiceList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Category");
            headerRow1.createCell(1).setCellValue("Service Name");
            headerRow1.createCell(2).setCellValue("Service Price");
            headerRow1.createCell(3).setCellValue("Duration");
            headerRow1.createCell(4).setCellValue("Currency");
            headerRow1.createCell(5).setCellValue("Availabilities Type");
            headerRow1.createCell(6).setCellValue("Attendants Number");
            headerRow1.createCell(7).setCellValue("Flag");
            headerRow1.createCell(8).setCellValue("Description");
//            headerRow1.createCell(2).setCellValue("UOM");
            int i = 0;
            for (ServicesPojo servicesPojo : servicesPojoList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(servicesPojo.getId_service_categories().getName());
                row.createCell(1).setCellValue(servicesPojo.getName());
                row.createCell(3).setCellValue(servicesPojo.getDuration());
                row.createCell(2).setCellValue(servicesPojo.getPrice());
                row.createCell(4).setCellValue(servicesPojo.getCurrency());
                row.createCell(5).setCellValue(servicesPojo.getAvailabilities_type());
                row.createCell(6).setCellValue(servicesPojo.getAttendants_number());
                row.createCell(7).setCellValue(servicesPojo.getFlag());
                row.createCell(8).setCellValue(servicesPojo.getDescription());
//                row.createCell(2).setCellValue(currency.getCurrencyDescription());
//                row.createCell(3).setCellValue(unitOfMeasurementPojo.getUnitOfMeasurement());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }





    @Transactional
    public void downloadStatePdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableState(searchText);
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public PdfPTable createFirstTableState(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 3);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("State", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("State Name", font));
        PdfPCell pc3 = new PdfPCell(new Phrase("Country Name", font));
        PdfPCell pc4 = new PdfPCell(new Phrase("Status", font));
        pc2.setBackgroundColor(myColor);
        pc3.setBackgroundColor(myColor);
        pc4.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        table.addCell(pc4);
        List<StateDTO> statePojoList = getStatesList(searchText);
        table.setWidthPercentage(100);
        for (StateDTO statePojo : statePojoList) {
            table.addCell(new Phrase(statePojo.getStateName() + "", font1));
            table.addCell(new Phrase(statePojo.getCountry() + "", font1));
            table.addCell(new Phrase(statePojo.getStatus() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }
    public void downloadStateExcelSheet(OutputStream out, String searchText) {
        try {
            List<StateDTO> statePojoList = getStatesList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("State Name");
            headerRow1.createCell(1).setCellValue("Country Name");
            headerRow1.createCell(2).setCellValue("Status");
            int i = 0;
            for (StateDTO statePojo : statePojoList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(statePojo.getStateName());
                row.createCell(1).setCellValue(statePojo.getCountry());
                row.createCell(2).setCellValue(statePojo.getStatus());
            }

            hwb.write(out);
            out.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }

    @Transactional
    public void downloadCityPdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table,table1;
            table = createFirstTableCity();
            table1 = cityList(searchText);
            document.add(table);
            document.add(table1);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public PdfPTable createFirstTableCity() {
        int a = 0;
        PdfPTable table = new PdfPTable(a + 1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("City", font));
        p.setBackgroundColor(myColor);
        table.addCell(p);
        return table;
    }
    public PdfPTable cityList(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 4);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell pc2 = new PdfPCell(new Phrase("Country", font));
        PdfPCell pc3 = new PdfPCell(new Phrase("State", font));
        PdfPCell pc4 = new PdfPCell(new Phrase("City Name", font));
        PdfPCell pc5 = new PdfPCell(new Phrase("Status", font));
        pc2.setBackgroundColor(myColor);
        pc3.setBackgroundColor(myColor);
        pc4.setBackgroundColor(myColor);
        pc5.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        table.addCell(pc4);
        table.addCell(pc5);
        List<CityDTO> cityPojoList = getCitiesList(searchText);
        for(CityDTO cityPojo:cityPojoList) {
            table.addCell( new Phrase( cityPojo.getCountryName() + "", font1 ) );
            table.addCell( new Phrase( cityPojo.getState() + "", font1 ) );
            table.addCell( new Phrase( cityPojo.getCityName() + "", font1 ) );
            table.addCell( new Phrase( cityPojo.getStatus() + "", font1 ) );
        }
        return table;
    }
    public void downloadCityExcelSheet(OutputStream out, String searchText) {
        try {
            List<CityDTO> cityPojoList = getCitiesList(searchText);
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue("Country");
            headerRow1.createCell(1).setCellValue("State");
            headerRow1.createCell(2).setCellValue("City Name");
            headerRow1.createCell(3).setCellValue("Status");
            int i = 0;
            for (CityDTO cityPojo : cityPojoList) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(cityPojo.getState());
                row.createCell(1).setCellValue(cityPojo.getCountryName());
                row.createCell(2).setCellValue(cityPojo.getCityName());
                row.createCell(3).setCellValue(cityPojo.getStatus());
            }

            hwb.write(out);
            out.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }
    @Transactional
    public void downloadCategoryPdf(OutputStream outputStream) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstTableCategory();
            table.setHeaderRows(1);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            Phrase ph5 = new Phrase("Terms And Condition:Terms                    Prepared By                                        Approved By   ", font1);
            foot.add(ph5);
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public PdfPTable createFirstTableCategory() {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable table = new PdfPTable(a + 2);
        PdfPTable table1 = new PdfPTable(a +1);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("Category", font));
        p.setBackgroundColor(myColor);
        table1.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Name", font));
        PdfPCell pc3 = new PdfPCell(new Phrase("Description", font));
        pc2.setBackgroundColor(myColor);
        pc3.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        List<CategoriesPojo> pojo = categoriesList();
        table.setWidthPercentage(100);
        for (CategoriesPojo categoriesPojo : pojo) {
            table.addCell(new Phrase(categoriesPojo.getName() + "", font1));
            table.addCell(new Phrase(categoriesPojo.getDescription() + "", font1));
        }
        table1.addCell(table);
        return table1;
    }
    public void downloadCategoryExcelSheet(ByteArrayOutputStream outputStream) {
        try {
            List<CategoriesPojo> pojo = categoriesList();
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            HSSFRow headerRow1 = sheet.createRow(0);
            headerRow1.createCell(0).setCellValue(" Name");
            headerRow1.createCell(1).setCellValue(" Description");
            int i = 0;
            for (CategoriesPojo categoriesPojo : pojo) {
                HSSFRow row = sheet.createRow(++i);
                row.createCell(0).setCellValue(categoriesPojo.getName());
                row.createCell(1).setCellValue(categoriesPojo.getDescription());
            }

            hwb.write(outputStream);
            outputStream.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }
    @Transactional
    public void downloadAppointmentPdf(OutputStream outputStream,String searchText) {
        try {
            Font font1 = new Font(getcustomfont(), 12F, Font.BOLD);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Chunk CONNECT = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.BLACK, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT);
            document.add(new Paragraph("", font1));
            Chunk CONNECT1 = new Chunk(new com.lowagie.text.pdf.draw.LineSeparator(1, 100, Color.WHITE, Element.ALIGN_JUSTIFIED, 3f));
            document.add(CONNECT1);
            PdfPTable table = createFirstApnt(searchText);
            document.add(table);
            document.add(CONNECT1);
            Paragraph foot = new Paragraph();
            document.add(foot);
            document.add(CONNECT);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public PdfPTable createFirstApnt(String searchText) {
        Font font1 = new Font(getcustomfont(), 8, Font.NORMAL, Color.BLACK);
        int a = 0;
        PdfPTable tab = new PdfPTable(1);
        Font f = new Font(getcustomfont(), 15, Font.BOLD, Color.WHITE);
        PdfPTable table = new PdfPTable(a + 6);
        Font font = new Font(getcustomfont(), 10, Font.NORMAL, Color.WHITE);
        Color myColor = WebColors.getRGBColor("#326397");
        PdfPCell p = new PdfPCell(new Phrase("AppointmentReport", f));
        p.setBackgroundColor(myColor);
        tab.addCell(p);
        PdfPCell pc2 = new PdfPCell(new Phrase("Name", font));
        pc2.setBackgroundColor(myColor);
        PdfPCell pc3 = new PdfPCell(new Phrase("AppointmentNo", font));
        pc3.setBackgroundColor(myColor);
        PdfPCell pc4 = new PdfPCell(new Phrase("Email", font));
        pc4.setBackgroundColor(myColor);
        PdfPCell pc5 = new PdfPCell(new Phrase("Address", font));
        pc5.setBackgroundColor(myColor);
        PdfPCell pc6 = new PdfPCell(new Phrase("PhoneNumber", font));
        pc6.setBackgroundColor(myColor);
        PdfPCell pc7 = new PdfPCell(new Phrase("AppointmentDate", font));
        pc7.setBackgroundColor(myColor);
        table.addCell(pc2);
        table.addCell(pc3);
        table.addCell(pc4);
        table.addCell(pc5);
        table.addCell(pc6);
        table.addCell(pc7);
        List<AppointmentPojo> appList =appointmentList();
        for (AppointmentPojo list : appList) {
            table.addCell(new Phrase(list.getCustomers().getFirstName() + "", font1));
            table.addCell(new Phrase(list.getAppointmentNo() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getEmail() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getAddress() + "", font1));
            table.addCell(new Phrase(list.getCustomers().getPhoneNumber() + "", font1));
            table.addCell(new Phrase(list.getBook_datetime() + "", font1));
        }
        tab.addCell(table);
        return tab;
    }


    @Transactional
    public void downloadAppointmentExcel(OutputStream out,String searchText) {
        try {
            List<AppointmentPojo> appList =appointmentList();
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("First Sheet");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
            headerRow.createCell(0).setCellValue("AppointmentReport ");
            HSSFRow headerRow1 = sheet.createRow(1);
//            headerRow1.createCell( 0 ).setCellValue( "From Date" );
//            headerRow1.createCell( 1 ).setCellValue( "To Date" );
            headerRow1.createCell(0).setCellValue("Name");
            headerRow1.createCell(1).setCellValue("AppointmentNo");
            headerRow1.createCell(2).setCellValue("Email");
            headerRow1.createCell(3).setCellValue("Address");
            headerRow1.createCell(4).setCellValue("PhoneNo");
            headerRow1.createCell(5).setCellValue("AppointmentDate");
            int i = 1;
            for (AppointmentPojo list : appList) {
                HSSFRow row = sheet.createRow(++i);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                row.createCell( 0 ).setCellValue( list.getCustomers().getFromdate() );
//                row.createCell( 1 ).setCellValue( list.getCustomers().getTodate() );
                row.createCell(0).setCellValue(list.getCustomers().getFirstName());
                row.createCell(1).setCellValue(list.getAppointmentNo());
                row.createCell(2).setCellValue(list.getCustomers().getEmail());
                row.createCell(3).setCellValue(list.getCustomers().getAddress());
                row.createCell(4).setCellValue(list.getCustomers().getPhoneNumber());
                if (list.getBook_datetime() != null) {
                    row.createCell(5).setCellValue(dateFormat.format(list.getBook_datetime()));
                }
                else
                    row.createCell(5).setCellValue("");
            }
            hwb.write(out);
            out.close();
        } catch (Exception gex) {
            gex.printStackTrace();
        }
    }
}

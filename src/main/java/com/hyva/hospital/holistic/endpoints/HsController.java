package com.hyva.hospital.holistic.endpoints;
import com.google.gson.Gson;
import com.hyva.hospital.holistic.entities.*;
import com.hyva.hospital.holistic.pojo.*;
import com.hyva.hospital.holistic.respositories.UserRepository;
import com.hyva.hospital.holistic.service.UserService;
import com.hyva.hospital.holistic.pojo.EntityResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
     * Created by azgar on 3/13/18.
     */
    @RestController
    @RequestMapping("/hospital")
    public class HsController {
//        @Autowired
//        BshimOrdersService bshimOrdersService;
        @Autowired
UserService userService;
        @Autowired
    UserRepository userRepository;
        @RequestMapping(value = "/login",method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public EntityResponse login(@RequestBody Users credentials) throws Exception {
//            User bshimData = bshimUserService.get(credentials) ;
            String accessToken="12345";
            if (StringUtils.isBlank(credentials.getEmail()) ||StringUtils.isBlank(credentials.getFirstName()) || StringUtils.isBlank(credentials.getPassword())) {
                return new EntityResponse(HttpStatus.OK.value(), "Invalid User");
            }
            return new EntityResponse(HttpStatus.OK.value(), "success", credentials);
        }

        @RequestMapping(value = "/saveLoginDetails", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
        public Users saveLoginDetails(@RequestBody UserPojo userPojo)  {
            return userService.saveUserDetails(userPojo);
        }

        @RequestMapping(value = "/userValidate", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
        public Users userValidate(@RequestBody UserPojo userPojo)  {

            return userService.userValidate(userPojo);
        }
//        @RequestMapping(value = "/getUserDetailsList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//        public EntityResponse getUserDetailsList(){
//            List<UserPojo> bsUserPojos = bsUserService.sassUserList();
//            return new EntityResponse(HttpStatus.OK.value(), " success", bsUserPojos);
//        }

    @RequestMapping(value = "/getCategories", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getCategories(){
        List<CategoriesPojo> gradeMasters = userService.categoriesList();
        return new EntityResponse(HttpStatus.OK.value(), " success", gradeMasters);
    }

    @RequestMapping(value = "/getUOMList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getUOMList(){
        List<UnitOfMeasurementPojo> gradeMasters = userService.UOMList();
        return new EntityResponse(HttpStatus.OK.value(), " success", gradeMasters);
    }
    @RequestMapping(value = "/getMedicine", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getMedicine(){
        List<MedicinePojo> gradeMasters = userService.medicineList();
        return new EntityResponse(HttpStatus.OK.value(), " success", gradeMasters);
    }
    @RequestMapping(value = "/getCustomers", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getCustomers(){
        List<CustomersPojo> userPojos = userService.customerList();
        return new EntityResponse(HttpStatus.OK.value(), " success", userPojos);
    }
//    @RequestMapping(value = "/getAdmins", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    public EntityResponse getAdmins(){
//        List<AdminsPojo> gradeMasters = bsUserService.adminsList();
//        return new EntityResponse(HttpStatus.OK.value(), " success", gradeMasters);
//    }

    @RequestMapping(value = "/getStateListBasedOnCountry", method = RequestMethod.POST)
    public ResponseEntity getStateListBasedOnCountry(@RequestParam(value = "countryName") String country) {
        return ResponseEntity.status(200).body(userService.getStateListBasedOnCountry(country));
    }

//    @RequestMapping(value = "/getUOMListBasedOnMedicine", method = RequestMethod.POST)
//    public ResponseEntity getUOMListBasedOnMedicine(@RequestParam(value = "medicineName") String medicine) {
//        return ResponseEntity.status(200).body(userService.getUOMListBasedOnMedicine(medicine));
//    }

    @RequestMapping(value = "/getCityListBasedOnState", method = RequestMethod.POST)
    public ResponseEntity getCityListBasedOnState(@RequestParam(value = "stateName") String state) {
        return ResponseEntity.status(200).body(userService.getCityListBasedOnState(state));
    }
    @RequestMapping(value = "/getPatientByName", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPatientByName(@RequestParam(value = "name", required = false) String name) {
            return ResponseEntity.status(200).body(userService.getPatientByName(name));
    }

    @RequestMapping(value = "/getCountryList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getCountryList(@RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.status(200).body(userService.getCountryList(type));
    }
    @RequestMapping(value = "/saveCountry", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveCountry(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.status(200).body(userService.saveCountry(countryDTO));
    }

    @RequestMapping(value = "/deleteCountry", method = RequestMethod.POST, produces = "application/json")
    public void deleteCountry(@RequestParam(value = "countryName", required = false) String countryName) {
        userService.deleteCountry(countryName);
    }

    @RequestMapping(value = "/editCountry", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editCountry(@RequestParam(value = "countryName", required = false) String countryName) {
        return ResponseEntity.status(200).body(userService.editCountry(countryName));
    }
    @RequestMapping(value = "/getStateList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getStateList(@RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "searchText",required = false) String searchText,
                                       @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getStateList(type,basePojo,searchText));
    }

    @RequestMapping(value = "/saveState", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveState(@RequestBody StateDTO state) {
        return ResponseEntity.status(200).body(userService.saveState(state));
    }

    @RequestMapping(value = "/deleteState", method = RequestMethod.POST, produces = "application/json")
    public void deleteState(@RequestParam(value = "stateName", required = false) String  stateName) {
        userService.deleteState(stateName);
    }
    @RequestMapping(value = "/getCityList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getCityList(@RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "searchText",required = false) String searchText,
                                       @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getCityList(type,basePojo,searchText));
    }

    @RequestMapping(value = "/saveCity", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveCity(@RequestBody CityDTO city) {
        return ResponseEntity.status(200).body(userService.saveCity(city));
    }

    @RequestMapping(value = "/saveplusCity", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveplusCity(@RequestBody CityDTO city) {
        return ResponseEntity.status(200).body(userService.saveplusCity(city));
    }
//    @RequestMapping(value = "/deleteCity/{}", method = RequestMethod.POST, produces = "application/json")
//    public void deleteCity(@RequestParam(value = "cityName", required = false) String  cityName) {
//        userService.deleteCity(cityName);
//    }

    @RequestMapping(value = "/getCurrencyList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getCurrencyList(@RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.status(200).body(userService.getCurrencyList(type));
    }
    @RequestMapping(value = "/saveCurrency", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return ResponseEntity.status(200).body(userService.saveCurrency(currencyDTO));
    }

    @RequestMapping(value = "/deleteCurrency", method = RequestMethod.POST, produces = "application/json")
    public void deleteCurrency(@RequestParam(value = "currencyName", required = false) String currencyName) {
        userService.deleteCurrency(currencyName);
    }

//    @RequestMapping(value = "/deleteScheduler", method = RequestMethod.POST, produces = "application/json")
//    public void deleteScheduler(@RequestParam(value = "firstName", required = false) String firstName) {
//        userService.deleteScheduler(firstName);
//    }
    @RequestMapping(value = "/editCurrency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editCurrency(@RequestParam(value = "currencyName", required = false) String currencyName) {
        return ResponseEntity.status(200).body(userService.editCurrency(currencyName));
    }
    @RequestMapping(value = "/editState", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editState(@RequestParam(value = "stateName", required = false) String  stateName) {
        return ResponseEntity.status(200).body(userService.editState(stateName));
    }

    @RequestMapping(value = "/editCity", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editCity(@RequestParam(value = "cityName", required = false) String  cityName) {
        return ResponseEntity.status(200).body(userService.editCity(cityName));
    }


    @RequestMapping(value = "/saveMedicineDetails",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveMedicineDetails(@RequestBody MedicinePojo medicinePojo ) throws Exception  {
        Medicine master = null;
        master =  userService.SaveMedicine(medicinePojo);
        return ResponseEntity.status(200).body(master);
    }
//    @RequestMapping(value = "/savePrescriptionDetails",method = RequestMethod.POST,consumes ="application/json")
//    public ResponseEntity savePrescriptionDetails(@RequestBody PrescriptionPojo prescriptionPojo ) throws Exception  {
//        Prescription master = null;
//        master =  userService.savePrescription(prescriptionPojo);
//        return ResponseEntity.status(200).body(master);
//    }
    @RequestMapping(value = "/saveNewCategory",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewCategory(@RequestBody CategoriesPojo categoriesPojo ) throws Exception  {
        ServiceCategories master = null;
        master =  userService.SaveCategories(categoriesPojo);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/getFormSetup",method = RequestMethod.POST)
    public ResponseEntity getFormSetup1() throws Exception  {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("message",userService.getFormSetUpNo());
        return ResponseEntity.status(200).body(objectNode.toString());
    }

    @RequestMapping(value = "/saveConsultationDetails",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveConsultationDetails(@RequestBody ConsultationPojo consultationPojo ) throws Exception  {
        Consultation master = null;
        master =  userService.SaveConsultation(consultationPojo);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/saveMedplus",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveMedplus(@RequestBody MedPlusPojo medPlusPojo ) throws Exception  {
        Medplus master = null;
        master =  userService.SaveMedplus(medPlusPojo);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/saveNotes",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNotes(@RequestBody NotesPojo notesPojo ) throws Exception  {
        Notes master = null;
        master =  userService.SaveNotes(notesPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveNewUOM",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewUOM(@RequestBody UnitOfMeasurementPojo unitOfMeasurementPojo ) throws Exception  {
        UnitOfMeasurement master = null;
        master =  userService.SaveNewUOM(unitOfMeasurementPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveNewCustomer",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewCustomer(@RequestBody CustomersPojo customersPojo ) throws Exception  {
        Customers master = null;
        master =  userService.SaveCustomers(customersPojo);
        return ResponseEntity.status(200).body(master);
    }


    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveStudent(@RequestBody Studentpojo studentpojo) {
        return ResponseEntity.status(200).body(userService.saveStudent(studentpojo));
    }

    @RequestMapping(value = "/saveCurrentUserDetails",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveCurrentUserDetails(@RequestBody UserPojo userPojo ) throws Exception  {
        Users master = null;
        master =  userService.SaveCurrentUser(userPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveGeneral",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveGeneral(@RequestBody GeneralSettingsPojo generalSettingsPojo) throws Exception  {
        Settings master = null;
        master =  userService.SaveGeneralSettings(generalSettingsPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveNewBreaks",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewBreaks(@RequestBody BreaksPojo breaksPojo) throws Exception  {
        Breaks master = null;
        master =  userService.saveNewBreaks(breaksPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveNewWorkingPlan",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewWorkingPlan(@RequestBody WorkingPlanPojo workingPlanPojo) throws Exception  {
        WorkingPlan master = null;
        master =  userService.saveNewWorkingPlan(workingPlanPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/saveNewAdmin",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewAdmin(@RequestBody AdminsPojo adminsPojo ) throws Exception  {
        Users master = null;
        master =  userService.SaveAdmins(adminsPojo);
        return ResponseEntity.status(200).body(master);
    }
    @RequestMapping(value = "/getWorkingPlanList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getWorkingPlanList(){
        List<WorkingPlanPojo> getWorkingPlanList = userService.getWorkingPlanList();
        return new EntityResponse(HttpStatus.OK.value(), " success", getWorkingPlanList);
    }
    @RequestMapping(value = "/getBreaksList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getBreaksList(){
        List<BreaksPojo> getBreaksList = userService.breaksList();
        return new EntityResponse(HttpStatus.OK.value(), " success", getBreaksList);
    }
    @RequestMapping(value = "/getAdminsList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getAdminsList(){
        List<AdminsPojo> servicesList = userService.adminsList();
        return new EntityResponse(HttpStatus.OK.value(), " success", servicesList);
    }
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getCurrentUser(){
        Users userPojos = userService.getCurrentUserList();
        return new EntityResponse(HttpStatus.OK.value(), " success", userPojos);
    }
    @RequestMapping(value = "/getPatient", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getPatient(){
        Patient patient = userService.getPatientList();
        return new EntityResponse(HttpStatus.OK.value(), " success", patient);
    }
    @RequestMapping(value = "/getGeneralSettingsDetailsList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getGeneralSettingsDetailsList(){
        Settings generalSettingsPojoList = userService.generalSettingsList();
        return new EntityResponse(HttpStatus.OK.value(), " success", generalSettingsPojoList);
    }
    @RequestMapping(value = "/saveNewAppointment",method = RequestMethod.POST,consumes ="application/json")
         public ResponseEntity saveNewAppointment(@RequestBody AppointmentPojo appointmentPojo ) throws Exception  {
            Appointments master = null;
        master =  userService.SaveAppointment(appointmentPojo);
        return ResponseEntity.status(200).body(appointmentPojo);
    }
    @RequestMapping(value = "/saveOldAppointments",method = RequestMethod.POST,consumes ="application/json")
         public ResponseEntity saveOldAppointments(@RequestBody AppointmentPojo appointmentPojo ) throws Exception  {
            Appointments master = null;
        master =  userService.saveOldAppointments(appointmentPojo);
        return ResponseEntity.status(200).body(appointmentPojo);
    }
    @RequestMapping(value = "/getServices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getServices(){
        List<ServicesPojo> servicesList = userService.servicesList();
        return new EntityResponse(HttpStatus.OK.value(), " success", servicesList);
    }
    @RequestMapping(value = "/getAppointment", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse getAppointment(){
        List<AppointmentPojo> appointmentList = userService.appointmentList();
        return new EntityResponse(HttpStatus.OK.value(), " success", appointmentList);
    }
    @RequestMapping(value = "/saveNewServices",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewServices(@RequestBody ServicesPojo servicesPojo ) throws Exception  {
        Services master = null;
        master =  userService.SaveServices(servicesPojo);
        return ResponseEntity.status(200).body(master);
    }

//    @RequestMapping(value = "/saveInvoice",method = RequestMethod.POST,consumes ="application/json")
//    public ResponseEntity saveInvoice(@RequestBody InvoicePojo invoicePojo ) throws Exception  {
//        Invoice master = null;
//        master =  userService.SaveInvoice(invoicePojo);
//        return ResponseEntity.status(200).body(master);
//    }

//    @RequestMapping(value = "/saveCurrency", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public ResponseEntity saveCurrency(@RequestBody CurrencyDTO currencyDTO) {
//        return ResponseEntity.status(200).body(userService.saveCurrency(currencyDTO));
//    }

    @RequestMapping(value = "/deleteServices", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteServices(@RequestBody ServicesPojo servicesPojo) {
        return ResponseEntity.status(200).body(userService.deleteServices(servicesPojo));
    }

    @RequestMapping(value = "/deleteappointment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity deleteappointment(@RequestParam(value = "id") Long id) {
        userService.deleteappointment(id);
        return ResponseEntity.status(200).body(null);

    }
   @RequestMapping(value = "/getallocatedTimeSlots", method = RequestMethod.POST,produces = "application/json")
   public ResponseEntity getallocatedTimeSlots(@RequestParam(value = "date")String  date) throws ParseException {
       SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z");
       Date formattedDate = formatter.parse(date.toString());
       return ResponseEntity.status(200).body(userService.getallocatedTimeSlots(new java.sql.Date(formattedDate.getTime() )));
   }

    @RequestMapping(value = "/deleteCategories", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteCategories(@RequestBody CategoriesPojo categoryPojo) {
        return ResponseEntity.status(200).body(userService.deleteCategoriesObj(categoryPojo));
    }
    @RequestMapping(value = "/deleteScheduler", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteScheduler(@RequestBody SchedulerPojo schedulerPojo) {
        return ResponseEntity.status(200).body(userService.deleteScheduler(schedulerPojo));
    }

    @RequestMapping(value = "/deleteDetails", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteDetails(@RequestBody MedicinePojo medicinePojo) {
        return ResponseEntity.status(200).body(userService.deleteMedicine(medicinePojo));
    }

    @RequestMapping(value = "/deleteWorkingPlan", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteWorkingPlan(@RequestBody WorkingPlanPojo workingPlanPojo) {
        return ResponseEntity.status(200).body(userService.deleteWorkingPlan(workingPlanPojo));
    }

    @RequestMapping(value = "/deleteBreaks", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteBreaks(@RequestBody BreaksPojo breaksPojo) {
        return ResponseEntity.status(200).body(userService.deleteBreaks(breaksPojo));
    }

    @RequestMapping(value = "/deleteProviders", method = RequestMethod.POST)
    public ResponseEntity deleteProviders(@RequestParam(value = "id") Long providersId) {
        return ResponseEntity.status(200).body(userService.deleteProviders(providersId));
    }


    @RequestMapping(value = "/providersList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResponse providersList(){
        List<ProvidersPojo> providersList = userService.providersdetailsList();
        return new EntityResponse(HttpStatus.OK.value(), " success", providersList);
    }

    @RequestMapping(value = "/saveProviderDetails",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewProviders(@RequestBody ProvidersPojo providersPojo ) throws Exception  {
        ServicesProviders master = null;
        master =  userService.SaveProvidersdetails(providersPojo);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/saveNewPatient",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveNewPatient(@RequestBody patientPojo patientPojo ) throws Exception  {
        Patient master = null;
        master =  userService.savePatient(patientPojo);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/saveServiceInv",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity saveInvoice(@RequestParam(value="appointmentId",required = false)Long appointmentId,
            @RequestBody List<InvoiceListPojo>  serviceinv) throws Exception  {
        Gson gson=new Gson();
        Invoice master = null;
        master =  userService.saveInvoice(gson.toJson(serviceinv),appointmentId);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/savePrescription",method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity savePrescription(@RequestParam(value="appointmentId",required = false)Long appointmentId,
                                           @RequestParam(value="patientName" ,required = false)String patientName,
                                           @RequestBody List<MedicineListPojo>  prescription) throws Exception  {
        Gson gson=new Gson();
        Prescription master = null;
        master =  userService.savePrescription(appointmentId,gson.toJson(prescription),patientName);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/saveformsetup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveformsetup(@RequestBody FormsetupDTO formsetupDTO) {
        return ResponseEntity.status(200).body(userService.saveFormSetup(formsetupDTO));
    }

    @RequestMapping(value = "/editFormSetupMethod", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editFormSetupMethod(@RequestParam(value = "typeName", required = false) String typeName) {
        return ResponseEntity.status(200).body(userService.editFormsetupMethod(typeName));
    }

    @RequestMapping(value = "/getFormsetupList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getFormsetupList() {
        return ResponseEntity.status(200).body(userService.getFormSetupList());
    }
    @RequestMapping(value = "/getPaginatedFormsetupList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedFormSetupList(@RequestParam(value = "searchText")String searchText,
                                                    @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedFormSetUpList(basePojo,searchText));
    }

    @RequestMapping(value = "/getFormSetup", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getFormSetup(@RequestParam(value = "type", required = false) String  type) {
        return ResponseEntity.status(200).body(userService.getFormSetup(type));
    }

    @RequestMapping(value = "/getCountryState", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getCountryState(@RequestParam(value = "countryName", required = false) String countryName) {
        return ResponseEntity.status(200).body(userService.getCountryState(countryName));
    }

    @RequestMapping(value = "/getMedicineUom", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getMedicineUom(@RequestParam(value = "medicineName", required = false) String medicineName) {
        return ResponseEntity.status(200).body(userService.getMedicineUom(medicineName));
    }
    @RequestMapping(value = "/getStateCity", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getStateCity(@RequestParam(value = "stateName", required = false) String stateName) {
        return ResponseEntity.status(200).body(userService.getStateCity(stateName));
    }

    @RequestMapping(value = "/getPrescription", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPrescription(@RequestParam(value = "id", required = false) Long appointmentId) {
        return ResponseEntity.status(200).body(userService.getPrescription(appointmentId));
    }

    @RequestMapping(value = "/getInvoiceList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getInvoiceList(@RequestParam(value = "id") Long id) throws Exception  {
        List<InvoiceListPojo> master = null;
        master =  userService.invoiceList(id);
        return ResponseEntity.status(200).body(master);
    }

    @RequestMapping(value = "/getRemarksList",method = RequestMethod.POST)
    public ResponseEntity getRemarksList(@RequestParam(value = "id") Long id) throws Exception  {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("message",userService.remarksList(id));
        return ResponseEntity.status(200).body(objectNode.toString());
    }

//    @RequestMapping(value = "/getInvoice", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity getInvoice(@RequestParam(value = "id", required = false) Long id) {
//        return ResponseEntity.status(200).body(userService.getInvoice(id));
//    }
    @RequestMapping(value = "/getInvoiceDetails", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getInvoiceDetails(@RequestParam(value = "appointmentId", required = false) Long appointmentId) {
        return ResponseEntity.status(200).body(userService.getInvoiceDetails(appointmentId));
    }
//    @RequestMapping(value = "/getPrescriptionDetails", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity getPrescriptionDetails(@RequestParam(value = "appointmentId", required = false) Long appointmentId) {
//        return ResponseEntity.status(200).body(userService.getPrescriptionDetails(appointmentId));
//    }
   @RequestMapping(value = "/getPaginatedCountryList", method = RequestMethod.POST, produces = "application/json")
   public ResponseEntity getPaginatedCountryList(@RequestParam(value = "type", required = false) String type,
                                                 @RequestParam(value = "searchText",required = false) String searchText,
                                                 @RequestBody BasePojo basePojo) {
     return ResponseEntity.status(200).body(userService.getPaginatedCountryList(type,basePojo,searchText));
    }

    @RequestMapping(value = "/getPaginatedCategoryList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedCategoryList(@RequestParam(value = "searchText",required = false) String searchText,
                                                  @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedCategoryList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedCurrencyList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedCurrencyList(@RequestParam(value = "searchText",required = false) String searchText,
                                                  @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedCurrencyList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedServicesList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedServicesList(@RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedServicesList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedMedicineList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedMedicineList(@RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedMedicineList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedProvidersList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedProvidersList(@RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedProvidersList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedUomList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedUomList(@RequestParam(value = "searchText",required = false) String searchText,
                                                    @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedUomList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedMailList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedMailList() {
        return ResponseEntity.status(200).body(userService.getMailList());
        }
    @RequestMapping(value = "/editMail", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editMail(@RequestParam(value = "userName", required = false)String  userName) {
        return ResponseEntity.status(200).body(userService.editMail(userName));
    }
    @RequestMapping(value = "/deleteMail", method = RequestMethod.POST, produces = "application/json")
    public void deleteMail(@RequestParam(value = "userName", required = false) String userName) {
        userService.deleteMail(userName);
    }
    @RequestMapping(value = "/saveMail", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveMail(@RequestBody MailDTO saveMailDetails) {
        MailDTO camDTO = null;
        camDTO = userService.createSaveMailDetails(saveMailDetails);
        return ResponseEntity.status(200).body(camDTO);
    }
    @RequestMapping(value = "/saveScheduler", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveScheduler(@RequestBody MailSchedulerData mailSchedulerData)throws Exception {
        userService.saveMailSchedule(mailSchedulerData);
        return ResponseEntity.status(HttpStatus.OK).body(mailSchedulerData  );
    }
    @RequestMapping(value = "/getPaginatedWorkPlanList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedWorkPlanList(@RequestParam(value = "searchText",required = false) String searchText,
                                                  @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedWorkPlanList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedBreaksList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedBreaksList(@RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedBreaksList(basePojo,searchText));
    }
    @RequestMapping(value = "/getPaginatedAppointmentList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedAppointmentList(@RequestParam(value = "searchText",required = false) String searchText,
                                                 @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedAppointmentList(basePojo,searchText));
    }

    @RequestMapping(value = "/getPaginatedEmployeeList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedEmployeeList(@RequestParam(value = "type", required = false) String type,
                                                   @RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedEmployeeList(type,basePojo,searchText));
    }

    @RequestMapping(value = "/getPaginatedSchedulerList", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getPaginatedSchedulerList(@RequestParam(value = "type", required = false) String type,
                                                   @RequestParam(value = "searchText",required = false) String searchText,
                                                   @RequestBody BasePojo basePojo) {
        return ResponseEntity.status(200).body(userService.getPaginatedSchedularList(type,basePojo,searchText));
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(200).body(userService.saveEmployee(employeeDTO));
    }

    @RequestMapping(value = "/saveSchedulerDetails", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity saveSchedulerDetails(@RequestBody SchedulerPojo schedulerPojo) {
        return ResponseEntity.status(200).body(userService.saveScheduler(schedulerPojo));
    }

    @RequestMapping(value = "/getFormSetupValue",method = RequestMethod.POST)
    public ResponseEntity getFormSetup() throws Exception  {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("message",userService.getFormSetUpNo());
        return ResponseEntity.status(200).body(objectNode.toString());
    }

    @RequestMapping(value = "/countryImportSave" ,method = RequestMethod.POST)
    public ResponseEntity countryImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
          XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell countryName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell status = row.getCell(1);
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setCountryName(countryName == null ? null : countryName.toString());
                    countryDTO.setStatus(status == null ? "Active" : status.toString());
                    userService.saveCountry(countryDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/appointmentImportSave" ,method = RequestMethod.POST)
    public ResponseEntity appointmentImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null&&row.getPhysicalNumberOfCells()>0) {
//                    org.apache.poi.ss.usermodel.Cell uhid = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell services = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell providers = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell book_datetime = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell firstName = row.getCell(3);
                    org.apache.poi.ss.usermodel.Cell lastName = row.getCell(4);
                    org.apache.poi.ss.usermodel.Cell Email = row.getCell(5);
                    org.apache.poi.ss.usermodel.Cell phoneNumber = row.getCell(6);
                    org.apache.poi.ss.usermodel.Cell gender = row.getCell(7);
                    org.apache.poi.ss.usermodel.Cell Age = row.getCell(8);
                    org.apache.poi.ss.usermodel.Cell occupation = row.getCell(9);
                    org.apache.poi.ss.usermodel.Cell address = row.getCell(10);
                    org.apache.poi.ss.usermodel.Cell Country = row.getCell(11);
                    org.apache.poi.ss.usermodel.Cell State = row.getCell(12);
                    org.apache.poi.ss.usermodel.Cell City = row.getCell(13);
                    org.apache.poi.ss.usermodel.Cell Zipcode = row.getCell(14);
                    org.apache.poi.ss.usermodel.Cell status = row.getCell(15);
                    org.apache.poi.ss.usermodel.Cell cheifComplaints = row.getCell(16);
                    org.apache.poi.ss.usermodel.Cell history = row.getCell(17);
                    org.apache.poi.ss.usermodel.Cell notes = row.getCell(18);
                    AppointmentPojo appointmentPojo = new AppointmentPojo();
                    String uhid=userService.getFormSetUpNo();
//                    appointmentPojo.setServices(services  == null ? null : services.toString());
                    appointmentPojo.setProviderName(providers  == null ? null : providers.toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    Date formattedDate = formatter.parse(book_datetime.toString());
                    appointmentPojo.setBook_datetime(book_datetime == null ? null : new java.sql.Date( formattedDate.getTime() ));
                    appointmentPojo.setUhid(uhid  == null ? null : uhid.toString());
                    appointmentPojo.setFirstName(firstName  == null ? null : firstName.toString());
                    appointmentPojo.setLastName(lastName  == null ? null : lastName .toString());
                    appointmentPojo.setEmail(Email == null ? null : Email.toString());
                    appointmentPojo.setPhoneNumber(phoneNumber == null ? null : new java.text.DecimalFormat("0").format( phoneNumber.getNumericCellValue()));
                    appointmentPojo.setGender(gender == null ? null : gender.toString());
                    appointmentPojo.setAge(Age == null ? null : new java.text.DecimalFormat("0").format( Age.getNumericCellValue()));
                    appointmentPojo.setOccupation(occupation == null ? null : occupation.toString());
                    appointmentPojo.setAddress(address == null ? null : address.toString());
                    appointmentPojo.setCountryName(Country == null ? null : Country.toString());
                    appointmentPojo.setStateName(State == null ? null : State.toString());
                    appointmentPojo.setCity(City == null ? null : City.toString());
                    appointmentPojo.setZipCode(Zipcode == null ? null : new java.text.DecimalFormat("0").format( Zipcode.getNumericCellValue()));
                    appointmentPojo.setStatus(status == null ? null : status.toString());
                    appointmentPojo.setCheifComplaints(cheifComplaints == null ? null : cheifComplaints.toString());
                    appointmentPojo.setHistory(history == null ? null : history.toString());
                    appointmentPojo.setNotes(notes == null ? null : notes.toString());
                    userService.SaveAppointment(appointmentPojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/providersImportSave" ,method = RequestMethod.POST)
    public ResponseEntity providersImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {

                    org.apache.poi.ss.usermodel.Cell firstName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell lastName = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell Email = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell phoneNumber = row.getCell(3);
                    org.apache.poi.ss.usermodel.Cell mobileNumber = row.getCell(4);
                    org.apache.poi.ss.usermodel.Cell Country = row.getCell(5);
                    org.apache.poi.ss.usermodel.Cell State = row.getCell(6);
                    org.apache.poi.ss.usermodel.Cell City = row.getCell(7);
                    org.apache.poi.ss.usermodel.Cell Zipode = row.getCell(8);
//                    org.apache.poi.ss.usermodel.Cell Calender = row.getCell(11);
                    org.apache.poi.ss.usermodel.Cell username = row.getCell(11);
                    org.apache.poi.ss.usermodel.Cell password = row.getCell(11);
//                    org.apache.poi.ss.usermodel.Cell gender = row.getCell(4);
//                    org.apache.poi.ss.usermodel.Cell Age = row.getCell(5);
/*                    org.apache.poi.ss.usermodel.Cell occupation = row.getCell(6);*/
                    org.apache.poi.ss.usermodel.Cell address = row.getCell(9);
//                    org.apache.poi.ss.usermodel.Cell maritalStatus = row.getCell(12);
                    org.apache.poi.ss.usermodel.Cell notes = row.getCell(10);

                    ProvidersPojo providersPojo = new ProvidersPojo();
//                    appointmentPojo.setUhid(UHID== null ? null : UHID.toString());
                    providersPojo.setFirstName(firstName  == null ? null : firstName.toString());
                    providersPojo.setLastName(lastName  == null ? null : lastName .toString());
                    providersPojo.setEmail(Email == null ? null : Email.toString());
                    providersPojo.setPhoneNumber(phoneNumber == null ? null : new java.text.DecimalFormat("0").format( phoneNumber.getNumericCellValue()));
                    providersPojo.setMobileNumber(mobileNumber == null ? null : new java.text.DecimalFormat("0").format( mobileNumber.getNumericCellValue()));
                    providersPojo.setCountryName(Country == null ? null : Country.toString());
                    providersPojo.setState(State == null ? null : State.toString());
                    providersPojo.setCity(City == null ? null : City.toString());
                    providersPojo.setZipCode(Zipode == null ? null : Zipode.toString());
//                    appointmentPojo.setZipCode(Calender == null ? null : Calender.toString());
                    providersPojo.setUsername(username == null ? null : username.toString());
                    providersPojo.setPassword(password == null ? null : password.toString());
//                    Users users=userRepository.findByUsername()
//                    appointmentPojo.setGender(gender == null ? null : gender.toString());
//                    appointmentPojo.setAge(Age == null ? null : Age.toString());
//                   appointmentPojo.setOccupation(occupation == null ? null : occupation.toString());
                    providersPojo.setAddress(address == null ? null : address.toString());
//                    appointmentPojo.setm(Marital Status == null ? null : Marital Status.toString());
                    providersPojo.setNotes(notes == null ? null : notes.toString());
                    userService.SaveProvidersdetails(providersPojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/schedulerImportSave" ,method = RequestMethod.POST)
    public ResponseEntity schedulerImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null&&row.getPhysicalNumberOfCells()>0) {
                    org.apache.poi.ss.usermodel.Cell firstName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell lastName = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell email = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell mobileNumber = row.getCell(3);
                    org.apache.poi.ss.usermodel.Cell gender = row.getCell(4);
                    org.apache.poi.ss.usermodel.Cell age = row.getCell(5);
                    org.apache.poi.ss.usermodel.Cell book_datetime = row.getCell(6);
                    org.apache.poi.ss.usermodel.Cell occupation = row.getCell(7);
                    org.apache.poi.ss.usermodel.Cell address = row.getCell(8);
                    org.apache.poi.ss.usermodel.Cell country = row.getCell(9);
                    org.apache.poi.ss.usermodel.Cell state = row.getCell(10);
                    org.apache.poi.ss.usermodel.Cell city = row.getCell(11);
                    org.apache.poi.ss.usermodel.Cell zipCode = row.getCell(12);
                    org.apache.poi.ss.usermodel.Cell maritalStatus = row.getCell(13);
                    org.apache.poi.ss.usermodel.Cell notes = row.getCell(14);
//                    org.apache.poi.ss.usermodel.Cell mobileNumber = row.getCell(8);
//                    org.apache.poi.ss.usermodel.Cell education = row.getCell(10);
                    SchedulerPojo schedulerPojo = new SchedulerPojo();
                    schedulerPojo.setFirstName(firstName == null ? null : firstName.toString());
                    schedulerPojo.setLastName(lastName == null ? "Active" : lastName.toString());
                    schedulerPojo.setEmail(email == null ? "Active" : email.toString());
                    schedulerPojo.setMobileNumber(mobileNumber == null ? null :new java.text.DecimalFormat("0").format( mobileNumber.getNumericCellValue()));

//                    schedulerPojo.setAlternativeNumber(alternativeNumber == null ? "Active" : alternativeNumber.toString());
                    schedulerPojo.setGender(gender == null ? "Active" : gender.toString());
                    schedulerPojo.setAge(age == null ? null : new java.text.DecimalFormat("0").format( age.getNumericCellValue()));

//                    schedulerPojo.setAge(age == null ? "Active" : age.toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    Date formattedDate = formatter.parse(book_datetime.toString());
                    schedulerPojo.setBook_datetime(book_datetime == null ? null : new java.sql.Date( formattedDate.getTime() ));
                    schedulerPojo.setOccupation(occupation == null ? "Active" : occupation.toString());
                    schedulerPojo.setAddress(address == null ? "Active" : address.toString());
                    schedulerPojo.setCountry(country == null ? "Active" : country.toString());
                    schedulerPojo.setState(state == null ? "Active" : state.toString());
                    schedulerPojo.setCity(city == null ? "Active" : city.toString());
                    schedulerPojo.setZipCode(zipCode == null ? null : new java.text.DecimalFormat("0").format( zipCode.getNumericCellValue()));

                    schedulerPojo.setMaritalStatus(maritalStatus == null ? "Active" : maritalStatus.toString());
                    schedulerPojo.setNotes(notes == null ? "Active" : notes.toString());
                    schedulerPojo.setStatus("Active");
                    userService.saveScheduler(schedulerPojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/currencyImportSave" ,method = RequestMethod.POST)
    public ResponseEntity currencyImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell currencyName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell currencyCode = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell currencyDescription = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell currencySymbol = row.getCell(3);
                    CurrencyDTO currencyDTO = new CurrencyDTO();
                    currencyDTO.setCurrencyName(currencyName == null ? null : currencyName.toString());
                    currencyDTO.setCurrencyCode(currencyCode == null ? null : currencyCode.toString());
                    currencyDTO.setCurrencyDescription(currencyDescription == null ? null : currencyDescription.toString());
                    currencyDTO.setCurrencySymbol(currencySymbol == null ? null : currencySymbol.toString());
                    userService.saveCurrency(currencyDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/medicineImportSave" ,method = RequestMethod.POST)
    public ResponseEntity medicineImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell medicineName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell medicineCode = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell unitOfMeasurement = row.getCell(2);
                    MedicinePojo medicinePojo = new MedicinePojo();
                    medicinePojo.setMedicineName(medicineName == null ? null : medicineName.toString());
                    medicinePojo.setCode(medicineCode == null ? null : medicineCode.toString());
                    medicinePojo.setUnitOfMeasurement(unitOfMeasurement == null ? null : unitOfMeasurement.toString());
                    userService.SaveMedicine(medicinePojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/uomImportSave" ,method = RequestMethod.POST)
    public ResponseEntity uomImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell uomName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell uomDescription = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell uomStatus = row.getCell(2);
                    UnitOfMeasurementPojo unitOfMeasurementPojo = new UnitOfMeasurementPojo();
                    unitOfMeasurementPojo.setUnitOfMeasurementName(uomName == null ? null : uomName.toString());
                    unitOfMeasurementPojo.setUnitOfMeasurementDescription(uomDescription == null ? null : uomDescription.toString());
                    unitOfMeasurementPojo.setUnitOfMeasurementStatus(uomStatus == null ? null : uomStatus.toString());
                    userService.SaveNewUOM(unitOfMeasurementPojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


//    @RequestMapping(value = "/serviceImportSave" ,method = RequestMethod.POST)
//    public ResponseEntity serviceImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
//        System.out.println(uploadfiles.getOriginalFilename());
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
//                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
//                if(row==null)
//                    break;
//                if(row!=null) {
//                    org.apache.poi.ss.usermodel.Cell serviceName = row.getCell(0);
//                    org.apache.poi.ss.usermodel.Cell Price = row.getCell(1);
//                    org.apache.poi.ss.usermodel.Cell currencyDescription = row.getCell(2);
//                    org.apache.poi.ss.usermodel.Cell currencySymbol = row.getCell(3);
//                    Services services = new Services();
//                    services.setName(serviceName == null ? null : serviceName.toString());
//                    services.setPrice(Price == null ? null : Price.toString());
//                    services.setId_service_categories(currencyDescription == null ? null : currencyDescription.toString());
//                    services.setId(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setFlag(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setDuration(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setDescription(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setCurrency(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setAvailabilitiesType(currencySymbol == null ? null : currencySymbol.toString());
//                    services.setAttendantsNumber(currencySymbol == null ? null : currencySymbol.toString());
//                    userService.SaveServices(services);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @RequestMapping(value = "/stateImportSave" ,method = RequestMethod.POST)
    public ResponseEntity stateImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell countryName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell stateName = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell status = row.getCell(2);
                    StateDTO stateDTO = new StateDTO();
                    stateDTO.setCountry(countryName == null ? null : countryName.toString());
                    stateDTO.setStateName(stateName == null ? null :  stateName.toString());
                    stateDTO.setStatus(status == null ? "Active" : status.toString());
                    userService.saveState(stateDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/cityImportSave" ,method = RequestMethod.POST)
    public ResponseEntity cityImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell countryName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell stateName = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell cityName = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell status = row.getCell(3);
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setCountryName(countryName == null ? null : countryName.toString());
                    cityDTO.setState(stateName == null ? null :  stateName.toString());
                    cityDTO.setCityName(cityName == null ? null :  cityName.toString());
                    cityDTO.setStatus(status == null ? "Active" : status.toString());
                    userService.saveCity(cityDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/categoriesImportSave" ,method = RequestMethod.POST)
    public ResponseEntity categoriesImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null&&row.getPhysicalNumberOfCells()>0) {
                    org.apache.poi.ss.usermodel.Cell name = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell description = row.getCell(1);
                    CategoriesPojo categoriesPojo = new CategoriesPojo();
                    categoriesPojo.setName(name == null ? null : name.toString());
                    categoriesPojo.setDescription(description == null ? null :  description.toString());
                    userService.SaveCategories(categoriesPojo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/employeeImportSave" ,method = RequestMethod.POST)
    public ResponseEntity employeeImportSave(@RequestParam("myFile") MultipartFile uploadfiles) throws Exception {
        System.out.println(uploadfiles.getOriginalFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadfiles.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if(row==null)
                    break;
                if(row!=null) {
                    org.apache.poi.ss.usermodel.Cell empName = row.getCell(0);
                    org.apache.poi.ss.usermodel.Cell empCode = row.getCell(1);
                    org.apache.poi.ss.usermodel.Cell empAddr = row.getCell(2);
                    org.apache.poi.ss.usermodel.Cell empPhone = row.getCell(3);
                    org.apache.poi.ss.usermodel.Cell empDob = row.getCell(4);
                    org.apache.poi.ss.usermodel.Cell empDoj = row.getCell(5);
                    org.apache.poi.ss.usermodel.Cell gender = row.getCell(6);
                    org.apache.poi.ss.usermodel.Cell status = row.getCell(7);
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setEmployeeName(empName == null ? null : empName.toString());
                    employeeDTO.setEmployeeCode(empCode == null ? null :  empCode.toString());
                    employeeDTO.setEmployeeAddr(empAddr == null ? null :  empAddr.toString());
                    employeeDTO.setEmployeePhone(empPhone == null ? null :new java.text.DecimalFormat("0").format( empPhone.getNumericCellValue()));

//                    employeeDTO.setEmployeePhone(empPhone == null ? null :  empPhone.toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    if(empDob!=null) {
                        Date dobDate = formatter.parse( empDob.toString() );
                        Date dojDate = formatter.parse( empDoj.toString() );
                        employeeDTO.setEmployeeDOB( empDob == null ? null : dobDate );
                        employeeDTO.setEmployeeDOJ( empDoj == null ? null : dojDate );
                    }
                    employeeDTO.setGender(gender == null ? null :  gender.toString());
                    employeeDTO.setStatus(status == null ? "Active" :  status.toString());
                    userService.saveEmployee(employeeDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/countryExport", method = RequestMethod.GET)
    public ResponseEntity countryExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadCountryPdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Country.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadCountryExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Country.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }


    @RequestMapping(path = "/appointmentExport", method = RequestMethod.GET)
    public ResponseEntity appointmentExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String searchText)
//                                            @RequestParam(value = "fromDate") String fromdate,
//                                            @RequestParam(value = "toDate") String todate
    {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadAppointmentPdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Appointment.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadAppointmentExcel(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Appointment.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }




    @RequestMapping(path = "/ProvidersExport", method = RequestMethod.GET)
    public ResponseEntity ProvidersExport(@RequestParam(value = "type") String type,
                                            @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadProvidersPdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Providers.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadProvidersReportExcel(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Providers.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }


    @RequestMapping(path = "/currencyExport", method = RequestMethod.GET)
    public ResponseEntity currencyExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String currencySearchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadCurrencyPdf(outputStream,currencySearchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Currency.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadCurrencyExcelSheet(outputStream,currencySearchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Currency.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/medicineExport", method = RequestMethod.GET)
    public ResponseEntity medicineExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadMedicinePdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Medicine.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadMedicineExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Medicine.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/uomExport", method = RequestMethod.GET)
    public ResponseEntity uomExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String UOMSearchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadUomPdf(outputStream,UOMSearchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Uom.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadUomExcelSheet(outputStream,UOMSearchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Uom.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/serviceExport", method = RequestMethod.GET)
    public ResponseEntity serviceExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadServicePdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Service.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadServiceExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Service.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/employeeExport", method = RequestMethod.GET)
    public ResponseEntity employeeExport(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadEmployeePdf(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Employee.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadEmployeeExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Employee.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/StateExport", method = RequestMethod.GET)
    public ResponseEntity StateExport(@RequestParam(value = "type") String type,
                                      @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadStatePdf(outputStream, searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "State.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadStateExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "State.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

    @RequestMapping(path = "/CityExport", method = RequestMethod.GET)
    public ResponseEntity CityExport(@RequestParam(value = "type") String type,
                                     @RequestParam(value = "val") String searchText) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadCityPdf(outputStream, searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "City.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadCityExcelSheet(outputStream,searchText);
            headers.add("Content-Disposition", "attachment; filename=\"" + "City.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }
    @RequestMapping(path = "/categoryExport", method = RequestMethod.GET)
    public ResponseEntity categoryExport(@RequestParam(value = "type") String type) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (StringUtils.equalsIgnoreCase(type, "Pdf")) {
            userService.downloadCategoryPdf(outputStream);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Category.pdf" + "\"");
        } else if (StringUtils.equalsIgnoreCase(type, "Excel")) {
            userService.downloadCategoryExcelSheet(outputStream);
            headers.add("Content-Disposition", "attachment; filename=\"" + "Category.xlsx" + "\"");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(byteArrayResource);
    }

}

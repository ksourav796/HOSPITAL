package com.hyva.hospital.holistic.service;

import com.hyva.hospital.holistic.entities.*;
import com.hyva.hospital.holistic.respositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicDataService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    PosFormSetupRepository posFormSetupRepository;
  @Autowired
  PosStateRepository posStateRepository;
  @Autowired
  CategoriesRepository categoriesRepository;
  @Autowired
  PosCurrencyRepository posCurrencyRepository;
  @Autowired
  CityRepository cityRepository;
    @Autowired
    SmsConfiguratorRepository smsConfiguratorRepository;
    @Autowired
    UOMRepository uomRepository;

    public void insertBasicData() throws Exception {
        //============================================= User ======================================================================
        List<Users> userList = (List<Users>) userRepository.findAll();
        if (userList.isEmpty()) {
            Users userObj = new Users();
            userObj.setEmail("");
            userObj.setFirstName("admin");
            userObj.setUsername("admin");
//            userObj.setPhone("");
//            userObj.setSecurityAnswer("");
//            userObj.setSecurityQuestion("");
//            userObj.setStatus("Active");
            userObj.setPassword("admin");
            userObj.setRetypepassword("admin");
            userObj.setFlagType("admin");
            userObj.setFlag('1');
            userRepository.save(userObj);


        }

    }

   public  void pushBasicData(){
    List<FormSetUp> formSetUpList = posFormSetupRepository.findAll();
    if (formSetUpList.isEmpty()) {
        insertFormSetUp("AppointmentNumber", "HLC", "00100", true, "AR","");
        insertFormSetUp("InvoiceNumber", "INV", "00000", true, "AR","");
    }

    List<Country> countryList = countryRepository.findAll();
    if (countryList.isEmpty()) {
        insertCountryDate("India");
        insertCountryDate("Europe");
        insertCountryDate("Malaysia");
        insertCountryDate("Indonesia");
        insertCountryDate("Singapore");
        insertCountryDate("Thailand");
        insertCountryDate("USA");
        insertCountryDate("England");
        insertCountryDate("Australia");
        insertCountryDate("Srilanka");
        insertCountryDate("Pakistan");
        insertCountryDate("Brunei");
    }

    List<State> stateList = posStateRepository.findAll();
    if (stateList.isEmpty()) {
        insertStateDate("Andra Pradesh","India");
        insertStateDate("Telangana","India");
        insertStateDate("Arunachal Pradesh","India");
        insertStateDate("Assam","India");
        insertStateDate("Bihar","India");
        insertStateDate("Chhattisgarh","India");
        insertStateDate("Goa","India");
        insertStateDate("Gujarat","India");
        insertStateDate("Haryana","India");
        insertStateDate("Himachal Pradesh","India");
        insertStateDate("Jammu and Kashmir","India");
        insertStateDate("Jharkhand","India");
        insertStateDate("Karnataka","India");
        insertStateDate("Kerala","India");
        insertStateDate("Madya Pradesh","India");
        insertStateDate("Maharashtra","India");
        insertStateDate("Manipur","India");
        insertStateDate("Meghalaya","India");
        insertStateDate("Mizoram","India");
        insertStateDate("Nagaland","India");
        insertStateDate("Orissa","India");
        insertStateDate("Punjab","India");
        insertStateDate("Rajasthan","India");
        insertStateDate("Sikkim","India");
        insertStateDate("Tamil Nadu","India");
        insertStateDate("Uttaranchal","India");
        insertStateDate("Uttar Pradesh","India");
        insertStateDate("West Bengal","India");
        insertStateDate("Andaman and Nicobar Islands","India");
        insertStateDate("Chandigarh","India");
        insertStateDate("Dadar and Nagar Haveli","India");
        insertStateDate("Daman and Diu","India");


    }


     List<City> cityList = cityRepository.findAll();
    if (cityList.isEmpty()) {
        insertCityDate("Bengaluru","Karnataka","India");
        insertCityDate("Afzalpur","Karnataka","India");
        insertCityDate("Ainapur","Karnataka","India");
        insertCityDate("Aland","Karnataka","India");
        insertCityDate("Alur","Karnataka","India");
        insertCityDate("Anekal","Karnataka","India");
        insertCityDate("Ankola","Karnataka","India");
        insertCityDate("Arsikere","Karnataka","India");
        insertCityDate("Athani","Karnataka","India");
        insertCityDate("Aurad","Karnataka","India");
        insertCityDate("Bableshwar","Karnataka","India");
        insertCityDate("Badami","Karnataka","India");
        insertCityDate("Bagalkot","Karnataka","India");
        insertCityDate("Bagepalli","Karnataka","India");
        insertCityDate("Bailhongal","Karnataka","India");
        insertCityDate("Bangarpet","Karnataka","India");
        insertCityDate("Bantwal","Karnataka","India");
        insertCityDate("Basavakalyan","Karnataka","India");
        insertCityDate("Basavanabagewadi","Karnataka","India");
        insertCityDate("Basavapatna","Karnataka","India");
        insertCityDate("Belgaum","Karnataka","India");
        insertCityDate("Bellary","Karnataka","India");
        insertCityDate("Belthangady","Karnataka","India");
        insertCityDate("Belur","Karnataka","India");
        insertCityDate("Bhadravati","Karnataka","India");
        insertCityDate("Bhalki","Karnataka","India");
        insertCityDate("Bhatkal","Karnataka","India");
        insertCityDate("Bidar","Karnataka","India");
        insertCityDate("Bijapur","Karnataka","India");
        insertCityDate("Biligi","Karnataka","India");
        insertCityDate("Chadchan","Karnataka","India");
        insertCityDate("Challakere","Karnataka","India");
        insertCityDate("Chamrajnagar","Karnataka","India");
        insertCityDate("Channagiri","Karnataka","India");
        insertCityDate("Channapatna","Karnataka","India");
        insertCityDate("Channarayapatna","Karnataka","India");
        insertCityDate("Chickmagalur","Karnataka","India");
        insertCityDate("Chikballapur","Karnataka","India");
        insertCityDate("Chikkaballapur","Karnataka","India");
        insertCityDate("Chikkanayakanahalli","Karnataka","India");
        insertCityDate("Chikkodi","Karnataka","India");
        insertCityDate("Chikmagalur","Karnataka","India");
        insertCityDate("Chincholi","Karnataka","India");
        insertCityDate("Chittapur","Karnataka","India");
        insertCityDate("Chitradurga","Karnataka","India");
        insertCityDate("Cowdahalli","Karnataka","India");
        insertCityDate("Davanagere","Karnataka","India");
        insertCityDate("Deodurga","Karnataka","India");
        insertCityDate("Devangere","Karnataka","India");
        insertCityDate("Devarahippargi","Karnataka","India");
        insertCityDate("Dharwad","Karnataka","India");
        insertCityDate("Doddaballapur","Karnataka","India");
        insertCityDate("Gadag","Karnataka","India");
        insertCityDate("Gangavathi","Karnataka","India");
        insertCityDate("Gokak","Karnataka","India");
        insertCityDate("Gowribdanpur","Karnataka","India");
        insertCityDate("Gubbi","Karnataka","India");
        insertCityDate("Gulbarga","Karnataka","India");
        insertCityDate("Gundlupet","Karnataka","India");
        insertCityDate(" H.B.Halli","Karnataka","India");
        insertCityDate(" H.D. Kote","Karnataka","India");
        insertCityDate("Haliyal","Karnataka","India");
        insertCityDate("Harapanahalli","Karnataka","India");
        insertCityDate("Hassan","Karnataka","India");
        insertCityDate("Haveri","Karnataka","India");
        insertCityDate("Hebri","Karnataka","India");
        insertCityDate("Hirekerur","Karnataka","India");
        insertCityDate("Hiriyur","Karnataka","India");

    }



    List<ServiceCategories> categoriesList = categoriesRepository.findAll();
    if (categoriesList.isEmpty()) {
        insertCategoryDate("Ayurveda");
        insertCategoryDate("Naturopathy");
        insertCategoryDate("Unani");
        insertCategoryDate("Prophetic Medicine");
        insertCategoryDate("Medicine");
        insertCategoryDate("Yoga");
        insertCategoryDate("Homeopathy");
        insertCategoryDate("Acupuncture");
        insertCategoryDate("Physiotherapy");
        insertCategoryDate("Diet");
        insertCategoryDate("nutrition");
    }


   List<UnitOfMeasurement> uomList = uomRepository.findAll();
    if (uomList.isEmpty()) {
        insertuom("ml");
        insertuom("gm");
        insertuom("no.");
        insertuom("mg");
    }



    List<Currency> currencyList = posCurrencyRepository.findAll();
    if (currencyList.isEmpty()) {
        getCurrencyObject("INR", "Rupee", "Indian Rupee", "Rs");
        getCurrencyObject("MYR", "Ringgit", "Malaysian ringgit", "RM");
        getCurrencyObject("BND", "Brunei Dollar", "Brunei Dollar", "B$");
        getCurrencyObject("IDR", "Ruphiah", "Indonesia Ruphiah", "Rp");
        getCurrencyObject("SGD", "Singapore Dollar", "Singapore Dollar", "S$");
        getCurrencyObject("THB", "Thai Baht", "Thai Baht", "B");
        getCurrencyObject("USD", "US Dollar", "US Dollar", "$");
        getCurrencyObject("EUR", "Euro", "Euro", "€");
        getCurrencyObject("GBP", "Pound", "Pound Sterling", "£");
    }

    }

    public void insertFormSetUp(String typename, String typeprefix, String nextref, boolean include_date, String transactionType,String smsMessage) {
        FormSetUp c = new FormSetUp();
        c.setTypename(typename);
        c.setTypeprefix(typeprefix);
        c.setNextref(nextref);
        c.setTransactionType(transactionType);
        c.setInclude_date(include_date);
        c.setSmsId(addSmsConfigurator(smsMessage,"true"));
        posFormSetupRepository.save(c);
    }
    private Long addSmsConfigurator(String message,String enableSms){
        SmsConfigurator smsConfigurator=new SmsConfigurator();
        smsConfigurator.setMessage(message);
        smsConfigurator.setEnableSms(enableSms);
        smsConfiguratorRepository.save(smsConfigurator);
        return smsConfigurator.getSmsId();
    }

    public void insertCountryDate(String countryName){
        Country country=new Country();
        country.setCountryName(countryName);
        country.setStatus("Active");
        countryRepository.save(country);
    }

    public void insertStateDate(String stateName,String countryName){
        State state=new State();
        state.setStateName(stateName);
        state.setCountryName(countryName);
        state.setStatus("Active");
        posStateRepository.save(state);
    }
   public void insertCityDate(String cityName,String stateName,String countryName){
        City city=new City();
       city.setCityName(cityName);
       city.setStateName(stateName);
       city.setCountryName(countryName);
       city.setStatus("Active");
        cityRepository.save(city);
    }

    public void insertCategoryDate(String Name){
        ServiceCategories serviceCategories =new ServiceCategories();
        serviceCategories.setName(Name);
        categoriesRepository.save(serviceCategories);
    }

    public void insertuom(String Name){
        UnitOfMeasurement unitOfMeasurement=new UnitOfMeasurement();
        unitOfMeasurement.setUnitOfMeasurementName(Name);
        unitOfMeasurement.setUnitOfMeasurementStatus("Active");
        uomRepository.save(unitOfMeasurement);
    }


    public void getCurrencyObject(String currencyCode, String currencyDescription, String currencyName, String currencySymbol) {


        Currency curObj = new Currency();
        curObj.setCurrencyCode(currencyCode);
        curObj.setCurrencyDescription(currencyDescription);
        curObj.setCurrencyName(currencyName);
        curObj.setCurrencySymbol(currencySymbol);
        posCurrencyRepository.save(curObj);
    }

}



















package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.*;
import com.hyva.hospital.holistic.pojo.*;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static Users mapPojoToEntity(UserPojo pojo) {
        Users customer = new Users();
        customer.setId(pojo.getId());
        customer.setFirstName(pojo.getFirstName());
        customer.setLastName(pojo.getLastName());
        customer.setEmail(pojo.getEmail());
        customer.setPhoneNumber(pojo.getPhoneNumber());
        customer.setMobileNumber(pojo.getMobileNumber());
        customer.setAge( pojo.getAge() );
        customer.setCity(pojo.getCity());
        customer.setState(pojo.getState());
        customer.setCountry(pojo.getCountry());
        customer.setUsername(pojo.getUsername());
        customer.setPassword(pojo.getPassword());
        customer.setRetypepassword(pojo.getRetypepassword());
        customer.setAddress(pojo.getAddress());
        customer.setZipCode(pojo.getZipCode());
        customer.setNotes(pojo.getNotes());
        customer.setDate( pojo.getDate() );
        customer.setUhid( pojo.getUhid() );
        customer.setHistory( pojo.getHistory());
        customer.setCheifComplaints( pojo.getCheifComplaints() );
        return customer;
    }
    public static List<ProvidersPojo> mapProvidersEntityToPojo(List<Users> userslist){
        List<ProvidersPojo> list=new ArrayList<>();
        for(Users users:userslist) {
            ProvidersPojo pojo = new ProvidersPojo();
            pojo.setId(users.getId());
            pojo.setFirstName(users.getFirstName());
            pojo.setLastName(users.getLastName());
            pojo.setEmail(users.getEmail());
            pojo.setPhoneNumber(users.getPhoneNumber());
            pojo.setMobileNumber(users.getMobileNumber());
            pojo.setCity(users.getCity());
            pojo.setState(users.getState());
            pojo.setUsername(users.getUsername());
            pojo.setPassword(users.getPassword());
            pojo.setRetypepassword(users.getRetypepassword());
            pojo.setAddress(users.getAddress());
            pojo.setZipCode(users.getZipCode());
            pojo.setNotes(users.getNotes());
            list.add(pojo);
        }
        return list;
    }
    public static List<UnitOfMeasurementPojo> mapUomEntityToPojo(List<UnitOfMeasurement> uomlist){
        List<UnitOfMeasurementPojo> list=new ArrayList<>();
        for(UnitOfMeasurement uom:uomlist) {
            UnitOfMeasurementPojo pojo = new UnitOfMeasurementPojo();
            pojo.setUnitOfMeasurementId(uom.getUnitOfMeasurementId());
            pojo.setUnitOfMeasurementName(uom.getUnitOfMeasurementName());
            pojo.setUnitOfMeasurementDescription(uom.getUnitOfMeasurementDescription());
            list.add(pojo);
        }
        return list;
    }

    public static List<CountryDTO> mapCountryEntityToPojo(List<Country> countryList){
        List<CountryDTO> list=new ArrayList<>();
        for(Country country:countryList) {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryId(country.getCountryId());
            countryDTO.setCountryName(country.getCountryName());
            countryDTO.setStatus(country.getStatus());
            list.add(countryDTO);
        }
        return list;
    }
    public static Country mapCountryPojoToEntity(CountryDTO countryDTO){
        Country country = new Country();
        country.setCountryId(countryDTO.getCountryId());
        country.setCountryName(countryDTO.getCountryName());
        country.setStatus(countryDTO.getStatus());
        return country;
    }

    public static List<Studentpojo> mapStudentEntityToPojo(List<Student> studentList){
        List<Studentpojo> list=new ArrayList<>();
        for(Student student:studentList) {
            Studentpojo studentpojo = new Studentpojo();
            studentpojo.setStudentId(student.getStudentId());
            studentpojo.setStudentName(student.getStudentName());
            studentpojo.setStudentaddress(student.getStudentaddress());
            list.add(studentpojo);
        }
        return list;
    }
    public static Student mapStudentPojoToEntity(Studentpojo studentpojo){
        Student student = new Student();
        student.setStudentId(studentpojo.getStudentId());
        student.setStudentName(studentpojo.getStudentName());
        student.setStudentaddress(studentpojo.getStudentaddress());
        return student;
    }


    public static List<CurrencyDTO> mapCurrencyEntityToPojo(List<Currency> currencyList){
        List<CurrencyDTO> list=new ArrayList<>();
        for(Currency currency:currencyList) {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setCurrencyId(currency.getCurrencyId());
            currencyDTO.setCurrencyCode(currency.getCurrencyCode());
            currencyDTO.setCurrencyDescription(currency.getCurrencyDescription());
            currencyDTO.setCurrencyName(currency.getCurrencyName());
            currencyDTO.setCurrencySymbol(currency.getCurrencySymbol());
            currencyDTO.setStatus(currency.getStatus());
            list.add(currencyDTO);
        }
        return list;
    }
    public static Currency mapCurrencyPojoToEntity(CurrencyDTO currencyDTO){
        Currency currency = new Currency();
        currency.setCurrencyId(currencyDTO.getCurrencyId());
        currency.setCurrencyCode(currencyDTO.getCurrencyCode());
        currency.setCurrencyDescription(currencyDTO.getCurrencyDescription());
        currency.setCurrencyName(currencyDTO.getCurrencyName());
        currency.setCurrencySymbol(currencyDTO.getCurrencySymbol());
        currency.setStatus(currencyDTO.getStatus());
        return currency;
    }

    public static List<StateDTO> mapStateEntityToPojo(List<State> stateList){
        List<StateDTO> list=new ArrayList<>();
        for(State state:stateList) {
            StateDTO stateDTO = new StateDTO();
            stateDTO.setId(state.getId());
            stateDTO.setStateCode(state.getStateCode());
            stateDTO.setStateName(state.getStateName());
            stateDTO.setVehicleSeries(state.getVehicleSeries());
            stateDTO.setStatus(state.getStatus());
            stateDTO.setCountry(state.getCountryName());
            list.add(stateDTO);
        }
        return list;
    }

    public static List<CityDTO> mapCityEntityToPojo(List<City> cityList){
        List<CityDTO> list=new ArrayList<>();
        for(City city:cityList) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(city.getId());
            cityDTO.setCityCode(city.getCityCode());
            cityDTO.setCityName(city.getCityName());
            cityDTO.setStatus(city.getStatus());
            cityDTO.setState(city.getStateName());
            cityDTO.setCountryName(city.getCountryName());
            list.add(cityDTO);
        }
        return list;
    }

    public static State mapStatePojoToEntity(StateDTO stateDTO){
        State state = new State();
        state.setStateCode(stateDTO.getStateCode());
        state.setId(stateDTO.getId());
        state.setStateName(stateDTO.getStateName());
        state.setVehicleSeries(stateDTO.getVehicleSeries());
        state.setStatus(stateDTO.getStatus());
        return state;
    }


    public static City mapCityPojoToEntity(CityDTO cityDTO){
        City city = new City();
        city.setCityCode(cityDTO.getCityCode());
        city.setId(cityDTO.getId());
        city.setCityName(cityDTO.getCityName());
        city.setStatus(cityDTO.getStatus());
        city.setCountryName(cityDTO.getCountryName());
        city.setStateName(cityDTO.getState());
        return city;
    }

    public static List<FormsetupDTO> mapFormSetupEntityToPojo(List<FormSetUp> formSetUpList){
        List<FormsetupDTO> list=new ArrayList<>();
        for(FormSetUp formSetUp:formSetUpList) {
            FormsetupDTO formsetupDTO = new FormsetupDTO();
            formsetupDTO.setNextref(formSetUp.getNextref());
            formsetupDTO.setFormsetupId(formSetUp.getFormsetupId());
            formsetupDTO.setTypename(formSetUp.getTypename());
            formsetupDTO.setTransactionType(formSetUp.getTransactionType());
            formsetupDTO.setTypeprefix(formSetUp.getTypeprefix());
            list.add(formsetupDTO);
        }
        return list;
    }
    public  static FormSetUp mapFormSetupPojoToEntity(FormsetupDTO formsetupDTO){
        FormSetUp formSetUp = new FormSetUp();
        formSetUp.setFormsetupId(formsetupDTO.getFormsetupId());
        formSetUp.setTypeprefix(formsetupDTO.getTypeprefix());
        formSetUp.setNextref(formsetupDTO.getNextref());
        formSetUp.setTypename(formsetupDTO.getTypename());
        formSetUp.setTransactionType(formsetupDTO.getTransactionType());
        return formSetUp;
    }


    public static Employee mapEmployeePojoToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setEmployeeCode(employeeDTO.getEmployeeCode());
        employee.setEmployeeAddr(employeeDTO.getEmployeeAddr());
        employee.setEmployeePhone(employeeDTO.getEmployeePhone());
        employee.setEmployeeDOB(employeeDTO.getEmployeeDOB());
        employee.setEmployeeDOJ(employeeDTO.getEmployeeDOJ());
        employee.setGender(employeeDTO.getGender());
        employee.setImage(employeeDTO.getImage());
        employee.setDeliveryFlag(employeeDTO.isDeliveryFlag());
        employee.setWaiterFlag(employeeDTO.isWaiterFlag());
        employee.setStatus(employeeDTO.getStatus());
        return employee;
    }
    public static List<EmployeeDTO> mapEmployeeEntityToPojo(List<Employee> employeeList){
        List<EmployeeDTO> list=new ArrayList<>();
        for(Employee employee:employeeList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setEmployeeName(employee.getEmployeeName());
            employeeDTO.setEmployeeCode(employee.getEmployeeCode());
            employeeDTO.setEmployeeAddr(employee.getEmployeeAddr());
            employeeDTO.setEmployeePhone(employee.getEmployeePhone());
            employeeDTO.setImage(employee.getImage());
            employeeDTO.setEmployeeDOB(employee.getEmployeeDOB());
            employeeDTO.setEmployeeDOJ(employee.getEmployeeDOJ());
            employeeDTO.setWaiterFlag(employee.isWaiterFlag());
            employeeDTO.setDeliveryFlag(employee.isDeliveryFlag());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setStatus(employee.getStatus());
            list.add(employeeDTO);
        }
        return list;
    }

    public static SMSServer mapSMSServerPojoToEntity(SMSServerDTO smsServerDTO){
        SMSServer smsServer = new SMSServer();
        smsServer.setMessageId(smsServerDTO.getId());
        smsServer.setSmsUrl(smsServerDTO.getSmsURL());
        smsServer.setApiKey(smsServerDTO.getApiKey());
        smsServer.setSenderId(smsServerDTO.getSenderId());
        return smsServer;
    }
    public static List<SMSServerDTO> mapSMSServerEntityToPojo(List<SMSServer> smsServers){
        List<SMSServerDTO> smsServerDTOS=new ArrayList<>();
        for(SMSServer smsServer:smsServers) {
            SMSServerDTO smsServerDTO = new SMSServerDTO();
            smsServerDTO.setId(smsServer.getMessageId());
            smsServerDTO.setSmsURL(smsServer.getSmsUrl());
            smsServerDTO.setApiKey(smsServer.getApiKey());
            smsServerDTO.setSenderId(smsServer.getSenderId());
            smsServerDTOS.add(smsServerDTO);
        }
        return smsServerDTOS;
    }
}
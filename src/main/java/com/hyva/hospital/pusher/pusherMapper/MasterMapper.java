package com.hyva.hospital.pusher.pusherMapper;
import com.google.gson.Gson;
import com.hyva.hospital.pusher.pusherPojo.MasterPojo;
import org.json.JSONException;
import org.json.JSONObject;

public class MasterMapper {
    public static MasterPojo convetToMasterPojo1(Object o) throws JSONException {
        MasterPojo masterPojo=new MasterPojo();

        Gson gson=new Gson();
        String jsonInString=gson.toJson(o);
        JSONObject jsonObj = null;
        try {
             jsonObj = new JSONObject(jsonInString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        masterPojo.setMasterFlag("TEST");

if(o.getClass().getSimpleName().equals("AcademicYearMaster")) {
    masterPojo.setAcdyrId(jsonObj.getString("acdyrId"));
    masterPojo.setAcdyrName(jsonObj.getString("acdyrName"));
    masterPojo.setAcdyrDescription(jsonObj.getString("acdyrDescription"));
    masterPojo.setAcdyrfromDate(jsonObj.getString("fromDate"));
    masterPojo.setAcdyrtoDate(jsonObj.getString("toDate"));

}else if(o.getClass().getSimpleName().equals("FeeReceipt")){
masterPojo.setFeeReceiptID(jsonObj.getString("feeReceiptID"));
masterPojo.setFeeReceiptReceiptNo(jsonObj.getString("receiptNo"));
masterPojo.setFeeReceiptPaymentMode(jsonObj.getString("paymentMode"));
masterPojo.setFeeReceiptCashAmt(jsonObj.getString("cashAmt"));
masterPojo.setFeeReceiptCardAmt(jsonObj.getString("cardAmt"));
masterPojo.setFeeReceiptBankAmt(jsonObj.getString("bankAmt"));
masterPojo.setFeeReceiptChequeDate(jsonObj.getString("chequeDate"));
masterPojo.setFeeReceiptReceiptDate(jsonObj.getString("receiptDate"));
masterPojo.setFeeReceiptChequeNo(jsonObj.getString("chequeNo"));
masterPojo.setFeeReceiptTotalReceived(jsonObj.getString("totalReceived"));
masterPojo.setFeeReceiptTotalPayable(jsonObj.getString("totalPayable"));
masterPojo.setFeeReceiptApprovalCode(jsonObj.getString("approvalCode"));
masterPojo.setFeeReceiptstudentFee(jsonObj.getString("studentFee"));
masterPojo.setFeeReceiptcardNo(jsonObj.getString("cardNo"));
masterPojo.setFeeReceiptbankName(jsonObj.getString("bankName"));
}
else if(o.getClass().getSimpleName().equals("FeeReceiptDetails")){
masterPojo.setDetailsId(jsonObj.getString("detailsId"));
masterPojo.setDetailsFeeReceipt(jsonObj.getString("feeReceipt"));
masterPojo.setDetailsTotalReceived(jsonObj.getString("totalReceived"));
masterPojo.setDetailsFeeType(jsonObj.getString("feeType"));
masterPojo.setDetailsReceiptNo(jsonObj.getString("receiptNo"));
}
else if(o.getClass().getSimpleName().equals("Installments")){
    masterPojo.setInstallmentsId(jsonObj.getString("installmentsId"));
    masterPojo.setInstallmentsAmount(jsonObj.getString("installmentsAmount"));
    masterPojo.setInstallmentsdueDate(jsonObj.getString("dueDate"));
    masterPojo.setInstallmentsstatus(jsonObj.getString("status"));
    masterPojo.setInstallmentsfeeTypeName(jsonObj.getString("feeTypeName"));
    masterPojo.setInstallmentspaidAmt(jsonObj.getString("paidAmt"));
    String feeTypeMasterFK=jsonObj.getString("feeTypeMaster");
    JSONObject jsonObject=new JSONObject(feeTypeMasterFK);
    masterPojo.setInstallmentsfeeTypeMasterFK(jsonObject);
    String studentFeeFK=jsonObj.getString("studentFee");
    JSONObject jsonObject1=new JSONObject(studentFeeFK);
    masterPojo.setInstallmentsstudentFeeFK(jsonObject1);
}
else if(o.getClass().getSimpleName().equals("FeeTypeMaster")){
    masterPojo.setFeeTypeId(jsonObj.getString("feeTypeId"));
    masterPojo.setFeeTypeName(jsonObj.getString("feeTypeName"));
    masterPojo.setFeeTypefeeAmount(jsonObj.getString("feeAmount"));
    masterPojo.setFeeTypestatus(jsonObj.getString("status"));
    masterPojo.setFeeTypepayingFee(jsonObj.getString("payingFee"));
    masterPojo.setFeeTypevalue(jsonObj.getString("value"));
    String academicYearMasterFK = jsonObj.getString("acdyrmaster");
    JSONObject jsonObject = new JSONObject(academicYearMasterFK);
    masterPojo.setFeeTypeAcademicYearMasterFK(jsonObject);
    String gradeMasterFK=jsonObj.getString("gradeMaster");
    JSONObject jsonObjectGr=new JSONObject(gradeMasterFK);
    masterPojo.setFeeTypeGradeMasterFK(jsonObjectGr);
}
else if(o.getClass().getSimpleName().equals("GradeMaster")){
    masterPojo.setGradeId(jsonObj.getString("gradeId"));
    masterPojo.setGradeName(jsonObj.getString("gradeName"));
    masterPojo.setGradeDescription(jsonObj.getString("gradeDescription"));
    masterPojo.setGradeStatus(jsonObj.getString("gradeStatus"));
}
else if(o.getClass().getSimpleName().equals("Student")){
    masterPojo.setStudentId(jsonObj.getString("studentId"));
    masterPojo.setStudentName(jsonObj.getString("studentName"));
    String gradeMasterFK = jsonObj.getString("gradeMaster");
    JSONObject jsonObject = new JSONObject(gradeMasterFK);
    masterPojo.setStudentGradeMasterFK(jsonObject);
    masterPojo.setStudentAdmissionFormNo(jsonObj.getString("admissionFormNo"));
    String academicYearMasterFK = jsonObj.getString("academicYearMaster");
    JSONObject jsonObjectAYM = new JSONObject(academicYearMasterFK);
    masterPojo.setStudentAcademicYearMasterFK(jsonObjectAYM);
    masterPojo.setStudentDateofbirth(jsonObj.getString("dateofbirth"));
    masterPojo.setStudentGender(jsonObj.getString("gender"));
    masterPojo.setStudentDateOfAdmission(jsonObj.getString("dateOfAdmission"));
    masterPojo.setStudentDateOfJoining(jsonObj.getString("dateOfJoining"));
    masterPojo.setStudentProfileId(jsonObj.getString("studentProfileId"));
    masterPojo.setStudentFatherName(jsonObj.getString("fatherName"));
    masterPojo.setStudentFatherOccupation(jsonObj.getString("fatherContactNo"));
    masterPojo.setStudentFatherEmailId(jsonObj.getString("fatherEmailId"));
    masterPojo.setStudentFatherOccupation(jsonObj.getString("fatherOccupation"));
    masterPojo.setStudentMotherName(jsonObj.getString("motherName"));
    masterPojo.setStudentMotherContactNo(jsonObj.getString("motherContactNo"));
    masterPojo.setStudentMotherEmailId(jsonObj.getString("motherEmailId"));
    masterPojo.setStudentMotherOccupation(jsonObj.getString("motherOccupation"));
    masterPojo.setStudentBloodGroup(jsonObj.getString("bloodGroup"));
    masterPojo.setStudentPrimaryContactNo(jsonObj.getString("primaryContactNo"));
//    masterPojo.setStudentAdmissionStatus(jsonObj.getString("admissionStatus"));
    masterPojo.setStudentGaurdianName(jsonObj.getString("gaurdianName"));
//    masterPojo.setStudentAnnualIncome(jsonObj.getString("annualIncome"));
//    masterPojo.setStudentPresentAddress(jsonObj.getString("presentAddress"));
    masterPojo.setStudentPermanentAddress(jsonObj.getString("permanentAddress"));
    masterPojo.setStudentReligion(jsonObj.getString("religion"));
    masterPojo.setStudentPhysicalCondition(jsonObj.getString("physicalCondition"));
//   masterPojo.setStudentDocumentUpload(jsonObj.getString("documentUpload"));





}
else if(o.getClass().getSimpleName().equals("StudentFee")){
    masterPojo.setStudentFeeId(jsonObj.getString("studentFeeId"));
    masterPojo.setStudentFeetotalFeeAmount(jsonObj.getString("totalFeeAmount"));
    masterPojo.setStudentFeeStudentName(jsonObj.getString("StudentName"));
    String academicYearMasterFK=jsonObj.getString("academicYearMaster");
    JSONObject jsonObject=new JSONObject(academicYearMasterFK);
    masterPojo.setStudentFeeacademicYearMasterFK(jsonObject);
    String gradeMasterFK=jsonObj.getString("gradeMaster");
    JSONObject jsonObject1=new JSONObject(gradeMasterFK);
    masterPojo.setStudentFeegradeMasterFK(jsonObject1);
    masterPojo.setStudentFeestudentFK(jsonObj.getString("student"));
    masterPojo.setStudentFeenoOfInstallments(jsonObj.getString("noOfInstallments"));
    masterPojo.setStudentFeepaymentType(jsonObj.getString("paymentType"));
    masterPojo.setStudentFeepaidAmount(jsonObj.getString("paidAmount"));
    masterPojo.setStudentFeechequeNo(jsonObj.getString("chequeNo"));
    masterPojo.setStudentFeepaymentDate(jsonObj.getString("paymentDate"));
    masterPojo.setStudentFeebankName(jsonObj.getString("bankName"));
    masterPojo.setStudentFeeddNo(jsonObj.getString("ddNo"));
    masterPojo.setStudentFeefeeTypeAmount(jsonObj.getString("feeTypeAmount"));
    masterPojo.setStudentFeetotalPayable(jsonObj.getString("totalPayable"));
    masterPojo.setStudentFeedueAmount(jsonObj.getString("dueAmount"));
    masterPojo.setStudentFeepayingFee(jsonObj.getString("payingFee"));
    masterPojo.setStudentFeebankDetails(jsonObj.getString("bankDetails"));
    masterPojo.setStudentFeecardDetails(jsonObj.getString("cardDetails"));
    masterPojo.setStudentFeebankAmt(jsonObj.getString("bankAmt"));
    masterPojo.setStudentFeecardAmt(jsonObj.getString("cardAmt"));
    masterPojo.setStudentFeecashAmt(jsonObj.getString("cashAmt"));

}
else if(o.getClass().getSimpleName().equals("StudentFeeDetails")) {
//    List<StudentFeeDetails> list = new ArrayList<StudentFeeDetails>(Arrays.asList(array));
//    masterPojo.setStudentFeeDetailsId(jsonObj.getString("studentFeeDetailsId"));
    masterPojo .setStudentFeeDetailsfeeTypeName(jsonObj.getString("feeTypeName"));
    masterPojo.setStudentFeeDetailsfeeTypeAmount(jsonObj.getString("feeTypeAmount"));
    //  masterPojo.setStudentFeeDetailscheckboxstatus(jsonObj.getString("ttttttt"));
    masterPojo.setStudentFeeDetailsnoOfInstallments(jsonObj.getString("noOfInstallments"));
    masterPojo.setStudentFeeDetailsinstallmentsAmount(jsonObj.getString("installmentsAmount"));
    masterPojo.setStudentFeeDetailspendingFee(jsonObj.getString("pendingFee"));
    masterPojo.setStudentFeeDetailsstatus(jsonObj.getString("status"));
    // masterPojo.setStudentFeeDetailsdueDate(jsonObj.getString("dueDate"));
    masterPojo.setStudentFeeDetailspayable(jsonObj.getString("payable"));
    masterPojo.setStudentFeeDetailsdiscount(jsonObj.getString("discount"));
    masterPojo.setStudentFeeDetailspaidAmt(jsonObj.getString("paidAmt"));
    //  masterPojo.setStudentFeeDetailspaidDate(jsonObj.getString("paidDate"));
    String feetypemasterFK = jsonObj.getString("feetypemaster");
    JSONObject jsonObject = new JSONObject(feetypemasterFK);
    masterPojo.setStudentFeeDetailsfeetypemasterFK(jsonObject);
    String studentfeeFK = jsonObj.getString("studentfee");
    JSONObject jsonObject1 = new JSONObject(studentfeeFK);
    masterPojo.setStudentFeeDetailsstudentfeeFK(jsonObject1);



}

           return masterPojo;
    }


//    public <T> MasterPojo convetToMasterPojo(T t) {
//
//        MasterPojo masterPojo=new MasterPojo();
//
//
//
//
//        return masterPojo;
//    }
//
//

}

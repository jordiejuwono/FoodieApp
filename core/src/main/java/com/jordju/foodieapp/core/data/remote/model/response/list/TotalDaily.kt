package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class TotalDaily(
    @SerializedName("CA")
    var cA: CA?,
    @SerializedName("CHOCDF")
    var cHOCDF: CHOCDF?,
    @SerializedName("CHOLE")
    var cHOLE: CHOLE?,
    @SerializedName("ENERC_KCAL")
    var eNERCKCAL: ENERCKCAL?,
    @SerializedName("FASAT")
    var fASAT: FASAT?,
    @SerializedName("FAT")
    var fAT: FAT?,
    @SerializedName("FE")
    var fE: FE?,
    @SerializedName("FIBTG")
    var fIBTG: FIBTG?,
    @SerializedName("FOLDFE")
    var fOLDFE: FOLDFE?,
    @SerializedName("K")
    var k: K?,
    @SerializedName("MG")
    var mG: MG?,
    @SerializedName("NA")
    var nA: NA?,
    @SerializedName("NIA")
    var nIA: NIA?,
    @SerializedName("P")
    var p: P?,
    @SerializedName("PROCNT")
    var pROCNT: PROCNT?,
    @SerializedName("RIBF")
    var rIBF: RIBF?,
    @SerializedName("THIA")
    var tHIA: THIA?,
    @SerializedName("TOCPHA")
    var tOCPHA: TOCPHA?,
    @SerializedName("VITA_RAE")
    var vITARAE: VITARAE?,
    @SerializedName("VITB12")
    var vITB12: VITB12?,
    @SerializedName("VITB6A")
    var vITB6A: VITB6A?,
    @SerializedName("VITC")
    var vITC: VITC?,
    @SerializedName("VITD")
    var vITD: VITD?,
    @SerializedName("VITK1")
    var vITK1: VITK1?,
    @SerializedName("ZN")
    var zN: ZN?
)
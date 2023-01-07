package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class TotalNutrients(
    @SerializedName("CA")
    var cA: CA?,
    @SerializedName("CHOCDF")
    var cHOCDF: CHOCDF?,
    @SerializedName("CHOCDF.net")
    var cHOCDFNet: CHOCDFNet?,
    @SerializedName("CHOLE")
    var cHOLE: CHOLE?,
    @SerializedName("ENERC_KCAL")
    var eNERCKCAL: ENERCKCAL?,
    @SerializedName("FAMS")
    var fAMS: FAMS?,
    @SerializedName("FAPU")
    var fAPU: FAPU?,
    @SerializedName("FASAT")
    var fASAT: FASAT?,
    @SerializedName("FAT")
    var fAT: FAT?,
    @SerializedName("FATRN")
    var fATRN: FATRN?,
    @SerializedName("FE")
    var fE: FE?,
    @SerializedName("FIBTG")
    var fIBTG: FIBTG?,
    @SerializedName("FOLAC")
    var fOLAC: FOLAC?,
    @SerializedName("FOLDFE")
    var fOLDFE: FOLDFE?,
    @SerializedName("FOLFD")
    var fOLFD: FOLFD?,
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
    @SerializedName("SUGAR")
    var sUGAR: SUGAR?,
    @SerializedName("SUGAR.added")
    var sUGARAdded: SUGARAdded?,
    @SerializedName("Sugar.alcohol")
    var sugarAlcohol: SugarAlcohol?,
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
    @SerializedName("WATER")
    var wATER: WATER?,
    @SerializedName("ZN")
    var zN: ZN?
)
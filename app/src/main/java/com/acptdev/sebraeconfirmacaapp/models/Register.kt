package com.acptdev.sebraeconfirmacaapp.models

import com.google.gson.annotations.SerializedName

class Register (
    @SerializedName("nome") var nome : String,
    @SerializedName("cpfcnpj") var cpfcnpj : String,
    @SerializedName("email") var email : String,
    @SerializedName("uf") var uf : String,
    @SerializedName("vinculado") var vinculado : Boolean,
    @SerializedName("qual") var qual : String,
    @SerializedName("tipo_participante") var tipoParticipante : String
)
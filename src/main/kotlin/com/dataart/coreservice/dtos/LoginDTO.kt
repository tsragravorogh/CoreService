package com.dataart.coreservice.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class LoginDTO(

    @JsonProperty("email")
    var email: String,

    var password: String
) {
    fun desc(): String {
        val cutDescLength : Int = 15
        return this.javaClass.declaredFields
            .map { m -> m.get(this) }
            .filterNotNull()
            .map { if (it.toString().length > cutDescLength) it.toString().substring(0, cutDescLength) + " " else it.toString() + " " }
            .toString()
    }

}
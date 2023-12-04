package com.v01d.professormanager.entities

import java.util.Date

class Professor(
    var id: Int,
    var first_name:String,
    var last_name:String,
    var dateNaissance: Date?,
    var specialite:Specialite) {

    override fun toString(): String {
        return "$first_name $last_name"
    }
}
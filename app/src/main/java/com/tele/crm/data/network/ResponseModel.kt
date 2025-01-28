package com.tele.crm.data.network;

import java.io.Serializable

class ResponseModel<T> : Serializable {
    private var status_code: String? = null
    private var status: Boolean = false
    private var message: String? = null
    private var data: T? = null

    fun setStatusCode(status_code:String){
        this.status_code=status_code
    }
    fun getStatusCode(): String? {
        return status_code
    }

    fun setStatus(status:Boolean){
        this.status=status
    }

    fun getStatus(): Boolean {
        return status
    }

    fun setMessage(status:String){
        this.message=status
    }

    fun getMessage(): String? {
        return message
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }
}
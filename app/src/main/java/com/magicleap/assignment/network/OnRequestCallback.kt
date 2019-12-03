package com.magicleap.assignment.network


interface OnRequestCallback<T> {
    fun onSuccess(response: T?)

    fun onFailure(errorMessage: String)
}
package com.utn.appsure.db

import com.utn.appsure.model.Policy

class PolicyRepository(db: AppSureDatabase) : BaseRepository(db) {
    fun insertAll(policies: Array<Policy>) {
        db.policyDao().insertAll(*policies)
    }

    fun getAll(): List<Policy> {
        return db.policyDao().getAll()
    }
}
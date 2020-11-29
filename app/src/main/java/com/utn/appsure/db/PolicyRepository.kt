package com.utn.appsure.db

import com.utn.appsure.model.Policy

class PolicyRepository(db: AppSureDatabase) : BaseRepository(db) {
    fun insertAll(policies: Array<Policy>) {
        db.policyDao().insertAll(*policies)
    }

    fun getAll(): List<Policy> {
        return db.policyDao().getAll()
    }

    fun getById(id: String): Policy {
        return db.policyDao().getById(id)
    }

    fun insert(policy: Policy) {
        db.policyDao()
    }
}
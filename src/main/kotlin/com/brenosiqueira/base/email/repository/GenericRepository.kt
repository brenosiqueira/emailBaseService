package com.brenosiqueira.base.email.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

/**
 * Created by breno on 19/06/2016.
 */
@Repository
open class GenericRepository @Autowired constructor(val em : EntityManager) {

    fun save(obj : Any){
        em.persist(obj)
    }
}
package com.juandiegoh.grails_rollback

import grails.transaction.Transactional

class MyHalfTransactionalService {

    @Transactional
    def createMyDomain(String name) {
        def myDomain = new MyDomain(name: name)
        myDomain.save(failOnError: true)
    }

    def nonTransactionalMethod() {
        throw new RuntimeException("This method is NOT transactional, I will not rollback")
    }
}
